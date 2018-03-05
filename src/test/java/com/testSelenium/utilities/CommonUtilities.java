package com.testSelenium.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import com.testSelenium.pages.PageBase;

public class CommonUtilities extends PageBase{

  public HashMap<String, String> testDataMap = new HashMap<String, String>();

  public CommonUtilities() {
  }

  @SuppressWarnings("resource")
  public HashMap<String, String> loadData() {     

    try {
      FileInputStream fileInputStream = new FileInputStream("TestData.xls");
      HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
      HSSFSheet worksheet = workbook.getSheet("Sheet1");
      int totalNoOfRows = worksheet.getPhysicalNumberOfRows();
      for (int rowCount = 1; rowCount < totalNoOfRows ; rowCount++) {
        HSSFRow row = worksheet.getRow(rowCount);
        HSSFCell cellKeyword = row.getCell((short) 0);
        HSSFCell cellvalue = row.getCell((short) 1);
        testDataMap.put(cellKeyword.getStringCellValue(),cellvalue.getStringCellValue());
      }


    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return testDataMap;
  }

  public Date dateConverter1(String dateInString) throws Exception {
    SimpleDateFormat formatter = new SimpleDateFormat("MMMM dd, yyyy hh:mm");
    Date date = formatter.parse(dateInString);
    return date;
  }
  
  public Date dateConverter2(String dateInString) throws Exception {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date date = formatter.parse(dateInString);
    return date;
  }
}