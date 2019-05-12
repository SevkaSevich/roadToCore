package funcsThatHelp;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
//import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;

public class FromFrontToBack {
	
	public static void main(WebDriver driver,WebDriverWait wait,String bookingNo){
        WebElement operations = driver.findElement(By.xpath("//span[contains(text(),'Operations')]"));
        new Actions(driver).moveToElement(operations).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Booking management')]")));
        driver.findElement(By.xpath("//span[contains(text(),'Booking management')]")).click();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("OPE_RES_LIST")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[class='field']")));
        driver.findElements(By.cssSelector("input[class='field']")).get(6).sendKeys(bookingNo);
        driver.findElement(By.xpath("//span[contains(text(),'More options')]")).click();
        driver.findElement(By.xpath("//td[@id='tBFilter']/button")).click();
        wait = new WebDriverWait(driver, 3 , 500);
        try{
        	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ginfopag'][contains(text(),'NO RECORD HAS BEEN FOUND')]")));
        	driver.findElement(By.xpath("//span[contains(text(),'Only show trial bookings')]")).click();
        	driver.findElement(By.xpath("//td[@id='tBFilter']/button")).click();
        }
        catch(TimeoutException ee){}
        wait = new WebDriverWait(driver, 100 , 500);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[title='Edit']")));
        driver.findElements(By.cssSelector("button[title='Cancel booking']")).get(0).click();
        driver.switchTo().window(driver.getWindowHandle());
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("OPE_RES_CANCEL")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@id='tBCancel1']/button")));
        driver.findElement(By.xpath("//td[@id='tBCancel1']/button")).click();
        driver.switchTo().window(driver.getWindowHandle());
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.sendKeys("Book cancelation autotest");
        alert.accept();
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
	}
}
