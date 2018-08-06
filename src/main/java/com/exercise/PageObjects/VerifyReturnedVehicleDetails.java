package com.exercise.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.Assert.*;

public class VerifyReturnedVehicleDetails extends BasePage {
	@FindBy(xpath="//span[@class='reg-mark']")
	public WebElement regNo;
	
	@FindBy(xpath="//span[contains(text(), 'Make')]//../span[2]/strong")
	public WebElement make;
	
	@FindBy(xpath="//span[contains(text(), 'Colour')]//../span[2]/strong")
	public WebElement colour;
	
	@Override
	public ExpectedCondition getPageLoadCondition() {
		return ExpectedConditions.visibilityOf(regNo);
	}
	
	public VerifyReturnedVehicleDetails AssertVehicleDetails(String regNo, String make, String colour) {
		System.out.println("Reg no:" + this.regNo.getText() + " Make: " + this.make.getText() + " Colour:" + this.colour.getText());
				
		assertEquals(this.regNo.getText(), regNo);
		assertEquals(this.make.getText(), make);
		assertEquals(this.colour.getText(), colour);
		
		return this;
	}

}
