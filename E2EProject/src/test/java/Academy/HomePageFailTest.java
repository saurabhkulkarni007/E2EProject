package Academy;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObject.LandingPage;
import pageObject.LoginPage;

public class HomePageFailTest extends base {
public WebDriver driver;

	@BeforeTest
	public void launchBrowser() throws IOException {
		driver = initializeBrowser();
		driver.get(pro.getProperty("URL"));
	}

	@Test(dataProvider = "getData")
	public void basepageNavigation(String username, String password, String success) throws IOException {
		LandingPage lp = new LandingPage(driver);
		lp.getlogin().click();
		LoginPage lg = new LoginPage(driver);
		lg.getEmail().sendKeys(username);
		lg.getPassword().sendKeys(password);
		lg.getLoginButton().click();
		String alertText = lg.getalertinvalidlogin().getText().trim();
		Assert.assertEquals(alertText, "Invalid email or password.", success);
		driver.navigate().back();
		Assert.assertTrue(lp.getContactus().isDisplayed());
	}

	@DataProvider
	public Object[][] getData() {
		Object[][] data = new Object[2][3];
		data[0][0] = "aaa";
		data[0][1] = "bbb";
		data[0][2] = "Case1";

		data[1][0] = "bbbbb";
		data[1][1] = "ddddd";
		data[1][2] = "Case2";

		return data;
	}

	@AfterTest
	public void closebrowser() {
		driver.close();
	}

}
