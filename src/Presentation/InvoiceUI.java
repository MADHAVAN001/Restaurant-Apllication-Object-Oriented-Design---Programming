package Presentation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import Business.*;
import Data.*;

/**
 * This class implements the boundary Invoice UI to interact with the user.
 * 
 * @author Pathangi Janardhanan Jatin Shravan
 * @version 1.0
 * @since 6th November 2014
 */
public class InvoiceUI {
	/**
	 * This function display a list of orders for the user to select from to
	 * print the order. Before printing, user would be ask to select a choice of
	 * discount the customer has and update the order. Once the order invoice is
	 * printed, the order is set to close and the table is now vacant.
	 */
	public static void printOrderInvoice() {
		System.out.print("\t\t");
		System.out.println("************Printing order Invoice************");
		// TODO - implement RRPSS.printOrderInvoice
		OrderManager orderManager = new OrderManager();
		List listOfOrders = orderManager.viewOrder();

		MembershipManager membershipManager = new MembershipManager();
		List listOfMemberships = membershipManager.onStartUp();
		Membership membership = null;

		TableManager tableManager = new TableManager();
		Table table = null;

		OrderedItemManager orderedItemManager = new OrderedItemManager();
		List listOfOrderedItems = null;
		OrderedPackageManager orderedPackageManager = new OrderedPackageManager();
		List listOfOrderedPromotionalPackage = null;
		Date date = new Date();

		int i = 0;
		int choice = 0;
		Order order = null;
		boolean check = false;
		Scanner sc = new Scanner(System.in);

		if (listOfOrders.size() == 0) {
			System.out.print("\t\t");
			System.out.format("%-25s:", "TASK STATUS");
			System.out.println("There is no orders!");
			return;
		}
		try {
			// print the list of orders for the user to select from.
			for (i = 0; i < listOfOrders.size(); i++) {
				order = (Order) listOfOrders.get(i);
				System.out.print("\t\t");

				System.out.println((i + 1) + ") Order: " + order.getId()
						+ " | Table: " + order.getTable().getId());
			}
			System.out.println();
			System.out.print("\t\t");

			System.out.print("Select an order to print the order invoice: ");
			choice = Integer.parseInt(sc.nextLine());

			order = (Order) listOfOrders.get(choice - 1);

			listOfOrderedItems = orderedItemManager
					.retrieveOrderedItemsByOrderID(order.getId());
			listOfOrderedPromotionalPackage = orderedPackageManager
					.retrieveOrderedPackageByOrderID(order.getId());

			System.out.println();
			// print the list of membership for the user to select from.
			for (i = 0; i < listOfMemberships.size(); i++) {
				membership = (Membership) listOfMemberships.get(i);
				System.out.print("\t\t");

				System.out.println((i + 1) + ") Membership ID: "
						+ membership.getId() + " | Name: "
						+ membership.getName() + " | Discount rate: "
						+ (membership.getDiscount() * 100) + "%");
			}
			System.out.println();
			System.out.print("\t\t");

			System.out.format("%-25s:", "Select a membership");

			choice = Integer.parseInt(sc.nextLine());

			membership = (Membership) listOfMemberships.get(choice - 1);
			order.setMembershipDiscount(membership);

			if (listOfOrderedItems.size() == 0
					&& listOfOrderedPromotionalPackage.size() == 0) {
				System.out.print("\t\t");
				System.out.format("%-25s:", "TASK STATUS");
				System.out.println("Order is empty!");
				// System.out.println("Total Cost: $0");
				// return;
			}

			double cost = 0;
			System.out.println();
			if (listOfOrderedItems.size() > 0) {
				System.out.print("\t\t");

				System.out.println("All Cart Items Ordered:");

				for (int j = 0; j < listOfOrderedItems.size(); j++) {
					OrderedItem orderedItem = (OrderedItem) listOfOrderedItems
							.get(j);
					System.out.print("\t\t");

					System.out.println((j + 1) + ") ID: "
							+ orderedItem.getItem().getId() + " | Name: "
							+ orderedItem.getItem().getName() + " | $"
							+ orderedItem.getPrice());
					cost += orderedItem.getPrice();
				}

				System.out.println();
			}

			if (listOfOrderedPromotionalPackage.size() > 0) {
				System.out.print("\t\t");

				System.out.println("Promotional Packages Ordered:");

				for (int j = 0; j < listOfOrderedPromotionalPackage.size(); j++) {
					OrderedPackage orderedPackage = (OrderedPackage) listOfOrderedPromotionalPackage
							.get(j);
					System.out.print("\t\t");

					System.out.println((j + 1) + ") ID: "
							+ orderedPackage.getPackage().getId() + " | Name: "
							+ orderedPackage.getPackage().getName() + " | $"
							+ orderedPackage.getPrice());
					cost += orderedPackage.getPrice();
				}
			}

			order.setTimeStamp(date);
			System.out.print("\t\t");

			System.out.println(order.getTimeStamp());
			order.setClosed(true);
			System.out.print("\t\t");
			System.out.format("%-25s:$", "Cost of the Order");
			System.out.println(cost);
			System.out.print("\t\t");

			System.out.println("Service Charge(" + (RRPSS.SERVICE_CHARGE * 100)
					+ "% of total order): $" + (cost * RRPSS.SERVICE_CHARGE));
			order.setServiceCharge(cost * RRPSS.SERVICE_CHARGE);
			order.setGST((cost + order.getServiceCharge()) * RRPSS.GST);
			System.out.print("\t\t");
			System.out.format("%-25s:$", "GST");
			System.out.println(order.getGST());
			double final_price = cost + order.getGST()
					+ order.getServiceCharge();
			System.out.print("\t\t");
			System.out.format("%-25s:$", "Total Price");
			System.out.println(final_price);
			double discount = final_price
					* order.getMembershipDiscount().getDiscount();
			System.out.print("\t\t");
			System.out.format("%-25s:$", "Membership Discount");
			System.out.println(discount);
			System.out.print("\t\t");
			System.out.println("Total Price After Discount: $"
					+ (final_price - discount));
			System.out.println();

			check = orderManager.closeOrder(order);

			if (check) {
				System.out.print("\t\t");
				System.out.format("%-25s:", "TASK STATUS");
				System.out.println("Order invoice is printed successfully!");

				table = order.getTable();
				table.setOrder(null);

				check = tableManager.updateTable(table);

				if (check) {
					System.out.print("\t\t");
					System.out.format("%-25s:", "TASK STATUS");
					System.out.println("Table " + table.getId()
							+ " is now vacant!");
				}

				else {
					System.out.print("\t\t");
					System.out.format("%-25s:", "TASK STATUS");
					System.out.println("Fail to update table status!");
				}
			}
		}

		catch (Exception e) {
			System.out.print("\t\t");
			System.out.format("%-25s:", "TASK STATUS");
			System.out.println("Invalid Input!");
		}
		System.out.print("\t\t");
		System.out.println("************End of Invoice************");
	}

	/**
	 * This function ask user to input a certain day. Then prints out a list of
	 * order invoice with complete breakdown of ordered items and packages and
	 * total revenue of that day.
	 */
	public static void printSaleRevenueReportByDay() {
		System.out.print("\t\t");
		System.out
				.println("************Sales Revenue Report by Day************");
		// TODO - implement RRPSS.printSaleRevenueReport
		OrderManager orderManager = new OrderManager();
		List listOfOrders = null;
		OrderedItemManager orderedItemManager = new OrderedItemManager();
		List listOfOrderedItems = null;
		OrderedPackageManager orderedPackageManager = new OrderedPackageManager();
		List listOfOrderedPromotionalPackage = null;

		int i = 0;
		int choice = 0;
		Order order = null;
		Scanner sc = new Scanner(System.in);
		double total_sales = 0;

		try {
			System.out.print("\t\t");

			System.out
					.print("Enter the date for which the details have to be retrieved(DDMMYYYY): ");
			String date_report;
			date_report = sc.nextLine();

			listOfOrders = orderManager.retrieveAllOrderByDay(date_report);

			if (listOfOrders.size() == 0) {
				System.out.print("\t\t");
				System.out.format("%-25s:", "TASK STATUS");
				System.out.println("There is no orders for that day!");
				return;
			}
			System.out.print("\t\t");

			System.out.println("Report of the day for Date: " + date_report);

			for (i = 0; i < listOfOrders.size(); i++) {
				order = (Order) listOfOrders.get(i);
				listOfOrderedItems = orderedItemManager
						.retrieveOrderedItemsByOrderID(order.getId());
				listOfOrderedPromotionalPackage = orderedPackageManager
						.retrieveOrderedPackageByOrderID(order.getId());
				System.out.print("\t\t");

				System.out.println("Order ID: " + order.getId() + " | Table: "
						+ order.getTable().getId());
				System.out.print("\t\t");

				System.out.println("Order Date: "
						+ order.getTimeStamp().toString());
				System.out.print("\t\t");

				System.out.println("Created by Staff ID: "
						+ order.getStaff().getId() + " | Name: "
						+ order.getStaff().getName());

				double cost = 0;
				System.out.println();
				if (listOfOrderedItems.size() > 0) {
					System.out.print("\t\t");

					System.out.println("All Cart Items Ordered:");

					for (int j = 0; j < listOfOrderedItems.size(); j++) {
						OrderedItem orderedItem = (OrderedItem) listOfOrderedItems
								.get(j);
						System.out.print("\t\t");

						System.out.println((j + 1) + ") ID: "
								+ orderedItem.getItem().getId() + " | Name: "
								+ orderedItem.getItem().getName() + " | $"
								+ orderedItem.getPrice());
						cost += orderedItem.getPrice();
					}

					System.out.println();
				}

				if (listOfOrderedPromotionalPackage.size() > 0) {
					System.out.print("\t\t");

					System.out.println("Promotional Packages Ordered:");

					for (int j = 0; j < listOfOrderedPromotionalPackage.size(); j++) {
						OrderedPackage orderedPackage = (OrderedPackage) listOfOrderedPromotionalPackage
								.get(j);
						System.out.print("\t\t");

						System.out.println((j + 1) + ") ID: "
								+ orderedPackage.getPackage().getId()
								+ " | Name: "
								+ orderedPackage.getPackage().getName()
								+ " | $" + orderedPackage.getPrice());
						cost += orderedPackage.getPrice();
					}
				}

				double final_price = cost + order.getGST()
						+ order.getServiceCharge();
				System.out.print("\t\t");
				System.out.format("%-25s:$", "Total Price");
				System.out.println(final_price);
				double discount = final_price
						* order.getMembershipDiscount().getDiscount();
				System.out.print("\t\t");
				System.out.format("%-25s:-$", "Membership Discoun");
				System.out.println(discount);
				System.out.print("\t\t");
				System.out.format("%-25s:$", "Total Price After Discount");
				System.out.println((final_price - discount));
				System.out.println();

				total_sales += final_price - discount;
			}
			System.out.print("\t\t");

			System.out.println("The total sales revenue for the day is: $"
					+ total_sales);
		}

		catch (Exception e) {
			System.out.print("\t\t");
			System.out.format("%-25s:", "TASK STATUS");
			System.out.println("Invalid Input!");
		}
		System.out.print("\t\t");
		System.out
				.println("************End of Sales Revenue Report************");
	}

	/**
	 * This function ask user to input a certain month. Then prints out the top
	 * & least amount sales and total revenue of that day. Then prints out the
	 * total revenue of that month.
	 */
	public static void printSaleRevenueReportByMonth() {
		System.out.print("\t\t");
		System.out
				.println("************Sales Revenue Report by Month************");
		OrderManager orderManager = new OrderManager();
		List listOfOrders = null;
		OrderedItemManager orderedItemManager = new OrderedItemManager();
		List listOfOrderedItems = null;
		OrderedPackageManager orderedPackageManager = new OrderedPackageManager();
		List listOfOrderedPromotionalPackage = null;

		int i = 0;
		int choice = 0;
		Order order = null;
		Scanner sc = new Scanner(System.in);
		double total_sales = 0;

		String dateMinSales = "";
		double minSales = 0;
		double minRevenue = 0;
		String dateMaxSales = "";
		double maxSales = 0;
		double maxRevenue = 0;

		try {
			System.out.print("\t\t");

			System.out
					.print("Enter the date for which the details have to be retrieved(MMYYYY): ");
			String date_report;
			date_report = sc.nextLine();

			listOfOrders = orderManager.retrieveAllOrderByMonth(date_report);

			if (listOfOrders.size() == 0) {
				System.out.print("\t\t");
				System.out.format("%-25s:", "TASK STATUS");
				System.out.println("There is no orders for that month!");
				return;
			}
			System.out.print("\t\t");

			System.out.println("Report of the month for Date: " + date_report);
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			ArrayList<String> listOfVisitedDate = new ArrayList<String>();
			ArrayList<Integer> listOfVisitedOrderID = new ArrayList<Integer>();

			for (i = 0; i < listOfOrders.size(); i++) {
				order = (Order) listOfOrders.get(i);
				double cost = 0;
				int salesCounter = 0;

				if (listOfVisitedDate.contains(dateFormat.format(order
						.getTimeStamp())) == false) {
					// mark date as visited
					listOfVisitedDate.add(dateFormat.format(order
							.getTimeStamp()));

					for (int k = 0; k < listOfOrders.size(); k++) {
						double final_price = 0;
						// start calculating total revenue for that day.
						Order tempOrder = (Order) listOfOrders.get(k);

						if (dateFormat.format(order.getTimeStamp()).equals(
								dateFormat.format(tempOrder.getTimeStamp()))) {
							if (listOfVisitedOrderID
									.contains(tempOrder.getId()) == false) {
								// mark order as visited.
								listOfVisitedOrderID.add(tempOrder.getId());
								salesCounter++;
								listOfOrderedItems = orderedItemManager
										.retrieveOrderedItemsByOrderID(tempOrder
												.getId());
								listOfOrderedPromotionalPackage = orderedPackageManager
										.retrieveOrderedPackageByOrderID(tempOrder
												.getId());

								for (int j = 0; j < listOfOrderedItems.size(); j++) {
									OrderedItem orderedItem = (OrderedItem) listOfOrderedItems
											.get(j);
									final_price += orderedItem.getPrice();
								}

								for (int j = 0; j < listOfOrderedPromotionalPackage
										.size(); j++) {
									OrderedPackage orderedPackage = (OrderedPackage) listOfOrderedPromotionalPackage
											.get(j);
									final_price += orderedPackage.getPrice();
								}

								final_price = final_price + tempOrder.getGST()
										+ tempOrder.getServiceCharge();
								final_price = final_price
										- (final_price * tempOrder
												.getMembershipDiscount()
												.getDiscount());
								cost += final_price;
							}
						}

					}

					total_sales += cost;

					// first loop, initialize max and min
					if (i == 0) {
						dateMinSales = dateFormat.format(order.getTimeStamp());
						minSales = salesCounter;
						minRevenue = cost;
						dateMaxSales = dateFormat.format(order.getTimeStamp());
						maxSales = salesCounter;
						maxRevenue = cost;
					}

					else {
						if (minRevenue >= cost) {
							dateMinSales = dateFormat.format(order
									.getTimeStamp());
							minSales = salesCounter;
							minRevenue = cost;
						}

						if (maxRevenue <= cost) {
							dateMaxSales = dateFormat.format(order
									.getTimeStamp());
							maxSales = salesCounter;
							maxRevenue = cost;
						}
					}
				}
			}
			System.out.print("\t\t");
			System.out.format("%-50s:",
					"The top sales revenue of that month is in");
			System.out.println("The top sales revenue of that month is in: "
					+ dateMaxSales);
			System.out.print("\t\t");
			System.out.format("%-50s:", "Total number of sales that day");
			System.out.println(maxSales);
			System.out.print("\t\t");
			System.out.format("%-50s:$", "Total revenue of that day");
			System.out.println(maxRevenue);
			System.out.print("\t\t");
			System.out.format("%-50s:",
					"The least sales revenue of that month is in");
			System.out.println(dateMinSales);
			System.out.print("\t\t");
			System.out.format("%-50s:", "Total number of sales that day");
			System.out.println(minSales);
			System.out.print("\t\t");
			System.out.format("%-50s:$", "Total revenue of that day");
			System.out.println(minRevenue);
			System.out.print("\t\t");
			System.out.format("%-50s:", "The total sales for that month is");
			System.out.println(total_sales);
		}

		catch (Exception e) {
			System.out.print("\t\t");
			System.out.format("%-25s:", "TASK STATUS");
			System.out.println("Invalid Input!");
		}
		System.out.print("\t\t");
		System.out
				.println("************End of Sales Revenue Report************");
	}
}
