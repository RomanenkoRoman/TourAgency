package ua.nure.romanenko.SummaryTask4.db.dao;

import org.apache.log4j.Logger;
import ua.nure.romanenko.SummaryTask4.db.DBManager;
import ua.nure.romanenko.SummaryTask4.db.entity.CatalogItem;
import ua.nure.romanenko.SummaryTask4.db.entity.TourType;
import ua.nure.romanenko.SummaryTask4.db.entity.TypeHotel;
import ua.nure.romanenko.SummaryTask4.exception.DBException;
import ua.nure.romanenko.SummaryTask4.exception.Messages;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Роман on 23.08.2016.
 */
public class CatalogItemDAO {
    private static final Logger LOG = Logger.getLogger(CatalogItemDAO.class);
    private static final String SQL_UPDATE_HOT = "UPDATE \n" +
            "catalog \n" +
            "SET\n" +
            "hot = ?\n" +
            "WHERE\n" +
            "id = ?;";

    private static final String SQL_FILTER_CATALOG = "SELECT \n" +
            "catalog.hot as 'hot',\n" +
            "catalog.id,\n" +
            "catalog.name as 'Hotel',\n" +
            "categories.name as 'Category',\n" +
            "type_hotel.name as 'Hotel type',\n" +
            "catalog.q_people,\n" +
            "catalog.price\n" +
            "FROM catalog\n" +
            "LEFT JOIN categories ON categories.id = catalog.category_id \n" +
            "LEFT JOIN type_hotel on catalog.type_hotel_id = type_hotel.id\n" +
            "\n" +
            "WHERE catalog.price BETWEEN ? AND ?\n" +
            "AND categories.id BETWEEN ? AND ?\n" +
            "AND catalog.type_hotel_id BETWEEN ? AND ?\n" +
            "AND catalog.q_people BETWEEN ? AND ?\n" +
            "\n" +
            "ORDER BY catalog.id AND catalog.hot DESC;";

    public static boolean doHot(String[] listID) throws DBException {
        boolean cur = false;
        if (listID != null) {
            LOG.trace("do hot method starts");
            DBManager manager = DBManager.getInstance();
            PreparedStatement ps = null;
            Connection con = null;
            try {
                con = manager.getConnection();
                con.setAutoCommit(false);

                ps = con.prepareStatement(SQL_UPDATE_HOT);
                for (String aListID : listID) {
                    ps.setBoolean(1, true);
                    ps.setInt(2, Integer.parseInt(aListID));
                    cur = 0 < ps.executeUpdate();
                }
                con.commit();
                LOG.trace("do hot method finished");

                return cur;
            } catch (SQLException e) {
                manager.rollback(con);
                LOG.error("error with update", e);
                throw new DBException(Messages.ERR_CANNOT_CHANGE_HOT_ATTRIBUTE, e);
            } finally {
                manager.close(con);
                manager.close(ps);
            }

        }
        return false;
    }

    public static boolean doUnHot(String[] listID) throws DBException {
        boolean cur = false;
        if (listID != null) {
            LOG.trace("do UnHot method starts");
            DBManager manager = DBManager.getInstance();
            PreparedStatement ps = null;
            Connection con = null;
            try {
                con = manager.getConnection();
                con.setAutoCommit(false);

                ps = con.prepareStatement(SQL_UPDATE_HOT);
                for (String aListID : listID) {
                    LOG.trace("current id element --> " + aListID);
                    ps.setBoolean(1, false);
                    ps.setInt(2, Integer.parseInt(aListID));
                    cur = 0 < ps.executeUpdate();
                }
                con.commit();

                LOG.trace("do unHot method finished");
                return cur;
            } catch (SQLException e) {
                manager.rollback(con);
                LOG.error("error with update", e);
                throw new DBException(Messages.ERR_CANNOT_CHANGE_HOT_ATTRIBUTE, e);
            } finally {
                manager.close(con);
                manager.close(ps);
            }

        }
        return false;
    }

    public static List<CatalogItem> filterCatalog(String price, String category,
                                                  String type, String people) throws DBException {
        LOG.trace("filterCatalog method starts");
        LOG.trace("argument price --> " + price);
        LOG.trace("argument category --> " + category);
        LOG.trace("argument type --> " + type);
        LOG.trace("argument people --> " + people);


        List<CatalogItem> list = new ArrayList<>();
        int lowBorderPrice = 0;
        int highBorderPrice = 0;
        if (price == null) {
            lowBorderPrice = 0;
            highBorderPrice = 99999;
        } else if (price.equals("1")) {
            lowBorderPrice = 0;
            highBorderPrice = 700;
        } else if (price.equals("2")) {
            lowBorderPrice = 700;
            highBorderPrice = 2100;
        } else if (price.equals("3")) {
            lowBorderPrice = 2100;
            highBorderPrice = 99999;
        }

        int[] pairCat = borderReveal(category, "TourType");
        int lowBorderCategory = pairCat[0];
        int highBorderCategory = pairCat[1];


        int[] pairType = borderReveal(type, "TypeHotel");
        int lowBorderHotelType = pairType[0];
        int highBorderHotelType = pairType[1];


        int lowBorderPeople = 0;
        int highBorderPeople = 0;
        if (people == null) {
            lowBorderPeople = 0;
            highBorderPeople = 9999;
        } else {
            lowBorderPeople = Integer.parseInt(people);
            highBorderPeople = Integer.parseInt(people);
        }

        DBManager manager = DBManager.getInstance();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        LOG.trace("lowBorderPrice --> " + lowBorderPrice);
        LOG.trace("highBorderPrice --> " + highBorderPrice);
        LOG.trace("lowBorderCategory --> " + lowBorderCategory);
        LOG.trace("highBorderCategory --> " + highBorderCategory);
        LOG.trace("lowBorderHotelType --> " + lowBorderHotelType);
        LOG.trace("highBorderHotelType --> " + highBorderHotelType);
        LOG.trace("lowBorderPeople --> " + lowBorderPeople);
        LOG.trace("highBorderPeople --> " + highBorderPeople);
        try {
            conn = manager.getConnection();
            ps = conn.prepareStatement(SQL_FILTER_CATALOG);
            ps.setInt(1, lowBorderPrice);
            ps.setInt(2, highBorderPrice);
            ps.setInt(3, lowBorderCategory);
            ps.setInt(4, highBorderCategory);
            ps.setInt(5, lowBorderHotelType);
            ps.setInt(6, highBorderHotelType);
            ps.setInt(7, lowBorderPeople);
            ps.setInt(8, highBorderPeople);

            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                list.add(manager.extractCatalogItem(resultSet));
            }

        } catch (SQLException e) {
            LOG.error("cannot filter the catalog ", e);
            throw new DBException("cannot filter the catalog", e);
        } finally {
            manager.close(conn, ps, resultSet);
        }
        LOG.trace("filterCatalog method finished");
        return list;

    }

    private static int[] borderReveal(String type, String en) {


        int[] arr = new int[2];

        int lowBorder = 0;
        int highBorder = 0;
        if (type == null) {
            lowBorder = 0;
            highBorder = 99999;
        } else {
            if (en.equals("TourType")) {
                lowBorder = TourType.valueOf(type.toUpperCase()).ordinal();
                highBorder = TourType.valueOf(type.toUpperCase()).ordinal();
            } else {
                lowBorder = TypeHotel.valueOf(type.toUpperCase()).ordinal();
                highBorder = TypeHotel.valueOf(type.toUpperCase()).ordinal();
            }
        }
        arr[0] = lowBorder;
        arr[1] = highBorder;
        return arr;
    }


}
