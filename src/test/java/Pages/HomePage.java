package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class HomePage extends BaseTest {

    public HomePage() {
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

    @FindBy(className = "inventory_item_name")
    public List<WebElement> productNames;

    @FindBy(css = "button.btn_inventory")
    public List<WebElement> addToCartButtons;



    public void ClickOnBurgerMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(burgerMenu)).click();
        wait.until(ExpectedConditions.visibilityOf(allItems));
    }

    public void clickOnAllItems() {
        wait.until(ExpectedConditions.elementToBeClickable(allItems)).click();
    }

    public void setAddToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCart)).click();
    }

    public void addItemToCartByIndex(int index) {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButtons.get(index))).click();
    }

    public void addRandomItemsToCart(int count) {
        Set<Integer> addedIndexes = new HashSet<>();
        Random random = new Random();

        while (addedIndexes.size() < count && addedIndexes.size() < addToCartButtons.size()) {
            int index = random.nextInt(addToCartButtons.size());
            if (!addedIndexes.contains(index)) {
                addedIndexes.add(index);
                addToCartButtons.get(index).click();
            }
        }
    }

    public void clickOnCartButton() {
        wait.until(ExpectedConditions.elementToBeClickable(cartButton)).click();
    }

    public void clearCartIfNotEmpty() {
        List<WebElement> badges = driver.findElements(org.openqa.selenium.By.className("shopping_cart_badge"));
        if (!badges.isEmpty()) {
            clickOnCartButton();

            List<WebElement> removeButtons = driver.findElements(org.openqa.selenium.By.cssSelector("button.cart_button"));

            for (WebElement removeBtn : removeButtons) {
                wait.until(ExpectedConditions.elementToBeClickable(removeBtn)).click();
                wait.until(ExpectedConditions.stalenessOf(removeBtn));
            }

            ClickOnBurgerMenu();
            clickOnAllItems();
        }
    }

    public void clickOnRemoveItemFromCart() {
        wait.until(ExpectedConditions.elementToBeClickable(removeButton)).click();
    }

    public void clickOnLogoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }

    public int getCartItemCount() {
        try {
            return Integer.parseInt(cartBadge.getText());
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean areAllItemsVisible() {
        for (WebElement item : allInventoryItems) {
            if (!item.isDisplayed()) {
                return false;
            }
        }
        return true;
    }

    public boolean areAllAddToCartButtonsEnabled() {
        for (WebElement button : addToCartButtons) {
            if (!button.isEnabled()) {
                return false;
            }
        }
        return true;
    }

    public void openProductDetailsByIndex(int index) {
        productNames.get(index).click();
    }

}
