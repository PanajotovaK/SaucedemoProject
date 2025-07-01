package Tests;

import Base.BaseTest;
import Pages.HomePage;
import Pages.LoginPage;
import com.fasterxml.jackson.databind.JsonSerializable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LogoutTest extends BaseTest {

    @BeforeMethod

    public void pageSetUp2 () {

        loginPage = new LoginPage();
        homePage = new HomePage();

        driver.manage().window().maximize();
        driver.navigate().to("https://www.saucedemo.com/");
        loginPage.inputUsername("standard_user");
        loginPage.inputPassword("secret_sauce");
        loginPage.clickOnLoginButton();

    }

    @Test

    public void userCanLogout () {

        homePage.ClickOnBurgerMenu();
        homePage.clickOnLogoutButton();

        WebElement loginButton = driver.findElement(By.id("login-button"));
        Assert.assertTrue(loginButton.isDisplayed());



    }
}
