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
    public static final String PAGE_ADMIN_LIST_CATALOG_HOT = "/WEB-INF/jsp/admin/admin_list_catalog.jsp";
    public static final String PAGE_SETTINGS = "/WEB-INF/jsp/settings.jsp";
    public static final String PAGE_YOUR_ORDER = "/WEB-INF/jsp/client/yourOrder.jsp";
    public static final String PAGE_PRIVATE_OFFICE = "/WEB-INF/jsp/client/private_office.jsp";
    public static final String PAGE_DISCOUNT_SETTINGS = "/WEB-INF/jsp/admin/discount.jsp";
    public static final String PAGE_SIGN_IN = "/signIn.jsp";
    public static final String PAGE_SUCCESSFUL = "/WEB-INF/jsp/successful.jsp";



    // commands
    public static final String COMMAND_LIST_ORDERS = "/controller?command=adminListOrders";
    public static final String COMMAND_LIST_CATALOG = "/controller?command=listCatalog";
    public static final String COMMAND_YOUR_ORDER = "/controller?command=yourOrder";
    public static final String COMMAND_ADMIN_LIST_CATALOG = "/controller?command=adminListCatalog";
    public static final String COMMAND_CONFIRM_ORDER = "/controller?command=confirmOrder";
    public static final String COMMAND_PRIVATE_OFFICE = "/controller?command=private_office";
    public static final String COMMAND_DISCOUNT = "/controller?command=discount";

}
