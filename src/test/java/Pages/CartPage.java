package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BaseTest {

    public CartPage () {
        PageFactory.initElements(driver, this);
    }

    @FindBy (id = "checkout")
    public WebElement checkoutButton;

    @FindBy(id = "first-name")
    public WebElement firstnameField;

    @FindBy(id = "last-name")
    public WebElement lastnameField;

    @FindBy(id = "postal-code")
    public WebElement postalCodeField;

    @FindBy(id = "continue")
    public WebElement continueButton;

    @FindBy(id = "finish")
    public WebElement finishButton;

    @FindBy(id = "cancel")
    public WebElement cancelButton;

    @FindBy(css = ".header_secondary_container .title")
    public WebElement youtCartMsg;

    @FindBy(id = "continue-shopping")
    public WebElement continueShoppingButton;


    public void clickOnCheckOutButton () {

        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
    }

    public void inputFirstname (String firstname) {

        firstnameField.click();
        firstnameField.sendKeys(firstname);
    }

    public void inputLastname (String lastname) {
        lastnameField.click();
        lastnameField.sendKeys(lastname);

    }

    public void inputPostalCode(String postalCode) {
        postalCodeField.click();
        postalCodeField.sendKeys(postalCode);
    }


    public void clickOnContinueButton () {
        continueButton.click();
    }

    public void clickOnFinishButton () {
        finishButton.click();
    }

    public void clickOnCancelButton () {
        cancelButton.click();
    }

    public void clickOnContinueShoppingButton () {
        continueShoppingButton.click();
    }
}
