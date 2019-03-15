package pages;

import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class ControlAccountsPage {
    public ControlAccountsPage(){
        PageFactory.initElements(Driver.getDriver(), this );
    }
}
