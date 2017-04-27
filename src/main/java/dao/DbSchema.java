package dao;

/**
 * Created by arahis on 4/27/17.
 */
public class DbSchema {

    public static class Clients {
        public static final String TABLE_NAME = "Clients";
        public static final String NAME_COL = "name"; // not null
        public static final String PHONE_NUM_COL = "phone_number"; // not null, unique
    }

    public static class Products {
        public static final String TABLE_NAME = "Products";
        public static final String NAME_COL = "name"; // not null, unique
        public static final String PRICE_COL = "price";
    }

    public static class Orders {
        public static final String TABLE_NAME = "Orders";
        public static final String DATE_COL = "date"; // not null
        public static final String CLIENT_ID_COL = "client_id"; // not null
        public static final String PRODUCT_ID_COL = "product_id"; // not null
    }
}
