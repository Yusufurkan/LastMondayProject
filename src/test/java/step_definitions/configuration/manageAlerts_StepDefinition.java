package step_definitions.configuration;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.BrowserUtils;
import utilities.Driver;
import utilities.Pages;


public class manageAlerts_StepDefinition {

    Pages pages = new Pages();

    public static int sizeBeforeAction;
    public static int sizeAfterAction;

    @Given("go to {string}")
    public void go_to(String functionality) {
        pages.lunchPage().goToFunctionality(functionality);
    }

    @Given("click on create and fill the required fields")
    public void click_on_create_and_fill_the_required_fields() {
        pages.createAlertPage().createAlert();
    }

    @Then("Get the size of alert")
    public void get_the_size_of_alert() {
        sizeBeforeAction = pages.alertsPage().alertsInput.size();
    }


    @Then("Verify alert size decreased by one")
    public void verify_alert_size_decreased_by_one() {

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 5);
        wait.until(ExpectedConditions.invisibilityOf(Driver.getDriver().findElement(By.xpath("//h4[@class='modal-title']"))));
        Assert.assertTrue(sizeBeforeAction - 1 == pages.alertsPage().alertsInput.size());
    }


    // *************************************************** Archive Alert  ******************************************************

    @Then("click on the magnet on the search button")
    public void click_on_the_magnet_on_the_search_button() {
        pages.constants().magnet.click();
    }

    @Then("click on filter")
    public void click_on_filter() {
        pages.constants().filter.click();
    }

    @Then("click on {string} in filters")
    public void click_on_in_filters(String filter) {
        BrowserUtils.getElementInTheList(pages.constants().filters, filter).click();
        BrowserUtils.waitForVisibility(pages.constants().searchBoxFilter.get(0), 3);

    }

    @Then("get the size of alerts before action")
    public void get_the_size_of_alerts_before_action() {
        sizeBeforeAction = pages.alertsPage().alertsInput.size();
    }

    @Then("select the last alert")
    public void select_the_last_alert() {
        BrowserUtils.wait(2);

        // TODO fix if the archive is empty go to alerts and archive alert
//        if (pages.alertsPage().alertsInput.size() == 0) {
//            pages.lunchPage().goToFunctionality("alerts");
//            pages.alertsPage().alertsInput.get(pages.alertsPage().alertsInput.size() - 1).click();
//            the_selected_Alert("archive");
//            click_on_in_filters("archive");
//        }
        WebElement lastAlert = pages.alertsPage().alertsInput.get(pages.alertsPage().alertsInput.size() - 1);
        lastAlert.click();
    }

    @Then("{string} the selected Alert")
    public void the_selected_Alert(String action) {


        int clickThis = 0;
        switch (action.toLowerCase()) {
            case "export":
                clickThis = 0;
                break;
            case "archive":
                clickThis = 1;
                break;
            case "unarchive":
                clickThis = 2;
                break;
            case "delete":
                clickThis = 3;
                break;
        }
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 3);
        wait.until(ExpectedConditions.not(ExpectedConditions.stalenessOf(pages.alertsPage().action)));
        BrowserUtils.waitForClickablility(pages.alertsPage().action, 3).click();
        pages.alertsPage().actions.get(clickThis).click();
        try {
            BrowserUtils.waitForInvisibility(pages.alertsPage().action, 2);

        } catch (TimeoutException e) {
        }

        if (clickThis == 3) {
            pages.alertsPage().confirmationPopupOk.click();
            BrowserUtils.wait(2);
        }
    }

    @Then("get the size of alerts after action")
    public void get_the_size_of_alerts_after_action() {
        sizeAfterAction = pages.alertsPage().alertsInput.size();
    }

    @Then("verify the Alert size is increased by one")
    public void verify_the_Alert_size_is_increased_by_one() {
        System.out.println(sizeBeforeAction);
        System.out.println(sizeAfterAction);
        Assert.assertTrue(sizeBeforeAction + 1 == sizeAfterAction);
    }

    @Then("verify the Alert size is decreased by one")
    public void verify_the_Alert_size_is_decreased_by_one() {
        System.out.println(sizeBeforeAction);
        System.out.println(sizeAfterAction);
        Assert.assertTrue(sizeBeforeAction - 1 == sizeAfterAction);
    }

}
