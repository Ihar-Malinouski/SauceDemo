package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Log4j2
public class BasePage {
     WebDriver driver;

    public static final String BASE_URL = "https://www.saucedemo.com";
    private static final By APP_LOGO = By.xpath("//*[@class='app_logo']");

    BasePage(WebDriver driver) {
        this.driver = driver;
      //  log.debug("Initializing PageFactory");
        PageFactory.initElements(driver, this);
    }

    @Step("Открытие страницы '{url}'")
    public void openPage(String url) {
        log.info("Opening URL: " + url);
        driver.get(url);
    }

    public void waitForElementLocated(By element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        log.debug("Waiter timeout is: " + timeout + " for element: " + element);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }
    public void waitForElementAppLogo() {
        waitForElementLocated(APP_LOGO, 10);
    }
}
