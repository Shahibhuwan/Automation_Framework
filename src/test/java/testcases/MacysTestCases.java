package testcases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.MacysPage;

public class MacysTestCases {
    private WebDriver driver;
    public ExtentSparkReporter htmlReporter;
    public ExtentReports reports;
    public ExtentTest test;

    public void startReport() {
        String currentDir = System.getProperty("user.dir");
        htmlReporter = new ExtentSparkReporter(currentDir + "\\TestsReports.html");
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
//            options.addArguments("user-data-dir=C:/Users/user_name/AppData/Local/Google/Chrome/User Data");

            options.addArguments(String.format("user-agent=%s", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Safari/537.36"));
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--disable-web-security");
            options.addArguments("--disable-blink-features=AutomationControlled");
//            System.setProperty("webdriver.chrome.whitelistedIps", "192.168.1.6");
            WebDriverManager.chromedriver().setup();
            System.out.println("Setting up driver");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();

            driver.navigate().to("https://www.macys.com/shop/for-the-home?id=22672&cm_sp=intl_hdr-_-home-_-22672_home");
//            driver.navigate().to("https://www.macys.com/shop/product/skinnydip-london-harper-small-crossbody-satchel?ID=15226401&CategoryID=26846&swatchColor=Pink%20Pink");
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

    @Test
    public void searchAndVerify() {
        MacysPage macysPage = new MacysPage(driver);
        macysPage.enterSearchKeyword();
        macysPage.clickElement();
        macysPage.addButton();

    }
}
