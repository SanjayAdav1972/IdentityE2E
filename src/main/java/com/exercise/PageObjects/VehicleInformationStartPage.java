package com.exercise.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class VehicleInformationStartPage extends BasePage {

	@FindBy(xpath="//a[@class='gem-c-button govuk-button govuk-button--start']")
	public WebElement btnStartNow;
	
	@Override
	public ExpectedCondition getPageLoadCondition() {
		return ExpectedConditions.visibilityOf(btnStartNow);
	}
	
	public VehicleInformationStartPage open() {
		return (VehicleInformationStartPage) openPage(VehicleInformationStartPage.class);
	}
	
	public SearchRegistrationPage startRegistrationSearch() {
		this.btnStartNow.click();
		return (SearchRegistrationPage) openPage(SearchRegistrationPage.class);
	}

}
