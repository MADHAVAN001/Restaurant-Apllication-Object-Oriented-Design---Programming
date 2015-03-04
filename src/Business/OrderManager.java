package Business;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import Data.*;

/**
 * This class implements the Order Manager controller
 * @author Bansal Ankur
 *	@version 1.0
 * @since 7th November 2014
 */
public class OrderManager 
{
	/**
	 * This function verifies if the order to be created does not overwrites the previous order to the table.
	 * @param order - An order to be created.
	 * @return TRUE if the creation is successful. Return FALSE if the creation fails.
	 */
	public boolean createOrder(Order order) {
		// TODO - implement OrderManager.createOrder
		boolean check = false;
		OrderDAO orderDAO = new OrderDAO();
		
		if(order.getTable().getOrder() != null)
		{
			System.out.print("\t\t");
			System.out.format("%-25s:", "TASK STATUS");
			System.out.println("Table already have an existing order!");
			check = false;
			return check;
		}
		
		order.getTable().setOrder(order);
		check = orderDAO.insert(order);
		return check;
	}

	/**
	 * This function retrieves all the orders that is still open.
	 * @return A list of orders
	 */
	public List viewOrder() {
		// TODO - implement OrderManager.viewOrder
		OrderDAO orderDAO = new OrderDAO();
		List listOfOrders = orderDAO.retrieveAllOpenOrder();		
		return listOfOrders;
	}

	/**
	 * This functions updates an order that is closed.
	 * @param order - An order to be updated.
	 */
	public boolean closeOrder(Order order) {
		// TODO - implement OrderManager.closeOrder
		OrderDAO orderDAO = new OrderDAO();
		boolean check = false;
		
		check = orderDAO.update(order);
		
		return check;
	}

	/**
	 * This function retrieves all orders that is closed on a certain day.
	 * @param date_DDMMYYYY - A date with the format (ddMMyyyy).
	 * @return A list of orders.
	 */
	public List retrieveAllOrderByDay(String date_DDMMYYYY) 
	{
		// TODO - implement OrderManager.retrieveAllOrderByPeriod
		OrderDAO orderDAO = new OrderDAO();
		List listOfOrders = orderDAO.retrieveAllClosedOrders();
		List listOfOrdersByDay = new ArrayList<Order>();
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		
		for(int i = 0; i < listOfOrders.size(); i++)
		{
			Order order = (Order)listOfOrders.get(i);
			
			if(dateFormat.format(order.getTimeStamp()).equals(date_DDMMYYYY))
			{
				listOfOrdersByDay.add(order);
			}
		}
		
		return listOfOrdersByDay;
	}
	
	/**
	 * This function retrieves all orders that is closed on a certain month.
	 * @param date_MMYYYY - A date with the format (MMyyyy).
	 * @return A list of orders.
	 */
	public List retrieveAllOrderByMonth(String date_MMYYY) 
	{
		// TODO - implement OrderManager.retrieveAllOrderByPeriod
		OrderDAO orderDAO = new OrderDAO();
		List listOfOrders = orderDAO.retrieveAllClosedOrders();
		List listOfOrdersByMonth = new ArrayList<Order>();
		DateFormat dateFormat = new SimpleDateFormat("MMyyyy");
		
		for(int i = 0; i < listOfOrders.size(); i++)
		{
			Order order = (Order)listOfOrders.get(i);
			
			if(dateFormat.format(order.getTimeStamp()).equals(date_MMYYY))
			{
				listOfOrdersByMonth.add(order);
			}
		}
		
		return listOfOrdersByMonth;
	}

}