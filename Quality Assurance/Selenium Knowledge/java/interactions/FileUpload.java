package interactions;

import org.openqa.selenium.*;

import setup.Main;

public class FileUpload extends Main {
    public static void main(String[] args) {
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");//Open the URL in the browser.
        String filPath = "/workspaces/test-automation-with-selenium-webdriver-for-java-2124033/src/main/resources/file.txt";
         //Specify the file path to be uploaded.
        WebElement uploadElement = driver.findElement(By.name("my-file"));
         //Find the file upload element using its name.
        uploadElement.sendKeys(filPath); //Send the file path to the file upload element.
        driver.quit();//Close the browser & shuts down the WebDriver instance.
    }

}
