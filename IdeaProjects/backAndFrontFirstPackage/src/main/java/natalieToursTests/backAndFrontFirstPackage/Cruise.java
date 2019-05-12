package natalieToursTests.backAndFrontFirstPackage;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.fail;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import funcsThatHelp.FromBackToFront;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class Cruise
{
	WebDriver driver = new ChromeDriver();
	
	@Before
	public void settings (){
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(24, TimeUnit.SECONDS);
		switch (Initializer.portal.getSelectedIndex()){
		case 0:
			driver.get("http://nttestmoscow.natecnia.com/back/login.svt");
			break;
		case 1:
			driver.get("http://preproductiondevelopment.natecnia.com/back/login.svt");
			break;
		case 2:
			driver.get("https://global.natalie-tours.ru/back/");
			break;
		}
        driver.manage().window().maximize();
	}
	
	@Test
	public void bookIssuanceCancel() throws Exception {
		try{
			WebDriverWait wait = new WebDriverWait(driver, 44 , 333);
        FromBackToFront.main(driver,wait);// opens Front Office in a new window

        ArrayList<String> windows= new ArrayList<String>();//array for window handles
        windows.addAll(driver.getWindowHandles());
        driver.switchTo().window(windows.get(1)); //switching driver to newly opened window

        Thread.sleep(2000);//!!!!!!!!!!!
        wait.until(ExpectedConditions.elementToBeClickable(By.id("menuCRU"))); //magic random problems
        WebElement problemButton0 = driver.findElement(By.id("menuCRU"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", problemButton0);
        
// field for needed info appears

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("CRU_SEARCHER")));
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Введите место вылета']")));
        ArrayList <WebElement> inputFields = new ArrayList <WebElement>();
        inputFields.addAll(driver.findElements(By.className("field")));
        ArrayList <WebElement> selInput = new ArrayList <WebElement>();
        selInput.addAll(driver.findElements(By.className("selinput")));
        inputFields.get(0).sendKeys(frame4Cruise.whenTo.getText());
        inputFields.get(1).sendKeys(frame4Cruise.whenBack.getText());
        selInput.get(0).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'"+frame4Cruise.region.getItemAt(frame4Cruise.region.getSelectedIndex())+"')]")));
        driver.findElement(By.xpath("//div[contains(text(),'"+frame4Cruise.region.getItemAt(frame4Cruise.region.getSelectedIndex())+"')]")).click();
        inputFields.get(2).sendKeys(frame4Cruise.ship.getText());
        selInput.get(1).sendKeys(frame4Cruise.roomQuantity.getText());
        selInput.get(2).sendKeys(frame4Cruise.adultQuantity.getText());
        selInput.get(3).sendKeys(frame4Cruise.childQuantity.getText());
        switch (Integer.parseInt(frame4Cruise.childQuantity.getText())){
        case 1:
        	selInput.get(3).sendKeys(Keys.TAB,frame4Cruise.childAge1.getText());
        	break;
        case 2:
        	selInput.get(3).sendKeys(Keys.TAB,frame4Cruise.childAge1.getText());
        	selInput.get(3).sendKeys(Keys.TAB,Keys.TAB,frame4Cruise.childAge2.getText());
        	break;
        case 3:
        	selInput.get(3).sendKeys(Keys.TAB,frame4Cruise.childAge1.getText());
        	selInput.get(3).sendKeys(Keys.TAB,Keys.TAB,frame4Cruise.childAge2.getText());
        	selInput.get(3).sendKeys(Keys.TAB,Keys.TAB,Keys.TAB,frame4Cruise.childAge3.getText());
        }
        driver.findElement(By.xpath("//span[contains(text(),'Поиск')]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Под запрос')]")));
        driver.findElement(By.xpath("//span[contains(text(),'Под запрос')]")).click();
    
        
        
        
        driver.switchTo().window(driver.getWindowHandle());
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("CRU_CREATE")));
        ArrayList <WebElement> fields = new ArrayList<WebElement>();
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@class='content3']")));
        Thread.sleep(1500);
        fields.addAll(driver.findElements(By.className("field")));
        int intAdultCount = Integer.parseInt(frame4Cruise.adultQuantity.getText());
        int intChildCount = Integer.parseInt(frame4Cruise.childQuantity.getText());
        for (int i = 0;i<intAdultCount;i++ ) {
        	for(int j=1;j<6;j++){
        		fields.get(j+6*i).sendKeys(AllInfo.getAdultInfo().get(i).get(j));
        	}
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyy");
        for(int i = intAdultCount;i< intAdultCount+intChildCount;i++){
        		fields.get(1+6*i).sendKeys(AllInfo.getChildInfo().get(i-intAdultCount).get(1));
        		fields.get(2+6*i).sendKeys(AllInfo.getChildInfo().get(i-intAdultCount).get(2));
        		int age = Integer.parseInt(frame4Cruise.getKinderAge().get(i-intAdultCount));
        		fields.get(3+6*i).sendKeys(dtf.format(LocalDate.now().minusYears(age).minusDays(1)));
        		fields.get(4+6*i).sendKeys(AllInfo.getChildInfo().get(i-intAdultCount).get(3));
        		fields.get(5+6*i).sendKeys(AllInfo.getChildInfo().get(i-intAdultCount).get(4));
        }
        
        driver.findElement(By.cssSelector("button[title='Забронировать']")).click();
        driver.switchTo().alert().accept();
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();

        driver.switchTo().window(driver.getWindowHandle());
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("COM_MSG")));
        String bookingNo = driver.findElement(By.cssSelector("h2")).getText();
        
        driver.switchTo().window(windows.get(0)); 
        WebElement operations = driver.findElement(By.xpath("//span[contains(text(),'Operations')]"));
        new Actions(driver).moveToElement(operations).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Booking management')]")));
        driver.findElement(By.xpath("//span[contains(text(),'Booking management')]")).click();
        
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("OPE_RES_LIST")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[class='field']")));
        driver.findElements(By.cssSelector("input[class='field']")).get(6).sendKeys(bookingNo);
        driver.findElement(By.xpath("//td[@id='tBFilter']/button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[title='Cancel booking']")));
        driver.findElements(By.cssSelector("button[title='Cancel booking']")).get(0).click();
        driver.switchTo().window(driver.getWindowHandle());
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("OPE_RES_CANCEL")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@id='tBCancel1']/button")));
        driver.findElement(By.xpath("//td[@id='tBCancel1']/button")).click();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.sendKeys("Book cancelation autotest");
        alert.accept();
        
        Thread.sleep(5000);
		}
		catch (Exception e){
			String screenshotsDir = "C:/Users/syavin.v/Desktop/failureScreenshots";
			Path screenshotPath = Paths.get(screenshotsDir, System.currentTimeMillis()+" CruiseFailureScreenshot.png");
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, screenshotPath.toFile());
			fail("Smth went wrong in Cruise bookCancel test : "+ e);
		}
        
	}
	
	
	
	@After
	public void afterTest(){
        driver.quit();
	}
	public static void main(String[] args){
		new Cruise();
	}


}
