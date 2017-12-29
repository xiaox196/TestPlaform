package com.tool.plaform.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCursor;
import com.tool.plaform.domain.Mock;
import com.tool.plaform.myConfig;
import com.tool.plaform.utils.MongoDbUtils;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;


public class MockDao {

    public boolean addMockDataToDb(Mock mock){
        BasicDBObject obj=new BasicDBObject();
        String path=mock.getApiPath();
        obj.append("describe",mock.getApidescribe());
        obj.append("name",mock.getName());
        obj.append("project",mock.getProject());
        obj.append("method","get");
        obj.append("path",path);
        obj.append("operator","alun");
        obj.append("update",System.currentTimeMillis());
        obj.append("resultMsg",mock.getResultMsg());
        obj.append("returnUrl",myConfig.baseUrl+"mock/"+path);
        boolean issuccess=MongoDbUtils.insertOne("MockApiData", myConfig.db_thirdparty, obj);
        if(issuccess){
            return true;
        }else{
            return false;
        }
    }

    public List<Mock> findMockDataAll(){
        List<Mock> list=new ArrayList<>();
        BasicDBObject query=new BasicDBObject();
        MongoCursor<Document> cursor= MongoDbUtils.find("MockApiData",myConfig.db_thirdparty,query);
        try {
            while (cursor!=null && cursor.hasNext()) {
                Document doc = cursor.next();
                System.out.println("doc"+doc);
                if(doc.containsKey("_id")){
                    System.out.println("describe"+doc.getString("describe"));
                    Mock mock=new Mock();
                    mock.setApidescribe(doc.getString("describe"));
                    mock.setApiPath(doc.getString("path"));
                    mock.setName(doc.getString("name"));
                    mock.setCreater(doc.getString("operator"));
                    mock.setMethod("get");
                    mock.setProject(doc.getString("project"));
                    mock.setStuts("在用");
                    mock.setOperator("add");
                    mock.setUpdateTime(doc.getLong("update"));
                    mock.setResultMsg(doc.getString("resultMsg"));
                    list.add(mock);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (cursor!=null)
                cursor.close();
        }
        System.out.println("list=="+list);
        return list;
    }
}
