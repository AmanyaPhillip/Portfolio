package setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;


public class Main {
  static protected WebDriver driver;// Declared WebDriver object.
  //WebDriver is an interface that allows you to control a web browser programmatically.
  static {
    chromedriver().setup();//Chrome driver specification.
    var options = new ChromeOptions();//Inititation for behavior and properties of the ChromeDriver.
    options.addArguments("--no-sandbox");//Needed to use Chrome in a container.(codespaces)
    driver = new ChromeDriver(options);//ChromeDriver object is created.


  }

  public static void main(String[] args) {
    driver.get("https://www.google.com/");//Open the URL in the browser.
    System.out.println(driver.getTitle());//Print the title of the page.
    driver.quit();//Close the browser & shuts down the WebDriver instance.

  }
}