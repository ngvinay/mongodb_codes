package com.abhyaastech.mongodb;
import com.mongodb.MongoClient;
//import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.result.DeleteResult;
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
		Integer prev_id=-1;
		int counter=0;
		int deletecount=0;
        MongoDatabase database = client.getDatabase("students");
        final MongoCollection<Document> collection = database.getCollection("grades");
        Bson sort = Sorts.orderBy(Sorts.ascending("student_id"),Sorts.ascending("score"));
        //Bson projection = Projections.fields(include("student_id"));
        List<Document> cur = collection.find(new Document("type","homework")).
        		sort(sort)
        		//.projection(new Document("student_id",1).append("_id",1))
        		.into(new ArrayList<Document>());
        for(Document all:cur){
        	//if(((Integer)all.get("student_id")) != prev_id){
        	//	System.out.println(((Integer)all.get("student_id")));
        	//	System.out.println(prev_id);
        		if (counter == 0){
        		System.out.println(JSON.serialize(all));
        		Object id = all.get("_id");
        		//DeleteResult d=
        		collection.deleteOne(Filters.eq("_id", id));
        		deletecount=deletecount+1;
        		}
        		counter=counter+1;
        		if(counter==2){
        			counter=0;
        		}
        	
        	//prev_id=(Integer)all.get("student_id");
        }
            
        System.out.println(deletecount);
        //collection.deleteMany(eq("type","homework"));      
			}

}
