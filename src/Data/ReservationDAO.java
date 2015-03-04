package Data;

import java.util.List;
import java.util.ArrayList;

/**
 * This class implements the Reservation Data Access Object with the attributes fileName, fileNameForRunningID.
 * 
 * This class is solely responsible for Create/Retrieve/Update/Delete functions from our flat file(Text File).
 * 
 * This class is using Serialization to write Reservation objects into a text file.
 * 
 * @author Low See Rong
 * @version 1.0
 * @since 24th October 2014
 */
public class ReservationDAO 
{
	/**
	 * The file name where the Reservation object is stored.
	 */
	private String fileName;
	
	/**
	 * The file name for keeping track of the Reservation unique running id.
	 */
	private String fileNameForRunningID;
	
	/**
	 * Default constructor.
	 * 
	 *	fileName = "Reservation.txt";
	 *	fileNameForRunningID = "ReservationRunningID.txt";
	 */
	public ReservationDAO()
	{
		fileName = "Reservation.txt";
		fileNameForRunningID = "ReservationRunningID.txt";
	}
	
	/**
	 * This function retrieves all the reservations.
	 * @return A list of reservations.
	 */
	public List retrieveAll()
	{
		List listOfReservations = null;
		try 
		{

			listOfReservations =  SerializeDB.readSerializedObject(fileName);
		}


		catch (Exception e) 
		{
			System.out.println("Exception >> " + e.getMessage());
		}
		return listOfReservations;

	}

	/**
	 * This function get the current running ID for Reservation and increment it to set it as the reservation's id that is to be inserted.
	 * The reservation is serialized and written back into the text file.
	 * @param reservation - An reservation to be inserted into the text file.
	 * @return Return TRUE if the insertion is successful. Return FALSE if the insertion fails.
	 */
	public boolean insert(Reservation reservation) {
		List listOfReservation;
		try {
			int runningID = SerializeDB.incrementAndGetRunningID(fileNameForRunningID);
			reservation.setId(runningID);
			
			listOfReservation = SerializeDB.readSerializedObject(fileName);
			listOfReservation.add(reservation);
			SerializeDB.writeSerializedObject(fileName, listOfReservation);
			return true;
		} catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
			return false;
		}

	}

	/**
	 * This function deletes a existing reservation in the text file. 
	 * @param reservation - An reservation to be deleted.
	 * @return Return TRUE if the delete is successful. Return FALSE if the delete fails.
	 */
	public boolean delete(Reservation reservation) {
		List listOfReservation;
		boolean bool = false;
		try {
			listOfReservation = SerializeDB.readSerializedObject(fileName);
			
			for(int i = 0; i < listOfReservation.size(); i++)
			{
				Reservation tempReservation = (Reservation)listOfReservation.get(i);
				
				if(tempReservation.getId() == reservation.getId())
				{
					listOfReservation.remove(i);
					bool = true;
					break;
				}
			}

			SerializeDB.writeSerializedObject(fileName, listOfReservation);
		} catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
		}
		return bool;
	}

}