package in.gic.staticpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import in.gic.qa.common.Base;

public class LoginPage extends Base {

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	
	@FindBy(xpath = "//button[text()='Accept']") private WebElement acceptCookies;

// LOGIN PAGE ELEMENTS
	@FindBy(xpath = "//a[text()='Login / SignUp']") private WebElement loginButton;

	@FindBy(xpath = "//strong[text()='Sign in with Discord']") private WebElement signinDiscord;
	@FindBy(xpath = "//h2[text()='Log in with QR Code']") private WebElement discordPageText;
	
	@FindBy(xpath = "//p[text()='or']") private WebElement orText;

	@FindBy(xpath = "//label[text()='Email']") private WebElement emailLabel;
	@FindBy(id = "username") private WebElement emailField;
	
	@FindBy(xpath = "//label[text()='Password']") private WebElement passwordLabel;
	@FindBy(id = "password") private WebElement passwordField;

	@FindBy(xpath = "//button[text()='Log In']") private WebElement loginSubmit;
	
	@FindBy(xpath = "//a[text()='Forgot Password?']") private WebElement forgotPasswordLink;
	@FindBy(xpath = "//h5[text()='Reset Your Password']") private WebElement resetPasswordText;
	@FindBy(xpath = "//a[@href='/common/login/']") private WebElement loginLink;

	@FindBy(xpath = "//a[contains(text(), 'Sign Up')]") private WebElement signUpLink;
	@FindBy(xpath = "//button[text()='Sign Up']") private WebElement signUpButton;

	@FindBy(xpath = "//p[contains(text(), 'all rights reserved')]") private WebElement copyright;

	@FindBy(xpath = "//button[text()='Upload']") private WebElement uploadCVbutton;

	
// ERROR MESSAGES
	@FindBy(xpath = "//li[text()='Email: This field is required.']") private WebElement emailRequiredError;
	@FindBy(xpath = "//li[text()='Password: This field is required.']") private WebElement passwordRequiredError;
	@FindBy(xpath = "//li[contains(text(),'Please enter a correct username and password.')]") private WebElement incorrectError;
	
	@FindBy(xpath = "//span[@class='d-none d-sm-inline mx-1']") private WebElement usernameDropdown;
	@FindBy(xpath = "//a[@href='/common/logout/']") private WebElement logout;
	

//  ====================== METHODS =======================

	public void clickLoginHeading() {
		waitUntilVisibilityOf(loginButton);
		loginButton.click();
	}

	public boolean isPageOpened() {
		waitUntilVisibilityOf(loginSubmit);
		return loginSubmit.isDisplayed();
	}
	
	public boolean allElementsDisplayed() {
		waitUntilVisibilityOf(signinDiscord);
		signinDiscord.isDisplayed();
		orText.isDisplayed();
		emailLabel.isDisplayed();
		emailField.isDisplayed();
		passwordLabel.isDisplayed();
		passwordField.isDisplayed();
		loginSubmit.isDisplayed();
		forgotPasswordLink.isDisplayed();
		signUpLink.isDisplayed();
		return copyright.isDisplayed();
	}

	public void enterEmail(String email) {
		waitUntilVisibilityOf(emailField);
		emailField.sendKeys(email);
	}

	public void enterPassword(String password) {
		passwordField.sendKeys(password);
	}
	
	public void clickLoginButton() {
		loginSubmit.click();
	}

	public void performLogin(String email, String password){
//		waitUntilVisibilityOf(loginButton);
//		loginButton.click();
		waitUntilVisibilityOf(emailField);
		enterEmail(email);
		enterPassword(password);
		loginSubmit.click();
	}
	
	public boolean emailErrorDisplayed() {
		waitUntilVisibilityOf(emailRequiredError);
		return emailRequiredError.isDisplayed();
	}
	
	public boolean passwordErrorDisplayed() {
		waitUntilVisibilityOf(passwordRequiredError);
		return passwordRequiredError.isDisplayed();
	}
	
	public boolean testEmptyCredentials() {
		waitUntilVisibilityOf(incorrectError);
		return incorrectError.isDisplayed();
	}
	
	public boolean isLoginSuccessful() {
		waitUntilVisibilityOf(uploadCVbutton);
		return uploadCVbutton.isDisplayed();
	}

	public boolean discordPageTextDisplayed() {
		signinDiscord.click();
		waitUntilVisibilityOf(discordPageText);
		return discordPageText.isDisplayed();
	}
	
	public boolean resetPasswordTextDisplayed() {
		forgotPasswordLink.click();
		waitUntilVisibilityOf(resetPasswordText);
		return resetPasswordText.isDisplayed();
	}
	public boolean signUpButtonDisplayed() {
		driver.navigate().back();
		driver.navigate().back();
		waitUntilVisibilityOf(signUpLink);
		signUpLink.click();
		waitUntilVisibilityOf(signUpButton);
		return signUpButton.isDisplayed();
	}

	public void clickLogout() {
		waitUntilVisibilityOf(usernameDropdown);
		usernameDropdown.click();
		logout.click();
	}
	
	public void clickLoginlink() {
		waitUntilVisibilityOf(loginLink);
		loginLink.click();
	}
	
	public void acceptCookies() {
		waitUntilVisibilityOf(acceptCookies);
		acceptCookies.click();
	}
	
}
