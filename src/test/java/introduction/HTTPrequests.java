package introduction;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.response.Response;



public class HTTPrequests {

	String getUrl="https://reqres.in/api/users?page=2";
	String responseString;
	int id;
	
	@Test(priority = 1)
	void getUserfromDummyWebsite()
	{
	
	
	Response response=given()
	
	
	.when()
	.get("https://reqres.in/api/users?page=2");

	
	

	responseString=response.asString();
	System.out.println(responseString);

	System.out.println("Helloe");
	
	}

	@Test(priority=2)
	void getUserfromDummyWebsitewithoutresponsevariable()
	{
	
	
	given()
	
	
	.when()
	.get("https://reqres.in/api/users?page=2")
	
	.then()
	.statusCode(200)
	.body("page", equalTo(2))
	.log().all();

	
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test(priority = 3)
	void podtUserfromDummyWebsitewithHashMap()
	{
	
	HashMap hm=new HashMap();
	hm.put("name", "vivek");
	hm.put("job","janotor" );
	
		
	given()
	.contentType("application/json")
	.body(hm)
	
	
	.when()
	.post("https://reqres.in/api/users")
	
	.then()
	.statusCode(201)
	//.body("page", equalTo(2))
	.log().all();

	
	

	
	
	}

	@Test(priority = 4)
	void postUserfromDummyWebsitewithHashMapwithresponsevariable()
	{
	
	HashMap hm=new HashMap();
	hm.put("name", "vivek");
	hm.put("job","janotor" );
	
		
	id=given()
	.contentType("application/json")
	.body(hm)
	
	
	.when()
	.post("https://reqres.in/api/users")
	.jsonPath().getInt("id");

	System.out.println(id);
	
	

	
	

	
	
	}



	@Test(priority=5,dependsOnMethods="postUserfromDummyWebsitewithHashMapwithresponsevariable")
	void updateUserfromDummyWebsitewithHashMapwithresponsevariable()
	{
	
	
		
	 given()
	
	
	
	.when()
	.put("https://reqres.in/api/users/"+id)
	
	.then()
	.statusCode(200);
	

	System.out.println(id);
	
	

	
	

	
	
	}
	
	
	@Test(priority=6,dependsOnMethods="postUserfromDummyWebsitewithHashMapwithresponsevariable")
	void deleteUserfromDummyWebsitewithHashMapwithresponsevariable()
	{
	
	
	
		
	 given()
	
	
	
	.when()
	.delete("https://reqres.in/api/users/"+id)
	
	.then()
	.statusCode(204)
	.log().all();
	

	System.out.println(id);
	
	

	
	

	
	
	}
	
	
}
