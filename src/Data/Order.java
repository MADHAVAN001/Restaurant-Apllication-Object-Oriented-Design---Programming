package Data;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class implements the Order entity with the attributes id, table, timeStamp, staff, serviceCharge, gst, closed, listOfOrderedItems, listOfOrderedPacakages, membershipDiscount.
 * 
 * This class represents an Order.
 * 
 * This entity class is Serializable.
 * Allowing this entity to be written as an object into a text file.
 * 
 * @author Bansal Ankur
 * @version 1.0
 * @since 24th October 2014
 */

public class Order implements Serializable 
{
	/**
	 * The serialized class ID.
	 */
	private static final long serialVersionUID = 1627865406538520143L;

	/**
	 * The identity of this order.
	 */
	private int id;
	
	/**
	 * The table this order belongs to.
	 */
	private Table table;
	
	/**
	 * The date and time when the order is closed.
	 */
	private Date timeStamp;
	
	/**
	 * The staff that created this order.
	 */
	private Staff staff;
	
	/**
	 * The service charge that is applied to this order.
	 */
	private double serviceCharge;
	
	/**
	 * The gst charge that is applied to this order.
	 */
	private double gst;
	
	/**
	 * The boolean flag to indicate if this order is closed or open.
	 * True means this order is closed. False means this order is open.
	 */
	private boolean closed;
	
	/**
	 * This contains a list of Ordered Items.
	 */
	private List listOfOrderedItems;
	
	/**
	 * This contains a list of Ordered Promotional Packages.
	 */
	private List listOfOrderedPackages;
	
	/**
	 * The membership discount is applied to this order. 
	 */
	private Membership membershipDiscount;

	/**
	 * Default constructor.
	 * 
	 * Initialize attributes to default values.
	 * id = 0;
	 *	table = null;
	 *	timeStamp = null;
	 * 	staff = null;
	 *	serviceCharge = 0;
	 *	gst = 0;
	 *	closed = false;
	 *	listOfOrderedItems = new ArrayList<OrderedItem>();
	 *	listOfOrderedPackages = new ArrayList<OrderedPackage>();
	 *	membershipDiscount = null;
	 */
	public Order()
	{
		id = 0;
		table = null;
		timeStamp = null;
		staff = null;
		serviceCharge = 0;
		gst = 0;
		closed = false;
		listOfOrderedItems = new ArrayList<OrderedItem>();
		listOfOrderedPackages = new ArrayList<OrderedPackage>();
		membershipDiscount = null;
	}
	
	/**
	 * Return order identity.
	 * @return id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Initialize order identity.
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Return the table this order is attached to.
	 * @return table
	 */
	public Table getTable() {
		return this.table;
	}

	/**
	 * Initialize the table this order is attached to.
	 * @param table
	 */
	public void setTable(Table table) {
		this.table = table;
	}

	/**
	 * Return the date and time this order is closed to.
	 * @return timeStamp
	 */
	public Date getTimeStamp() 
	{
		return timeStamp;
	}

	/**
	 * Initialize the date and time this order is closed to.
	 * @param timeStamp
	 */
	public void setTimeStamp(Date timeStamp) 
	{
		this.timeStamp = timeStamp;
	}

	/**
	 * Return the staff that created this order.
	 * @return staff
	 */
	public Staff getStaff() {
		return this.staff;
	}

	/**
	 * Initialize the staff that created this order.
	 * @param staff
	 */
	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	/**
	 * Return the service charge that applieds to this order.
	 * @return serviceCharge
	 */
	public double getServiceCharge() {
		return this.serviceCharge;
	}

	/**
	 * Initialize the service charge that applies to this order.
	 * @param serviceCharge
	 */
	public void setServiceCharge(double serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	/**
	 * Return the GST charge that applies to this order.
	 * @return gst
	 */
	public double getGST() 
	{
		return this.gst;
	}

	/**
	 * Initialize the GST charge that applies to this order.
	 * @param GST
	 */
	public void setGST(double GST) {
		// TODO - implement Order.setGST
		this.gst = GST;
	}

	/**
	 * Return the boolean flag that indicate this order as closed or open.
	 * @return  True means it is closed. False means it is still open.
	 */
	public boolean getClosed() 
	{
		return this.closed;
	}

	/**
	 * Initialize the boolean flag that indicate this order as closed or open.
	 * @param  True means it is closed. False means it is still open.
	 */
	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	/**
	 * Return the list of ordered items that belongs to this order.
	 * @return A list of ordered items.
	 */
	public List getListOfOrderedItems() {
		return this.listOfOrderedItems;
	}

	/**
	 * Initialize the list of ordered items that belongs to this order.
	 * @param listOfOrderedItems
	 */
	public void setListOfOrderedItems(List listOfOrderedItems) {
		this.listOfOrderedItems = listOfOrderedItems;
	}

	/**
	 * Return the list of ordered promotional packages that belongs to this order.
	 * @return A list of ordered promotional packages.
	 */
	public List getListOfOrderedPackages() {
		return this.listOfOrderedPackages;
	}

	/**
	 * Initialize the list of ordered promotional packages that belongs to this order.
	 * @param A list of ordered promotional packages.
	 */
	public void setListOfOrderedPackages(List listOfOrderedPackages) {
		this.listOfOrderedPackages = listOfOrderedPackages;
	}

	/**
	 * Add an ordered item into the list of ordered items that belongs to this order.
	 * @param orderedItem
	 */
	public void addOrderedItem(OrderedItem orderedItem) {
		// TODO - implement Order.addOrderedItem
		listOfOrderedItems.add(orderedItem);
		orderedItem.setOrder(this);
	}

	/**
	 * Remove an ordered item from the list of ordered items that belongs to this order.
	 * @param orderedItem
	 */
	public void removeOrderedItem(OrderedItem orderedItem) {
		// TODO - implement Order.removeOrderedItem
		if(listOfOrderedItems.contains(orderedItem))
		{
			listOfOrderedItems.remove(orderedItem);
			orderedItem.setOrder(null);
		}
	}

	/**
	 * Add an ordered promotional package into the list of ordered items that belongs to this order.
	 * @param orderedPackage
	 */
	public void addOrderedPackage(OrderedPackage orderedPackage) {
		// TODO - implement Order.addOrderedPackage
		listOfOrderedPackages.add(orderedPackage);
		orderedPackage.setOrder(this);
	}

	/**
	 * Remove an ordered promotional package from the list of ordered items that belongs to this order.
	 * @param orderedPackage
	 */
	public void removeOrderedPackage(OrderedPackage orderedPackage) {
		// TODO - implement Order.removeOrderedPackage
		if(listOfOrderedPackages.contains(orderedPackage))
		{
			listOfOrderedPackages.remove(orderedPackage);
			orderedPackage.setOrder(null);
		}
	}

	/**
	 * Return the membership discount that is applied to this order.
	 * @return membershipDiscount
	 */
	public Membership getMembershipDiscount() 
	{
		return this.membershipDiscount;
	}

	/**
	 * Initialize the membership discount that is applied to this order.
	 * @param membershipDiscount
	 */
	public void setMembershipDiscount(Membership membershipDiscount) {
		this.membershipDiscount = membershipDiscount;
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
		out.writeObject(table);
		out.writeObject(timeStamp);
		out.writeObject(staff);
		out.writeObject(listOfOrderedItems);
		out.writeObject(listOfOrderedPackages);
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
		table = (Table) in.readObject();
		timeStamp = (Date) in.readObject();
		staff = (Staff) in.readObject();
		listOfOrderedItems = (List) in.readObject();
		listOfOrderedPackages = (List) in.readObject();
	}
}