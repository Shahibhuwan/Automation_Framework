package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Utilities;

import java.util.concurrent.TimeUnit;

public class MacysPage {
    private final WebDriver driver;

    private Utilities utlobj;
    private String searchKwyword = "mac";
    private String itemLocator= "//img[@class=' thumbnailImage']";

    private  String addButtonLocator ="//button[@class='button primary expanded']";
    private  String sizeSelectLocator = "//div[@class='collapsed c-rel']//li[1]";

    public MacysPage(WebDriver driver) {
        Utilities utilities = new Utilities();
        this.driver = driver;
        this.utlobj = utilities;
    }

    String searchLocator = "#globalSearchInputField";


    public void enterSearchKeyword() {
        WebElement searchElement = utlobj.getElement("cssselector", searchLocator, driver);
        searchElement.click();
        searchElement.sendKeys(searchKwyword);
        searchElement.sendKeys(Keys.RETURN);


    }

    public  void clickElement(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement searchElement = utlobj.getElement("xpath", itemLocator, driver);
        searchElement.click();

    }

    public  void addButton(){
        WebElement selectSizeElement = utlobj.getElement("xpath", sizeSelectLocator, driver);
        selectSizeElement.click();

        WebElement addButtonElement = utlobj.getElement("xpath", addButtonLocator, driver);

        addButtonElement.click();

    }


}
