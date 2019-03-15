package restAsured;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UserActions {

    @BeforeClass
    public void setUP(){

        baseURI = "https://www.example.yufu.dev.cc";
        basePath = "/wp-json/wp/v2/";


    }


// we go as a public user thats why we only see one admin
    @Test
    public void GetAdminInfo(){

        given()
                .relaxedHTTPSValidation().
        when()
                .get("/users").
        then()
//                .statusCode(HttpStatus.SC_OK) // same status validation
                .statusCode(200)
                .contentType(ContentType.JSON)
                .header("Content-Type", "application/json; charset=UTF-8")
                .body("id", hasItem(1))
                .body("id", hasSize(1))
                .log().all();


    }


    @Test
    public void publicUserCantCreate(){

        given()
                .relaxedHTTPSValidation().
                contentType(ContentType.JSON).
        when()
                .log().all()
                .body("{\n" +
                        "\t\n" +
                        "\t\"username\" : \"subsciber\" ,\n" +
                        "\t\"name\" : \"follower\" ,\n" +
                        "\t\"first_name\" : \"gigid\", \n" +
                        "\t\"last_name\" : \"bona\" ,\n" +
                        "\t\"email\" : \"asdfh@g.com\" ,\n" +
                        "\t\"roles\" : \"subscriber\" ,\n" +
                        "\t\"password\" : \"yufu\" \n" +
                        "\t\n" +
                        "\t\n" +
                        "}")
                .post("/users").

        then()
//                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .statusCode(401)
//                .header("Content-Type", "application/json; charset=UTF-8")
                .body("code", is("rest_cannot_create_user"))
                .log().all();


    }


    @Test
    public void CreateUserAdmin(){

        given()
                .relaxedHTTPSValidation().
                contentType(ContentType.JSON)
                .accept(ContentType.JSON)               // this is returns a json format. rest returns json by default anyways
                .auth().preemptive().basic("yufu" , "yufu").
                when()
                .log().all()
                .body("{\n" +
                        "\t\n" +
                        "\t\"username\" : \"ADMIN can do anything\" ,\n" +
                        "\t\"name\" : \"admin\" ,\n" +
                        "\t\"first_name\" : \"gigid\", \n" +
                        "\t\"last_name\" : \"bona\" ,\n" +
                        "\t\"email\" : \"asasdh@g.com\" ,\n" +
                        "\t\"roles\" : \"subscriber\" ,\n" +
                        "\t\"password\" : \"yufu\" \n" +
                        "\t\n" +
                        "\t\n" +
                        "}")
                .post("/users").

                then()
                .statusCode(HttpStatus.SC_CREATED)
                .statusCode(201)
                .body("name", is("admin"))
                .log().all();


    }


    @Test
    public void AdminUserCanEdit(){

        given()
                .relaxedHTTPSValidation().
                contentType(ContentType.JSON)
                .auth().preemptive().basic("yufu" , "yufu").
        when()
                .log().all()
                .body("{\n" +
                        "\n" +
                        "\t\"first_name\" : \"changed\", \n" +
                        "\t\"name\" : \"changed\" ,\n" +
                        "\t\"email\" : \"asdgasg@g.com\" ,\n" +
                        "\t\"roles\" : \"author\" ,\n" +
                        "\t\"password\" : \"user\" \n" +
                        "\t\n" +
                        "\t\n" +
                        "}")
                .pathParam("change", 7)
                .put("/users/{change}").

        then()
                .statusCode(200)
                .body("first_name" , equalTo("changed"))
                .body("name" , equalTo("changed"))
                .body("email" , equalTo("asdgasg@g.com"))
                .log().all();
    }


    @Test
    public void UserCanDelete(){

        given()
                .relaxedHTTPSValidation()
                .auth().preemptive().basic("yufu" , "yufu")
                .params("force", true)
                .param("reassign", 1)
                .contentType(ContentType.JSON).
        when()
                .log().all()
                .delete("/users/7").
        then()
                .statusCode(200)
                .body("deleted" , is(true));

    }

}
