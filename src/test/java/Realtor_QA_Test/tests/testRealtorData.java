package Realtor_QA_Test.tests;

import Realtor_QA_Test.pages.realtorDetailsPage;
import Realtor_QA_Test.pages.realtorHomePage;
import Realtor_QA_Test.pages.realtorListingsPage;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

// class to test content on www.realtor.com

public class testRealtorData {

    private WebDriver driver;

    // Function to load and initialize driver for different browsers

    @Parameters("currentBrowser")
    @BeforeTest
    public void initialize_Driver(String currentBrowser) throws Exception {

        if(currentBrowser.equalsIgnoreCase("chrome")) {

            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
            driver = new ChromeDriver();
        }
        else if (currentBrowser.equalsIgnoreCase("firefox")) {

            System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
            driver = new FirefoxDriver();
        }
        else{

            //Throw error if browser not detected
            throw new Exception("Browser cannot be detected!");
        }

        driver.manage().window().setSize(new Dimension(1024,768));

        // Navigate to the home page

        driver.get("https://www.realtor.com/");

    }

    // Test scripts to verify Home Page

    @Test
    public void testRealtor_HomePage() {

        // creating object for Home Page

        realtorHomePage homePageObj = new realtorHomePage(driver);

        // Navigating to realtor.com and enter Morgantown, WV in search box

        String location = "Morgantown, WV";

        homePageObj.searchLocation(location);

        // Get the total number of property listings available at a particular day

        homePageObj.getHouseListings();

    }

    // Test scripts to verify Listings and Details Page
    @Test
    public void testRealtor_Listings_And_Details_Page() {

        // creating objects for Listings and Details page

        realtorListingsPage listingsPageObj = new realtorListingsPage(driver);

        realtorDetailsPage detailsPageObj = new realtorDetailsPage(driver);

        // get total number of available House listings

        int total_Listings = listingsPageObj.getTotalListings();

        System.out.println("Current total house listings: " + total_Listings);

        // Verifying that the number of house listings is greater than 0

        Assert.assertTrue(total_Listings > 0, "Verified! Number of house listings greater than 0.");

        // Get the price of second search result

        String price_Of_Second_Search = listingsPageObj.getPrice();

        System.out.println("Price of the property from Second Search Result: " + price_Of_Second_Search);

        // click the address of second search result

        listingsPageObj.clickAddressOfSecondSearch();

        // Get the price of the second search result from the "View Details" page

        String price_Of_Second_Details = detailsPageObj.getPrice();

        System.out.println("Price of the property from the Details page: " + price_Of_Second_Details);

        // Verifying the price displayed on the search result page matches price on the “View Details” page

        Assert.assertTrue(price_Of_Second_Search.equals(price_Of_Second_Details), "Price Validated! Price is same in both the sections.");

    }

    // Close the driver after tests completed
    @AfterTest
    public void close_Driver(){

        driver.quit();
    }

}
