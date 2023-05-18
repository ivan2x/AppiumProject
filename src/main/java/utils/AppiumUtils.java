package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public abstract class AppiumUtils {
    public AppiumDriverLocalService service;

    public Double getFormattedAmount(String amount){
        Double price = Double.valueOf(amount.substring(1));
        return price;
    }
    public static List<HashMap<String,String>> getJsonData(String jsonFilePath) throws IOException {
        //convert json file content to json string -- step1: Parse Json file --> Json String (Commons-io)
       // "System.getProperty("user.dir")+"/src/main/resources/eComerce.json"
        String jsonContent= FileUtils.readFileToString(new File(jsonFilePath),
                StandardCharsets.UTF_8);
        ObjectMapper mapper=new ObjectMapper();
        //convert json string to hashmap -- step2:Json String --> Hash Map (Jackson)
        List<HashMap<String,String>> data=mapper.readValue(jsonContent,
                new TypeReference<List<HashMap<String,String>>>(){
        });
        return data;
    }

    public AppiumDriverLocalService startAppiumServer(String ipAdress, int port){
        //ipAdress 127.0.0.1 port 4723
        String appiumPath = "/usr/local/lib/node_modules/appium/build/lib/main.js";
        String nodePath = "/usr/local/bin/node";
        service = new AppiumServiceBuilder().usingDriverExecutable(new File(nodePath)).withAppiumJS(new File(appiumPath))
                .withIPAddress(ipAdress).usingPort(port).build();
        return service;
    }

    public void waitForElementToAppear(WebElement ele, AndroidDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        wait.until(ExpectedConditions.attributeContains((ele),"Text","Cart"));

    }
    public String getScreenshotPath(String testCaseName, AppiumDriver driver) throws IOException {
        File source = driver.getScreenshotAs(OutputType.FILE);
        String destinationFile = System.getProperty("user.dir")+"/reports/" + testCaseName + ".png";
        FileUtils.copyFile(source, new File(destinationFile));
        return destinationFile;
        //1. capture and place in folder
        //2. extent report pick file and attach to report

    }
}
