package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utilities.Driver;

import java.util.List;

public class DatabasePage {
    public DatabasePage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(css = "button[class='dt-button buttons-create']")
    public WebElement newButton;

    @FindBy(id = "DTE_Field_first_name")
    public WebElement firstname;

    @FindBy(id = "DTE_Field_last_name")
    public WebElement lastname;

    @FindBy(id = "DTE_Field_position")
    public WebElement position;

    @FindBy(id = "DTE_Field_office")
    public WebElement office;

    @FindBy(id = "DTE_Field_extn")
    public WebElement extention;

    @FindBy(id = "DTE_Field_start_date")
    public WebElement date;

    @FindBy(id = "DTE_Field_salary")
    public WebElement salary;

    @FindBy(xpath = "//button[@class='btn']")
    public WebElement createButton;

    @FindBy(css = "input[type='search']")
    public WebElement search;

    @FindBy(xpath = "//tbody//td[2]")
    public WebElement firstrow;

    @FindBy(xpath = "(//tr[@role='row'])/td[2]")
    public WebElement verifyName;

    @FindBy(xpath = "(//tr[@role='row'])/td[3]")
    public WebElement verifyPosition;

    @FindBy(xpath = "(//tr[@role='row'])/td[4]")
    public WebElement verifyOffice;

    @FindBy(xpath = "(//tr[@role='row'])/td[5]")
    public WebElement verifyDate;

    @FindBy(xpath = "(//tr[@role='row'])/td[6]")
    public WebElement verifySalary;




}
