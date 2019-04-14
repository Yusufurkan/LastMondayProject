package restAsured;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class MyRequest {
	
	private String name;
	private String location;
	private int age;
	private String address;
	private String date;

}
