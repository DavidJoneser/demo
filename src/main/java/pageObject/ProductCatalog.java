package pageObject;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalog extends AbstractComponent {
    WebDriver driver;

    public ProductCatalog(WebDriver driver) {
        //super keyword to initialize driver
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    //this By locator replaces this code "By.cssSelector" this was originally:
    // (wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.col-lg-4")));)

    //this locator for loader called "Spinner"

    // here we explicitly wait for the products located by "productBy"
    By prodcutsBy = By.cssSelector(".mb-3");
    By addToCart = By.cssSelector("div.card-body button:last-of-type");
    By toastMessage = By.cssSelector("#toast-container");

    // getProductList = we explicitly wait for the products to render on page then return the products

    @FindBy(css = ".mb-3")
    List<WebElement> products;
    @FindBy(css= ".ng-animating")
    WebElement spinner;
    @FindBy(css="[routerlink*='cart']")
    WebElement cartBtn;


    public List<WebElement> getProductList()
    {
        waitForElementToAppear(prodcutsBy);
        return products;
    }
    public WebElement getProductByName(String productName) {
        WebElement prod = getProductList()
                .stream()
                .filter(product ->
                        product.findElement(By.cssSelector("b"))
                                .getText()
                                .equals(productName))
                .findFirst()
                .orElse(null);
        return prod;
    }


    //product.findElement(By.cssSelector("div.card-body button:nth-of-type(2)")).click();
    // this product is not from type (driver.findElement(By)) => not feasible to use FindBy class.
    // so we simply use By locator = By "String name " = css (id = "abc")
    // locator is written above.

    public void addProductToCart(String productName) {
        WebElement prod = getProductByName(productName);
        prod.findElement(addToCart).click();
        //was : wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        //here we wait for invisibility of the element
        waitForElementToAppear(toastMessage);
        //was: wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
        // here we wait for invisibility of the element
        waitForElementToDisappear(spinner);
    }

    //driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

}

