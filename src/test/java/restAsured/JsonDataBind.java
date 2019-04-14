package restAsured;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;


public class JsonDataBind {


    @Test
    public void DataBindTest() {
        Response response =
                given().get("http://ergast.com/api/f1/drivers.json");

        JsonPath jp = response.jsonPath();
    }

    @Test
    public void ObjectToJSON() throws IOException {

        // map your json object to your java object

       String jsonString = "{\"name\" : \"yusuf\", \"age\" : \"19\"}";
       ObjectMapper om = new ObjectMapper();

      Person obj = om.readValue(jsonString, Person.class );

        System.out.println(jsonString);
        System.out.println(obj);
    }


    @Test
    public void JsontoObject() throws IOException {
        // map your json object to your java object

        String jsonString = "{\"name\" : \"yusuf\", \"age\" : \"19\"}";
        ObjectMapper om = new ObjectMapper();
        Person obj = om.readValue(jsonString, Person.class );

       String jsonF =  om.writeValueAsString(obj);
        // same thing can be done with jsonpath as well you can deserilaize the json format to a object
       // response json path ----> jsonpath.from(response.asString())
       Person p1 = JsonPath.from(jsonString).getObject("", Person.class);

        System.out.println(jsonF);

    }

    @Test
    public void JsonToDriverObj() throws IOException {

        String json = "{\n" +
                "                    \"driverId\": \"arnoux\",\n" +
                "                    \"url\": \"http://en.wikipedia.org/wiki/Ren%C3%A9_Arnoux\",\n" +
                "                    \"givenName\": \"René\",\n" +
                "                    \"familyName\": \"Arnoux\",\n" +
                "                    \"dateOfBirth\": \"1948-07-04\",\n" +
                "                    \"nationality\": \"French\"\n" +
                "                }";

        ObjectMapper om = new ObjectMapper();
        DriverPojo obj = om.readValue(json, DriverPojo.class);
        System.out.println(obj);

        System.out.println(om.writeValueAsString(obj));

    }


    @Test
    public void JsonArrToDriverObj() throws IOException {

        String json = "[{\"name\" : \"yusuf\", \"age\" : \"19\"}, {\"name\" : \"tarik\", \"age\" : \"19\"}, {\"name\" : \"feyaz\", \"age\" : \"19\"}]";
        ObjectMapper om = new ObjectMapper();
        Person [] arr = om.readValue(json, Person[].class);

        //these two are same thing firstone using jakson library second one using Jsonpath

        List<Person> arr1 = JsonPath.from(json).getList("", Person.class);

        System.out.println(om.writeValueAsString(arr));
        System.out.println(arr1);

    }


    @Test
    public void JsonListToDriverObj() throws IOException {

        String json = "[{\"name\" : \"yusuf\", \"age\" : \"19\"}, {\"name\" : \"tarik\", \"age\" : \"19\"}, {\"name\" : \"feyaz\", \"age\" : \"19\"}]";

        List<Person> people = Arrays.asList(new Person("ben", 19), new Person("bura", 32));

        ObjectMapper om = new ObjectMapper();

        //         typeReference is a abstract class thats why we have to close with body {} // it gives you ability to assign type
        List<Person> list = om.readValue(json, new TypeReference<List<Person>>() {});
        System.out.println(om.writeValueAsString(people));


    }

}


class Person{
    int age;
    String name;

    public Person(){

    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }


    public String getName() {
        return name;
    }

    public Person(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

}