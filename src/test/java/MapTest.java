import com.inivos.util.AppiumTestSupport;
import com.thoughtworks.qdox.model.expression.And;
import io.appium.java_client.*;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.example.config.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

//API_DEMOS TEXT tests
public class MapTest {
    private final String MAP_TEST = "com.google.android.maps.MapsActivity";
    private final String PACKAGE;
    private AndroidDriver<?> driver;

    public MapTest(AndroidDriver<?> driver, String packageName) {
        this.PACKAGE = packageName;
        this.driver = driver;
    }

    @Test
    public void testMap() throws InterruptedException {
        //Open up Google Maps
        driver.startActivity(new Activity(PACKAGE, MAP_TEST));

        //Get my location btn
        AndroidElement center_btn = (AndroidElement) AppiumTestSupport.locateElement(driver, "com.google.android.apps.maps:id/mylocation_button", "id");
        //Check btn status
        String text = center_btn.getAttribute("content-desc");

        //If not location permission granted
        WebDriverWait wait = new WebDriverWait(driver, 10);
        if (text.equalsIgnoreCase("Location services disabled. Enable location services to re-center map to your location.")) {
            AppiumTestSupport.buttonClick(center_btn);

            if (Double.parseDouble(ConfigFactory.getInstance().ANDROID_PLATFORM_VERSION) >= 10.0) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.permissioncontroller:id/grant_dialog")));
                AndroidElement grant_view = (AndroidElement) AppiumTestSupport.locateElement(driver, "com.android.permissioncontroller:id/grant_dialog", "id");
                AppiumTestSupport.buttonClick(driver, "com.android.permissioncontroller:id/permission_allow_one_time_button", "id");
            }
            else{
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.google.android.gms:id/action_bar_root")));
                AndroidElement grant_view = (AndroidElement) AppiumTestSupport.locateElement(driver, "com.google.android.gms:id/action_bar_root", "id");
                AppiumTestSupport.buttonClick(driver, "//android.widget.Button[@text=\"OK\"]", "xpath");
            }
        } else {
            AppiumTestSupport.buttonClick(center_btn);
        }

        //Get Map container Element
        AndroidElement view = (AndroidElement) AppiumTestSupport.locateElement(driver, "com.google.android.apps.maps:id/map_frame", "id");

        //Drop Location pin
        //AppiumTestSupport.longPressOnElementPerform(driver,view);

        TouchAction t1 = AppiumTestSupport.swipeByPercentageOnElement(driver, view, 0.7, 0.4, 0.5, "Vertical", 3000);
        TouchAction t2 = AppiumTestSupport.swipeByPercentageOnElement(driver, view, 0.8, 0.5, 0.5, "Horizontal", 3000);


        AppiumTestSupport.buttonClick(view);

        t1.perform();
        t2.perform();


        //Drop Location pin
        AppiumTestSupport.longPressOnElementPerform(driver, view);

        //AppiumTestSupport.buttonMultipleClick(view, 2);

        //Print out the selected location
        System.out.println(AppiumTestSupport.getElementText(driver, "com.google.android.apps.maps:id/title", "id"));

        //Directions btn
        AndroidElement direction_btn = (AndroidElement) AppiumTestSupport.locateElement(driver, "//android.widget.Button[@text=\"Directions\"]", "xpath");
        AppiumTestSupport.buttonClick(direction_btn);

        //Check Travelling modes
        AppiumTestSupport.buttonClick(driver, "//android.widget.LinearLayout[starts-with(@content-desc,'Driving mode')]", "xpath");
        AppiumTestSupport.customWaitAction(driver, 2);
        AppiumTestSupport.buttonClick(driver, "//android.widget.LinearLayout[starts-with(@content-desc,'Transit mode')]", "xpath");
        AppiumTestSupport.customWaitAction(driver, 2);
        AppiumTestSupport.buttonClick(driver, "//android.widget.LinearLayout[starts-with(@content-desc,'Walking mode')]", "xpath");

        //Check Steps to destination
        AppiumTestSupport.buttonClick(driver, "com.google.android.apps.maps:id/persistent_footer_secondary_button", "id");
        AppiumTestSupport.customWaitAction(driver, 3);
        AppiumTestSupport.buttonClick(driver, "com.google.android.apps.maps:id/persistent_footer_secondary_button", "id");

        //Go to Navigation mode
        AppiumTestSupport.buttonClick(driver, "Start navigation", "AccessibilityId");
        AppiumTestSupport.customWaitAction(driver, 10);

        for (String s : AppiumTestSupport.getAllAvailableContexts(driver)) {
            System.out.println(s);
        }
        AndroidElement btn_sheet = (AndroidElement) AppiumTestSupport.locateElement(driver, "com.google.android.apps.maps:id/home_bottom_sheet_container", "id");
        AppiumTestSupport.pressOnCoordinatePerform(driver, AppiumTestSupport.getCoordinateOnElementPerc(btn_sheet, 0.78, 0.8));
        AppiumTestSupport.customWaitAction(driver, 4);

        //Re center
        AppiumTestSupport.pressBackButtonAndroid(driver);

        //Go back
        AppiumTestSupport.pressBackButtonAndroid(driver);
        AppiumTestSupport.pressBackButtonAndroid(driver);
    }

    @Test
    public void testExplore() throws InterruptedException {
        //Open up Google Maps
        driver.startActivity(new Activity(PACKAGE, MAP_TEST));

        //Get my location btn
        AndroidElement center_btn = (AndroidElement) AppiumTestSupport.locateElement(driver, "com.google.android.apps.maps:id/mylocation_button", "id");
        //Check btn status
        String text = center_btn.getAttribute("content-desc");

        //If not location permission granted
        WebDriverWait wait = new WebDriverWait(driver, 20);
        if (text.equalsIgnoreCase("Location services disabled. Enable location services to re-center map to your location.")) {
            AppiumTestSupport.buttonClick(center_btn);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.permissioncontroller:id/grant_dialog")));
            AndroidElement grant_view = (AndroidElement) AppiumTestSupport.locateElement(driver, "com.android.permissioncontroller:id/grant_dialog", "id");
            AppiumTestSupport.buttonClick(driver, "com.android.permissioncontroller:id/permission_allow_one_time_button", "id");
        } else {
            AppiumTestSupport.buttonClick(center_btn);
        }
        //Draw Explore Card
        AndroidElement expore_view = (AndroidElement) AppiumTestSupport.locateElement(driver, "com.google.android.apps.maps:id/scrollable_card_stream_container", "id");
        AppiumTestSupport.pressOnCoordinatePerform(driver, AppiumTestSupport.getCoordinateOnElementPerc(expore_view, 0.5, 0.1));
        AppiumTestSupport.customWaitAction(driver, 2);

        //Swipe down to hotel carousel
        AppiumTestSupport.swipeByPercentageOnElementPerform(driver, expore_view, 0.95, 0.05, 0.5, "Vertical", 1000);
        AppiumTestSupport.swipeByPercentageOnElementPerform(driver, expore_view, 0.3, 0.9, 0.5, "Vertical", 3000);

        //Hotel Carousel element
        AndroidElement hotel_carousel = (AndroidElement) AppiumTestSupport.locateElement(driver, "com.google.android.apps.maps:id/hotels_carousel", "id");

        //Swipe through the Carousel
        AppiumTestSupport.swipeByPercentageOnElementPerform(driver, hotel_carousel, 0.95, 0.05, 0.5, "Horizontal", 1000);

        //Go to 3rd Item
        AppiumTestSupport.locateElement(driver, "com.google.android.apps.maps:id/recycler_view", "id").findElementByXPath("//androidx.cardview.widget.CardView[3]").click();

        //Directions btn
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.Button[@text=\"Directions\"]")));

        AndroidElement direction_btn = (AndroidElement) AppiumTestSupport.locateElement(driver, "//android.widget.Button[@text=\"Directions\"]", "xpath");
        AppiumTestSupport.buttonClick(direction_btn);


        //Check Travelling modes
        AppiumTestSupport.buttonClick(driver, "//android.widget.LinearLayout[starts-with(@content-desc,'Transit mode')]", "xpath");
        AppiumTestSupport.customWaitAction(driver, 2);
        AppiumTestSupport.buttonClick(driver, "//android.widget.LinearLayout[starts-with(@content-desc,'Walking mode')]", "xpath");
        AppiumTestSupport.customWaitAction(driver, 2);
        AppiumTestSupport.buttonClick(driver, "//android.widget.LinearLayout[starts-with(@content-desc,'Driving mode')]", "xpath");

        //Check Steps to destination
        AppiumTestSupport.buttonClick(driver, "com.google.android.apps.maps:id/persistent_footer_secondary_button", "id");
        AppiumTestSupport.swipeByPercentagePerform(driver, 0.5, 0.1, 0.5, "Vertical", 3000);
        AppiumTestSupport.customWaitAction(driver, 3);
        AppiumTestSupport.buttonClick(driver, "com.google.android.apps.maps:id/persistent_footer_secondary_button", "id");

        //Go to Navigation mode
        AppiumTestSupport.buttonClick(driver, "Start navigation", "AccessibilityId");
        AppiumTestSupport.customWaitAction(driver, 10);

        for (String s : AppiumTestSupport.getAllAvailableContexts(driver)) {
            System.out.println(s);
        }
        AndroidElement btn_sheet = (AndroidElement) AppiumTestSupport.locateElement(driver, "com.google.android.apps.maps:id/home_bottom_sheet_container", "id");
        AppiumTestSupport.pressOnCoordinatePerform(driver, AppiumTestSupport.getCoordinateOnElementPerc(btn_sheet, 0.78, 0.8));
        AppiumTestSupport.customWaitAction(driver, 4);

        //Re center
        AppiumTestSupport.pressBackButtonAndroid(driver);

        //Go back
        AppiumTestSupport.pressBackButtonAndroid(driver);
        AppiumTestSupport.pressBackButtonAndroid(driver);
    }
}