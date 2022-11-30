import com.inivos.util.AppiumTestSupport;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

//API_DEMOS TEXT tests
public class AppTests {
    private AndroidDriver<?> driver;

    private final String APP_NOTIFICATION_TEST = ".app.IncomingMessage";
    private final String PACKAGE;

    public AppTests(AndroidDriver<?> driver, String packageName){
        this.PACKAGE = packageName;
        this.driver = driver;
    }

    @Test
    public void testNotifications() throws InterruptedException {
        driver.startActivity(new Activity(PACKAGE,APP_NOTIFICATION_TEST));
        AppiumTestSupport.buttonClick(driver,"Show App Notification","AccessibilityId");
        AppiumTestSupport.drawQuickSettings(driver);

        MobileElement element = AppiumTestSupport.locateElement(driver,"//*[contains(@text,'API Demos')]/../..","xpath");

        AppiumTestSupport.longPressOnElementPerform(driver,element);
        AppiumTestSupport.longPressOnElementPerform(driver,element);
        AppiumTestSupport.pressOnElementPerform(driver,element);

        MobileElement header = AppiumTestSupport.locateElement(driver,"android:id/action_bar","id");
        String text = header.findElement(new By.ByXPath("//android.widget.TextView[1]")).getText();
        Assert.assertEquals(text,"App/Notification/IncomingMessageView");
    }
}