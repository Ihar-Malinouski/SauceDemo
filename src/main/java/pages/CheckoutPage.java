package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class CheckoutPage extends BasePage {
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    private static final By FIRST_NAME_INPUT = By.xpath("//*[@id='first-name']");
    private static final By LAST_NAME_INPUT = By.xpath("//*[@id='last-name']");
    private static final By CONTINUE_BUTTON = By.xpath("//*[@id='continue']");
    private static final String CHECKOUT_URL = "/checkout-step-one.html";
    private static final String ERROR_TEXT_EMPTY_FIELD = "//*[contains(@class,'error-button')]/ancestor::*/h3";

    public CheckoutPage openPage() {
        openPage(BASE_URL + CHECKOUT_URL);
        waitForElementAppLogo();
        return this;
    }

    @Step("Заполенние полей для опланы '{firstName}' и '{lastName}' на странице CheckoutPage")
    public CheckoutPage fillInDataEntryForPayment(String firstName, String lastName, String zip) {
        log.info(String.format("Type text: '%s' into firstName input from CheckoutPage. Locator is: '%s'" , firstName,FIRST_NAME_INPUT));
        driver.findElement(FIRST_NAME_INPUT).sendKeys(firstName);
        log.info(String.format("Type text: '%s' into lastName input from CheckoutPage. Locator is: '%s'", lastName, LAST_NAME_INPUT));
        driver.findElement(LAST_NAME_INPUT).sendKeys(lastName);
        log.info(String.format("Type text: '%s' zip/code input from CheckoutPage", zip));
        return this;
    }

    @Step("Нажатие кнопки CHECKOUT на странице CheckoutPage")
    public CheckoutPage clickContinueButton() {
        log.info("Click 'continue' button from CheckoutPage.");
        driver.findElement(CONTINUE_BUTTON).click();
        return this;
    }

    public String getErrorTextMessage(String validationMessageText) {
        String errorTextMessage = driver.findElement(By.xpath(String.format(ERROR_TEXT_EMPTY_FIELD, validationMessageText))).getText();
        log.info("Text error message: " + errorTextMessage);
        return errorTextMessage;
    }
}
