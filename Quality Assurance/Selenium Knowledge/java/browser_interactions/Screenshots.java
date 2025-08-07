package browser_interactions;

import setup.Main;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.browsingcontext.BrowsingContext;
import org.openqa.selenium.remote.RemoteWebElement;

public class Screenshots extends Main {

  public static void main(String[] args) {
    // Navigate to the Selenium web form page
    driver.get("https://www.selenium.dev/selenium/web/web-form.html");
    // Create a BrowsingContext for advanced screenshot features
    var browsingContext = new BrowsingContext(driver, driver.getWindowHandle());

    // FULL PAGE SCREENSHOT
    String fullScreenshot = browsingContext.captureScreenshot();
    saveScreenshot(fullScreenshot, "full_screenshot.png");

    // ELEMENT SCREENSHOT
    WebElement colorPicker = driver.findElement(By.name("my-colors"));
    // Get the internal element ID required for element screenshot
    String internalElementId = ((RemoteWebElement) colorPicker).getId();
    String elementScreenshot = browsingContext.captureElementScreenshot(internalElementId);
    saveScreenshot(elementScreenshot, "element_screenshot.png");

    // VIEWPORT SCREENSHOT (specific area)
    driver.findElement(By.name("my-date")).click(); // Open the date picker
    var datePicker = driver.findElement(By.className("datepicker")).getRect();
    String viewportScreenshot = browsingContext.captureBoxScreenshot(
        datePicker.getX(),
        datePicker.getY(),
        datePicker.getWidth(),
        datePicker.getHeight());

    saveScreenshot(viewportScreenshot, "viewport_screenshot.png");

    // Close the browser and end the session
    driver.quit();
  }

  // Helper method to decode a Base64 screenshot and save it to a file
  private static void saveScreenshot(String screenshot, String filename) {
    var decodedScreenshot = Base64.getDecoder().decode(screenshot);
    try {
      String path = "/workspaces/test-automation-with-selenium-webdriver-for-java-2124033/screenshots/";
      Files.write(Paths.get(path + filename), decodedScreenshot);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}