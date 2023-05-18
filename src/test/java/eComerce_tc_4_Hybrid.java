import TestUtils.BaseTest;
import io.appium.java_client.android.Activity;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.android.CartPage;
import pageObjects.android.FormPage;
import pageObjects.android.ProductCataloge;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class eComerce_tc_4_Hybrid extends BaseTest {
    @Test(dataProvider="getData", groups = {"smoke"})
    public void fillForm( HashMap<String, String> input ) throws InterruptedException {
        //formPage = new FormPage(driver)
        formPage.setNameField(input.get("name"));
        formPage.setGender(input.get("gender"));
        formPage.setCountry(input.get("country"));
        ProductCataloge productCataloge = formPage.submitForm();
        productCataloge.addItemToCartByIndex(0);
        productCataloge.addItemToCartByIndex(0);
        CartPage cartPage =   productCataloge.goToCartPage();
        //id("com.androidsample.generalstore:id/toolbar_title")),"Text","Cart"));
        //validation
       double totalSum = cartPage.getProductSum();
       double displayFormattedSum = cartPage.getTotalAmountDisplayed();
       Assert.assertEquals(totalSum,displayFormattedSum);
       cartPage.acceptTermsConditions();
       cartPage.submitOrder();

    }
    @BeforeMethod(alwaysRun = true)
    public void preSetup(){
        //reset to home page
        formPage.setActivity();
    }
    @DataProvider
    public Object[][] getData() throws IOException {
      List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir")+"/src/main/resources/eComerce.json");
        return new Object[][]{ {data.get(0)}, {data.get(1)} };
        //{ {hash},   {hash}  } data
    }
}
