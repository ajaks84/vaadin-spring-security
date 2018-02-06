package org.deshand.excel;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.deshand.model.CentralWareHouse;
import org.deshand.repo.CentralWareHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApachePOIExcelRead {

	@Autowired
	CentralWareHouseRepository repository;

	public void processExcelFile(String fileName) {
		
		repository.deleteAll();

		try {
			FileInputStream file = new FileInputStream(new File(fileName));

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			System.out.println(sheet.getLastRowNum());

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
