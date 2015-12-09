package PracticeTestNJ;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;



public class PracticeTestNJ {
	
	WebDriver browser;
	int waittime = 10;
	
	@BeforeClass
	public void openbrowser(){
		browser = new FirefoxDriver();
		browser.manage().window().maximize();
				
	}
	
	
	@Test(groups="search")
	public void titletesting(){
		browser.get("http://www.google.com");
		String expectedtitle = "Google";
		String actualtitle = browser.getTitle();
		Assert.assertEquals(expectedtitle, actualtitle);
		
	}
	
	
	
	
	
	@Test(dependsOnMethods = "titletesting", groups="search")
	public void search(){
		browser.manage().timeouts().implicitlyWait(waittime,TimeUnit.SECONDS);
		browser.findElement(By.id("lst-ib")).sendKeys("Time now in Dhaka");
		browser.findElement(By.name("btnG")).click();
		String a = browser.findElement(By.xpath(".//*[@id='resultStats']")).getText();
		System.out.println(a);
		//takescreenshoot("GoogleSearch");
	}
	
	
	
	@Test(groups = "gmaillogin")
	public void gmailpage(){
		browser.get("https:\\www.gmail.com");
		//takescreenshoot("GmailLogin");
		String expected = "Gmail";
		String actual = browser.getTitle();
		System.out.println(actual);
		Assert.assertEquals(actual, expected);
	}
	
	
	
	
	@Test(dependsOnMethods = "gmailpage", groups = "gmaillogin")
	public void gmaillogin(){
		String username = "hesunny7";
		String password = "DELLvostro3400";
		browser.findElement(By.id("Email")).sendKeys(username);
		browser.findElement(By.id("next")).click();
		browser.findElement(By.id("Passwd")).sendKeys(password);
		browser.findElement(By.id("signIn")).click();
		browser.manage().timeouts().implicitlyWait(waittime, TimeUnit.SECONDS);
		if(browser.findElement(By.cssSelector(".gb_d.gbii")).isEnabled())
		{
			String expected = "Inbox - "+username+"@gmail.com - Gmail";
			Assert.assertEquals(browser.getTitle(), expected);
		}
		
	}
	
	@Test(dependsOnMethods = "gmaillogin", groups = "gmaillogin")
	public void gmaillogout(){
		browser.manage().timeouts().implicitlyWait(waittime, TimeUnit.SECONDS);
		browser.findElement(By.cssSelector(".gb_d.gbii")).click();
		browser.findElement(By.cssSelector("#gb_71")).click();
		String expected = "Gmail";
		String actual = browser.getTitle();
		Assert.assertEquals(actual, expected);
	}
	
	
	
	@AfterClass
	public void closebrowser(){
		browser.close();
	}
	
	public void takescreenshoot(String condition){
		TakesScreenshot tsh = (TakesScreenshot) browser;
		File f = tsh.getScreenshotAs(OutputType.FILE);
		System.out.println(f.getAbsoluteFile());
		if(f.canRead()){
			try {
				String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
				FileUtils.copyFile(f.getAbsoluteFile(),new File("c:\\backup\\"+condition+timeStamp+".jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
	}

}
