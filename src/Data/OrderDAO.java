package Data;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
 * This class implements the Order Data Access Object with the attributes fileName, fileNameForRunningID.
 * 
 * This class is solely responsible for Create/Retrieve/Update/Delete functions from our flat file(Text File).
 * 
 * This class is using Serialization to write Order objects into a text file.
 * 
 * @author Bansal Ankur
 * @version 1.0
 * @since 24th October 2014
 */
public class OrderDAO 
{
	/**
	 * The file name where the Order object is stored.
	 */
	private String fileName;
	
	/**
	 * The file name for keeping track of the Order unique running id.
	 */
	private String fileNameForRunningID;

	/**
	 * Default constructor.
	 * 
	 *	fileName = "Order.txt";
	 *	fileNameForRunningID = "OrderRunningID.txt";
	 */
	public OrderDAO()
	{
		fileName = "Order.txt";
		fileNameForRunningID = "OrderRunningID.txt";
	}

	/**
	 * This function serialize the order object and writes it into a text file.
	 * @param customer - A order object to be stored into a text file.
	 * @return TRUE if the insertion is successful. Return FALSE if the insertion fails.
	 */
	public boolean insert(Order order) {
		boolean bool = false;
		List listOfOrders;
		try {
			int runningID = SerializeDB.incrementAndGetRunningID(fileNameForRunningID);
			order.setId(runningID);			
			
			listOfOrders = SerializeDB.readSerializedObject(fileName);
			listOfOrders.add(order);
			SerializeDB.writeSerializedObject(fileName, listOfOrders);
			bool = true;
		} catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
			bool = false;
		}
		return bool;
	}
	
	/**
	 * This function replace the existing order in the text file with an updated order.
	 * @param item - An order with updated values.
	 * @return Return TRUE if the update is successful. Return FALSE if the update fails.
	 */
	public boolean update(Order order)
	{
		boolean check = false;
		List listOfOrders;

		try 
		{
			listOfOrders = SerializeDB.readSerializedObject(fileName);

			for(int i = 0; i < listOfOrders.size(); i++)
			{
				Order tempOrder = (Order)listOfOrders.get(i);

				if(order.getId() == tempOrder.getId())
				{
					listOfOrders.set(i, order);
					SerializeDB.writeSerializedObject(fileName, listOfOrders);
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
	 * This function retrieves all the orders that is not yet paid for.
	 * @return A list of orders
	 */
	public List retrieveAllOpenOrder() 
	{
		List listOfOpenOrders = new ArrayList<Order>(); 
		List listOfOrders = null;

		try 
		{
			listOfOrders =  SerializeDB.readSerializedObject(fileName);

			for (int i = 0; i < listOfOrders.size(); i++)
			{
				Order tempOrder = (Order) listOfOrders.get(i);

				if (tempOrder.getClosed() == false) 
				{
					listOfOpenOrders.add(tempOrder);
				}
			}

		} catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
		}

		return listOfOpenOrders;
	}

	/**
	 * This function retrieves all the orders that is paid for.
	 * @return A list of orders
	 */
	public List retrieveAllClosedOrders() 
	{
		List listOfClosedOrders = new ArrayList<Order>(); 
		List listOfOrders = null;

		try 
		{
			listOfOrders =  SerializeDB.readSerializedObject(fileName);

			for (int i = 0; i < listOfOrders.size(); i++)
			{
				Order tempOrder = (Order) listOfOrders.get(i);

				if (tempOrder.getClosed() == true) 
				{
					listOfClosedOrders.add(tempOrder);
				}
			}

		} catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
		}

		return listOfClosedOrders;
	}
}