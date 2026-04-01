package Dataprovider_for_excell_source;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelDataProvider {

    @DataProvider(name = "excelData")
    public Object[][] getExcelData() throws IOException, InvalidFormatException {

        File file = new File("./src/test/resources/Excelldata/logindata_for_dataproider.xlsx");

        XSSFWorkbook workbook = new XSSFWorkbook(file);

        XSSFSheet sheet = workbook.getSheetAt(0);

        int noOfrow = sheet.getPhysicalNumberOfRows();

        int noOfcolum = sheet.getRow(0).getPhysicalNumberOfCells();

        Object[][] data = new Object[noOfrow - 1][noOfcolum]; // skip header row

        for (int i = 0; i < noOfrow - 1; i++) // start from 1 to skip header

            for (int j = 0; j < noOfcolum; j++) {

                data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
            }
        workbook.close();
        return data;
    }
}




