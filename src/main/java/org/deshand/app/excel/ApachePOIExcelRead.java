package org.deshand.app.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.deshand.model.CentralWareHouse;
import org.deshand.repo.CentralWareHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApachePOIExcelRead {

	private final String FILE_NAME = "d:/Siemens/CentralWarehouse.xlsx";
	// private final String FILE_NAME = "d:/CentralWarehouse.xlsx";

	@Autowired
	CentralWareHouseRepository repository;

	@SuppressWarnings("deprecation")
	public void readExcel() {

		try {

			FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();

			while (iterator.hasNext()) {

				Row currentRow = iterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();

				while (cellIterator.hasNext()) {

					Cell currentCell = cellIterator.next();
					// getCellTypeEnum shown as deprecated for version 3.15
					// getCellTypeEnum will be renamed to getCellType starting from version 4.0
					if (currentCell.getCellTypeEnum() == CellType.STRING) {
						System.out.print(currentCell.getStringCellValue() + "--");
					} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
						System.out.print(currentCell.getNumericCellValue() + "--");
					} else if (currentCell.getCellTypeEnum() == CellType.BLANK) {
						System.out.print(". . ." + "--");
					}

				}
				System.out.println();

			}
			workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void readExcel2() {
		try {
			FileInputStream file = new FileInputStream(new File(FILE_NAME));

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);
			
//			System.out.println(sheet.getLastRowNum());

			// I've Header and I'm ignoring header for that I've +1 in loop

			for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
				CentralWareHouse cVH = new CentralWareHouse();
				Row ro = sheet.getRow(i);
				for (int j = ro.getFirstCellNum(); j <= ro.getLastCellNum(); j++) {
					Cell ce = ro.getCell(j);
					if (j == 0) {
						cVH.setShelfName(getStringValue(ce));
					}
					if (j == 1) {
						cVH.setValueMetal(getStringValue(ce));
					}
					if (j == 2) {
						cVH.setPartDescription(getStringValue(ce));
					}
					if (j == 3) {
						cVH.setPartNumber(getStringValue(ce));
					}
					if (j == 4) {
						cVH.setwHNumber(getStringValue(ce));
					}
					if (j == 5) {
						cVH.setQuantity(getStringValue(ce));
					}
					if (j == 6) {
						cVH.setbKQuantity(getStringValue(ce));
					}
					if (j == 7) {
						cVH.setMissingQuantity(getStringValue(ce));
					}
					if (j == 8) {
						cVH.setPlaceOfInstallation(getStringValue(ce));
					}
				}
				repository.save(cVH);
			}

			file.close();
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	private String getStringValue(Cell ce) {
		if (ce.getCellTypeEnum() == CellType.STRING) {
			return ce.getStringCellValue();
		} else if (ce.getCellTypeEnum() == CellType.NUMERIC) {
			Double d = ce.getNumericCellValue();
			Integer i = d.intValue();
			return i.toString();
		} else if (ce.getCellTypeEnum() == CellType.BLANK | ce.getCellTypeEnum() == null) {
			return ". . .";
		}
		return "no data";
	}
}
