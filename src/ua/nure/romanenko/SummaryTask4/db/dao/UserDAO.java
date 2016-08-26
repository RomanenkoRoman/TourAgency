package ua.nure.romanenko.SummaryTask4.db.dao;

import org.apache.log4j.Logger;
import ua.nure.romanenko.SummaryTask4.db.DBManager;
import ua.nure.romanenko.SummaryTask4.db.entity.User;
import ua.nure.romanenko.SummaryTask4.exception.DBException;

import java.sql.*;

/**
 *
 * Created by Роман on 24.08.2016.
 */
public class UserDAO {
    private static final String SQL_UPDATE_USER = "UPDATE users SET password=?, first_name=?, last_name=?"
            + "	WHERE id=?";
    private static final String SQL_ADD_USER_TO_DB = "INSERT INTO users \n" +
            "VALUES(DEFAULT, ?, ?, ?, ?, 2);";
    private final static Logger LOGGER = Logger.getLogger(UserDAO.class);
    private static DBManager manager;

    public static boolean addUserToDataBase(User user) throws DBException {
        LOGGER.trace("addUserToDataBase starts");
        if (checkLogin(user.getLogin())) {
            manager = DBManager.getInstance();
            Connection con = null;
            PreparedStatement ps = null;
            try {
                con = manager.getConnection();
                con.setAutoCommit(false);

                ps = con.prepareStatement(SQL_ADD_USER_TO_DB);
                ps.setString(1, user.getLogin());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getFirstName());
                ps.setString(4, user.getLastName());
                ps.executeUpdate();

                con.commit();
                LOGGER.trace("addition successful");
                return true;
            } catch (SQLException e) {
                manager.rollback(con);
                LOGGER.error("addition fail", e);
            } finally {
                manager.close(con);
                manager.close(ps);
            }
        }
        LOGGER.trace("addUserToDataBase finished");
        return false;

    }

    private static boolean checkLogin(String login) throws DBException {
        LOGGER.trace("checkLogin starts");
        manager = DBManager.getInstance();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            con = manager.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT login FROM users WHERE login = '" + login+"'");

            rs.next();

            if (rs.getString("login").equals(login)) {
                LOGGER.trace("find this login in Data Base --> " + rs.getString("login"));
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LOGGER.trace("Din't find that login, operation complete");
        return true;
    }

}
