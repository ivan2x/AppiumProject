package pageObjects.android;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.androidActions;

import static org.openqa.selenium.support.PageFactory.initElements;

public class FormPage extends androidActions {
    AndroidDriver driver;

    public FormPage(AndroidDriver driver){
       super(driver); // send
       this.driver = driver;
       PageFactory.initElements(driver,this);

    }
    //LOCATORS
    @FindBy(id="com.androidsample.generalstore:id/nameField")
    private WebElement nameField;
    @FindBy(xpath="//android.widget.RadioButton[@text='Female']")
    private  WebElement femaleOption;
    @FindBy(xpath="//android.widget.RadioButton[@text='Male']")
    private  WebElement maleOption;

    @FindBy(id="android:id/text1")
    private WebElement countrySelection;

    @FindBy(id="com.androidsample.generalstore:id/btnLetsShop")
    private WebElement shopButton;
    //ACTIONS
    public void setNameField(String name){
        nameField.sendKeys(name);
        //Hide the keyboard
        driver.hideKeyboard();
    }
    public void setGender(String gender){
        if(gender.contains("female")){
            femaleOption.click();
        }
        else
            maleOption.click();
    }

    public void setCountry(String countryName){
        countrySelection.click();
        scrollToText(countryName);
        driver.findElement(By.xpath("//android.widget.TextView[@text='"+countryName+"']")).click();

    }
    public ProductCataloge submitForm(){
        shopButton.click();
        return new ProductCataloge(driver);
    }

    public void setActivity(){
        //reset to home page
        Activity activity = new Activity("com.androidsample.generalstore","com.androidsample.generalstore.SplashActivity");
        driver.startActivity(activity);
    }
}
