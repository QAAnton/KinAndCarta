import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Task {
    public static void main(String[] args) throws InterruptedException {

        /*
         Task
         Given user navigates to www.amazon.com
         And searched for Alexa
         And navigates to the second page
         And selects the third item
         Then assert that the item would be available for purchase (the user would be able to add it to the cart)
         */


        // I can keep all locators as Strings here, but usually I store them as POM (separate classes for different pages)
        String searchBox_id = "twotabsearchtextbox";
        String nameToSearch = "Alexa";
        String thirdItemResult_XPATH = "(//div[@class='a-section aok-relative s-image-fixed-height'])[3]";
        String addToCartButton_id = "add-to-cart-button";
        String cartButton_id = "nav-cart";
        String itemInCart_xpath = "//span[@class='a-size-medium sc-product-title a-text-bold']";


        // set up of ChromeDriver, can use Firefox, etc
        // Also I follow Singleton pattern, so I would create separate class Driver where I do all the set up
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().fullscreen();

        // execution:
        // user navigates to www.amazon.com
        driver.get("http://www.amazon.com");
        // searched for Alexa
        driver.findElement(By.id(searchBox_id)).sendKeys(nameToSearch + Keys.ENTER);

        String itemAdded = driver.findElement(By.xpath(thirdItemResult_XPATH)).getText();

        //selects the third item
        driver.findElement(By.xpath(thirdItemResult_XPATH)).click();
        Thread.sleep(2000); // wait until page fully loaded
        // user adds item to cart
        driver.findElement(By.id(addToCartButton_id)).click();
        Thread.sleep(2000); // wait until any pop up window shows up (if present)

        if (driver.findElement(By.xpath("//button[@aria-label='Close']")).isDisplayed()) {
            driver.findElement(By.xpath("//button[@aria-label='Close']")).click();
        }

        driver.findElement(By.id(cartButton_id)).click();

        String actualItemInCart = driver.findElement(By.xpath(itemInCart_xpath)).getText();

        if(actualItemInCart.contains(itemAdded)){
            System.out.println("User is able to add item to cart - PASS");
        } else {
            System.out.println("User was NOT able to add item to cart - FAIL");
        }











    }
}
