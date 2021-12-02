package steps;

import org.openqa.selenium.WebDriver;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductDetailsPage;
import pages.ProductsPage;

public class CartSteps {

    private LoginPage loginPage;
    private ProductsPage productsPage;
    private ProductDetailsPage productDetailsPage;
    private CartPage cartPage;

    public CartSteps(WebDriver driver) {
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
        cartPage = new CartPage(driver);
    }

    public CartSteps loginAnfClickInImageAndAddProduct(String login, String password, String secondNameProduct) {
        loginPage.openPage()
                .login(login, password)
                .clickOnProductImage(secondNameProduct)
                .addProductToCartFromDetails(secondNameProduct);
        cartPage.openPage();
        return this;
    }

    public CartSteps openPageAndDeleteProduct(String secondNameProduct) {
        cartPage.openPage()
                .deleteProductFromCart(secondNameProduct);
        return this;
    }
}
