package Data;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * This class implements the Staff entity with the attributes id, name, gender, jobTitle.
 * 
 * This class represents an Staff.
 * 
 * This entity class is Serializable.
 * Allowing this entity to be written as an object into a text file.
 * 
 * @author Pathangi Janardhanan Jatin Shravan
 * @version 1.0
 * @since 24th October 2014
 */
public class Staff implements Serializable
{
	/**
	 * The serialized class ID.
	 */
	private static final long serialVersionUID = -5319578840230468553L;

	/**
	 * The identity of this staff.
	 */
	private int id;

	/**
	 * The name of this staff.
	 */
	private String name;

	/**
	 * The gender of this staff.
	 */
	private String gender;

	/**
	 * The job title of this staff.
	 */
	private String jobTitle;

	/**
	 * Default constructor.
	 * 
	 * Initialize attributes to default values.
	 * id = 0;
	 *	name = "";
	 *	gender = "";
	 *	jobTitle = "";
	 */
	public Staff()
	{
		id = 0;
		name = "";
		gender = "";
		jobTitle = "";
	}

	/**
	 * Return the identity of this staff. 
	 * @return id
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * Initialize the identity of this staff.
	 * @param id
	 */
	public void setId(int id)
	{
		this.id = id;
	}

	/**
	 * Return the name of this staff.
	 * @return name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Initialize the name of this staff.
	 * @param name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Return the gender of this staff.
	 * @return gender
	 */
	public String getGender()
	{
		return gender;
	}

	/**
	 * Initialize the gender of this staff.
	 * @param gender
	 */
	public void setGender(String gender)
	{
		this.gender = gender;
	}

	/**
	 * Return the job title of this staff.
	 * @return jobTitle
	 */
	public String getJobTitle()
	{
		return jobTitle;
	}

	/**
	 * Initialize the job title of this staff.
	 * @param JobTitle
	 */
	public void setJobTitle(String JobTitle)
	{
		this.jobTitle = JobTitle;
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