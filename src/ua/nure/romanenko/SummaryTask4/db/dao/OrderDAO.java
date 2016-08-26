package ua.nure.romanenko.SummaryTask4.db.dao;

import ua.nure.romanenko.SummaryTask4.db.DBManager;
import ua.nure.romanenko.SummaryTask4.db.Status;
import ua.nure.romanenko.SummaryTask4.db.bean.ExtendedUserOrderBean;
import ua.nure.romanenko.SummaryTask4.db.entity.CatalogItem;
import ua.nure.romanenko.SummaryTask4.db.entity.User;
import ua.nure.romanenko.SummaryTask4.exception.DBException;

import java.sql.*;
import java.util.List;

/**
 * OrderDao
 * Created by Роман on 24.08.2016.
 */
public class OrderDAO {

    private static final String SQL_UPDATE_STATUS = "UPDATE \n" +
            "\torders_catalog\n" +
            "SET status_id = ?\n" +// 1 status id
            "\n" +
            "WHERE order_id=? AND catalog_id =?"; //2 order_id 3 catalog_id
    private static final String SQL_CREATE_ORDER = "INSERT INTO orders \n" +
            "\n" +
            "SET\n" +
            " id = ?,\n" +
            " bill = (SELECT sum(price)FROM mydbtest.catalog \n" +
            " INNER JOIN(SELECT catalog_id\n" +
            "                FROM orders_catalog\n" +
            "                WHERE order_id=(SELECT max(id)+1 FROM orders)) orders_catalog\n" +
            "                ON catalog.id=orders_catalog.catalog_id),\n" +
            "user_id =?;";
    private static final String SQL_CREATE_ORDER_CATALOG_DEPENDENCY = "INSERT INTO orders_catalog \n" +
            "VALUES ((SELECT max(id)+1 FROM mydbtest.orders),?,0,0);";
    private static final String SQL_UPDATE_DISCOUNT = "UPDATE orders_catalog\n" +
            "SET\n" +
            " discount = ?\n" +
            "WHERE order_id = ? and catalog_id = ?;";


    public static void updateStatusInOrder
            (ExtendedUserOrderBean extendedUserOrderBean, String status)
            throws DBException {
        Status st = Status.valueOf(status.toUpperCase());
        int numOfStatus = st.ordinal();

        DBManager manager = DBManager.getInstance();
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = manager.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(SQL_UPDATE_STATUS);
            ps.setInt(1, numOfStatus);
            ps.setInt(2, extendedUserOrderBean.getOrderId());
            ps.setInt(3, extendedUserOrderBean.getCatalogId());
            ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            manager.rollback(conn);
//            e.printStackTrace();
            throw new DBException("Cannot change status", e);
        } finally {
            manager.close(ps);
            manager.close(conn);
        }


    }

    public static void confirmOrder(List<CatalogItem> list, User user) throws DBException {
        DBManager manager = DBManager.getInstance();
        Connection con = null;
        Statement stmt = null;

        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            con = manager.getConnection();
            con.setAutoCommit(false);


            for (int i = 0; i < list.size(); i++) {
                ps = con.prepareStatement(SQL_CREATE_ORDER_CATALOG_DEPENDENCY);
                ps.setInt(1, list.get(i).getId());
                ps.executeUpdate();
            }


            stmt = con.createStatement();
            resultSet = stmt.executeQuery("(SELECT max(id)+1 FROM mydbtest.orders)");
            resultSet.next();
            int maxId = resultSet.getInt("max(id)+1");


            ps = con.prepareStatement(SQL_CREATE_ORDER);
            ps.setInt(1, maxId);
            ps.setInt(2, user.getId());
            ps.executeUpdate();

            con.commit();
        } catch (SQLException e) {
            manager.rollback(con);

            e.printStackTrace();
        }

    }

    public static void updateDiscount(ExtendedUserOrderBean extendedUserOrderBean,
                                      String discount) throws DBException {
        DBManager manager = DBManager.getInstance();
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = manager.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(SQL_UPDATE_DISCOUNT);
            ps.setInt(1, Integer.parseInt(discount));
            ps.setInt(2, extendedUserOrderBean.getOrderId());
            ps.setInt(3, extendedUserOrderBean.getCatalogId());
            ps.executeUpdate();

            conn.commit();
        } catch (SQLException e) {
            manager.rollback(conn);
            e.printStackTrace();
        }finally {
            manager.close(conn);
            manager.close(ps);
        }

    }
}
