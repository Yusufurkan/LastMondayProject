package pages;

import utilities.BrowserUtils;
import utilities.Driver;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LunchPage {

    public LunchPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//button[@class='btn btn-sm btn-primary']")
    public WebElement error;

    @FindBy(xpath = "(//div[@class='oe_secondary_menu'])[13]//li")
    public List<WebElement> functions;

    @FindBy(xpath = "//ol[@class='breadcrumb']/li")
    public WebElement title;


    public void goToFunctionality(String functionality) {
        try {
            for (int i = 0; i < functions.size(); i++) {
                if (functions.get(i).getText().equalsIgnoreCase(functionality)) {
                    functions.get(i).click();
                    BrowserUtils.waitUntilTheUrlChanged();
                    return;
                }
            }
            throw new NoSuchElementException("\\nFunctionality does not exist.. Check spelling! ");
        } catch (Exception e) {

        }
    }

    public void closePopup() {
        try {
            error.click();
        } catch (NoSuchElementException e) {
        }
    }


}
