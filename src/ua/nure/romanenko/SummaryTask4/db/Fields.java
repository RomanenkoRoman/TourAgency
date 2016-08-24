package ua.nure.romanenko.SummaryTask4.db;

/**
 * Holder for fields names of DB tables and beans.
 */
public final class Fields {
	
	// entities
	public static final String ENTITY_ID = "id";
	
	public static final String USER_LOGIN = "login";
	public static final String USER_PASSWORD = "password";
	public static final String USER_FIRST_NAME = "first_name";
	public static final String USER_LAST_NAME = "last_name";
	public static final String USER_ROLE_ID = "role_id";
	
	public static final String ORDER_BILL = "bill";
	public static final String ORDER_ID = "orderNum";
	public static final String ORDER_USER_ID = "user_id";
	public static final String ORDER_STATUS_ID= "status_id";

	public static final String CATEGORY_NAME = "name";
	
	public static final String CATALOG_ITEM_PRICE = "price";
	public static final String CATALOG_ITEM_NAME = "name";
	public static final String CATALOG_ITEM_CATEGORY_ID = "category_id";
	public static final String CATALOG_ITEM_QUANTITY_PEOPLE = "q_people";
	public static final String CATALOG_ITEM_ID = "catalogId";


	// beans
	public static final String USER_ORDER_BEAN_ORDER_ID = "id";	
	public static final String USER_ORDER_BEAN_USER_FIRST_NAME = "first_name";	
	public static final String USER_ORDER_BEAN_USER_LAST_NAME = "last_name";	
	public static final String USER_ORDER_BEAN_ORDER_BILL = "bill";	
	public static final String USER_ORDER_BEAN_STATUS_NAME = "name";

	//new bean
	public static final String NEW_USER_FIRST_NAME = "First name";
	public static final String NEW_USER_LAST_NAME = "Last name";
	public static final String NEW_HOTEL_NAME = "Hotel name";
	public static final String NEW_HOTEL_PRICE = "Hotel price";
	public static final String NEW_HOTEL_TYPE = "Hotel type";
	public static final String NEW_CATEGORY_NAME = "Category name";
	public static final String NEW_STATUS = "Status";

	//catalog bean
	public static final String CATALOG_BEAN_HOT = "hot";
	public static final String CATALOG_BEAN_HOTEL = "Hotel";
	public static final String CATALOG_BEAN_CATEGORY = "Category";
	public static final String CATALOG_BEAN_TYPE = "Type";

	
}