package testcases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.LoginPage;
import utilities.Utilities;

import javax.sound.sampled.Line;
import java.io.IOException;


public class LoginTestCases {


    private WebDriver driver;
    public ExtentSparkReporter htmlReporter;
    public ExtentReports reports;
    public ExtentTest test;

    public void startReport() {
        String currentDir = System.getProperty("user.dir");
        htmlReporter = new ExtentSparkReporter(currentDir+"\\TestsReports.html");
        reports = new ExtentReports();
        reports.attachReporter(htmlReporter);
        reports.setSystemInfo("OS", "Window11");
        reports.setSystemInfo("Browser", "Chrome");

        //configuration to change look and feel
        htmlReporter.config().setDocumentTitle("Negative test cases");
        htmlReporter.config().setReportName("Test Report");
        htmlReporter.config().setTheme(Theme.STANDARD);
//        htmlReporter.config().setTimeStampFormat();
    }


    @BeforeTest
    public void setUp() {
        try {
            // creates a toggle for the given test, adds all log events under it
            System.out.println("Setting up driver");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            System.setProperty("webdriver.chrome.whitelistedIps", "192.168.1.6");
            WebDriverManager.chromedriver().setup();
            System.out.println("Setting up driver");
            driver = new ChromeDriver(options);
            driver.get("https://member.daraz.com.np/user/login");
            driver.manage().window().maximize();
            startReport();
            System.out.println("Driver is set up");
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    @AfterTest
    public void tearDown() {
        reports.flush();
        driver.close();
    }
// to check after each scenario and after every scenario we need to flush
    @AfterTest
    public void getTestResult(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Test Failure");

        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test Pass");

        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.PASS, "Test Skipped}");

        }
    }

    @Test
    public void EmptyCredential() throws IOException {

        test = reports.createTest("First Scenario: Empty credential login");

        test.log(Status.INFO,"Redirected to login page");
        LoginPage loginobj = new LoginPage(driver);
        Utilities utilitiesobj = new Utilities();
        test.log(Status.INFO,"Username Empty");
        test.log(Status.INFO,"Password Empty");
        loginobj.clickLogin();
        test.log(Status.INFO,"Clicked on the Login button");
        String message = loginobj.getValidationMessage();

        Assert.assertEquals(message, "You can't leave this empty.");
        utilitiesobj.screenCapture(driver);
        String pathScreenshot= utilitiesobj.screenCapture(driver);
        test.log(Status.PASS, "Login Passed", MediaEntityBuilder.createScreenCaptureFromPath(pathScreenshot).build());
    }

    @Test
    public void invalidCredential() throws IOException {

        test = reports.createTest("Scenod Scenario: Invalid credential login");


        System.out.println("i am inisde test");

        test.log(Status.INFO,"Redirected to login page");

        LoginPage loginobj = new LoginPage(driver);
        Utilities utilitiesobj = new Utilities();
        loginobj.setUsername();
        test.log(Status.INFO,"Username provided");
        loginobj.setPassword();
        test.log(Status.INFO,"Password Provided");
        loginobj.clickLogin();
        test.log(Status.INFO,"Clicked on the Login button");
        String message = loginobj.getErrorMessage();
        System.out.println(message);
        utilitiesobj.screenCapture(driver);
        String pathScreenshot= utilitiesobj.screenCapture(driver);
        System.out.println(pathScreenshot);
        test.log(Status.PASS, "Login Passed", MediaEntityBuilder.createScreenCaptureFromPath(pathScreenshot).build());

        Assert.assertEquals(message, "Please enter a valid phone number");


    }




}
