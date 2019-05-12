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
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

public class Tours {
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
	public void toursTest() throws Exception{
		try{
		    WebDriverWait smallWait = new WebDriverWait(driver, 3 , 333);
			WebDriverWait wait = new WebDriverWait(driver, 77 , 333);
	        FromBackToFront.main(driver,wait);   
		// opens Front Office in a new window
//ЗАПОЛНЯЕМ ИНФУ
		        ArrayList<String> windows= new ArrayList<String>();//array for window handles
		        windows.addAll(driver.getWindowHandles());
		        driver.switchTo().window(windows.get(1)); //switching driver to newly opened window
		        
		        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("STD_SEARCHER"));
		        ArrayList<WebElement> selinput=new ArrayList<WebElement>();
		        selinput.addAll(driver.findElements(By.className("selinput")));
		        selinput.get(0).click();
		        driver.findElement(By.xpath("//div[contains(text(),'"+frame4Tours.from.getText()+"')]")).click();
		        selinput.get(1).click();
		        driver.findElement(By.xpath("//div[contains(text(),'"+frame4Tours.to.getText()+"')]")).click();
		        driver.findElement(By.xpath("//td[@id='tZonas']//table[@class='selinput']")).click();
		        Thread.sleep(1333);
		        driver.findElement(By.xpath("//div[contains(text(),'"+frame4Tours.region.getText()+"')]")).click();
		        driver.findElements(By.cssSelector("input[title='dd/mm/yyyy']")).get(0).clear();
		        driver.findElements(By.cssSelector("input[title='dd/mm/yyyy']")).get(0).sendKeys(frame4Tours.firstDate.getText());
		        driver.findElements(By.cssSelector("input[title='dd/mm/yyyy']")).get(1).clear();
		        driver.findElements(By.cssSelector("input[title='dd/mm/yyyy']")).get(1).sendKeys(frame4Tours.secondDate.getText());
		        selinput.get(3).sendKeys(frame4Tours.nights1.getText());
		        selinput.get(4).sendKeys(frame4Tours.nights2.getText());
		        selinput.get(5).sendKeys(frame4Tours.rooms.getText());
		        selinput.get(6).sendKeys(frame4Tours.adultQuantity.getText());
		        WebElement kinder = driver.findElement(By.cssSelector("table[title='без детей']"));
		        kinder.click();
		        int intChildrenCount = Integer.parseInt(frame4Tours.childQuantity.getText());
		        switch (intChildrenCount){
		        case 1:
		        	driver.findElement(By.xpath("//div[contains(text(),'1 ребенок')]")).click();
		        	kinder.sendKeys(Keys.TAB ,frame4Tours.childAge1.getText());
		        	break;
		        case 2:
		        	driver.findElement(By.xpath("//div[contains(text(),'2 детей')]")).click();
		        	kinder.sendKeys(Keys.TAB ,frame4Tours.childAge1.getText());
		        	kinder.sendKeys(Keys.TAB ,Keys.TAB,frame4Tours.childAge2.getText());
		        	break;
		        case 3:
		        	driver.findElement(By.xpath("//div[contains(text(),'3 детей')]")).click();
		        	kinder.sendKeys(Keys.TAB ,frame4Tours.childAge1.getText());
		        	kinder.sendKeys(Keys.TAB ,Keys.TAB,frame4Tours.childAge2.getText());
		        	kinder.sendKeys(Keys.TAB ,Keys.TAB,Keys.TAB,frame4Tours.childAge3.getText());
		        	break;
		        }
		        
		        if (frame4Tours.straightWay.isSelected()){
		        driver.findElement(By.cssSelector("input[type='checkbox']")).click();
		        }
		        driver.findElement(By.cssSelector("input[type='checkbox']")).sendKeys(Keys.TAB,frame4Tours.tourNo.getText());
//ЗАПОЛНИЛИ ИНФУ И НАЖИМАЕМ ПОИСК
		        driver.findElement(By.xpath("//span[contains(text(),'Поиск')]")).click();
		        
		        ArrayList <WebElement> tzt = new ArrayList<WebElement>();
		        ArrayList <WebElement> moreOptions = new ArrayList<WebElement>();
		        WebElement thicc;
		        int i=0,j=0;
		        boolean isInstantConfirmed = false;
		   
		        
		        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Больше опций')]")));
		        moreOptions.addAll(driver.findElements(By.xpath("//span[contains(text(),'Больше опций')]")));
		        
	//ЗАЩИТА ОТ НЕВОЗВРАТНЫХ ТАРИФОВ
		        for (i=1;i<moreOptions.size();i++){
		        	moreOptions.get(i).click();
		        }
		        moreOptions.get(0).click();
		        tzt.addAll(driver.findElements(By.xpath("//span[contains(text(),'Выбрать')]"))); 
		        int lastInstantConfirmationButton = tzt.size();
		        tzt.addAll(driver.findElements(By.xpath("//span[contains(text(),'Под запрос')]")));
		        boolean isPenaltyToday;
		        
		        String currentDate = FuncsThatHelp.currentDate();
		        do {
		        while(j<tzt.size()){
		        	if(tzt.get(j).isDisplayed()){
		        		thicc = tzt.get(j).findElement(By.xpath("ancestor::td[@class='gtd']"));
		        		try{    	    
		        			thicc.findElement(By.xpath("preceding-sibling::td//i[@class='fa fa-exclamation-circle fa-lg']"));
		        			j++;
		        		}//проверка восклицательного знака в рекомендациях
		        		catch(Exception e){
		        			isInstantConfirmed = (j<lastInstantConfirmationButton);
		        			tzt.get(j).click();
		        			j++;
		        			break;
		        		}
		        	}
		        }
		        	driver.switchTo().window(driver.getWindowHandle());
			        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("VAR_SEARCH"));
			        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[title='Далее']")));
			        driver.findElement(By.cssSelector("button[title='Далее']")).click();
			        try{
			        	driver.findElement(By.xpath("//div[contains(text(),'"+currentDate+"')]"));
			        	isPenaltyToday = true;
			        	driver.switchTo().window(driver.getWindowHandle());
			        	driver.findElement(By.cssSelector("button[title='Назад']")).click();
			        	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("STD_SEARCHER"));
			        }
			        catch(NoSuchElementException ee){
			        	isPenaltyToday = false;
			        }
			        System.out.println(j);
		        }while(isPenaltyToday);
		        
		        ArrayList<WebElement> fields = new ArrayList<WebElement>();
		        fields.addAll(driver.findElements(By.className("field")));
		        ArrayList<WebElement> selectionFields = new ArrayList<WebElement>();
		        selectionFields.addAll(driver.findElements(By.className("selinput")));
		        //System.out.println(fields.size()+" "+selectionFields.size());
		        int intAdultCount = Integer.parseInt(frame4Tours.adultQuantity.getText());
//ENTER ADULT INFO
		        for (i =0;i<intAdultCount;i++){
		        	selectionFields.get(6+i*4).sendKeys(AllInfo.getAdultInfo().get(i).get(0));
		        	fields.get(13+i*13).sendKeys(AllInfo.getAdultInfo().get(i).get(1));
		        	fields.get(14+i*13).sendKeys(AllInfo.getAdultInfo().get(i).get(2));
		        	fields.get(15+i*13).sendKeys(AllInfo.getAdultInfo().get(i).get(3));
		        	selectionFields.get(7+i*4).sendKeys("р");
		        	fields.get(17+i*13).sendKeys(AllInfo.getAdultInfo().get(i).get(4));
		        	fields.get(18+i*13).sendKeys(AllInfo.getAdultInfo().get(i).get(5));
		        	fields.get(22+i*13).sendKeys(AllInfo.getAdultInfo().get(i).get(6));
		        }
//ENTER CHILDREN INFO
		        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyy");
		        for(i = intAdultCount; i<intAdultCount+intChildrenCount;i++){
		        	selectionFields.get(6+i*4).sendKeys(AllInfo.getChildInfo().get(i-intAdultCount).get(0));
		        	fields.get(13 +i*13).sendKeys(AllInfo.getChildInfo().get(i-intAdultCount).get(1));
		            fields.get(14 +i*13).sendKeys(AllInfo.getChildInfo().get(i-intAdultCount).get(2));
		            int age = Integer.parseInt(frame4Tours.getKinderAge().get(i-intAdultCount));
		            fields.get(15 +i*13).sendKeys(dtf.format(LocalDate.now().minusYears(age).minusDays(1)));
		            selectionFields.get(7+i*4).sendKeys("р");
		            fields.get(17+i*13).sendKeys(AllInfo.getChildInfo().get(i-intAdultCount).get(3));
		        	fields.get(18+i*13).sendKeys(AllInfo.getChildInfo().get(i-intAdultCount).get(4));
		        }
		        
		        driver.findElement(By.cssSelector("button[title='Добавить в корзину']")).click();
		        driver.switchTo().window(driver.getWindowHandle());
		        try{
		            smallWait.until(ExpectedConditions.alertIsPresent());
		        	driver.switchTo().alert().accept();
		            //ALERT OCCURS RANDOMLY
		        }
		        catch(Exception Ex ){}
		        if (isInstantConfirmed==false){
		        try{
		            smallWait.until(ExpectedConditions.alertIsPresent());
		        	driver.switchTo().alert().accept();
		            //ALERT OCCURS RANDOMLY
		        }
		        catch(Exception Ex ){}
		        }
		        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("DPG_CREATE"));
		        driver.findElement(By.className("selinput")).click();
		        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("seloptnormal")));
		        driver.findElement(By.className("seloptnormal")).click();
		        driver.findElement(By.cssSelector("button[title='Бронировать']")).click();
		        
		      //next page , 2 new windows appear
		        driver.switchTo().window(windows.get(1));
	              wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("COM_MSG")));
	              String bookingNo = driver.findElement(By.cssSelector("h2")).getText();
	              System.out.println(bookingNo);
	              windows.clear();
	              windows.addAll(driver.getWindowHandles());
	            if(isInstantConfirmed==true){
	            	try{
		              driver.switchTo().window(windows.get(2));
		              //.until(ExpectedConditions.alertIsPresent());
		              //driver.switchTo().alert().accept();
		              driver.close();
		              driver.switchTo().window(windows.get(3));		              
		              driver.switchTo().alert().accept();
		              }
		              catch(Exception ee){}
		              finally{driver.close();}
	            }
		              
		              driver.switchTo().window(windows.get(0)); 
		              FromFrontToBack.main(driver, wait, bookingNo);
		}
		catch(Exception e){
			Thread.sleep(5000);
			String screenshotsDir = "C:/Users/syavin.v/Desktop/failureScreenshots";
			Path screenshotPath = Paths.get(screenshotsDir, System.currentTimeMillis()+" TourFailureScreenshot.png");
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, screenshotPath.toFile());
			fail("tour test failed: "+e);
		}
	} 
	
	@After
	public void afterTest() {
        driver.quit();
	}

}
