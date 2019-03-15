package step_definitions.configuration;

import cucumber.api.java.en.Then;
import excelAutomation.ExcelUtil;
import jdbc.DBType;
import jdbc.DBUtility;
import org.junit.Assert;
import org.openqa.selenium.ElementNotVisibleException;
import utilities.Pages;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Map.*;


public class AlertsCreate_StepDefinition {


    Pages pages = new Pages();
    CreateAlerts_StepDefinition cas = new CreateAlerts_StepDefinition();

    @Then("enter between {string} and end {string}")
    public void enter_between_and_end(String between, String end) {

        pages.createAlertPage().between.clear();
        pages.createAlertPage().between.sendKeys(between);
        pages.createAlertPage().and.clear();
        pages.createAlertPage().and.sendKeys(end);
    }

    @Then("enter a date {string}")
    public void enter_a_date(String date) {
        try {
            pages.createAlertPage().day.clear();
            pages.createAlertPage().day.sendKeys(date);
        } catch (ElementNotVisibleException e) {

        }

    }

    @Then("enter a message {string}")
    public void enter_a_message(String message) {
        pages.createAlertPage().message.sendKeys(message);
    }

//    @Then("verify information exist in database {string}")
////    public void verify_information_exist_in_database(String message) throws SQLException {
////        DBUtility.establishConnection(DBType.POSTGRESQL);
////        List<Map<String, Object>> list = DBUtility.runSQLQuery("Select * from public.lunch_alert where message = '" + message + "'");
////        System.out.println(list);
////
////        for (int i = 0; i < list.size(); i++) {
////            Map<String, Object> map = list.get(i);
////            System.out.println(i);
////            for (Map.Entry<String, Object> entry : map.entrySet()) {
////                System.out.println(entry);
////                if (entry.getKey().equalsIgnoreCase(message)) {
////                    Assert.assertTrue(true);
////                }
////            }
////
////        }
////        Assert.assertTrue(false);
////
////    }


    @Then("verify information exist in database {string}")
    public void verify_information_exist_in_database(String message) throws SQLException {
        DBUtility.establishConnection(DBType.POSTGRESQL);
        List<Map<String, String>> list = DBUtility.runSQLQueryStr("Select * from public.lunch_alert where message = '" + message + "'");

        boolean passed = false;
        for (int i = 0; i < list.size(); i++) {
            Map<String, String> map = list.get(i);
            System.out.println(i);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                System.out.println(entry);
                if (entry.getValue().equalsIgnoreCase(message)) {
                    passed = true;
                    break;
                }
            }

        }
        Assert.assertTrue(passed);
        DBUtility.closeConnection();
    }

    //************************************************************************************************************************************************\\


    private static int counter = 0;

    @Then("enter required spaces from excel")
    public void enter_required_spaces_from_excel(List<String> dataTable) {
        ExcelUtil ex = new ExcelUtil("src\\test\\resources\\Alerts Monday Project.xlsx", "Sheet1");

        List<Map<String, String>> list = ex.getDataList();

        for (int i = counter; i < list.size(); i++) {
            Map<String, String> map = list.get(i);

            for (int j = 0; j < dataTable.size(); j++) {
                String result = dataTable.get(j);
                switch (result) {
                    case "Recurrence":
                        cas.select_recurrence(map.get(result));
                        break;
                    case "between":
                        enter_between_and_end(map.get(result), map.get(dataTable.get(j + 1)));
                        break;
                    case "message":
                        enter_a_message(map.get(dataTable.get(j)));
                        break;
                }

            }

            break;
        }


    }


    @Then("enter required spaces from excela")
    public void enter_required_spaces_from_excela() throws SQLException {

        ExcelUtil ex = new ExcelUtil("src\\test\\resources\\Alerts Monday Project.xlsx", "Sheet1");
        List<Map<String, String>> list = ex.getDataList();

        for (int i = 0; i < list.size(); i++) {
            Map<String, String> map = list.get(i);

            for (Entry<String, String> entry : map.entrySet()) {
                String result = entry.getKey();
                switch (entry.getKey()) {

                    case "recurrence":
                        cas.select_recurrence(map.get("recurrence"));
                        break;
                    case "between":

                        pages.createAlertPage().between.clear();
                        pages.createAlertPage().between.sendKeys(map.get("between"));

                        break;
                    case "end":
                        pages.createAlertPage().and.clear();
                        pages.createAlertPage().and.sendKeys(map.get("end"));
                        break;


                    case "message":
                        enter_a_message(map.get("message"));
                        break;
                }

            }
            if (i == list.size() - 1) {
                pages.lunchPage().goToFunctionality("alerts");
                pages.createAlertPage().warningOk.click();
                break;
            }

            cas.click_save();
            pages.lunchPage().goToFunctionality("alerts");
            pages.alertsPage().create.click();
            verify_information_exist_in_database(map.get("message"));

        }

    }

}
