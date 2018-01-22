package Realtor_QA_Test;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

// Class containing test scripts to verify data on www.realtor.com

public class realtorData {

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

    }

    // Function to write test scripts for the given scenario

    @Test
    public void verify_With_Given_Conditions() {

        // Navigating to realtor.com and enter Morgantown, WV in search box

        driver.get("https://www.realtor.com/");
        driver.findElement(By.id("searchBox")).clear();
        driver.findElement(By.id("searchBox")).sendKeys("Morgantown, WV");

        driver.findElement(By.className("ra-search")).click();

        // wait until search result page is visible

        WebDriverWait wait = new WebDriverWait(driver,1000);

        wait.until(ExpectedConditions.visibilityOfElementLocated((By.className("srp-list-header-count"))));

        // Get the total number of property listings available ata particular point

        String get_Total_Listings = driver.findElement(By.className("srp-list-header-count")).getText();

        int total_Listings = Integer.parseInt(get_Total_Listings.replaceAll("[\\D]", ""));

        System.out.println();
        System.out.println("Current total house listings: " + total_Listings);
        System.out.println();

        // Verifying that the number of house listings is greater than 0

        if(total_Listings > 0){
            System.out.println("Verified! Number of house listings greater than 0.");
        } else {
            System.out.println("Not verified");
        }

        System.out.println();

        // Get the price of second search result

        String price_Of_Second_Search = driver.findElement(By.xpath("//*[@id=\"2\"]/div[2]/div[2]/span")).getText();

        System.out.println("Price of the property from Second Search Result: " + price_Of_Second_Search);


        // Get and click on the address of second search result

        driver.findElement(By.xpath("//*[@id=\"2\"]/div[2]/div[3]/a/span[1]")).click();

        // Get the price of the house from the "View Details" page

        String price_Of_Second_Details= driver.findElement(By.id("ldp-pricewrap")).getText();

        System.out.println("Price of the property from the Details page: " + price_Of_Second_Details);

        // Verifying the price displayed on the search result page matches price on the “View Details” page

        if(price_Of_Second_Search.equals(price_Of_Second_Details)){

            System.out.println("Price Validated! Price is same in both the sections.");
        }
        else{

            System.out.println("Price varies");
        }
        System.out.println();
    }

    // Close the driver after tests completed
    @AfterTest
    public void close_Driver(){

        driver.quit();
    }
}