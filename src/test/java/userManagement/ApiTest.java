package userManagement;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.postRequestBody;

import java.util.ArrayList;
import java.util.List;

public class ApiTest {

    @Test(groups="SmokeSuite")
    public void createUserTest() {
        APIActions api = new APIActions(new getImplementInterface()
                , new postImplementInterface());
        postRequestBody user = new postRequestBody();
        List<String> listlanguage=new ArrayList<>();
        listlanguage.add("java");
        listlanguage.add("python");
        user.setJob("LEAD QA");
        user.setName("Rachna Pattnaik");
        user.setLanguages(listlanguage);

        Response response = api.sendPost("https://reqres.in/api/users", user);
        System.out.println("Status: " + response.getStatusCode());
        System.out.println(response.asPrettyString());
    }
}
