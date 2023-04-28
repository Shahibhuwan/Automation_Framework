package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Utilities;

public class LoginPage {


    Utilities utilitiesObj = new Utilities();

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private String usernameLocator = "//input[@placeholder='Please enter your Phone Number or Email']";
    private String passwordLocator = "input[placeholder='Please enter your password']";

    private String errorMessageLocator = "//div[@class='next-feedback next-feedback-error next-feedback-toast next-feedback-medium next-feedback-title-content next-overlay-inner animated pulse next-feedback-wrapper mod-fusion-toast next-position-cc']/div[2]";

//    private  String validationMessageLocator = "span[data-spm-anchor-id='a2a0e.login_signup.0.i7.46e76af7YOdRsA']";
    private String validationMessageLocator="//div[@class='mod-input error mod-login-input-loginName mod-input-loginName']//span[1]";
    private String username = "bhuwan";
    private String password = "bhuwan123";
    private String loginLocator = "//button[@type='submit']";


    public void setUsername() {

        WebElement usernameElement = utilitiesObj.getElement("xpath", usernameLocator, driver);
        usernameElement.click();
        usernameElement.sendKeys(username);
    }

    public void setPassword() {
        WebElement passwordElement = utilitiesObj.getElement("cssSelector", passwordLocator, driver);
        passwordElement.sendKeys(passwordLocator);

    }

    public void clickLogin() {
        WebElement loginElement = utilitiesObj.getElement("xpath", loginLocator, driver);
        loginElement.click();
    }

    public String getErrorMessage() {
        //Explicit wait
        WebElement errorElement = utilitiesObj.getElement("xpath", loginLocator, driver);
        String errorMessage = errorElement.getText();
        return errorMessage;

    }
    public String getValidationMessage() {
        WebElement validationElement = utilitiesObj.getElement("xpath", validationMessageLocator, driver);
        String ValidationMessage = validationElement.getText();
        return ValidationMessage;

    }

}
