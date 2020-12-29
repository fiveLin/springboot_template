package com.example.lin.resp;


/**
 * API响应Object对象。
 * <ul>
 * 	<li>可以传入基本数据，如
 * 	<pre>new APIObjectJson(10);
 数据部分为 data":{"obj":10}</pre>
 * 	</li>
 * 	<li>可以传入自定义对象，如：<pre>new APIObjectJson(new Member("姓名", 20))，
 数据部分为 "data":{"age":20,"name":"姓名"}</pre>
 * </li>
 * 	<li>可以传入Map, 如:<pre>
 Map<String, Object> map = new HashMap<String, Object>();
 map.put("name", "姓名");
 map.put("age", 20);
 map.put("tel", "13777436601");
 new APIObjectJson(map);
 数据部分为"data":{"age":20,"name":"姓名","tel":"13777436601"}</pre>
 </li>
 <li>可以传入Page, 如:<pre> new APIObjectJson(page);
 数据部分为 "data":{"firstPage":true,"lastPage":false,
 "list":[{"age":20,"name":"姓名0"},{"age":21,"name":"姓名1"},{"age":22,"name":"姓名2"}],
 "pageNumber":1,"pageSize":10,"totalPage":50,"totalRow":499}</pre></li>
 * </ul>
 * @author LuLihong
 *
 */
public class APIObjectJson extends APIRespJson {
    private static final long serialVersionUID = -6748552684040052684L;

    public APIObjectJson(Object obj) {
        this(ResultCode.SUCC, obj);
    }

    public APIObjectJson(int code, Object obj) {
        super(code);
        if (isBaseType(obj)) {
            setData(new APIObject(obj));
        } else {
            setData(obj);
        }
    }

    public APIObjectJson(int code, String message) {
        super(code, message);
    }

    public static boolean isBaseType(Object obj) {
        return obj instanceof Integer || obj instanceof String
                || obj instanceof Character || obj instanceof Boolean
                || obj instanceof Byte || obj instanceof Long
                || obj instanceof Float || obj instanceof Double;
    }
}
