package PracticeTestNJ;

/*----------------------------------there is a bug with logout option
 * 
 */


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PracticeTestNJ.YmailLoginObject;

public class YmailTest {
	WebDriver driver;
	YmailLoginObject ymail;
	
	@BeforeTest
	public void openbrowser(){
		driver = new FirefoxDriver();
		ymail = new YmailLoginObject(driver);
		driver.manage().window().maximize();
		driver.get("https://mail.yahoo.com");
	}
	
	@Test
	public void login(){
		wait(2000);
		String username = "hossain.quality";
		String password = "DELLvostro3400";
		ymail.login(username, password);
		ymail.loginsuccss();
	}
	
	@Test(dependsOnMethods = "login")
	public void logout(){
		ymail.logout();
	}
	
	@AfterTest
	public void closebrowser(){
		driver.close();
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
