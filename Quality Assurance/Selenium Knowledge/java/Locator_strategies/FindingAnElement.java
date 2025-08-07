import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import setup.Main;

public class FindingAnElement extends Main {
    public static void main(String[] args) {
        driver.get("https://www.selenium.dev/selenium/web/formPage.html");//Open the URL in the browser.
        WebElement emailTextbox = driver.findElement(By.id("email")); //Find the email text box using its ID.
        System.out.println(driver.getTitle());//Print the title of the page.
        driver.quit();//Close the browser & shuts down the WebDriver instance.
    }

}
