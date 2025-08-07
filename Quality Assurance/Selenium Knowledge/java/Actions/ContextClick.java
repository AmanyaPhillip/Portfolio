package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import setup.Main;

// This class demonstrates how to perform a right-click (context click) action using Selenium WebDriver
public class ContextClick extends Main {

  public static void main(String[] args) {
    // Navigate to the test page with a context-clickable element
    driver.get("https://selenium.dev/selenium/web/mouse_interaction.html");

    // Locate the element with ID "clickable"
    var input = driver.findElement(By.id("clickable"));
    // Perform a right-click (context click) action on the input element
    new Actions(driver).contextClick(input).perform();
    // Print the text of the element with ID "click-status" after right-clicking
    System.out.println(driver.findElement(By.id("click-status")).getText());

    // Close the browser and end the session
    driver.quit();
  }
}