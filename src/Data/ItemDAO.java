package Data;

import java.util.List;
import java.util.ArrayList;

/**
 * This class implements the Item Data Access Object with the attributes fileName, fileNameForRunningID.
 * 
 * This class is solely responsible for Create/Retrieve/Update/Delete functions from our flat file(Text File).
 * 
 * This class is using Serialization to write Item objects into a text file.
 * 
 * @author Seshadri Madhavan
 * @version 1.0
 * @since 24th October 2014
 */
public class ItemDAO
{
	/**
	 * The file name where the Item object is stored.
	 */
	private String fileName;

	/**
	 * The file name for keeping track of the Item unique running id.
	 */
	private String fileNameForRunningID;

	/**
	 * Default constructor.
	 * 
	 *	fileName = "Item.txt";
	 *	fileNameForRunningID = "ItemRunningID.txt";
	 */
	public ItemDAO()
	{
		fileName = "Item.txt";
		fileNameForRunningID = "ItemRunningID.txt";
	}

	/**
	 * This function get the current running ID for Item and increment it to set it as the item's id that is to be inserted.
	 * The item is serialized and written back into the text file.
	 * @param item - An item to be inserted into the text file.
	 * @return Return TRUE if the insertion is successful. Return FALSE if the insertion fails.
	 */
	public boolean insert(Item item) 
	{
		List listOfItems;
		try 
		{
			int runningID = SerializeDB.incrementAndGetRunningID(fileNameForRunningID);

			listOfItems = SerializeDB.readSerializedObject(fileName);
			item.setId(runningID);
			listOfItems.add(item);
			SerializeDB.writeSerializedObject(fileName, listOfItems);
			return true;
		} catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
			return false;
		}
	}

	/**
	 * This function replace the existing item in the text file with an updated item.
	 * @param item - An item with updated values.
	 * @return Return TRUE if the update is successful. Return FALSE if the update fails.
	 */
	public boolean update(Item item)
	{
		boolean check = false;
		List listOfItems;

		try 
		{
			listOfItems = SerializeDB.readSerializedObject(fileName);

			for(int i = 0; i < listOfItems.size(); i++)
			{
				Item tempItem = (Item)listOfItems.get(i);

				if(item.getId() == tempItem.getId())
				{
					listOfItems.set(i, item);
					SerializeDB.writeSerializedObject(fileName, listOfItems);
					check = true;

					return check;
				}
			}
		} 

		catch (Exception e) 
		{
			System.out.println("Exception >> " + e.getMessage());
			check = false;
		}

		return check;
	}

	/**
	 * This function deletes a existing item in the text file. 
	 * @param item - An item to be deleted.
	 * @return Return TRUE if the delete is successful. Return FALSE if the delete fails.
	 */
	public boolean delete(Item item) 
	{
		boolean check = false;
		List listOfItems;

		try {
			listOfItems =  SerializeDB.readSerializedObject(fileName);

			for(int i = 0; i < listOfItems.size(); i++)
			{
				Item tempItem = (Item)listOfItems.get(i);

				if(item.getId() == tempItem.getId())
				{
					tempItem.setRemoved(true);
					listOfItems.set(i, tempItem);
					SerializeDB.writeSerializedObject(fileName, listOfItems);
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
	 * This function retrieves all the items.
	 * @return A list of items
	 */
	public List retrieveAll()
	{
		List listOfItems = null;

		try 
		{
			listOfItems = SerializeDB.readSerializedObject(fileName);			
		} 

		catch (Exception e)
		{			
			System.out.println("Exception >> " + e.getMessage());
		}

		return listOfItems;
	}
}