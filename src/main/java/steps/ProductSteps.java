package steps;

import org.openqa.selenium.WebDriver;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;

public class ProductSteps {

    private LoginPage loginPage;
    private ProductsPage productsPage;
    private CartPage cartPage;

    public ProductSteps(WebDriver driver) {
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
    }

    public ProductSteps loginAndAddProductToCart(String login, String password, String secondNameProduct) {
        loginPage.openPage()
                .login(login, password)
                .addProductToCart(secondNameProduct);
        cartPage.openPage();
        return this;
    }

    public ProductSteps loginAndAddFirstAbdSecondProductToCart(String login, String password, String secondNameProduct, String firstNameProduct) {
        loginPage.openPage()
                .login(login, password)
                .addProductToCart(secondNameProduct)
                .addProductToCart(firstNameProduct);
        return this;
    }

    public ProductSteps loginAndAddFirstdProductToCart(String login, String password, String firstNameProduct) {
        loginPage.openPage()
                .login(login, password)
                .addProductToCart(firstNameProduct);
        return this;
    }
}
