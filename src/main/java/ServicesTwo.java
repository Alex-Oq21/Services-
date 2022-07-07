import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class ServicesTwo {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://reqres.in/";
        RestAssured.basePath = "/api";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON).build().log().all();
    }

    @Test
    public void deleteUser() {
        RestAssured.given()
                .delete("/users/2")
                .then()
                .statusCode(204);
    }

    /*@Test
    public void registerUser(){
        RestAssured.given()
                .when()
                .body("{\n" +
                        "    \"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"pistol\"\n" +
                        "}")
                .post("/register");
    }*/

    @Test
    public void loginUser() {
        RestAssured.given()
                .when()
                .body("{\n" +
                        "    \"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"cityslicka\"\n" +
                        "}")
                .post("/login")
                .then()
                .statusCode(200)
                .body("token", Matchers.equalTo("QpwL5tke4Pnpja7X4"));
    }

}
