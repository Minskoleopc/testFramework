package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties pro = new Properties();
	public static Properties loc = new Properties();
	
	@BeforeTest
	public void SetUp() throws IOException {
		if(driver == null) {
			//System.out.println(System.getProperty("user.dir"));
			FileInputStream input = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\configFiles\\config.properties");
			FileInputStream input2 = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\configFiles\\locators.properties");
			pro.load(input);
			loc.load(input2);
			System.out.println(pro.getProperty("browser"));
		}
		
		if(pro.getProperty("browser").equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.get(pro.getProperty("baseUrl"));
		}
		else if(pro.getProperty("browser").equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.get(pro.getProperty("baseUrl"));
		}
	}
	
	@AfterTest
	public void TearDown() {
		driver.quit();	
	}
	
}
