package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage{

    String baseUrl = "http://www.demo.guru99.com/V4/";

    public LoginPage(WebDriver driver) {
        super(driver);
        driver.get(baseUrl);
    }

    @FindBy(name = "uid")
    WebElement login;

    @FindBy(name = "password")
    WebElement password;

    @FindBy(name = "btnLogin")
    WebElement submitButton;


    public LoginPage typeLogin(String value){
        login.sendKeys(value);
        return this;
    }

    public LoginPage typePassword(String pass){
        waitUntilElementWillBeVisible(password);
        password.sendKeys(pass);
        return this;
    }

    public HomePage submitLoginForm(){
        waitUntilElementWillBeVisible(submitButton);
        submitButton.click();
        return PageFactory.initElements(driver, HomePage.class);
    }

}
