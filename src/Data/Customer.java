package Data;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * This class implements the Customer entity with the attributes id, name, contactNumber, Reservation.
 * 
 * This class represents a customer.
 * Where a customer is allowed to make one reservation.
 * 
 * This entity class is Serializable.
 * Allowing this entity to be written as an object into a text file.
 * 
 * @author Low See Rong
 * @version 1.0
 * @since 24th October 2014
 */

public class Customer implements Serializable 
{
	/**
	 * The serialized class ID.
	 */
	private static final long serialVersionUID = -9090189154663096508L;

	/**
	 * The identity of this customer.
	 */
	private int id;
	
	/**
	 * The name of this customer.
	 */
	private String name;
	
	/**
	 * The contact number of this customer.
	 */
	private int contactNumber;
	
	/**
	 * The reservation this customer made.
	 */
	private Reservation reservation;
	
	/**
	 * Default constructor.
	 * 
	 * Initialize attributes to default values.
	 * id = 0, 
	 * name = "", 
	 * contactNumber = "", 
	 * reservation = null
	 */
	public Customer()
	{
		id = 0;
		name = "";
		contactNumber = 0;
		reservation = null;
	}

	/**
	 * Return customer identity.
	 * @return id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Initialize customer identity.
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Return customer name.
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Initialize customer name.
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Return customer contact number.
	 * @return contactNumber
	 */
	public int getContactNumber() {
		return this.contactNumber;
	}

	/**
	 * Initialize customer contact number.
	 * @param contactNumber
	 */
	public void setContactNumber(int contactNumber) {
		this.contactNumber = contactNumber;
	}

	/**
	 * Return customer reservation.
	 * @return reservation
	 */
	public Reservation getReservation() {
		return this.reservation;
	}

	/**
	 * Initialize customer reservation.
	 * @param reservation
	 */
	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
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
	}

}