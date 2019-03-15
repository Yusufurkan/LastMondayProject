package pages;

import utilities.Driver;
import org.openqa.selenium.support.PageFactory;

public class YourLunchAccountPage {

    public YourLunchAccountPage(){
        PageFactory.initElements(Driver.getDriver(), this );
    }
}
