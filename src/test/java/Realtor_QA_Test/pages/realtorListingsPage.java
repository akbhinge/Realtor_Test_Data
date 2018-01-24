package Realtor_QA_Test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// class to access elements on the house listings page

public class realtorListingsPage {

    private WebDriver driver;

    private WebDriverWait wait;

    // get css selectors and xpath for the elements needed to access

    private By searchTotal = By.className("srp-list-header-count");

    private By searchSecond = By.xpath("//*[@id=\"2\"]/div[2]/div[2]/span");

    private By addressSecond = By.xpath("//*[@id=\"2\"]/div[2]/div[3]/a/span[1]");

    public realtorListingsPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,1000);
    }

    // Get the total number of property listings

    public int getTotalListings(){


        // wait until the Listings page is visible

        wait.until(ExpectedConditions.visibilityOfElementLocated((searchTotal)));

        String get_Total_Listings = driver.findElement(searchTotal).getText();

        return (Integer.parseInt(get_Total_Listings.replaceAll("[\\D]", "")));

    }

    // get the price of the 2nd House from the search result
    public String getPrice() {

        wait.until(ExpectedConditions.visibilityOfElementLocated((searchSecond)));

        return driver.findElement(searchSecond).getText();

    }

    // get the address of the second search and click it
    public void clickAddressOfSecondSearch() {

        driver.findElement(addressSecond).click();
    }

}
