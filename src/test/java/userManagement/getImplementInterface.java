package userManagement;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class getImplementInterface implements methodGetInterfaces {

    @Override
    public Response get(String endPoint) {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .get(endPoint)
                .then()
                .extract().response();
    }

}
