package natalieToursTests.backAndFrontFirstPackage;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.fail;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import funcsThatHelp.FromBackToFront;
import funcsThatHelp.FromFrontToBack;
import funcsThatHelp.FuncsThatHelp;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
//import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
//import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class Avia
{
	WebDriver driver = new ChromeDriver();
	
	@Before
	public void settings (){
		//driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(24, TimeUnit.SECONDS);
		FuncsThatHelp.whereToGoTo(driver);
        driver.manage().window().maximize();
	}
	
	@Test
	public void bookCancelTest() throws Exception {
		try{
			int i=0;
			WebDriverWait wait = new WebDriverWait(driver, 100 , 500);
	        WebDriverWait smallWait = new WebDriverWait(driver, 2 , 250);
	        FromBackToFront.main(driver,wait);
        
// opens Front Office in a new window
        
	        ArrayList<String> windows= new ArrayList<String>();//array for window handles
	        windows.addAll(driver.getWindowHandles());
	        driver.switchTo().window(windows.get(1)); //switching driver to newly opened window
	        driver.manage().window().maximize();
	        Thread.sleep(2000);//!!!!!!!!!!!
	        wait.until(ExpectedConditions.elementToBeClickable(By.id("menuFLY"))); //magic random problems
	        WebElement problemButton0 = driver.findElement(By.id("menuFLY"));
	        JavascriptExecutor executor = (JavascriptExecutor)driver;
	        executor.executeScript("arguments[0].click();", problemButton0);
	        Thread.sleep(1000);
      
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@title='FLY_COMPARATOR']/span")));
	        WebElement problemButton = driver.findElement(By.xpath("//li[@title='FLY_COMPARATOR']/span"));
	        executor.executeScript("arguments[0].click();", problemButton); // нажимаем на Авиабилеты(*)
	        Thread.sleep(1000);
        	
// field for needed info appears

	        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("FLY_COMPARATOR")));
	        WebElement departureField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Enter an origin']")));
	        departureField.sendKeys(frame4Avia.from.getText());
        	wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.cssSelector("div[class='sellist']")).get(0)));
        	departureField.sendKeys(Keys.ARROW_DOWN,Keys.RETURN);
        	WebElement arrivalField = driver.findElement(By.cssSelector("input[placeholder='Enter a destination']"));
        	arrivalField.sendKeys(frame4Avia.to.getText());
        	wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.cssSelector("div[class='sellist']")).get(1)));
        	arrivalField.sendKeys(Keys.ARROW_DOWN,Keys.RETURN);       
        	driver.findElements(By.cssSelector("input[title='dd/mm/yyyy']")).get(0).sendKeys(frame4Avia.whenTo.getText());
        	if(frame4Avia.oneWay.isSelected()){
        		driver.findElement(By.xpath("//td[@id='tOneWay']/div/input")).click();
        	}
        	else{
        		driver.findElements(By.cssSelector("input[title='dd/mm/yyyy']")).get(1).sendKeys(frame4Avia.whenBack.getText());
        	}
	        //if(frame4Avia.hardWay.isSelected()){
	       // 	driver.findElement(By.xpath("//td[@id='tMultipleDestination']/div/input")).click();
	       // }
	        //if(frame4Avia.straightWay.isSelected()){
	       // 	driver.findElement(By.xpath("//td[@id='tDirectFlight']/div/input")).click();
	       // }
	        int intChildrenCount = Integer.parseInt(frame4Avia.childQuantity.getText());
	        int intAdultCount = Integer.parseInt(frame4Avia.adultQuantity.getText());
	        ArrayList <WebElement> selinput = new ArrayList<WebElement>();
	        selinput.addAll(driver.findElements(By.className("selinput")));
	        selinput.get(5).sendKeys(frame4Avia.adultQuantity.getText());
	        selinput.get(6).sendKeys(frame4Avia.childQuantity.getText());
	        switch (intChildrenCount){
	        case 1:
	        	selinput.get(6).sendKeys(Keys.TAB,frame4Avia.childAge1.getText());
	        	break;
	        case 2:
	        	selinput.get(6).sendKeys(Keys.TAB,frame4Avia.childAge1.getText());
	        	selinput.get(6).sendKeys(Keys.TAB,Keys.TAB,frame4Avia.childAge2.getText());
	        	break;
	        case 3:
	        	selinput.get(6).sendKeys(Keys.TAB,frame4Avia.childAge1.getText());
	        	selinput.get(6).sendKeys(Keys.TAB,Keys.TAB,frame4Avia.childAge2.getText());
	        	selinput.get(6).sendKeys(Keys.TAB,Keys.TAB,Keys.TAB,frame4Avia.childAge3.getText());
	        	break;
	        }

	        driver.findElement(By.xpath("//span[contains(text(),'Search')]")).click(); // input flight parameters and click search
	        
	// next page RECOMMENDATIONS and more FILTERS, check to NOT have fine
	        i=0;
	        boolean reccomendationNOTok=true;
	        ArrayList<String> preferredSuppliers = new  ArrayList<String>();
	        ArrayList<WebElement> options = new ArrayList<WebElement>();
	        preferredSuppliers.addAll(Arrays.asList(frame4Avia.supplier.getText().split(",")));
	        preferredSuppliers.replaceAll(String::toUpperCase);
	        while(reccomendationNOTok==true){
		        if(i==9){
		        	driver.findElement(By.xpath("//td[@id='next']/button")).click();
		        }
		        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tableResults")));
		        Thread.sleep(1488);
		
		        FuncsThatHelp.doAdditionalFiltrations(driver, smallWait);
		        
		        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[title='Book']")));	
		        options.clear();
		        options.addAll( driver.findElements(By.cssSelector("button[title='Book']")));
		        boolean isPenaltyToday = true;
		        i=0;
		        while (i<options.size()&&isPenaltyToday==true){
		        	System.out.println(i);
		        	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//td[@title='supplier']/b")));
		        	if (preferredSuppliers.contains(driver.findElements(By.xpath("//td[@title='supplier']/b")).get(i).getText())||frame4Avia.supplier.getText().equals("")){
		        		options.get(i).click();
		        		try{
			        		smallWait.until(ExpectedConditions.alertIsPresent());
			        		driver.switchTo().alert().accept();
			        		i++;
			        		driver.switchTo().window(driver.getWindowHandle());
			    	        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("FLY_COMPARATOR")));
			    	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@id='tStops']//span[contains(text(),'All')]")));
			        		FuncsThatHelp.doAdditionalFiltrations(driver, smallWait);
		            //ALERT OCCURS RANDOMLY
		        		}
		        		catch(TimeoutException Exx){
			        		driver.switchTo().window(driver.getWindowHandle());
			        		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("FLY_CREATE")));
			        		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@id='tCancelTerms']//td[@class='gtd']")));
			        		if (FuncsThatHelp.isFineDateBeforeToday(driver)){
			        			driver.switchTo().window(driver.getWindowHandle());
			        			driver.findElement(By.cssSelector("button[title='Back']")).click();
			        			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("FLY_COMPARATOR"));	
			        			i++;
			        		}
			        		else{
			        			isPenaltyToday=false;
			        			reccomendationNOTok=false;
			        		}
		        		}
		        	}
		        	else{
		        		i++;
	        		}
		        }
	        }
                
// next page
//        Thread.sleep(5000);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("field")));
        
        ArrayList<WebElement> fields = new ArrayList<WebElement>();
        fields.addAll(driver.findElements(By.className("field")));
        ArrayList<WebElement> selectionFields = new ArrayList<WebElement>();
        selectionFields.addAll(driver.findElements(By.cssSelector("table[class='selinput']")));
        
       
//INSERTING ADULT(-S) INFO
        
        for(i = 0; i < intAdultCount ;i++){
        selectionFields.get(1 + i*5).sendKeys(AllInfo.getAdultInfo().get(i).get(0));
        selectionFields.get(2 + i*5).sendKeys("r");
        Thread.sleep(666);
        selectionFields.get(2 + i*5).click();
        driver.findElements(By.id("fSelect__178")).get(i).click();
//        Thread.sleep(333);
//        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("fSelect__178")));
//        driver.findElements(By.id("fSelect__178")).get(i).click();
        selectionFields.get(3 + i*5).sendKeys("p");
        for(int j = 1;j<7;j++){
        	fields.get(j +i*9).sendKeys(AllInfo.getAdultInfo().get(i).get(j));
        }
        }
        
//INSERTING CHILDREN INFO
        DateTimeFormatter standardDtf = DateTimeFormatter.ofPattern("ddMMyyyy");
        
        for(i = intAdultCount; i<intAdultCount+intChildrenCount;i++){
        	selectionFields.get(1 + i*5).sendKeys(AllInfo.getChildInfo().get(i-intAdultCount).get(0));
        	fields.get(1 +i*9).sendKeys(AllInfo.getChildInfo().get(i-intAdultCount).get(1));
            fields.get(2 +i*9).sendKeys(AllInfo.getChildInfo().get(i-intAdultCount).get(2));
            int age = Integer.parseInt(frame4Avia.getKinderAge().get(i-intAdultCount));
            fields.get(3 +i*9).sendKeys(standardDtf.format(LocalDate.now().minusYears(age).minusDays(1)));
            selectionFields.get(2 + i*5).sendKeys("r");
            Thread.sleep(666);
            selectionFields.get(2 + i*5).click();
            driver.findElements(By.id("fSelect__178")).get(i).click();
            selectionFields.get(3 + i*5).sendKeys("p");
            fields.get(4 + i*9).sendKeys(AllInfo.getChildInfo().get(i-intAdultCount).get(3));
            fields.get(5 + i*9).sendKeys(AllInfo.getChildInfo().get(i-intAdultCount).get(4));
        }
        

        driver.findElement(By.cssSelector("button[title='Add to my basket']")).click();
        driver.switchTo().window(driver.getWindowHandle());
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("DPG_CREATE"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("selinput"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("seloptnormal")));
        driver.findElement(By.className("seloptnormal")).click();
        driver.findElement(By.cssSelector("button[title='Book']")).click();
        try{
        	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("imgProcessing")));
            smallWait.until(ExpectedConditions.alertIsPresent()).accept();;
            //ALERT OCCURS RANDOMLY
        }
        catch(Exception Ex ){}
//next page , 2 new windows appear
        
        driver.switchTo().window(windows.get(1));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("COM_MSG")));
        String bookingNo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2"))).getText();
        System.out.println(bookingNo);
        windows.clear();
        windows.addAll(driver.getWindowHandles());
        for (i=1;i<windows.size();i++){
        	driver.switchTo().window(windows.get(i));
            driver.close();
            try{
                driver.switchTo().alert().accept();
                }
                catch(Exception ee){}
        }
        
        driver.switchTo().window(windows.get(0)); 
        if (frame4Avia.cancelOrNot.isSelected()){
        FromFrontToBack.main(driver, wait, bookingNo);
        }
		}
		catch (Exception e){
			String screenshotsDir = "C:/Users/syavin.v/Desktop/failureScreenshots";
			Path screenshotPath = Paths.get(screenshotsDir, System.currentTimeMillis()+" AviaFailureScreenshot.png");
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, screenshotPath.toFile());
			fail("Smth went wrong in bookIssuanceCancel test : "+ e);
		}
        
	}
	
	
	
	@After
	public void afterTest(){
        driver.quit();
	}
	public static void main(String[] args){
		new Avia();
	}


}
