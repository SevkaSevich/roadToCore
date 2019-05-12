package natalieToursTests.backAndFrontFirstPackage;

import static org.junit.Assert.fail;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
//import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import funcsThatHelp.FromBackToFront;
import funcsThatHelp.FromFrontToBack;
import funcsThatHelp.FuncsThatHelp;

public class Transfer {
WebDriver driver = new ChromeDriver();
	
	@Before
	public void settings (){
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
	public void transferTest() throws Exception{
		try{
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
		    //WebDriverWait smallWait = new WebDriverWait(driver, 3 , 333);
			WebDriverWait wait = new WebDriverWait(driver, 77 , 333);
	        FromBackToFront.main(driver,wait);
	        ArrayList<String> windows= new ArrayList<String>();//array for window handles
	        windows.addAll(driver.getWindowHandles());
	        driver.switchTo().window(windows.get(1)); //switching driver to newly opened window
	        Thread.sleep(1111);
	        wait.until(ExpectedConditions.elementToBeClickable(By.id("menuSERLabel")));
	        driver.findElement(By.id("menuSERLabel")).click();
	        WebElement problemButton0 = driver.findElement(By.cssSelector("span[title='Трансферы (*)']"));
	        JavascriptExecutor executor = (JavascriptExecutor)driver;
	        executor.executeScript("arguments[0].click();", problemButton0);
//ЗАПОЛНЯЕМ ИНФУ
	        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("TRS_COMPARATOR"));
	        ArrayList<WebElement> selinput = new ArrayList<WebElement>();
	        selinput.addAll(driver.findElements(By.className("selinput")));


	        selinput.get(0).click();
	        driver.findElement(By.xpath("//div[contains(text(),'Терминал')]")).click();
	        selinput.get(1).click();
	        driver.findElements(By.xpath("//div[contains(text(),'Отель')]")).get(1).click();
	        if(frame4Transfer.thereAndBack.isSelected()){
	        	driver.findElement(By.cssSelector("input[type='checkbox']")).click();
	        }
	        ArrayList<WebElement> fields = new ArrayList<WebElement>();
	        fields.addAll(driver.findElements(By.className("field")));
	        fields.get(2).sendKeys(frame4Transfer.from.getText());
	        Thread.sleep(1888);
	        fields.get(2).sendKeys(Keys.ARROW_DOWN,Keys.ENTER);      
	        fields.get(3).click();
	        driver.switchTo().window(driver.getWindowHandle());
	        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("COM_LOV_STD"));
	        driver.findElement(By.xpath("//td[@id='tSearcher']//input")).sendKeys(frame4Transfer.to.getText());
	        driver.findElement(By.xpath("//div[contains(text(),'"+frame4Transfer.to.getText()+"')]")).click();
	        driver.switchTo().window(driver.getWindowHandle());
	        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("TRS_COMPARATOR"));
	        
	        fields.get(4).sendKeys(frame4Transfer.firstDate.getText());
	        fields.get(6).sendKeys(frame4Transfer.time1.getText());
	        if(frame4Transfer.thereAndBack.isSelected()){
	        	fields.get(8).sendKeys(frame4Transfer.secondDate.getText());
	        	fields.get(9).sendKeys(frame4Transfer.time2.getText());
	        	fields.get(11).sendKeys(frame4Transfer.time3.getText());
	        }
	        selinput.get(4).sendKeys(frame4Transfer.adultQuantity.getText());
	        selinput.get(5).sendKeys(frame4Transfer.childQuantity.getText());
	        int intChildrenCount = Integer.parseInt(frame4Transfer.childQuantity.getText());
	        switch (intChildrenCount){
	        case 1:
	        	selinput.get(5).sendKeys(Keys.TAB ,frame4Transfer.childAge1.getText());
	        	break;
	        case 2:
	        	selinput.get(5).sendKeys(Keys.TAB ,frame4Transfer.childAge1.getText());
	        	selinput.get(5).sendKeys(Keys.TAB ,Keys.TAB,frame4Transfer.childAge2.getText());
	        	break;
	        case 3:
	        	selinput.get(5).sendKeys(Keys.TAB ,frame4Transfer.childAge1.getText());
	        	selinput.get(5).sendKeys(Keys.TAB ,Keys.TAB,frame4Transfer.childAge2.getText());
	        	selinput.get(5).sendKeys(Keys.TAB ,Keys.TAB,Keys.TAB,frame4Transfer.childAge3.getText());
	        	break;
	        }
	        
	        driver.findElement(By.xpath("//span[contains(text(),'Поиск')]")).click();
	        
	        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='radio']")));
	        
	        int i = 0; int j = 0;
	        boolean isPenaltyToday=true;
	        String currentDate = FuncsThatHelp.currentDate();
	        ArrayList<WebElement> resultsFrom = new ArrayList<WebElement>();
	        ArrayList<WebElement> resultsTo = new ArrayList<WebElement>();
	        resultsTo.addAll(driver.findElements(By.xpath("//td[@id='tResults0']//input[@type='radio']")));
	        if(frame4Transfer.thereAndBack.isSelected()){
		        resultsFrom.addAll(driver.findElements(By.xpath("//td[@id='tResults1']//input[@type='radio']")));
	        }
	        
	        while(i<resultsTo.size()&&isPenaltyToday==true){
	        resultsTo.get(i).click();
	        if(frame4Transfer.thereAndBack.isSelected()){
	        	resultsFrom.get(j).click();
	        }
	        driver.findElement(By.xpath("//span[contains(text(),'Выбрать')]")).click();
	        try{
	        	System.out.println("ассептаю но не нажимаю как мудила");
	        	driver.switchTo().window(driver.getWindowHandle());
	        	driver.switchTo().alert().accept();
	        	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("TRS_COMPARATOR"));
	        	if (j<resultsFrom.size()-1){
	        		j++;
	        	}
	        	else{
	        		j=0;
	        		i++;
	        	}
	        	continue;
	        }
	        catch(NoAlertPresentException e){}
	        driver.switchTo().window(driver.getWindowHandle());
	        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("TRS_CREATE"));
	        try{
	        	driver.findElement(By.xpath("//div[contains(text(),'"+currentDate+"')]"));
	        	driver.switchTo().window(driver.getWindowHandle());
	        	driver.findElement(By.cssSelector("button[title='Назад']")).click();
	        	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("TRS_COMPARATOR"));
	        	if (j<resultsFrom.size()-1){
	        		j++;
	        	}
	        	else{
	        		j=0;
	        		i++;
	        	}
	        }
	        catch(NoSuchElementException ee){
	        	isPenaltyToday = false;
	        }
	        }
	        
	        fields.clear();
	        fields.addAll(driver.findElements(By.cssSelector("input[class='field']")));
	        int intAdultCount = Integer.parseInt(frame4Transfer.adultQuantity.getText());
	        int intChildCount = Integer.parseInt(frame4Transfer.childQuantity.getText());
	        for(i = 0;i<intAdultCount;i++){
	        	fields.get(0+6*i).sendKeys(AllInfo.getAdultInfo().get(i).get(1));
	        	fields.get(1+6*i).sendKeys(AllInfo.getAdultInfo().get(i).get(2));
	        }
	        for(i = intAdultCount;i<intAdultCount+intChildCount;i++){
	        	fields.get(0+6*i).sendKeys(AllInfo.getChildInfo().get(i-intAdultCount).get(1));
	        	fields.get(1+6*i).sendKeys(AllInfo.getChildInfo().get(i-intAdultCount).get(2));
	        }
	        fields.get((intChildCount+intAdultCount)*6).sendKeys(frame4Transfer.flight1.getText());
	        fields.get((intChildCount+intAdultCount)*6+1).sendKeys(frame4Transfer.terminalFrom.getText());
	        Thread.sleep(1666);
	        fields.get((intChildCount+intAdultCount)*6+1).sendKeys(Keys.ARROW_DOWN,Keys.ENTER);
	        fields.get((intAdultCount+intChildCount+1)*6).sendKeys(frame4Transfer.flight2.getText());
	        fields.get((intAdultCount+intChildCount+1)*6+1).sendKeys(frame4Transfer.terminalTo.getText());
	        Thread.sleep(1666);
	        fields.get((intChildCount+intAdultCount+1)*6+1).sendKeys(Keys.ARROW_DOWN,Keys.ENTER);
	        
	        driver.findElement(By.xpath("//span[contains(text(),'Добавить в корзину')]")).click();
	        driver.switchTo().window(driver.getWindowHandle());
	        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("DPG_CREATE"));
	        driver.findElement(By.className("selinput")).click();
	        Thread.sleep(333);
	        driver.findElement(By.className("seloptnormal")).click();
	        driver.findElement(By.xpath("//span[contains(text(),'Бронировать')]")).click();
;
	        driver.switchTo().window(windows.get(1));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("COM_MSG")));
            String bookingNo = driver.findElement(By.cssSelector("h2")).getText();
            System.out.println(bookingNo);
	        windows.clear();
	        windows.addAll(driver.getWindowHandles());
            driver.switchTo().window(windows.get(2));
            driver.close();
            driver.switchTo().window(windows.get(3));
            
            try{
            driver.switchTo().alert().accept();
            }
            catch(Exception ee){}
            finally{driver.close();}
            
            driver.switchTo().window(windows.get(0)); 
            
            FromFrontToBack.main(driver,wait,bookingNo);
	        
	        Thread.sleep(6666);
		       
		}
		catch(Exception e){
			Thread.sleep(1111);
			String screenshotsDir = "C:/Users/syavin.v/Desktop/failureScreenshots";
			Path screenshotPath = Paths.get(screenshotsDir, System.currentTimeMillis()+" TransferFailureScreenshot.png");
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, screenshotPath.toFile());
			fail("transfer test failed: "+e);
		}
	} 
	
	@After
	public void afterTest() {
        driver.quit();
	}

}
