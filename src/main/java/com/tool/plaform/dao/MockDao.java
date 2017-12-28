package com.tool.plaform.dao;

import com.mongodb.BasicDBObject;
import com.tool.plaform.domain.Mock;
import com.tool.plaform.myConfig;
import com.tool.plaform.utils.MongoDbUtils;
import org.omg.CORBA.OBJ_ADAPTER;

public class MockDao {

    public boolean addMockDataToDb(Mock mock){
        BasicDBObject obj=new BasicDBObject();
        obj.append("describe",mock.getApidescribe());
        obj.append("name",mock.getName());
        obj.append("project",mock.getProject());
        obj.append("method","get");
        obj.append("path",mock.getApiPath());
        obj.append("operator","alun");
        obj.append("update",System.currentTimeMillis());
        obj.append("path",mock.getStuts());

        boolean issuccess=MongoDbUtils.insertOne("MockApiData", myConfig.db_thirdparty, obj);
        if(issuccess){
            return true;
        }else{
            return false;
        }
    }
}
