package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	
	public WebDriver driver;
	By email=By.id("user_email");
	By password=By.id("user_password");
	By loginbutton=By.xpath("//input[@value='Log In']");
	By alertinvalidlogindata=By.xpath("//div[@role='alert']");
	
	public LoginPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
	this.driver=driver;
	
	}


	public WebElement getEmail()
	{
		return driver.findElement(email);
	}
	
	public WebElement getPassword()
	{
		return driver.findElement(password);
	}


	public WebElement getLoginButton()
	{
		return driver.findElement(loginbutton);
	}
	
	public WebElement getalertinvalidlogin()
	{
		return driver.findElement(alertinvalidlogindata);
	}

}
