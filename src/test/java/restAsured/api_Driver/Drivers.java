package restAsured.api_Driver;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Drivers {

  String driverId;
  String url;
  String givenName;
  String familyName;
  String dateOfBirth;
  String nationality;

}

