package Base;

import Pages.CartPage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.SortPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BaseTest {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public LoginPage loginPage;
    public ExcelReader excelReader;
    public HomePage homePage;
    public SortPage sortPage;
    public CartPage cartPage;

    @BeforeClass

    public void setUp () {

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();



        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-password-manager-reauthentication");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-features=PasswordLeakDetectionEnabled");
        options.addArguments("--password-store=basic");
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));



    }

    @AfterClass

    public void teardown () {
        driver.quit();
    }
}
