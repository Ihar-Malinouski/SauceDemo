package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTests extends BaseTest {

    @Test
    public void addProductToCartTest() {
        loginPage.openPage("https://www.saucedemo.com/");
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProductToCart("Sauce Labs Fleece Jacket");
        productsPage.openPage("https://www.saucedemo.com/cart.html");
        Assert.assertEquals(cartPage.getProductPrice("Sauce Labs Fleece Jacket"), "$49.99");
    }

    @Test
    public void orderPayment() {
        loginPage.openPage("https://www.saucedemo.com/");
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProductToCart("Sauce Labs Onesie");
        productsPage.openPage("https://www.saucedemo.com/cart.html");
        Assert.assertEquals(cartPage.getProductPrice("Sauce Labs Onesie"), "$7.99");
        productsPage.openPage("https://www.saucedemo.com/checkout-step-one.html");
        checkoutPage.dataEntryForPayment("Ihar", "Malinouski", "123123");
    }

    @Test
    public void addingTwoBooks() {
        loginPage.openPage("https://www.saucedemo.com/");
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProductToCart("Sauce Labs Onesie");
        productsPage.addProductToCart("Sauce Labs Fleece Jacket");
        productsPage.openPage("https://www.saucedemo.com/cart.html");
        cartPage.deleteProductFromCart("Sauce Labs Fleece Jacket");
    }

    @Test
    public void productComparisonOnTheDetailsPage() {
        loginPage.openPage("https://www.saucedemo.com/");
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickOnTheImage("Sauce Labs Fleece Jacket");
        productDetailsPage.addProductToCartFromDetails("Sauce Labs Fleece Jacket");
        productsPage.openPage("https://www.saucedemo.com/cart.html");
        Assert.assertEquals(cartPage.getQuantityLabel("QUANTITY_LABEL"), cartPage.getQuantityIconCart("QUANTITY_ICON_CART"));
    }

    @Test
    public void unsuccessfulAuthorization() {
        loginPage.openPage("https://www.saucedemo.com/");
        loginPage.clickButtonEmptyLoginPassword();
        Assert.assertEquals("Epic sadface: Username is required", loginPage.getErrorText("ERROR_TEXT_POP_UP"));
        loginPage.clickButtonEmptyPassword("Ihar");
        Assert.assertEquals("Epic sadface: Password is required", loginPage.getErrorText("ERROR_TEXT_POP_UP"));
        loginPage.clickButtonEmptylogin("Ihar2");
        Assert.assertEquals("Epic sadface: Username and password do not match any user in this service", loginPage.getErrorText("ERROR_TEXT_POP_UP"));
    }
}