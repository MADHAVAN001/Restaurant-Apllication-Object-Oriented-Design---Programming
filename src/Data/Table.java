package Data;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * This class implements the Table entity with the attributes id, capacity, reservation, order.
 * 
 * This class represents an Table.
 * 
 * This entity class is Serializable.
 * Allowing this entity to be written as an object into a text file.
 * 
 * @author Adrian Wang Wenjie
 * @version 1.0
 * @since 24th October 2014
 */
public class Table implements Serializable
{
	/**
	 * The serialized class ID.
	 */
	private static final long serialVersionUID = -6460485097619347854L;

	/**
	 * The identity of this table.
	 */
	private int id;

	/**
	 * The number of customers this table can be accommodate.
	 */
	private int capacity;

	/**
	 * The reservation made for this table.
	 */
	private Reservation reservation;

	/**
	 * The order made for this table.
	 */
	private Order order;

	/**
	 * Default constructor.
	 * 
	 * Initialize attributes to default values.
	 * id = 0;
	 *	capacity = 0;
	 *	reservation = null;
	 *	order = null;
	 */
	public Table()
	{
		id = 0;
		capacity = 0;
		reservation = null;
		order = null;
	}

	/**
	 * Return identity of this table.
	 * @return id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Initialize the identity of this table.
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Return the number of customers this table can accommodate.
	 * @return capacity
	 */
	public int getCapacity() {
		return this.capacity;
	}

	/**
	 * Initialize the number of customers this table can accommodate.
	 * @param capacity
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * Return the reservation made for this table.
	 * @return reservation
	 */
	public Reservation getReservation() {
		return this.reservation;
	}
	
	/**
	 * Initialize the reservation made for this table.
	 * @param reservation
	 */
	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	/**
	 * Return the order made for this table.
	 * @return order
	 */
	public Order getOrder()
	{
		return this.order;
	}

	/**
	 * Initialize the order made for this table.
	 * @param order
	 */
	public void setOrder(Order order)
	{
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
		out.writeObject(reservation);
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
		reservation = (Reservation) in.readObject();
		order = (Order)in.readObject();
	}

}