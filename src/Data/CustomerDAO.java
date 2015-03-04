package Data;

import java.util.List;

/**
 * This class implements the Customer Data Access Object with the attributes fileName, fileNameForRunningID.
 * 
 * This class is solely responsible for Create/Retrieve/Update/Delete functions from our flat file(Text File).
 * Where a customer is allowed to make one reservation.
 * 
 * This class is using Serialization to write Customer objects into a text file.
 * 
 * @author Low See Rong
 * @version 1.0
 * @since 24th October 2014
 */
public class CustomerDAO 
{
	/**
	 * The file name where the customer object is stored.
	 */
	private String fileName;

	/**
	 * The file name for keeping track of the customer unique running id.
	 */
	private String fileNameForRunningID;

	/**
	 * Default constructor.
	 * 
	 * fileName = "Customer.txt";
	 *	fileNameForRunningID = "CustomerRunningID.txt";
	 */
	public CustomerDAO()
	{
		fileName = "Customer.txt";
		fileNameForRunningID = "CustomerRunningID.txt";
	}

	/**
	 * This function serialize the customer object and writes it into a text file.
	 * @param customer - A customer object to be stored into a text file.
	 * @return TRUE if the insertion is successful. Return FALSE if the insertion fails.
	 */
	public boolean insert(Customer customer) 
	{
		List listOfCustomers;

		try 
		{
			int runningID = SerializeDB.incrementAndGetRunningID(fileNameForRunningID);			

			listOfCustomers = SerializeDB.readSerializedObject(fileName);
			customer.setId(runningID);
			listOfCustomers.add(customer);			
			SerializeDB.writeSerializedObject(fileName, listOfCustomers);
			return true;
		} 

		catch (Exception e) 
		{
			System.out.println("Exception >> " + e.getMessage());
			return false;
		}	
	}

	/**
	 * This function reads the list of serialized customer objects and return the customer object that matches the id.
	 * @param id - An ID of a customer.
	 * @return A customer.
	 */
	public Customer retrieveByID(int id) 
	{
		List list;
		Customer customer = new Customer();
		try {

			list = (List) SerializeDB.readSerializedObject(fileName);

			for (int i = 0 ; i < list.size() ; i++) 
			{
				Customer tempCustomer = (Customer)list.get(i);

				if(tempCustomer.getId() == id)
				{
					customer = tempCustomer;
				}
			}

		} catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
		}
		return customer;
	}

}