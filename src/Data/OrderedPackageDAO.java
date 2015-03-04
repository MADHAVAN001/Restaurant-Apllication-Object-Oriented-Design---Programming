package Data;

import java.util.List;
import java.util.ArrayList;

/**
 * This class implements the Ordered Package Data Access Object with the attributes fileName, fileNameForRunningID.
 * 
 * This class is solely responsible for Create/Retrieve/Update/Delete functions from our flat file(Text File).
 * 
 * This class is using Serialization to write Ordered Package objects into a text file.
 * 
 * @author Seshadri Madhavan
 * @version 1.0
 * @since 24th October 2014
 */
public class OrderedPackageDAO
{
	/**
	 * The file name where the Ordered Package object is stored.
	 */
	private String fileName;
	
	/**
	 * The file name for keeping track of the Ordered Package unique running id.
	 */
	private String fileNameForRunningID;
	
	/**
	 * Default constructor.
	 * 
	 *	fileName = "OrderedPackage.txt";
	 *	fileNameForRunningID = "OrderedPackageRunningID.txt";
	 */
	public OrderedPackageDAO()
	{
		fileName = "OrderedPackage.txt";
		fileNameForRunningID = "OrderedPackageRunningID.txt";
	}
	
	/**
	 * This function get the current running ID for Ordered Package and increment it to set it as the ordered package's id that is to be inserted.
	 * The ordered package is serialized and written back into the text file.
	 * @param orderedPackage - An ordered package to be inserted into the text file.
	 * @return Return TRUE if the insertion is successful. Return FALSE if the insertion fails.
	 */
	public boolean insert(OrderedPackage orderedPackage)
	{
		List listOfOrderedPackage;
		boolean bool = false;
		
		try {			
			int runningID = SerializeDB.incrementAndGetRunningID(fileNameForRunningID);
			orderedPackage.setId(runningID);
			
			listOfOrderedPackage = SerializeDB.readSerializedObject(fileName);
			listOfOrderedPackage.add(orderedPackage);
			
			SerializeDB.writeSerializedObject(fileName, listOfOrderedPackage);
			bool = true;
		} catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
		}
		return bool;
	}
	
	/**
	 * This function deletes a existing ordered package in the text file. 
	 * @param orderedPackage - An ordered item to be deleted.
	 * @return Return TRUE if the delete is successful. Return FALSE if the delete fails.
	 */
	public boolean delete(OrderedPackage orderedPackage)
	{
		List listOfOrderedPackage;
		boolean bool = false;
		try {
			listOfOrderedPackage = SerializeDB.readSerializedObject(fileName);
			
			for(int i = 0; i < listOfOrderedPackage.size(); i++)
			{
				OrderedPackage tempOrderedPackage = (OrderedPackage)listOfOrderedPackage.get(i);
				
				if(tempOrderedPackage.getId() == orderedPackage.getId())
				{
					listOfOrderedPackage.remove(i);
					bool = true;
					break;
				}
			}
			SerializeDB.writeSerializedObject(fileName, listOfOrderedPackage);
		} catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
		}
		return bool;
	}
	
	/**
	 * This function retrieves all the ordered packages that belongs to the order ID.
	 * @param id - An order ID.
	 * @return A list of ordered packages.
	 */
	public List retrieveAllByOrderID(int id)
	{
		List listOfOrderedPackage = null;
		List listOfOrderedPackageByOrderID = new ArrayList<OrderedPackage>();

		try {

			listOfOrderedPackage = SerializeDB.readSerializedObject(fileName);
			for (int i = 0; i < listOfOrderedPackage.size(); i++) {
				OrderedPackage tempOrderedPackage = (OrderedPackage) listOfOrderedPackage.get(i);
				
				if (tempOrderedPackage.getOrder().getId() == id) 
				{
					listOfOrderedPackageByOrderID.add(tempOrderedPackage);
				}
			}

		} catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
		}
		
		return listOfOrderedPackageByOrderID;
	}
}