package ua.nure.romanenko.SummaryTask4.web.ban;

import org.apache.log4j.Logger;
import ua.nure.romanenko.SummaryTask4.db.DBManager;
import ua.nure.romanenko.SummaryTask4.db.entity.User;
import ua.nure.romanenko.SummaryTask4.exception.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * BanList
 * Created by Роман on 27.08.2016.
 */
public class BanList {
    private static final Logger LOG = Logger.getLogger(BanList.class);

    private static List<User> banList = new ArrayList<>();

    static {
        getBannedUsersFromDB();
        LOG.debug("BanList was successfully initialized");
        LOG.trace("Number of ban users --> " + banList.size());
    }


    public static List<User> getBanList() {
        return banList;
    }

    public static boolean unBanUser(int id) {
        for (int i = 0; i < banList.size(); i++) {
            if (banList.get(i).getId() == id) {
                banList.remove(i);
                return true;
            }
        }
        return false;
    }



    public static void getBannedUsersFromDB() {
        LOG.trace("getBannedUsersFromDB method starts");

        DBManager manager = null;

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            manager = DBManager.getInstance();
            con = manager.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT \n" +
                    "users.id,\n" +
                    "login,\n" +
                    "password,\n" +
                    "first_name,\n" +
                    "last_name,\n" +
                    "ban,\n" +
                    "roles.name as role\n" +
                    "\n" +
                    "FROM users \n" +
                    "\n" +
                    "inner join roles on users.role_id = roles.id\n" +
                    "\n" +
                    "WHERE ban = true;");
            while (rs.next()) {
                User user = manager.extractUser(rs);
                banList.add(user);
            }
        } catch (SQLException | DBException e) {
            LOG.error("error with connection or get ban list from db", e);
            e.printStackTrace();
        }

    }

}
