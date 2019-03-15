package excelAutomation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class EmployeesPageForm {


    private WebDriver driver;

    public EmployeesPageForm() {
        PageFactory.initElements(Driver.getDriver(), this);
        driver = Driver.getDriver();
    }

    @FindBy(name = "Name_First")
    public WebElement firstName;

    @FindBy(name = "Name_Last")
    public WebElement lastName;

    @FindBy(name = "SingleLine")
    public WebElement role;

    @FindBy(id = "Radio_1")
    public WebElement male;

    @FindBy(id = "Radio_2")
    public WebElement female;

    @FindBy(name = "Email")
    public WebElement email;

    @FindBy(name = "Number")
    public WebElement annualSalary;

    @FindBy(name = "Dropdown")
    public WebElement education;

    @FindBy(id = "Checkbox_1")
    public WebElement javaOCACert;

    @FindBy(id = "Checkbox_2")
    public WebElement awsCert;

    @FindBy(id = "Checkbox_3")
    public WebElement scrumMasterCert;

    @FindBy(name = "MultiLine")
    public WebElement additionalSkills;

    @FindBy(xpath = "//em[.='Submit']")
    public WebElement submitBttn;


    public void selectGender(String gender) {
        if(gender.toLowerCase().equals("male")) {
            if(! male.isSelected()) {
                male.click();
            }
        }else if(gender.toLowerCase().equals("female")) {
            if(! female.isSelected()) {
                female.click();
            }
        }
    }
}
