package Tests;

import Base.BaseTest;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.ProductDetailsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductDetailsTest extends BaseTest {

    private LoginPage loginPage;
    private HomePage homePage;
    private ProductDetailsPage productDetailsPage;

    @BeforeMethod
    public void pageSetUp() {
        loginPage = new LoginPage();
        homePage = new HomePage();
        productDetailsPage = new ProductDetailsPage();

        driver.manage().window().maximize();
        driver.navigate().to("https://www.saucedemo.com/");
        loginPage.inputUsername("standard_user");
        loginPage.inputPassword("secret_sauce");
        loginPage.clickOnLoginButton();
    }

    @Test
    public void verifyProductDetailsAndAddToCart() {

        String productName = homePage.productNames.get(0).getText();

        homePage.openProductDetailsByIndex(0);

        productDetailsPage.waitForPageToLoad();

        Assert.assertTrue(productDetailsPage.isTitleDisplayed(), "Product title is not displayed");
        Assert.assertEquals(productDetailsPage.getTitleText(), productName, "Product title does not match expected");
        Assert.assertTrue(productDetailsPage.isDescriptionDisplayed(), "Product description is not displayed");
        Assert.assertTrue(productDetailsPage.isPriceDisplayed(), "Product price is not displayed");
        Assert.assertTrue(productDetailsPage.isAddToCartButtonDisplayed(), "Add to cart button is not displayed");

        productDetailsPage.clickAddToCart();

        Assert.assertEquals(homePage.getCartItemCount(), 1, "Cart item count should be 1 after adding product");
    }
}
