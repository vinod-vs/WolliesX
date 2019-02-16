package helper;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.given;

public class RestAssuredHelper {
    private Response response;

    public Response getRequest(String uri) {
        response = given().relaxedHTTPSValidation().when().get(uri);
        return response;
    }

    public Response postRequest(String path, String body) {
        response = given()
                .relaxedHTTPSValidation()
                .header("Content-Type", "application/json;charset=UTF-8")
                .contentType(ContentType.JSON)
                .body(body)
                .when().post(path);
        return response;
    }


}