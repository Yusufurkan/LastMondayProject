package step_definitions.configuration;

import utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utilities.Pages;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class CreateAlertPage {

    Pages pages;

    public CreateAlertPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//select[@name='alert_type']")
    private WebElement recur;

    @FindBy(xpath = "//select[@name='alert_type']")
    public Select recurrence;

    @FindBy(xpath = "//input[@name='specific_day']")
    public WebElement day;

    @FindBy(xpath = "//input[@name='start_hour']")
    public WebElement between;

    @FindBy(xpath = "//input[@name='end_hour']")
    public WebElement and;

    @FindBy(css = "textarea[name='message']")
    public WebElement message;

    @FindBy(xpath = "(//button[@type='button'])[8]")
    public WebElement save;

    @FindBy(xpath = "//div[@class='o_notification_manager']/div")
    public WebElement errorMessage;


    @FindBy(xpath = "//button[@class='btn btn-default btn-sm o_form_button_cancel']")
    public WebElement discard;


    @FindBy(xpath = "//button[@class='btn btn-sm btn-primary']")
    public WebElement warningOk;





    //TODO complete crating an Alert for each recurrences
    public void selectRecurrence(String recurType) {
        Select select = new Select(recur);
        select.selectByVisibleText(recurType);
    }

    public String date(int daysLater) {
        DateTimeFormatter formaterr = DateTimeFormatter.ofPattern("MM/dd/Y");
        Period pr = Period.ofDays(daysLater);
        LocalDate date = LocalDate.now();
        date = date.plus(pr);
        return "" + date.format(formaterr);
    }

    public void createAlert() {
        pages = new Pages();
        pages.alertsPage().create.click();
        pages.createAlertPage().selectRecurrence("Specific Day");
        pages.createAlertPage().day.clear();
        //TODO make the selected day and time dynamic (random)
        pages.createAlertPage().day.sendKeys(pages.createAlertPage().date(10));
        pages.createAlertPage().between.clear();
        pages.createAlertPage().between.sendKeys("01.01");
        pages.createAlertPage().and.clear();
        pages.createAlertPage().and.sendKeys("21.21");
        pages.createAlertPage().message.sendKeys("This the new Alert.");
        pages.createAlertPage().save.click();
    }

    public void deleteAlert() {
        pages = new Pages();
        Driver.nap(1000);
        pages.alertsPage().isAlertDisplayed();
        pages.alertsPage().alertsInput.get(pages.alertsPage().alertsInput.size() - 1).click();
        pages.alertsPage().action.click();
        pages.alertsPage().actions.get(3).click();
        pages.alertsPage().confirmationPopupOk.click();

    }


}
