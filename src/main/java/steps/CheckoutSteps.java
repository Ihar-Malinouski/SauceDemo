package steps;

import org.openqa.selenium.WebDriver;
import pages.CartPage;
import pages.CheckoutPage;

public class CheckoutSteps {

    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    public CheckoutSteps(WebDriver driver) {
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    public CheckoutSteps clickButtonCheckoutAndInputField(String firstName, String lastName, String zip) {
        cartPage.openPage()
                .checkoutClickButton()
                .fillInDataEntryForPayment(firstName, lastName, zip)
                .clickContinueButton();
        return this;
    }
}
