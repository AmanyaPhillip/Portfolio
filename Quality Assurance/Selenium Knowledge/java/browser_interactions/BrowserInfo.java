package browser_interactions;

import setup.Main;

public class BrowserInfo extends Main {
    public static void main(String[] args) {
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");//Open the URL in the browser.
        String title = driver.getTitle();//Get the title of the page.
        System.out.println("Page title is: " + title);//Print the title of the page.
        String url = driver.getCurrentUrl();//Get the current URL of the page.
        System.out.println("Current URL is: " + url);//Print the current URL of the page.
        String source = driver.getPageSource();//Get the source code of the page.
        System.out.println("Page source is: " + source);//Print the source code of the page.

        driver.quit();//Close the browser & shuts down the WebDriver instance.
    }

}
