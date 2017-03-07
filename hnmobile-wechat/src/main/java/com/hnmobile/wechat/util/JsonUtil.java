package com.hnmobile.wechat.util;
import java.util.ArrayList;
import java.util.List;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class JsonUtil {

    // Java object to JSON String
    public static String getJsonStrFromObj(Object obj) {
        JSONSerializer serializer = new JSONSerializer();
        serializer.exclude("class");
        return serializer.serialize(obj);
    }

    // Java object to JSON String
    public static String getJsonStrFromObj(Object obj, String... params) {
        JSONSerializer serializer = new JSONSerializer();
        serializer = serializer.include(params);
        serializer.exclude("class");
        return serializer.serialize(obj);
    }

    // List to JSON String
    public static String getJsonStrFromList(List<Object> objs, String rootName,
            String... params) {
        JSONSerializer serializer = new JSONSerializer();
        serializer = serializer.include(params);
        serializer.rootName(rootName);
        return serializer.serialize(objs);
    }

    // Json String to Java object
    public static <T> T getObjFromJsonStr(String source, Class<T> bean) {
        return new JSONDeserializer<T>().deserialize(source, bean);
    }
    
    public static void main( String[]args ){
//   	    String json=new JSONSerializer().exclude("*.class").deepSerialize(po); 
//    	System.out.println( json );
    }
    
}
