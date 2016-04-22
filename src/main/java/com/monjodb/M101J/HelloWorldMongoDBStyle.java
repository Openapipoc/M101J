package com.monjodb.M101J;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.net.UnknownHostException;

import org.bson.Document;

public class HelloWorldMongoDBStyle {
	public static void main(String[] args) throws UnknownHostException {
		MongoClient client =
				new MongoClient(new ServerAddress("localhost", 27017));

		MongoDatabase database = client.getDatabase("m101");
		MongoCollection<Document> collection = database.getCollection("funnynumbers");

		FindIterable<Document> iterable = collection.find();

		iterable.forEach(new Block<Document>() {
			@Override
			public void apply(final Document document) {
				System.out.println(document);
			}
		});

		client.close();
	}
}
