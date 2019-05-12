package natalieToursTests.backAndFrontFirstPackage;

import static org.junit.Assert.fail;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import funcsThatHelp.FromBackToFront;
import funcsThatHelp.FromFrontToBack;
import funcsThatHelp.FuncsThatHelp;

public class Hotel {

WebDriver driver = new ChromeDriver();
	
@Before
public void settings (){
	//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.manage().timeouts().pageLoadTimeout(24, TimeUnit.SECONDS);
	FuncsThatHelp.whereToGoTo(driver);
    driver.manage().window().maximize();
}
	
@Test
	public void bookIssuanceCancel() throws Exception {
		try{
			WebDriverWait smallWait = new WebDriverWait(driver, 3 , 333);
			WebDriverWait wait = new WebDriverWait(driver, 77 , 333);
	        FromBackToFront.main(driver,wait);
        
	        //FRONT OFFICE
	        ArrayList<String> windows= new ArrayList<String>();//array for window handles
	        windows.addAll(driver.getWindowHandles());
	        driver.switchTo().window(windows.get(1));
		    driver.manage().window().maximize();
		    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("imgProcessing")));
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menuHTLLabel"))).click();
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("imgProcessing")));
	        WebElement problemButton0 = driver.findElement(By.id("tabHTL02"));
	        JavascriptExecutor executor = (JavascriptExecutor)driver;
	        executor.executeScript("arguments[0].click();", problemButton0);
	        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("HTL_COMPARATOR"));
	        Thread.sleep(1000);
	        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("input[title='dd/mm/yyyy']"))).get(0).sendKeys(frame4Hotel.whenTo.getText());
	        driver.findElements(By.cssSelector("input[title='dd/mm/yyyy']")).get(1).sendKeys(frame4Hotel.whenBack.getText());
	        driver.findElement(By.xpath("//td[@id='tDestino']//input")).click();
	        driver.switchTo().window(driver.getWindowHandle());
	        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("COM_LOV_DST"));
	        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("fieldr"))).get(1).click();
	        driver.findElement(By.className("selinput")).click();
	        String prefferedCountry = StringUtils.capitalize(frame4Hotel.country.getText());
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'"+prefferedCountry+"')]"))).click();
	        driver.findElement(By.xpath("//td[@id='tProvincia']//table")).click();
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'"+StringUtils.capitalize(frame4Hotel.to.getText())+"')]"))).click();
	        driver.findElement(By.className("button")).click();
	        driver.switchTo().window(driver.getWindowHandle());
	        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("HTL_COMPARATOR"));
	
	        if (!frame4Hotel.hotel.getText().equals("")){
		        driver.findElement(By.xpath("//td[@id='tHotel']//input")).click();
		        driver.switchTo().window(driver.getWindowHandle());
		        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("COM_LOV_STD"));
		        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[@id='tSearcher']//input"))).sendKeys(frame4Hotel.hotel.getText().toUpperCase());
		        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'"+frame4Hotel.hotel.getText().toUpperCase()+"')]"))).click();
		        driver.switchTo().window(driver.getWindowHandle());
		        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("HTL_COMPARATOR"));
	        }
	        driver.findElements(By.className("selinput")).get(1).sendKeys(frame4Hotel.roomQuantity.getText());
	        driver.findElements(By.className("selinput")).get(2).sendKeys(frame4Hotel.childQuantity.getText());
	        driver.findElements(By.className("selinput")).get(1).sendKeys(Keys.TAB,frame4Hotel.adultQuantity.getText());
	        switch (Integer.parseInt(frame4Hotel.childQuantity.getText())){
	        case 1:
	        	driver.findElements(By.className("selinput")).get(3).sendKeys(frame4Hotel.childAge1.getText());
	        	break;
	        case 2:
	        	driver.findElements(By.className("selinput")).get(3).sendKeys(frame4Hotel.childAge1.getText());
	        	driver.findElements(By.className("selinput")).get(4).sendKeys(frame4Hotel.childAge2.getText());
	        	break;
	        case 3:
	        	driver.findElements(By.className("selinput")).get(3).sendKeys(frame4Hotel.childAge1.getText());
	        	driver.findElements(By.className("selinput")).get(4).sendKeys(frame4Hotel.childAge2.getText());
	        	driver.findElements(By.className("selinput")).get(5).sendKeys(frame4Hotel.childAge3.getText());
	        	break;
	        }
	        driver.findElement(By.xpath("//span[contains(text(),'Search')]")).click();
	//ВВЕЛИ ДАННЫЕ И НАЖАЛИ ПОИСК
	
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button/span[contains(text(),'Book')]")));
	        ArrayList <WebElement> hotelOptions = new ArrayList<WebElement>();         
	        WebElement nothing;
	        hotelOptions.addAll(driver.findElements(By.xpath("//button/span[contains(text(),'Book')]")));
	        //System.out.println("===||==="+hotelOptions.size());
	        int i=0;
	        boolean isPenaltyToday = true;
	        do{
		        while(i<hotelOptions.size()){
		        	nothing = hotelOptions.get(i).findElement(By.xpath("ancestor::td[@class='gtd']"));
		        	try{
		        		nothing.findElement(By.xpath("preceding-sibling::td//i[@class='fa fa-exclamation-circle fa-lg']"));
		        		i++;
		        	}
		        	catch(NoSuchElementException eee){
		        		hotelOptions.get(i).click();
		        		try{
		        			driver.switchTo().window(driver.getWindowHandle());
		            		driver.switchTo().alert().accept();
		            		i++;
		            		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("HTL_COMPARATOR"));
		            	}
		            	catch(NoAlertPresentException ee){
		            		i++;
		            		break;
		            	}	
		        	}
		        }
		        driver.switchTo().window(driver.getWindowHandle());
		        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("HTL_CREATE"));
		        if (FuncsThatHelp.isFineDateBeforeToday(driver)){
		        	driver.switchTo().window(driver.getWindowHandle());
		        	driver.findElement(By.cssSelector("button[title='Назад']")).click();
		        	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("HTL_COMPARATOR"));
		        }
		        else{
		        	isPenaltyToday = false;
		        }
	        }while(isPenaltyToday);
	        
	        int intAdultCount = Integer.parseInt(frame4Hotel.adultQuantity.getText());
	        int intChildrenCount = Integer.parseInt(frame4Hotel.childQuantity.getText());
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("field")));
	        ArrayList<WebElement> fields = new ArrayList<WebElement>();
	        fields.addAll(driver.findElements(By.className("field")));
	        ArrayList<WebElement> selectionFields = new ArrayList<WebElement>();
	        selectionFields.addAll(driver.findElements(By.className("selinput")));
	
	        
	        for (i =0;i<intAdultCount;i++){
	        	if(AllInfo.getAdultInfo().get(i).get(0).equals("М")){
	        		selectionFields.get(1+i*3).sendKeys("m");
	        	}
	        	else{
	        			selectionFields.get(1+i*3).click();
	        			Thread.sleep(333);
	        			driver.findElements(By.id("fSelect__3000027")).get(i).click();
	        	}
	        	selectionFields.get(2+i*3).sendKeys("ru");
	        	for (int j=0;j<5;j++){
	            	fields.get(j+i*5).sendKeys(AllInfo.getAdultInfo().get(i).get(j+1));
	        	}  	
	        }
	        
	        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyy");
	        for(i = intAdultCount; i<intAdultCount+intChildrenCount;i++){
	        	if(AllInfo.getChildInfo().get(i-intAdultCount).get(0).equals("М")){
	            	selectionFields.get(1+i*3).sendKeys("m");
	            	}
	            	else{
	            		selectionFields.get(1+i*3).click();
	            		driver.findElements(By.id("fSelect__3000027")).get(i).click();
	            	}
	        	fields.get(0+i*5).sendKeys(AllInfo.getChildInfo().get(i-intAdultCount).get(1));
	        	fields.get(1+i*5).sendKeys(AllInfo.getChildInfo().get(i-intAdultCount).get(2));
	        	int age = Integer.parseInt(frame4Hotel.getKinderAge().get(i-intAdultCount));
	        	fields.get(2+i*5).sendKeys(dtf.format(LocalDate.now().minusYears(age).minusDays(1)));
	        	fields.get(3+i*5).sendKeys(AllInfo.getChildInfo().get(i-intAdultCount).get(3));
	        	fields.get(4+i*5).sendKeys(AllInfo.getChildInfo().get(i-intAdultCount).get(4));
	        }
	        driver.findElement(By.cssSelector("button[title='Add to my basket']")).click();
	        driver.switchTo().window(driver.getWindowHandle());
	        try{
	            smallWait.until(ExpectedConditions.alertIsPresent());
	        	driver.switchTo().alert().accept();
	            //ALERT OCCURS RANDOMLY
	        }
	        catch(Exception Ex ){}
	//        try{
	//            driver.switchTo().alert().accept();
	//            }
	//            catch(Exception ee){}
	        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("DPG_CREATE"));
	        driver.findElement(By.className("selinput")).click();
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("seloptnormal")));
	        driver.findElement(By.className("seloptnormal")).click();
	        driver.findElement(By.cssSelector("button[title='Book']")).click();
	
	        driver.switchTo().window(windows.get(1));
	        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("COM_MSG")));
	        String bookingNo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2"))).getText();
	        System.out.println(bookingNo);
	        windows.clear();
	        windows.addAll(driver.getWindowHandles());
	        	//Thread.sleep(5000);
	        	driver.switchTo().window(windows.get(2));
	            driver.close();
	            driver.switchTo().window(windows.get(3));
	            try{
	            driver.switchTo().alert().accept();
	            }
	            catch(Exception ee){}
	            finally{
	//            	String price = driver.findElement(By.xpath("//b[contains(text(),'EUR')]")).getText();
	//            	System.out.println(price);
	            	driver.close();
	            	}
	            driver.switchTo().window(windows.get(1));
	            driver.close();
	 
	            driver.switchTo().window(windows.get(0)); 
	            
	            if (frame4Hotel.cancelOrNot.isSelected()){
	            FromFrontToBack.main(driver, wait, bookingNo);
	            }
        
		}catch(Exception e){
			String screenshotsDir = "C:/Users/syavin.v/Desktop/failureScreenshots";
			Path screenshotPath = Paths.get(screenshotsDir, System.currentTimeMillis()+" HotelFailureScreenshot.png");
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, screenshotPath.toFile());
			fail("Smth went wrong in bookIssuanceCancel test : "+ e);
}
		}
	@After
	public void afterTest() {
		driver.close();
        driver.quit();
	}
}