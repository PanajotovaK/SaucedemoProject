package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class SortPage extends BaseTest {

    public SortPage () {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "select[data-test='product-sort-container']")
    private WebElement sortDropdown;


    @FindBy(className = "inventory_item_price")
    public List<WebElement> itemPrices;

    @FindBy(className = "inventory_item_name")
    public List<WebElement> itemNames;

    public void selectSortOption(String visibleText) {
        wait.until(ExpectedConditions.visibilityOf(sortDropdown));
        Select select = new Select(sortDropdown);
        select.selectByVisibleText(visibleText);
    }


    public List<Double> getAllItemPrices() {
        List<Double> prices = new ArrayList<>();
        for (WebElement price : itemPrices) {
            String priceText = price.getText().replace("$", "").trim();
            prices.add(Double.parseDouble(priceText));
        }
        return prices;
    }

    public List<String> getAllItemNames() {
        List<String> names = new ArrayList<>();
        for (WebElement name : itemNames) {
            names.add(name.getText().trim());
        }
        return names;
    }


}
