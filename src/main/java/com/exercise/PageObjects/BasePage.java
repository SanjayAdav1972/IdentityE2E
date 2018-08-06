package com.exercise.PageObjects;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.exercise.Setup.TestSetup;

public abstract class BasePage<T> extends TestSetup {
	//WebDriver driver;
	public int webDriverTimeout = 20;
	
	// Generic used here to open instance of passed page
	public T openPage(Class<T> clazz) {
		T page = null;
		
		// If the page has any Ajax control, then wait till all gets loaded
		// Page factory implementation for POM
		AjaxElementLocatorFactory ajaxLocatorFactory = new AjaxElementLocatorFactory(driver, 20);
		page = PageFactory.initElements(driver, clazz);		
		PageFactory.initElements(ajaxLocatorFactory, page);
		
		// Page load till condition satisfy
		ExpectedCondition pageLoadCondition = ((BasePage)page).getPageLoadCondition();
		waitForPageLoad(pageLoadCondition);
		
		return page;
	}
	
	// Page load condition (it has declare as abstract so that it get implemented in all derived pages.
	public abstract ExpectedCondition getPageLoadCondition();
		
	// Wait for page load
	public void waitForPageLoad(ExpectedCondition pageLoadCondition) {
		WebDriverWait wait = new WebDriverWait(driver, webDriverTimeout);
		wait.until(pageLoadCondition);
	}
}
