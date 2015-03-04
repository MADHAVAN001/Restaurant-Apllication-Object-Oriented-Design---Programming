package Business;

import Data.*;

/**
 * This class implements the Customer Manager controller
 * @author Low See Rong
 *	@version 1.0
 * @since 6th November 2014
 */
public class CustomerManager 
{
	/**
	 * This function creates the customer data.
	 * @param customer - A customer to be created.
	 * @return TRUE if the creation is successful. Return FALSE if the creation fails.
	 */
	public boolean createCustomer(Customer customer) 
	{
		// TODO - implement CustomerManager.createCustomer
		boolean check = false;
		CustomerDAO customerDAO = new CustomerDAO();
		
		check = customerDAO.insert(customer);
		
		return check;
	}
}