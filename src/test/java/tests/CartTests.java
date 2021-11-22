package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CartTests extends BaseTest {


    @Test(description = "Human-readable test name", retryAnalyzer = Retry.class)
    @Description("Some detailed test description")
    @Parameters({"login", "password", "secondNameProduct"})
    @Link("1111")
    @Issue("2222")
    @TmsLink("3333")
    public void addProductToCartTest(@Optional("standard_user") String login, @Optional("secret_sauce") String password, @Optional("Sauce Labs Onesie") String secondNameProduct) {
        loginPage.openPage()
                .login(login, password)
                .addProductToCart(secondNameProduct);
        cartPage.openPage();
        Assert.assertEquals(cartPage.getProductPrice(secondNameProduct), "$7.99");
    }

    @Parameters({"login", "password", "firstNameProduct"})
    @Test(retryAnalyzer = Retry.class)
    public void priceComparisonInDetailTest(@Optional("standard_user") String login, @Optional("secret_sauce") String password, @Optional("Sauce Labs Onesie") String firstNameProduct) {
        loginPage.openPage()
                .login(login, password)
                .addProductToCart(firstNameProduct);
        cartPage.openPage();
        Assert.assertEquals(cartPage.getProductPrice(firstNameProduct), "$7.99");
    }

    @Parameters({"login", "password", "firstNameProduct", "secondNameProduct"})
    @Test(retryAnalyzer = Retry.class)
    public void addingTwoBooksTest(@Optional("standard_user") String login, @Optional("secret_sauce") String password, @Optional("Sauce Labs Fleece Jacket") String secondNameProduct, @Optional("Sauce Labs Onesie") String firstNameProduct) {
        loginPage.openPage()
                .login(login, password)
                .addProductToCart(firstNameProduct)
                .addProductToCart(secondNameProduct);
        cartPage.openPage()
                .deleteProductFromCart(secondNameProduct);
        Assert.assertEquals(cartPage.getQuantityIconCart(), "1");
    }

    @Parameters({"login", "password", "secondNameProduct"})
    @Test(retryAnalyzer = Retry.class)
    public void productComparisonOnTheDetailsPageTest(@Optional("standard_user") String login, @Optional("secret_sauce") String password, @Optional("Sauce Labs Onesie") String secondNameProduct) {
        loginPage.openPage()
                .login(login, password)
                .clickOnProductImage(secondNameProduct)
                .addProductToCartFromDetails(secondNameProduct);
        cartPage.openPage();
        Assert.assertEquals(cartPage.getQuantityLabel(secondNameProduct), cartPage.getQuantityIconCart());
    }

    @Test(retryAnalyzer = Retry.class)
    public void unsuccessfulAuthorizationTest() {
        loginPage.openPage()
                .waitForElementLocate()
                .errorFieldClickButton();
        Assert.assertEquals("Epic sadface: Username is required", loginPage.getErrorText("ERROR_TEXT_POP_UP"));
        loginPage.getEmptyPasswordField("Ihar")
                .errorFieldClickButton();
        Assert.assertEquals("Epic sadface: Password is required", loginPage.getErrorText("ERROR_TEXT_POP_UP"));
        loginPage.getEmptyloginField("Ihar2")
                .errorFieldClickButton();
        Assert.assertEquals("Epic sadface: Username and password do not match any user in this service", loginPage.getErrorText("ERROR_TEXT_POP_UP"));
    }

    @Parameters({"login", "password", "secondNameProduct"})
    @Test
    public void addProductToCartThisPageFactoryTest(@Optional("standard_user") String login, @Optional("secret_sauce") String password, @Optional("Sauce Labs Onesie") String secondNameProduct) {
        loginPage.openPage()
                .login(login, password)
                .addProductToCart(secondNameProduct);
        cartPage.openPage();
        Assert.assertEquals(cartPage.getProductPrice(secondNameProduct), "$49.99");
    }

    @Parameters({"login", "password", "firstNameProduct", "firstName", "lastName", "zip"})
    @Test
    public void addInvalidCheckoutStepOneTest(@Optional("standard_user") String login, @Optional("secret_sauce") String password, @Optional("Sauce Labs Onesie") String firstNameProduct, @Optional("ihar") String firstName, @Optional("Malinouski") String lastName, @Optional("123") String zip) {
        loginPage.openPage()
                .login(login, password)
                .addProductToCart(firstNameProduct);
        cartPage.openPage()
                .checkoutClickButton()
                .fillInDataEntryForPayment(firstName, lastName, zip)
                .clickContinueButton();
        Assert.assertEquals("Error: Postal Code is required", checkoutPage.getErrorTextMessage("ERROR_TEXT_EMPTY_FIELD"));
    }
}

