package amazon_pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;


public class AmazonLandingPage {
    public AmazonLandingPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy (id = "twotabsearchtextbox")
    public WebElement searchBox;

    @FindBy (xpath = "(//div[@class='a-section aok-relative s-image-fixed-height'])[3]")
    public WebElement thirdItemResult;

    @FindBy (id = "add-to-cart-button")
    public WebElement addToCartButton;

    @FindBy (id = "nav-cart")
    public WebElement cartButton;

    @FindBy (xpath = "//span[@class='a-size-medium sc-product-title a-text-bold']")
    public WebElement itemInCart;
}
