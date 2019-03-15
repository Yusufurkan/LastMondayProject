package pages;

import utilities.Driver;
import org.openqa.selenium.support.PageFactory;

public class OrdersbyVendorPage {
    public OrdersbyVendorPage(){
        PageFactory.initElements(Driver.getDriver(), this );
    }
}
