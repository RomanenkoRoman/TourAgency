package ua.nure.romanenko.SummaryTask4.db.dao;

import org.apache.log4j.Logger;
import ua.nure.romanenko.SummaryTask4.db.DBManager;
import ua.nure.romanenko.SummaryTask4.db.entity.Discount;
import ua.nure.romanenko.SummaryTask4.exception.DBException;

import java.sql.*;


/**
 * DiscountDAO
 * Created by Роман on 26.08.2016.
 */
public class DiscountDAO {
    private static final Logger LOGGER = Logger.getLogger(DiscountDAO.class);
    private static final String SQL_UPDATE_DISCOUNT = "UPDATE discount\n" +
            "SET max=?,\n" +
            "\tstep=?\n" +
            "WHERE id =1;";
    private static final String SQL_GET_DISCOUNT = "SELECT max,step from discount\n" +
            "WHERE id = 1;";

    public static void updateDiscount(int max, int step) throws DBException {
        LOGGER.trace("method updateDiscount starts");

        DBManager manager = DBManager.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = manager.getConnection();
            connection.setAutoCommit(false);

            ps = connection.prepareStatement(SQL_UPDATE_DISCOUNT);
            ps.setInt(1, max);
            ps.setInt(2, step);
            ps.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            manager.rollback(connection);
            LOGGER.error("Cannot update discount ",e);
        } finally {
            manager.close(connection);
            manager.close(ps);
        }
        LOGGER.trace("method updateDiscount finished");
    }

    public static Discount getDiscount() throws DBException {
        LOGGER.trace("method getDiscount starts");
        DBManager manager = DBManager.getInstance();
        Discount discount = null;
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            connection = manager.getConnection();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(SQL_GET_DISCOUNT);
            rs.next();
            discount = new Discount(rs.getInt("max"),rs.getInt("step"));
        } catch (SQLException e) {
            LOGGER.error("cannot take the discount from db",e);
        } finally {
            manager.close(connection,stmt,rs);
        }
        LOGGER.trace("method getDiscount finished");
        return discount;
    }
}
