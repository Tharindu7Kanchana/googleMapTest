import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.inivos.util.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.example.config.ConfigFactory;
import org.example.config.Constants;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import org.testng.log4testng.Logger;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestSuite extends BaseTest {

    public static Logger log;
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest test;

    // Initiating Appium Driver
    public static AndroidDriver<?> driver;

    /*
     * This method is used for initializing the Log4j and Config.properties
     */
    @BeforeSuite
    public static void startSuite() {
        log = Logger.getLogger(TestSuite.class);
        log.info("Test Started successfully");
    }

    /*
     * This method is used for init the Appium Driver and Extent report
     */
    @BeforeTest
    public static void startTest() {

        // Getting the driver declared using our Capabilities
        try {
            driver = startAppium();
        } catch (Exception e) {
            log.fatal("Driver is not Initiated as Expected" + e.getMessage());
        }
        // LOC for initializing the Extent Report
        log.info("Initializing the Extent Report");
        try {
            log.info("Extent report is available under the directory " + System.getProperty("user.dir") + "/Reports/");
            // starting the HTML report
            htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/reports/testReport.html");
            // To Initialize the reporters
            extent = new ExtentReports();
            // attach only HtmlReporter
            extent.attachReporter(htmlReporter);
            // Providing internal name for the report
            htmlReporter.config().setReportName("Test Report");
            // To create a test case and steps, we need to use reference variable for
            // "ExtentTest" class.
            // This test reference will help us to create test.
            test = extent.createTest("testcase Name");
        } catch (Exception e) {
            log.error("Unable to Initialize the Extent Report" + e.getMessage());
        }
    }


    /*
     * This method is used for initiate the AppiumDriver with caps and connection protocol
     */
    public static AndroidDriver<?> startAppium() {
        // Initializing the Appium driver
        Constants constants = ConfigFactory.getInstance();
        try {
            DesiredCapabilities cap = new DesiredCapabilities();
            // All Capability values are retrieved from Config.properties file.
            cap.setCapability(MobileCapabilityType.PLATFORM_NAME, constants.PLATFORM);
            cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, constants.ANDROID_PLATFORM_VERSION);
            cap.setCapability(MobileCapabilityType.UDID, constants.ANDROID_UDID);
            cap.setCapability(MobileCapabilityType.DEVICE_NAME, constants.ANDROID_DEVICE_NAME);
            cap.setCapability(AndroidMobileCapabilityType.AVD, constants.AVD);
            cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, constants.APP_ACTIVITY);
            cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, constants.APP_PACKAGE);
            cap.setCapability(AndroidMobileCapabilityType.AUTO_LAUNCH, constants.AUTO_LAUNCH);
            cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, constants.NEW_COMMAND_TIMEOUT);
            cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, constants.AUTOMATION_NAME);

            // Declaring the driver as "Android driver" with the Host and Port number to communicate with Appium desktop
            driver = new AndroidDriver<AndroidElement>(new URL(constants.APPIUM_URL), cap);
            driver.manage().timeouts().implicitlyWait(constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
            //Printing the driver details in Log file
            log.info("Driver declared successfully : " + driver);
        } catch (Exception e) {
            driver = null;
            log.fatal("Driver declaration failed : " + driver);
            log.fatal(e.getStackTrace());
        }
        //Returning the instance of the driver to the parent method
        return driver;
    }

    @BeforeMethod
    public static void openApp() {
        startTest();
        log.info("Test case started successfully");
        log.info("Trying to launch the Application under Test");
        try {
            driver.launchApp();
            log.info("Application launched successfully");
        } catch (Exception e) {
            log.info("Unable to launch the application :" + e.getMessage());
        }
    }

//    @Test
//    public void testButton(){
//        System.out.println(AppiumTestSupport.getCurrentActivity(driver));
//    }

    @Test
    public void viewTests() throws InterruptedException {
        ViewTests test = new ViewTests(driver, ConfigFactory.getInstance().APP_PACKAGE);
        //Views AutoComplete
        //AutoComplete ScreenTop Test
        test.testAutoCompleteScreenTop();
        //AutoComplete Scroll Test
        test.testAutoCompleteScroll();
        //AutoComplete Multiple Test
        test.testAutoCompleteMultiple();

        //Views ToggleButton
        test.testToggleButton();

        //Views Chronometer
        //Start,Stop,Reset Time Test
        test.testChronometerStart();

        //Views DateWidget
        //Date Widget Dialog date,time,spinner
        test.testDateWidget();

        //Views Drag and Drop
        test.testDragDrop();

        //Views Expandable Lists
        test.testExpandableList();

        //Views Image Switcher
        test.testImageSwitcher();

        //Views ScrollBars Style
        test.testScrollBars();

        //Views Rating Bar
        test.testRatingBar();

        //Views Sliding Picker
        test.testSlidingPicker();

        //Views Splitting Touches
        test.testSplittingTouches();

        //Views Seek Bar
        test.testSeekBar();

        //Alert Dialog
        test.testAlertDialog();

        //Views Search bar filter
        test.testSearchFilter();

        //Views List Selection
        test.testSelectList();
    }

    @Test
    public void preferenceTests() throws InterruptedException {
        PreferenceTests test = new PreferenceTests(driver, ConfigFactory.getInstance().APP_PACKAGE);
        test.testCheckBox();
        test.testSwitch();
    }

    @Test
    public void appTests() throws InterruptedException {
        AppTests test = new AppTests(driver, ConfigFactory.getInstance().APP_PACKAGE);
        test.testNotifications();
    }

    @Test
    public void camTests() throws InterruptedException {
        CameraTest test = new CameraTest(driver, "com.android.camera2");
//        test.testCamera();
        test.ShutterTest();
        test.GalleryTestSwipe();
    }

    @Test
    public void mapTests() throws InterruptedException {
        MapTest test = new MapTest(driver, "com.google.android.apps.maps");
        test.testMap();
        test.testExplore();
    }


    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}