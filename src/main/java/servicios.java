import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static net.serenitybdd.rest.RestRequests.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class servicios {

    @Before
    public void setUp(){
        RestAssured.baseURI = "https://reqres.in/";
        RestAssured.basePath= "/api";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON).build();
    }

    @Test
    public void GetAllUsers(){
        Response response = given().get("/users?page=2");
        Headers headers = response.getHeaders();
        String body = response.getBody().asString();
        int statuscode = response.statusCode();
        String contentType = response.getContentType();
        assertThat(statuscode, equalTo(200));
    }

    @Test
    public void getASingleUser(){
        RestAssured.get("/users/2")
                .then()
                .log()
                .all()
                .statusCode(200)
                .assertThat()
                .body("data.first_name", Matchers.equalTo("Janet"));
    }

    @Test
    public void getAllResources(){
        Response response = given().get("/unknown");
        Headers headers = response.getHeaders();
        String body = response.getBody().asString();
        int statusCode = response.getStatusCode();
        String contentType = response.getContentType();
        assertThat(statusCode, equalTo(200));

        System.out.println("Headers*******************");
        System.out.println(headers);
        System.out.println();
        System.out.println("Body**********************");
        System.out.println(body);
        System.out.println();
        System.out.println("Content type***************");
        System.out.println(contentType);
    }

}
