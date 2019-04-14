package restAsured.api_Driver;

import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java.bs.A;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import restAsured.DriverPojo;

import java.io.IOException;
import java.util.*;

public class TestDriverPojo {

    Response response;
    JsonPath json;
    ObjectMapper om;

      @BeforeTest
    public void setUp() {
        RestAssured.baseURI = "http://ergast.com/api/f1/drivers.json";
        response = RestAssured.given().contentType(ContentType.JSON).get();
        json = response.jsonPath();
        om = new ObjectMapper();
    }


    //TODO store all the drivers in the map


    @Test
    public void getJson() throws IOException {


    }

    @Test
    public void Map() {
        Map<String, String> map = json.getMap("MRData.DriverTable.Drivers[0]", String.class, String.class);
        System.out.println(map);
    }

    @Test
    public void Map2() {

        List<Map<String, String>> driverMap = new ArrayList<>();
        List<Drivers> list1 = response.jsonPath().getList("MRData.DriverTable.Drivers", Drivers.class);

        System.out.println(list1.size());

        for (Drivers drivers : list1) {
            System.out.println(drivers);
        }

        for (int i = 0; i < list1.size(); i++) {
            Map<String, String> m = json.getMap("MRData.DriverTable.Drivers[" + i + "]", String.class, String.class);
            driverMap.add(m);

        }

        System.out.println();

        System.out.println(driverMap.get(0).get("nationality"));


    }

    @Test
    public void List() {

//         List<Person> list = om.readValue(json, new TypeReference<List<Person>>() {});

//        List<Drivers> list = om.readValue(json , new TypeReference<List<Drivers>>() {} );
    }

    @Test
    public void array() {
        Drivers[] d = response.jsonPath().getObject("MRData.DriverTable.Drivers", Drivers[].class);
        System.out.println(Arrays.toString(d));
        List<Drivers> list = Arrays.asList(d);
    }

    @Test
    public void OM() throws IOException {

        DriverPojo[] dd = json.getObject("MRData.DriverTable.Drivers", DriverPojo[].class);

        System.out.println(Arrays.toString(dd));

        DriverPojo ddd = json.getObject("MRData.DriverTable.Drivers[0]", DriverPojo.class);

//        System.out.println(ddd);


        Response response = RestAssured.get("MRData.DriverTable.Drivers[0]");


        //DriverPojo a = om.readValue(response.getBody().toString(), DriverPojo.class);

//        System.out.println(response.getBody().as(DriverPojo.class));


//
//        DriverPojo arr = om.readValue((DataInput) dd, DriverPojo.class);
//
//        System.out.println(arr);
    }

    @Test
    public void OM2() throws IOException {

//        DriverPojo ddd = json.getObject("MRData.DriverTable.Drivers[0]", DriverPojo.class);
//        System.out.println(ddd);

//        System.out.println(response.jsonPath().get("MRData.DriverTable.Drivers[0]").toString());


        //TODO this one does not work
//        Response response2 = RestAssured.get("MRData.DriverTable.Drivers");
//        System.out.println(response2.jsonPath().get().toString());

//        Map<String, String> map = json.getMap("MRData.DriverTable.Drivers[0]");
//
//        System.out.println(map);

        RestAssured.baseURI = "http://ergast.com/api/f1/drivers.json";
        Response res = RestAssured.given().contentType(ContentType.JSON).get();
        JsonPath json = res.jsonPath();
        ObjectMapper objectMapper = new ObjectMapper();


//        DriverPojo b = objectMapper.readValue(res.jsonPath().getString("MRData.DriverTable.Drivers[0]"), DriverPojo.class);
//        System.out.println(b);

        res.jsonPath().get("MRData.DriverTable.Drivers[0]");
        Drivers ba = objectMapper.readValue(res.asString(), Drivers.class);
        System.out.println(ba);


        Drivers ab = res.as(Drivers.class);
        System.out.println(ab);


//        DriverPojo ba1 = objectMapper.readValue(res.as(DriverPojo), DriverPojo.class);
//        System.out.println(ba1);
    }


    @Test
    public void OM3() throws IOException {


        RestAssured.baseURI = "http://ergast.com/api/f1/drivers.json";
        Response res = RestAssured.given().contentType(ContentType.JSON).get();
        JsonPath json = res.jsonPath();

//        response = response.path("MRData.DriverTable.Drivers[0]");
        response.prettyPrint();


    }




















    @Test
    public void test() {

        String str = "I love disney";
        String temp = "";

        System.out.println(Arrays.toString(str.toCharArray()));

        for (int i = 0; i < str.length(); i++) {
//             str.substring(str.charAt(i))
        }

    }

    @Test
    public void test2() {
        int a = 5;
        int b = 3;

        a = a + b - a;
        a = a * b / a;

        System.out.println(a);

    }

    @Test
    public void factotrial() {

        int i = 5;

        int result = 1;
        for (int j = 1; j <= i; j++) {
            result *= j;
        }
        System.out.println(result);
        System.out.println(fac(6));

    }

    private int fac(int n) {
        if (n == 0)
            return 1;
        return n * fac(n - 1);
    }

    @Test
    public void m() {
        System.out.println(getword("aaaabbcccddaf"));

    }

    private String getword(String str) {
        String result = "";

        for (int i = 0; i < str.length(); i++) {
            int temp = 1;
            if (str.charAt(i) == '1')
                continue;
            str.charAt(i);
            for (int j = i + 1; j < str.length(); j++) {
                if (str.charAt(i) == str.charAt(j)) {
                    temp++;
                }
            }
            result = result + str.charAt(i) + temp;
            str = str.replace("" + str.charAt(i), "1");

        }

        return result;
    }

    @Test
    public void testtt() {
        int[] values = {1, 5, 66, 32, 0, 100};

        int largestA = values[0];
        int largestB = -1;

        for (int i = 0; i < values.length; i++) {

            if (values[i] > largestA) {
                largestB = largestA;
                largestA = values[i];
            } else if (values[i] > largestB && values[i] != largestA) {
                largestB = values[i];
            }
        }
        System.out.println("Largest - " + largestA);
        System.out.println("2nd largest Largest - " + largestB);

    }

    @Test
    public void testt() {

        System.out.println(rev("Amazon", 2, Direction.LEFT));
        //true means take it from the back
        //false means take it from the front

        //azonAM
        //onAmaz
    }

    private String rev(String amazon, int lim, Direction dr) {

        if (dr.equals(Direction.LEFT)) {
            System.out.println("aaaaaaaaaa");
        }

        return amazon.substring(lim) + amazon.substring(0, lim);
    }

    enum Direction {
        LEFT, RIGHT
    }

}
