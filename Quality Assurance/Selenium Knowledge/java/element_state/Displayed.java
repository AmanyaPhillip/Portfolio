package element_state;

import org.openqa.selenium.*;

import setup.Main;

public class Displayed extends Main {
  public static void main(String[] args) {
    driver.get("https://www.selenium.dev/selenium/web/web-form.html");// Open the URL in the browser.
    boolean isVisible = driver.findElement(By.name("my-hidden")).isDisplayed();
    // Find the element using its name and check if it is displayed.
    // Check if the element is displayed on the page.
    if (isVisible) {
      System.out.println("The element is displayed on the page.");
    } else {
      System.out.println("The element is not displayed on the page.");
    }
    // Print the result of the visibility check.

    driver.quit();// Close the browser & shuts down the WebDriver instance.
  }

}
