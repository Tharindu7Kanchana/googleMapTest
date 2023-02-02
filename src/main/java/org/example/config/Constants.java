package org.example.config;

public interface Constants {
    /**
     * Common properties ------------
     */
    String PLATFORM = "Android";
    String APP_PATH = "app/ApiDemos-debug.apk";
    String APP_NAME = "ApiDemos-debug";
    String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";
    Boolean AUTO_LAUNCH = false;
    int NEW_COMMAND_TIMEOUT = 500;
    long IMPLICIT_WAIT = 10;

    /**
     * ANDROID properties ------------
     */
    String ANDROID_PLATFORM_VERSION = "11";
//    String ANDROID_PLATFORM_VERSION = "5.1";
    String APP_PACKAGE = "io.appium.android.apis";
    String APP_ACTIVITY = ".ApiDemos";

    String ANDROID_UDID ="emulator-5554";

    //    String ANDROID_UDID = "42007f6cce6fc4f5";
    String ANDROID_DEVICE_NAME ="emulator-5554";
 String AVD = "Pixel_2_API_30";
    //String AVD = "S_9_plus_API_27";
    String AUTOMATION_NAME = "UIAutomator2";

    /**
     * IOS properties ------------
     */
    String IOS_PLATFORM_VERSION = "12.0.1";
    String IOS_DEVICE_NAME = "iPhone 8";
    String IOS_BUNDLE_ID = "com.invoiceapp";
    String IOS_UDID = "3jtn3j4n3ijn3ji4nrj34inrj34nr34nrk";
    String IOS_AUTOMATION_NAME = "XCuiTest";

}
