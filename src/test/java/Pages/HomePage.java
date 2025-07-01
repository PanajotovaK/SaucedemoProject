package Pages;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.Random;


import java.util.List;

public class HomePage extends BaseTest {
    public HomePage () {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "react-burger-menu-btn")
    public WebElement burgerMenu;

    @FindBy(id = "inventory_sidebar_link")
    public WebElement allItems;

    @FindBy(css = "button[data-test='add-to-cart-sauce-labs-backpack']")
    public WebElement addToCart;

    @FindBy(className = "inventory_item")
    public List<WebElement> allInventoryItems;

    @FindBy(css = "[data-test='shopping-cart-link']")
    public WebElement cartButton;

    @FindBy(css = "button.cart_button")
    WebElement removeButton;

    @FindBy(id = "logout_sidebar_link")
    WebElement logoutButton;

    @FindBy(className = "shopping_cart_badge")
    WebElement cartBadge;



    public void ClickOnBurgerMenu () {
        wait.until(ExpectedConditions.elementToBeClickable(burgerMenu)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("inventory_sidebar_link")));
        wait.until(ExpectedConditions.visibilityOf(allItems));
    }


    public void clickOnAllItems () {
        wait.until(ExpectedConditions.elementToBeClickable(allItems)).click();
    }

    public void setAddToCart () {
        wait.until(ExpectedConditions.elementToBeClickable(addToCart)).click();
    }

    public void addItemToCartByIndex(int index) {
        WebElement item = allInventoryItems.get(index);


        WebElement addButton = item.findElement(By.cssSelector("button.btn_inventory"));

        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
    }

    public void addRandomItemsToCart(int numberOfItems) {
        Random rand = new Random();

        for (int i = 0; i < numberOfItems; i++) {
            int randomIndex = rand.nextInt(allInventoryItems.size());
            WebElement item = allInventoryItems.get(randomIndex);


            WebElement addButton = item.findElement(By.cssSelector("button.btn_inventory"));


            if (addButton.getText().equalsIgnoreCase("Add to cart")) {
                wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
            }
        }
    }

    public void clickOnCartButton () {
        wait.until(ExpectedConditions.elementToBeClickable(cartButton)).click();

    }

    public void clearCartIfNotEmpty() {
        List<WebElement> badges = driver.findElements(By.className("shopping_cart_badge"));
        if (!badges.isEmpty()) {
            clickOnCartButton();

            List<WebElement> removeButtons = driver.findElements(By.cssSelector("button.cart_button"));

            for (WebElement removeBtn : removeButtons) {
                wait.until(ExpectedConditions.elementToBeClickable(removeBtn)).click();

                wait.until(ExpectedConditions.stalenessOf(removeBtn));
            }


            ClickOnBurgerMenu();
            clickOnAllItems();
        }
    }

    public void clickOnRemoveItemFromCart () {
        wait.until(ExpectedConditions.elementToBeClickable(removeButton)).click();
    }

    public void clickOnLogoutButton () {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }

    public int getCartItemCount () {
        try {
            return Integer.parseInt(cartBadge.getText());
        }

        catch (Exception e){

            return 0;

        }
    }

    public void verifyThatAllItemsAreVisible () {
        for (WebElement item : allInventoryItems) {
            if(!item.isDisplayed()) {
                Assert.assertTrue(item.isDisplayed(), "Item is not visible: " + item.getText());
            }
        }
    }

    public void verifyThatAllAddToCartButtonsAreEnabled () {
        for (WebElement item : allInventoryItems) {
            if (!addToCart.isEnabled()) {
                Assert.assertTrue(item.isEnabled(), "Add to cart button not enabled for item: " + item.getText());
            }
        }
    }
}
