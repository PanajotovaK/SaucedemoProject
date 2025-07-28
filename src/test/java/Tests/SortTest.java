package Tests;

import Base.BaseTest;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.SortPage;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static Base.BaseTest.driver;

public class SortTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp3 () {

        loginPage = new LoginPage();
        homePage = new HomePage();
        sortPage = new SortPage();

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

    @Test
    public void verifySortByPriceLowToHigh() {


        homePage.ClickOnBurgerMenu();
        homePage.clickOnAllItems();
        sortPage.selectSortOption("Price (low to high)");

        List<Double> actualPrices = sortPage.getAllItemPrices();
        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        Collections.sort(expectedPrices);

        Assert.assertEquals(actualPrices, expectedPrices, "Prices are not sorted from low to high.");
    }

    @Test
    public void verifySortByPriceHighToLow () {

        homePage.ClickOnBurgerMenu();
        homePage.clickOnAllItems();
        sortPage.selectSortOption("Price (high to low)");

        List<Double> actualPrices = sortPage.getAllItemPrices();
        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        expectedPrices.sort(Collections.reverseOrder());

        Assert.assertEquals(actualPrices, expectedPrices, "Prices are not sorted from high to low.");
    }

    @Test
    public void verifySortByNameAtoZ() {

        homePage.ClickOnBurgerMenu();
        homePage.clickOnAllItems();
        sortPage.selectSortOption("Name (A to Z)");

        List<String> actualName = sortPage.getAllItemNames();
        List<String> expectedName = new ArrayList<>(actualName);
        Collections.sort(expectedName);

        Assert.assertEquals(actualName, expectedName, "Items are not sorted by name");
    }

    @Test
    public void verifySortByNameZtoA () {

        homePage.ClickOnBurgerMenu();
        homePage.clickOnAllItems();
        sortPage.selectSortOption("Name (Z to A)");

        List<String> actualName = sortPage.getAllItemNames();
        List<String> expectedName = new ArrayList<>(actualName);
        expectedName.sort(Collections.reverseOrder());

        Assert.assertEquals(actualName, expectedName, "Items are not sorted by name");
    }
}
