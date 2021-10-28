package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    private static final String PRODUCT_PRICE = "//*[text()='%s']/ancestor::*[@class='cart_item']//*[@class='inventory_item_price']";
    private static final String REMOVE_BUTTON = "//*[text()='%s']/ancestor::*[@class='cart_item']//button";
    private static final String QUANTITY_LABEL = "//*[@class='cart_quantity']";
    private static final String QUANTITY_ICON_CART = "//*[@class='shopping_cart_badge']";

    public String getProductPrice(String productName) {
        return driver.findElement(By.xpath(String.format(PRODUCT_PRICE, productName))).getText();
    }

    public void deleteProductFromCart(String productName) {
        driver.findElement(By.xpath(String.format(REMOVE_BUTTON, productName))).click();
    }

    public String getQuantityLabel(String numberLabel) {
        return driver.findElement(By.xpath(String.format(QUANTITY_LABEL, numberLabel))).getText();
    }

    public String getQuantityIconCart(String numberIconCart) {
        return driver.findElement(By.xpath(String.format(QUANTITY_ICON_CART, numberIconCart))).getText();
    }
}
