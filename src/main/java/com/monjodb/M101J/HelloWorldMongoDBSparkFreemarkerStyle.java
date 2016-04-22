package com.monjodb.M101J;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.IOException;
import java.io.StringWriter;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.bson.Document;

public class HelloWorldMongoDBSparkFreemarkerStyle {
	public static void main(String[] args) throws UnknownHostException {
		final Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(
				HelloWorldSparkFreemarkerStyle.class, "/");

		MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));

		MongoDatabase database = client.getDatabase("m101");
		MongoCollection<Document> collection = database.getCollection("funnynumbers");


		Spark.get("/",new Route() {
			@Override
			public Object handle(final Request request,
					final Response response) {
				StringWriter writer = new StringWriter();
				try {
					Template helloTemplate = configuration.getTemplate("hello.ftl");

					FindIterable<Document> iterable = collection.find();

					iterable.forEach(new Block<Document>() {
						@Override
						public void apply(final Document document) {
							try {

								Map <String,Object> helloMap = new HashMap<String, Object>();
								helloMap.put("name", document.toString());
								helloTemplate.process(helloMap, writer);

								helloTemplate.process(helloMap, writer);
							} catch (TemplateException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});



				} catch (Exception e) {
					//halt(500);
					e.printStackTrace();
				}
				 finally{
                	client.close();
                }
				return writer;
			}
		});
	}
}
