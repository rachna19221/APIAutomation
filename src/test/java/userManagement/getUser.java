package userManagement;

import core.statusCode;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.JsonReader;
import utils.propertyReader;
import utils.softAssertionUtil;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;
import static org.testng.Assert.assertEquals;


public class getUser {
    softAssertionUtil softAssert=new softAssertionUtil();

    //private String token;


    @Test
    public void validateGetResponseBody() {
        // Set base URI for the API
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Send a GET request and validate the response body using 'then'
        given()
                .when()
                .get("/todos/1")
                .then()
                .assertThat()
                .statusCode(200)
                .body(not(isEmptyString()))
                .body("userId", equalTo(1));
    }


    @Test
    public void validateResponseHasItems() {
        // Set base URI for the API
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Send a GET request and store the response in a variable
        Response response = given()
                .when()
                .get("/posts");

        // Use Hamcrest to check that the response body contains specific items
        assertThat(response.jsonPath().getList("title"), hasItems("sunt aut facere repellat provident occaecati excepturi optio reprehenderit", "qui est esse"));
    }

    @Test
    public void validateResponseHasSize() {
        // Set base URI for the API
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Send a GET request and store the response in a variable
        Response response = given()
                .when()
                .get("/comments")
                .then()
                .extract()
                .response();

        // Use Hamcrest to check that the response body has a specific size
        assertThat(response.jsonPath().getList(""), hasSize(500));

    }

    @Test
    public void validateListContainsItems() {
        // Set base URI for the API
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Send a GET request and store the response in a variable
        Response response = given()
                .when()
                .get("/users")
                .then()
                .extract()
                .response();

        // Use Hamcrest to check that the response body contains specific items
        List<String> expectedNames = Arrays.asList("Leanne Graham", "Ervin Howell", "Clementine Bauch");

        assertThat(response.jsonPath().getList("name"), hasItems(expectedNames.toArray(new String[0])));
    }

    @Test
    public void validateListContainsInOrder() {
        // Set base URI for the API
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Send a GET request and store the response in a variable
        Response response = given()
                .when()
                .get("/comments?postId=1")
                .then()
                .extract()
                .response();

        // Use Hamcrest to check that the response body contains specific items in a specific order
        List<String> expectedEmails = Arrays.asList("Eliseo@gardner.biz", "Jayne_Kuhic@sydney.com", "Nikita@garfield.biz", "Lew@alysha.tv", "Hayden@althea.biz");
        assertThat(response.jsonPath().getList("email"), contains(expectedEmails.toArray(new String[0])));
    }


    @Test(groups="SmokeSuite")
    public void testGetUserListWithHeader() {
        given()
                .header("Content-Type", "application/json")
                .header("x-api-key","reqres-free-v1")
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200);
        System.out.println("testGetUserListWithHeader Executed Successfully");

    }

    @Test(groups="RegressionSuite")
    public void testWithTwoHeaders() {
        given()
                .header("Authorization", "bearer ywtefdu13tx4fdub1t3ygdxuy3gnx1iuwdheni1u3y4gfuy1t3bx")
                .header("Content-Type", "application/json")
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200);
        System.out.println("testWithTwoHeaders Executed Successfully");
    }

    @Test(groups={"SmokeSuite","RegressionSuite"})
    public void testGetUserList() {
        // Set base URI for the API
        RestAssured.baseURI = "https://reqres.in/api";

        // Create a Map to hold headers
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("x-api-key","reqres-free-v1");
        headers.put("Authorization", "bearer ywtefdu13tx4fdub1t3ygdxuy3gnx1iuwdheni1u3y4gfuy1t3bx");

        // Send a GET request with headers
        given()
                .headers(headers)
                .when()
                .get("/users?page=2")
                .then()
                .statusCode(200)
                .body("page", equalTo(2))
                .body("data[0].first_name", equalTo("Michael"))
                .body("data[0].last_name", equalTo("Lawson"));
    }

   /* @Test(description = "validate 204 for Delete user", groups = {"RegressionSuite", "B_User"})
    public void verifyStatusCodeDelete() {


        Response resp = given().delete("https://reqres.in/api/users/2");
        assertEquals(resp.getStatusCode(), 204);
        System.out.println("***********************************PASS*******************************");

    }*/




    @Test(groups="SmokeSuite")
    public void validateWithDataFromPropertiesFile() throws IOException, ParseException {
        String serverAddress = propertyReader.propertyReader("environment.properties","serverAddress");
        // Set base URI for the API
        System.out.println("Server Address is : " + serverAddress);
        RestAssured.baseURI = serverAddress;


        // Send a GET request and validate the response body using 'then'
        given()
                .when()
                .get("/todos/1")
                .then()
                .assertThat()
                .statusCode(200)
                .body(not(isEmptyString()))
                .body("userId", equalTo(1));
        System.out.println("validateWithDataFromPropertiesFile executed successfully" + serverAddress);
    }

    @Test
    public void validateWithDataFromPropertiesAndsonFile() throws IOException, ParseException {
        String serverAddress = propertyReader.propertyReader("environment.properties","serverAddress");
        // Set base URI for the API
        System.out.println("Server Address is : " + serverAddress);
        RestAssured.baseURI = serverAddress;
        String userId = JsonReader.getTestData("userId");
        // Send a GET request and validate the response body using 'then'
        given()
                .when()
                .get("/todos/1")
                .then()
                .assertThat()
                .statusCode(200)
                .body(not(isEmptyString()))
                .body("userId", equalTo(Integer.valueOf(userId)));

        System.out.println("validateWithDataFromPropertiesFile executed successfully" + serverAddress);
    }

    @Test
    public void validateWithDataFromPropertiesAndsonFileWithSoftwAssert() throws IOException, ParseException {
        String serverAddress = propertyReader.propertyReader("environment.properties","serverAddress");
        // Set base URI for the API
        System.out.println("Server Address is : " + serverAddress);
        RestAssured.baseURI = serverAddress;
        String userId = JsonReader.getTestData("userId");
        // Send a GET request and validate the response body using 'then'
        Response resp=given()
                .when()
                .get("/todos/1");

        softAssert.assertEquals(resp.getStatusCode(), statusCode.SUCCESS.code,"Status code validation");
        softAssert.assertEquals(resp.body().path("userId"),Integer.valueOf(userId),"Validate user id value " + resp.body().path("userId"));
        softAssert.assertAll();
        System.out.println("Validate user id value " + resp.body().path("userId"));
    }

   /* @Test
    public void softAssertion(){

        softAssert.assertTrue(true,"Testing softassertion success");
        softAssert.assertTrue(false,"Testing softassertion success");
        softAssert.assertAll();
    }*/


    @DataProvider(name = "testdata")
    public Object[][] testData() {
        return new Object[][] {
                {"1", "John"},
                {"2", "Jane"},
                {"3", "Bob"}
        };
    }

    @Test(dataProvider = "testdata")
    @Parameters({"id", "name"})
    public void testEndpoint(String id, String name) {
        given()
                .param("id", id)
                .param("name", name)
                .when()
                .get("https://reqres.in/api/users")
                .then()
                .statusCode(401);
    }

    @Test(groups="testJson")
    public void Test() throws IOException, ParseException {
        System.out.println(JsonReader.getJsonArrayData("languages",2));
       // Map<String,String> contactDetails=new HashMap<>();
        for (Object obj:JsonReader.getJsonArray("contact")){

            System.out.println(obj);

        }



    }
}















