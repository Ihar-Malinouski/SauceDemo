package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private static final By USERNAME_INPUT = By.xpath("//*[@id='user-name']");
    private static final By PASSWORD_INPUT = By.xpath("//*[@id='password']");
    private static final By BUTTON_LOGIN = By.xpath("//*[@id='login-button']");
    private static final String ERROR_TEXT_POP_UP = "//*[@class='error-button']/ancestor::*[@class='error-message-container error']/h3";

    @Step("Авторизация с Логин '{username}' и паролем '{password}' и нажатием на кнопку LOGIN")
    public ProductsPage login(String username, String password) {
        log.info(String.format("Type text: '%s' into username input from CheckoutPage. Locator is: '%s'", username, USERNAME_INPUT));
        driver.findElement(USERNAME_INPUT).sendKeys(username);
        log.info(String.format("Type text: '%s' into password input from CheckoutPage. Locator is: '%s'", password, PASSWORD_INPUT));
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        log.info(String.format("Click button on LoginPage. Locator is: '%s'", BUTTON_LOGIN));
        driver.findElement(BUTTON_LOGIN).click();
        return new ProductsPage(driver);
    }

    public LoginPage openPage() {
        openPage(BASE_URL);
        waitForElementLocate();
        return this;
    }

    public String getErrorText(String validationMessageText) {
        String errorTextWithIncorrectAuthorization = driver.findElement(By.xpath(String.format(ERROR_TEXT_POP_UP, validationMessageText))).getText();
        log.info("Error text with incorrect authorization: " + errorTextWithIncorrectAuthorization);
        return errorTextWithIncorrectAuthorization;
    }

    @Step("Нажаите кнопки LOGIN на странице LoginPage")
    public LoginPage errorFieldClickButton() {
        log.info(String.format("Click button on LoginPage. Locator is: '%s'", BUTTON_LOGIN));
        driver.findElement(BUTTON_LOGIN).click();
        return this;
    }

    @Step("Ввод значения '{username}' в поле login на странице LoginPage")
    public LoginPage getEmptyPasswordField(String username) {
        log.info(String.format("Type text: '%s' into username input on LoginPage. Locator is: '%s'", username, USERNAME_INPUT));
        driver.findElement(USERNAME_INPUT).sendKeys(username);
        return this;
    }

    @Step("Ввод значения '{password}' в поле password на странице LoginPage")
    public LoginPage getEmptyingField(String password) {
        log.info(String.format("Type text: '%s' into username input on LoginPage. Locator is: '%s'", password, PASSWORD_INPUT));
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        return this;
    }

    public LoginPage waitForElementLocate() {
        waitForElementLocated(BUTTON_LOGIN, 10);
        return this;
    }
}
