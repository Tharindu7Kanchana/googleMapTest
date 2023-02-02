import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.*;

public class lt extends lbt {
    // Initiating Appium Driver
    public static AndroidDriver<?> driver;

    /*
     * This method is used for initializing the Log4j and Config.properties
     */
    @BeforeSuite
    public static void startSuite() {
        System.out.println("In startSuite in suite");
    }

    /*
     * This method is used for init the Appium Driver and Extent report
     */
    @BeforeTest
    public static void startTest() {
        System.out.println("In startTest in suite");
        try {
            driver = startAppium();
        } catch (Exception e) {
        }
    }

    /*
     * This method is used for initiate the AppiumDriver with caps and connection protocol
     */
    public static AndroidDriver<?> startAppium() {
        System.out.println("In startAppium in suite");

        //Returning the instance of the driver to the parent method
        return driver;
    }

    @BeforeMethod
    public static void openApp() {
        System.out.println("In openApp in suite");
        startTest();
    }

    @AfterClass
    public void tearDown() {
        System.out.println("In tearDown in suite");
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void test(){
        System.out.println(s);
        System.out.println("In testcase in suite");
    }
}