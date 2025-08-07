package wait_strategies;
import java.time.Duration;

import org.openqa.selenium.*;

import setup.Main;

public class ImplicitWaits extends Main {
  public static void main(String[] args) {
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2)); //Set implicit wait time to 2 seconds.
    //This means that if an element is not found immediately, the WebDriver will wait up to 2 seconds before 
    //throwing a NoSuchElementException.
    driver.get("https://www.selenium.dev/selenium/web/dynamic.html"); //Open the URL in the browser.
    driver.findElement(By.id("reveal")).click(); //Find the button using its ID and click it.
    driver.findElement(By.id("revealed")).sendKeys("I see you!");
     //Find the text box using its ID and send the text "I see you!" to it.)

  }  

}
