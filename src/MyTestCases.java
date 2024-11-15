import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.crypto.DecapsulateException;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class MyTestCases {
	
	DesiredCapabilities cpas = new DesiredCapabilities();
	
	String AppiumURl = "http://127.0.0.1:4723/wd/hub";
	
	AndroidDriver driver;
	
	
	
	@BeforeTest
	public void mySetup() {
		
		cpas.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
		cpas.setCapability(MobileCapabilityType.DEVICE_NAME,"roro");
		File myApplication = new File("src/MyApplications/calculator.apk");
		
		cpas.setCapability(MobileCapabilityType.APP,myApplication.getAbsolutePath());
		
		
	} 
	
	
	
	@Test()
	public void MyFirstTest() throws MalformedURLException {
		
		driver = new AndroidDriver(new URL(AppiumURl), cpas);
		
	}

}
