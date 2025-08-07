package browser_interactions;

import org.openqa.selenium.*;

import setup.Main;

public class Tabs extends Main {
    public static void main(String[] args) {
        driver.get("https://www.selenium.dev/selenium/web/window_switching_tests/page_with_frame.html");
        //Open the URL in the browser.

        driver.findElement(By.id("a-link-that-opens-a-new-window")).click();
        //Find the link using its ID and click it to open a new window.

        var windows = driver.getWindowHandles();
        //Get the handles of all open windows.
        String ogwindow = driver.getWindowHandle();
        //Get the handle of the original window.
        for (String windo : windows) {
            if (!windo.equals(ogwindow)) {
                driver.switchTo().window(windo);
                //Switch to the new window.
                break;
            }
        }       
    }

}
