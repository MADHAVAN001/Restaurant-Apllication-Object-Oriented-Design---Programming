package Business;

import java.util.Date;
import java.util.List;

import Data.*;

/**
 * This class implements the Reservation Manager controller
 * 
 * @author Low See Rong
 * @version 1.0
 * @since 6th November 2014
 */
public class ReservationManager {

	/**
	 * This function retrieves a list of reservations
	 * 
	 * @return - A list of reservations.
	 */
	public List onStartUp() {
		// TODO - implement ReservationManager.onStartUp
		ReservationDAO reservationDAO = new ReservationDAO();

		List listOfReservation = reservationDAO.retrieveAll();

		return listOfReservation;
	}

	/**
	 * This function verifies the user input to ensure that the restaurant is
	 * able to accommodate the expected number of people. Also to ensure that
	 * the booking time is can only be made in advance.
	 * 
	 * @param reservation
	 *            - A reservation to be created.
	 * @param reservationExpiryMintue
	 *            - A number of minutes the restaurant allows the customer to be
	 *            late.
	 * @return True means the reservation is made successfully. False means that
	 *         the reservation has failed to be created.
	 */
	public boolean createReservationBooking(Reservation reservation,
			int reservationExpiryMintue) {
		// TODO - implement ReservationManager.createReservationBooking
		boolean check = false;
		ReservationDAO reservationDAO = new ReservationDAO();

		List listOfReservation = onStartUp();
		TableManager tableManager = new TableManager();
		List listOfTables = tableManager.onStartUp();

		boolean validCapacity = false;
		boolean tableFound = false;

		if (reservation.getStartDateTime().before(
				new Date(System.currentTimeMillis()))) {
			System.out.print("\t\t");
			System.out.format("%-25s:", "TASK STATUS");
			System.out.println("You can only book a time slot in advance!");
			check = false;
			return check;
		}

		for (int i = 0; i < listOfTables.size(); i++) {
			Table table = (Table) listOfTables.get(i);

			if (table.getCapacity() >= reservation.getNumberOfPax()) {
				validCapacity = true;
				tableFound = true;

				for (int j = 0; j < listOfReservation.size(); j++) {
					Reservation tempReservation = (Reservation) listOfReservation
							.get(j);

					if (tempReservation.getTable().getId() == table.getId()) {
						tableFound = checkReservationBooking(tempReservation,
								reservationExpiryMintue);
						break;
					}
				}

				if (tableFound) {
					reservation.setTable(table);
					table.setReservation(reservation);
					check = reservationDAO.insert(reservation);
					break;
				}
			}
		}

		if (validCapacity == false) {
			System.out.print("\t\t");
			System.out.format("%-25s:", "TASK STATUS");
			System.out.println("Unable to accommodate that number of people!");
			return validCapacity;
		}

		if (validCapacity == true && tableFound == false) {
			System.out.print("\t\t");
			System.out.format("%-25s:", "TASK STATUS");
			System.out.println("Sorry, all tables are reserved!");
			return tableFound;
		}

		return check;
	}

	/**
	 * This function removes the reservation and update the table accordingly to
	 * release it for reservation.
	 * 
	 * @param reservation
	 *            - A reservation to be removed.
	 * @return True means that the reservation has be removed successfully.
	 *         False means that the reservation has failed to be removed.
	 */
	public boolean removeReservationBooking(Reservation reservation) {
		// TODO - implement ReservationManager.removeReservationBooking
		boolean check = false;

		TableManager tableManager = new TableManager();
		ReservationDAO reservationDAO = new ReservationDAO();

		check = reservationDAO.delete(reservation);

		if (check) {
			Table table = reservation.getTable();
			table.setReservation(null);
			if (tableManager.updateTable(table)) {
				System.out.print("\t\t");

				System.out.println("Table " + table.getId()
						+ " is now available for booking!");
			}

			else {
				System.out.print("\t\t");
				System.out.format("%-25s:", "TASK UPDATE");
				System.out.println("Fail to release table " + table.getId()
						+ " for booking!");
			}
		}

		return check;

	}

	/**
	 * This function checks if the reservation has expired pass the allowed
	 * timing. If the reservation has expired pass the allowed timing, remove
	 * the reservation.
	 * 
	 * @param reservation
	 *            - A reservation to be checked.
	 * @param reservationExpiryMintue
	 *            - A number of minutes the restaurant allows the customer to be
	 *            late.
	 * @return True means the reservation has expired past the allowed timing
	 *         and removed successfully. False means that the reservation has
	 *         not expired past the allowed timing.
	 */
	public boolean checkReservationBooking(Reservation reservation,
			int reservationExpiryMinute) {
		// TODO - implement ReservationManager.checkReservationBooking
		ReservationDAO reservationDAO = new ReservationDAO();
		boolean check = false;

		Date reservationTiming = new Date(reservation.getStartDateTime()
				.getTime() + (reservationExpiryMinute * 60000));

		if (reservationTiming.before(new Date(System.currentTimeMillis()))) {
			check = removeReservationBooking(reservation);

			if (check) {
				System.out.print("\t\t");
				System.out.format("%-25s:", "TASK UPDATE");
				System.out.println("Reservation ID: " + reservation.getId()
						+ " has expired and have been automatically removed!");
			}
		}

		return check;
	}

}