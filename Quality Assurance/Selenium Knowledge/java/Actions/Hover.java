package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import setup.Main;

// This class demonstrates how to perform a mouse hover action using Selenium WebDriver
public class Hover extends Main {

  public static void main(String[] args) {
    // Navigate to the test page with a hoverable element
    driver.get("https://www.selenium.dev/selenium/web/mouseOver.html");

    // Locate the element with ID "redbox"
    var redBox = driver.findElement(By.id("redbox"));
    // Print the background color before hovering
    System.out.println(redBox.getCssValue("background-color"));

    // Perform mouse hover action on the redBox element
    new Actions(driver).moveToElement(redBox).perform();
    // Print the background color after hovering
    System.out.println(redBox.getCssValue("background-color"));

    // Close the browser and end the session
    driver.quit();
  }
}