package Realtor_QA_Test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class realtorDetailsPage {

    private WebDriver driver;

    private WebDriverWait wait;

    // get css selectors and xpath for the elements needed to access

    private By priceOnDetailsPage = By.id("ldp-pricewrap");

    public realtorDetailsPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,1000);
    }

    // get price of a particular house

    public String getPrice() {

        wait.until(ExpectedConditions.visibilityOfElementLocated((priceOnDetailsPage)));
        return driver.findElement(priceOnDetailsPage).getText();

    }
}
