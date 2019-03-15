package step_definitions.configuration;


import cucumber.api.java.en.Then;
import org.junit.Assert;
import utilities.Pages;

public class CreateAlerts_StepDefinition {

    Pages pages = new Pages();
    @Then("click on create")
    public void click_on_create() {
        pages.alertsPage().create.click();
    }

    @Then("select recurrence {string}")
    public void select_recurrence(String recurrence) {
        pages.createAlertPage().selectRecurrence(recurrence);
    }

    @Then("enter a date")
    public void enter_a_date() {
        pages.createAlertPage().day.sendKeys(pages.createAlertPage().date(10));
    }

    @Then("enter between and end")
    public void enter_between_and_end() {
        pages.createAlertPage().between.clear();
        pages.createAlertPage().between.sendKeys("01.01");
        pages.createAlertPage().and.clear();
        pages.createAlertPage().and.sendKeys("21.21");
    }


    @Then("click save")
    public void click_save() {
        pages.createAlertPage().save.click();
    }

    @Then("verify error message is displayed")
    public void verify_error_message_is_displayed() {
        Assert.assertTrue(pages.createAlertPage().errorMessage.isDisplayed());
    }

    @Then("enter a message")
    public void enter_a_message() {
        pages.createAlertPage().message.sendKeys("This the new Alert.");
    }

    @Then("click on discard")
    public void click_on_discard() {
        pages.createAlertPage().discard.click();

    }
    @Then("click okay in pop up")
    public void click_okay_in_pop_up() {
        pages.createAlertPage().warningOk.click();
    }

    @Then("verify the size is same")
    public void verify_the_size_is_same() {
        Assert.assertTrue(manageAlerts_StepDefinition.sizeBeforeAction == manageAlerts_StepDefinition.sizeAfterAction);

    }


}
