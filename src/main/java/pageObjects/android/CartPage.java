package pageObjects.android;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.androidActions;

import java.util.List;

public class CartPage extends androidActions {
    AndroidDriver driver;

    public CartPage(AndroidDriver driver) {
        super(driver); // send
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }
    @FindBy(id="com.androidsample.generalstore:id/productPrice")
    private List<WebElement> productList;
    @FindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
    private WebElement totalAmount;
    @FindBy(id = "com.androidsample.generalstore:id/termsButton")
    private WebElement terms;
    @FindBy(id = "android:id/button1")
    private WebElement acceptButton;
    @FindBy(className = "android.widget.CheckBox")
    private WebElement checkBox;
    @FindBy(id = "com.androidsample.generalstore:id/btnProceed")
    private WebElement proceed;

    public List<WebElement> getProductList(){
        return productList;

    }

    public double getProductSum(){
        int count = productList.size();
        double totalSum = 0;
        for(int i = 0; i< count; i++){
            String amountString = productList.get(i).getText();
            Double price = getFormattedAmount(amountString);
            totalSum = totalSum + price;
        }
        return totalSum;
    }

    public Double getTotalAmountDisplayed(){
        return getFormattedAmount(totalAmount.getText());
    }

    public void acceptTermsConditions(){
        longPressAction(terms);
        acceptButton.click();
    }

    public Double getFormattedAmount(String amount){
        Double price = Double.parseDouble(amount.substring(1));
        return price;
    }

    public void submitOrder(){
        checkBox.click();
        proceed.click();
    }



}

