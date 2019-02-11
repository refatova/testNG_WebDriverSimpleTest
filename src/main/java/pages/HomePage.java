package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isElementPresent(String id) {
        System.out.println(String.format("//tr[@class='heading3']/td[.='Manger Id : %s ']", id));
        WebElement element = driver.findElement(By.xpath("//tr[@class='heading3']/td[.='Manger Id : " + id + "']"));
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.isDisplayed();
    }
}
