package ua.nure.romanenko.SummaryTask4;

/**
 * Path holder (jsp pages, controller commands).
 * Created by Роман on 04.08.2016.
 */
public class Path {
    // pages
    public static final String PAGE_LOGIN = "/login.jsp";
    public static final String PAGE_ERROR_PAGE = "/WEB-INF/jsp/error_page.jsp";
    public static final String PAGE_LIST_CATALOG = "/WEB-INF/jsp/client/list_catalog.jsp";
    public static final String PAGE_LIST_ORDERS = "/WEB-INF/jsp/admin/list_orders.jsp";
    public static final String PAGE_SETTINGS = "/WEB-INF/jsp/settings.jsp";

    // commands
    public static final String COMMAND_LIST_ORDERS = "/controller?command=listOrders";
    public static final String COMMAND_LIST_CATALOG = "/controller?command=listCatalog";
}
