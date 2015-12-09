package PracticeTestNJ;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class YmailLoginObject {

	WebDriver driver;
	String link = "https://mail.yahoo.com";
	By username = By.id("login-username");
	By password = By.id("login-passwd");
	By singinbutton = By.id("login-signin");
	By homebutton = By.xpath(".//*[@href = 'https://www.yahoo.com/']");
	By OptionMainManu = By.xpath(".//*[@id='yui_3_10_3_1_1375219693637_127']/b[2]");
	By singout = By.id("yucs-signout");
	
	public YmailLoginObject(WebDriver driver){
		this.driver=driver;
		//this.driver.manage().window().maximize();
	}
	
	public void setusername(String username){
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(this.username).sendKeys(username);
	}
	
	public void setpassword(String password){
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(this.password).sendKeys(password);
	}
	
	public void clicksignin(){
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(singinbutton).click();
	}
	
	public void loginsuccss(){
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement homebutton = driver.findElement(this.homebutton);
		Assert.assertTrue(homebutton.isDisplayed(), "Login Successfull");
		Assert.assertFalse(!homebutton.isDisplayed(), "login faild");
	}
	
	public void login(String username, String password){
		setusername(username);
		setpassword(password);
		clicksignin();
	}
	
	public void logout(){
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		wait(5000);
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(OptionMainManu));
		wait(2000);
		action.moveToElement(driver.findElement(singout));
		action.click();
		action.perform();
	}
	
	
	public void wait(int time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
