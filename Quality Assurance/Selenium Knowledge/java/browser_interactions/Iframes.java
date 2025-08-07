package browser_interactions;

import org.openqa.selenium.By;

import setup.Main;

// This class demonstrates interacting with elements inside an iframe using Selenium WebDriver
public class Iframes extends Main {

  public static void main(String[] args) {
    // Navigate to the test page containing an iframe
    driver.get("https://www.selenium.dev/selenium/web/click_tests/click_in_iframe.html");

    // Switch to the iframe with the name or ID "ifr"
    driver.switchTo().frame("ifr");
    // Find the element with ID "link" inside the iframe and click it
    driver.findElement(By.id("link")).click();
    // Switch back to the main document content
    driver.switchTo().defaultContent();

    // Close the browser and end the session
    driver.quit();
  }
}