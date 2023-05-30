package lambdatest.practice;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestScenario2_Edge {
	public String username="priyanka.samantaray";
	public String accesskey = "ucwMAhA9E8vrTAKr1mbNrB9RKoITpCAClOd3uVZXLmYmzlt7za";
	public RemoteWebDriver driver;
	public String gridURL = "hub.lambdatest.com";
	String status;
	String hub;
	SessionId sessionId;
	WebDriver webdriver;
	
	
	@BeforeTest
	public void testsetUp() {

		
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("build", "TestDemo1");
		caps.setCapability("name", "test");
		caps.setCapability("platform", "macOS High Sierra");
		caps.setCapability("browserName", "Edge");
		caps.setCapability("version", "87.0");
		caps.setCapability("visual", true);
		caps.setCapability("network", true);
		caps.setCapability("console", true);
		

		try
        {
            driver = new RemoteWebDriver(new URL("https://" + "priyanka.samantaray" + ":" + "ucwMAhA9E8vrTAKr1mbNrB9RKoITpCAClOd3uVZXLmYmzlt7za" + "@hub.lambdatest.com/wd/hub"), caps);
        
        
        }
        catch (MalformedURLException e)
        {
            System.out.println("Invalid grid URL");
        }
		
	}
	
	@Test
	public void myTest() {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		driver.get("https://lambdatest.com");
		wait.until(ExpectedConditions.titleContains("Next-Generation Mobile Apps and Cross Browser Testing Cloud | LambdaTest"));
		String parentWindow=driver.getWindowHandle();
			WebElement allIntegrationsLink = driver.findElement(By.xpath("//h2[text()='Seamless Collaboration']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", allIntegrationsLink);


		Actions actions = new Actions(driver);
		
		
		actions.keyDown(Keys.COMMAND).build().perform();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[.='See All Integrations']"))));
		driver.findElement(By.xpath("//a[.='See All Integrations']")).click();
		actions.keyUp(Keys.COMMAND).build().perform();
		
		ArrayList<String> childWindows=new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(childWindows.get(1));
		
		wait.until(ExpectedConditions.titleContains("Integrations"));
		String expectedUrl = "https://www.lambdatest.com/integrations";

		org.testng.Assert.assertEquals(expectedUrl, driver.getCurrentUrl(), "URL is not matching with Expected");
	}
	
	@AfterTest
	public void endTest() {
		driver.quit();
	}

}
