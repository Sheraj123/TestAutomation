/**
 * 
 */
package popcornWorkflows;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * @author Sheraj
 *
 */
public class LoginPage {
	
	public String baseURL = "http://qa.cms.popcorn.lk";
	public WebDriver driver;
	WebDriverWait wait;
	HSSFWorkbook workbook;
	HSSFSheet sheet;
	HSSFCell cell;

	
	@BeforeTest
	public void SetBaseURL() {
			
		System.setProperty("webdriver.firefox.marionette","C:\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get(baseURL);
		driver.manage().window().maximize();	
		wait = new WebDriverWait(driver,30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
	
	
	 @Test
	 public void VerifyValidLogin() throws IOException
	 {
		 // Import excel sheet.
		 File src=new File("C:\\Users\\Sheraj\\eclipse-workspace\\zMessengerAutomation\\TestData.xls");
		 
		 // Load the file.
		 FileInputStream finput = new FileInputStream(src);
		 
		 // Load the workbook.
		workbook = new HSSFWorkbook(finput);
		 
	     // Load the sheet in which data is stored.
		 sheet= workbook.getSheetAt(0);
		 
		 for(int i=1; i<=sheet.getLastRowNum(); i++)
		 {
			 // Import data for username.
			 cell = sheet.getRow(i).getCell(1);
			 cell.setCellType(Cell.CELL_TYPE_STRING);
			 driver.findElement(By.xpath("//input[contains(@id,'username')]")).sendKeys(cell.getStringCellValue());
			 
			 // Import data for password.
			 cell = sheet.getRow(i).getCell(2);
			 cell.setCellType(Cell.CELL_TYPE_STRING);
			 driver.findElement(By.xpath("//input[contains(@id,'password')]")).sendKeys(cell.getStringCellValue());
			 driver.findElement(By.xpath("//button")).click();
			 
			 //Verify Title
				Assert.assertTrue(driver.findElement(By.xpath("//a/span/img")).isDisplayed());
			   		
	        }
	  } 
	
	@AfterTest
	public void EndSession() {
		driver.quit();
		
	}

}
