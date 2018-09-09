package com.water.user.springboot.constants;

public class Constants {


	public static final String COLLECTION_WAHTER_USERS = "wahter_users";
	public static final String COLLECTION_POTENTIAL_USERS = "potential_users";
	public static final String COLLECTION_USER_MOBILE_NUMBERS = "User_mobile_numbers";

	
	public static final String PHONE_NUMBER = "phoneNumber";
	public static final String PASSWORD = "password";
	public static final String DATE_FORMAT = "E yyyy.MM.dd 'at' hh:mm:ss a zzz";
	public static final String PIPE_DELIMITER = "|";
	
	public static final String ERROR_CODE_1000 = "1000";
	public static final String ERROR_CODE_1001 = "1001";
	public static final String ERROR_CODE_1002 = "1002";
	public static final String ERROR_CODE_1003 = "1003";
	public static final String ERROR_CODE_1004 = "1004";
	public static final String RESPONSE_CODE_001 = "001";		  //Rekki has been completed. User is ready for Sign up.
	public static final String RESPONSE_CODE_002 = "002";		  //Phone number is not available in the system
	public static final String ERROR_CODE_1100 = "1100";		  //Default code for login errors
	public static final String ERROR_CODE_1101 = "1101";          //Phone number is registered as Wahter User and active.
	public static final String ERROR_CODE_1102 = "1102";		  //Phone number is registered as Wahter User and inactive.
	public static final String ERROR_CODE_1103 = "1103";		  //Phone number is registered as Potential User and Rekki not cleared.
	public static final String ERROR_CODE_1104 = "1104";		  //Phone number is registered as Interested User.
	public static final String ERROR_CODE_1105 = "1105";		  //Phone number is registered as Potential User and Rekki cleared
	public static final String ERROR_CODE_1106 = "1106";		  //Incorrect Credentials provided for Login


}
