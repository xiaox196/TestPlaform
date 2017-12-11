package com.tool.plaform.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCursor;
import com.tool.plaform.domain.TestPlaform;
import com.tool.plaform.utils.MongoDbUtils;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class TestMobileDao {

    public List<TestPlaform> findAllTestMobile(){
        BasicDBObject query=new BasicDBObject();
        MongoCursor<Document> cursor=MongoDbUtils.find("TestMobile",query);
        List<TestPlaform>  list=parseTestPlaform(cursor);
        return list;
    }

    public List<TestPlaform> findTestMobileByPlaform(String plaform){

        BasicDBObject query=new BasicDBObject();
        query.append("型号",plaform);
        MongoCursor<Document> cursor=MongoDbUtils.find("TestMobile",query);
        System.out.println(cursor);
        List<TestPlaform> list=parseTestPlaform(cursor);
        return list;
    }

    public List<TestPlaform> parseTestPlaform(MongoCursor<Document> cursor){
        List<TestPlaform> list=new ArrayList<>();
        try {
            while (cursor!=null && cursor.hasNext()) {
                Document doc = cursor.next();
                if(doc.containsKey("_id")){
                    TestPlaform aa=setTestMobileObj(doc);
                    list.add(aa);
                }
            }
        } catch (Exception e) {

        } finally {
            if (cursor!=null)
                cursor.close();
        }
        return  list;
    }


    public TestPlaform setTestMobileObj(Document doc ){
        TestPlaform testPlaform=new TestPlaform();
        String mobile=doc.getString("型号");
        String plaform=doc.getString("厂家");
        String uuid=doc.getString("uuid");
        String owner=doc.getString("保管人");
        String remark=doc.getString("备注");
        testPlaform.setMobile(mobile);
        testPlaform.setOwner(owner);
        testPlaform.setPlaform(plaform);
        testPlaform.setRemark(remark);
        testPlaform.setUuid(uuid);
        return  testPlaform;
    }

}
