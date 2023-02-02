import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;

public abstract class lbt {

    public static String s = null;

    @BeforeMethod
    @Parameters(value = {"platform"})
    public void beforeMethodTest(@Optional("Android") String platform, Method testMethod) {
        System.out.println("In beforeMethodTest in base param platform - " + platform + ", param testMethod - " + testMethod.getName() + ", " + testMethod.getClass());
        s = "base";
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("In beforeTest in base");
    }

    @BeforeSuite
    public void globalSetup() throws IOException {
        System.out.println("In globalSetup in base");
    }

    @AfterSuite
    public void globalTearDown() {
        System.out.println("In globalTearDown in base");
    }

}