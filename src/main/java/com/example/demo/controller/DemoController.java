// C035384 정세린 
// Java Program to Illustrate DemoController

// Importing package to code module
package com.example.demo.controller;

// Importing required classes
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.*;
import java.sql.Connection;

// Annotation
@Controller
@RequestMapping("/students")

// Class
public class DemoController {
	TableFunctions TF = new TableFunctions();
	Connection connection = TF.get_connection();

	// Method

	@GetMapping("/degree")
	@ResponseBody
	public String getDegree(@RequestParam (value = "name") String name) {
		if (TF.CountStudent(connection, name) == 0)  // 해당 이름을 가진 학생이 0명 존재 
			return "No such student";
		else if (TF.CountStudent(connection, name) == 1)  // 해당 이름을 가진 학생이 1명 존재 
			return name + " : " + TF.SelectDegree(connection, name);
		else   // 해당 이름을 가진 학생이 여러명 존재 
			return "There are multiple students with the same name. Please provide an email address instead.";
	}

	@GetMapping("/email")
	@ResponseBody
	public String getEmail(@RequestParam (value = "name") String name) {
		if (TF.CountStudent(connection, name) == 0)  // 해당 이름을 가진 학생이 0명 존재 
			return "No such student";
		else if (TF.CountStudent(connection, name) == 1)  // 해당 이름을 가진 학생이 1명 존재 
			return name + " : " + TF.SelectEmail(connection, name);
		else   // 해당 이름을 가진 학생이 여러명 존재 
			return "There are multiple students with the same name. Please contact the administrator by phone.";
	}

	@GetMapping("/stat")
	@ResponseBody
	public String getStat(@RequestParam (value = "degree") String degree) {
		return "Number of " + degree + "'s student : " + TF.SelectStat(connection, degree);
	}

	@PutMapping("/register")
	@ResponseBody
	public String putStudent(@RequestParam (value = "name") String name, 
			@RequestParam (value = "email") String email,
			@RequestParam (value = "graduation") Integer graduation,
			@RequestParam (value = "degree") String degree ) {
		System.out.println("Argument received " + " " + name + " " + email + " " + graduation + " " + degree);
		if (TF.CountStudent(connection, name) != 0) {  // 같은 이름을 가진 학생이 이미 존재 
			return "Already Registered";
		}
		else {
			TF.InsertRow(connection, name, email, graduation, degree);
			return "Registration successful";
		}
	}

}
