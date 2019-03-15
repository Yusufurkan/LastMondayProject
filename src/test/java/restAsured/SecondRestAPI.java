package restAsured;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class SecondRestAPI {


    @BeforeClass
    public void setUP() {
        baseURI = "https://www.example.yufu.dev.cc";
        basePath = "/wp-json/wp/v2/";

    }

    @Test
    public void simpleGetRequest() {

        given()
                .relaxedHTTPSValidation().
                when()
                .log().all()
                .get("/posts/").
                then()
                .log().ifValidationFails()
                .statusCode(200);


    }

    @Test
    public void simpleGetRequest2() {

        given()
                .relaxedHTTPSValidation().
                when()
                .log().all()
                .get("/posts/").
                then()
                .log().ifValidationFails()
                .assertThat()
                .statusCode(201);

    }


    @Test
    public void FilterTheResult() {

        given()
                .relaxedHTTPSValidation().
                when()
                .log().all()
                .queryParam("per_page", 1)
                .get("/posts/").
                then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void quaryParameter() {

        /**
         * TThis one fails because when I get the query parameter
         * which returns array thats why the result is compared int to object
         * has item works fine
         */

        given()
                .relaxedHTTPSValidation().
                when()
                .log().all()
                .queryParam("per_page", 1)
                .get("/posts/").
                then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .and()
//                .body("id", hasItem(20));
                .body("id", is(20));
    }


    @Test
    public void printBody() {

        given()
                .relaxedHTTPSValidation().
                when()
                .get("/posts/")
                .body().prettyPrint();
    }


    @Test
    public void GetRequestForSingleItem() {

        given()
                .relaxedHTTPSValidation().
        when()
                .log().all()
                .pathParam("id", 5)             // we can split up the passing parameter into two steps
//                .pathParam("whatever", 5)             // we can split up the passing parameter into two steps
//                .get("whatever", 5)              // you have to have the same string in get method you will have to pass in pathParam
                .get("/posts/{id}" , 5).
//                .get("/posts/{id}" , 5).    // or you cn write in one line

        then()
                .log().all()
        .assertThat()
                .statusCode(200)
        .and()
                .body("id", equalTo(5));
//               .body("title.render" , is("I MADE A CHANGE HERE"));
    }


    @Test
    public void POST(){

        given()
                .relaxedHTTPSValidation()
                .auth().preemptive().basic("yufu" , "yufu")
                .contentType(ContentType.JSON).
        when()
                .body("{\n" +
                        "\t\"title\" : \"From rest-assured\",\n" +
                        "\t\"content\" : \"This is the POST man\",\n" +
                        "\t\"status\" : \"publish\"\n" +
                        "}")
                .log().all()
                .post("/posts").
        then()
                .log().all()
                .statusCode(201);

    }

    @Test
    public void PUT(){

        given()
                .relaxedHTTPSValidation()
                .auth().preemptive().basic("yufu" , "yufu")
                .contentType(ContentType.JSON).
        when()
                .body("{\n" +
                        "\t\"title\" : \"UPDATED WITH OUT METHOD   From rest-assured\"\n" +
                        "}")
                .log().all()
                .pathParam("new title", 23)
                .put("/posts/{new title}").
//                .put("/posts/{23}").
        then()
                .log().all()
                .statusCode(200)
                .body("title.rendered" , equalTo("UPDATED WITH OUT METHOD   From rest-assured" ));
    }


    @Test
    public void DELETE(){

        given()
                .relaxedHTTPSValidation()
                .auth().preemptive().basic("yufu" , "yufu")
                .contentType(ContentType.JSON).
        when()
                .log().all()
//                .pathParam("new title", 23)
//                .put("/posts/{new title}").
                .queryParam("force", true)
                .delete("/posts/23").
        then()
                .log().all()
                .statusCode(200)
                .body("deleted" , is(true));

    }














}
