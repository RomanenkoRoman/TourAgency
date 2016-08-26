package ua.nure.romanenko.SummaryTask4.db;

import org.apache.log4j.Logger;
import ua.nure.romanenko.SummaryTask4.db.bean.ExtendedUserOrderBean;
import ua.nure.romanenko.SummaryTask4.db.bean.UserOrderBean;
import ua.nure.romanenko.SummaryTask4.db.entity.CatalogItem;
import ua.nure.romanenko.SummaryTask4.db.entity.Category;
import ua.nure.romanenko.SummaryTask4.db.entity.Order;
import ua.nure.romanenko.SummaryTask4.db.entity.User;
import ua.nure.romanenko.SummaryTask4.exception.DBException;
import ua.nure.romanenko.SummaryTask4.exception.Messages;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DB manager.
 */
public final class DBManager {

    private static final Logger LOG = Logger.getLogger(DBManager.class);

    private static DBManager instance;

    public static synchronized DBManager getInstance() throws DBException {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    private DataSource ds;

    private DBManager() throws DBException {
        try {

            Context initContext = new InitialContext();

            Context envContext = (Context) initContext.lookup("java:/comp/env/");
            // ST4DB - the name of data source
            ds = (DataSource) envContext.lookup("jdbc/mydbtest");
            LOG.trace("Data source ==> " + ds);
        } catch (NamingException ex) {
            ex.printStackTrace();
            LOG.error(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
        }
    }


    // //////////////////////////////////////////////////////////
    // SQL queries
    // //////////////////////////////////////////////////////////

    private static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";

    private static final String SQL_FIND_ALL_USERS = "SELECT * FROM users";

    private static final String SQL_FIND_ALL_ORDERS = "SELECT * FROM orders";

    private static final String SQL_FIND_USER_BY_ID = "SELECT * FROM users WHERE id=?";

    private static final String SQL_FIND_ALL_CATALOG_ITEMS = "SELECT \n" +
            "catalog.hot as 'hot',\n" +
            "catalog.id,\n" +
            "catalog.name as 'Hotel',\n" +
            "categories.name as 'Category',\n" +
            "type_hotel.name as 'Hotel type',\n" +
            "catalog.q_people,\n" +
            "catalog.price\n" +
            "FROM catalog\n" +
            "LEFT JOIN categories ON categories.id = catalog.category_id \n" +
            "LEFT JOIN type_hotel on catalog.type_hotel_id = type_hotel.id \n" +
            "\n" +
            "\n" +
            "ORDER BY catalog.id AND catalog.hot DESC ";

    private static final String SQL_FIND_ORDERS_BY_STATUS_AND_USER = "SELECT * FROM orders WHERE status_id=? AND user_id=?";

    private static final String SQL_FIND_CATALOG_ITEMS_BY_ORDER = "select * from catalog where id in (select catalog_id from orders_catalog where order_id=?)";

    private static final String SQL_FIND_ORDERS_BY_STATUS = "SELECT * FROM orders WHERE status_id=?";

    private static final String SQL_FIND_ALL_CATEGORIES = "SELECT * FROM categories";

    private static final String SQL_UPDATE_USER = "UPDATE users SET password=?, first_name=?, last_name=?"
            + "	WHERE id=?";
    //    private static final String SQL_GET_USER_ORDER_BEANS = "SELECT o.id, u.first_name, u.last_name, o.bill, s.name"
//            + "	FROM users u, orders o, statuses s"
//            + "	WHERE o.user_id=u.id AND o.status_id=s.id";
    /*  private static final String SQL_GET_USER_ORDER_BEANS = "SELECT DISTINCT o.id, u.first_name, u.last_name, o.bill, s.name" +
              "FROM users u, orders o, statuses s ,orders_catalog o_r" +
              "WHERE o.user_id=u.id AND s.id=o_r.status_id";
        *
    private static final String SQL_GET_USER_ORDER_BEANS  ="Select"+
"`usr`.`first_name` as `First name`, `usr`.`last_name` as `Last name`," +
            "`ct`.`name` as `Hotel name`,"+
"`ct`.`price` as `Hotel price`,`t_hl`.`name` as `Hotel type`,`ctrs`.`name` as `Category name`,"+
"`st`.`name` as `Status`"+

"From`users` as `usr`"+
"Left Join `orders` as `od` on `usr`.`id` = `od`.`user_id`"+
"LEFT Join `orders_catalog` as `od_ct` on `od`.`id` = `od_ct`.`order_id`"+
"LEFT Join `statuses` as `st` on `st`.`id` = `od_ct`.`status_id`"+
"Left Join `catalog` as `ct` on `ct`.`id` = `od_ct`.`catalog_id`#"+
"LEFT Join `type_hotel` as `t_hl` on `t_hl`.`id` = `ct`.`type_hotel_id`"+
"LEFT Join `categories` as `ctrs` on `ctrs`.`id` = `ct`.`category_id`"+

"Where `usr`.`id` in (SELECT id from users)";

*/
    private static final String SQL_GET_EXTENDED_USER_ORDER_BEANS = "Select \n" +
            "catalog.id as catalogId,\n" +
            "orders.id as orderNum,\n" +
            "q_people,\n" +
            "`users`.`first_name` as `First name`, \n" +
            "`users`.`last_name` as `Last name`, \n" +
            "`catalog`.`name` as `Hotel name`,\n" +
            "`catalog`.`price` as `Hotel price`,\n" +
            "`type_hotel`.`name` as `Hotel type`,\n" +
            "`categories`.`name` as `Category name`,\n" +
            "`statuses`.`name` as `Status`,\n" +
            " orders_catalog.discount\n" +
            " From `users`\n" +
            " Left Join `orders` on `users`.`id` = `orders`.`user_id` \n" +
            " LEFT Join `orders_catalog` on `orders`.`id` = `orders_catalog`.`order_id`\n" +
            " LEFT Join `statuses` on `statuses`.`id` = `orders_catalog`.`status_id`\n" +
            " INNER Join `catalog` on `catalog`.`id` = `orders_catalog`.`catalog_id`\n" +
            " LEFT Join `type_hotel` on `type_hotel`.`id` = `catalog`.`type_hotel_id`\n" +
            " LEFT Join `categories` on `categories`.`id` = `catalog`.`category_id`\n" +
            "\n" +
            " Where `users`.`id` in (SELECT id from users) order BY users.id";
    private static final String SQL_GET_EXTENDED_USER_ORDER_BEAN = "Select \n" +
            "orders.id as orderNum,\n" +
            "catalog.id as catalogId,\n" +
            "q_people,\n" +
            "`users`.`first_name` as `First name`, \n" +
            "`users`.`last_name` as `Last name`, \n" +
            "`catalog`.`name` as `Hotel name`,\n" +
            "`catalog`.`price` as `Hotel price`,\n" +
            "`type_hotel`.`name` as `Hotel type`,\n" +
            "`categories`.`name` as `Category name`,\n" +
            "`statuses`.`name` as `Status`,\n" +
            "orders_catalog.discount\n" +
            "From`users`\n" +
            "Left Join `orders` on `users`.`id` = `orders`.`user_id` \n" +
            "LEFT Join `orders_catalog` on `orders`.`id` = `orders_catalog`.`order_id`\n" +
            "LEFT Join `statuses` on `statuses`.`id` = `orders_catalog`.`status_id`\n" +
            "INNER Join `catalog` on `catalog`.`id` = `orders_catalog`.`catalog_id`\n" +
            "LEFT Join `type_hotel` on `type_hotel`.`id` = `catalog`.`type_hotel_id`\n" +
            "LEFT Join `categories` on `categories`.`id` = `catalog`.`category_id`\n" +
            "Where `users`.`id` = ? order BY users.id AND hot DESC";
    private static final String SQL_GET_USER_ORDER_BEANS = "Select \n" +
            "`users`.`first_name` as `First name`, `users`.`last_name` as `Last name`, \n" +
            "#`od`.`bill` as `Bill`,\n" +
            "`catalog`.`name` as `Hotel name`,\n" +
            "`catalog`.`price` as `Hotel price`,`type_hotel`.`name` as `Hotel type`,\n" +
            "`categories`.`name` as `Category name`,\n" +
            "`statuses`.`name` as `Status`\n" +
            "\n" +
            "From`users`\n" +
            "Left Join `orders` on `users`.`id` = `orders`.`user_id`   #\n" +
            "LEFT Join `orders_catalog` on `orders`.`id` = `orders_catalog`.`order_id`\n" +
            "LEFT Join `statuses` on `statuses`.`id` = `orders_catalog`.`status_id`\n" +
            "INNER Join `catalog` on `catalog`.`id` = `orders_catalog`.`catalog_id`#\n" +
            "LEFT Join `type_hotel` on `type_hotel`.`id` = `catalog`.`type_hotel_id`\n" +
            "LEFT Join `categories`  on `categories`.`id` = `catalog`.`category_id`\n" +
            "\n" +
            "Where `users`.`id` in (SELECT id from users)";
    private static final String SQL_GET_CATALOG_ITEMS_BY_USER = "Select \n" +
            "catalog.hot,"+
            "catalog.id,"+
            "orders.id AS orderNum,"+
            "`users`.`first_name` as `First name`, \n" +
            "`users`.`last_name` as `Last name`, \n" +
            "`catalog`.`name` as `Hotel`,\n" +
            "`catalog`.`price` as `price`,\n" +
            "`type_hotel`.`name` as `Hotel type`,\n" +
            "`categories`.`name` as `Category`,\n" +
            "`catalog`.`q_people`,\n"+
            "`statuses`.`name` as `Status`\n" +
            "From`users` \n" +
            "Left Join `orders` on `users`.`id` = `orders`.`user_id`   #\n" +
            "LEFT Join `orders_catalog` on `orders`.`id` = `orders_catalog`.`order_id`\n" +
            "LEFT Join `statuses` on `statuses`.`id` = `orders_catalog`.`status_id`\n" +
            "INNER Join `catalog` on `catalog`.`id` = `orders_catalog`.`catalog_id`#\n" +
            "LEFT Join `type_hotel` on `type_hotel`.`id` = `catalog`.`type_hotel_id`\n" +
            "LEFT Join `categories` on `categories`.`id` = `catalog`.`category_id`\n" +
            "Where `users`.`id` in (?) order BY users.id";
    private static final String SQL_CREATE_ORDER = "INSERT INTO orders VALUES(DEFAULT,\n" +
            "(SELECT sum(price) FROM mydbtest.catalog INNER JOIN\n" +
            "\t(\n" +
            "    SELECT catalog_id\n" +
            "    FROM orders_catalog\n" +
            "    WHERE order_id=?\n" +
            "\t) orders_catalog\n" +
            "    ON catalog.id=orders_catalog.catalog_id\n" +
            "), ?, ?);";
    private static final String SQL_CREATE_ORDER_CATALOG_DEPENDENCY =
            "INSERT INTO orders_catalog VALUES(?,?,0)";
    private static final String SQL_FIND_MAX_ID_NUMBER = "SELECT max(id) FROM mydbtest.orders;";

    /**
     * Returns a DB connection from the Pool Connections. Before using this
     * method you must configure the Date Source and the Connections Pool in
     * your WEB_APP_ROOT/META-INF/context.xml file.
     *
     * @return DB connection.
     */
    public Connection getConnection() throws DBException, SQLException {

        Connection con = null;
        try {
            con = ds.getConnection();
        } catch (SQLException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
//			ex.printStackTrace();
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
        }

        return con;
    }

    // //////////////////////////////////////////////////////////s
    // Methods to obtain beans
    // //////////////////////////////////////////////////////////

    /**
     * Returns all categories.
     *
     * @return List of category entities.
     */
    public List<UserOrderBean> getUserOrderBeans() throws DBException {
        List<UserOrderBean> orderUserBeanList = new ArrayList<UserOrderBean>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_GET_USER_ORDER_BEANS);
            while (rs.next()) {
                orderUserBeanList.add(extractUserOrderBean(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_USER_ORDER_BEANS, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_ORDER_BEANS, ex);
        } finally {
            close(con, stmt, rs);
        }
        return orderUserBeanList;
    }

    public List<ExtendedUserOrderBean> getExtendedUserOrderBean() throws DBException {
        List<ExtendedUserOrderBean> list = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_GET_EXTENDED_USER_ORDER_BEANS);

            while (rs.next()) {
                list.add(extractExtendedUserOrderBean(rs));
            }
            con.commit();
        } catch (SQLException e) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_USER_ORDER_BEANS, e);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_ORDER_BEANS, e);
        } finally {
            close(con, stmt, rs);
        }
        return list;
    }
    public List<ExtendedUserOrderBean> getExtendedUserOrderBean(User user) throws DBException {
        List<ExtendedUserOrderBean> list = new ArrayList<>();
        PreparedStatement ps = null;

        ResultSet rs = null;
        Connection con = null;

        try {
            con = getConnection();
            con.setAutoCommit(false);

            ps = con.prepareStatement(SQL_GET_EXTENDED_USER_ORDER_BEAN);
            ps.setInt(1,user.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(extractExtendedUserOrderBean(rs));
            }
            con.commit();
        } catch (SQLException e) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_USER_ORDER_BEANS, e);
        } finally {
            close(con, ps, rs);
        }
        return list;
    }

    // //////////////////////////////////////////////////////////
    // Entity access methods
    // //////////////////////////////////////////////////////////

    /**
     * Returns all categories.
     *
     * @return List of category entities.
     */
    public List<Category> findCategories() throws DBException {
        List<Category> categoriesList = new ArrayList<Category>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_FIND_ALL_CATEGORIES);
            while (rs.next()) {
                categoriesList.add(extractCategory(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);
        } finally {
            close(con, stmt, rs);
        }
        return categoriesList;
    }

    /**
     * Returns all menu items.
     *
     * @return List of menu item entities.
     */
    public List<CatalogItem> findCatalogItems() throws DBException {
        List<CatalogItem> catalogItemList = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            stmt = con.createStatement();
            con.setAutoCommit(false);
            rs = stmt.executeQuery(SQL_FIND_ALL_CATALOG_ITEMS);
            while (rs.next()) {
                catalogItemList.add(extractCatalogItem(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_MENU_ITEMS, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_MENU_ITEMS, ex);
        } finally {
            close(con, stmt, rs);
        }
        return catalogItemList;
    }

    /**
     * Returns menu items of the given order.
     *
     * @param order Order entity.
     * @return List of menu item entities.
     */
    public List<CatalogItem> findCatalogItems(Order order) throws DBException {
        // TODO: 22.08.2016 исправить, метод не изменен после рефакторинга класса CatalogItem
        List<CatalogItem> catalogItemList = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_FIND_CATALOG_ITEMS_BY_ORDER);
            pstmt.setLong(1, order.getId());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                catalogItemList.add(extractCatalogItem(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_MENU_ITEMS_BY_ORDER, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_MENU_ITEMS_BY_ORDER, ex);
        } finally {
            close(con, pstmt, rs);
        }
        return catalogItemList;
    }

    /**
     * Returns menu items with given identifiers.
     *
     * @param ids Identifiers of menu items.
     * @return List of menu item entities.
     */
    public List<CatalogItem> findCatalogItems(String[] ids) throws DBException {
        List<CatalogItem> catalogItemList = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();

            // create SQL query like "... id IN (1, 2, 7)"
            StringBuilder query = new StringBuilder(
                    "SELECT \n" +
                            "catalog.hot as 'hot',\n" +
                            "catalog.id,\n" +
                            "catalog.name as 'Hotel',\n" +
                            "categories.name as 'Category',\n" +
                            "type_hotel.name as 'Hotel type',\n" +
                            "catalog.q_people,\n" +
                            "catalog.price\n" +
                            "FROM catalog\n" +
                            "LEFT JOIN categories ON categories.id = catalog.category_id \n" +
                            "LEFT JOIN type_hotel on catalog.type_hotel_id = type_hotel.id \n" +
                            "\n" +
                            "WHERE catalog.id IN (");
            for (String idAsString : ids) {
                query.append(idAsString).append(',');
            }
            query.deleteCharAt(query.length() - 1);
            query.append(')');

            stmt = con.createStatement();
            rs = stmt.executeQuery(query.toString());
            while (rs.next()) {
                catalogItemList.add(extractCatalogItem(rs));
            }
        } catch (SQLException ex) {
            rollback(con);
            LOG.error(ex);
//            ex.printStackTrace();
//            throw new DBException(
//                    Messages.ERR_CANNOT_OBTAIN_MENU_ITEMS_BY_IDENTIFIERS, ex);
        } finally {
            close(con, stmt, rs);
        }
        return catalogItemList;
    }

    public List<CatalogItem> findCatalogItems(User user) throws DBException {

        List<CatalogItem> catalogItemList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_GET_CATALOG_ITEMS_BY_USER);
            ps.setInt(1,user.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                catalogItemList.add(extractCatalogItem(rs));
            }
        } catch (SQLException ex) {
            rollback(con);
            throw new DBException(
                    Messages.ERR_CANNOT_OBTAIN_MENU_ITEMS_BY_IDENTIFIERS, ex);
        } finally {
            close(con);
            close(rs);
            close(ps);
        }
        return catalogItemList;
    }


    /**
     * Returns all orders.
     *
     * @return List of order entities.
     */
    public List<Order> findOrders() throws DBException {
        List<Order> ordersList = new ArrayList<Order>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_FIND_ALL_ORDERS);
            while (rs.next()) {
                ordersList.add(extractOrder(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_ORDERS, ex);
        } finally {
            close(con, stmt, rs);
        }
        return ordersList;
    }

    /**
     * Returns orders with the given status.
     *
     * @param statusId Status identifier.
     * @return List of order entities.
     */
    public List<Order> findOrders(int statusId) throws DBException {
        List<Order> ordersList = new ArrayList<Order>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_FIND_ORDERS_BY_STATUS);
            pstmt.setInt(1, statusId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ordersList.add(extractOrder(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            throw new DBException(
                    Messages.ERR_CANNOT_OBTAIN_ORDERS_BY_STATUS_ID, ex);
        } finally {
            close(con, pstmt, rs);
        }
        return ordersList;
    }

    /**
     * Returns orders with given identifiers.
     *
     * @param ids Orders identifiers.
     * @return List of order entities.
     */
    public List<Order> findOrders(String[] ids) throws DBException {
        List<Order> ordersList = new ArrayList<Order>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();

            // create SQL query like "... id IN (1, 2, 7)"
            StringBuilder query = new StringBuilder(
                    "SELECT * FROM orders WHERE id IN (");
            for (String idAsString : ids) {
                query.append(idAsString).append(',');
            }
            query.deleteCharAt(query.length() - 1);
            query.append(')');

            stmt = con.createStatement();
            rs = stmt.executeQuery(query.toString());
            while (rs.next()) {
                ordersList.add(extractOrder(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            throw new DBException(
                    Messages.ERR_CANNOT_OBTAIN_ORDERS_BY_IDENTIFIERS, ex);
        } finally {
            close(con, stmt, rs);
        }
        return ordersList;
    }

    /**
     * Returns orders of the given user and status
     *
     * @param user     User entity.
     * @param statusId Status identifier.
     * @return List of order entities.
     * @throws DBException
     */

    public List<Order> findOrders(User user, int statusId) throws DBException {
        List<Order> ordersList = new ArrayList<Order>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_FIND_ORDERS_BY_STATUS_AND_USER);
            pstmt.setInt(1, statusId);
            pstmt.setLong(2, user.getId());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ordersList.add(extractOrder(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            throw new DBException(
                    Messages.ERR_CANNOT_OBTAIN_ORDERS_BY_USER_AND_STATUS_ID, ex);
        } finally {
            close(con, pstmt, rs);
        }
        return ordersList;
    }


    /**
     * Returns a user with the given identifier.
     *
     * @param id User identifier.
     * @return User entity.
     * @throws DBException
     */
    public User findUser(long id) throws DBException {
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_FIND_USER_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = extractUser(rs);
            }
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_ID, ex);
        } finally {
            close(con, pstmt, rs);
        }
        return user;
    }

    /**
     * Returns a user with the given login.
     *
     * @param login User login.
     * @return User entity.
     * @throws DBException
     */
    public User findUserByLogin(String login) throws DBException {
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(SQL_FIND_USER_BY_LOGIN);
            pstmt.setString(1, login);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = extractUser(rs);
            }
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            ex.printStackTrace();
//			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, ex);
        } finally {
            close(con, pstmt, rs);
        }
        return user;
    }

    /**
     * Update user.
     *
     * @param user user to update.
     * @throws DBException
     */
    public void updateUser(User user) throws DBException {
        Connection con = null;
        try {
            con = getConnection();
            updateUser(con, user);
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            throw new DBException(Messages.ERR_CANNOT_UPDATE_USER, ex);
        } finally {
            close(con);
        }
    }

    // //////////////////////////////////////////////////////////
    // Entity access methods (for transactions)
    // //////////////////////////////////////////////////////////

    /**
     * Update user.
     *
     * @param user user to update.
     * @throws SQLException
     */
    private void updateUser(Connection con, User user) throws SQLException {
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(SQL_UPDATE_USER);
            int k = 1;
            pstmt.setString(k++, user.getPassword());
            pstmt.setString(k++, user.getFirstName());
            pstmt.setString(k++, user.getLastName());
            pstmt.setLong(k, user.getId());
            pstmt.executeUpdate();
        } finally {
            close(pstmt);
        }
    }

    // //////////////////////////////////////////////////////////
    // DB util methods
    // //////////////////////////////////////////////////////////

    /**
     * Closes a connection.
     *
     * @param con Connection to be closed.
     */
    public void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                LOG.error(Messages.ERR_CANNOT_CLOSE_CONNECTION, ex);
            }
        }
    }

    /**
     * Closes a statement object.
     */
    public void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                LOG.error(Messages.ERR_CANNOT_CLOSE_STATEMENT, ex);
            }
        }
    }

    /**
     * Closes a result set object.
     */
    public void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                LOG.error(Messages.ERR_CANNOT_CLOSE_RESULTSET, ex);
            }
        }
    }

    /**
     * Closes a Prepaeed statment
     */
    private void close(PreparedStatement rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                LOG.error("Cannot close prepared statement", ex);
            }
        }
    }

    /**
     * Closes resources.
     */
    public void close(Connection con, Statement stmt, ResultSet rs) {
        close(rs);
        close(stmt);
        close(con);
    }

    /**
     * Rollbacks a connection.
     *
     * @param con Connection to be rollbacked.
     */
    public void rollback(Connection con) {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                LOG.error("Cannot rollback transaction", ex);
            }
        }
    }

    // //////////////////////////////////////////////////////////
    // Other methods
    // //////////////////////////////////////////////////////////

    /**
     * Extracts a user order bean from the result set.
     *
     * @param rs Result set from which a user order bean will be extracted.
     * @return UserOrderBean object
     */

    private UserOrderBean extractUserOrderBean(ResultSet rs)
            throws SQLException {
        UserOrderBean bean = new UserOrderBean();
        bean.setId(rs.getInt(Fields.USER_ORDER_BEAN_ORDER_ID));
        bean.setOrderBill(rs.getInt(Fields.USER_ORDER_BEAN_ORDER_BILL));
        bean.setUserFirstName(rs.getString(Fields.USER_ORDER_BEAN_USER_FIRST_NAME));
        bean.setUserLastName(rs.getString(Fields.USER_ORDER_BEAN_USER_LAST_NAME));
        bean.setStatusName(rs.getString(Fields.USER_ORDER_BEAN_STATUS_NAME));
        return bean;
    }

    private ExtendedUserOrderBean extractExtendedUserOrderBean(ResultSet rs) throws SQLException {
        ExtendedUserOrderBean bean = new ExtendedUserOrderBean();
        bean.setUserFirstName(rs.getString(Fields.NEW_USER_FIRST_NAME));
        bean.setUserLastName(rs.getString(Fields.NEW_USER_LAST_NAME));
        bean.setHotelName(rs.getString(Fields.NEW_HOTEL_NAME));
        bean.setHotelType(rs.getString(Fields.NEW_HOTEL_TYPE));
        bean.setCategoryName(rs.getString(Fields.NEW_CATEGORY_NAME));
        bean.setHotelPrice(rs.getInt(Fields.NEW_HOTEL_PRICE));
        bean.setStatus(rs.getString(Fields.NEW_STATUS));
        bean.setOrderId(rs.getInt(Fields.ORDER_ID));
        bean.setCatalogId(rs.getInt(Fields.CATALOG_ITEM_ID));
        bean.setPeople(rs.getInt(Fields.CATALOG_ITEM_QUANTITY_PEOPLE));
        bean.setDiscount(rs.getInt(Fields.USER_ORDER_BEAN_DISCOUNT));
        return bean;

    }

    /**
     * Extracts a user entity from the result set.
     *
     * @param rs Result set from which a user entity will be extracted.
     * @return User entity
     */
    private User extractUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt(Fields.ENTITY_ID));
        user.setLogin(rs.getString(Fields.USER_LOGIN));
        user.setPassword(rs.getString(Fields.USER_PASSWORD));
        user.setFirstName(rs.getString(Fields.USER_FIRST_NAME));
        user.setLastName(rs.getString(Fields.USER_LAST_NAME));
        user.setRoleId(rs.getInt(Fields.USER_ROLE_ID));
        return user;
    }

    /**
     * Extracts an order entity from the result set.
     *
     * @param rs Result set from which an order entity will be extracted.
     * @return
     */
    private Order extractOrder(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(rs.getInt(Fields.ENTITY_ID));
        order.setBill(rs.getInt(Fields.ORDER_BILL));
        order.setUserId(rs.getLong(Fields.ORDER_USER_ID));
        order.setStatusId(rs.getInt(Fields.ORDER_STATUS_ID));
        return order;
    }

    /**
     * Extracts a category entity from the result set.
     *
     * @param rs Result set from which a category entity will be extracted.
     * @return Category entity.
     */
    private Category extractCategory(ResultSet rs) throws SQLException {
        Category category = new Category();
        category.setId(rs.getInt(Fields.ENTITY_ID));
        category.setName(rs.getString(Fields.CATEGORY_NAME));
        return category;
    }

    /**
     * Extracts a menu item from the result set.
     *
     * @param rs Result set from which a menu item entity will be extracted.
     * @return Menu item entity.
     */
    public CatalogItem extractCatalogItem(ResultSet rs) throws SQLException {

        CatalogItem catalogItem = new CatalogItem();
        catalogItem.setHot(rs.getBoolean(Fields.CATALOG_BEAN_HOT));
        catalogItem.setId(rs.getInt(Fields.ENTITY_ID));
        catalogItem.setName(rs.getString(Fields.CATALOG_BEAN_HOTEL));
        catalogItem.setPrice(rs.getInt(Fields.CATALOG_ITEM_PRICE));
        catalogItem.setCategoryId(rs.getString(Fields.CATALOG_BEAN_CATEGORY));
        catalogItem.setPeopleQuantity(rs.getInt(Fields.CATALOG_ITEM_QUANTITY_PEOPLE));
        catalogItem.setTypeHotel(rs.getString(Fields.NEW_HOTEL_TYPE));
        return catalogItem;
    }

    /**************** THIS METHOD IS NOT USED IN THE PROJECT *******/
    /**
     * Returns a DB connection. This method is just for a example how to use the
     * DriverManager to obtain a DB connection. It does not use a pool
     * connections and not used in this project. It is preferable to use
     * {@link #getConnection()} method instead.
     *
     * @return A DB connection.
     */
    public static Connection getConnectionWithDriverManager() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException ex) {
            LOG.error("Cannot obtain a connection", ex);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/mydbtest?" +
                        "user=root&password=root");
        connection
                .setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        connection.setAutoCommit(false);
        return connection;
    }
    /**************************************************************/


//	public static void main(String[] args) throws DBException, ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
//		Connection connection = DBManager.getConnectionWithDriverManager();
//		Statement st= connection.createStatement();
//		ResultSet resultSet;
//
//		resultSet = st.executeQuery(SQL_FIND_ALL_USERS);
//
//		while (resultSet.next()){
//			System.out.println(resultSet.getString("login"));
//		}
//
//
////		Connection conn = null;
////		Statement st=null;
////		ResultSet resultSet = null;
//
////		Class.forName("com.mysql.jdbc.Driver").newInstance();
////		try {
////			conn =
////					DriverManager.getConnection("jdbc:mysql://localhost:3306/mydbtest?" +
////							"user=root&password=root");
////
////			st = conn.createStatement();
////			resultSet = st.executeQuery(SQL_FIND_ALL_CATALOG_ITEMS);
////
////			while (resultSet.next()){
////				System.out.println(resultSet.getString("name"));
////			}
////
////
////		} catch (SQLException ex) {
////			 handle any errors
////			System.out.println("SQLException: " + ex.getMessage());
////			System.out.println("SQLState: " + ex.getSQLState());
////			System.out.println("VendorError: " + ex.getErrorCode());
////		}
//
//	}

}