package userManagement;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class postImplementInterface implements methodPostInterface{

    @Override
    public Response post(String endPoint, Object body) {
        return given()
                .header("Content-Type", "application/json")
                .header("x-api-key","reqres-free-v1")
                .body(body)
                .when()
                .post(endPoint)
                .then()
                .extract().response();
    }

}
