package com.abhyaastech.mongodb;
import com.mongodb.BasicDBObject;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

public class Homework3_1 {

	public static void main(String[] args) {
		
		MongoClient client = new MongoClient();
		Object typ1="exam";
		Object typ2="homework";
		Object typ3="quiz";
		Double maxhmwk=0.0;
		int counter=0;
		int outercounter=0;
		BasicDBObject document = new BasicDBObject();
		List<BasicDBObject> updatesubdoc = null;
    	//List<BasicDBObject> updatesubdoc = new ArrayList<BasicDBObject>();
		//HashMap typeMap = new HashMap<String, String>();
		//HashMap scoreMap = new HashMap<String, Double>();
        MongoDatabase database = client.getDatabase("school");
        //List<Document> updatedsubdoc = null;
        final MongoCollection<Document> collection = database.getCollection("students1");
        //Bson sort = Sorts.orderBy(Sorts.ascending("student_id"),Sorts.ascending("score"));
        //Bson projection = Projections.fields(include("student_id"));
        List<Document> alldocs = collection.find(new Document()).into(new ArrayList<Document>());
        List<Document> subdocs;
        
        for(Document doc:alldocs){
        	
        	
        	Object id = doc.get("_id");
        	Object name = doc.get("name");
        	
        	
        	subdocs= (List<Document>) doc.get("scores");
        	updatesubdoc = new ArrayList<BasicDBObject>();
        		for(Document subdoc:subdocs){
        		   
        	       Object typ=subdoc.get("type");
        	       Object scr=subdoc.get("score");
        	       updatesubdoc.add(new BasicDBObject("type",typ).append("score", scr));
        	       //updatesubdoc.put("type",typ);
        	       //updatesubdoc.put("score",scr);
        	       //System.out.println(JSON.serialize(updatesubdoc));	
        	      
        	   }
        	   
        	   document.put("_id",id);
        	   document.put("name", name);
        	   document.put("scores", updatesubdoc);
        	   System.out.println(JSON.serialize(document));
        	   
        	   
        	       
        	      /* if (typ.equals(typ2)){
        	    	   Double hmwk=(Double)scr;
        	    	   int retval=Double.compare(hmwk,maxhmwk);
        	    	   if(retval > 0);
        	    	   {
        	    		   maxhmwk=hmwk;
        	    	   }
        	    	   
        	    	   scr=maxhmwk;
      	    	   
        	      	   //System.out.println(JSON.serialize(subdoc.get("type")));
        	    	           	    	   
        	       } */
        	           	       
        	       //List<Document> updatedsubdoc=
        	    		   //new Document("scores",Arrays.asList(typeMap,scoreMap));
        	       
        	       
        	       
        	       //outercounter=outercounter+1;
        	       //System.out.println("outercounter"+outercounter);
        	       //new Document("_id", "user1").append("interests", Arrays.asList("basketball", "drumming"));
        	       
        		   //System.out.println(JSON.serialize(updatedsubdoc));
        	    	
        	    }
        	   //Document updateddoc= new Document("_id",id).append("name",name).append("scores",Arrays.asList(new Document
        	    	
        	    
        		
        		
      //  }
            
              
			}
	
}
