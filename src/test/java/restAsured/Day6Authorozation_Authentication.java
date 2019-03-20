package restAsured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class Day6Authorozation_Authentication {
    Response response;

    @BeforeTest
    public void beforetest() {



        RestAssured.baseURI = "https://www.example.yufu.dev.cc";
        RestAssured.basePath = "/wp-json/wp/v2";
        RestAssured.useRelaxedHTTPSValidation();
    }


    @Test
    public void test2() {

        response = RestAssured.given().
                auth().preemptive().basic("yufu", "yufu").
                contentType(ContentType.JSON).get("/posts/");


        JsonPath json = response.jsonPath();

        assertThat(json.get("[0]._links.version-history[0].count"), is(2));

//        System.out.println(json.prettyPrint());

        String str = json.getString("[1].title.rendered");
        System.out.println(str);
        Assert.assertTrue(response.statusCode() == 200);


        Assert.assertTrue(json.get("[0]._links.version-history[0].count").equals(2));


    }



    @Test
    public void test() {

        response = RestAssured.given().
                auth().preemptive().basic("yufu", "yufu").
                contentType(ContentType.JSON).queryParam("").get("/posts/");


        JsonPath json = response.jsonPath();

//       String []str = json.get("[0]", String[].class);
//
//        System.out.println(str);

    }
}
