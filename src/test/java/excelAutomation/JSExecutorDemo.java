package excelAutomation;

import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import utilities.Driver;

public class JSExecutorDemo {

    WebDriver driver = Driver.getDriver();
    @Test
    public void test(){
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        // the command creates an alert on the page
//        jse.executeScript("alert('You are doing sth dangerous');");

        // it will click on the employeesFormPage.male
        // first argument is js command, second argument is web element on which the command will be executed
//
//        jse.executeScript(
//                "arguments[0].click();",
//                employeesFormPage.male);

//        String name = "Kunkka";
//        jse.executeScript(
//                "arguments[0].setAttribute('value', '" + name + "')",
//                employeesFormPage.firstName);

        // scroll down on a page
        jse.executeScript("window.scrollBy(0,1000)");

        // putting so that driver does not close quickly
    }
}
