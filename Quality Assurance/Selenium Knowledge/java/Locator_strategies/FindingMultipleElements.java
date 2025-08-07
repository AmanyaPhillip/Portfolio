import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import setup.Main;

public class FindingMultipleElements extends Main {
    public static void main(String[] args) {
        driver.get("https://www.selenium.dev/selenium/web/formPage.html");//Open the URL in the browser.
        List<WebElement> radioButtons = 
          driver.findElements(By.cssSelector("input[type='radio']")); //Find all radio buttons using CSS selector.
        var elements = driver.findElements(By.tagName("input")); //Find all input elements on the page.
        System.out.println("Number of input elements: " + radioButtons.size()); //Print the number of input elements found.
        driver.quit();//Close the browser & shuts down the WebDriver instance.
    }

}
