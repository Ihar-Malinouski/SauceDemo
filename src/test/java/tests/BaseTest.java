package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.*;
import steps.CartSteps;
import steps.CheckoutSteps;
import steps.LoginSteps;
import steps.ProductSteps;

@Log4j2
@Listeners(TestListener.class)
public class BaseTest {
    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    ProductDetailsPage productDetailsPage;
    ProductSteps productSteps;
    CheckoutSteps checkoutSteps;
    CartSteps cartSteps;
    LoginSteps loginSteps;

    @BeforeMethod
    public void initTest(ITestContext context) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        log.debug("Browser is started in fullscreen mode.");
        driver.manage().window().maximize();
        String driverVariable = "driver";
        context.setAttribute(driverVariable, driver);
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
        productSteps = new ProductSteps(driver);
        checkoutSteps = new CheckoutSteps(driver);
        cartSteps = new CartSteps(driver);
        loginSteps = new LoginSteps(driver);
    }

    @AfterMethod
    public void closeDriver() {
        log.info("Close browser");
        driver.quit();
    }
}

