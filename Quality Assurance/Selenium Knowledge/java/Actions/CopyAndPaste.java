package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.interactions.Actions;

import setup.Main;

// This class demonstrates how to perform copy and paste actions using Selenium WebDriver
public class CopyAndPaste extends Main {

  public static void main(String[] args) {
    // Navigate to the test page with input fields
    driver.get("https://www.selenium.dev/selenium/web/formPage.html");

    // Locate the source and target input fields
    var field1 = driver.findElement(By.id("withText"));
    var field2 = driver.findElement(By.id("emptyTextArea"));

    // Determine the correct modifier key for copy/paste based on the OS
    Keys cmdCtrl = Platform.getCurrent().is(Platform.MAC) ? Keys.COMMAND : Keys.CONTROL;

    Actions actions = new Actions(driver);

    // Copy: Select all text in field1 and copy it to clipboard
    actions.click(field1)
        .keyDown(cmdCtrl)
        .sendKeys("a")
        .sendKeys("c")
        .keyUp(cmdCtrl)
        .perform();

    // Paste: Paste the copied text into field2
    actions.click(field2)
        .keyDown(cmdCtrl)
        .sendKeys("v")
        .keyUp(cmdCtrl)
        .perform();

    // Close the browser and end the session
    driver.quit();
  }
}