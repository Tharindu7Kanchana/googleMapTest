import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.inivos.util.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.example.config.ConfigFactory;
import org.example.config.Constants;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import org.testng.log4testng.Logger;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class IosTestSuite extends BaseTest {

    public static Logger log;
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest test;

    // Initiating Appium Driver
    public static IOSDriver<?> driver;

    /*
     * This method is used for initializing the Log4j and Config.properties
     */
    @BeforeSuite
    public static void startSuite() {
        log = Logger.getLogger(IosTestSuite.class);
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
    public static IOSDriver<?> startAppium() {
        // Initializing the Appium driver
        Constants constants = ConfigFactory.getInstance();
        try {
            DesiredCapabilities cap = new DesiredCapabilities();
            // All Capability values are retrieved from Config.properties file.
            cap.setCapability(MobileCapabilityType.PLATFORM_NAME, constants.PLATFORM);
            cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, constants.IOS_PLATFORM_VERSION);
            cap.setCapability(MobileCapabilityType.DEVICE_NAME, constants.IOS_DEVICE_NAME);
            cap.setCapability(MobileCapabilityType.UDID,constants.IOS_UDID);
            cap.setCapability(IOSMobileCapabilityType.BUNDLE_ID,constants.IOS_BUNDLE_ID);
            cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, constants.NEW_COMMAND_TIMEOUT);
            cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, constants.IOS_AUTOMATION_NAME);

            // Declaring the driver as "Android driver" with the Host and Port number to communicate with Appium desktop
            driver = new IOSDriver<IOSElement>(new URL(constants.APPIUM_URL), cap);
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



    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}