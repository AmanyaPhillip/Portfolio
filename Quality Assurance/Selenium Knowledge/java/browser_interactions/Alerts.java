package browser_interactions;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import setup.Main;

// This class demonstrates handling different types of browser alerts using Selenium WebDriver.
public class Alerts extends Main {
  
  public static void main(String[] args) {
    // Run all alert handling examples
    alerts();
    confirmations();
    prompts();
    waits();

    // Close the browser after all tests
    driver.quit();
  }

  // Handles a simple alert dialog
  public static void alerts(){
    driver.get("https://www.selenium.dev/selenium/web/alerts.html");
    driver.findElement(By.id("alert")).click(); // Click to trigger alert
    Alert alert = driver.switchTo().alert();    // Switch to alert
    String message = alert.getText();           // Get alert text
    alert.accept();                             // Accept (close) the alert
    System.out.println("Alert message: " + message); // Print alert message
  } 

  // Handles a confirmation dialog (accept and dismiss)
  public static void confirmations() {
      driver.get("https://www.selenium.dev/selenium/web/alerts.html");
      driver.findElement(By.id("confirm")).click(); // Trigger confirmation
      Alert confirmation = driver.switchTo().alert();
      confirmation.accept();   // Accept the confirmation
      confirmation.dismiss();  // Dismiss the confirmation (note: this line may throw if alert is already closed)
  }

  // Handles a prompt dialog (send text and accept)
  public static void prompts(){
      driver.get("https://www.selenium.dev/selenium/web/alerts.html");
      driver.findElement(By.id("prompt")).click(); // Trigger prompt
      Alert prompt = driver.switchTo().alert();
      prompt.sendKeys("Hello, this is a prompt test!"); // Send text to prompt
      prompt.accept(); // Accept the prompt
  }

  // Handles an alert that appears after a delay using explicit wait
  public static void waits(){
      driver.get("https://www.selenium.dev/selenium/web/alerts.html");
      driver.findElement(By.id("slow-alert")).click(); // Trigger delayed alert
      var wait = new WebDriverWait(driver, Duration.ofSeconds(2));
      Alert alert = wait.until(ExpectedConditions.alertIsPresent()); // Wait for alert
      alert.accept(); // Accept the alert
  }
}