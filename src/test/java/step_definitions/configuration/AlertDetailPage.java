package step_definitions.configuration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class AlertDetailPage {
    public AlertDetailPage(){
        PageFactory.initElements(Driver.getDriver(), this );
    }

    @FindBy(xpath = "//button[@class='btn btn-primary btn-sm o_form_button_edit']")
    public WebElement edit;

    @FindBy(xpath = "//span[@name='start_hour']")
    public WebElement between;

    @FindBy(xpath = "//span[@name='end_hour']")
    public WebElement and;


}
