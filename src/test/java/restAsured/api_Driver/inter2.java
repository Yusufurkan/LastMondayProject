package restAsured.api_Driver;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.openqa.selenium.json.Json;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class inter2 {

    /*
    One method will accept author name, make a GET request to this endpoint:
    https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=c0feb0bc81c74e9284814912f6ccaa4a  endpoint,
    and return title(s) belong to that author.
    One method return all articles if  source['id'] is not null.
    This app should have exception handling.
    Write unit tests for method in #1 and #2
     */

    public static void main(String[] args) {
        System.out.println(getTitle("Eustance Huang"));
    }


    public static List<String> getTitle(String q) {


        RestAssured.baseURI = "https://newsapi.org/v2/top-headlines";

        Response response = RestAssured.given().
                queryParam("country", "us").
                queryParam("category", "business").
                queryParam("apiKey", "c0feb0bc81c74e9284814912f6ccaa4a").get();

        response.then().statusCode(200);

        List<String> authors = new ArrayList<>();

        JsonPath json = response.jsonPath();
        List<Object> list = json.getList("articles");


        for (int i = 0; i < list.size(); i++) {
            if (json.get("articles[" + i + "].author") == null) {
                continue;
            }
            if (json.get("articles[" + i + "].author").toString().equals(q)) {
                authors.add(json.get("articles[" + i + "].author").toString());
            }

        }


        return authors;
    }


    @Test
    public void titleGrovey() {
        RestAssured.baseURI = "https://newsapi.org/v2/top-headlines";

        Response response =
                RestAssured.
                        given().
                        queryParam("country", "us").
                        queryParam("category", "business").
                        queryParam("apiKey", "c0feb0bc81c74e9284814912f6ccaa4a").get();
        response.then().statusCode(200);

        JsonPath json = response.jsonPath();

        List<Object> al = json.getList("articles.findAll{ it.author != 'null' }");

        System.out.println(al);

//        System.out.println(jp.getList("MRData.DriverTable.Drivers.findAll{ it.givenName == 'George'" +
//                "&& it.nationality== 'American'}"));
    }
    @Test
    public void checkBody() {
        RestAssured.baseURI = "https://newsapi.org/v2/top-headlines";

        Response response =
                RestAssured.
                        given().
                        queryParam("country", "us").
                        queryParam("category", "business").
                        queryParam("apiKey", "c0feb0bc81c74e9284814912f6ccaa4a").get();
        response.then().statusCode(200);

        JsonPath json = response.jsonPath();

        List<Object> al = json.getList("articles.findAll{ it.author != 'null' }");

        System.out.println(al);

//        System.out.println(jp.getList("MRData.DriverTable.Drivers.findAll{ it.givenName == 'George'" +
//                "&& it.nationality== 'American'}"));
    }
}
