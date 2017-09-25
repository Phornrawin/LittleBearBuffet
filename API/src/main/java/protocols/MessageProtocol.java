package protocols;

/**
 * == MessageProtocol Document ==
 *
 * -- request category id message --
 * method:load
 * sender:<sender></sender>     ex. customer-1
 * type:category-id
 *
 * -- response category id message --
 * result:200 OK
 * method:reply
 * sender:<sender></sender>     ex. server-1
 * type:category-id
 * ids:<id-list></id-list>      ex. 1,2,3,4,5
 *
 * -- request category message --
 * method:load
 * sender:<sender></sender>     ex. customer-1
 * type:category
 * id:<id></id>
 *
 * -- response category message --
 * result:200 OK
 * method:reply
 * sender:<sender></sender>
 * type:category
 * id:<id></id>
 * name:<name></name>
 *
 * -- request item id message --
 * method:load
 * sender:<sender></sender>
 * type:item-id
 *
 * -- response item id message --
 * result:200 OK
 * method:reply
 * sender:<sender></sender>
 * type:item-id
 * ids:<id></id>
 *
 * -- request item message --
 * method:load
 * sender:<sender></sender>
 * type:item
 * id:<id></id>
 *
 * -- response item message --
 * result:200 OK
 * method:reply
 * sender:<sender></sender>
 * type:item
 * id:<id></id>
 * name:<name></name>
 * price:<price></price>
 * category-id:<cate-id></cate-id>
 *
 * -- request add order message --
 * method:add
 * sender:<sender></sender>
 * type:order
 * id:<id></id>
 * amount:<amt></amt>
 * item-id:<item-id></item-id>
 * name:<name></name>
 * price:<price></price>
 * category-id:<cate-id></cate-id>
 *
 * -- response add order message --
 * result:200 OK
 * method:reply
 * sender:<sender></sender>
 * type:order
 * id:<id></id>
 * amount:<amt></amt>
 * item-id:<item-id></item-id>
 * name:<name></name>
 * price:<price></price>
 * category-id:<cate-id></cate-id>
 *
 */
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
        public static final String IDS = "ids";
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
        public static final String CATEGORY_ID = "category-id";
        public static final String ITEM_ID = "item-id";
    }




}