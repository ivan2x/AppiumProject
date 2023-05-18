package pageObjects.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.androidActions;

import java.util.List;

public class ProductCataloge extends androidActions {

    AndroidDriver driver;
    //GRANDPARENT(AppiumUtils) -> AndroidActions -> ProductCatalogue
    public ProductCataloge(AndroidDriver driver){
        super(driver); // send
        this.driver = driver;
        PageFactory.initElements(driver,this);

    }

    @FindBy(xpath ="//android.widget.TextView[@text='ADD TO CART']")
    private List<WebElement> addToCart;

    @FindBy(id ="com.androidsample.generalstore:id/appbar_btn_cart")
    private WebElement cart;


    public void addItemToCartByIndex(int index){
        addToCart.get(index).click();
    }

    public CartPage goToCartPage() throws InterruptedException {
        cart.click();
        Thread.sleep(3500);
        return new CartPage(driver);
    }
}
