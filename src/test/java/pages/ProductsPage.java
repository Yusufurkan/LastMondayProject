package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.Driver;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductsPage {
    public ProductsPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//input[@class='o_field_char o_field_widget o_input o_required_modifier']")
    public WebElement product;

    @FindBy(xpath = "//input[@class='o_input ui-autocomplete-input']")
    public WebElement category;

    @FindBy(xpath = "(//input[@class='o_input ui-autocomplete-input'])[2]")
    public WebElement vendor;

    @FindBy(xpath = "//input[@class='o_field_float o_field_number o_field_widget o_input']")
    public WebElement price;

    @FindBy(xpath = "(//ul[@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content'])[2]//a")
    public List<WebElement> vendors;

    @FindBy(xpath = "(//ul[@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content'])[1]//a")
    public List<WebElement> catogory;

    @FindBy(xpath = "(//span[@class='o_dropdown_button'])[2]")
    public WebElement dropdownButton;

    @FindBy(xpath = "(//button[@class='btn btn-sm btn-primary'])[2]")
    public WebElement vedorPopUpEdit;

    @FindBy(xpath = "(//button[@class='btn btn-sm btn-primary'])[2]")
    public WebElement vedorPopUpCreate;



}
