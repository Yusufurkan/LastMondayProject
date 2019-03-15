package excelAutomation.tests;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

public class ExcelRead {

    public static void main(String[] args) throws Exception {

        String str ="src/SampleData.xlsx";
        File file = new File(str);

        System.out.println(file.exists());


        FileInputStream inputStream = new FileInputStream(file);

        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

        XSSFSheet worksheet = workbook.getSheet("Employees");


        System.out.println(worksheet.getRow(2).getCell(1));

        int usedRows = worksheet.getPhysicalNumberOfRows();

        int lastUsedRows = worksheet.getLastRowNum();
        System.out.println(usedRows);
        System.out.println(lastUsedRows);


        for (int i = 0 ; i <= usedRows ; i++){
            if (worksheet.getRow(i).getCell(0).toString().equalsIgnoreCase("Kristian")){
                System.out.println(worksheet.getRow(i).getCell(2));
                break;
            }

        }
        workbook.close();
        inputStream.close();

    }
}
