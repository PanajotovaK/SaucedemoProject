package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductDetailsPage extends BaseTest {

    public ProductDetailsPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "inventory_details_name")
    public WebElement title;

    @FindBy(className = "inventory_details_desc")
    public WebElement description;

    @FindBy(className = "inventory_details_price")
    public WebElement price;

    @FindBy(css = "button.btn_primary.btn_inventory")
    public WebElement addToCartButton;


    public void waitForPageToLoad() {
        wait.until(ExpectedConditions.visibilityOf(title));
    }


    public void clickAddToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
    }


    public String getTitleText() {
        return title.getText();
    }


    public boolean isTitleDisplayed() {
        return title.isDisplayed();
    }

    public boolean isDescriptionDisplayed() {
        return description.isDisplayed();
    }

    public boolean isPriceDisplayed() {
        return price.isDisplayed();
    }

    public boolean isAddToCartButtonDisplayed() {
        return addToCartButton.isDisplayed();
    }
}

