package Base;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {
    File file;
    FileInputStream fis;
    XSSFWorkbook wb;
    XSSFSheet sheet;
    XSSFRow row;
    XSSFCell cell;

    public ExcelReader(String filePath) throws IOException {
        file = new File(filePath);
        fis = new FileInputStream(file);
        wb = new XSSFWorkbook(fis);
    }

    public String getStringData(String sheetName, int rowNumber, int cellNumber) {
        sheet = wb.getSheet(sheetName);
        if (sheet == null) {
            throw new IllegalArgumentException("Sheet '" + sheetName + "' does not exist.");
        }

        row = sheet.getRow(rowNumber);
        if (row == null) {
            return "";
        }

        cell = row.getCell(cellNumber);
        if (cell == null) {
            return "";
        }

        return cell.toString();
    }

    public int getIntegerData(String sheetName, int rowNumber, int cellNumber) {
        sheet = wb.getSheet(sheetName);
        row = sheet.getRow(rowNumber);
        cell = row.getCell(cellNumber);
        return (int) cell.getNumericCellValue();
    }

    public int getLastRow(String sheetName) {
        sheet = wb.getSheet(sheetName);
        return sheet.getLastRowNum();
    }
}
