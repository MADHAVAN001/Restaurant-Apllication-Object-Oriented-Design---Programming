package Data;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * This class implements the Membership entity with the attributes id, name, discount.
 * 
 * This class represents a membership.
 * 
 * This entity class is Serializable.
 * Allowing this entity to be written as an object into a text file.
 * 
 * @author Bansal Ankur
 * @version 1.0
 * @since 24th October 2014
 */
public class Membership implements Serializable
{
	/**
	 * The serialized class ID.
	 */
	private static final long serialVersionUID = 8425265454065487289L;

	/**
	 * The identity of this membership.
	 */
	private int id;
	
	/**
	 * The name of this membership.
	 */
	private String name;
	
	/**
	 * The amount of discount this membership is entitled to as a fraction of 100.
	 */
	private double discount;
	
	/**
	 * Default constructor.
	 * 
	 * Initialize attributes to default values.
	 * id = 0;
	 *	name = "";
	 *	discount = 0.0;
	 */
	public Membership()
	{
		id = 0;
		name = "";
		discount = 0.0;
	}

	/**
	 * Return membership identity.
	 * @return id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Initialize membership identity.
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Return membership name
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Initialize membership name.
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Return membership discount rate as fraction of 100.
	 * @return discount
	 */
	public double getDiscount() {
		return this.discount;
	}
	
	/**
	 * Initialize membership discount rate as fraction of 100.
	 * @param discount
	 */
	public void setDiscount(double discount) {
		this.discount = discount;
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