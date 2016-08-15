package com.abhyaastech.mongodb;
import com.mongodb.MongoClient;
//import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.mongodb.util.JSON;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.bson.Document;
import org.bson.conversions.Bson;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import static com.mongodb.client.model.Filters.*;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

public class Homework2_3 {

	public static void main(String[] args) {
		
		MongoClient client = new MongoClient();

        MongoDatabase database = client.getDatabase("students");
        final MongoCollection<Document> collection = database.getCollection("grades");
        Bson sort = Sorts.orderBy(Sorts.ascending("student_id"),Sorts.ascending("score"));
        //Bson projection = Projections.fields(include("student_id"));
        List<Document> cur = collection.find(new Document("type","homework")).
        		sort(sort)
        		.projection(new Document("student_id",1).append("_id",1))
        		.into(new ArrayList<Document>());
        for(Document all:cur){
        	System.out.println(JSON.serialize(all.get("_id")));
        	
        }
        
        
        
        //collection.deleteMany(eq("type","homework"));      
		

	}

}
