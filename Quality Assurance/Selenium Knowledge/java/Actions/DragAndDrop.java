package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import setup.Main;

// This class demonstrates how to perform a drag-and-drop action using Selenium WebDriver
public class DragAndDrop extends Main {

  public static void main(String[] args) {
    // Navigate to the test page with draggable lists
    driver.get("https://www.selenium.dev/selenium/web/draggableLists.html");

    // Locate the source and target elements for drag-and-drop
    WebElement leftItem = driver.findElement(By.id("leftitem-1"));
    WebElement rightItem = driver.findElement(By.id("rightitem-1"));
    // Perform drag-and-drop from leftItem to rightItem
    new Actions(driver).dragAndDrop(leftItem, rightItem).perform();
    // Print the text of all items in the right list after the operation
    var rightItems = driver.findElements(By.cssSelector("#sortable2 li"));
    rightItems.forEach(e -> System.out.println(e.getText()));

    // Close the browser and end the session
    driver.quit();
  }
}