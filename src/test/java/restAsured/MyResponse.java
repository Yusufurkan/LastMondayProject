package restAsured;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MyResponse {
	
	private String name;
	private String location;
	private int age;
//	private List<Book> books;

}
