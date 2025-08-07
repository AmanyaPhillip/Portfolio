package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import setup.Main;

// This class demonstrates how to perform a double-click action using Selenium WebDriver
public class DoubleClick extends Main {

  public static void main(String[] args) {
    // Navigate to the test page with a double-clickable element
    driver.get("https://selenium.dev/selenium/web/mouse_interaction.html");

    // Locate the element with ID "clickable"
    var input = driver.findElement(By.id("clickable"));
    // Perform a double-click action on the input element
    new Actions(driver).doubleClick(input).perform();
    // Print the text of the element with ID "click-status" after double-clicking
    System.out.println(driver.findElement(By.id("click-status")).getText());

    // Close the browser and end the session
    driver.quit();
  }
}