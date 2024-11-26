import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Random;

import javax.crypto.DecapsulateException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class MyTestCases {

	DesiredCapabilities cpas = new DesiredCapabilities();

	String AppiumURl = "http://127.0.0.1:4723/wd/hub";

	AndroidDriver driver;

	Random rand = new Random();

	@BeforeTest
	public void mySetup() {

		cpas.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		cpas.setCapability(MobileCapabilityType.DEVICE_NAME, "roro");
		File myApplication = new File("src/MyApplications/calculator.apk");

		cpas.setCapability(MobileCapabilityType.APP, myApplication.getAbsolutePath());

	}

	@Test(priority = 1, enabled = false)
	public void AddTwoNumber() throws MalformedURLException {

		driver = new AndroidDriver(new URL(AppiumURl), cpas);

		driver.findElement(By.id("com.google.android.calculator:id/digit_6")).click();
		driver.findElement(By.id("com.google.android.calculator:id/op_add")).click();
		driver.findElement(By.id("com.google.android.calculator:id/digit_5")).click();

		String ActualResult = driver.findElement(By.id("com.google.android.calculator:id/result_preview")).getText();
		String expectedResult = "11";
		System.out.println(ActualResult);
		System.out.println(expectedResult);

		Assert.assertEquals(ActualResult, expectedResult);

	}

	@Test(priority = 2, enabled = false)
	public void ClickOnTwoRandomNumbers() throws MalformedURLException {

		driver = new AndroidDriver(new URL(AppiumURl), cpas);

		List<WebElement> AllButtons = driver.findElements(By.className("android.widget.ImageButton"));

		for (int i = 0; i < AllButtons.size(); i++) {
			if (AllButtons.get(i).getAttribute("resource-id").contains("digit")) {
				AllButtons.get(i).click();

			}
		}

		String Actual = driver.findElement(By.id("com.google.android.calculator:id/formula")).getText();
		String Expected = "7894561230";

		Assert.assertEquals(Actual, Expected);

	}

	@Test(priority = 3, enabled = false)
	public void ClickOnEvenNumbers() throws MalformedURLException {

		driver = new AndroidDriver(new URL(AppiumURl), cpas);

		List<WebElement> AllButtons = driver.findElements(By.className("android.widget.ImageButton"));

		for (int i = 0; i < AllButtons.size(); i++) {

//			if (AllButtons.get(i).getAttribute("resource-id").contains("digit")) { // sol 1
//			AllButtons.get(i).click();
//		}

//			if (AllButtons.get(i).getAttribute("resource-id").contains("2")  // sol 2
//					|| AllButtons.get(i).getAttribute("resource-id").contains("4")
//					|| AllButtons.get(i).getAttribute("resource-id").contains("6")
//					|| AllButtons.get(i).getAttribute("resource-id").contains("8")
//					|| AllButtons.get(i).getAttribute("resource-id").contains("0")) {
//				
//				AllButtons.get(i).click();
//			}

			if (AllButtons.get(i).getAttribute("resource-id").contains("digit")) { // sol 3

				String theNumber = AllButtons.get(i).getAttribute("resource-id")
						.replace("com.google.android.calculator:id/digit_", "");
				int theNumberAsInt = Integer.parseInt(theNumber);

				if (theNumberAsInt % 2 == 0) {

					System.out.println(theNumber);
					AllButtons.get(i).click();
				}

			}
		}
	}

	@Test(priority = 4, enabled = true)
	public void PriorityTest() throws MalformedURLException {

		driver = new AndroidDriver(new URL(AppiumURl), cpas);

//		driver.findElement(By.cssSelector("android.widget.ImageView")).click();

		driver.findElement(By.id("com.google.android.calculator:id/parens")).click();

		List<WebElement> AllButtons = driver.findElements(By.className("android.widget.ImageButton"));
		for (int i = 0; i < AllButtons.size(); i++) {
			if (AllButtons.get(i).getAttribute("resource-id").contains("digit")) {
				String theNumber = AllButtons.get(i).getAttribute("resource-id")
						.replace("com.google.android.calculator:id/digit_", "");
				int theNumberAsInt = Integer.parseInt(theNumber);
				if (theNumberAsInt % 2 != 0) {
					System.out.println(theNumber);
					AllButtons.get(i).click();
				}
			}
		}
		driver.findElement(By.id("com.google.android.calculator:id/op_mul")).click();
		driver.findElement(By.id("com.google.android.calculator:id/digit_6")).click();
		driver.findElement(By.id("com.google.android.calculator:id/parens")).click();
		driver.findElement(By.id("com.google.android.calculator:id/op_add")).click();
		driver.findElement(By.id("com.google.android.calculator:id/digit_5")).click();

	}

}
