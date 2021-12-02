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
    @Link("TMS-1")
    @Issue("TMS-2")
    @TmsLink("TMS-3")
    public void addProductToCartTest(@Optional("standard_user") String login, @Optional("secret_sauce") String password, @Optional("Sauce Labs Onesie") String secondNameProduct) {
        productSteps.loginAndAddProductToCart(login, password, secondNameProduct);
        Assert.assertEquals(cartPage.getProductPrice(secondNameProduct), "$7.99");
    }

    @Parameters({"login", "password", "firstNameProduct"})
    @Test(retryAnalyzer = Retry.class)
    @Description("Проверка цены продукта по имени на странице cartPage")
    @Link("TMS-1")
    @Issue("TMS-2")
    @TmsLink("TMS-3")
    public void priceComparisonInDetailTest(@Optional("standard_user") String login, @Optional("secret_sauce") String password, @Optional("Sauce Labs Onesie") String firstNameProduct) {
        productSteps.loginAndAddProductToCart(login, password, firstNameProduct);
        Assert.assertEquals(cartPage.getProductPrice(firstNameProduct), "$7.99");
    }

    @Parameters({"login", "password", "firstNameProduct", "secondNameProduct"})
    @Test(retryAnalyzer = Retry.class)
    @Description("Проверка удаления второй добавленной книги на странице cartPage")
    @Link("TMS-1")
    @Issue("TMS-2")
    @TmsLink("TMS-3")
    public void addingTwoBooksTest(@Optional("standard_user") String login, @Optional("secret_sauce") String password, @Optional("Sauce Labs Fleece Jacket") String secondNameProduct, @Optional("Sauce Labs Onesie") String firstNameProduct) {
        productSteps.loginAndAddFirstAbdSecondProductToCart(login, password, secondNameProduct, firstNameProduct);
        cartSteps.openPageAndDeleteProduct(secondNameProduct);
        Assert.assertEquals(cartPage.getQuantityIconCart(), "1");
    }

    @Parameters({"login", "password", "secondNameProduct"})
    @Test(retryAnalyzer = Retry.class)
    @Description("Проверка наличия продукта по имени на странице cartPage")
    @Link("TMS-1")
    @Issue("TMS-2")
    @TmsLink("TMS-3")
    public void productComparisonOnTheDetailsPageTest(@Optional("standard_user") String login, @Optional("secret_sauce") String password, @Optional("Sauce Labs Onesie") String secondNameProduct) {
        cartSteps.loginAnfClickInImageAndAddProduct(login, password, secondNameProduct);
        Assert.assertEquals(cartPage.getQuantityLabel(secondNameProduct), cartPage.getQuantityIconCart());
    }

    @Test(retryAnalyzer = Retry.class)
    @Description("Проверка текста валидационного сообщения на странице loginPage")
    @Link("TMS-1")
    @Issue("TMS-2")
    @TmsLink("TMS-3")
    public void unsuccessfulAuthorizationTest() {
        loginSteps.openPageAndClickButtonLogin();
        Assert.assertEquals("Epic sadface: Username is required", loginPage.getErrorText("ERROR_TEXT_POP_UP"));
        loginSteps.openPageAndClickButtonLoginErrorMessage();
        Assert.assertEquals("Epic sadface: Password is required", loginPage.getErrorText("ERROR_TEXT_POP_UP"));
        loginSteps.openPageAndClickButtonLEmptyField();
        Assert.assertEquals("Epic sadface: Username and password do not match any user in this service", loginPage.getErrorText("ERROR_TEXT_POP_UP"));
    }

//    @Parameters({"login", "password", "secondNameProduct"})
//    @Test(retryAnalyzer = Retry.class)
//    @Description("Проверка соответсвия цены по имени продукта на странице cartPage")
//    @Link("TMS-1")
//    @Issue("TMS-2")
//    @TmsLink("TMS-3")
//    public void addProductToCartThisPageFactoryTest(@Optional("standard_user") String login, @Optional("secret_sauce") String password, @Optional("Sauce Labs Onesie") String secondNameProduct) {
//        productSteps.loginAndAddProductToCart(login, password, secondNameProduct);
//        Assert.assertEquals(cartPage.getProductPrice(secondNameProduct), "$49.99");
//    }
//S
    @Parameters({"login", "password", "firstNameProduct", "firstName", "lastName", "zip"})
    @Test(retryAnalyzer = Retry.class)
    @Description("Проверка соответсвия валидационного сообщения на странице checkoutPage")
    @Link("TMS-1")
    @Issue("TMS-2")
    @TmsLink("TMS-3")
    public void addInvalidCheckoutStepOneTest(@Optional("standard_user") String login, @Optional("secret_sauce") String password, @Optional("Sauce Labs Onesie") String firstNameProduct, @Optional("ihar") String firstName, @Optional("Malinouski") String lastName, @Optional("123") String zip) {
        productSteps.loginAndAddFirstdProductToCart(login, password, firstNameProduct);
        checkoutSteps.clickButtonCheckoutAndInputField(firstName, lastName, zip);
        Assert.assertEquals("Error: Postal Code is required", checkoutPage.getErrorTextMessage("ERROR_TEXT_EMPTY_FIELD"));
    }
}

