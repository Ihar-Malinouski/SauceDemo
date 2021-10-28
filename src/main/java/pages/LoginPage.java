package pages;

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

    public void login(String username, String password) {
        driver.findElement(USERNAME_INPUT).sendKeys(username);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(BUTTON_LOGIN).click();
    }

    public String getErrorText(String validationMessageText) {
        return driver.findElement(By.xpath(String.format(ERROR_TEXT_POP_UP, validationMessageText))).getText();
    }

    public void clickButtonEmptyLoginPassword() {
        driver.findElement(BUTTON_LOGIN).click();
    }

    public void clickButtonEmptyPassword(String username) {
        driver.findElement(USERNAME_INPUT).sendKeys(username);
        driver.findElement(BUTTON_LOGIN).click();
    }

    public void clickButtonEmptylogin(String password) {
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(BUTTON_LOGIN).click();
    }
}
