package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class CartPage extends BasePage {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    private static final String PRODUCT_PRICE = "//*[text()='%s']/ancestor::*[@class='cart_item']//*[@class='inventory_item_price']";
    private static final String REMOVE_BUTTON = "//*[text()='%s']/ancestor::*[@class='cart_item']//button";
    private static final String QUANTITY_LABEL = "//*[text()='%s']/ancestor::*[@class='cart_item']//*[@class='cart_quantity']";
    private static final String QUANTITY_ICON_CART = "//*[@class='shopping_cart_badge']";
    private static final String CART_URL = "/cart.html";
    private static final By CHECKOUT_CLICK_BUTTON = By.id("checkout");

    public CartPage openPage() {
        openPage(BASE_URL + CART_URL);
        waitForElementCheckoutButton();
        return this;
    }

    public CartPage waitForElementCheckoutButton() {
        waitForElementLocated(CHECKOUT_CLICK_BUTTON, 10);
        return this;
    }

    public String getProductPrice(String productName) {
        log.info(String.format("Get price for product: '%s'. Locator is: '%s'", productName, PRODUCT_PRICE));
        String productPrice = driver.findElement(By.xpath(String.format(PRODUCT_PRICE, productName))).getText();
        log.info("Product price is: " + productPrice);
        return productPrice;
    }

    public CartPage deleteProductFromCart(String productName) {
        log.info(String.format("Removing a product from CartPage with the name: '%s'. Locator is: '%s'", productName, REMOVE_BUTTON));
        driver.findElement(By.xpath(String.format(REMOVE_BUTTON, productName))).click();
        return this;
    }

    public String getQuantityLabel(String productName) {
        log.info(String.format("Get quantity label for product: '%s'. Locator is: '%s'", productName, QUANTITY_LABEL));
        String productLabel = driver.findElement(By.xpath(String.format(QUANTITY_LABEL, productName))).getText();
        log.info("Product label is:" + productLabel);
        return productLabel;
    }

    public String getQuantityIconCart() {
        String quantityIcon = driver.findElement(By.xpath(String.format(QUANTITY_ICON_CART))).getText();
        log.info(String.format("Product quantity icon from CartPage is: '%s'. Locator is: '%s'", quantityIcon, QUANTITY_ICON_CART));
        return quantityIcon;
    }

    public CheckoutPage checkoutClickButton() {
        log.info(String.format("Click button on page CartPage. Locator is: '%&s'" + CHECKOUT_CLICK_BUTTON));
        driver.findElement(CHECKOUT_CLICK_BUTTON).click();
        return new CheckoutPage(driver);
    }
}

