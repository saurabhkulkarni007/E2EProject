package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage {
	
	public WebDriver driver;
	By Login=By.xpath("//span[text()='Login']");
	By Contactus=By.xpath("//li/a[text()='Contact']");
	
	
	public LandingPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
	this.driver=driver;
	
	}


	public WebElement getlogin()
	{
		return driver.findElement(Login);
	}


	public WebElement getContactus()
	{
		return driver.findElement(Contactus);
	}

	
}
