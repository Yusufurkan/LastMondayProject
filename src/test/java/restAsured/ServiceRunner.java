package restAsured;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ServiceRunner {
	
	private static MyResponse resp;
	private static ObjectMapper mapper = new ObjectMapper();

	public static void runGetRequest(String uri) {
		Response mr = RestAssured.get(uri);
		
		try {
			resp = mapper.readValue(mr.asString(), MyResponse.class);
		} catch (Exception e) {
			System.out.println("Json couln't map into MyResponse object");
			e.printStackTrace();
		} 
		
	}
	
	public static void runPostRequest(String uri, MyRequest body){
		String json ="";
		try {
			json = mapper.writeValueAsString(body);
		} catch (JsonProcessingException e) {
			System.out.println("Couln't convert form POJO to Json");
			e.printStackTrace();
		}
		Response mr = RestAssured.given().contentType(ContentType.JSON).body(json).post(uri);
		try {
			resp = mapper.readValue(mr.asString(), MyResponse.class);
		} catch (Exception e) {
			System.out.println("Json couln't map into MyResponse object");
			e.printStackTrace();
		} 
			
	}
	
	public static MyResponse getResponse() {
		return resp;
	}
	
}





