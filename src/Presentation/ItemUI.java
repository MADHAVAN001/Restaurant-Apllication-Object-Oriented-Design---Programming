package Presentation;

import java.util.List;
import java.util.Scanner;

import Business.*;
import Data.*;

/**
 * This class implements the boundary Item UI to interact with the user.
 * 
 * @author Seshadri Madhavan
 * @version 1.0
 * @since 6th November 2014
 */

public class ItemUI {
	/**
	 * This function ask the user to input the item name, type, price and
	 * description that the user wishes to create.
	 */
	public static void createMenuItem() {
		// TODO - implement RRPSS.createMenuItem
		try {
			Item item = new Item();
			Scanner sc = new Scanner(System.in);

			boolean check = false;
			ItemManager itemManager = new ItemManager();
			System.out.println();
			System.out.print("\t\t");
			System.out.println("************Creating a new item************");
			System.out.print("\t\t");
			System.out.format("%-25s:", "Enter item name");
			item.setName(sc.nextLine());
			System.out.print("\t\t");
			System.out.format("%-25s:", "Enter item type");
			item.setType(sc.nextLine());
			System.out.print("\t\t");
			System.out.format("%-25s:$", "Enter item price");
			item.setPrice(Double.parseDouble(sc.nextLine()));
			System.out.print("\t\t");
			System.out.format("%-25s:", "Enter item description");
			item.setDescription(sc.nextLine());

			item.setRemoved(false);

			check = itemManager.createMenuItem(item);

			if (check == true) {
				System.out.print("\t\t");
				System.out.format("%-25s:", "TASK STATUS");
				System.out.println("Item created successfully!");
			}

			else {
				System.out.println("Fail to create item!");
			}
			System.out.print("\t\t");
			System.out.println("************End Creation of item************");
		}

		catch (Exception e) {
			System.out.println("Invalid Input!");
		}
	}

	/**
	 * This function ask the user to select an item from a list of items he/she
	 * wishes to update. This function is only able to update the item's price.
	 */
	public static void updateMenuItem() {
		System.out.print("\t\t");
		System.out.println("************Updating Menu Item************");
		// TODO - implement RRPSS.updateMenuItem
		try {
			Scanner sc = new Scanner(System.in);

			ItemManager itemManager = new ItemManager();

			List listOfItems = itemManager.onStartUp();

			int choice = 0;
			Item item = null;
			boolean check = false;

			if (listOfItems.size() == 0) {
				System.out.print("\t\t");
				System.out.format("%-25s:", "TASK STATUS");
				System.out.println("No items to update!");
			}

			else {
				// print the list of items for the user to select from.
				for (int i = 0; i < listOfItems.size(); i++) {
					item = (Item) listOfItems.get(i);
					System.out.print("\t\t");
					System.out.println((i + 1) + ") ID: " + item.getId()
							+ " | Name: " + item.getName() + " | Price: $"
							+ item.getPrice());
				}
				System.out.println();
				System.out.print("\t\t");
				System.out.print("Select an item to update the price: ");
				choice = Integer.parseInt(sc.nextLine());

				item = (Item) listOfItems.get(choice - 1);
				System.out.print("\t\t");
				System.out.format("%-25s:$", "Enter price");
				item.setPrice(Double.parseDouble(sc.nextLine()));

				check = itemManager.updateMenuItem(item);

				if (check) {
					System.out.print("\t\t");
					System.out.format("%-25s:", "TASK STATUS");
					System.out.println("Update item successfully!");
				}

				else {
					System.out.print("\t\t");
					System.out.format("%-25s:", "TASK STATUS");
					System.out.println("Fail to update item!");
				}
			}
		}

		catch (Exception e) {
			System.out.print("\t\t");
			System.out.format("%-25s:", "TASK STATUS");
			System.out.println("Invalid Input!");
		}
		System.out.print("\t\t");
		System.out.println("************End of Updation************");
	}

	/**
	 * This function ask the user to select an item from a list of items he/she
	 * wishes to delete.
	 */
	public static void removeMenuItem() {
		System.out.print("\t\t");
		System.out.println("************Removing Menu Item************");
		// TODO - implement RRPSS.removeMenuItem
		try {
			Scanner sc = new Scanner(System.in);

			ItemManager itemManager = new ItemManager();

			List listOfItems = itemManager.onStartUp();

			int choice = 0;
			Item item = null;
			boolean check = false;

			if (listOfItems.size() == 0) {
				System.out.print("\t\t");
				System.out.format("%-25s:", "TASK STATUS");
				System.out.println("No items to delete!");
			}

			else {
				// print the list of items for the user to select from.
				for (int i = 0; i < listOfItems.size(); i++) {
					item = (Item) listOfItems.get(i);
					System.out.print("\t\t");

					System.out.println((i + 1) + ") ID: " + item.getId()
							+ " | Name: " + item.getName() + " | Price: $"
							+ item.getPrice());
				}
				System.out.println();
				System.out.print("\t\t");

				System.out.print("Select an item to delete : ");
				choice = Integer.parseInt(sc.nextLine());

				item = (Item) listOfItems.get(choice - 1);

				check = itemManager.removeMenuItem(item);

				if (check) {
					System.out.print("\t\t");
					System.out.format("%-25s:", "TASK STATUS");
					System.out.println("Deleted item successfully!");
				}

				else {
					System.out.print("\t\t");
					System.out.format("%-25s:", "TASK STATUS");
					System.out.println("Fail to delete item!");
				}
			}
		}

		catch (Exception e) {
			System.out.println("Invalid Input!");
		}
		System.out.print("\t\t");
		System.out.println("************End of Removing************");
	}
}
