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
    public void priceComparisonInDetailTest() {
        loginPage.openPage("https://www.saucedemo.com/");
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProductToCart("Sauce Labs Onesie");
        productsPage.openPage("https://www.saucedemo.com/cart.html");
        Assert.assertEquals(cartPage.getProductPrice("Sauce Labs Onesie"), "$7.99");
    }

    @Test
    public void addingTwoBooksTest() {
        loginPage.openPage("https://www.saucedemo.com/");
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProductToCart("Sauce Labs Onesie");
        productsPage.addProductToCart("Sauce Labs Fleece Jacket");
        productsPage.openPage("https://www.saucedemo.com/cart.html");
        cartPage.deleteProductFromCart("Sauce Labs Fleece Jacket");
        Assert.assertEquals(cartPage.getQuantityIconCart(), "1");
    }

    @Test
    public void productComparisonOnTheDetailsPageTest() {
        loginPage.openPage("https://www.saucedemo.com/");
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickOnProductImage("Sauce Labs Fleece Jacket");
        productDetailsPage.addProductToCartFromDetails("Sauce Labs Fleece Jacket");
        productsPage.openPage("https://www.saucedemo.com/cart.html");
        Assert.assertEquals(cartPage.getQuantityLabel("Sauce Labs Fleece Jacket"), cartPage.getQuantityIconCart());
    }

    @Test
    public void unsuccessfulAuthorizationTest() {
        loginPage.openPage("https://www.saucedemo.com/");
        loginPage.waitForElementLocate();
        loginPage.clickLoginButton();
        Assert.assertEquals("Epic sadface: Username is required", loginPage.getErrorText("ERROR_TEXT_POP_UP"));
        loginPage.clickButtonEmptyPassword("Ihar");
        loginPage.clickLoginButton();
        Assert.assertEquals("Epic sadface: Password is required", loginPage.getErrorText("ERROR_TEXT_POP_UP"));
        loginPage.clickButtonEmptylogin("Ihar2");
        loginPage.clickLoginButton();
        Assert.assertEquals("Epic sadface: Username and password do not match any user in this service", loginPage.getErrorText("ERROR_TEXT_POP_UP"));
    }
}