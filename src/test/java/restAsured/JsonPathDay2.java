package restAsured;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static io.restassured.RestAssured.*;

public class JsonPathDay2 {


    @BeforeClass
    public void setUP() {
        baseURI = "https://www.example.yufu.dev.cc";
        basePath = "/wp-json/wp/v2/";
        useRelaxedHTTPSValidation();
    }


    @Test
    public void getRequest() {
        Response response =
                given()
                        .auth().preemptive().basic("yufu", "yufu").
                        when()

                        .get("/posts");

        JsonPath jp = response.jsonPath();

//        System.out.println(jp.getInt("[0].author"));
        System.out.println(jp.getInt("author[0]"));


        List<Object> lst = jp.getList("author");
        List<Integer> lstNum = jp.getList("author", Integer.class);
        System.out.println(lstNum);


        System.out.println(jp.getString("_links.version-history[0].count"));
        List<String> list = jp.getList("_links.version-history.count", String.class);
        System.out.println(list);

    }


    @Test
    public void getRequest2() {

        Response response =
                given().get("http://ergast.com/api/f1/drivers.json");

        JsonPath jp = response.jsonPath();

        List<String> list = jp.getList("MRData.DriverTable.Drivers.givenName", String.class);
        System.out.println(jp.getString("MRData.DriverTable.Drivers[0].givenName"));
        System.out.println(list);

    }

    @Test
    public void getRequestMAP() {

        Response response =
                given().get("http://ergast.com/api/f1/drivers.json");

        JsonPath jp = response.jsonPath();

        Map<String, Object> map = jp.getMap("MRData.DriverTable.Drivers[0]");
        Map<String, String> map1 = jp.getMap("MRData.DriverTable.Drivers[0]", String.class, String.class);

        System.out.println(map);
        System.out.println(map.keySet());


        // jsonpath rest assured use GPath from groovy
        //get the firsname = george
        System.out.println("Drivers names with george ");
                                                                    //.find returns the first element
        System.out.println(jp.getList("MRData.DriverTable.Drivers.findAll{ it.givenName == 'George'}"));

        System.out.println("Driver name george and american");
        System.out.println(jp.getList("MRData.DriverTable.Drivers.findAll{ it.givenName == 'George'" +
                "&& it.nationality== 'American'}"));
    }


    @Test
    public void FindDriverGivenName3Letters() {

        Response response =
                given().get("http://ergast.com/api/f1/drivers.json");

        JsonPath jp = response.jsonPath();
        Map<String, String> map1 = jp.getMap("MRData.DriverTable.Drivers[0]", String.class, String.class);

        System.out.println("Drivers names with 3 letters ");

        List<Object> list = jp.get("MRData.DriverTable.Drivers.findAll{ it.givenName.length() == 3}");
        System.out.println(list);

    }


    @Test
    public void Predicate() {

        Response response =
                given().get("http://ergast.com/api/f1/drivers.json");

        JsonPath jp = response.jsonPath();
        Map<String, String> map1 = jp.getMap("MRData.DriverTable.Drivers[0]", String.class, String.class);

        System.out.println("Drivers names with 3 letters ");

        // whatever comes before findAll must be a type of collection , object ,string arr , list

        List<Object> list = jp.getList("MRData.DriverTable.Drivers.findAll{ driver-> driver.givenName.length() == 3}");
        Predicate<String> p = h -> h.length() == 4;
        System.out.println(list);


        // drivers lastname with the names.length() equal == 3
        System.out.println();
        System.out.println(jp.getList("MRData.DriverTable.Drivers.findAll{ driver-> driver.givenName.length() == 3}.familyName"));
    }


    @Test
    public void Driver() {
        Response response =
                given().get("http://ergast.com/api/f1/drivers.json");

        JsonPath jp = response.jsonPath();

        //single json object
        // data binding ----> Binding jsoon field in POJO field
        // we need external deserializer from databinding

        Driver driverObj = jp.getObject("MRData.DriverTable.Drivers[1]", Driver.class);

        System.out.println(driverObj);

    }

    @Test
    public void ResponseType() {
        Response response =
                given().get("http://ergast.com/api/f1/drivers.json");

        //TODO ask how to use response as
//        response.as(Driver );
        JsonPath jp = response.jsonPath();

        Driver driverObj = jp.getObject("MRData.DriverTable.Drivers[1]", Driver.class);

        System.out.println(driverObj);

    }
}