package excelAutomation.tests;

import com.github.javafaker.Faker;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ChangeTheValue {

    public static void main(String[] args) throws Exception {

        XSSFWorkbook workbook;
        XSSFSheet sheet;
        XSSFRow row;
        XSSFCell cell;


        String path = "src/SampleData.xlsx";
        File file = new File(path);
        System.out.println(file.exists());

        FileInputStream inputStream = new FileInputStream(file);

        workbook = new XSSFWorkbook(inputStream);
        sheet = workbook.getSheet("Sheet2");

        FileOutputStream outputStream = new FileOutputStream(path);

        System.out.println(sheet.getRow(0).getCell(0));
        System.out.println(sheet.getRow(0).getCell(1));
        System.out.println(sheet.getRow(0).getCell(2));

        Faker faker = new Faker();

        System.out.println(sheet.getLastRowNum());

        for (int i = 1; i < 100; i++) {
            String name = faker.name().fullName();
            String address = faker.address().fullAddress();
            String country = faker.address().country();
            sheet.getRow(i).getCell(0).setCellValue(name);
            sheet.getRow(i).getCell(1).setCellValue(address);
            sheet.getRow(i).getCell(2).setCellValue(country);
        }

        workbook.write(outputStream);
        inputStream.close();
        outputStream.close();
        workbook.close();

    }
}
