package Presentation;

import java.util.List;
import java.util.Scanner;

import Business.*;
import Data.*;

/**
 * This class implements the boundary Order UI to interact with the user.
 * 
 * @author Bansal Ankur
 * @version 1.0
 * @since 6th November 2014
 */
public class OrderUI {
	/**
	 * This function ask user to select a table to create an order.
	 * 
	 * @param staff
	 *            - A staff whom is creating this order.
	 **/
	public static void createOrder(Staff staff) {
		System.out.print("\t\t");
		System.out.println("************Creating a new order************");
		// TODO - implement RRPSS.createOrder
		TableManager tableManager = new TableManager();
		List listOfTables = tableManager.onStartUp();

		OrderManager orderManager = new OrderManager();
		Table table = null;
		Order order = null;
		int i = 0;
		int choice = 0;
		boolean check = false;
		Scanner sc = new Scanner(System.in);

		try {
			// print the list of tables for the user to select from.
			for (i = 0; i < listOfTables.size(); i++) {
				table = (Table) listOfTables.get(i);
				System.out.print("\t\t");
				System.out.println((i + 1) + ") Table " + table.getId()
						+ " | Size: " + table.getCapacity());
			}
			System.out.println();
			System.out.print("\t\t");
			System.out.print("Select a table to create an order: ");
			choice = Integer.parseInt(sc.nextLine());

			table = (Table) listOfTables.get(choice - 1);

			order = new Order();
			order.setStaff(staff);
			order.setTable(table);
			order.setClosed(false);

			check = orderManager.createOrder(order);

			if (check) {
				System.out.print("\t\t");
				System.out.format("%-25s:", "TASK UPDATE");
				System.out.println("Order is created successfully!");

				check = tableManager.updateTable(table);

				if (check) {
					System.out.print("\t\t");
					System.out.format("%-25s:", "TASK STATUS");
					System.out.println("Added order to table!");
				}

				else {
					System.out.print("\t\t");
					System.out.format("%-25s:", "TASK STATUS");
					System.out.println("Failed to add order to table!");
				}
			}

			else {
				System.out.print("\t\t");
				System.out.format("%-25s:", "TASK STATUS");
				System.out.println("Failed to created order!");
			}
		}

		catch (Exception e) {
			System.out.print("\t\t");
			System.out.format("%-25s:", "TASK STATUS");
			System.out.println("Invalid Input!");
		}
		System.out.print("\t\t");
		System.out.println("************End of creating new order************");
	}

	/**
	 * This function display a list of open orders and ask the user to select
	 * one of them to view a list of ordered item/promotional packages.
	 */
	public static void viewOrder() {
		System.out.print("\t\t");
		System.out.println("************Viewing existing orders************");
		// TODO - implement RRPSS.viewOrder
		OrderManager orderManager = new OrderManager();
		List listOfOrders = orderManager.viewOrder();

		OrderedItemManager orderedItemManager = new OrderedItemManager();
		List listOfOrderedItems = null;
		OrderedPackageManager orderedPackageManager = new OrderedPackageManager();
		List listOfOrderedPromotionalPackage = null;

		int i = 0;
		int choice = 0;
		Order order = null;
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

			System.out.print("Select an order to view the item ordered: ");
			choice = Integer.parseInt(sc.nextLine());

			order = (Order) listOfOrders.get(choice - 1);

			listOfOrderedItems = orderedItemManager
					.retrieveOrderedItemsByOrderID(order.getId());
			listOfOrderedPromotionalPackage = orderedPackageManager
					.retrieveOrderedPackageByOrderID(order.getId());

			if (listOfOrderedItems.size() == 0
					&& listOfOrderedPromotionalPackage.size() == 0) {
				System.out.print("\t\t");
				System.out.format("%-25s:", "TASK STATUS");
				System.out.println("Order is empty!");
				return;
			}

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
				}

				System.out.println();
			}
		}

		catch (Exception e) {
			System.out.print("\t\t");
			System.out.format("%-25s:", "TASK STATUS");
			System.out.println("Invalid Input!");
		}
		System.out.print("\t\t");
		System.out.println("************End of viewing orders************");
	}

	/**
	 * This function display a list of orders for the user to select from, then
	 * displays a list of items for the user to add it into the order.
	 */
	public static void addOrderedItem() {
		System.out.print("\t\t");
		System.out.println("************Adding ordered item************");
		OrderManager orderManager = new OrderManager();
		List listOfOrders = orderManager.viewOrder();

		OrderedItemManager orderedItemManager = new OrderedItemManager();
		List listOfOrderedItems = null;

		ItemManager itemManager = new ItemManager();
		List listOfItems = itemManager.onStartUp();

		int i = 0;
		int choice = 0;
		Order order = null;
		Item item = null;
		OrderedItem orderedItem = null;
		boolean check = false;
		Scanner sc = new Scanner(System.in);

		if (listOfOrders.size() == 0) {
			System.out.print("\t\t");
			System.out.format("%-25s:", "TASK STATUS");
			System.out.println("There is no orders!");
			return;
		}

		if (listOfItems.size() == 0) {
			System.out.print("\t\t");
			System.out.format("%-25s:", "TASK STATUS");
			System.out.println("There is no items!");
			return;
		}

		try {
			System.out.println();
			// print the list of orders for the user to select from.
			for (i = 0; i < listOfOrders.size(); i++) {
				order = (Order) listOfOrders.get(i);
				System.out.print("\t\t");

				System.out.println((i + 1) + ") Order: " + order.getId()
						+ " | Table: " + order.getTable().getId());
			}
			System.out.println();
			System.out.print("\t\t");

			System.out.print("Select an order to add the item ordered: ");
			choice = Integer.parseInt(sc.nextLine());

			order = (Order) listOfOrders.get(choice - 1);

			do {
				for (i = 0; i < listOfItems.size(); i++) {
					item = (Item) listOfItems.get(i);
					System.out.print("\t\t");

					System.out.println((i + 1) + ") ID: " + item.getId()
							+ " | Name: " + item.getName() + " | Price: $"
							+ item.getPrice());
				}
				System.out.print("\t\t");
				System.out.println((i + 1) + ") Done");
				System.out.println();
				System.out.print("\t\t");

				System.out.print("Select an item to add into order: ");
				choice = Integer.parseInt(sc.nextLine());

				if (choice != (i + 1)) {
					item = (Item) listOfItems.get(choice - 1);

					orderedItem = new OrderedItem();
					orderedItem.setItem(item);
					orderedItem.setOrder(order);
					orderedItem.setPrice(item.getPrice());

					order.addOrderedItem(orderedItem);

					check = orderedItemManager.createOrderedItem(orderedItem);

					if (check) {
						System.out.print("\t\t");
						System.out.format("%-25s:", "TASK UPDATE");
						System.out
								.println("Item added into order successfully!");
					}

					else {
						System.out.print("\t\t");
						System.out.format("%-25s:", "TASK STATUS");
						System.out.println("Failed to add item into order!");
					}
				}

			} while (choice != (i + 1));

		}

		catch (Exception e) {
			System.out.print("\t\t");
			System.out.format("%-25s:", "TASK STATUS");
			System.out.println("Invalid Input!");
		}
		System.out.print("\t\t");
		System.out.println("************End of adding items************");
	}

	/**
	 * This function display a list of orders for the user to select from, then
	 * displays a list of promotional packages for the user to add it into the
	 * order.
	 */
	public static void addOrderedPromotionalPackage() {
		System.out.print("\t\t");
		System.out
				.println("************Adding promotional Package************");
		OrderManager orderManager = new OrderManager();
		List listOfOrders = orderManager.viewOrder();

		OrderedPackageManager orderedPromotionalPackageManager = new OrderedPackageManager();
		List listOfOrderedPromotionalPackage = null;

		PackageManager promotionalPackageManager = new PackageManager();
		List listOfPromotionalPackages = promotionalPackageManager.onStartUp();

		int i = 0;
		int choice = 0;
		Order order = null;
		PromotionalPackage promotionalPackage = null;
		OrderedPackage orderedPromotionalPackage = null;
		boolean check = false;
		Scanner sc = new Scanner(System.in);

		if (listOfOrders.size() == 0) {
			System.out.print("\t\t");
			System.out.format("%-25s:", "TASK STATUS");
			System.out.println("There is no orders!");
			return;
		}

		if (listOfPromotionalPackages.size() == 0) {
			System.out.print("\t\t");
			System.out.format("%-25s:", "TASK STATUS");
			System.out.println("There is no promotional packages!");
			return;
		}

		try {
			System.out.println();
			// print the list of orders for the user to select from.
			for (i = 0; i < listOfOrders.size(); i++) {
				order = (Order) listOfOrders.get(i);
				System.out.print("\t\t");

				System.out.println((i + 1) + ") Order: " + order.getId()
						+ " | Table: " + order.getTable().getId());
			}
			System.out.println();
			System.out.print("\t\t");

			System.out
					.print("Select an order to add the promotional package ordered: ");
			choice = Integer.parseInt(sc.nextLine());

			order = (Order) listOfOrders.get(choice - 1);

			do {
				for (i = 0; i < listOfPromotionalPackages.size(); i++) {
					promotionalPackage = (PromotionalPackage) listOfPromotionalPackages
							.get(i);
					System.out.print("\t\t");

					System.out.println((i + 1) + ") ID: "
							+ promotionalPackage.getId() + " | Name: "
							+ promotionalPackage.getName() + " | Price: $"
							+ promotionalPackage.getPrice());
				}
				System.out.print("\t\t");
				System.out.println((i + 1) + ") Done");
				System.out.println();
				System.out.print("\t\t");

				System.out.print("Select an item to add into order: ");
				choice = Integer.parseInt(sc.nextLine());

				if (choice != (i + 1)) {
					promotionalPackage = (PromotionalPackage) listOfPromotionalPackages
							.get(choice - 1);

					orderedPromotionalPackage = new OrderedPackage();
					orderedPromotionalPackage.setPackage(promotionalPackage);
					orderedPromotionalPackage.setOrder(order);
					orderedPromotionalPackage.setPrice(promotionalPackage
							.getPrice());

					order.addOrderedPackage(orderedPromotionalPackage);

					check = orderedPromotionalPackageManager
							.createOrderedPackage(orderedPromotionalPackage);

					if (check) {
						System.out.print("\t\t");
						System.out.format("%-25s:", "TASK STATUS");
						System.out
								.println("Promotional package added into order successfully!");
					}

					else {
						System.out.print("\t\t");
						System.out.format("%-25s:", "TASK STATUS");
						System.out
								.println("Failed to add promotional package into order!");
					}
				}

			} while (choice != (i + 1));

		}

		catch (Exception e) {
			System.out.print("\t\t");
			System.out.format("%-25s:", "TASK STATUS");
			System.out.println("Invalid Input!");
		}
		System.out.print("\t\t");
		System.out
				.println("************End of adding Promotional Package************");
	}

	/**
	 * This function display a list of orders for the user to select from, then
	 * displays a list of ordered items belonging to that order for the user to
	 * remove it from the order.
	 */
	public static void removeOrderedItem() {
		System.out.print("\t\t");
		System.out.println("************Removing ordered item************");
		OrderManager orderManager = new OrderManager();
		List listOfOrders = orderManager.viewOrder();

		OrderedItemManager orderedItemManager = new OrderedItemManager();
		List listOfOrderedItems = null;

		int i = 0;
		int choice = 0;
		Order order = null;
		OrderedItem orderedItem = null;
		boolean check = false;
		Scanner sc = new Scanner(System.in);

		if (listOfOrders.size() == 0) {
			System.out.print("\t\t");
			System.out.format("%-25s:", "TASK STATUS");
			System.out.println("There is no orders!");
			return;
		}

		try {
			System.out.println();
			// print the list of orders for the user to select from.
			for (i = 0; i < listOfOrders.size(); i++) {
				order = (Order) listOfOrders.get(i);
				System.out.print("\t\t");

				System.out.println((i + 1) + ") Order: " + order.getId()
						+ " | Table: " + order.getTable().getId());
			}
			System.out.println();
			System.out.print("\t\t");

			System.out.print("Select an order to remove the item ordered: ");
			choice = Integer.parseInt(sc.nextLine());

			order = (Order) listOfOrders.get(choice - 1);

			listOfOrderedItems = orderedItemManager
					.retrieveOrderedItemsByOrderID(order.getId());

			do {
				System.out.println();
				if (listOfOrderedItems.size() == 0) {
					System.out.print("\t\t");
					System.out.format("%-25s:", "TASK STATUS");
					System.out.println("There is no ordered items!");
					return;
				}

				for (i = 0; i < listOfOrderedItems.size(); i++) {
					orderedItem = (OrderedItem) listOfOrderedItems.get(i);
					System.out.print("\t\t");

					System.out.println((i + 1) + ") ID: "
							+ orderedItem.getItem().getId() + " | Name: "
							+ orderedItem.getItem().getName() + " | $"
							+ orderedItem.getPrice());
				}
				System.out.print("\t\t");
				System.out.println((i + 1) + ") Done");
				System.out.println();
				System.out.print("\t\t");

				System.out
						.print("Select an ordered item to remove from order: ");
				choice = Integer.parseInt(sc.nextLine());

				if (choice != (i + 1)) {
					orderedItem = (OrderedItem) listOfOrderedItems
							.get(choice - 1);

					check = orderedItemManager.removeOrderedItem(orderedItem);

					if (check) {
						System.out.print("\t\t");
						System.out.format("%-25s:", "TASK STATUS");
						System.out
								.println("Ordered item removed from order successfully!");
						listOfOrderedItems.remove(orderedItem);
					}

					else {
						System.out.print("\t\t");
						System.out.format("%-25s:", "TASK STATUS");
						System.out
								.println("Failed to remove ordered item from order!");
					}
				}

			} while (choice != (i + 1));

		}

		catch (Exception e) {
			System.out.print("\t\t");
			System.out.format("%-25s:", "TASK STATUS");
			System.out.println("Invalid Input!");
		}
		System.out.print("\t\t");
		System.out.println("************End of removing items************");
	}

	/**
	 * This function display a list of orders for the user to select from, then
	 * displays a list of ordered promotional package belonging to that order
	 * for the user to remove it from the order.
	 */
	public static void removeOrderedPromotionalPackage() {
		System.out.print("\t\t");
		System.out
				.println("************Removing ordered Promotional Package************");
		OrderManager orderManager = new OrderManager();
		List listOfOrders = orderManager.viewOrder();

		OrderedPackageManager orderedPackageManager = new OrderedPackageManager();
		List listOfOrderedPromotionalPackages = null;

		int i = 0;
		int choice = 0;
		Order order = null;
		OrderedPackage orderedPackage = null;
		boolean check = false;
		Scanner sc = new Scanner(System.in);

		if (listOfOrders.size() == 0) {
			System.out.print("\t\t");
			System.out.format("%-25s:", "TASK STATUS");
			System.out.println("There is no orders!");
			return;
		}

		try {
			System.out.println();
			// print the list of orders for the user to select from.
			for (i = 0; i < listOfOrders.size(); i++) {
				order = (Order) listOfOrders.get(i);
				System.out.print("\t\t");

				System.out.println((i + 1) + ") Order: " + order.getId()
						+ " | Table: " + order.getTable().getId());
			}
			System.out.println();
			System.out.print("\t\t");

			System.out
					.print("Select an order to remove the promotional package ordered: ");
			choice = Integer.parseInt(sc.nextLine());

			order = (Order) listOfOrders.get(choice - 1);

			listOfOrderedPromotionalPackages = orderedPackageManager
					.retrieveOrderedPackageByOrderID(order.getId());

			do {
				System.out.println();
				if (listOfOrderedPromotionalPackages.size() == 0) {
					System.out.print("\t\t");
					System.out.format("%-25s:", "TASK STATUS");
					System.out
							.println("There is no ordered promotional package!");
					return;
				}

				for (i = 0; i < listOfOrderedPromotionalPackages.size(); i++) {
					orderedPackage = (OrderedPackage) listOfOrderedPromotionalPackages
							.get(i);
					System.out.print("\t\t");

					System.out.println((i + 1) + ") ID: "
							+ orderedPackage.getPackage().getId() + " | Name: "
							+ orderedPackage.getPackage().getName() + " | $"
							+ orderedPackage.getPrice());
				}
				System.out.print("\t\t");
				System.out.println((i + 1) + ") Done");
				System.out.println();
				System.out.print("\t\t");

				System.out
						.print("Select an ordered promotional package to remove from order: ");
				choice = Integer.parseInt(sc.nextLine());

				if (choice != (i + 1)) {
					orderedPackage = (OrderedPackage) listOfOrderedPromotionalPackages
							.get(choice - 1);

					check = orderedPackageManager
							.removeOrderedPackage(orderedPackage);

					if (check) {
						System.out.print("\t\t");
						System.out.format("%-25s:", "TASK STATUS");
						System.out
								.println("Ordered promotional package removed from order successfully!");
						listOfOrderedPromotionalPackages.remove(orderedPackage);
					}

					else {
						System.out.print("\t\t");
						System.out.format("%-25s:", "TASK STATUS");
						System.out
								.println("Failed to remove ordered promotional package from order!");
					}
				}

			} while (choice != (i + 1));

		}

		catch (Exception e) {
			System.out.print("\t\t");
			System.out.format("%-25s:", "TASK STATUS");
			System.out.println("Invalid Input!");
		}
		System.out.print("\t\t");
		System.out
				.println("************End of removing promotional Package************");
	}
}
