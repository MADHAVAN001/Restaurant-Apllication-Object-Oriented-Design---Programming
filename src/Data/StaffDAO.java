package Data;

import java.util.List;
import java.util.ArrayList;

/**
 * This class implements the Staff Data Access Object with the attributes fileName, fileNameForRunningID.
 * 
 * This class is solely responsible for Create/Retrieve/Update/Delete functions from our flat file(Text File).
 * 
 * This class is using Serialization to write Staff objects into a text file.
 * 
 * @author Pathangi Janardhanan Jatin Shravan
 * @version 1.0
 * @since 24th October 2014
 */
public class StaffDAO 
{
	/**
	 * The file name where the Staff object is stored.
	 */
	private String fileName;

	/**
	 * The file name for keeping track of the Staff unique running id.
	 */
	private String fileNameForRunningID;

	/**
	 * Default constructor.
	 * 
	 *	fileName = "Staff.txt";
	 *	fileNameForRunningID = "StaffRunningID.txt";
	 */
	public StaffDAO()
	{
		fileName = "Staff.txt";
		fileNameForRunningID = "StaffRunningID.txt";
	}

	/**
	 * This function retrieves all the staff.
	 * @return A list of staffs.
	 */
	public List retrieveAll() 
	{
		List listOfStaff = null;

		try 
		{
			listOfStaff =  SerializeDB.readSerializedObject(fileName);

		} catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
		}

		return listOfStaff;
	}
}