package pages;

import utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AlertsPage {
    public AlertsPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//a[@data-menu='107']")
    public WebElement alertBttn;

    @FindBy(xpath = "//tbody//input")
    public List<WebElement> alertsInput;

    @FindBy(xpath = "(//tbody/tr)/td[2]")
    public List<WebElement> alertsMessage;

    @FindBy(xpath = "(//button[@data-toggle='dropdown'])[2]")
    public WebElement action;

    @FindBy(xpath = "(//ul[@class='dropdown-menu'])[4]/li")
    public List<WebElement> actions;

    @FindBy(xpath = "//button[@class='btn btn-primary btn-sm o_list_button_add']")
    public WebElement create;

    @FindBy(css = "button[class='btn btn-sm btn-primary']")
    public WebElement confirmationPopupOk;


    @FindBy(xpath = "//h4[@class='modal-title']")
    public WebElement confirmationPopup;


    public int getRndmAlert() {
        return (int) (Math.random() * alertsInput.size());
    }

    public void selectAction(int action) {
        actions.get(action - 1).click();
    }

    public void isAlertDisplayed() {
        try {
            if (alertsInput.size() == 0) {
                throw new IndexOutOfBoundsException();
            }
        } catch (IndexOutOfBoundsException e) {
            System.err.println("No Alert is available.");
        }

    }

}
