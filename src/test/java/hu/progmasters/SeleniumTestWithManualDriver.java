package hu.progmasters;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniumTestWithManualDriver {

    WebDriver driver = null;

    @BeforeEach
    void setupTest() {
        System.setProperty("webdriver.chrome.driver", "D:\\Progmaster-Java\\Kocka-private_projects\\ws-selenium\\src\\test\\java\\hu\\progmasters\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void test_get_title() {
        driver.get("https://www.python.org/");
        String title = driver.getTitle();
        assertEquals("Welcome to Python.org", title);
    }

}
