package excelAutomation;


import pages.DatabasePage;
import utilities.Driver;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExelPractice {
    WebDriver driver = Driver.getDriver();
    List<Map<String, String>> mapData;
    DatabasePage dpage;


    // TODO fix the synchronization issue many time name had numbers selenium sending keys to
    // TODO irrevelant inputs
    @Test
    public synchronized void test() throws Exception {

        ExcelUtil excel = new ExcelUtil("./src/test/resources/TestData.xlsx", "data");

        File file = new File("./src/test/resources/TestData.xlsx");
        System.out.println(file.exists());

        mapData = table(new FileInputStream(file));

        driver.get("https://editor.datatables.net/");
        dpage = new DatabasePage();

        for (Map<String, String> map : mapData) {

            dpage.newButton.click();
            dpage.firstname.sendKeys(map.get("firstName"));
            dpage.lastname.sendKeys(map.get("lastName"));
            dpage.position.sendKeys(map.get("position"));
            dpage.office.sendKeys(map.get("office"));
            dpage.extention.sendKeys(map.get("extension"));
            dpage.date.sendKeys(map.get("date"));
            dpage.salary.sendKeys(map.get("salary"));

            dpage.createButton.click();

            waitUrl();
//            dpage.search.sendKeys(map.get("firstName"));
            // waitSearch();

            System.out.println(map.get("firstName"));
            System.out.println(map.get("date"));


//            Assert.assertEquals(dpage.verifyName.getText() + " " + dpage.lastname.getText(), map.get("firstName") + " " + map.get("lastName"));
//            Assert.assertEquals(dpage.verifyPosition.getText(), map.get("position"));
//            Assert.assertEquals(dpage.verifyOffice.getText(), map.get("office"));
//            Assert.assertEquals(dpage.verifyDate.getText(), map.get("date"));
//            Assert.assertEquals(dpage.verifySalary.getText(), map.get("salary"));


        }

    }


    public void waitUrl() {
        String url = Driver.getDriver().getCurrentUrl();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 5);
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='DTE_Body']"))));
    }

    public void waitSearch() {
        String url = Driver.getDriver().getCurrentUrl();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 5);
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//a[@class='paginate_button next']"))));
    }


    public List<Map<String, String>> table(FileInputStream exelFile) throws IOException {
        List<Map<String, String>> table = new ArrayList<>();

        Workbook book = new XSSFWorkbook(exelFile);

        Sheet sheet = book.getSheet("data");
        Row row;
        Cell cell;


        List<String> columns = new ArrayList<>();

        for (Cell cel : sheet.getRow(0)) {
            columns.add(cel.toString());
        }


        for (int i = 1; i < sheet.getLastRowNum(); i++) {

            Map<String, String> map = new HashMap<>();

            row = sheet.getRow(i);

            for (int j = 0; j < columns.size(); j++) {
                map.put(columns.get(j), row.getCell(j).toString());
            }
            table.add(map);
        }


        exelFile.close();

        return table;
    }

}
