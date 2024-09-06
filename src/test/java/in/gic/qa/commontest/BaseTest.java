package in.gic.qa.commontest;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.github.javafaker.Faker;

import in.gic.qa.common.DriverFactory;

public class BaseTest {
	
	protected WebDriver driver;
	
	private static ExtentSparkReporter extentSparkReporter;	//for creating and formatting the report
	private static ExtentReports extentReports;	//manages overall reporting
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();	
	//This ThreadLocal variable holds the ExtentTest instance specific to the current test case, ensures that reports are managed independently for each test
	
	//FOR WEBDRIVER WAIT
	protected WebDriverWait driverwait = new WebDriverWait(driver, Duration.ofSeconds(10));
	
	protected void waitUntilVisibilityOf(WebElement element) {
		driverwait.until(ExpectedConditions.visibilityOf(element));
	}

	//FOR FAKER 
	protected Faker f = new Faker();
	protected String fakerEmail = f.internet().emailAddress();
	protected String fakerPassword = f.internet().password();
	
	@BeforeClass
	@Parameters({"browser", "url"})
	protected void setUp(String browser, String url) throws Exception {
		driver = DriverFactory.getDriver(browser);
		driver.get(url);
	}
	
	  @BeforeSuite
	  protected void startReporter()	//initialized before any tests are run
	    {
	        extentSparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "\\test-output\\extentReport.html");
	        extentReports = new ExtentReports();
	        extentReports.attachReporter(extentSparkReporter);
	   //====== CUSTOMIZE REPORT========
	        extentSparkReporter.config().setDocumentTitle("Automation Test Report");
	        extentSparkReporter.config().setReportName("Automation Test Report");
	        extentSparkReporter.config().setTheme(Theme.STANDARD);
	        extentSparkReporter.config().setTimeStampFormat("EEEE, DD MM, yyyy, hh:mm a '('zzz')'");
	    }
	  
	  @BeforeMethod
	    protected void beforeTestmethod(Method method) {
		  ExtentTest test = extentReports.createTest(method.getName());	//Creates new test entry in report for current test method
		  extentTest.set(test);	//Stores ExtentTest instance in ThreadLocal variable so it can be used later
	  }
	    
	//============== SCREENSHOT ================
	    protected String captureScreenshot(String screenshotName)
	    {
			File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	        String screenshotPath = System.getProperty("user.dir") + "\\failed_screenshots"+File.separator + screenshotName+ ".png";
	        try {
				FileHandler.copy(src, new File(screenshotPath));
			} catch (IOException e) {
				e.printStackTrace();
			}
	     return screenshotPath;
	    }
	    
	//========== AFTER TEST METHOD EXECUTION ==============   
	    
	    @AfterMethod
	    protected void getResult(ITestResult result) {
	    	ExtentTest test = extentTest.get();	// Retrieves the ExtentTest instance for the current test
	    	
	        if(result.getStatus() == ITestResult.FAILURE ) {
	            test.log(Status.FAIL, "Test Case FAILED : "+result.getName());
	            test.log(Status.FAIL,result.getThrowable());
	            String screenshotPath = captureScreenshot(result.getName());
	            test.addScreenCaptureFromPath(screenshotPath);
	        }
	        
	        else if(result.getStatus() == ITestResult.SUCCESS) {
	            test.log(Status.PASS, "Test Case PASSED : "+ result.getName());
	        }
	        else {
	            test.log(Status.SKIP, "Test Case SKIPPED : "+result.getName());
	        }
	    }
	    
	   //====== TO REPLACE PREVIOUSLY STORED RESULTS WITH NEW ONES
	    @AfterSuite
	    protected void tearDown() {
	    	if(extentReports != null)
	        extentReports.flush();
	    }
	    
	     @AfterClass
	     public void closeBrowser(){
	     driver.quit();
	     }
	}
