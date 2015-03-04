package Business;

import java.util.List;

import Data.*;

/**
 * This class implements the Package Item Manager controller
 * @author Pathangi Janardhanan Jatin Shravan
 *	@version 1.0
 * @since 7th November 2014
 */
public class PackageItemManager 
{
	/**
	 * This function creates the package item.
	 * @param packageItem - A package item to be created.
	 * @return TRUE if the creation is successful. Return FALSE if the creation fails.
	 */
	public boolean createPackageItem(PackageItem packageItem)
	{
		// TODO - implement PackageItemManager.createPackageItem
		boolean check = false;
		
		PackageItemDAO packageitemDAO = new PackageItemDAO();
		
		check = packageitemDAO.insert(packageItem);
		
		return check;
	}

	/**
	 * This function retrieves all the package items that belongs to the package ID.
	 * @param id - A package ID.
	 * @return A list of Package Items.
	 */
	public List retrievePackageItemsByPackageID(int id) {
		// TODO - implement PackageItemManager.retrievePackageItemsByPackageID
		PackageItemDAO packageItemDAO = new PackageItemDAO();
		
		List listOfPackageItems = packageItemDAO.retrieveAllByPackageID(id);
		
		return listOfPackageItems;
	}

}