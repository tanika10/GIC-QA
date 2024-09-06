package in.gic.qa.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
    public static WebDriver driver;

    public static WebDriver getDriver(String browser) throws Exception {
//        WebDriver driver;
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
//            options.addArguments("--headless");
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("incognito");
            options.addArguments("start-maximized");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--disable-features=SameSiteByDefaultCookies");
            options.addArguments("--disable-features=CookiesWithoutSameSiteMustBeSecure");
            driver = new ChromeDriver(options);
        }
        
        else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--headless");
            options.addArguments("inprivate");
            options.addArguments("start-maximized");
            driver = new EdgeDriver(options);
        } 

        else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");
            options.addArguments("-private");
            driver = new FirefoxDriver(options);
        }
        else {
            throw new Exception("Does Not Support this Browser");
        }
        return driver;
    }
}
