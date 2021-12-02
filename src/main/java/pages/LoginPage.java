package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
        driver.findElement(USERNAME_INPUT).sendKeys(username);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(BUTTON_LOGIN).click();
        return new ProductsPage(driver);
    }

    public LoginPage openPage() {
        openPage(BASE_URL);
        waitForElementLocate();
        return this;
    }

      public String getErrorText(String validationMessageText) {
        return driver.findElement(By.xpath(String.format(ERROR_TEXT_POP_UP, validationMessageText))).getText();
    }

    @Step("Нажаите кнопки LOGIN на странице LoginPage")
    public LoginPage errorFieldClickButton() {
        driver.findElement(BUTTON_LOGIN).click();
        return this;
    }

    @Step("Ввод значения '{username}' в поле login на странице LoginPage")
    public LoginPage getEmptyPasswordField(String username) {
        driver.findElement(USERNAME_INPUT).sendKeys(username);
        return this;
    }

    @Step("Ввод значения '{password}' в поле password на странице LoginPage")
    public LoginPage getEmptyloginField(String password) {
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        return this;
    }

    public LoginPage waitForElementLocate() {
        waitForElementLocated(BUTTON_LOGIN, 10);
        return this;
    }
}
