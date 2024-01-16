package br.ce.wcaquino.tasks.apitest;

import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class APITest {
	
	@BeforeClass
	
	public static void setup() {
		RestAssured.baseURI="http://mo76was1.fyre.ibm.com:8080/tasks-backend";
	
	}
	
	
	@Test
	public void shouldReturnTasks() {
		RestAssured.given()
		.when()
			.get("/todo")
		.then()
			.statusCode(200)
		;
	}
	
	@Test
	public void addTasksWithSuccess() {
		RestAssured.given()
			.body("{\"task\":\"teste api rest assured\",\"dueDate\":\"2024-01-30\"}")
			.contentType(ContentType.JSON)
		.when()	
			.post("/todo")
		.then()
			.statusCode(201)
		;
	}
	
	@Test
	public void addInvalidTasks() {
		RestAssured.given()
			.body("{\"task\":\"teste api rest assured\",\"dueDate\":\"2010-01-30\"}")
			.contentType(ContentType.JSON)
		.when()	
			.post("/todo")
		.then()
			.statusCode(400)
			.body("message", CoreMatchers.is("Due date must not be in past"))
		;
	}

}