package interactions;

import org.openqa.selenium.*;

import setup.Main;

public class TextBoxes extends Main{
    public static void main(String[] args) {
        driver.get("https://www.selenium.dev/selenium/web/formPage.html");//Open the URL in the browser.
        
        WebElement textbox = driver.findElement(By.name("id-name1"));//Find the text box using its ID.
        textbox.getText();//Get the text from the text box.
        textbox.clear();//Clear the text box.
        textbox.sendKeys("Hello World!");//Send the text "Hello World!" to the text box.

        driver.quit();//Close the browser & shuts down the WebDriver instance.


  }
}
