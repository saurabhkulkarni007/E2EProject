package Academy;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class base {

	public WebDriver driver;
	public Properties pro;

	public WebDriver initializeBrowser() throws IOException {
		pro = new Properties();
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Academy\\data.properties");
		pro.load(fis);
		//String browserName=System.getProperty("browser");
	
		String browserName = pro.getProperty("browser");
		System.out.println(browserName);
		// Uses data.properties values for browser field

		if (browserName.equals("chrome")) {
					System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\java\\Academy\\chromedriver.exe");
					driver = new ChromeDriver();
		}

		if (browserName.equals("firefox"))

		{
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\main\\java\\Academy\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		if (browserName.equals("headlesschrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\java\\Academy\\chromedriver.exe");
			ChromeOptions option=new ChromeOptions();
			option.addArguments("--headless");
			
			driver = new ChromeDriver(option);
		}
		
		if(browserName.contentEquals("chrome_emulator"))
		{
			Map<String, String> mobileEmulation = new HashMap<>();

			mobileEmulation.put("deviceName", "iPhone X");
			mobileEmulation.put("deviceName", "iPhone X");
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\java\\Academy\\chromedriver.exe");
			
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

			driver = new ChromeDriver(chromeOptions);
			
		}
		
		if(browserName.contentEquals("appium"))
		{
			/*DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability("deviceName", "SKRedmi");
			cap.setCapability("udid", "28e91d3f0305");
			cap.setCapability("platformName", "Android");
			cap.setCapability("platformVersion", "9 PKQ1.180917.001");
			cap.setCapability("appPackage","com.nextbus.dublin");
			cap.setCapability("appActivity","com.nextbus.dublin.activity.MainActivity");
			URL url = new URL("http://localhxost:4723/wd/hub");
			driver = new AppiumDriver<MobileElement>(url, cap);
			*/
			File appDir = new File("src//main//java");
			File app = new File(appDir, "APIDemos-debug.apk");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "SKRedmi");
			capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
			capabilities.setCapability("appPackage","com.android.chrome");
			capabilities.setCapability("appActivity","org.chromium.chrome.browser.incognito.IncognitoTabLauncher");
			driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			driver.findElement(By.xpath("//*[@text='Accept & continue']")).click();
			driver.findElement(By.xpath("//*[@text='No, thanks']")).click();
			return driver;
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;

	}
	
	

	public void captureScreenshot() throws IOException {
		Date date = new Date();
		File evidence = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		//FileUtils.copyFile(evidence,
			//	new File("F:\\Automation 2020\\Screenshots\\E2EProject\\screenshot_" + date.getTime() + ".png"));
		FileUtils.copyFile(evidence,
				new File(System.getProperty("user.dir")+ date.getTime() + ".png"));
		

	}

}
