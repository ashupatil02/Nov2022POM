package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {
	
	public WebDriver driver;
    public Properties prop;
    public OptionManager optionManager;
    public static String highlight;
    
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
    
    
    
 /**
  * this method is initializing the driver on the basis of given browser name
  * @param browserName
  * @return the driver 
  */
	
	
	public WebDriver initDriver(Properties prop) {
		optionManager = new OptionManager(prop);
		 highlight = prop.getProperty("highlight").trim();
		String browserName = prop.getProperty("browser").toLowerCase().trim();
		
		
		System.out.println("browser name is : " + browserName);
		
		if(browserName.equalsIgnoreCase("chrome")) {
			tlDriver.set(new ChromeDriver(optionManager.getChromeOptions()));
			
			//driver = new ChromeDriver(optionManager.getChromeOptions());
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			//driver = new FirefoxDriver(optionManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionManager.getFirefoxOptions()));
		}
		else if(browserName.equalsIgnoreCase("safari")) {
			//driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
		}
		else if(browserName.equalsIgnoreCase("edge")) {
		//	driver = new EdgeDriver(optionManager.getEdgeOptions());
			tlDriver.set(new EdgeDriver(optionManager.getEdgeOptions()));
					
		}
		else {
			System.out.println("plz pass the right browsername...." + browserName);
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url").trim());
		return getDriver();
	}
	
	/*
	 * get the local thread copy of the driver
	 */
	public synchronized static WebDriver getDriver() {
		return tlDriver.get();
	}
	
	
	/**
	 * this method is reading the properties from the .properties file
	 * @return
	 */
		
		public Properties initProp() {
			
			
			
			
			// mvn clean install -Denv="qa"
			// mvn clean install
			prop = new Properties();
			FileInputStream ip = null;
			String envName = System.getProperty("env");
			System.out.println("Running test cases on Env: " + envName);

			try {
				if (envName == null) {
					System.out.println("no env is passed....Running tests on QA env...");
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
				} else {
					switch (envName.toLowerCase().trim()) {
					case "qa":
						ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
						break;
					case "stage":
						ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
						break;
					case "dev":
						ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
						break;
					case "prod":
						ip = new FileInputStream("./src/test/resources/config/config.properties");
						break;

					default:
						System.out.println("....Wrong env is passed....No need to run the test cases....");
					//	throw new FrameworkException("WRONG ENV IS PASSED...");
						//break;
					}

				}
			} catch (FileNotFoundException e) {
			}

			try {
				prop.load(ip);
			} catch (IOException e) {
				e.printStackTrace();
			}

			return prop;
		}

		/**
		 * take screenshot
		 */
		
		
		public static String getScreenshot() {
			File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
			String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
			File destination = new File(path);
			try {
				FileHandler.copy(srcFile, destination);
			} catch (IOException e) {
				e.printStackTrace();

			}
		return path;
		}
		
		}
	
	
	
	
	
	



