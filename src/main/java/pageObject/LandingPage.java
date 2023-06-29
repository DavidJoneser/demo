package pageObject;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent {

    // here Landing page is inheriting the parent class (AbstractComponent)
    // so all page object classes created should extend AbstractComponent class

    WebDriver driver;

    //Constructor initialization for driver usage
    public LandingPage(WebDriver driver) {
    // super keyword send driver from child into parent (this class into AbstractComponent (his parent))
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //create pageFactory WebElements using keyword FindBy
    @FindBy(id = "userEmail")
    WebElement userEmail;
    @FindBy(id = "userPassword")
    WebElement userPassword;
    @FindBy(id = "login")
    WebElement submit;

    //define method to perform a login into page, then arguments within the method for login not using a hard-coded:.
    // Those are called: Actions Methods.
    public ProductCatalog loginApplication(String email, String password) {
        //here we use the WebElements via pageFactory class and send as arguments the email and password written in standAlone class
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        submit.click();
        ProductCatalog productCatalog = new ProductCatalog(driver);
        return productCatalog;
    }


    public void goTo() {
        // here we hard-code the app URL
        driver.get("https://rahulshettyacademy.com/client/");
    }


}

// this constructor define the driver of this class
// (the driver is taken from stanAloneTest class by creating object of this class there)