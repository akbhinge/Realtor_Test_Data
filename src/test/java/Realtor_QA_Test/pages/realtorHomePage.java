package Realtor_QA_Test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// class to access elements on the Home page of www.realtor.com

public class realtorHomePage {

    private WebDriver driver;

    // get css selectors and xpath needed to access the elements on the web page

    private By searchHouse = By.id("searchBox");

    private By searchList = By.className("ra-search");

    public realtorHomePage(WebDriver driver){
        this.driver = driver;
    }

    // enter the location in the search box
    public void searchLocation(String location){
        driver.findElement(searchHouse).clear();
        driver.findElement(searchHouse).sendKeys(location);
    }

    // click the search icon to get total listings for a location
    public void getHouseListings() {
        driver.findElement(searchList).click();
    }

}
