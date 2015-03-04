package Data;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * This class implements the Package Item entity with the attributes id, item, packaging.
 * 
 * This class represents an Package Item.
 * 
 * This entity class is Serializable.
 * Allowing this entity to be written as an object into a text file.
 * 
 * @author Pathangi Janardhanan Jatin Shravan
 * @version 1.0
 * @since 24th October 2014
 */
public class PackageItem implements Serializable
{
	/**
	 * The serialized class ID.
	 */
	private static final long serialVersionUID = -4331095005444426554L;

	/**
	 * The identity of this package item.
	 */
	private int id;
	
	/**
	 * The item that is added to the promotional package.
	 */
	private Item item;
	
	/**
	 * The promotional package this package item belongs to.
	 */
	private PromotionalPackage packaging;

	/**
	 * Default constructor.
	 * 
	 * Initialize attributes to default values.
	 * id = 0;
	 *	item = null;
	 *	packaging = null;
	 */
	public PackageItem()
	{
		id = 0;
		item = null;
		packaging = null;
	}

	/**
	 * Return package item identity.
	 * @return id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Initialize package item identity.
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Return the item that has been added to the package.
	 * @return item
	 */
	public Item getItem() {
		return this.item;
	}

	/**
	 * Initialize the item that has been added to the package.
	 * @param item
	 */
	public void setItem(Item item) {
		this.item = item;
	}

	/**
	 * Return the promotional package this package item belongs to.
	 * @return packaging
	 */
	public PromotionalPackage getPackage() {
		return this.packaging;
	}

	/**
	 * Initialize the promotional package this package item belongs to.
	 * @param packaging
	 */
	public void setPackage(PromotionalPackage packaging) 
	{
		this.packaging = packaging;
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
		out.writeObject(item);
		out.writeObject(packaging);
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
		item = (Item) in.readObject();
		packaging = (PromotionalPackage) in.readObject();
	}
}