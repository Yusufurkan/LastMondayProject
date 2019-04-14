package restAsured.api_Driver;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.Data;
import org.testng.annotations.Test;


import java.io.IOException;
import java.util.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class BeforeIntervie_Prep {


    @Test
    public void getHTTP() {
        RestAssured.get("http://ergast.com/api/f1/drivers.json").then().statusCode(200);
    }

    @Test
    public void getHTTP2() {
        String base = RestAssured.baseURI = "http://ergast.com";
        String path = RestAssured.basePath = "/api/f1/drivers.json";

        Response res = RestAssured.get(base + path);
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test
    public void pojo2() throws IOException {
        RestAssured.get("http://ergast.com/api/f1/drivers.json").then().body("MRData.DriverTable.Drivers[0].driverId" , is("abate"));
    }

    @Test
    public void basicDeserilization() {
        Response res = RestAssured.get("http://ergast.com/api/f1/drivers.json");
        JsonPath json = res.jsonPath();
        Map<Object, Object> map = json.getMap("MRData.DriverTable.Drivers[0]");
        System.out.println(map);
        System.out.println(map.get("nationality"));
    }

    @Test
    public void basicDeserilization2() {
        Response res = RestAssured.get("http://ergast.com/api/f1/drivers.json");
        JsonPath json = res.jsonPath();
        List<Object> aa = json.getList("MRData.DriverTable.Drivers");


        List<Map<String, String>> list = new ArrayList<>();

        for (int i = 0; i < aa.size(); i++) {
            Map<String, String> tempMap = json.getMap("MRData.DriverTable.Drivers[" + i + "]");
            list.add(tempMap);
        }
    }

    @Test
    public void pojo0() throws IOException {
        Response res = RestAssured.get("http://ergast.com/api/f1/drivers.json");
        JsonPath json = res.jsonPath();

        Drivers[] a = json.getObject("MRData.DriverTable.Drivers", Drivers[].class);

        for (int i = 0; i < a.length; i++) {
            assertThat(a[i], hasProperty("givenName"));
        }


    }

    @Test
    public void deserialize() throws IOException {
        String json = "{\"city\":\"Jos\",\"country\":\"Nigeria\",\"houseNumber\":\"13\",\"lga\":\"Jos South\",\n" +
                "\"state\":\"Plateau\",\"streetName\":\"Jonah Jann\",\"village\":\"Bukuru\",\"ward\":\"1\"}";
        Gson gson = new GsonBuilder().create();
        Address address = gson.fromJson(json, Address.class);
        System.out.println(address.toString());
    }

    @Test
    public void pojo() throws IOException {
        Response res = RestAssured.get("http://ergast.com/api/f1/drivers.json");
        ObjectMapper om = new ObjectMapper();

        String json = "{\"city\":\"Jos\",\"country\":\"Nigeria\",\"houseNumber\":\"13\",\"lga\":\"Jos South\",\n" +
                "\"state\":\"Plateau\",\"streetName\":\"Jonah Jann\",\"village\":\"Bukuru\",\"ward\":\"1\"}";

        Address ad = om.readValue(json, Address.class);
        System.out.println(ad);

    }

    @Test
    public void MapOb(){
    // List<Drivers>
    RestAssured.baseURI= "http://ergast.com/api/f1/drivers.json";

        JsonPath json = RestAssured.get().jsonPath();
        List<Object> list = json.getList("MRData.DriverTable.Drivers");

        List<Drivers> drivers = new ArrayList<>();

        for (int i = 0; i <list.size() ; i++) {
            Drivers driver = json.getObject("MRData.DriverTable.Drivers["+i+"]", Drivers.class);
            drivers.add(driver);
            System.out.println(drivers.get(1).getFamilyName());
        }

    }












    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public class Address {

        private String city;
        private String country;
        private String houseNumber;
        private String lga;
        private String state;
        private String streetName;
        private String village;
        private String ward;

    }
}
