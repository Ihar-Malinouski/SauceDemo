package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
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
        log.info("Add product on ProductsPage. Locator is: " + ADD_PRODUCT_TO_CART_BUTTON);
        driver.findElement(By.xpath(String.format(ADD_PRODUCT_TO_CART_BUTTON, productName))).click();
        return this;
    }

    @Step("Добавление пролукта '{productName}' нажатием на изображение к продукту '{productName}' на странице ProductsPage")
    public ProductDetailsPage clickOnProductImage(String productName) {
        log.info("Add product on ProductsPage by clicking on the icon. Locator is: " + PRODUCT_IMAGE);
        driver.findElement(By.xpath(String.format(PRODUCT_IMAGE, productName))).click();
        return new ProductDetailsPage(driver);
    }
}