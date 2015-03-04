package Business;

import java.util.List;

import Data.*;

/**
 * This class implements the Item Manager controller
 * 
 * @author Seshadri Madhavan
 * @version 1.0
 * @since 6th November 2014
 */
public class ItemManager {
	/**
	 * This function retrieve all the items that is not logically removed.
	 * 
	 * @return A list of items.
	 */
	public List onStartUp() {
		// TODO - implement ItemManager.onStartUp

		ItemDAO itemDAO = new ItemDAO();

		List listOfItems = itemDAO.retrieveAll();

		for (int i = 0; i < listOfItems.size(); i++) {
			Item tempItem = (Item) listOfItems.get(i);

			if (tempItem.getRemoved() == true) {
				listOfItems.remove(i);
				i = i - 1;
			}
		}

		return listOfItems;

	}

	/**
	 * This function verifies the input before creating the item.
	 * 
	 * @param item
	 *            - An item filled with user inputs.
	 * @return TRUE if the creation is successful. Return FALSE if the creation
	 *         fails.
	 */
	public boolean createMenuItem(Item item) {
		// TODO - implement ItemManager.createMenuItem
		ItemDAO itemDAO = new ItemDAO();
		boolean check = false;

		if (item.getName().isEmpty()) {
			System.out.print("\t\t");
			System.out.format("%-25s:", "TASK STATUS");
			System.out.println("Item name cannot be empty!");
			return check;
		}

		if (item.getType().isEmpty()) {
			System.out.print("\t\t");
			System.out.format("%-25s:", "TASK STATUS");
			System.out.println("Item type cannot be empty!");
			return check;
		}

		if (item.getPrice() <= 0) {
			System.out.print("\t\t");
			System.out.format("%-25s:", "TASK STATUS");
			System.out.println("Item price must be a positive number!");
			return check;
		}

		if (item.getDescription().isEmpty()) {
			System.out.print("\t\t");
			System.out.format("%-25s:", "TASK STATUS");
			System.out.println("Item description cannot be empty!");
			return check;
		}

		check = itemDAO.insert(item);

		return check;
	}

	/**
	 * This function verifies the input before updating the item.
	 * 
	 * @param item
	 *            - An item with updated values.
	 * @return TRUE if the update is successful. Return FALSE if the update
	 *         fails.
	 */
	public boolean updateMenuItem(Item item) {
		// TODO - implement ItemManager.updateMenuItem
		ItemDAO itemDAO = new ItemDAO();
		boolean check = false;

		if (item.getPrice() <= 0) {
			System.out.print("\t\t");
			System.out.format("%-25s:", "TASK STATUS");
			System.out.println("Item price must be a positive number!");
			return check;
		}

		check = itemDAO.update(item);

		return check;
	}

	/**
	 * This function removes an item.
	 * 
	 * @param item
	 *            - An item to be deleted.
	 * @return TRUE if the deletion is successful. Return FALSE if the deletion
	 *         fails.
	 */
	public boolean removeMenuItem(Item item) {
		// TODO - implement ItemManager.removeMenuItem
		ItemDAO itemDAO = new ItemDAO();
		boolean check = false;

		PackageManager packageManager = new PackageManager();
		List listOfPromotionalPackages = packageManager.onStartUp();
		PromotionalPackage promotionalPackage = null;

		PackageItemManager packageItemManager = new PackageItemManager();
		List listOfPackageItem = null;
		PackageItem packageItem = null;

		for (int i = 0; i < listOfPromotionalPackages.size(); i++) {
			promotionalPackage = (PromotionalPackage) listOfPromotionalPackages
					.get(i);

			listOfPackageItem = packageItemManager
					.retrievePackageItemsByPackageID(promotionalPackage.getId());

			for (int j = 0; j < listOfPackageItem.size(); j++) {
				packageItem = (PackageItem) listOfPackageItem.get(j);

				if (packageItem.getItem().getId() == item.getId()) {
					System.out.print("\t\t");
					System.out.format("%-25s:", "TASK STATUS");
					System.out
							.println("Unable to remove item as it belongs to package ID: "
									+ promotionalPackage.getId());
					check = false;
					return check;
				}
			}
		}

		check = itemDAO.delete(item);

		return check;
	}
}