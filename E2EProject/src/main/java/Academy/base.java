package Academy;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class base {

	public WebDriver driver;
	public Properties pro;

	public WebDriver initializeBrowser() throws IOException {
		pro = new Properties();
		FileInputStream fis = new FileInputStream(
				"F:\\Automation 2020\\E2EProject\\src\\main\\java\\Academy\\data.properties");
		pro.load(fis);
		//String browserName=System.getProperty("browser");
	
		String browserName = pro.getProperty("browser");
		System.out.println(browserName);
		// Uses data.properties values for browser field

		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "F:\\Automation 2020\\ChromeDriver 83\\chromedriver.exe");
			driver = new ChromeDriver();
		}

		if (browserName.equals("firefox"))

		{
			System.setProperty("webdriver.gecko.driver", "F:\\Automation 2020\\Firefox Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;

	}

	public void captureScreenshot() throws IOException {
		Date date = new Date();
		File evidence = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(evidence,
				new File("F:\\Automation 2020\\Screenshots\\E2EProject\\screenshot_" + date.getTime() + ".png"));

	}

}
