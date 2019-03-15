package step_definitions;

import cucumber.api.java.en.Given;
import utilities.Pages;

public class Login_StepDefinition {
    Pages pages =new Pages();

    @Given("using valid credential sign in as a manager and click lunch")
    public void using_valid_credential_sign_in_as_a_manager_and_click_lunch() {
        pages.login().goLunch();
    }

}