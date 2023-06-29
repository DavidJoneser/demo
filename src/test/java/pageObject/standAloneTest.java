package pageObject;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

// this class will move the driver into page object class by creating a constructor.
// in case AbstractComponent class will use a driver then LandingPage which is a child of AbstractComponent
// by extends keyword will use super keyword to move the driver into parent class.


public class standAloneTest {
        public static void main(String[] args) throws InterruptedException {
        String productName = "ZARA COAT 3";
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
        LandingPage landingPage = new LandingPage(driver);
        landingPage.goTo();
        ProductCatalog productCatalog = landingPage.loginApplication("david@botmail.com", "David123!");
        List<WebElement> products = productCatalog.getProductList();
        productCatalog.addProductToCart(productName);
        CartPage cartPage = productCatalog.goToCartPage();
        Boolean match = cartPage.VerifyProductDisplay(productName);
        Assert.assertTrue(match);
        //cartPage.scroll();
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.SelectCountry("India");
        checkoutPage.submitOrder();
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();
        String confirmationMessage = confirmationPage.getConfirmationMessege();
        Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        driver.close();
        }
}
