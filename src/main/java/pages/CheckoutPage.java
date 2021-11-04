package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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

    public CheckoutPage fillInDataEntryForPayment(String firstName, String lastName, String zipPostalCode) {
        driver.findElement(FIRST_NAME_INPUT).sendKeys(firstName);
        driver.findElement(LAST_NAME_INPUT).sendKeys(lastName);
        return this;
    }

    public CheckoutPage clickContinueButton() {
        driver.findElement(CONTINUE_BUTTON).click();
        return this;
    }

    public String getErrorTextMessage(String validationMessageText) {
        return driver.findElement(By.xpath(String.format(ERROR_TEXT_EMPTY_FIELD, validationMessageText))).getText();
    }

}
