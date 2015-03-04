package Data;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class implements the Promotional Package entity with the attributes id, name, price, removed, listOfPackageItem.
 * 
 * This class represents an Promotional Package.
 * 
 * This entity class is Serializable.
 * Allowing this entity to be written as an object into a text file.
 * 
 * @author Adrian Wang Wenjie
 * @version 1.0
 * @since 24th October 2014
 */
public class PromotionalPackage implements Serializable
{
	/**
	 * The serialized class ID.
	 */
	private static final long serialVersionUID = -6757682743282457634L;

	/**
	 * The identity of this promotional package.
	 */
	private int id;

	/**
	 * The name of this promotional package.
	 */
	private String name;

	/**
	 * The price of this promotional package.
	 */
	private double price;

	/**
	 * The boolean flag of this promotional package to indicate if it is removed.
	 */
	private boolean removed;

	/**
	 * The list of items that belongs to this promotional package.
	 */
	private List listOfPackageItem;

	/**
	 * Default constructor.
	 * 
	 * Initialize attributes to default values.
	 *	id = 0;
	 *	name = "";
	 *	price = 0.0;
	 *	removed = false;
	 *	listOfPackageItem = new ArrayList<PackageItem>();
	 */
	public PromotionalPackage()
	{
		id = 0;
		name = "";
		price = 0.0;
		removed = false;
		listOfPackageItem = new ArrayList<PackageItem>();
	}

	/**
	 * Return promotional package identity.
	 * @return id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Initialize promotional package identity.
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Return promotional package name.
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Initialize promotional package name.
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Return promotional package price.
	 * @return price
	 */
	public double getPrice() {
		return this.price;
	}

	/**
	 * Initialize promotional package price.
	 * @param price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Return promotional package removed flag.
	 * @return True means it is removed. False means it is still available.
	 */
	public boolean getRemoved() {
		return this.removed;
	}

	/**
	 * Initialize promotional package removed flag.
	 * @param True means it is removed. False means it is still available.
	 */
	public void setRemoved(boolean removed) {
		this.removed = removed;
	}

	/**
	 * Return a list of package items that belongs to this promotional package.
	 * @param listOfPackageItem
	 */
	public void setListOfPackageItems(List listOfPackageItem) {
		this.listOfPackageItem = listOfPackageItem;
	}

	/**
	 * Initialize a list of package items that belongs to this promotional package.
	 * @return listOfPackageItem
	 */
	public List getListOfPackageItems() {
		return this.listOfPackageItem;
	}

	/**
	 * This function add a package item into the listOfPackageItem and set the package item to belong to this promotional package.
	 * @param packageItem - A package item to be added.
	 */
	public void addPackageItem(PackageItem packageItem)
	{
		//TODO implement add package item

		listOfPackageItem.add(packageItem);
		packageItem.setPackage(this);
	}

	/**
	 * This function removes a package item from the listOfPackageItem and set the package item to not belong to this promotional package.
	 * @param packageItem - A package item to be added.
	 */
	public void removePackageItem(PackageItem packageItem)
	{
		//TODO implement remove package item

		if(listOfPackageItem.contains(packageItem))
		{
			listOfPackageItem.remove(packageItem);
			packageItem.setPackage(null);
		}
	}

	/**
	 * Implements Serializable interface, prepares this entity object to be transmitted  
	 * @param out
	 * @throws IOException
	 */
	private void writeObject(ObjectOutputStream out) throws IOException 
	{
		// write 'this' to 'out'...
		out.defaultWriteObject();
		out.writeObject(listOfPackageItem);
	}

	/**
	 * Implements Serializable interface, allows this entity object to be read  
	 * @param out
	 * @throws IOException
	 */
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException 
	{
		// populate the fields of 'this' from the data in 'in'...
		in.defaultReadObject();
		listOfPackageItem = (List)in.readObject();
	}

}