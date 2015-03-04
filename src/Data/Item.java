package Data;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * This class implements the Item entity with the attributes id, name, type, price, description, removed.
 * 
 * This class represents an Item.
 * 
 * This entity class is Serializable.
 * Allowing this entity to be written as an object into a text file.
 * 
 * @author Seshadri Madhavan
 * @version 1.0
 * @since 24th October 2014
 */
public class Item implements Serializable
{
	/**
	 * The serialized class ID.
	 */
	private static final long serialVersionUID = 6610548520913981104L;

	/**
	 * The identity of this item.
	 */
	private int id;
	
	/**
	 * The name of this item.
	 */
	private String name;
	
	/**
	 * The type of this item.
	 */
	private String type;
	
	/**
	 * The price of this item.
	 */
	private double price;
	
	/**
	 * The description of this item.
	 */
	private String description;
	
	/**
	 * The boolean flag of this item.
	 * True means it is removed. False means it is still available.
	 */
	private boolean removed;
	
	/**
	 * Default constructor.
	 * 
	 * Initialize attributes to default values.
	 * id = 0;
	 *	name = "";
	 *	type = "";
	 * 	price = 0.0;
	 *	description = "";
	 *	removed = false;
	 */
	public Item()
	{
		id = 0;
		name = "";
		type = "";
		price = 0.0;
		description = "";
		removed = false;
	}

	/**
	 * Return item identity.
	 * @return id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Initialize item identity.
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Return item name.
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Initialize item name.
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Return item type.
	 * @return type
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Initialize item type.
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Return item price.
	 * @return price
	 */
	public double getPrice() {
		return this.price;
	}

	/**
	 * Initialize item price.
	 * @param price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Return item description.
	 * @return description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Initialize item description.
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Return item removed flag.
	 * @return True means it is removed. False means it is still available.
	 */
	public boolean getRemoved() {
		return this.removed;
	}

	/**
	 * Initialize item removed flag.
	 * @param True means it is removed. False means it is still available.
	 */
	public void setRemoved(boolean removed) {
		this.removed = removed;
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
	}

}