package pages;

import utilities.Driver;
import org.openqa.selenium.support.PageFactory;

public class EmployeePaymentsPage {

    public EmployeePaymentsPage(){
        PageFactory.initElements(Driver.getDriver(), this );
    }
}
