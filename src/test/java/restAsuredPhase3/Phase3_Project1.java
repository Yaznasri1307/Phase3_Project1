package restAsuredPhase3;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Phase3_Project1 {
	String emp_id;
	@Test
	public void TC01_GetAllEmployee() {
		RestAssured.baseURI = "http://localhost:3000/employees";
		RequestSpecification request = RestAssured.given();
		Response response = request.get();
		String responseBody = response.getBody().asString();
		int resCode = response.statusCode();
		System.out.println(responseBody);
		System.out.println(resCode);
		Assert.assertEquals(resCode, 200);

		
	}
	
@Test
	public void TC02_GetSingleEmployee() {
	RestAssured.baseURI = "http://localhost:3000/employees";
	RequestSpecification request = RestAssured.given();
	Response response = request.get("/2");
	String responseBody = response.getBody().asString();
	int resCode = response.statusCode();
	System.out.println(responseBody);
	System.out.println(resCode);
	Assert.assertEquals(resCode, 200);
	Assert.assertTrue(responseBody.contains("David"));
	
}

@Test
public void TC03_CreateEmployee() {
	HashMap<String, String> map = new HashMap<String, String>();
	map.put("name", "yazna");
	map.put("salary", "12000");
	map.put("id", "15");

	RestAssured.baseURI = "http://localhost:3000/employees";
	RequestSpecification request = RestAssured.given();
	Response response = request.contentType(ContentType.JSON).accept(ContentType.JSON).body(map).post();
	String responseBody = response.getBody().asString();
	int responseCode = response.getStatusCode();
	System.out.println(responseBody);
	System.out.println(responseCode);
	Assert.assertEquals(responseCode, 201);
	JsonPath path = response.jsonPath();
	emp_id = path.get("id");
	System.out.println("The Emloyee Id is " + emp_id);
}
@Test
public void TC04_DeleteEmployee() {
	RestAssured.baseURI = "http://localhost:3000/employees";
	RequestSpecification request = RestAssured.given();
	Response response = request.delete(emp_id);
	String resBody = response.getBody().asString();
	int resCode = response.statusCode();
	System.out.println(resBody);
	System.out.println(resCode);
	Assert.assertEquals(resCode, 200);

	

}

@Test
public void TC05_TestDeletedEmployee() {
	RestAssured.baseURI = "http://localhost:3000/employees/"+emp_id;
	RequestSpecification request = RestAssured.given();
	Response response = request.get();
	String responseBody = response.getBody().asString();
	int resCode = response.statusCode();
	System.out.println(responseBody);
	System.out.println(resCode);
	Assert.assertEquals(resCode, 404);
}
}
