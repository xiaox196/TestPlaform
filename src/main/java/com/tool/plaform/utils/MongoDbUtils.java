package com.tool.plaform.utils;

import java.util.ArrayList;
import java.util.List;


import com.tool.plaform.myConfig;
import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;


public class MongoDbUtils {



    private static  String user= myConfig.user;

    private static  String password=myConfig.password;


    private static  String ip=myConfig.ip;


    private static  String db=myConfig.db;


	
	public  static MongoDatabase  getMongo(){
		MongoDatabase mongoDatabase=null;
		try {  
	        //连接到MongoDB服务 如果是远程连接可以替换“localhost”为服务器所在IP地址  
	        //ServerAddress()两个参数分别为 服务器地址 和 端口  
	        ServerAddress serverAddress = new ServerAddress(ip,27014);  
	        List<ServerAddress> addrs = new ArrayList<ServerAddress>();  
	        addrs.add(serverAddress);  
	          
	        //MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码  
	        MongoCredential credential = MongoCredential.createScramSha1Credential(user, db, password.toCharArray());
	        List<MongoCredential> credentials = new ArrayList<MongoCredential>();  
	        credentials.add(credential);  	          
	        //通过连接认证获取MongoDB连接  
	        MongoClient mongoClient = new MongoClient(addrs,credentials);  
	          
	        //连接到数据库  
	        mongoDatabase= mongoClient.getDatabase(db);  
	        System.out.println("Connect to database successfully");  
	    } catch (Exception e) {  
	        System.err.println( e.getClass().getName() + ": " + e.getMessage() );  
	    }
		return mongoDatabase; 
	}
	
    public static boolean insertOne(String coll, BasicDBObject obj) {
        long start = System.currentTimeMillis();
        try {
        	MongoDbUtils.getInstance().getColl(coll).insertOne(Document.parse(obj.toString()));
        } catch (Throwable e) {
        	System.err.println("coll:"+coll+", insertOne db exception: " + e.getMessage()+e);
            return false;
        } finally {
            long spend = System.currentTimeMillis() - start;
            System.err.println("coll:"+coll+", time:"+spend);
        }
        return true;
    }
	
    public static boolean updateOne(String coll, BasicDBObject query, BasicDBObject obj) {
        long start = System.currentTimeMillis();
        try {
        	MongoDbUtils.getInstance().getColl(coll).updateOne(query, obj);
        } catch (Throwable e) {
            return false;
        } finally {
            long spend = System.currentTimeMillis() - start;
        }
        return true;
    }
    
    /**
     * 查询被匹配到的所有记录

     * @param coll    数据集名称
     * @param query   查询条件
     * @return  MongoCursor<Document>
     */
    public static MongoCursor<Document> find(String coll, BasicDBObject query) {
        return find(coll, query, null);
    }
    
    /**
     * 查询被匹配到的按排序规则排序后的所有记录

     * @param coll    数据集名称
     * @param query   查询条件
     * @param orderby 排序规则
     * @return  MongoCursor<Document>
     */
    public static MongoCursor<Document> find(String coll, BasicDBObject query, BasicDBObject orderby) {
        long start = System.currentTimeMillis();
        MongoCursor<Document> cursor = null;
        try {
            if (orderby != null) {
                cursor = MongoDbUtils.getInstance().getColl(coll).find(query).sort(orderby).iterator();
            } else {
                cursor = MongoDbUtils.getInstance().getColl(coll).find(query).iterator();
            }
        } catch (Throwable e) {
            System.err.println("coll:"+coll+", query:"+query+", find db exception: " + e.getMessage()+e);
        } finally {
            long spend = System.currentTimeMillis() - start;
            System.err.println("coll:"+coll+", query:"+query+", orderby:"+orderby+", time:"+spend);
        }
        return cursor;
    }
    public static BasicDBObject findOne(String coll, BasicDBObject query, BasicDBObject orderby) {
        long start = System.currentTimeMillis();
        BasicDBObject resObj = null;
        try {
            Document doc = null;
            if (orderby != null) {
                doc = MongoDbUtils.getInstance().getColl(coll).find(query).sort(orderby).first();
            } else {
                doc = MongoDbUtils.getInstance().getColl(coll).find(query).first();
            }
            if (doc != null) {
                resObj = (BasicDBObject)JSON.parse(doc.toJson());
            }
        } catch (Throwable e) {
            System.err.println("coll:"+coll+", query:"+query+", orderby:"+orderby+", find db exception: " + e.getMessage()+e);
            return null;
        } finally {
            long spend = System.currentTimeMillis() - start;
            System.err.println("coll:"+coll+", query:"+query+", orderby:"+orderby+", time:"+spend);
        }
        return resObj;
    }
	
	  private static class SingletonClassInstance {
	        private static final MongoDbUtils instance = new MongoDbUtils();
	    }

	    public static MongoDbUtils getInstance() {
	        return SingletonClassInstance.instance;
	    }
	
	  public MongoCollection<Document> getColl(String coll) {
	        return getMongo().getCollection(coll);
	    }
}
