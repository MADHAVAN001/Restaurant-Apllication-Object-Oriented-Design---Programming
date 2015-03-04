package Presentation;

import java.util.List;
import java.util.Scanner;

import Business.*;
import Data.*;
/**
 * This class implements the boundary Table UI to interact with the user.
 * @author Adrian Wang Wenjie
 * @version 1.0
 * @since 6th November 2014
 */
public class TableUI 
{
	/**
	 * This function displays a list of tables status, "Occupied" means there is an order for the table and "Reserved" means that there is a reservation made for that table.
	 */
	public static void checkTableAvailability() {
		System.out.print("\t\t");
		System.out
				.println("************Checking Table Availability************");
		// TODO - implement RRPSS.checkTableAvailability
		ReservationManager reservationManager = new ReservationManager();
		List listOfReservations = reservationManager.onStartUp();

		Reservation reservation = null;
		boolean check = false;
		Scanner sc = new Scanner(System.in);

		for(int i = 0; i < listOfReservations.size(); i++)
		{
			reservation = (Reservation)listOfReservations.get(i);
			reservationManager.checkReservationBooking(reservation, RRPSS.RESERVATION_EXPIRES_IN_MINUTES);
		}


		TableManager tableManager = new TableManager();
		List listOfTables = tableManager.onStartUp();


		for(int i = 0; i < listOfTables.size(); i++)
		{
			Table table = (Table)listOfTables.get(i);

			if(table.getOrder() != null)
			{
				if(table.getReservation() != null)	
				{
					System.out.print("\t\t");
					
					System.out.println("Table " + table.getId() + " is occupied and reserved!");
				}

				else
				{
					System.out.print("\t\t");
				
					System.out.println("Table " + table.getId() + " is occupied!");
				}

			}

			else if(table.getReservation() != null)
			{
				System.out.print("\t\t");
				
				System.out.println("Table " + table.getId() + " is reserved!");
			}

			else
			{
				System.out.print("\t\t");
			
				System.out.println("Table " + table.getId() + " is available!");
			}
		}
		System.out.print("\t\t");
		System.out
				.println("************End Checking Table Availability************");
	}

}
