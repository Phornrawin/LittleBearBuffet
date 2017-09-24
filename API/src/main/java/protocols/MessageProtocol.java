package protocols;

public class MessageProtocol {

    public static final String DELIMETER = ":";
    public static class Header{
        public static final String METHOD = "method";
        public static final String TYPE = "type";
        public static final String RESULT = "result";
        public static final String SENDER = "sender";
        public static final String NAME = "name";
        public static final String ID = "id";
        public static final String CATE_ID = "category-id";
        public static final String ITEM_ID = "item-id";
        public static final String TABLE = "table";
        public static final String AMOUNT = "amount";
        public static final String PRICE = "price";

    }

    public static class Result{
        public static final String OK = "200 OK";
        public static final String DATABASE_ERROR = "400 DATABASE ERROR";
    }

    public static class Method{
        public static final String ADD = "add";
        public static final String LOAD = "load";
        public static final String REPLY = "reply";
    }

    public static class Type{
        public static final String ITEM = "item";
        public static final String ORDER = "order";
        public static final String CATEGORY = "category";
    }


    /* Protocol message
    method:<method>
    sender:<sender>
    type:<type>
    id:<id>
    name:<name>
     */
}
