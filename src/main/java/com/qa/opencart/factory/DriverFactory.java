package com.qa.opencart.factory;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.aspectj.util.FileUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.exception.FrameworkException;

public class DriverFactory {
	public WebDriver driver;
	public Properties prop;
	public OptionManager optionManager;

	public static String highlight;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/**
	 * this method is initializing the driver on the basis of given browser name
	 * 
	 * @param browserName
	 * @return this returns the driver
	 */
	public WebDriver initDriver(Properties prop) {

		optionManager = new OptionManager(prop);

		highlight = prop.getProperty("highlight").trim();
		String browserName = prop.getProperty("browser").toLowerCase().trim();
		// String browserName = system.getProperty("browser");

		System.out.println("browser name is : " + browserName);

		// chrome:
		if (browserName.equalsIgnoreCase("chrome")) {
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// run on remote/grid:
				init_remoteDriver("chrome");
			} else {
				// local execution
				tlDriver.set(new ChromeDriver(optionManager.getChromeOptions()));
			}

		}

		// firefox:
		else if (browserName.equalsIgnoreCase("firefox")) {
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// run on remote/grid:
				init_remoteDriver("firefox");
			} else {
				tlDriver.set(new FirefoxDriver(optionManager.getFirefoxOptions()));

			}
		}

		// edge:
		else if (browserName.equalsIgnoreCase("edge")) {
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// run on remote/grid:
				init_remoteDriver("edge");
			} else {
				tlDriver.set(new EdgeDriver(optionManager.getEdgeOptions()));
			}
		}

		// safari:
		else if (browserName.equalsIgnoreCase("safari")) {
			tlDriver.set(new SafariDriver());
		}

		else {
			System.out.println("plz pass the right browser name...." + browserName);
		//	throw new FrameworkException("NO BROWSER FOUND EXCEPTION....");
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url").trim());
		return getDriver();

	}

	/**
	 * this method is called internally to initialize the driver with
	 * RemoteWebDriver
	 * 
	 * @param browser
	 */
	private void init_remoteDriver(String browser) {
		
		System.out.println("Running tests on grid server:::" + browser);
		
		try {
			switch (browser.toLowerCase()) {
			case "chrome":
				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionManager.getChromeOptions()));
				break;
			case "firefox":
				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionManager.getFirefoxOptions()));
				break;
			case "edge":
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionManager.getEdgeOptions()));
				break;
			default:
				System.out.println("plz pass the right browser for remote execution..." + browser);
				//throw new FrameworkException("NOREMOTEBROWSEREXCEPTION");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

	/*
	 * get the local thread copy of the driver
	 */
	public synchronized static WebDriver getDriver() {
		return tlDriver.get();
	}


	/**
	 * this method is reading the properties from the .properties file
	 * 
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
					throw new FrameworkException("WRONG ENV IS PASSED...");
				// break;
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
			FileUtil.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

//	public static String getScreenshot() {
//		// TODO Auto-generated method stub
//		return null;
//	}
}


	



