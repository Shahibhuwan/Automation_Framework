package utilities;

import com.aventstack.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {
    public WebElement getElement(String locatorType, String locatorValue, WebDriver driver) {
        WebElement element = null;
        switch(locatorType.toLowerCase()) {
            case "id":
                element = driver.findElement(By.id(locatorValue));
                break;
            case "name":
                element = driver.findElement(By.name(locatorValue));
                break;
            case "class":
                element = driver.findElement(By.className(locatorValue));
                break;
            case "tagname":
                element = driver.findElement(By.tagName(locatorValue));
                break;
            case "linktext":
                element = driver.findElement(By.linkText(locatorValue));
                break;
            case "cssselector":
                element = driver.findElement(By.cssSelector(locatorValue));
                System.out.println(element);
                break;
            case "xpath":
                element = driver.findElement(By.xpath(locatorValue));
                break;
            default:
                System.out.println("Invalid locator type.");
        }
        return element;
    }

    public String screenCapture(WebDriver driver) throws IOException {
        System.out.println("I am inside scrrenshot");
        String currentDir = System.getProperty("user.dir");
        System.out.println(currentDir);
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            File screenshotName = new File(currentDir + "\\Screenshot\\" + timestamp + ".png");
            String absoulutePath=  screenshotName.getAbsolutePath();
            System.out.println(screenshotName);
            FileUtils.copyFile(scrFile, screenshotName);


//            test.log("<br><img src='" + screenshotName + "'height ='400' width='400' width='400/><br>");
        return absoulutePath;

        }


    }


