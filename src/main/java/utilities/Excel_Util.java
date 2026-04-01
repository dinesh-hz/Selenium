package utilities;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Excel_Util {

	// this is try to get data in hole excell sheet value we can take wherever and
	// use it

	public static String getCellDataByColumnName(int rowNum, String columnName) throws Exception {
		FileInputStream fis = new FileInputStream("./src/test/resources/Excelldata/External_data_from_excell.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sheet = wb.getSheetAt(0);

		Row headerRow = sheet.getRow(0);// first row with column names
		Map<String, Integer> colIndexMap = new HashMap<>();

		for (int col = 0; col < headerRow.getPhysicalNumberOfCells(); col++) {
			colIndexMap.put(headerRow.getCell(col).getStringCellValue().trim(), col);
		}

		int targetCol = colIndexMap.get(columnName);
		Row targetRow = sheet.getRow(rowNum);
		Cell cell = targetRow.getCell(targetCol);

		wb.close();
		return cell.toString();
	}

	// it can use take data wherever it
	public static void main(String[] args) throws Exception {
// this main method for cheking purpose only 
		String paswword = getCellDataByColumnName(4, "password");
		String username = getCellDataByColumnName(2, "username");

		System.out.println(username);

	}

}
