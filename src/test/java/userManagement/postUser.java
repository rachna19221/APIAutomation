package userManagement;

import core.statusCode;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.postRequestBody;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class postUser {

@Test(groups="SmokeSuite")
    public void setup() {
        // Set base URI for the API

        // Authenticate and get an authorization token
        Response response = given()
                .header("Content-Type", "application/json")
                .header("x-api-key","reqres-free-v1")
                .body("{\n\"name\":\"testingPost\",\n\"job\":\"testpass\"\n}")
                .when()
                .post("https://reqres.in/api/users");
        System.out.println(response.body()+"  "+response.statusCode());
        assertEquals(response.getStatusCode(), statusCode.CREATED.code);

    }

    @Test(groups="SmokeSuite")
    public void validatePostwithPojo() {
        // Set base URI for the API
        // Authenticate and get an authorization token
        postRequestBody po=new postRequestBody();
        po.setJob("testJobPojo");
        po.setName("testNamePojo");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("x-api-key","reqres-free-v1")
                .body(po)
                .when()
                .post("https://reqres.in/api/users");
        System.out.println(response.body()+"  "+response.statusCode()+" using pojo");
        assertEquals(response.getStatusCode(), statusCode.CREATED.code);

    }

    @Test(groups="SmokeSuite")
    public void validatePostwithPojoWithArray() {
        // Set base URI for the API
        // Authenticate and get an authorization token
        List<String> listlanguage=new ArrayList<>();
        listlanguage.add("java");
        listlanguage.add("python");
        postRequestBody po=new postRequestBody();
        po.setJob("testJobPojo");
        po.setName("testNamePojo");
        po.setLanguages(listlanguage);
        Response response = given()
                .header("Content-Type", "application/json")
                .header("x-api-key","reqres-free-v1")
                .body(po)
                .when()
                .post("https://reqres.in/api/users");
        System.out.println(response.getBody()+"  "+response.statusCode()+" using pojo");
        assertEquals(response.getStatusCode(), statusCode.CREATED.code);

    }
}
