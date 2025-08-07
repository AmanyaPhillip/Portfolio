package browser_interactions;

import org.openqa.selenium.By;

import setup.Main;

public class Navigation extends Main {
    public static void main(String[] args) {
        driver.get("https://www.selenium.dev/selenium/web/clicks.html");
        //Open the URL in the browser.
        System.out.println("Page title is: " + driver.getTitle());
        //Print the title of the page.

        driver.findElement(By.id("twoClientRects")).click();
        //Find the element using its ID and click on it.
        System.out.println("Page title is: " + driver.getTitle());
        //Print the title of the page.
        driver.navigate().back();
        //Navigate back to the previous page.
        System.out.println("Page title is: " + driver.getTitle());
        //Print the title of the page.
        driver.navigate().forward();
        //Navigate forward to the next page.
    }

}
