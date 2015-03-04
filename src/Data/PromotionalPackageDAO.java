package Data;

import java.util.List;

/**
 * This class implements the Promotional Package Data Access Object with the attributes fileName, fileNameForRunningID.
 * 
 * This class is solely responsible for Create/Retrieve/Update/Delete functions from our flat file(Text File).
 * 
 * This class is using Serialization to write Promotional Package objects into a text file.
 * 
 * @author Adrian Wang Wenjie
 * @version 1.0
 * @since 24th October 2014
 */
public class PromotionalPackageDAO 
{
	/**
	 * The file name where the Promotional Package object is stored.
	 */
	private String fileName;
	
	/**
	 * The file name for keeping track of the Promotional Package unique running id.
	 */
	private String fileNameForRunningID;

	/**
	 * Default constructor.
	 * 
	 *	fileName = "PromotionalPackage.txt";
	 *	fileNameForRunningID = "PromotionalPackageRunningID.txt";
	 */
	public PromotionalPackageDAO()
	{
		fileName = "PromotionalPackage.txt";
		fileNameForRunningID = "PromotionalPackageRunningID.txt";
	}

	/**
	 * This function get the current running ID for Promotional Package and increment it to set it as the promotional package's id that is to be inserted.
	 * The promotional package is serialized and written back into the text file.
	 * @param packaging - An promotional Package to be inserted into the text file.
	 * @return Return TRUE if the insertion is successful. Return FALSE if the insertion fails.
	 */
	public boolean insert(PromotionalPackage packaging) 
	{
		boolean bool = false;
		List listOfPromotionalPackage;
		try {
			int runningID = SerializeDB.incrementAndGetRunningID(fileNameForRunningID);
			packaging.setId(runningID);
			
			listOfPromotionalPackage = SerializeDB.readSerializedObject(fileName);
			listOfPromotionalPackage.add(packaging);
			SerializeDB.writeSerializedObject(fileName, listOfPromotionalPackage);
			bool = true;
		} catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
		}
		return bool;

	}

	/**
	 * This function replace the existing promotional package in the text file with an updated promotional package.
	 * @param packaging - An promotional package with updated values.
	 * @return Return TRUE if the update is successful. Return FALSE if the update fails.
	 */
	public boolean update(PromotionalPackage packaging) 
	{
		boolean check = false;

		List listOfPromotionalPackage;

		try 
		{
			listOfPromotionalPackage = SerializeDB.readSerializedObject(fileName);

			for(int i = 0; i < listOfPromotionalPackage.size(); i++)
			{
				PromotionalPackage tempPromotionalPackage = (PromotionalPackage)listOfPromotionalPackage.get(i);

				if(tempPromotionalPackage.getId() == packaging.getId())
				{
					listOfPromotionalPackage.set(i, packaging);

					SerializeDB.writeSerializedObject(fileName, listOfPromotionalPackage);
					check = true;

					return check;
				}
			}
		} catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
			check = false;
		}

		return check;

	}

	/**
	 * This function deletes a existing promotional package in the text file. 
	 * @param packaging - An promotional package to be deleted.
	 * @return Return TRUE if the delete is successful. Return FALSE if the delete fails.
	 */
	public boolean delete(PromotionalPackage packaging) {
		boolean check = false;

		List listOfPromotionalPackage;

		try 
		{
			listOfPromotionalPackage = SerializeDB.readSerializedObject(fileName);

			for(int i = 0; i < listOfPromotionalPackage.size(); i++)
			{
				PromotionalPackage tempPromotionalPackage = (PromotionalPackage)listOfPromotionalPackage.get(i);

				if(tempPromotionalPackage.getId() == packaging.getId())
				{
					tempPromotionalPackage.setRemoved(true);

					listOfPromotionalPackage.set(i, tempPromotionalPackage);
					
					SerializeDB.writeSerializedObject(fileName, listOfPromotionalPackage);
					check = true;

					return check;
				}
			}
		} catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
			check = false;
		}

		return check;

	}

	/**
	 * This function retrieves all the promotional packages.
	 * @return A list of promotional packages.
	 */
	public List retrieveAll() {
		List listOfPromotionalPackage = null;
		try 
		{

			listOfPromotionalPackage =  SerializeDB.readSerializedObject(fileName);
		}


		catch (Exception e) 
		{
			System.out.println("Exception >> " + e.getMessage());
		}
		return listOfPromotionalPackage;

	}
}