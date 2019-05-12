package funcsThatHelp;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import natalieToursTests.backAndFrontFirstPackage.Initializer;

public class FromBackToFront {

	public static void main(WebDriver driver,WebDriverWait wait) throws InterruptedException{
		driver.findElement(By.id("login")).sendKeys(Initializer.getLogin());
        driver.findElement(By.id("pass")).sendKeys(Initializer.getPassword());
        driver.findElement(By.xpath("//input[@class='loginButton']")).click();
//next page
       
        WebElement clients = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Clients')]")));
        new Actions(driver).moveToElement(clients).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Travel agency management')]"))).click();
//next page
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("CLI_OPERADOR_LIST")));
        Thread.sleep(666);
        String agencyName = Initializer.agency.getText().toUpperCase();
        driver.findElements(By.cssSelector("input")).get(1).sendKeys(agencyName);
        driver.findElement(By.xpath("//button[@title='Filter']")).click();                      // filter by name of agency
// shows table
        WebElement preferredAgency = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr/td/div[contains(text(),'"+agencyName+"')]")));
        WebElement misc = preferredAgency.findElement(By.xpath("parent::td"));
        misc.findElement(By.xpath("following-sibling::td/button[@title='Edit']")).click();
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("CLI_OPERADOR_EDIT")));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("selinput")));
        String portalOfAgency = Initializer.portalOfAgency.getItemAt(Initializer.portalOfAgency.getSelectedIndex());
        Thread.sleep(1500);//sukablyat
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@id='tPortal']//img[@src='/back/images/select.gif']"))).click();
        WebElement agencyPortal = driver.findElement(By.xpath("//td[@id='tPortal']//div[contains(text(),'"+portalOfAgency+"')]"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", agencyPortal); 
		((JavascriptExecutor)driver).executeScript("arguments[0].click()", agencyPortal); 
		driver.switchTo().defaultContent();
		driver.findElement(By.cssSelector("button[title='Save']")).click();
		wait.until(ExpectedConditions.alertIsPresent()).accept();
        Thread.sleep(666);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[title='Go to Front Office']"))).click();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("ACCESO_WEB_GESTION")));
        Thread.sleep(666);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Access')]"))).click();
	}
}
