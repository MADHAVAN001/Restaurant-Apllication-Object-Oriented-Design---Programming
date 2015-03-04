package Data;

import java.util.List;
import java.util.ArrayList;

/**
 * This class implements the Ordered Item Data Access Object with the attributes fileName, fileNameForRunningID.
 * 
 * This class is solely responsible for Create/Retrieve/Update/Delete functions from our flat file(Text File).
 * 
 * This class is using Serialization to write Ordered Item objects into a text file.
 * 
 * @author Seshadri Madhavan
 * @version 1.0
 * @since 24th October 2014
 */
public class OrderedItemDAO
{
	/**
	 * The file name where the Ordered Item object is stored.
	 */
	private String fileName;
	
	/**
	 * The file name for keeping track of the Ordered Item unique running id.
	 */
	private String fileNameForRunningID;
	
	/**
	 * Default constructor.
	 * 
	 *	fileName = "OrderedItem.txt";
	 *	fileNameForRunningID = "OrderedItemRunningID.txt";
	 */
	public OrderedItemDAO()
	{
		fileName = "OrderedItem.txt";
		fileNameForRunningID = "OrderedItemRunningID.txt";
	}
	
	/**
	 * This function get the current running ID for Ordered Item and increment it to set it as the ordered item's id that is to be inserted.
	 * The ordered item is serialized and written back into the text file.
	 * @param orderedItem - An ordered item to be inserted into the text file.
	 * @return Return TRUE if the insertion is successful. Return FALSE if the insertion fails.
	 */
	public boolean insert(OrderedItem  orderedItem)
	{
		List listOfOrderItems;
		boolean bool = false;
		
		try {
			int runningID = SerializeDB.incrementAndGetRunningID(fileNameForRunningID);
			orderedItem.setId(runningID);
			
			listOfOrderItems = SerializeDB.readSerializedObject(fileName);
			listOfOrderItems.add(orderedItem);
			
			SerializeDB.writeSerializedObject(fileName, listOfOrderItems);
			bool = true;
		} catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
			bool = false;
		}
		
		return bool;
	}
	
	/**
	 * This function deletes a existing ordered item in the text file. 
	 * @param orderedItem - An ordered item to be deleted.
	 * @return Return TRUE if the delete is successful. Return FALSE if the delete fails.
	 */
	public boolean delete(OrderedItem orderedItem)
	{
		List listOfOrderItems;
		boolean bool = false;
		
		try {
			listOfOrderItems =  SerializeDB	.readSerializedObject(fileName);
			
			for(int i = 0; i < listOfOrderItems.size(); i++)
			{
				OrderedItem tempOrderedItem = (OrderedItem)listOfOrderItems.get(i);
				
				if(tempOrderedItem.getId() == orderedItem.getId())
				{
					listOfOrderItems.remove(i);
					bool = true;
					break;
				}
			}
			SerializeDB.writeSerializedObject(fileName, listOfOrderItems);
		} catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
		}
		return bool;
	}
	
	/**
	 * This function retrieves all the ordered items that belongs to the order ID.
	 * @param id - An order ID.
	 * @return A list of ordered items.
	 */
	public List retrieveAllByOrderID(int id)
	{
		List listOfOrderItems;
		List listOfOrderedItemByOrderID = new ArrayList<OrderedItem>();
		
		try 
		{
			listOfOrderItems = SerializeDB.readSerializedObject(fileName);
			
			for (int i = 0 ; i < listOfOrderItems.size() ; i++) 
			{
				OrderedItem tempOrderedItem = (OrderedItem)listOfOrderItems.get(i);


				if(tempOrderedItem.getOrder().getId() == id)
				{
					listOfOrderedItemByOrderID.add(tempOrderedItem);
				}
			}
			
		} catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
		}
		
		return listOfOrderedItemByOrderID;
	}
}