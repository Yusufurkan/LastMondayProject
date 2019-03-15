package excelAutomation;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import utilities.ConfigurationReader;
import utilities.Driver;

import java.util.List;
import java.util.Map;

public class DataDrivenTesting {
    WebDriver driver = Driver.getDriver();
    List<Map<String, String>> testData;

    public void getTestData() {
        ExcelUtil excelUtil = new ExcelUtil("./src/test/resources/EmployeesTestData.xlsx", "data");
        testData = excelUtil.getDataList();
    }


    // TODO this part is copied from testng framework after method condition needed

    @Test
    public void employeesFormTest() {
        getTestData();
        Driver.getDriver().get(ConfigurationReader.getProperty("employees.app.url"));


        for (Map<String, String> empData : testData) {

            EmployeesPageForm empPage = new EmployeesPageForm();
            empPage.firstName.sendKeys(empData.get("first_name"));
            empPage.lastName.sendKeys(empData.get("last_name"));
            empPage.role.sendKeys(empData.get("Role"));
            empPage.selectGender(empData.get("gender"));
            empPage.email.sendKeys(empData.get("email"));
            empPage.annualSalary.sendKeys(empData.get("Salary").replace(".0", ""));
            new Select(empPage.education).selectByVisibleText(empData.get("Education"));
            empPage.javaOCACert.click();
            empPage.additionalSkills.sendKeys(empData.get("Skills"));
            empPage.submitBttn.click();

            ConfirmationPage cPage = new ConfirmationPage();

            Assert.assertEquals(empData.get("first_name") + " " + empData.get("last_name"),
                    cPage.getFullName());

            Driver.getDriver().get(ConfigurationReader.getProperty("employees.app.url"));

            Driver.getDriver().navigate().back();

        }


//    @Test
//    public void employeesFormTest() {
//
//        for (Map<String, String> empData : testData) {
//
//            EmployeesPageForm emp = new EmployeesPageForm();
//            Faker faker = new Faker();
//
//            emp.firstName.sendKeys(faker.name().firstName());
//            emp.lastName.sendKeys(faker.name().lastName());
//            emp.lastName.sendKeys(faker.name().lastName());
//            emp.role.sendKeys(faker.job().position());
//            emp.selectGender("male");
//            emp.email.sendKeys("safok@tempcloud.info");
//            emp.annualSalary.sendKeys(faker.number().digits(5));
//            new Select(emp.education).selectByVisibleText("High School");
//
//            emp.javaOCACert.click();
//            emp.additionalSkills.sendKeys("I know a lot");
//            emp.submitBttn.click();
//
//
//            ConfirmationPage cp = new ConfirmationPage();
//            System.out.println(cp.getFullName());
//
//            //  Assert.assertEquals(cp.dear.getText(),cp.getFullName() );
//
//        }
    }
}
