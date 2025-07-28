package Tests;

import Base.BaseTest;
import Pages.CartPage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.SortPage;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp5() {

        sortPage = new SortPage();
        loginPage = new LoginPage();
        homePage = new HomePage();
        cartPage = new CartPage();

        driver.manage().window().maximize();
        driver.navigate().to("https://www.saucedemo.com/");
        loginPage.inputUsername("standard_user");
        loginPage.inputPassword("secret_sauce");
        loginPage.clickOnLoginButton();
        homePage.ClickOnBurgerMenu();
        homePage.clickOnAllItems();
        homePage.addRandomItemsToCart(1);
        homePage.clickOnCartButton();

    }

    @AfterMethod
    public void captureScreenshotOnFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            takeScreenshot(result.getName());
        }
    }

    @Test

    public void verifyFinishingOrderSuccessfully() {

        cartPage.clickOnCheckOutButton();
        cartPage.inputFirstname("standard");
        cartPage.inputLastname("user");
        cartPage.inputPostalCode("11");
        cartPage.clickOnContinueButton();
        cartPage.clickOnFinishButton();

        WebElement orderConfirmation = driver.findElement(By.cssSelector("div[data-test='complete-text']"));
        String actualText = orderConfirmation.getText();

        Assert.assertEquals(actualText, "Your order has been dispatched, and will arrive just as fast as the pony can get there!");
    }

    @Test

    public void verifyThatUserCanCancelFinishingOrder() {

        cartPage.clickOnCheckOutButton();
        cartPage.inputFirstname("standard");
        cartPage.inputLastname("user");
        cartPage.inputPostalCode("11");
        cartPage.clickOnCancelButton();

        Assert.assertTrue(cartPage.checkoutButton.isDisplayed(), "Checkout button is not visible on the page.");
        Assert.assertTrue(cartPage.youtCartMsg.isDisplayed(), "'User is not on the Cart page. ");

    }

    @Test

    public void verifyThatUserCanFinishOrderAfterCanceling() {

        cartPage.clickOnCheckOutButton();
        cartPage.inputFirstname("standard");
        cartPage.inputLastname("user");
        cartPage.inputPostalCode("11");
        cartPage.clickOnCancelButton();
        cartPage.clickOnCheckOutButton();
        cartPage.inputFirstname("standard");
        cartPage.inputLastname("user");
        cartPage.inputPostalCode("11");
        cartPage.clickOnContinueButton();
        cartPage.clickOnFinishButton();

        WebElement orderConfirmation = driver.findElement(By.cssSelector("div[data-test='complete-text']"));
        String actualText = orderConfirmation.getText();

        Assert.assertEquals(actualText, "Your order has been dispatched, and will arrive just as fast as the pony can get there!");


    }

    @Test

    public void verifyThatUserCanContinueShoppingAfterCancelingFinishingOrder() {

        cartPage.clickOnCheckOutButton();
        cartPage.inputFirstname("standard");
        cartPage.inputLastname("user");
        cartPage.inputPostalCode("11");
        cartPage.clickOnCancelButton();
        cartPage.clickOnContinueShoppingButton();
        int initialCount = homePage.getCartItemCount();
        homePage.setAddToCart();
        int newCount = homePage.getCartItemCount();

        Assert.assertEquals(newCount, initialCount + 1, "Item is not added to the cart.");

    }

    @Test
    public void userCannotProceedWithEmptyFirstName() {
        cartPage.clickOnCheckOutButton();
        cartPage.inputFirstname("");
        cartPage.inputLastname("User");
        cartPage.inputPostalCode("11000");
        cartPage.clickOnContinueButton();

        WebElement error = driver.findElement(By.cssSelector("h3[data-test='error']"));
        Assert.assertTrue(error.isDisplayed());
        Assert.assertTrue(error.getText().contains("Error: First Name is required"));
    }

    @Test
    public void userCannotProceedWithEmptyLastName() {
        cartPage.clickOnCheckOutButton();
        cartPage.inputFirstname("Standard");
        cartPage.inputLastname("");
        cartPage.inputPostalCode("11000");
        cartPage.clickOnContinueButton();

        WebElement error = driver.findElement(By.cssSelector("h3[data-test='error']"));
        Assert.assertTrue(error.isDisplayed());
        Assert.assertTrue(error.getText().contains("Error: Last Name is required"));
    }

    @Test
    public void userCannotProceedWithEmptyPostalCode() {
        cartPage.clickOnCheckOutButton();
        cartPage.inputFirstname("Standard");
        cartPage.inputLastname("User");
        cartPage.inputPostalCode("");
        cartPage.clickOnContinueButton();

        WebElement error = driver.findElement(By.cssSelector("h3[data-test='error']"));
        Assert.assertTrue(error.isDisplayed());
        Assert.assertTrue(error.getText().contains("Error: Postal Code is required"));
    }

}
