package Tests;

import Base.BaseTest;
import Pages.CartPage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.SortPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VisibilityTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp6() {
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

    @Test
    public void verifyThatItemsAreVisibly() {
        for (int i = 0; i < homePage.allInventoryItems.size(); i++) {
            Assert.assertTrue(homePage.allInventoryItems.get(i).isDisplayed(), "Item at index " + i + "is not visible.");

        }
    }

    @Test
    public void verifyThatAllAddToCartButtonsAreEnabled() {
        for (int i = 0; i < homePage.allInventoryItems.size(); i++) {
            WebElement item = homePage.allInventoryItems.get(i);
            WebElement addButton = item.findElement(By.cssSelector("button.btn_inventory"));
            Assert.assertTrue(addButton.isEnabled(), "Add to cart button should be enabled for item index: " + i);
        }
    }
}