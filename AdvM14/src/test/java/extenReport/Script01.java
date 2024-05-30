package extenReport;

import java.time.LocalDateTime;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Script01 {

	@Test
	public void Script1 (){
		WebDriver driver;
		ScreenShotMethod ss=new ScreenShotMethod();
		LocalDateTime dateTime=LocalDateTime.now();
		String date=dateTime.toString().replace(":","-");
		ExtentReports reports = new ExtentReports();
		ExtentSparkReporter reporter= new ExtentSparkReporter("./reports/"+date+".html");
		reports.attachReporter(reporter);		
		ExtentTest test=reports.createTest("login");
		driver =new ChromeDriver();
		driver.manage().window().maximize();
		test.log(Status.INFO, "browser is maximised");
		driver.get("https://demowebshop.tricentis.com/");
		test.log(Status.INFO, "Demowebshop appl is launched");
		driver.findElement(By.linkText("Log in")).click();
		test.log(Status.INFO,"login page is displayed");
		driver.findElement(By.id("Email")).sendKeys("bangalore541@gmail.com");
		test.log(Status.INFO,"user entered email");
		driver.findElement(By.id("Password")).sendKeys("bangalor123");
		test.log(Status.INFO,"user entered password");
		driver.findElement(By.xpath("//input[@value='Log in']")).click();
		test.log(Status.INFO,"user clicked on login button");
		try {
			if (driver.findElement(By.linkText("Log out")).isDisplayed()){
			test.pass(MediaEntityBuilder.createScreenCaptureFromPath(ss.takeScreenShot(driver)).build());
			
			}
		}catch (Exception e) {
			test.fail(MediaEntityBuilder.createScreenCaptureFromPath(ss.takeScreenShot(driver)).build());
		}
		reports.flush();
	}
}
