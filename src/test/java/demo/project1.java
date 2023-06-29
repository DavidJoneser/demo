package demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class project1 {
     public static void main(String[] args) throws InterruptedException {

          String productName = "ZARA COAT 3";
          WebDriverManager.chromedriver().setup();
          WebDriver driver = new ChromeDriver();
          driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
          WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
          driver.manage().window().maximize();
          driver.get("https://rahulshettyacademy.com/client/");
          driver.findElement(By.id("userEmail")).sendKeys("david@botmail.com");
          driver.findElement(By.id("userPassword")).sendKeys("David123!");
          driver.findElement(By.id("login")).click();
//wait for element render
          wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.col-lg-4")));
//click to add product into cart
          List<WebElement> products = driver.findElements(By.cssSelector("div.col-lg-4"));
          WebElement product = products.stream().filter(p -> p.findElement(By.tagName("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
          assert product != null;
          product.findElement(By.cssSelector("div.card-body button:nth-of-type(2)")).click();
//wait for toast msg
          wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
//wait render
          wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
//go into cart page
          driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
//let's write logic to identify if correct product got into the
// cart out of all products within cart
          List<WebElement> list = driver.findElements(By.cssSelector(".cart h3"));
          Boolean match = list.stream().anyMatch(a -> a.getText().equalsIgnoreCase(productName));
          Assert.assertTrue(match);
//click on checkout Button
          driver.findElement(By.cssSelector(".totalRow button.btn.btn-primary")).click();
          Actions actions = new Actions(driver);
//send into dynamic drop-down value of country => build and perform
          actions.sendKeys(driver.findElement(By.cssSelector(".text-validated:nth-child(1)")), "Afghanistan").build().perform();
// wait for the results to render
          wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results.list-group.ng-star-inserted")));
//if option at drop-down display you click it
          driver.findElement(By.cssSelector(".ta-results button")).click();
//scroll into the submit button at UI
          WebElement element = driver.findElement(By.cssSelector("a.action__submit"));
          JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
          jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
//finally click submit
          driver.findElement(By.cssSelector("a.action__submit")).click();
          // confirm last confirmation message
          String confirmation =  driver.findElement(By.cssSelector("h1.hero-primary")).getText();
          Assert.assertTrue(confirmation.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
          driver.close();
     }
}