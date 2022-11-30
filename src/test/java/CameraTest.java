import com.inivos.util.AppiumTestSupport;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.touch.WaitOptions;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

//API_DEMOS TEXT tests
public class CameraTest {
    private final String CAMERA_TEST = "com.android.camera.CameraLauncher";
    private final String PACKAGE;
    private AndroidDriver<?> driver;

    public CameraTest(AndroidDriver<?> driver, String packageName) {
        this.PACKAGE = packageName;
        this.driver = driver;
    }

    @Test
    public void testCamera() throws InterruptedException {
        driver.startActivity(new Activity(PACKAGE, CAMERA_TEST));
    }

    @Test
    public void ShutterTest() throws InterruptedException {
        driver.startActivity(new Activity(PACKAGE, CAMERA_TEST));
        //Shutter Element
        AndroidElement shutter = (AndroidElement) AppiumTestSupport.locateElement(driver, "Shutter", "AccessibilityId");

        //Take 3 Photos
        AppiumTestSupport.buttonMultipleClick(shutter, 3);

        //Goto Gallery via Thumbnail Icon and explore
        AppiumTestSupport.buttonClick(driver, "com.android.camera2:id/rounded_thumbnail_view", "id");
        AppiumTestSupport.swipeByPercentagePerform(driver, 0.9, 0.1, 0.5, "Horizontal", 3000);

        //Delete Element
        AndroidElement delete = (AndroidElement) AppiumTestSupport.locateElement(driver, "Delete", "AccessibilityId");

        //Delete 2 Photos
        AppiumTestSupport.buttonMultipleClick(delete, 2);

        //Return to camera
        AppiumTestSupport.buttonClick(driver, "Navigate up", "AccessibilityId");
    }

    @Test
    public void GalleryTestSwipe() {
        driver.startActivity(new Activity(PACKAGE, CAMERA_TEST));

        TouchAction t = AppiumTestSupport.swipeByPercentage(driver, 0.9, 0.1, 0.5, "Horizontal", 2000);
        TouchAction t2 = AppiumTestSupport.swipeByPercentage(driver, 0.9, 0.1, 0.5, "Horizontal", 2000);

        t.perform();
        AppiumTestSupport.customWaitAction(driver, 2);
        t2.perform();
    }
}