package Data;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * This class implements the Ordered Item entity with the attributes id, item, price, order.
 * 
 * This class represents an Ordered Item.
 * 
 * This entity class is Serializable.
 * Allowing this entity to be written as an object into a text file.
 * 
 * @author Seshadri Madhavan
 * @version 1.0
 * @since 24th October 2014
 */

public class OrderedItem implements Serializable 
{
	/**
	 * The serialized class ID.
	 */
	private static final long serialVersionUID = -4029587975872122288L;

	/**
	 * The identity of this ordered item.
	 */
	private int id;
	
	/**
	 * The item that has been ordered.
	 */
	private Item item;
	
	/**
	 * The item price that has been ordered at that point in time.
	 */
	private double price;
	
	/**
	 * The order this ordered item belongs to.
	 */
	private Order order;
	
	/**
	 * Default constructor.
	 * 
	 * Initialize attributes to default values.
	 * id = 0;
	 *	item = null;
	 *	price = 0.0;
	 *	order = null;
	 */
	public OrderedItem()
	{
		id = 0;
		item = null;
		price = 0.0;
		order = null;
	}

	/**
	 * Return ordered item identity.
	 * @return id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Initialize ordered item identity.
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Return the item that has been ordered.
	 * @return item
	 */
	public Item getItem() {
		return this.item;
	}

	/**
	 * Initialize the item that has been ordered.
	 * @param item
	 */
	public void setItem(Item item) {
		this.item = item;
	}

	/**
	 * Return the price of the item that has been ordered at that point in time.
	 * @return price
	 */
	public double getPrice() {
		return this.price;
	}

	/**
	 * Initialize the price of the item that has been ordered at that point in time.
	 * @param price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Return the order this ordered item belongs to.
	 * @return order
	 */
	public Order getOrder() {
		return this.order;
	}

	/**
	 * Initialize the order this ordered item belongs to.
	 * @param order
	 */
	public void setOrder(Order order) {
		this.order = order;
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
		out.writeObject(order);
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
		order = (Order) in.readObject();
		
	}
}