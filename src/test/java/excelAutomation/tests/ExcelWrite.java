package excelAutomation.tests;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ExcelWrite {


    public static void main(String[] args) throws Exception {
        XSSFWorkbook book;
        XSSFSheet sheet;
        XSSFRow row;
        XSSFCell cell;

        String path = "src/SampleData.xlsx";
        File file = new File(path);

        System.out.println(file.exists());


        FileInputStream inputStream = new FileInputStream(file);

        book = new XSSFWorkbook(inputStream);
        sheet = book.getSheet("Sheet1");
        row = sheet.getRow(4);
        cell = row.getCell(0);

        System.out.println(cell.toString());

        System.out.println(sheet.getRow(6).getCell(0));

        XSSFCell adamcell = sheet.getRow(6).getCell(0);
        adamcell.setCellValue("Not Happy");

        System.out.println(sheet.getRow(6).getCell(0));
        FileOutputStream outputStream = new FileOutputStream(path);
          book.write(outputStream);


//        for (int i = sheet.getLastRowNum(); i < 100; i++) {
//            try {
//                XSSFCell celll = sheet.getRow(i).getCell(0);
//                if (row == null) {
//                    celll = row.createCell(i + 1);
//                }
//                celll.setCellValue(i + 1 + "come on");
//                System.out.println(sheet.getRow(i).getCell(0));
//            } catch (Exception e) {
//                book.write(outputStream);
//                inputStream.close();
//                outputStream.close();
//                book.close();
//
//            }
//        }
        book.write(outputStream);
        inputStream.close();
        outputStream.close();
        book.close();



    }
}
