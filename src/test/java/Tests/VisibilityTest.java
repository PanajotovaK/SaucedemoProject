package Tests;

import Base.BaseTest;
import Pages.CartPage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.SortPage;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VisibilityTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        cartPage = new CartPage();
        homePage = new HomePage();
        loginPage = new LoginPage();
        sortPage = new SortPage();

        driver.manage().window().maximize();
        driver.navigate().to("https://www.saucedemo.com/");

        loginPage.inputUsername("standard_user");
        loginPage.inputPassword("secret_sauce");
        loginPage.clickOnLoginButton();

        homePage.ClickOnBurgerMenu();
        homePage.clickOnAllItems();
    }

    @AfterMethod
    public void captureScreenshotOnFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            takeScreenshot(result.getName());
        }
    }

    @Test
    public void verifyThatItemsAreVisible() {
        Assert.assertTrue(homePage.areAllItemsVisible(), "Not all items are visible on the page.");
    }

    @Test
    public void verifyThatAllAddToCartButtonsAreEnabled() {
        Assert.assertTrue(homePage.areAllAddToCartButtonsEnabled(), "Some add to cart buttons are not enabled.");
    }

}
