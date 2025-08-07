package interactions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import setup.Main;

public class DropDownMenu extends Main {
    public static void main(String[] args) {
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");//Open the URL in the browser.
        Select dropdown = new Select(driver.findElement(By.name("my-select")));
        dropdown.selectByValue("2");//Select the option with value "2" from the dropdown menu.
        dropdown.selectByVisibleText("Three");//Select the option with visible text "three" from the dropdown menu.
        dropdown.selectByIndex(1);//Select the option at index 1 from the dropdown menu.

        WebElement selectedOption = dropdown.getFirstSelectedOption();//Get the first selected option from the dropdown menu.

        List<WebElement> allSelectedOptions = 
          dropdown.getAllSelectedOptions();//Get all selected options from the dropdown menu.

        dropdown.deselectByValue("2");//Deselect the option with value "2" from the dropdown menu.
        dropdown.deselectByVisibleText("Three");//Deselect the option with visible text "three" from the dropdown menu.
        dropdown.deselectByIndex(1);//Deselect the option at index 1 from the dropdown menu.
        dropdown.deselectAll();//Deselect all selected options from the dropdown menu.
        driver.quit();//Close the browser & shuts down the WebDriver instance.
    }

}
