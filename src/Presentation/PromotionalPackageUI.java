package Presentation;

import java.util.List;
import java.util.Scanner;

import Business.*;
import Data.*;

/**
 * This class implements the boundary Promotional Package UI to interact with
 * the user.
 * 
 * @author Adrian Wang Wenjie
 * @version 1.0
 * @since 6th November 2014
 */
public class PromotionalPackageUI {
	/**
	 * This function retrieves a list of items for the user to select from in
	 * order to add it into the promotional package. Then ask the user input for
	 * the promotional package name and price.
	 */
	public static void createPackage() {
		System.out.print("\t\t");
		System.out
				.println("************Creating a Promotional Package************");
		// TODO - implement RRPSS.createPackge
		try {
			Scanner sc = new Scanner(System.in);
			PackageManager packageManager = new PackageManager();
			PackageItemManager packageItemManager = new PackageItemManager();

			PackageItem packageItem = null;
			Item item = null;
			int choice = 0;
			int i = 0;
			boolean check = false;

			PromotionalPackage promotionalPackage = new PromotionalPackage();

			ItemManager itemManager = new ItemManager();
			List listOfItems = itemManager.onStartUp();

			if (listOfItems.size() == 0) {
				System.out.print("\t\t");
				System.out.format("%-25s:", "TASK STATUS");
				System.out
						.println("No Items to be added into the promotional package!");
				return;
			}

			do {
				System.out.println();
				try {
					for (i = 0; i < listOfItems.size(); i++) {
						System.out.print("\t\t");

						item = (Item) listOfItems.get(i);
						System.out.println((i + 1) + ") ID: " + item.getId()
								+ " | Name: " + item.getName() + " | Price: $"
								+ item.getPrice());
					}
					System.out.print("\t\t");
					System.out.println((i + 1) + ") Done");
					System.out.println();
					System.out.print("\t\t");
					System.out
							.print("Select an item to be added into promotional package: ");
					choice = Integer.parseInt(sc.nextLine());

					item = (Item) listOfItems.get(choice - 1);

					if (choice != (i + 1)) {
						packageItem = new PackageItem();
						packageItem.setItem(item);
						packageItem.setPackage(promotionalPackage);
						promotionalPackage.addPackageItem(packageItem);
						System.out.print("\t\t");
						System.out.format("%-25s:", "TASK STATUS");
						System.out.println("Item added!");
					}
				}

				catch (Exception e) {
					if (choice != (i + 1)) {
						System.out.print("\t\t");
						System.out.format("%-25s:", "TASK STATUS");
						System.out.println("Invalid Input!");
					}
				}

			} while (choice != (i + 1));
			System.out.print("\t\t");
			System.out.format("%-40s:", "Enter Promotional Package name");
			promotionalPackage.setName(sc.nextLine());
			System.out.print("\t\t");
			System.out.format("%-40s:$", "Enter Promotional Package price");
			promotionalPackage.setPrice(Double.parseDouble(sc.nextLine()));

			promotionalPackage.setRemoved(false);

			check = packageManager.createPackage(promotionalPackage);

			if (check) {
				System.out.print("\t\t");
				System.out.format("%-25s:", "FINAL TASK STATUS");
				System.out.println("Promotional package created successfully!");

				for (int j = 0; j < promotionalPackage.getListOfPackageItems()
						.size(); j++) {
					packageItem = (PackageItem) promotionalPackage
							.getListOfPackageItems().get(j);
					check = packageItemManager.createPackageItem(packageItem);

					if (check == false) {
						System.out.print("\t\t");
						System.out.format("%-25s:", "TASK STATUS");
						System.out.println("Fail to add package item : "
								+ packageItem.getItem().getName());
					}
				}
			}

			else {
				System.out.print("\t\t");
				System.out.format("%-25s:", "TASK STATUS");
				System.out.println("Failed to create promotional package!");
			}
		}

		catch (Exception e) {
			System.out.println("Invalid Input!");
		}
		System.out.print("\t\t");
		System.out
				.println("************End of Creation of Promotional Package************");
	}

	/**
	 * This function ask the user to select a promotional package from a list of
	 * promotional package he/she wishes to update. This function is only able
	 * to update the promotional package's price.
	 */
	public static void updatePackage() {
		System.out.print("\t\t");
		System.out
				.println("************Updating Promotional Package************");
		// TODO - implement RRPSS.updatePackage
		try {
			Scanner sc = new Scanner(System.in);

			PackageManager packageManager = new PackageManager();

			List listOfPromotionalPackages = packageManager.onStartUp();

			int choice = 0;
			PromotionalPackage promotionalPackage = null;
			boolean check = false;

			if (listOfPromotionalPackages.size() == 0) {
				System.out.print("\t\t");
				System.out.format("%-25s:", "TASK STATUS");
				System.out.println("No promotional packages to update!");
			}

			else {
				// print the list of promotional packages for the user to select
				// from.
				for (int i = 0; i < listOfPromotionalPackages.size(); i++) {
					promotionalPackage = (PromotionalPackage) listOfPromotionalPackages
							.get(i);
					System.out.print("\t\t");

					System.out.println((i + 1) + ") ID: "
							+ promotionalPackage.getId() + " | Name: "
							+ promotionalPackage.getName() + " | Price: $"
							+ promotionalPackage.getPrice());
				}
				System.out.println();
				System.out.print("\t\t");

				System.out
						.print("Select a promotional package to update the price: ");
				choice = Integer.parseInt(sc.nextLine());

				promotionalPackage = (PromotionalPackage) listOfPromotionalPackages
						.get(choice - 1);
				System.out.print("\t\t");
				System.out.format("%-25s:$", "Enter price");

				promotionalPackage.setPrice(Double.parseDouble(sc.nextLine()));

				check = packageManager.updatePackage(promotionalPackage);

				if (check) {
					System.out.print("\t\t");
					System.out.format("%-25s:", "TASK STATUS");
					System.out
							.println("Update promotional package successfully!");
				}

				else {
					System.out.print("\t\t");
					System.out.format("%-25s:", "TASK STATUS");
					System.out.println("Fail to update promotional package!");
				}
			}
		}

		catch (Exception e) {
			System.out.print("\t\t");
			System.out.format("%-25s:", "TASK STATUS");
			System.out.println("Invalid Input!");
		}
		System.out.print("\t\t");
		System.out
				.println("************End Updating Promotional Package************");
	}

	/**
	 * This function ask the user to select a promotional package from a list of
	 * promotional packages he/she wishes to delete.
	 */
	public static void removePackage() {
		System.out.print("\t\t");
		System.out
				.println("************Removing a Promotional Package************");
		// TODO - implement RRPSS.removePackage
		try {
			Scanner sc = new Scanner(System.in);

			PackageManager packageManager = new PackageManager();

			List listOfPromotionalPackages = packageManager.onStartUp();

			int choice = 0;
			PromotionalPackage promotionalPackage = null;
			boolean check = false;

			if (listOfPromotionalPackages.size() == 0) {
				System.out.print("\t\t");
				System.out.format("%-25s:", "TASK STATUS");
				System.out.println("No promotional packages to delete!");
			}

			else {
				// print the list of items for the user to select from.
				for (int i = 0; i < listOfPromotionalPackages.size(); i++) {
					promotionalPackage = (PromotionalPackage) listOfPromotionalPackages
							.get(i);
					System.out.print("\t\t");
					System.out.println((i + 1) + ") ID: "
							+ promotionalPackage.getId() + " | Name: "
							+ promotionalPackage.getName() + " | Price: $"
							+ promotionalPackage.getPrice());
				}
				System.out.print("\t\t");

				System.out.print("Select an item to delete : ");
				choice = Integer.parseInt(sc.nextLine());

				promotionalPackage = (PromotionalPackage) listOfPromotionalPackages
						.get(choice - 1);

				check = packageManager.removePackage(promotionalPackage);

				if (check) {
					System.out.print("\t\t");
					System.out.format("%-25s:", "TASK STATUS");
					System.out
							.println("Deleted promotional package successfully!");
				}

				else {
					System.out.print("\t\t");
					System.out.format("%-25s:", "TASK STATUS");
					System.out.println("Fail to delete promotional package!");
				}
			}
		}

		catch (Exception e) {
			System.out.print("\t\t");
			System.out.format("%-25s:", "TASK STATUS");
			System.out.println("Invalid Input!");
		}
		System.out.print("\t\t");
		System.out
				.println("************End Removing Promotional Package************");
	}
}
