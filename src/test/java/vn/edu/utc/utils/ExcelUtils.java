package vn.edu.utc.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.params.provider.Arguments;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ExcelUtils {
    
    // Hàm tĩnh dùng chung để đọc file Excel cung cấp cho các Test Case
    public static Stream<Arguments> getLoginData() throws Exception {
        List<Arguments> records = new ArrayList<>();
        String filePath = "src/test/resources/test-data/TestCase_Login_UTC.xlsx";
        
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {
            
            Sheet sheet = workbook.getSheetAt(0); 
            
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                String username = getCellValue(row.getCell(0));
                String password = getCellValue(row.getCell(1));
                String expectedMessage = getCellValue(row.getCell(2));

                records.add(Arguments.of(username, password, expectedMessage));
            }
        }
        return records.stream();
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) return "";
        if (cell.getCellType() == CellType.STRING) return cell.getStringCellValue();
        if (cell.getCellType() == CellType.NUMERIC) return String.valueOf((int)cell.getNumericCellValue());
        return "";
    }
}
