package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import setup.Main;

// This class demonstrates how to perform scrolling actions using Selenium WebDriver
public class Scroll extends Main {

  public static void main(String[] args) {

    // Scroll to a specific element on the page
    driver.get("https://www.selenium.dev/selenium/web/scroll.html");
    var line9 = driver.findElement(By.id("line9"));
    new Actions(driver).scrollToElement(line9).perform();

    // Scroll by a specific amount (vertically by the height of the page)
    driver.get("https://www.selenium.dev/selenium/web/scrolling_tests/page_with_tall_frame.html");
    var height = driver.findElement(By.tagName("body")).getSize().getHeight();
    new Actions(driver).scrollByAmount(0, height).perform();

    // Close the browser and end the session
    driver.quit();
  }
}