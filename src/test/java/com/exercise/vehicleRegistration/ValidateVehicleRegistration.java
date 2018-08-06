package com.exercise.vehicleRegistration;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.exercise.PageObjects.SearchRegistrationPage;
import com.exercise.PageObjects.VehicleInformationStartPage;
import com.exercise.PageObjects.VerifyReturnedVehicleDetails;
import com.exercise.Setup.TestSetup;

public class ValidateVehicleRegistration extends TestSetup {
	private final static Logger LOGGER = LoggerFactory.getLogger(ValidateVehicleRegistration.class);
	
	@Test
	public void validateVehicleRegistration() {
		// web-driver
		// navigate to url
		
		// Start from Gov.uk GetVehicle page
		LOGGER.info("Gov.uk website opened for Vehicle Registration.");
		VehicleInformationStartPage startPage = new VehicleInformationStartPage().open();
		
		LOGGER.info("Start Vehicle Registration");
		SearchRegistrationPage searchRegistration = startPage.startRegistrationSearch();
		
		// Retrieve RegNo, Make and Colour from .xlsx file
		// Enter RegNo to search its details
		LOGGER.info("Retrieve Vehicle details from Excel file");
		String regNo = excel.getCellData("VR", 0, 2);			
		String make = excel.getCellData("VR", 2, 2);
		String colour = excel.getCellData("VR", 1, 2);
		
		LOGGER.info("Passed Registration number to get the vehicle Make and colour");
		VerifyReturnedVehicleDetails vehDetails = searchRegistration.enterRegistrationNo(regNo);
		
		LOGGER.info("Verify the Vehicles color and make matched with excel sheet data for the entered vehicle");
		vehDetails.AssertVehicleDetails(regNo, make, colour);
	}

}
