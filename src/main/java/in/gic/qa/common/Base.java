package in.gic.qa.common;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base {

    protected WebDriver driver;
    private WebDriverWait driverwait;

    protected Base(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        
        driverwait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
    
  //FOR WEBDRIVER WAIT
  	
  	protected void waitUntilVisibilityOf(WebElement element) {
  		driverwait.until(ExpectedConditions.visibilityOf(element));
  	}
}
