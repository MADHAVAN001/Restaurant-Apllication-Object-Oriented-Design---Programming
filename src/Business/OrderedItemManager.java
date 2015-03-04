package Business;

import java.util.List;

import Data.*;

/**
 * This class implements the Ordered Item Manager controller
 * @author Seshadri Madhavan
 *	@version 1.0
 * @since 7th November 2014
 */

public class OrderedItemManager {

	/**
	 * This function creates the ordered item.
	 * @param orderedItem - An ordered item to be created.
	 * @return TRUE if the creation is successful. Return FALSE if the creation fails.
	 */
	public boolean createOrderedItem(OrderedItem orderedItem) {
		// TODO - implement OrderedItemManager.createOrderedItem
		boolean check = false;
		OrderedItemDAO ordereditemDAO = new OrderedItemDAO();
		check=ordereditemDAO.insert(orderedItem);
		return check;
	}

	/**
	 * This function removes the ordered item
	 * @param orderedItem - An ordered item to be removed
	 * @return TRUE if the deletion is successful. Return FALSE if the deletion fails.
	 */
	public boolean removeOrderedItem(OrderedItem orderedItem) {
		// TODO - implement OrderedItemManager.removeOrderedItem
		boolean check=false;
        OrderedItemDAO ordereditemDAO = new OrderedItemDAO();
        check=ordereditemDAO.delete(orderedItem);
		return check;
	}

	/**
	 * This function retrieves all the ordered items belonging to the order id.
	 * @param id - An order ID.
	 * @return A list of Ordered Items.
	 */
	public List retrieveOrderedItemsByOrderID(int id) {
		// TODO - implement OrderedItemManager.retrieveOrderedItemsByOrderID
		OrderedItemDAO orderedItemDAO = new OrderedItemDAO();
		
		List listOfOrderedItem = orderedItemDAO.retrieveAllByOrderID(id);
		
		return listOfOrderedItem;
	}

}