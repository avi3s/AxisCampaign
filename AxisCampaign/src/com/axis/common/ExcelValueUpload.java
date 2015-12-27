/*package com.axis.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.multipart.MultipartFile;

import com.axis.model.TargetModel;

public class ExcelValueUpload {

	public static List getExcelValues(MultipartFile file)
			throws InvalidFormatException, IOException {

		List wholeExcelList = new ArrayList<String>();

		InputStream inputStream = null;
		// System.out.println("file size===" + file.getSize());
		String file_size = "";

		if (file.getSize() == 0) // File Of No DATA
		{
			file_size = "File Size is Zero";
		}

		else {
			String fileName = file.getOriginalFilename();

			List<TargetModel> targetModels = new ArrayList<TargetModel>();

			inputStream = file.getInputStream();

			// XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory
					.create(inputStream);
			// System.out.println("fileName :" + fileName);
			org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);
			// XSSFSheet sheet = workbook.getSheetAt(0);
			
			 * System.out.println("Number Of Rows :: " +
			 * sheet.getPhysicalNumberOfRows() + " ** " +
			 * sheet.getLastRowNum());
			 int count = 0;

			Iterator rowIterator = sheet.rowIterator();
			while (rowIterator.hasNext()) // Getting a Whole Row
			{
				List excelList = new ArrayList<>();
				count++;
				// System.out.println("count :: " + count);
				int num_of_columns = 0;
				HSSFRow row = (HSSFRow) rowIterator.next();
				int num_of_cols = row.getLastCellNum();
				// System.out.println("Number Of Columns :: " + num_of_cols);
				int i = 0;

				// For each row, iterate through each columns
				Iterator cellIterator = row.cellIterator();
				while (i < num_of_cols) // Getting the value of each cell
				{
					HSSFCell cell = row.getCell(i);

					i++;
					// XSSFCell cell = (XSSFCell)cellIterator.next();
					DataFormatter df = new DataFormatter();
					String value = df.formatCellValue(cell);
					// System.out.println("Value ==== " + value);
					excelList.add(value);

				}
				wholeExcelList.add(excelList);
			}

		}
		return wholeExcelList;

	}

}
*/