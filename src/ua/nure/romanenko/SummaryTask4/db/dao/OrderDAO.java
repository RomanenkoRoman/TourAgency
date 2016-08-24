package ua.nure.romanenko.SummaryTask4.db.dao;

import ua.nure.romanenko.SummaryTask4.db.DBManager;
import ua.nure.romanenko.SummaryTask4.db.Status;
import ua.nure.romanenko.SummaryTask4.db.bean.ExtendedUserOrderBean;
import ua.nure.romanenko.SummaryTask4.exception.DBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Роман on 24.08.2016.
 */
public class OrderDAO {

    private static final String SQL_UPDATE_STATUS = "UPDATE \n" +
            "\torders_catalog\n" +
            "SET status_id = ?\n" +// 1 status id
            "\n" +
            "WHERE order_id=? AND catalog_id =?"; //2 order_id 3 catalog_id


    public static void updateStatusInOrder
            (ExtendedUserOrderBean extendedUserOrderBean,String status)
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


}
