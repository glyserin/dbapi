// C035384 정세린 
// Java Program to Illustrate DemoApplication

// Importing package module to code
package com.example.demo;

// Importing required classes
import org.springframework.boot.SpringApplication;
import java.sql.Connection;
import java.util.ArrayList;
import java.lang.Integer;

import org.springframework.boot.autoconfigure.SpringBootApplication;

// Annotation
@SpringBootApplication

// Main class
public class DemoApplication {

	// Main driver method
	public static void main(String[] args) {

		Crawling C = new Crawling();

		TableFunctions TF = new TableFunctions();

		Connection connection = TF.get_connection();
		TF.DropTable(connection);
		TF.CreateTable(connection);

		ArrayList<ArrayList<ArrayList<String>>> Crawled = C.Crawl();
		for (ArrayList<ArrayList<String>> InnerALone : Crawled) {
			for (ArrayList<String> InnerALtwo : InnerALone) {
				TF.InsertRow(connection, InnerALtwo.get(0), InnerALtwo.get(1), Integer.parseInt(InnerALtwo.get(2)),
						InnerALtwo.get(3));
			}
		}

		SpringApplication.run(DemoApplication.class, args);
	}
}
