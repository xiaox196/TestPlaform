package com.tool.plaform.utils;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class DBObjectUtil {
    public static DBObject parseJson(String strJson){
        BasicDBObject obj = new BasicDBObject();
        try {
            obj = (BasicDBObject) JSON.parse(strJson);
        } catch(Exception e) {
            System.out.print("解析jason异常："+e);
        }
        return obj;
    }
}
