package introduction;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.*;

import java.io.*;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;

import introduction.pojo;

import io.restassured.response.Response;

public class Session3pathcookies {

    //Get https://reqres.in/api/users?page=2       200

    // Post  https://reqres.in/api/users           201
    /*{
        "name": "morpheus",
        "job": "leader",
        "id": "997",
        "createdAt": "2024-09-23T19:40:20.926Z"
    }*/
    // Update https://reqres.in/api/users/2         200
    /*{
        "name": "morpheus",
        "job": "zion resident",
        "updatedAt": "2024-09-23T19:40:58.725Z"
    }*/

    //Delete https://reqres.in/api/users/2          204

    //query parameter doesnt need to go in the request only path paramter has to go

@Test
void testqueryandPathParam()
{
    
    given()
    .pathParam("pathparam1", "users")
    .queryParam("page", 2)
    .queryParam("id", 5)

    .when()
    .get("https://reqres.in/api/{pathpaam1}")

    .then()
    .statusCode(200);
}


@Test
void cookiesDemo()
{
    
    given()
   

    .when()
    .get("https://google.com")

    .then()
    .statusCode(200)
//since everytime new cookies will be given so test case should fail

    .cookie("AEC","abc")
    .log().all();
    
}

@Test
void cookiesDemowithVariable()
{

    Response response=given()
	
	
	.when()
	.get("https://www.google.com");

	
	//single cookie
    response.getCookie("AEC");

    //all cookies
    Map<String,String> hr;
    
    hr=response.getCookies();

   // System.out.println(hr.keySet());
    //System.out.println(hr.values());
   

    for(String k:hr.keySet())
    {
        String cookieInfo=response.getCookie(k);
        System.out.println(cookieInfo);
    }

	//String responseString=response.asString();
	//System.out.println(responseString);

	System.out.println("Helloe");

    
    
}

    
}
