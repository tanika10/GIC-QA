package in.gic.staticpagestest;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import in.gic.qa.commontest.BaseTest;
import in.gic.staticpages.LoginPage;

public class LoginPageTest extends BaseTest {

	private LoginPage loginpage;

	@BeforeClass
	public void setup() {
		loginpage = new LoginPage(driver);
		loginpage.acceptCookies();
    	loginpage.clickLoginHeading();
		//verifying required page is opened
		Assert.assertEquals(loginpage.isPageOpened(), true);
		//verifying all login page elements are displayed properly
		Assert.assertEquals(loginpage.allElementsDisplayed(), true);
		Reporter.log("===== LOGIN PAGE OPENED, ALL ELEMENTS DISPLAYED=====", true);
	}
	
	// ====================================

	@Test
	@Parameters({"email", "password"})
	public void testLoginSucessful(String email, String password) {
		loginpage.performLogin(email, password);
		//verifying successful login by checking correct landing page is opened
		Assert.assertEquals(loginpage.isLoginSuccessful(), true);
		Reporter.log("===== TEST LOGIN SUCCESSFUL =====", true);
		if(loginpage.isLoginSuccessful()) {
			loginpage.clickLogout();
		}  
	}
	
	@Test
	public void testEmptyCredentials() {
		loginpage.clickLoginButton();
		//verifying error message for empty email and password fields
		Assert.assertEquals(loginpage.emailErrorDisplayed(), true);
		Assert.assertEquals(loginpage.passwordErrorDisplayed(), true);
		Reporter.log("===== TEST EMPTY CREDENTIALS =====", true);
	}

	@Test
	@Parameters("password")
	public void testEmptyEmail(String password) {
		loginpage.enterPassword(password);
    	loginpage.clickLoginButton();
		//verifying error message for empty email field
		Assert.assertEquals(loginpage.emailErrorDisplayed(), true);
		Reporter.log("===== TEST EMPTY EMAIL =====", true);
	}

	@Test
	@Parameters("email")
	public void testEmptyPassword(String email) {
		loginpage.enterEmail(email);
    	loginpage.clickLoginButton();
		//verifying error message for empty password field
		Assert.assertEquals(loginpage.passwordErrorDisplayed(), true);
		Reporter.log("===== TEST EMPTY PASSWORD =====", true);
	}
	
	@Test
	public void testIncorrectCredentials() {
		loginpage.enterEmail(f.name().username());
		loginpage.enterPassword(f.internet().password());
		loginpage.clickLoginButton();
		//verifying error message for incorrect login credentials
		Assert.assertEquals(loginpage.testEmptyCredentials(), true);
		Reporter.log("===== TEST INCORRECT EMAIL & PASSWORD =====", true);
	}
	
	@Test
	@Parameters("password")
	public void testIncorrectEmail(String password) {
		loginpage.enterEmail(f.name().username());
		loginpage.enterPassword(password);
		loginpage.clickLoginButton();
		Assert.assertEquals(loginpage.testEmptyCredentials(), true);
		Reporter.log("===== TEST INCORRECT EMAIL =====", true);
	}
	
	@Test
	@Parameters("email")
	public void testIncorrectPassword(String email) {
		loginpage.enterEmail(email);
		loginpage.enterPassword(f.internet().password());
		loginpage.clickLoginButton();
		Assert.assertEquals(loginpage.testEmptyCredentials(), true);
		Reporter.log("===== TEST INCORRECT PASSWORD =====", true);
	}
	
	@Test
	@Parameters("password")
	public void testInvalidEmail(String password) {
		loginpage.enterEmail("test@.com");
		loginpage.enterPassword(password);
		loginpage.clickLoginButton();
		Assert.assertEquals(loginpage.testEmptyCredentials(), true);
		Reporter.log("===== TEST INVALID EMAIL =====", true);
	}
	
	@Test
	public void testSigninDiscord() {
		Assert.assertEquals(loginpage.discordPageTextDisplayed(), true);
		Reporter.log("===== TEST SIGN IN WITH DISCORD =====", true);
		driver.navigate().back();
	}
	
	@Test
	public void testForgotPasswordLink() {
		Assert.assertEquals(loginpage.resetPasswordTextDisplayed(), true);
		Reporter.log("===== TEST FORGOT PASSWORD LINK =====", true);
		loginpage.clickLoginlink();
	}
	
	@Test
	public void testSignupLink() {
		Assert.assertEquals(loginpage.signUpButtonDisplayed(), true);
		Reporter.log("===== TEST SIGN UP LINK =====", true);
		driver.navigate().back();
	}
	
}