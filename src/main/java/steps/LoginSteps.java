package steps;

import org.openqa.selenium.WebDriver;
import pages.LoginPage;

public class LoginSteps {

    private LoginPage loginPage;

    public LoginSteps(WebDriver driver) {
        loginPage = new LoginPage(driver);
    }

    public LoginSteps openPageAndClickButtonLogin() {
        loginPage.openPage()
                .waitForElementLocate()
                .errorFieldClickButton();
        return this;
    }

    public LoginSteps openPageAndClickButtonLoginErrorMessage() {
        loginPage.getEmptyPasswordField("Ihar")
                .errorFieldClickButton();
        return this;
    }

    public LoginSteps openPageAndClickButtonLEmptyField() {
        loginPage.getEmptyloginField("Ihar2")
                .errorFieldClickButton();
        return this;
    }
}
