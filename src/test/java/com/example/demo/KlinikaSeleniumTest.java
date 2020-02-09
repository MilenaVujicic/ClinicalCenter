package com.example.demo;

import static org.assertj.core.api.Assertions.setRemoveAssertJRelatedElementsFromStackTrace;
import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Termin;
import com.example.demo.service.TerminService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class KlinikaSeleniumTest {

	private WebDriver driver;
	
	@Autowired
	private TerminService terminService;
	
	private LocalStorage localStorage;
	
	/*
	@Test
	public void test1() {
		//System.setProperty("webdriver.chrome.driver", "D:\\MyDocsAndi\\downloads\\chromedriver\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", "D:\\Computer\\Downloads\\chromedriver.exe");
		// Initialize browser
		driver=new ChromeDriver();
		localStorage = new LocalStorage(driver);
		
		driver.get("http://localhost:8080");
		driver.manage().window().maximize();
		
		WebElement prijava_dugme = driver.findElement(By.id("prijava_dugme"));
		prijava_dugme.click();
		
		driver.navigate().to("http://localhost:8080/login.html");
		
		WebElement email = driver.findElement(By.id("email"));
		email.sendKeys("petar.pertovic@gmail.com");
		
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("njegos");
		
		WebElement button = driver.findElement(By.id("button"));
		button.click();
		
		localStorage.setItemInLocalStorage("id", "1");
		
		driver.manage().timeouts().implicitlyWait(100,TimeUnit.SECONDS);
		driver.navigate().to("http://localhost:8080/pacijentHomePage.html");
		driver.manage().timeouts().implicitlyWait(100,TimeUnit.SECONDS);
		
		WebElement klinika_dugme = driver.findElement(By.id("klinika_dugme"));
		klinika_dugme.click();
		
		driver.navigate().to("http://localhost:8080/klinike.html");
		
		WebElement datepicker = driver.findElement(By.id("datepicker"));
		datepicker.sendKeys("12/01/2019");
		
		WebElement spec = driver.findElement(By.id("spec"));
		spec.sendKeys("Opsti pregled");
		
		WebElement button_search_available = driver.findElement(By.id("button_search_available"));
		button_search_available.click();
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("1")));
		element.click();
		
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		
		driver.navigate().to("http://localhost:8080/lekari.html");
		
		WebElement lekar = wait.until(ExpectedConditions.elementToBeClickable(By.id("1")));
		lekar.click();
		
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		
		driver.navigate().to("http://localhost:8080/unapredDefinisaniPregledi.html");
		
		WebElement termin = wait.until(ExpectedConditions.elementToBeClickable(By.id("1")));
		termin.click();
		
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		
		WebElement logout_dugme = driver.findElement(By.id("logout_dugme"));
		logout_dugme.click();
		
		driver.navigate().to("http://localhost:8080/login.html");
		
		Termin t = terminService.findOne(1L);
		//assertEquals(t.isSlobodan(), false);
		
		driver.close();
	}
	
	@Test
	public void test2() {
		//System.setProperty("webdriver.chrome.driver", "D:\\MyDocsAndi\\downloads\\chromedriver\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", "D:\\Computer\\Downloads\\chromedriver.exe");
		// Initialize browser
		driver=new ChromeDriver();
		localStorage = new LocalStorage(driver);
		
		driver.get("http://localhost:8080");
		driver.manage().window().maximize();
		
		WebElement prijava_dugme = driver.findElement(By.id("prijava_dugme"));
		prijava_dugme.click();
		
		driver.navigate().to("http://localhost:8080/login.html");
		
		WebElement email = driver.findElement(By.id("email"));
		email.sendKeys("petar.pertovic@gmail.com");
		
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("njegos");
		
		WebElement button = driver.findElement(By.id("button"));
		button.click();
		
		localStorage.setItemInLocalStorage("id", "1");
		
		driver.manage().timeouts().implicitlyWait(100,TimeUnit.SECONDS);
		driver.navigate().to("http://localhost:8080/pacijentHomePage.html");
		driver.manage().timeouts().implicitlyWait(100,TimeUnit.SECONDS);
		
		WebElement klinika_dugme = driver.findElement(By.id("klinika_dugme"));
		klinika_dugme.click();
		
		driver.navigate().to("http://localhost:8080/klinike.html");
		
		WebElement datepicker = driver.findElement(By.id("datepicker"));
		datepicker.sendKeys("12/01/2019");
		
		WebElement spec = driver.findElement(By.id("spec"));
		spec.sendKeys("Opsti pregled");
		
		WebElement name = driver.findElement(By.id("clinicName"));
		name.sendKeys("Klinika");
		
		WebElement filter_klinika_dugme = driver.findElement(By.id("filter_klinika_dugme"));
		filter_klinika_dugme.click();
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("2")));
		element.click();
		
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		
		driver.navigate().to("http://localhost:8080/lekari.html");
		
		WebElement lekar = wait.until(ExpectedConditions.elementToBeClickable(By.id("4")));
		lekar.click();
		
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		
		driver.navigate().to("http://localhost:8080/unapredDefinisaniPregledi.html");
		
		WebElement termin = wait.until(ExpectedConditions.elementToBeClickable(By.id("4")));
		termin.click();
		
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		
		WebElement logout_dugme = driver.findElement(By.id("logout_dugme"));
		logout_dugme.click();
		
		driver.navigate().to("http://localhost:8080/login.html");
		
		Termin t = terminService.findOne(4L);
		//assertEquals(t.isSlobodan(), false);
		
		driver.close();
	}
	
	@Test
	public void test3() {
		System.setProperty("webdriver.chrome.driver", "D:\\Computer\\Downloads\\chromedriver.exe");
		driver=new ChromeDriver();
		localStorage = new LocalStorage(driver);
		
		driver.get("http://localhost:8080");
		driver.manage().window().maximize();
		
		WebElement prijava_dugme = driver.findElement(By.id("prijava_dugme"));
		prijava_dugme.click();
		
		driver.navigate().to("http://localhost:8080/login.html");
		
		WebElement email = driver.findElement(By.id("email"));
		email.sendKeys("adam.adminic@gmail.com");
		
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("adam");
		
		WebElement button = driver.findElement(By.id("button"));
		button.click();
		
		localStorage.setItemInLocalStorage("id", "4");
		
		WebElement aptBtn = driver.findElement(By.id("aApt"));
		aptBtn.click();
		
		WebElement moreBtn = driver.findElement(By.className("more"));
		moreBtn.click();
		
		
	}*/
}
