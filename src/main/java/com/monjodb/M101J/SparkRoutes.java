package com.monjodb.M101J;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class SparkRoutes {
	public static void main(String[] args) {
		Spark.get("/",new Route() {
			@Override
			public Object handle(final Request request, final Response response) {
				return "Hello World\n";
			}
		});

		Spark.get("/test",new Route() {
			@Override
			public Object handle(final Request request, final Response response) {
				return "This is a test page\n";
			}
		});

		Spark.get("/echo/:thing",new Route() {
			@Override
			public Object handle(final Request request, final Response response) {
				return request.params(":thing");
			}
		});
	}
}
