package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
     WebDriver driver;

    public static final String BASE_URL = "https://www.saucedemo.com";
    private static final By APP_LOGO = By.xpath("//*[@class='app_logo']");

    BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openPage(String url) {
        driver.get(url);
    }

    public void waitForElementLocated(By element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }
    public void waitForElementAppLogo() {
        waitForElementLocated(APP_LOGO, 10);
    }
}
