package com.prime.generics;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class MongoDBManager {

	/**
	 * It is used to get test case id from mongodb database
	 * @param testcaseid
	 * @param url
	 * @param dbName
	 * @param table
	 * @return JSONObject
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	public static JSONObject connectMongoDB(String testcaseid,String url,String dbName,String table) throws Exception {
		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE);
		MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
		MongoDatabase database = mongoClient.getDatabase(dbName);
		MongoCollection<Document> Collection = database.getCollection(table);
		Bson filter = Filters.eq("TCID", testcaseid);
		Document doc = Collection.find(filter).first();
		System.out.println(doc.toJson());
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(doc.toJson());
		JSONObject data = (JSONObject) json.get("data");
		return data;
	}
}
