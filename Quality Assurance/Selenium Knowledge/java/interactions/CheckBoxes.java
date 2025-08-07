package interactions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import setup.Main;

public class CheckBoxes extends Main {
    public static void main(String[] args) {
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");//Open the URL in the browser.
        WebElement checkbox = driver.findElement(By.id("my-check-1"));//Find the checkbox using its ID.
        if (!checkbox.isSelected()) {//Check if the checkbox is not selected.
            checkbox.click();//Click the checkbox to select it.
        }

        driver.quit();//Close the browser & shuts down the WebDriver instance.
    }

}
