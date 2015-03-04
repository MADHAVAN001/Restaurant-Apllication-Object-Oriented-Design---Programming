package Business;

import java.util.List;

import Data.*;

/**
 * This class implements the Promotional Package Manager controller
 * 
 * @author Adrian Wang Wenjie
 * @version 1.0
 * @since 6th November 2014
 */
public class PackageManager {

	/**
	 * This function retrieve all the promotional packages that is not logically
	 * removed.
	 * 
	 * @return A list of promotional packages.
	 */
	public List onStartUp() {
		PromotionalPackageDAO promotionalPackageDAO = new PromotionalPackageDAO();

		List listOfPromotionalPackage = promotionalPackageDAO.retrieveAll();

		for (int i = 0; i < listOfPromotionalPackage.size(); i++) {
			PromotionalPackage promotionalPackage = (PromotionalPackage) listOfPromotionalPackage
					.get(i);

			if (promotionalPackage.getRemoved() == true) {
				listOfPromotionalPackage.remove(i);
				i = i - 1;
			}
		}
		return listOfPromotionalPackage;
	}

	/**
	 * This function verifies the input before creating the promotional package.
	 * 
	 * @param packaging
	 *            - A promotional package filled with user inputs.
	 * @return TRUE if the creation is successful. Return FALSE if the creation
	 *         fails.
	 */
	public boolean createPackage(PromotionalPackage packaging) {
		boolean check = false;
		PromotionalPackageDAO promotionalPackageDAO = new PromotionalPackageDAO();

		if (packaging.getListOfPackageItems().size() <= 0) {
			System.out.print("\t\t");
			System.out.format("%-25s:", "TASK STATUS");
			System.out
					.println("Promotional package must contain at least one item!");
			return check;
		}

		if (packaging.getName().isEmpty()) {
			System.out.print("\t\t");
			System.out.format("%-25s:", "TASK STATUS");
			System.out.println("Promotional package name cannot be empty!");
			return check;
		}

		if (packaging.getPrice() <= 0) {
			System.out.print("\t\t");
			System.out.format("%-25s:", "TASK STATUS");
			System.out
					.println("Promotional package price must be a positive number!");
			return check;
		}

		check = promotionalPackageDAO.insert(packaging);

		return check;
	}

	/**
	 * This function verifies the input before updating the promotional package.
	 * 
	 * @param packaging
	 *            - A promotional package with updated values.
	 * @return TRUE if the update is successful. Return FALSE if the update
	 *         fails.
	 */
	public boolean updatePackage(PromotionalPackage packaging) {
		PromotionalPackageDAO promotionalPackageDAO = new PromotionalPackageDAO();
		boolean check = false;

		if (packaging.getPrice() <= 0) {
			System.out.print("\t\t");
			System.out.format("%-25s:", "TASK STATUS");
			System.out.println("Item price must be a positive number!");
			return check;
		}

		check = promotionalPackageDAO.update(packaging);

		return check;

	}

	/**
	 * This function removes an promotional package.
	 * 
	 * @param packaging
	 *            - A promotional package to be deleted.
	 * @return TRUE if the deletion is successful. Return FALSE if the deletion
	 *         fails.
	 */
	public boolean removePackage(PromotionalPackage packaging) {
		PromotionalPackageDAO promotionalPackageDAO = new PromotionalPackageDAO();
		boolean check = false;

		check = promotionalPackageDAO.delete(packaging);

		return check;
	}
}