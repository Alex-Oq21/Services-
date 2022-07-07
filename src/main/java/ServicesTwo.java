import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.Before;
import org.junit.Test;

public class ServicesTwo {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://reqres.in/";
        RestAssured.basePath = "/api";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.requestSpecification = new RequestSpecBuilder().build().log().all();
    }

    @Test
    public void deleteUser() {
        RestAssured.given()
                .delete("/users/2")
                .then()
                .statusCode(204);
    }


}
