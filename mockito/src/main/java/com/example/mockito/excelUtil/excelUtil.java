package com.example.mockito.excelUtil;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

@Component
public class excelUtil {
    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    public static void writeExcel(String filePath, HashMap<Integer, Long> map) throws IOException {
        File file = new File(filePath);
        Workbook workbook = getWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);
        Row row = sheet.createRow(sheet.getLastRowNum()+1);
        for (Integer i:map.keySet()){
            Cell cell = row.createCell(i);
            cell.setCellValue(map.get(i));
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        workbook.write(fileOutputStream);
    }

    public static Workbook getWorkbook(File file) throws IOException{
        Workbook wb = null;
        FileInputStream in = new FileInputStream(file);
        if(file.getName().endsWith(EXCEL_XLS)){     //Excel&nbsp;2003
            wb = new HSSFWorkbook(in);
        }else if(file.getName().endsWith(EXCEL_XLSX)){    // Excel 2007/2010
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }
}
