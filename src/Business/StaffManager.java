package Business;

import java.util.List;

import Data.*;

/**
 * This class implements the Staff Manager controller
 * @author Pathangi Janardhanan Jatin Shravan
 * @version 1.0
 * @since 6th November 2014
 */

public class StaffManager 
{
	/**
	 * This function retrieves a list of staff.
	 * @return A list of staff.
	 */
	public List onStartUp() 
	{
		// TODO - implement StaffManager.onStartUp
		StaffDAO staffDAO = new StaffDAO();
		
		List listOfStaff = staffDAO.retrieveAll();
		
		return listOfStaff;
	}

}