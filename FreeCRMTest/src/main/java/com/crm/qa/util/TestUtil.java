
package com.crm.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.crm.qa.base.TestBase;

public class TestUtil extends TestBase{

	public static long PAGE_LOAD_TIMEOUT=40;
	public static long IMPLICIT_WAIT=10;
	
    public static String TESTDATA_SHEET_PATH="C:\\Users\\dhsa7001\\eclipse-workspace\\FreeCRMTest\\src\\main\\java\\com\\crm\\qa\\testdata\\FreeCrmTestdata.xlsx";
	
    static XSSFWorkbook wb;
	static XSSFSheet sheet;
	
	
	public void switchToFrame() {
		
		driver.switchTo().frame("mainpanel");
		
	}
	
	
	
		public static Object[][] getTestData(String sheetName) throws Exception	
		{
		File src=new File(TESTDATA_SHEET_PATH);	
		FileInputStream fis=new FileInputStream(src);
		
	    wb=new XSSFWorkbook(fis);
		sheet=wb.getSheet(sheetName);
		int rowcount=sheet.getLastRowNum();
		int columncount=sheet.getRow(0).getLastCellNum();
		Object[][] data=new Object[rowcount][columncount];
		
		//System.out.println("Total row count is "+rowcount);
		for(int i=0;i<rowcount;i++)
		{
			for(int j=0;j<columncount;j++)
			{
		 data[i][j]=sheet.getRow(i+1).getCell(j).getStringCellValue();
			
			}
		}
		return data;
		}
		
		public static void takeScreenshotAtEndOfTest() throws IOException {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String currentDir = System.getProperty("user.dir");
			FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
		}
}
