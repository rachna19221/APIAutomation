package userManagement;

import io.restassured.response.Response;

public class APIActions {
private methodGetInterfaces getRequest;
private methodPostInterface postRequest;
public APIActions(methodGetInterfaces getRequest,methodPostInterface postRequest){
    this.getRequest=getRequest;
    this.postRequest=postRequest;
}
public Response sendGet(String endPoint){
    return getRequest.get(endPoint);

}
public Response sendPost(String endpoint, Object body){
    return postRequest.post(endpoint,body);
}
}
