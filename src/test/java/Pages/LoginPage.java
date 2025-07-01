package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class LoginPage extends BaseTest {


    public LoginPage () {
        PageFactory.initElements(driver, this);
    }

    @FindBy (id = "user-name")
    public WebElement usernameField;


    public void inputUsername (String username) {

        usernameField.click();
        usernameField.sendKeys(username);
    }

    @FindBy (id = "password")
    public WebElement passwordField;

    public void inputPassword (String password) {


        passwordField.click();
        passwordField.sendKeys(password);
    }

    @FindBy (id = "login-button")
    public WebElement loginButton;

    public void clickOnLoginButton () {
        loginButton.click();
    }




}
