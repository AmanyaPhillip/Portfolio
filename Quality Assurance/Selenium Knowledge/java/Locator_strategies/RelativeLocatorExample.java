import org.openqa.selenium.*;
import org.openqa.selenium.support.locators.RelativeLocator;

import setup.Main;

public class RelativeLocatorExample extends Main {
    public static void main(String[] args) {
        driver.get("https://www.selenium.dev/selenium/web/formPage.html");//Open the URL in the browser.
        WebElement bottomButton = driver.findElement(
          RelativeLocator.with(By.name("submit")).below(By.id("submit")));
          //Find the button below the submit button using relative locator.
      
        driver.quit();//Close the browser & shuts down the WebDriver instance.
    }
    //Relative locators are used to find elements based on their position relative to other elements on the page.

}
