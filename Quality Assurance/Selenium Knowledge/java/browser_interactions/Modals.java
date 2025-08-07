package browser_interactions;

import org.openqa.selenium.*;

import setup.Main;

public class Modals extends Main {
    public static void main(String[] args) {
      driver.get("https://www.selenium.dev/selenium/web/modal_dialogs/modern_modal.html");
      driver.findElement(By.id("trigger-modal-btn")).click();
      //Find the button using its ID and click it to open the modal dialog.
      WebElement modal = driver.findElement(By.id("modalContent"));
      if (modal.isDisplayed()) {
          System.out.println("Modal is displayed");
          //Check if the modal is displayed.
          //Print a message if the modal is displayed.
          driver.findElement(By.id("modal-input")).sendKeys("hey modal");
          driver.findElement(By.id("modal-close")).click(); 
          //Find the input field in the modal and send the text "hey modal" to it.
          //Find the close button in the modal and click it to close the modal dialog.
        } else {
          System.out.println("Modal is not displayed");
          //Print a message if the modal is not displayed.
        
      }
    }

}
