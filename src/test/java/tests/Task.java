package tests;

import amazon_pages.AmazonLandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import utilities.Driver;

public class Task {
    public static void main(String[] args) throws InterruptedException {

        AmazonLandingPage amazonLandingPage = new AmazonLandingPage();
        /*
         tests.Task
         Given user navigates to www.amazon.com
         And searched for Alexa
         And navigates to the second page
         And selects the third item
         Then assert that the item would be available for purchase (the user would be able to add it to the cart)
         */

        String nameToSearch = "Alexa";

        // Execution
        // user navigates to www.amazon.com
        Driver.getDriver().get("http://www.amazon.com");
        // searched for Alexa
        amazonLandingPage.searchBox.sendKeys(nameToSearch + Keys.ENTER);

        String itemAdded = Driver.getDriver().findElement(By.xpath(thirdItemResult_XPATH)).getText();

        //selects the third item
        Driver.getDriver().findElement(By.xpath(thirdItemResult_XPATH)).click();
        Thread.sleep(2000); // wait until page fully loaded
        // user adds item to cart
        Driver.getDriver().findElement(By.id(addToCartButton_id)).click();
        Thread.sleep(2000); // wait until any pop up window shows up (if present)

        if (Driver.getDriver().findElement(By.xpath("//button[@aria-label='Close']")).isDisplayed()) {
            Driver.getDriver().findElement(By.xpath("//button[@aria-label='Close']")).click();
        }

        Driver.getDriver().findElement(By.id(cartButton_id)).click();

        String actualItemInCart = Driver.getDriver().findElement(By.xpath(itemInCart_xpath)).getText();

        if(actualItemInCart.contains(itemAdded)){
            System.out.println("User is able to add item to cart - PASS");
        } else {
            System.out.println("User was NOT able to add item to cart - FAIL");
        }











    }
}
