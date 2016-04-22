package com.monjodb.M101J;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class HelloWorldSparkFreemarkerStyle {

	public static void main(String[] args) {
		Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(HelloWorldSparkFreemarkerStyle.class, "/");
		Spark.get("/",new Route(){
			@Override
			public Object handle(final Request request, final Response response) {
				StringWriter writer = new StringWriter();
				try {
					Template helloTemplate = configuration.getTemplate("hello.ftl");

					Map <String,Object> helloMap = new HashMap<String, Object>();
					helloMap.put("name", "Freemarker");
					helloTemplate.process(helloMap, writer);
				} catch (Exception e) {
					e.printStackTrace();
				}

				return writer;
			}
		});
	}

}
