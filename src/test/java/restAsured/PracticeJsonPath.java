package restAsured;

import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class PracticeJsonPath {


    private io.restassured.path.json.JsonPath jsonpath;

    @BeforeClass
    public void setUP(){
        baseURI = "https://www.example.yufu.dev.cc";
        basePath = "/wp-json/wp/v2/";
    }


    @Test
    public void testJsonPath(){
// www.jsonpathfinder.com

        Response response =
        given()
                .relaxedHTTPSValidation().
        when()
                .log().all()
                .get("/users/{1}", 1);


//        System.out.println(response.asString());
//            response.prettyPrint();

       jsonpath = response.jsonPath();

//       String str = jsonpath.setRoot("").getString();


        System.out.println("ID: " +jsonpath.getInt( "id"));
//        System.out.println("TITLE: " + jsonpath.getString("title.rendered"));
        System.out.println("SLUG: " +jsonpath.getString( "slug"));
        System.out.println("link: " + jsonpath.getString("_links.self[0].href"));
    }

















}
