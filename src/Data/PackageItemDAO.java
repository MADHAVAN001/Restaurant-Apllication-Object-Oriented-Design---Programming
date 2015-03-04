package Data;

import java.util.List;
import java.util.ArrayList;

/**
 * This class implements the Package Item Data Access Object with the attributes fileName, fileNameForRunningID.
 * 
 * This class is solely responsible for Create/Retrieve/Update/Delete functions from our flat file(Text File).
 * 
 * This class is using Serialization to write Package Item objects into a text file.
 * 
 * @author Pathangi Janardhanan Jatin Shravan
 * @version 1.0
 * @since 24th October 2014
 */
public class PackageItemDAO
{
	/**
	 * The file name where the Package Item object is stored.
	 */
	private String fileName;
	
	/**
	 * The file name for keeping track of the Package Item unique running id.
	 */
	private String fileNameForRunningID;
	
	/**
	 * Default constructor.
	 * 
	 *	fileName = "PackageItem.txt";
	 *	fileNameForRunningID = "PackageItemRunningID.txt";
	 */
	public PackageItemDAO()
	{
		fileName = "PackageItem.txt";
		fileNameForRunningID = "PackageItemRunningID.txt";
	}
	
	/**
	 * This function get the current running ID for Package Item and increment it to set it as the package item's id that is to be inserted.
	 * The package item is serialized and written back into the text file.
	 * @param packageItem - An package item to be inserted into the text file.
	 * @return Return TRUE if the insertion is successful. Return FALSE if the insertion fails.
	 */
	public boolean insert(PackageItem packageItem)
	{
		boolean bool = false;
		List listOfPackageItem;
		
		try {
			int runningID = SerializeDB.incrementAndGetRunningID(fileNameForRunningID);
			packageItem.setId(runningID);
			
			listOfPackageItem = SerializeDB.readSerializedObject(fileName);
			listOfPackageItem.add(packageItem);
			SerializeDB.writeSerializedObject(fileName, listOfPackageItem);
			bool = true;
		} catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
		}
		return bool;
	}
	
	/**
	 * This function deletes a existing package item in the text file. 
	 * @param packageItem - An package item to be deleted.
	 * @return Return TRUE if the delete is successful. Return FALSE if the delete fails.
	 */
	public boolean delete(PackageItem packageItem)
	{
		List listOfPackageItem;
		
		boolean bool = false;
		
		try 
		{
			listOfPackageItem =  SerializeDB.readSerializedObject(fileName);
			 bool = listOfPackageItem.remove(packageItem);
			SerializeDB.writeSerializedObject(fileName, listOfPackageItem);
		} catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
		}
		return bool;
	}
	
	/**
	 * This function retrieves all the package items that belongs to the promotional package ID.
	 * @param id - A promotional package ID.
	 * @return A list of package items.
	 */
	public List retrieveAllByPackageID(int id)
	{
		List listofPackageItem = null;
		List listofPackageItemByPackageID = new ArrayList<PackageItem>();
		
		try 
		{
			listofPackageItem = SerializeDB.readSerializedObject(fileName);
			
			for (int i = 0; i < listofPackageItem.size(); i++) {
				PackageItem tempPackageItem = (PackageItem) listofPackageItem.get(i);
				
				if (tempPackageItem.getPackage().getId() == id)
				{
					listofPackageItemByPackageID.add(tempPackageItem);
				}
			}

		} catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
		}
		
		return listofPackageItemByPackageID;

	}
}