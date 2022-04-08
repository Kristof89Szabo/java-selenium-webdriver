package hu.progmasters;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniumTestWithWebdriverManager {

    WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("headless");
//        options.addArguments("disable-extensions");
        options.addArguments("start-maximized");
        options.addArguments("incognito");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void test_get_title() {
        driver.get("https://www.python.org/");
        Dimension newDimension = new Dimension(800,600);
        driver.manage().window().setSize(newDimension);

        String title = driver.getTitle();
        assertEquals("Welcome to Python.org", title);
    }

    @Test
    void test_searchField() {
        // Exercise
        driver.get("https://www.python.org/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        WebElement searchBox = driver.findElement(By.id("id-search-field")); //find_element_by_id   -> find_elemnt(By.Id)
        WebElement submitButton = driver.findElement(By.id("submit"));
        searchBox.sendKeys("Python");
        submitButton.click();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        WebElement resultText = driver.findElement(By.xpath("//form[@method='get' and @action='.']/child::h3"));
        assertEquals("Results", resultText.getText());
    }

    @Test
    void test_openNewTab() {
        driver.get("https://demoqa.com/browser-windows");

        // Open new child window within the main window
        driver.findElement(By.id("tabButton")).click();
        //Get handles of the windows
        String mainWindowHandle = driver.getWindowHandle();  //Main windows ID
        Set<String> allWindowHandles = driver.getWindowHandles(); //All Windows ID
        Iterator<String> iterator = allWindowHandles.iterator();

        // Here we will check if child window has other child windows and will fetch the heading of the child window
        WebElement text = null;
        while (iterator.hasNext()) {
            String ChildWindow = iterator.next();
            if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
                driver.switchTo().window(ChildWindow);
                text = driver.findElement(By.id("sampleHeading"));
            }
        }
        assertEquals("This is a sample page", text.getText());
    }

}
