package interactions;

import org.openqa.selenium.By;

import setup.Main;

public class ClickableElements extends Main {
    public static void main(String[] args) {
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");//Open the URL in the browser.
        driver.findElement(By.xpath("/html/body/main/div/form/div/div[2]/button")).click();
        //Find the button using its XPath and click it.
        driver.findElement(By.xpath("//a[normalize-space(text())='Return to index']")).click();
        //Find the link using its XPath and click it.
        //driver.quit();//Close the browser & shuts down the WebDriver instance.
    }

}
