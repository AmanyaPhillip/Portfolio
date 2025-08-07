package browser_interactions;

import org.openqa.selenium.Cookie;

import setup.Main;

public class Cookies extends Main {

  public static void main(String[] args) {
    // Navigate to the Selenium web form page
    driver.get("https://www.selenium.dev/selenium/web/web-form.html");

    // Create a new cookie with name "theme" and value "light"
    Cookie cookie = new Cookie("theme", "light");
    // Add the cookie to the current browser session
    driver.manage().addCookie(cookie);

    // Retrieve the cookie named "theme"
    Cookie themeCookie = driver.manage().getCookieNamed("theme");
    // Print the domain of the retrieved cookie
    System.out.println(themeCookie.getDomain());

    // Delete the "theme" cookie from the browser
    driver.manage().deleteCookie(themeCookie);
    // Print the number of cookies remaining after deletion
    System.out.println(driver.manage().getCookies().size());

    // Close the browser and end the session
    driver.quit();
  }
}