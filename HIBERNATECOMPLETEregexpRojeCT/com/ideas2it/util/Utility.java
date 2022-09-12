package com.ideas2it.util;



public class Utility {
    
    private final static String employeIdPrefix = "I";
    private static int count = 1; 

    /**
     *used to extract Year From Date
     *@param dateOfJoin {@link String} the joining date of employee
     *@return {@link int} the joined year of the employee
     */
    public static int extractYearFromDate(String dateOfJoin) {
        String dateParts[] = dateOfJoin.split("-");
        String yearOfBirth = dateParts[0] ;
        int year = Integer.parseInt(yearOfBirth );
	return year;
    }	

    /**
     *used to generate employee Id 
     *@param year {@int year} the joining year of employee
     *@return {@link String} the generated id of the employee
     */	
    public static String  generateEmployeeId(int year) {
	StringBuilder employeeId = new StringBuilder();
	employeeId.append(employeIdPrefix).append(year%100).append(count);
	count=count+1;
        return employeeId.toString();
    }

    /**
     *used to generate employee emailId 
     *@param firstName {@link String} the first name of employee
     *@param lastName {@link String} the last name of employee
     *@return {@link String} the generated emailId of the employee
     */
    public static String generateMailId(String firstName, String lastName) {
	StringBuilder employeeEmailId = new StringBuilder();
	employeeEmailId.append(firstName.toLowerCase()).append(".")
	               .append(lastName.toLowerCase()).append("@ideas2it.com");   
	return employeeEmailId.toString();
    } 
                  
}
