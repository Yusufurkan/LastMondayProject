package step_definitions.configuration;

import cucumber.api.java.en.Then;
import org.openqa.selenium.WebElement;
import utilities.BrowserUtils;
import utilities.Pages;

import java.sql.Connection;
import java.util.List;

public class Products_StepDefinition {

    Pages pages = new Pages();

    @Then("enter product name {string}")
    public void enter_product_name(String name) {
        pages.productsPage().product.sendKeys(name);

    }

    @Then("enter product category {string}")
    public void enter_product_category(String category) {

        pages.productsPage().category.click();
        BrowserUtils.wait(2);
        List<WebElement> vendors = pages.productsPage().catogory;

        for (int i = 0; i < vendors.size(); i++) {
            if (vendors.get(i).getText().equalsIgnoreCase(category)) {
                vendors.get(i).click();
            }

        }
    }

    @Then("enter product vendor {string}")
    public void enter_product_vendor(String vedor) {
        pages.productsPage().vendor.click();
        List<String> vendors = BrowserUtils.getElementsText(pages.productsPage().vendors);

        for (String str : vendors) {
            if (str.equalsIgnoreCase(vedor)) {
                pages.productsPage().vendor.sendKeys(vedor);
                pages.productsPage().vedorPopUpEdit.click();
            }
        }


    }

    @Then("enter product price {double}")
    public void enter_product_price(Double double1) {
        pages.productsPage().price.sendKeys("" + double1);

    }



}
