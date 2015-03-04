package Data;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * This class implements the Ordered Package entity with the attributes id, packaging, price, order.
 * 
 * This class represents an Ordered Package.
 * 
 * This entity class is Serializable.
 * Allowing this entity to be written as an object into a text file.
 * 
 * @author Seshadri Madhavan
 * @version 1.0
 * @since 24th October 2014
 */
public class OrderedPackage implements Serializable
{
	/**
	 * The serialized class ID.
	 */
	private static final long serialVersionUID = 3473536159106241773L;

	/**
	 * The identity of this ordered package.
	 */
	private int id;
	
	/**
	 * The package that has been ordered.
	 */
	private PromotionalPackage packaging;
	
	/**
	 * The package price that has been ordered at that point in time.
	 */
	private double price;
	
	/**
	 * The order this ordered package belongs to.
	 */
	private Order order;
	
	/**
	 * Default constructor.
	 * 
	 * Initialize attributes to default values.
	 * id = 0;
	 *	packaging = null;
	 *	price = 0.0;
	 *	order = null;
	 */
	public OrderedPackage()
	{
		id = 0;
		packaging = null;
		price = 0.0;
		order = null;
	}

	/**
	 * Return ordered package identity.
	 * @return id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Initialize ordered package identity.
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Return the package that has been ordered.
	 * @return item
	 */
	public PromotionalPackage getPackage() {
		return packaging;
	}

	/**
	 * Initialize the package that has been ordered.
	 * @param item
	 */
	public void setPackage(PromotionalPackage packaging) {
		this.packaging = packaging;
	}

	/**
	 * Return the price of the package that has been ordered at that point in time.
	 * @return price
	 */
	public double getPrice() {
		return this.price;
	}

	/**
	 * Initialize the price of the package that has been ordered at that point in time.
	 * @param price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Return the order this ordered package belongs to.
	 * @return order
	 */
	public Order getOrder() {
		return this.order;
	}

	/**
	 * Initialize the order this ordered package belongs to.
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
		out.writeObject(packaging);
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
		packaging = (PromotionalPackage) in.readObject();
		order = (Order) in.readObject();
	}
}