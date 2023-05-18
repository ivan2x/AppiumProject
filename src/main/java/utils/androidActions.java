package utils;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;
import static java.util.Collections.singletonList;

public class androidActions extends AppiumUtils{
 AndroidDriver driver;
    public androidActions(AndroidDriver driver){
        this.driver = driver;

    }
    public void longPressAction(WebElement ele){

        TouchAction action = new TouchAction(driver);
        Point location = ele.getLocation();
        Dimension size = ele.getSize();
        int x = location.getX() + size.width / 2;
        int y = location.getY() + size.height / 2;
        action.longPress(longPressOptions().withDuration(Duration.ofMillis(600))
                .withPosition(new PointOption().withCoordinates(x, y))).perform();


        /*
        //vertical Scroll
        int centerX = ele.getSize().width/2;
        int centerY = ele.getSize().height/2;

        double startY = ele.getRect().y + (ele.getSize().height * 0.9);
        double endY =  ele.getRect().y + (ele.getSize().height * 0.1);
        //Type of pointer input
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH , "finger");
        //Create sequence object yo add actions
        Sequence swipe = new Sequence(finger, 1);
        //Moving finger into starting position
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), centerX, (int)startY));
        //Finger comes down into contact with screen
        swipe.addAction(finger.createPointerDown(0));
        //Finger moves to end position
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(800),
                PointerInput.Origin.viewport(), centerX, endY));
        //Get up Fingers from screen
        swipe.addAction(finger.createPointerUp(0));
        //Perform the actions
        driver.perform(singletonList(swipe));

         */

        }
    public void scrollToEndAction(){
        boolean canScrollMore;
        do {
            canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture",
                    ImmutableMap.of(
                            "left", 100, "top", 100, "width", 200, "height", 200,
                            "direction", "down",
                            "percent", 3.0
                    ));
        }while (canScrollMore);
    }

    public void scrollToText(String text){
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector())." +
                "scrollIntoView(text(\""+text+"\"));"));
    }
    public void swipeAction(WebElement ele, String direction){
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId",((RemoteWebElement)ele).getId(),
                "direction", direction,
                "percent", 0.75
        ));

    }
    public void dragNDropAction(WebElement element, int pos_x, int pos_y) throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "endX", pos_x,
                "endY", pos_y
        ));
        Thread.sleep(2000);

    }
}
