package helpers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataHelper {

	public static XSSFWorkbook getWorkBook() {
		XSSFWorkbook workbook = null;
		FileInputStream fileInputStream;
		try {
			fileInputStream = new FileInputStream(
					System.getProperty("user.dir") + "//src//test//resources//testData//TestData.xlsx");
			workbook = new XSSFWorkbook(fileInputStream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return workbook;
	}

	public static List<HashMap<String, String>> getWorkSheet(String sheetName) {
		List<HashMap<String, String>> mydata = new ArrayList<>();
		XSSFWorkbook workbook = getWorkBook();
		XSSFSheet sheet = workbook.getSheet(sheetName);
		XSSFRow HeaderRow = sheet.getRow(0);
		for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
			XSSFRow currentRow = sheet.getRow(i);
			HashMap<String, String> currentHash = new HashMap<String, String>();
			for (int j = 0; j < currentRow.getPhysicalNumberOfCells(); j++) {
				XSSFCell currentCell = currentRow.getCell(j);
				System.out.println(HeaderRow.getCell(j));
				switch (currentCell.getCellType()) {
				case STRING:
					currentHash.put(HeaderRow.getCell(j).getStringCellValue(), currentCell.getStringCellValue());
					break;
				case NUMERIC:
					currentHash.put(HeaderRow.getCell(j).getStringCellValue(), String.valueOf(currentCell.getNumericCellValue()));
					break;
				case _NONE:
					currentHash.put(HeaderRow.getCell(j).getStringCellValue(), "");
					break;
				case BLANK:
					break;
				default:
					break;
				}
			}
			mydata.add(currentHash);
		}
		return mydata;
	}
}
