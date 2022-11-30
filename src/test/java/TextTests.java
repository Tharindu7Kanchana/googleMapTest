import com.inivos.util.AppiumTestSupport;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import org.openqa.selenium.Point;
import org.testng.Assert;
import org.testng.annotations.Test;

//API_DEMOS TEXT tests
public class TextTests {
    private AndroidDriver<?> driver;
    private final String KEY_EVENT_ACTIVITY = ".text.KeyEventText";
    private final String Text_Link_ACTIVITY = ".text.Link";

    private final String LOG_TEXT_ACTIVITY = ".text.LogTextBox1";
    private final String PACKAGE;

    public TextTests(AndroidDriver<?> driver, String packageName){
        this.PACKAGE = packageName;
        this.driver = driver;
    }

    @Test
    public void testKeyPress() throws StringIndexOutOfBoundsException, InterruptedException {
        //Opening KeyEventText Activity
        driver.startActivity(new Activity(PACKAGE,KEY_EVENT_ACTIVITY));

        //Click on the TextView
        AndroidElement TextView = (AndroidElement) AppiumTestSupport.locateElement(driver,"io.appium.android.apis:id/text","id");
        AppiumTestSupport.pressAndroidKey(driver,AndroidKey.A);
        String text = AppiumTestSupport.getElementText(TextView);
        String actual = text.substring(0,12);
        System.out.println(actual);
        String expected = "[keycode=29]";
        Assert.assertEquals(actual,expected);
        //clear text
        AppiumTestSupport.buttonClick(AppiumTestSupport.locateElement(driver,"io.appium.android.apis:id/clear","id"));
    }

    @Test
    public void testTextLinksBrowser(){
        //Opening Text Linkfy Activity
        driver.startActivity(new Activity(PACKAGE,Text_Link_ACTIVITY));
//        AndroidElement link = (AndroidElement) driver.findElementById("io.appium.android.apis:id/text1");
//        link.findElementByXPath("//*[contains(.,'http://google.com')]").click();
        //Tap http:www.google.com link
        AppiumTestSupport.tapOnCoordinate(driver,new Point(246,400));

        String expected = "org.chromium.chrome.browser.firstrun.FirstRunActivity";
        String actual = driver.currentActivity();
        Assert.assertEquals(actual,expected);
    }

    @Test
    public void testTextLinksPhone(){
        //Opening Text Linkfy Activity
        driver.startActivity(new Activity(PACKAGE,Text_Link_ACTIVITY));
//        AndroidElement link = (AndroidElement) driver.findElementById("io.appium.android.apis:id/text1");
//        link.findElementByXPath("//*[contains(.,'http://google.com')]").click();
        //tap on a phone number
        AppiumTestSupport.tapOnCoordinate(driver,new Point(333,518));
        String expected = ".main.impl.MainActivity";
        String actual = AppiumTestSupport.getCurrentActivity(driver);
        Assert.assertEquals(actual,expected);
    }

    @Test
    public void testLogTexBox() throws InterruptedException {
        driver.startActivity(new Activity(PACKAGE,LOG_TEXT_ACTIVITY));

        AndroidElement AddButton = (AndroidElement) AppiumTestSupport.locateElement(driver,"io.appium.android.apis:id/add","id");
        AppiumTestSupport.buttonMultipleClick(AddButton,2);

        AndroidElement LogView = (AndroidElement) AppiumTestSupport.locateElement(driver,"io.appium.android.apis:id/text","id");
        String text = AppiumTestSupport.getElementText(LogView);
        int actual = text.split("\n").length;
        Assert.assertEquals(actual,2);

        AndroidElement DoNothingButton = (AndroidElement) driver.findElementByAccessibilityId("Do nothing");
        DoNothingButton.click();
        Assert.assertEquals(actual,2);
    }
}
