package Presentation;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

import Business.*;
import Data.*;

/**
 * This class implements the boundary Reservation UI to interact with the user.
 * 
 * @author Low See Rong
 * @version 1.0
 * @since 6th November 2014
 */
public class ReservationUI {
	/**
	 * This function ask user to input the customer's information. Then ask user
	 * to input the hour and minute of the day. Then creates the reservation and
	 * display the reservation information.
	 */
	public static void createReservation() {
		System.out.print("\t\t");
		System.out
				.println("************Creating a new Reservation************");
		// TODO - implement RRPSS.createReservation
		CustomerManager customerManager = new CustomerManager();
		Customer customer = null;

		ReservationManager reservationManager = new ReservationManager();
		Reservation reservation = null;
		List listOfReservations = reservationManager.onStartUp();

		TableManager tableManager = new TableManager();
		Table table = null;
		List listOfTables = tableManager.onStartUp();

		// int i = 0;
		boolean check = false;
		Scanner sc = new Scanner(System.in);

		try {
			customer = new Customer();
			System.out.print("\t\t");
			System.out.format("%-25s:", "Enter customer name");

			customer.setName(sc.nextLine());
			System.out.print("\t\t");

			System.out.print("Enter customer contact number: ");
			customer.setContactNumber(Integer.parseInt(sc.nextLine()));

			reservation = new Reservation();
			System.out.print("\t\t");
			System.out.format("%-25s:", "Enter number of people");

			reservation.setNumberOfPax(Integer.parseInt(sc.nextLine()));
			System.out.println();
			System.out.print("\t\t");

			System.out
					.println("Please input hour(HH) from 00-23, minutes(mm) from 00-59");
			System.out.print("\t\t");

			System.out
					.print("Enter start timing in 24-hr format(HH:mm) of the day to reserve: ");
			String[] timing = sc.nextLine().split(":");
			int hour = Integer.parseInt(timing[0]);
			int minute = Integer.parseInt(timing[1]);

			if (timing.length > 2) {
				System.out.print("\t\t");
				System.out.format("%-25s:", "TASK STATUS");
				throw new Exception("Invalid Input!");
			}

			if (hour < 0 || hour > 23) {
				System.out.print("\t\t");
				System.out.format("%-25s:", "TASK STATUS");
				throw new Exception("Invalid hour input!");
			}

			if (minute < 0 || minute > 59) {
				System.out.print("\t\t");
				System.out.format("%-25s:", "TASK STATUS");
				throw new Exception("Invalid minute input!");
			}

			Calendar c = new GregorianCalendar();
			c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timing[0]));
			c.set(Calendar.MINUTE, Integer.parseInt(timing[1]));
			c.set(Calendar.SECOND, 0);
			reservation.setStartDateTime(c.getTime());
			System.out.print("\t\t");
			reservation.setCustomer(customer);
			customer.setReservation(reservation);
			// System.out.println(reservation.getStartDateTime().toString());
			System.out.print("\t\t");
			check = reservationManager.createReservationBooking(reservation,
					RRPSS.RESERVATION_EXPIRES_IN_MINUTES);

			if (check) {
				System.out.print("\t\t");
				System.out.format("%-25s:", "TASK STATUS");
				System.out.println("Reservation made succesfully!");
				System.out.print("\t\t");
				System.out.format("%-25s:", "Reservation ID");
				System.out.println(reservation.getId());
				System.out.print("\t\t");
				System.out.println("Table " + reservation.getTable().getId()
						+ " is reserved for you!");
				System.out.print("\t\t");
				System.out.println();
				System.out.print("\t\t");
				System.out.format("%-25s:", "REMINDER");
				System.out
						.println("Reservation would automactically be removed after "
								+ RRPSS.RESERVATION_EXPIRES_IN_MINUTES
								+ " minutes past "
								+ reservation.getStartDateTime().toString());

				check = customerManager.createCustomer(customer);
				if (check) {
					System.out.print("\t\t");
					System.out.format("%-25s:", "TASK STATUS");
					System.out
							.println("Customer info is recorded successfully!");
				}

				check = tableManager.updateTable(reservation.getTable());

				if (check) {
					System.out.print("\t\t");
					System.out.format("%-25s:", "TASK STATUS");
					System.out.println("Table is updated successfully!");
				}
			}

			else {
				System.out.print("\t\t");
				System.out.format("%-25s:", "TASK STATUS");
				System.out.println("Fail to make reservation!");
			}
		}

		catch (Exception e) {
			System.out.print("\t\t");
			System.out.format("%-25s:", "TASK STATUS");
			System.out.println("Invalid Input!");
		}
		System.out.print("\t\t");
		System.out
				.println("************End Creating a reservation************");
	}

	/**
	 * This function display the list of reservation information.
	 */
	public static void checkReservation() {
		System.out.print("\t\t");
		System.out.println("************Checking a reservation************");
		// TODO - implement RRPSS.createReservation
		ReservationManager reservationManager = new ReservationManager();
		List listOfReservations = reservationManager.onStartUp();

		boolean check = false;

		if (listOfReservations.size() == 0) {
			System.out.print("\t\t");
			System.out.format("%-25s:", "TASK STATUS");
			System.out.println("No reservations to check!");
			return;
		}

		for (int i = 0; i < listOfReservations.size(); i++) {
			Reservation reservation = (Reservation) listOfReservations.get(i);

			check = reservationManager.checkReservationBooking(reservation,
					RRPSS.RESERVATION_EXPIRES_IN_MINUTES);

			if (check == false) {
				System.out.print("\t\t");
				System.out.println("Reservation ID: " + reservation.getId()
						+ " | Booked Timing: "
						+ reservation.getStartDateTime().toString()
						+ " | Table " + reservation.getTable().getId()
						+ " | Customer Name: "
						+ reservation.getCustomer().getName()
						+ " | Customer Contact Number: "
						+ reservation.getCustomer().getContactNumber());
			}
		}
		System.out.print("\t\t");
		System.out
				.println("************End Checking a reservation************");
	}

	/**
	 * This function display a list of reservation for the user to select from
	 * to manually remove a reservation.
	 */
	public static void removeReservation() {
		System.out.print("\t\t");
		System.out.println("************Removing a reservation************");
		// TODO - implement RRPSS.removeReservation
		ReservationManager reservationManager = new ReservationManager();
		List listOfReservations = reservationManager.onStartUp();

		Reservation reservation = null;
		boolean check = false;
		int choice = 0;
		Scanner sc = new Scanner(System.in);

		for (int i = 0; i < listOfReservations.size(); i++) {
			reservation = (Reservation) listOfReservations.get(i);
			reservationManager.checkReservationBooking(reservation,
					RRPSS.RESERVATION_EXPIRES_IN_MINUTES);
		}

		listOfReservations = reservationManager.onStartUp();

		if (listOfReservations.size() == 0) {
			System.out.print("\t\t");
			System.out.format("%-25s:", "TASK STATUS");
			System.out.println("No reservations to delete!");
			return;
		}

		try {
			// print the list of items for the user to select from.
			for (int i = 0; i < listOfReservations.size(); i++) {
				reservation = (Reservation) listOfReservations.get(i);
				System.out.print("\t\t");
				System.out.println((i + 1) + ") Reservation ID: "
						+ reservation.getId() + " | Booked Timing: "
						+ reservation.getStartDateTime().toString()
						+ " | Table " + reservation.getTable().getId()
						+ " | Customer Name: "
						+ reservation.getCustomer().getName()
						+ " | Customer Contact Number: "
						+ reservation.getCustomer().getContactNumber());
			}
			System.out.print("\t\t");
			System.out.print("Select a reservation to remove: ");
			choice = Integer.parseInt(sc.nextLine());
			reservation = (Reservation) listOfReservations.get(choice - 1);
			System.out.print("\t\t");
			check = reservationManager.removeReservationBooking(reservation);

			if (check) {
				System.out.print("\t\t");
				System.out.format("%-25s:", "TASK STATUS");
				System.out.println("Reservation is removed successfully!");
			}

			else {
				System.out.print("\t\t");
				System.out.format("%-25s:", "TASK STATUS");
				System.out.println("Fail to remove reservation!");
			}
		}

		catch (Exception e) {
			System.out.print("\t\t");
			System.out.format("%-25s:", "TASK STATUS");
			System.out.println("Invalid Input!");
		}
		System.out.print("\t\t");
		System.out
				.println("************End Removing a reservation************");
	}

}
