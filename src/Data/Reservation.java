package Data;

import java.util.Date;
import java.util.List;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * This class implements the Reservation entity with the attributes id, startDateTime, table, numberOfPax, customer.
 * 
 * This class represents an Reservation.
 * 
 * This entity class is Serializable.
 * Allowing this entity to be written as an object into a text file.
 * 
 * @author Low See Rong
 * @version 1.0
 * @since 24th October 2014
 */
public class Reservation implements Serializable
{
	/**
	 * The serialized class ID.
	 */
	private static final long serialVersionUID = -6011656161692115366L;

	/**
	 * The identity of this Reservation.
	 */
	private int id;

	/**
	 * The date and time of this reservation.
	 */
	private Date startDateTime;

	/**
	 * The table this reservation is booked for.
	 */
	private Table table;

	/**
	 * The number of expected customer for this reservation.
	 */
	private int numberOfPax;

	/**
	 * The customer who made this reservation.
	 */
	private Customer customer;

	/**
	 * Default constructor.
	 * 
	 * Initialize attributes to default values.
	 * id = 0;
	 *	startDateTime = null;
	 *	table = null;
	 *	numberOfPax = 0;
	 *	customer = null;
	 */
	public Reservation()
	{
		id = 0;
		startDateTime = null;
		table = null;
		numberOfPax = 0;
		customer = null;
	}

	/**
	 * Return reservation identity.
	 * @return id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Initialize reservation identity.
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	
	/**
	 * Return date and time for this reservation.
	 * @return startDateTime
	 */
	public Date getStartDateTime() {
		return this.startDateTime;
	}

	/**
	 * Initialize date and time for this reservation.
	 * @param startDateTime
	 */
	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}

	/**
	 * Return table reserved for this reservation.
	 * @return table
	 */
	public Table getTable() {
		return this.table;
	}

	/**
	 * Initialize table reserved for this reservation.
	 * @param table
	 */
	public void setTable(Table table) {
		this.table = table;
	}

	/**
	 * Return the number of expected customer for this reservation.
	 * @return numberOfPax
	 */
	public int getNumberOfPax() {
		return this.numberOfPax;
	}

	/**
	 * Initialize the number of expected customer for this reservation.
	 * @param numberOfPax
	 */
	public void setNumberOfPax(int numberOfPax) {
		this.numberOfPax = numberOfPax;
	}

	/**
	 * Return the customer who made this reservation.
	 * @return customer
	 */
	public Customer getCustomer() {
		return this.customer;
	}

	/**
	 * Initialize the customer who made this reservation.
	 * @param customer
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
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
		out.writeObject(startDateTime);
		out.writeObject(table);
		out.writeObject(customer);
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
		startDateTime = (Date) in.readObject();
		table = (Table) in.readObject();
		customer = (Customer) in.readObject();
	}

}