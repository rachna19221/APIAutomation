package userManagement;

import io.restassured.response.Response;

public interface methodPostInterface {
    Response post(String endPoint , Object body);
}
