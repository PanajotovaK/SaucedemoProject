package Tests;

import Base.BaseTest;
import Pages.LoginPage;
import Base.ExcelReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() throws IOException {

        loginPage = new LoginPage();
        excelReader = new ExcelReader("C:\\Users\\Kristina\\Desktop\\it_bootcamp\\sauceDemo.xlsx");

        driver.manage().window().maximize();
        driver.navigate().to("https://www.saucedemo.com/");
    }

    @Test
    public void standard_userCanLogin() {

        String username = excelReader.getStringData("Sheet1", 0,0);
        String password = excelReader.getStringData("Sheet1", 0,1);


        loginPage.inputUsername(username);
        loginPage.inputPassword(password);
        loginPage.clickOnLoginButton();

        WebElement shoppingCart = driver.findElement(By.cssSelector("[data-test='shopping-cart-link']"));
        Assert.assertTrue(shoppingCart.isDisplayed());

        WebElement demoLogo = driver.findElement(By.cssSelector(".app_logo"));
        Assert.assertTrue(demoLogo.isDisplayed());


    }

    @Test
    public void problem_userCanLogin() {

        String username1 = excelReader.getStringData("Sheet1", 1,0);
        String password = excelReader.getStringData("Sheet1", 0,1);

        loginPage.inputUsername(username1);
        loginPage.inputPassword(password);
        loginPage.clickOnLoginButton();

        WebElement shoppingCart = driver.findElement(By.cssSelector("[data-test='shopping-cart-link']"));
        Assert.assertTrue(shoppingCart.isDisplayed());

        WebElement demoLogo = driver.findElement(By.cssSelector(".app_logo"));
        Assert.assertTrue(demoLogo.isDisplayed());
    }

    @Test
    public void performance_glitch_userCanLogin() {

        String username2 = excelReader.getStringData("Sheet1", 2,0);
        String password = excelReader.getStringData("Sheet1", 0,1);

        loginPage.inputUsername(username2);
        loginPage.inputPassword(password);
        loginPage.clickOnLoginButton();

        WebElement shoppingCart = driver.findElement(By.cssSelector("[data-test='shopping-cart-link']"));
        Assert.assertTrue(shoppingCart.isDisplayed());

        WebElement demoLogo = driver.findElement(By.cssSelector(".app_logo"));
        Assert.assertTrue(demoLogo.isDisplayed());

    }

    @Test
    public void error_userCanLogin() {

        String username3 = excelReader.getStringData("Sheet1", 3,0);
        String password = excelReader.getStringData("Sheet1", 0,1);


        loginPage.inputUsername(username3);
        loginPage.inputPassword(password);
        loginPage.clickOnLoginButton();

        WebElement shoppingCart = driver.findElement(By.cssSelector("[data-test='shopping-cart-link']"));
        Assert.assertTrue(shoppingCart.isDisplayed());

        WebElement demoLogo = driver.findElement(By.cssSelector(".app_logo"));
        Assert.assertTrue(demoLogo.isDisplayed());

    }

    @Test
    public void visual_userCanLogin() {

        String username4 = excelReader.getStringData("Sheet1", 4,0);
        String password = excelReader.getStringData("Sheet1", 0,1);

        loginPage.inputUsername(username4);
        loginPage.inputPassword(password);
        loginPage.clickOnLoginButton();

        WebElement shoppingCart = driver.findElement(By.cssSelector("[data-test='shopping-cart-link']"));
        Assert.assertTrue(shoppingCart.isDisplayed());

        WebElement demoLogo = driver.findElement(By.cssSelector(".app_logo"));
        Assert.assertTrue(demoLogo.isDisplayed());


    }


    @Test

    public void userCannotLoginWithInvalidUsername () {

        String username5 = excelReader.getStringData("Sheet1", 5,0);
        String password = excelReader.getStringData("Sheet1", 0,1);

        loginPage.inputUsername(username5);
        loginPage.inputPassword(password);
        loginPage.clickOnLoginButton();

        WebElement errorMsg = driver.findElement(By.cssSelector("h3[data-test='error']"));
        Assert.assertTrue(errorMsg.isDisplayed());
        Assert.assertTrue(errorMsg.getText().contains("Epic sadface: Username and password do not match any user in this service"));
    }

    @Test

    public void userCannotLoginWithInvalidPassword () {

        String username6 = excelReader.getStringData("Sheet1", 0,0);
        String password1 = excelReader.getStringData("Sheet1", 1,1);

        loginPage.inputUsername(username6);
        loginPage.inputPassword(password1);
        loginPage.clickOnLoginButton();

        WebElement errorMsg = driver.findElement(By.cssSelector("h3[data-test='error']"));
        Assert.assertTrue(errorMsg.isDisplayed());
        Assert.assertTrue(errorMsg.getText().contains("Epic sadface: Username and password do not match any user in this service"));

    }

    @Test

    public void LockedUserTest () {

        String username7 = excelReader.getStringData("Sheet1", 6, 0);
        String password = excelReader.getStringData("Sheet1", 0,1);

        loginPage.inputUsername(username7);
        loginPage.inputPassword(password);
        loginPage.clickOnLoginButton();

        WebElement  errorMsgLockedUser = driver.findElement(By.cssSelector("h3[data-test='error']"));
        Assert.assertTrue(errorMsgLockedUser.isDisplayed());
        Assert.assertTrue(errorMsgLockedUser.getText().contains("Epic sadface: Sorry, this user has been locked out."));
    }


}
