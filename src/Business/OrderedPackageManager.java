package Business;

import java.util.List;

import Data.*;

/**
 * This class implements the Ordered Package Manager controller
 * @author Seshadri Madhavan
 *	@version 1.0
 * @since 7th November 2014
 */
public class OrderedPackageManager {

	/**
	 * This function creates the ordered package
	 * @param orderedPackage - An ordered package to be created.
	 * @return TRUE if the creation is successful. Return FALSE if the creation fails.
	 */
	public boolean createOrderedPackage(OrderedPackage orderedPackage) 
	{
		// TODO - implement OrderedPackageManager.createOrderedPackage
		boolean check=false;
		
		OrderedPackageDAO orderedPackageDAO = new OrderedPackageDAO();
		
		check = orderedPackageDAO.insert(orderedPackage);
		
		return check;
	}

	/**
	 * This function removes the ordered package
	 * @param orderedPackage - An ordered package to be removed.
	 * @return TRUE if the deletion is successful. Return FALSE if the deletion fails.
	 */
	public boolean removeOrderedPackage(OrderedPackage orderedPackage) {
		// TODO - implement OrderedPackageManager.removeOrderedPackage
		boolean check=false;
		
		OrderedPackageDAO orderedPackageDAO = new OrderedPackageDAO();
		
		check = orderedPackageDAO.delete(orderedPackage);
		
	    return check;
	}

	/**
	 * This function retrieve all the ordered packages that belongs to the order id.
	 * @param id - An order ID.
	 * @return A list of Ordered Packages.
	 */
	public List retrieveOrderedPackageByOrderID(int id) 
	{
		// TODO - implement OrderedPackageManager.retrieveOrderedPackageByPackageID
		OrderedPackageDAO orderedPackageDAO = new OrderedPackageDAO();
		
		List listOfOrderedPackage = orderedPackageDAO.retrieveAllByOrderID(id);
		
		return listOfOrderedPackage;
	}

}