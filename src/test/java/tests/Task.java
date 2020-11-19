package tests;

import amazon_pages.AmazonLandingPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Driver;

public class Task {

        @Test
        public void addToCartTest() throws InterruptedException {

        AmazonLandingPage amazonLandingPage = new AmazonLandingPage();
        /*
         Task
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

        // getting text of the 3rd item for comparison later
        String itemAdded = amazonLandingPage.thirdItemResult.getText();

        //selects the third item
        amazonLandingPage.thirdItemResult.click();
        //Thread.sleep(2000); // wait until page fully loaded
        // user adds item to cart
        amazonLandingPage.addToCartButton.click();
        Thread.sleep(3000); // wait until any pop up window shows up (if present)

        if (Driver.getDriver().findElement(By.xpath("//button[@aria-label='Close']")).isDisplayed()) {
            Driver.getDriver().findElement(By.xpath("//button[@aria-label='Close']")).click();
        } else {
            System.out.println("Pop up window wasn't displayed");
        }

        amazonLandingPage.cartButton.click();

        String actualItemInCart = amazonLandingPage.itemInCart.getText();


        // Assertion of item in cart was the item added
        Assert.assertTrue(actualItemInCart.contains(itemAdded));

        Driver.quitDriver();



        }

}
