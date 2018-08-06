package com.exercise.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchRegistrationPage extends BasePage {
	@FindBy(xpath="//input[@class='form-control form-control-1-4 input-upper']")
	public WebElement inputRegNo;
	
	@FindBy(xpath="//button[@name='Continue']")
	public WebElement btnContinue;

	@Override
	public ExpectedCondition getPageLoadCondition() {
		return ExpectedConditions.visibilityOf(inputRegNo);
	}
	
	public VerifyReturnedVehicleDetails enterRegistrationNo(String regNo) {
		// read
		this.inputRegNo.sendKeys(regNo);
		this.btnContinue.click();
		return (VerifyReturnedVehicleDetails) openPage(VerifyReturnedVehicleDetails.class);
	}

}
