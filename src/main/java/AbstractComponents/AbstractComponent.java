package AbstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.CartPage;

import java.time.Duration;

public class AbstractComponent {
    // this will be the parent class to all pageObject classes (because it holds reusable methods.)
    //local instance
    WebDriver driver;

    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);

    }

    //FindBy locators:
    @FindBy(css="[routerlink*='cart']")
    WebElement cartHeader;


    public void waitForElementToAppear(By findBy) {
        //using By locator and not driverFindElement
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }
    public void waitForElementToDisappear(WebElement ele) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(ele));
    }

    public CartPage goToCartPage(){
        //click on the cart Btn
        cartHeader.click();
        CartPage cartPage = new CartPage(driver);
        return cartPage;
    }
    public void scollDown(WebElement element)
    {
      JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
      jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

}