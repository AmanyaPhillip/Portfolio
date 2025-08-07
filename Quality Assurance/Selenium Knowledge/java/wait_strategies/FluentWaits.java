package wait_strategies;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import setup.Main;

public class FluentWaits extends Main {
    public static void main(String[] args) {
      driver.get("https://www.selenium.dev/selenium/web/dynamic.html"); //Open the URL in the browser.
      driver.findElement(By.id("adder")).click(); //Find the button using its ID and click it.
      var wait = new FluentWait<>(driver)
        .withTimeout(Duration.ofSeconds(5))
        .pollingEvery(Duration.ofMillis(200))
        .ignoring(NoSuchElementException.class);
      //Create a new FluentWait instance with a timeout of 5 seconds and polling interval of 200 milliseconds.
      //The wait will ignore NoSuchElementException while waiting for the condition to be met.  

      WebElement box = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("box0")));
      //Wait until the element with ID "box0" is present in the DOM.
    }

}
