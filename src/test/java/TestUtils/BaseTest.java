package TestUtils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pageObjects.android.FormPage;
import utils.AppiumUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class BaseTest extends AppiumUtils {
    public AndroidDriver driver;
    public AppiumDriverLocalService service;
    //
    public FormPage formPage;

    @BeforeClass(alwaysRun = true)
    public void configureAppium() throws IOException {
        //Code to start the server
        //js file to invoke server
        //Set global properties, it CAPTURES data from properties file
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/data.properties");
        //maven parameters
        //if it is not equals to null then its taken maven parameters if yes then takes the ip from the properties file
        String ipAddress = System.getProperty("ipAddress")!=null ?  System.getProperty("ipAddress") :prop.getProperty("ipAddress");
        prop.load(fis);
        String port = prop.getProperty("port");
        String device = prop.getProperty("AndroidDevice");
            service = startAppiumServer(ipAddress, Integer.parseInt(port));
            //service.start();
            //Appium code -> Appium server -> Mobile
            //capabilities what OS
            //UiAutomator2Options
            UiAutomator2Options options = new UiAutomator2Options();
            //set details
            options.setDeviceName(device);
            //DRIVER
            options.setChromedriverExecutable(System.getProperty("user.dir")+"/src/main/resources/chromedriver");
            //INSTALL APP
            options.setApp(System.getProperty("user.dir")+"/src/main/resources/General-Store.apk");
            //Create object AndroidDriver
            driver = new AndroidDriver(service.getUrl(), options);
            //TIMEOUT
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            formPage = new FormPage(driver);
    }


    @AfterClass(alwaysRun = true)
    public void tearDown(){
        driver.quit();
        service.stop();
        }
    }
