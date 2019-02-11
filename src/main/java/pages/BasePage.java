package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    WebDriver driver;
    WebDriverWait wait;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 5);
    }

    public String getTitle(){
        return driver.getTitle();
    }

    public void waitUntilElementWillBeVisible(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}
