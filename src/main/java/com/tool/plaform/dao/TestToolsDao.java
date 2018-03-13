package com.tool.plaform.dao;

import com.mongodb.BasicDBObject;
import com.tool.plaform.myConfig;
import com.tool.plaform.utils.MongoDbUtils;

public class TestToolsDao {

   /*
   * 1.代表 apply
   * 2.代表 stat
   * */

    public static boolean save(String id,String which){
        BasicDBObject obj = QueueLoanInfo(id);
        if(obj!=null){
            SaveToQueueStat(obj,which);
            return true;
        }else{
            System.out.println("数据库里面不存在这个订单的数据");
            return false;
        }
    }

    public static BasicDBObject QueueLoanInfo(String id) {
        BasicDBObject obj = null;
        BasicDBObject query = new BasicDBObject();
        BasicDBObject orderby = new BasicDBObject("logTime", -1);
        query.append("id", id);
        obj = MongoDbUtils.findOne("LoanApplication", myConfig.db_business, query, orderby);
        System.out.println(obj);
        if(obj!=null){
            obj.removeField("_id");
        }
        return obj;
    }

    public static void SaveToQueueStat(BasicDBObject obj,String which) {
        String platform = "";
        String id = "";
        String account = "";
        String sourceServer = "";
        BasicDBObject query = new BasicDBObject();
        System.out.println(obj);
        if (obj.containsField("platform")) {
            platform = obj.getString("platform");
        }
        if (obj.containsField("id")) {
            id = obj.getString("id");
            System.out.println(id);
        }
        if (obj.containsField("account")) {
            account = obj.getString("account");
        }

        query.append("id", id);
        query.append("account", account);
        query.append("platform", platform);
        query.append("applyTime", 1493807358);
        query.append("sourceServer", which);
        query.append("status", 0);
        query.append("productId", 1000);
        query.append("tryCount", 0);
        query.append("updateTime", 1493807358);
        query.append("logTime", 1493807358);
        System.out.println("-------------");
        System.out.println("插入querystat表的数据  :" + query);

        MongoDbUtils.insertOne(which, myConfig.db_business,query);
    }

}
