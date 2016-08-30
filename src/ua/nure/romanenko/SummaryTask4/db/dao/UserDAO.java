package ua.nure.romanenko.SummaryTask4.db.dao;

import org.apache.log4j.Logger;
import ua.nure.romanenko.SummaryTask4.db.DBManager;
import ua.nure.romanenko.SummaryTask4.db.entity.User;
import ua.nure.romanenko.SummaryTask4.exception.DBException;
import ua.nure.romanenko.SummaryTask4.exception.Messages;
import ua.nure.romanenko.SummaryTask4.web.ban.BanList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * User DAO
 * Created by Роман on 24.08.2016.
 */
public class UserDAO {
    private static final String SQL_UPDATE_USER = "UPDATE users SET  first_name=?, last_name=?"
            + "	WHERE id=?";
    private static final String SQL_ADD_USER_TO_DB = "INSERT INTO users \n" +
            "VALUES(DEFAULT, ?, ?, ?, ?, 2,DEFAULT);";
    private static final String SQL_FIND_ALL_USERS = "SELECT \n" +
            "users.id,\n" +
            "login,\n" +
            "password,\n" +
            "first_name,\n" +
            "last_name,\n" +
            "roles.name as role,\n" +
            "ban\n" +
            "\n" +
            " FROM mydbtest.users\n" +
            "LEFT join roles on roles.id = users.role_id";
    private static final String SQL_BAN_USER_BY_ID = "UPDATE users \n" +
            "SET\n" +
            "ban = ?\n" +
            "WHERE id = ?;";

    private final static Logger LOGGER = Logger.getLogger(UserDAO.class);


    public static boolean addUserToDataBase(User user) throws DBException {
        DBManager manager = null;
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
        DBManager manager = null;
        LOGGER.trace("checkLogin starts");
        if (login != null && !login.isEmpty()) {
            manager = DBManager.getInstance();
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;

            try {
                con = manager.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery("SELECT login FROM users WHERE login = '" + login + "'");


                if (rs.next()) {
                    if (rs.getString("login").equals(login)) {
                        LOGGER.trace("find this login in Data Base --> " + rs.getString("login"));
                        return false;
                    }
                }
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            LOGGER.trace("Din't find that login, operation complete");
        }
        return false;
    }

    public static List<User> findAllUsers() throws DBException {
        DBManager manager = DBManager.getInstance();

        LOGGER.trace("findAllUsers starts");

        List<User> list = new ArrayList<>();


        Statement stmt = null;
        Connection con = null;
        ResultSet rs = null;

        try {
            con = manager.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_FIND_ALL_USERS);

            while (rs.next()) {
                list.add(manager.extractUser(rs));
            }

        } catch (SQLException | DBException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static boolean banUsers(String[] listID) throws DBException {
        if (listID != null) {
            LOGGER.trace("banUsers method starts");
            DBManager manager = DBManager.getInstance();
            PreparedStatement ps = null;
            Connection con = null;
            try {
                con = manager.getConnection();
                con.setAutoCommit(false);

                ps = con.prepareStatement(SQL_BAN_USER_BY_ID);
                for (String aListID : listID) {
                    LOGGER.trace("current id element --> " + aListID);
                    ps.setBoolean(1, true);
                    ps.setInt(2, Integer.parseInt(aListID));
                    ps.executeUpdate();
                }
                con.commit();

                BanList.getBanList().clear();
                BanList.getBannedUsersFromDB();
                LOGGER.trace("ban list --> "+BanList.getBanList());

                LOGGER.trace("banUsers method finished");
                return true;
            } catch (SQLException e) {
                manager.rollback(con);
                LOGGER.error("error with ban users", e);
                throw new DBException("error with ban users", e);
            } finally {
                manager.close(con);
                manager.close(ps);
            }
        }
        return false;

    }
    public static boolean unBanUsers(String[] listID) throws DBException {
        if (listID != null) {
            LOGGER.trace("unBanUsers method starts");
            DBManager manager = DBManager.getInstance();
            PreparedStatement ps = null;
            Connection con = null;
            try {
                con = manager.getConnection();
                con.setAutoCommit(false);

                ps = con.prepareStatement(SQL_BAN_USER_BY_ID);
                for (String aListID : listID) {
                    LOGGER.trace("current id element --> " + aListID);
                    ps.setBoolean(1, false);
                    ps.setInt(2, Integer.parseInt(aListID));
                    ps.executeUpdate();
                    LOGGER.trace("User unBanned --> "
                            +BanList.unBanUser(Integer.parseInt(aListID)));
                }
                con.commit();

                LOGGER.trace("unBanUsers method finished");

                return true;
            } catch (SQLException e) {
                manager.rollback(con);
                LOGGER.error("error with ban users", e);
                throw new DBException("error with ban users", e);
            } finally {
                manager.close(con);
                manager.close(ps);
            }
        }
        return false;

    }

    public static boolean updateUser(User user, String firstName, String lastName)
            throws DBException {
        LOGGER.trace("update method starts");
        DBManager manager = DBManager.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = manager.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(SQL_UPDATE_USER);
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setInt(3, user.getId());
            ps.executeUpdate();

            connection.commit();

        } catch (DBException | SQLException e) {
            manager.close(ps);
            manager.close(connection);
            LOGGER.error("cannot update user",e);
        }
        LOGGER.trace("update method finished");
        return true;
    }

}