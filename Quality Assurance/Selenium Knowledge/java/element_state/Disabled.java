package element_state;

import org.openqa.selenium.*;

import setup.Main;

public class Disabled extends Main {
  public static void main(String[] args) {
    driver.get("https://www.selenium.dev/selenium/web/web-form.html");// Open the URL in the browser.
    boolean isDisabled = driver.findElement(By.name("my-disabled")).isEnabled();
    // Find the element using its name and check if it is enabled.
    // Check if the element is enabled on the page.
    if (isDisabled) {
      System.out.println("The element is enabled on the page.");
    } else {
      System.out.println("The element is disabled on the page.");
    }
    // Print the result of the enabled check.

    driver.quit();// Close the browser & shuts down the WebDriver instance.
  }

}
