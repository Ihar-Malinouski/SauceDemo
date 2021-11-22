package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {

     public ProductsPage(WebDriver driver) {
        super(driver);
    }

    private static final String INVENTORY_URL = "/inventory.html";
    private static final String ADD_PRODUCT_TO_CART_BUTTON = "//*[text() ='%s']/ancestor::*[@class='inventory_item']//button";
    private static final String PRODUCT_IMAGE = "//*[text()='%s']/ancestor::*[@class='inventory_item']/*[@class='inventory_item_img']";

    public ProductsPage openPage() {
        openPage(BASE_URL + INVENTORY_URL);
        waitForElementAppLogo();
        return this;
    }

    @Step("Добавление пролукта '{productName}' и нажатие кнопки ADD на странице ProductsPage")
    public ProductsPage addProductToCart(String productName) {
        driver.findElement(By.xpath(String.format(ADD_PRODUCT_TO_CART_BUTTON, productName))).click();
        return this;
    }

    @Step("Добавление пролукта '{productName}' нажатием на изображение к продукту '{productName}' на странице ProductsPage")
    public ProductDetailsPage clickOnProductImage(String productName) {
        driver.findElement(By.xpath(String.format(PRODUCT_IMAGE, productName))).click();
        return new ProductDetailsPage(driver);
    }
}