package wait_strategies;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import setup.Main;

public class explicitWait extends Main {
  public static void main(String[] args) {
    driver.get("https://www.selenium.dev/selenium/web/dynamic.html"); //Open the URL in the browser.

    WebElement textbox = driver.findElement(By.id("revealed")); //Find the text box using its ID.
    driver.findElement(By.id("reveal")).click(); //Find the button using its ID and click it.
    //Wait for the text box to be visible before sending keys to it.
    var wait = new WebDriverWait(driver, Duration.ofSeconds(2)); 
    //Create a new WebDriverWait instance with a timeout of 2 seconds.
    wait.until(ExpectedConditions.visibilityOf(textbox)); //Wait until the text box is visible.
  }

}
