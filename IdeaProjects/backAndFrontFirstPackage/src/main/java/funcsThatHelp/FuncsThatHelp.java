package funcsThatHelp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import natalieToursTests.backAndFrontFirstPackage.Initializer;
import natalieToursTests.backAndFrontFirstPackage.frame4Avia;

public class FuncsThatHelp {

	public static void whereToGoTo(WebDriver driver){
		if (!(Initializer.systemToGoTo.getText().equals(""))){
			driver.get(Initializer.systemToGoTo.getText());
		}
		else{
			switch (Initializer.portal.getSelectedIndex()){
			case 0:
				driver.get("http://empty-dp.natecnia.com/back/login.svt");
				break;
			case 1:
				driver.get("http://preproductiondevelopment.natecnia.com/back/login.svt");
				break;
			case 2:
				driver.get("https://global.natalie-tours.ru/back/");
				break;
			}
		}
	}
	
	public static String currentDate(){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMMM yyyy");
	    String currentDate = dtf.format(LocalDate.now());
	    currentDate = currentDate.substring(0, 3) + currentDate.substring(3, 4).toUpperCase() + currentDate.substring(4);
	    if (currentDate.contains("мая")){
	        currentDate = currentDate.substring(0,currentDate.length()-6) + "й" +currentDate.substring(currentDate.length()-5);
	    }
	    else if (currentDate.contains("a 20")){
	        currentDate = currentDate.substring(0,currentDate.length()-6) + currentDate.substring(currentDate.length()-5);
	    }
	    else if (currentDate.contains("я 20")){
	        currentDate = currentDate.substring(0,currentDate.length()-6) + "ь" +currentDate.substring(currentDate.length()-5);
	    }
	    return(currentDate);
	}
	
	public static void additionalAviaFilters(WebDriver driver, WebDriverWait smallWait, String tdID,String preferredOption){
		try{
			WebElement el1 = smallWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@id='"+tdID+"']//span[contains(text(),'All')]")));
		  	try{
	    		((JavascriptExecutor)driver).executeScript("arguments[0].click()", el1); 
	    		if(driver.findElement(By.xpath("//td[@id='"+tdID+"']//input")).isSelected()){
	    			((JavascriptExecutor)driver).executeScript("arguments[0].click()", el1); 
	    		}
	    		WebElement el2 = smallWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@id='"+tdID+"']//span[contains(text(),'"+preferredOption+"')]"))); 
	    		((JavascriptExecutor)driver).executeScript("arguments[0].click()", el2); 
	    	}
	    	catch(TimeoutException ee){
	    		System.out.println("No option "+preferredOption+", All will be selected");
	    		((JavascriptExecutor)driver).executeScript("arguments[0].click()", el1); 
	    	}
		}
		catch(TimeoutException ee){
			System.out.println("Only one option available");
		}
	}
	
	public static void doAdditionalFiltrations (WebDriver driver, WebDriverWait smallWait){
		int i;
        //ПЕРЕСАДКИ
        switch(frame4Avia.stop.getSelectedIndex()){
        case 1:
        	FuncsThatHelp.additionalAviaFilters(driver, smallWait, "tStops", "No stops");
        	break;
        case 2:
        	FuncsThatHelp.additionalAviaFilters(driver, smallWait, "tStops", "1 stop");
        	break;
        case 3:
        	FuncsThatHelp.additionalAviaFilters(driver, smallWait, "tStops", "2 stops");
        	break;
        }
        //КЛАСС ПЕРЕЛЁТА
        switch(frame4Avia.classOfService.getSelectedIndex()){
        case 1:
        	FuncsThatHelp.additionalAviaFilters(driver, smallWait, "tCabines", "Economy Standard");
        	break;
        case 2:
        	FuncsThatHelp.additionalAviaFilters(driver, smallWait, "tCabines", "Economy Premium");
        	break;
        case 3:
        	FuncsThatHelp.additionalAviaFilters(driver, smallWait, "tCabines", "Business");
        	break;
        }
        //БАГАЖ
        switch(frame4Avia.baggage.getSelectedIndex()){
        case 1:
        	FuncsThatHelp.additionalAviaFilters(driver, smallWait, "tAllowedOpt", "Included");
        	break;
        case 2:
        	FuncsThatHelp.additionalAviaFilters(driver, smallWait, "tAllowedOpt", "Not included");
        	break;
        }
		 //АВИАКОМПАНИИ
        if (!frame4Avia.preferredAirlines.getText().equals("")){
	        ArrayList<String> preferredAirlines = new  ArrayList<String>();
	        ArrayList<String> prefAirlinez = new  ArrayList<String>();
	        prefAirlinez.addAll(Arrays.asList(frame4Avia.preferredAirlines.getText().split(",")));
	        for (i=0;i<prefAirlinez.size();i++){
	        	preferredAirlines.add(StringUtils.capitalize(prefAirlinez.get(i)));
	        }
	        //driver.findElement(By.xpath("//td[@id='tAirlines']//td[@class='fmulsel']")).sendKeys(Keys.HOME);
	        WebElement miscEl = smallWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@id='tAirlines']//span[contains(text(),'All')]")));
	        ((JavascriptExecutor)driver).executeScript("arguments[0].click()", miscEl); 
	        if(driver.findElement(By.xpath("//td[@id='tAirlines']//input")).isSelected()){
	        	 ((JavascriptExecutor)driver).executeScript("arguments[0].click()", miscEl); 
	        }
	        for(i=0;i<preferredAirlines.size();i++){
	        	try{
	        		miscEl = smallWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[@id='tAirlines']//span[contains(text(),'"+preferredAirlines.get(i)+"')]")));
	        		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", miscEl); 
	        		((JavascriptExecutor)driver).executeScript("arguments[0].click()", miscEl); 
	        	}
	        	catch(TimeoutException ee){
	        		System.out.println("No such Airline for that destination: "+preferredAirlines.get(i));
	        	}
	        }
        }
        //ЦЕНА
        if(!frame4Avia.priceFrom.getText().equals("")){
        	smallWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@id='tPriceFrom']//input"))).clear();
        	driver.findElement(By.xpath("//td[@id='tPriceFrom']//input")).sendKeys(frame4Avia.priceFrom.getText());
        }
        if(!frame4Avia.priceTo.getText().equals("")){
        	smallWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@id='tPriceTo']//input"))).clear();
        	driver.findElement(By.xpath("//td[@id='tPriceTo']//input")).sendKeys(frame4Avia.priceTo.getText());
        }
	}
	public static boolean isFineDateBeforeToday (WebDriver driver){
		DateTimeFormatter standardDtf =DateTimeFormatter.ofPattern("dd-MM-yyyy");
        org.joda.time.format.DateTimeFormatter dtf = DateTimeFormat.forPattern("dd-MM-yyyy"); //dd MMMM yyyy
        org.joda.time.format.DateTimeFormatter dtfOnlyMonth = DateTimeFormat.forPattern("MMMM");
	    String currentDateString = standardDtf.format(LocalDate.now());
	    DateTime currentDate = dtf.withLocale(Locale.ENGLISH).parseDateTime(currentDateString);
		String cancelationDateString = driver.findElements(By.xpath("//td[@id='tCancelTerms']//td[@class='gtd']")).get(1).getText();//XX MAY 2018
		String monthName = cancelationDateString.substring(cancelationDateString.indexOf(" ")+1,cancelationDateString.lastIndexOf(" "));//MAY
		DateTime date00 = dtfOnlyMonth.withLocale(Locale.ENGLISH).parseDateTime(monthName);
		int monthNumber = date00.getMonthOfYear();
		cancelationDateString = cancelationDateString.substring(0,cancelationDateString.indexOf(" "))+"-"+Integer.toString(monthNumber)+"-"+cancelationDateString.substring
				(cancelationDateString.lastIndexOf(" ")+1,cancelationDateString.length());
		date00= dtf.parseDateTime(cancelationDateString);
		return currentDate.plusDays(1).isAfter(date00);
	}
}
