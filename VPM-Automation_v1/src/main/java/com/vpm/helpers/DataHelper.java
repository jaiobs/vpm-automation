package com.vpm.helpers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.ldap.Control;

import org.apache.poi.hssf.eventusermodel.HSSFEventFactory;
import org.apache.poi.hssf.eventusermodel.HSSFListener;
import org.apache.poi.hssf.eventusermodel.HSSFRequest;
import org.apache.poi.hssf.record.CommonObjectDataSubRecord;
import org.apache.poi.hssf.record.ObjRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.SubRecord;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.vpm.pageObjects.GlobalVariables;

public class DataHelper {
	XSSFWorkbook workbook = null;

	public XSSFWorkbook getWorkBook(String fileName) {

		FileInputStream fileInputStream;
		try {

			fileInputStream = new FileInputStream(fileName);
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

	public List<HashMap<String, String>> getWorkSheet(String sheetName) {
		List<HashMap<String, String>> mydata = new ArrayList<>();
		XSSFWorkbook workbook = getWorkBook(sheetName);
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
					currentHash.put(HeaderRow.getCell(j).getStringCellValue(),
							String.valueOf(currentCell.getNumericCellValue()));
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

	public Set<String> getExecutor(String fileName) {			
		Set<String> set = new HashSet<String>(); 
		XSSFWorkbook workbook = getWorkBook(fileName);
		XSSFSheet sheet = workbook.getSheet("Executor");
		CellReference cellReference = new CellReference("D2"); 
		String server = sheet.getRow(1).getCell(cellReference.getCol()).getStringCellValue().trim();
		if(server.contains(",")) {
			GlobalVariables.server.add(server.split(",")[0].trim());
			GlobalVariables.server.add(server.split(",")[1].trim());
		} else {
			GlobalVariables.server.add(server);
		}
		CellReference cellReference1 = new CellReference("G2"); 
		String browser1 = sheet.getRow(1).getCell(cellReference1.getCol()).getStringCellValue().trim();
		GlobalVariables.Browser1 = browser1;
		CellReference cellReference2 = new CellReference("G4"); 
		String browser2 = sheet.getRow(3).getCell(cellReference2.getCol()).getStringCellValue().trim();
		GlobalVariables.Browser2 = browser2;
		XSSFRow HeaderRow = sheet.getRow(0);
		for (int i = 2; i < sheet.getPhysicalNumberOfRows(); i++) {
			XSSFRow currentRow = sheet.getRow(i);			
			for (int j = 0; j < currentRow.getPhysicalNumberOfCells(); j++) {
				XSSFCell currentCell = currentRow.getCell(j);			
				System.out.println(HeaderRow.getCell(j));
				if(HeaderRow.getCell(j)!= null) {
				if(HeaderRow.getCell(j).getStringCellValue().contains("Execute?")) {
					if(currentRow.getCell(j)!= null) {
						//j=j+1;
						if(currentRow.getCell(j).getBooleanCellValue()) {
							String cellText = currentRow.getCell(j-1).getStringCellValue().trim();
							if(cellText.contains(",")) {
								String[] cellTexts = cellText.split(",");
								for(int a=0; a<cellTexts.length; a++) {
									set.add(cellTexts[a].trim());
								}
							} else {
								set.add(cellText);
							}
						
						}
					}
					//if(currentRow.getCell(j))
				}
				}
			}
		//	mydata.add(currentHash);
		}		
		return set;
	}

}
	