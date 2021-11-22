package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
         return driver.findElement(By.xpath(String.format(PRODUCT_PRICE, productName))).getText();
    }

    public CartPage deleteProductFromCart(String productName) {
        driver.findElement(By.xpath(String.format(REMOVE_BUTTON, productName))).click();
        return this;
    }

    public String getQuantityLabel(String productName) {
        return driver.findElement(By.xpath(String.format(QUANTITY_LABEL, productName))).getText();
    }

    public String getQuantityIconCart() {
        return driver.findElement(By.xpath(String.format(QUANTITY_ICON_CART))).getText();
    }

    public CheckoutPage checkoutClickButton() {
        driver.findElement(CHECKOUT_CLICK_BUTTON).click();
        return new CheckoutPage(driver);
    }
}

