package com.prestashop.tests;

import static org.testng.Assert.assertEquals;

import java.time.LocalDateTime;

import org.apache.http.impl.io.SocketOutputBuffer;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NegativeScenarios {
	
	@BeforeClass
	public void setUpClass() {
		System.out.println("TestNG started on : " + LocalDateTime.now()); 
	}
	
WebDriver driver;
	
    @BeforeMethod
   public void setup() {
    	WebDriverManager.chromedriver().setup();
    	driver = new ChromeDriver();
    	driver.navigate().to("http://automationpractice.com");
    	driver.manage().window().fullscreen();
    	
    }
    @AfterMethod
    public void tearDown() {
    //	driver.close();
    }

    @Test
    public void wrongCredential() {
    	driver.findElement(By.xpath("//a[@class=\"login\" and @href=\"http://automationpractice.com/index.php?controller=my-account\"]")).click();
		driver.findElement(By.id("email")).sendKeys("abc@gmail.com" + Keys.ENTER);
		driver.findElement(By.id("passwd")).sendKeys("abc123" + Keys.ENTER);
		String expected = "Authentication failed.";
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id=\"center_column\"]//li[.='Authentication failed.']")).getText(), expected);

	}
    @Test
    public void emailTest() {
    	driver.findElement(By.xpath("//a[@class=\"login\" and @href=\"http://automationpractice.com/index.php?controller=my-account\"]")).click();
		driver.findElement(By.id("email")).sendKeys("abcgmail.com" + Keys.ENTER);
		driver.findElement(By.id("passwd")).sendKeys("abc123" + Keys.ENTER);
		String expected = "Invalid email address.";
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id=\"center_column\"]//li[.='Invalid email address.']")).getText(), expected);
    }
    @Test
    public void blankEmail() {
    	driver.findElement(By.xpath("//a[@class=\"login\" and @href=\"http://automationpractice.com/index.php?controller=my-account\"]")).click();
		driver.findElement(By.id("email")).sendKeys("" + Keys.ENTER);
		driver.findElement(By.id("passwd")).sendKeys("abc123" + Keys.ENTER);
		String expected = "An email address required.";
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id=\"center_column\"]//li[.='An email address required.']")).getText(), expected);
    }
    @Test
    public void blankPassword() {
    	driver.findElement(By.xpath("//a[@class=\"login\" and @href=\"http://automationpractice.com/index.php?controller=my-account\"]")).click();
		driver.findElement(By.id("email")).sendKeys("abc@gmail.com" + Keys.ENTER);
		driver.findElement(By.id("passwd")).sendKeys("" + Keys.ENTER);
		String expected = "Password is required.";
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id=\"center_column\"]//li[.='Password is required.']")).getText(), expected);
    }

    @AfterClass
    public void tearDownClass() {
    	System.out.println("TestNG ended on : " + LocalDateTime.now()); 
    }
}
