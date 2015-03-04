package Data;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.ArrayList;

//
/**
 * This class implements the Serialization method.
 * 
 * This class serialize the entity to be written as an object into a text file.
 * 
 *  Note : When structure of the Object type (the class file) in the list changed the Serialized file may fail.
 * 
 * @author Low See Rong
 * @version 1.0
 * @since 24th October 2014
 */
public class SerializeDB
{
	/**
	 * This function reads from a text file where a list of serialized objects is stored.
	 * @param filename - A file name where the list of serialized objects is stored.
	 * @return A list of deserialize objects
	 */
	public static List readSerializedObject(String filename) {
		List pDetails = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(filename);
			in = new ObjectInputStream(fis);
			pDetails = (List) in.readObject();
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return pDetails;
	}

	/**
	 * This function writes a list of objects into a text file using serialization. 
	 * @param filename - A file name where the list of objects is to be stored.
	 * @param list - A list of objects to be written into the text file using serialization.
	 */
	public static void writeSerializedObject(String filename, List list) {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(list);
			out.close();
			//	System.out.println("Object Persisted");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * This function reads from a text file where an serialize unique ID is stored
	 * @param filename - A file name where the unique ID is stored.
	 * @return An ID number.
	 */
	public static int readSerializedRunningID(String filename) {
		int runningID = 0;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(filename);
			in = new ObjectInputStream(fis);
			runningID = (int)in.readObject();
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return runningID;
	}

	/**
	 * This function writes a unique ID into a text file using serialization.
	 * @param filename - A file name where the unique ID is to be stored.
	 * @param runningID - An ID number.
	 */
	public static void writeSerializedRunningID(String filename, int runningID)
	{
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(runningID);
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * This function retrieves and increment the serialized unique ID number from a text file and writes it back into the file.
	 * @param filename - A file name where the unique ID is stored.
	 * @return An ID number.
	 */
	public static int incrementAndGetRunningID(String filename)
	{
		int runningID = readSerializedRunningID(filename);
		runningID++;
		writeSerializedRunningID(filename, runningID);

		return runningID;
	}
}