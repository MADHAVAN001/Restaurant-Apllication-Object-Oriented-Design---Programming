package Data;

import java.util.List;

/**
 * This class implements the Membership Data Access Object with the attributes fileName, fileNameForRunningID.
 * 
 * This class is solely responsible for Create/Retrieve/Update/Delete functions from our flat file(Text File).
 * 
 * This class is using Serialization to write Membership objects into a text file.
 * 
 * @author Bansal Ankur
 * @version 1.0
 * @since 24th October 2014
 */
public class MembershipDAO 
{
	/**
	 * The file name where the Membership object is stored.
	 */
	private String fileName;
	
	/**
	 * The file name for keeping track of the Membership unique running id.
	 */
	private String fileNameForRunningID;
	
	/**
	 * Default constructor.
	 * 
	 *	fileName = "Membership.txt";
	 *	fileNameForRunningID = "MembershipRunningID.txt";
	 */
	public MembershipDAO()
	{
		fileName = "Membership.txt";
		fileNameForRunningID = "MembershipRunningID.txt";
	}

	/**
	 * This function retrieve all the serialized membership object.
	 * @return A list of memberships.
	 */
	public List retrieveAll() 
	{
		List listOfMembership = null;
		
		try 
		{		
			listOfMembership =  SerializeDB.readSerializedObject(fileName);
			
			return listOfMembership;
			
		} catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
		}
		
		return listOfMembership;
	}

}