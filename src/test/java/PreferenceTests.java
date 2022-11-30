import com.inivos.util.AppiumTestSupport;
import com.inivos.util.ElementTestSupport;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.testng.Assert;
import org.testng.annotations.Test;

//API_DEMOS TEXT tests
public class PreferenceTests {
    private final String CHECKBOX_TEST = ".preference.SwitchPreference";
    private final String PACKAGE;
    private AndroidDriver<?> driver;

    public PreferenceTests(AndroidDriver<?> driver, String packageName) {
        this.PACKAGE = packageName;
        this.driver = driver;
    }

    @Test
    public void testCheckBox() throws InterruptedException {
        driver.startActivity(new Activity(PACKAGE, CHECKBOX_TEST));
        String checked = ElementTestSupport.CheckBox(driver, "android:id/checkbox", "id");
        Assert.assertTrue(Boolean.parseBoolean(checked));
    }

    @Test
    public void testSwitch() throws InterruptedException {
        driver.startActivity(new Activity(PACKAGE, CHECKBOX_TEST));
        String checked = ElementTestSupport.Switch(driver, "//android.widget.ListView/android.widget.LinearLayout[2]", "xpath");
        Assert.assertFalse(Boolean.parseBoolean(checked));
    }
}