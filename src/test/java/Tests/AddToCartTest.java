package Tests;

import Base.BaseTest;
import Pages.HomePage;
import Pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class AddToCartTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp1 () {

        homePage = new HomePage();
        loginPage = new LoginPage();

        driver.manage().window().maximize();
        driver.navigate().to("https://www.saucedemo.com/");
        loginPage.inputUsername("standard_user");
        loginPage.inputPassword("secret_sauce");
        loginPage.clickOnLoginButton();
    }

    @AfterMethod
    public void captureScreenshotOnFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            takeScreenshot(result.getName());
        }
    }




    @Test(groups = "cart")

public void AddToCart () {


    homePage.clearCartIfNotEmpty();
    homePage.ClickOnBurgerMenu();
    homePage.clickOnAllItems();
    homePage.addItemToCartByIndex(1);
    homePage.clickOnCartButton();

        WebElement cartBadge = driver.findElement(By.cssSelector(".shopping_cart_badge"));
        Assert.assertEquals(cartBadge.getText(), "1");

        WebElement removeBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("button.btn_secondary.cart_button")));

        Assert.assertTrue(removeBtn.isDisplayed());

    }

    @Test(groups = "cart")

    public void addRandomProductToCartTest() {

        homePage.clearCartIfNotEmpty();
        homePage.ClickOnBurgerMenu();
        homePage.clickOnAllItems();
        homePage.addRandomItemsToCart(1);
        homePage.clickOnCartButton();


        WebElement cartBadge = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_badge")));
        Assert.assertEquals(cartBadge.getText(), "1");


        WebElement removeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("button.btn_secondary.cart_button")));
        Assert.assertTrue(removeButton.isDisplayed());
    }

    @Test

    public void removeItemFromCart () {

        homePage.clearCartIfNotEmpty();
        homePage.ClickOnBurgerMenu();
        homePage.clickOnAllItems();
        homePage.addRandomItemsToCart(1);
        homePage.clickOnCartButton();
        homePage.clickOnRemoveItemFromCart();

        List<WebElement> cartBadges = driver.findElements(By.className("shopping_cart_badge"));
        Assert.assertTrue(cartBadges.isEmpty(), "Cart is not empty.");

    }


    @Test(groups = "cart")
    public void addMultipleItemsToCartTest() {

        homePage.clearCartIfNotEmpty();
        homePage.ClickOnBurgerMenu();
        homePage.clickOnAllItems();
        homePage.addRandomItemsToCart(3);
        homePage.clickOnCartButton();

        WebElement cartBadge = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_badge")));
        Assert.assertEquals(cartBadge.getText(), "3");

        List<WebElement> removeButtons = driver.findElements(By.cssSelector("button.btn_secondary.cart_button"));
        Assert.assertEquals(removeButtons.size(), 3);
    }


}