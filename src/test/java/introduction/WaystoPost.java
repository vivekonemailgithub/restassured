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



public class WaystoPost {

    //4 different ways to post data
    //1. Using HashMap
    //2. Using POJO
    //3. Using org.json
    //4. Using external json file

    //1. Using HashMap is alredy done in the previous class 

    pojo pojo=new pojo();
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    //@Test
    void testPostusingHashmap()
    {

     HashMap data =new HashMap();

     data.put("name", "vivek1");
     data.put("job","developer");

     given()
     .contentType("application/json")
     .body(data)

     .when()
     .post("https://reqres.in/api/users")

     .then()
     .statusCode(201)
     .log().all()
     .body("name",equalTo("vivek1"))
     .body("job",equalTo("developer"));
     


    }

   
    @SuppressWarnings({ "unchecked", "rawtypes" })
    //@Test
    void testPostusingHashmapwithVariable()
    {

     HashMap data =new HashMap();

     data.put("name", "vivek1");
     data.put("job","developer");

     int res=given()
     .contentType("application/json")
     .body(data)

     .when()
     .post("https://reqres.in/api/users")
     .jsonPath().getInt("id");


     System.out.println(res);
     


    }


    //@SuppressWarnings({ })
    //@Test
    void testPostusingorgJsonwithVariable()
    {

    JSONObject data=new JSONObject();
    data.put("name", "vivek1");
    data.put("job","developer");

     int res=given()
     .contentType("application/json")
     .body(data)

     .when()
     .post("https://reqres.in/api/users")
     .jsonPath().getInt("id");


     System.out.println(res);
     


    }

    
    
    @Test
    void testPostusingExternalJSONFile()
    {
     
     //The goal is to somehow create a JSON Object which will come from JSONTokener which will come from FileReader

        
        try (FileReader reader = new FileReader("/Users/vivekpandey/Desktop/Programming/java/restassured/body.json")) 
        {
            JSONTokener tokener = new JSONTokener(reader);
            JSONObject jsonObject = new JSONObject(tokener);

            // Use the jsonObject as needed
            System.out.println(jsonObject.toString(2)); // Pretty print with an indentation of 2
        } catch (IOException e) {
            e.printStackTrace();
        }

    

     int res=given()
     .contentType("application/json")
     .body(pojo)

     .when()
     .post("https://reqres.in/api/users")
     .jsonPath().getInt("id");


     System.out.println(res);
     


    }


    

    
}
