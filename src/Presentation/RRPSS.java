package Presentation;

import java.util.Scanner;

import Business.*;
import Data.*;

/**
 * This class implements the boundary UI of the Restaurant Reservation and Point
 * of Sale System(RRPSS) to interact with the user.
 * 
 * @author Low See Rong
 * @version 1.0
 * @since 6th November 2014
 */

public class RRPSS {
	/**
	 * The restaurant service charge(%) as a fraction of 100.
	 */
	public static double SERVICE_CHARGE;

	/**
	 * The restaurant GST charge(%) as a fraction of 100.
	 */
	public static double GST;

	/**
	 * The restaurant maximum time allowance for the customer to be late for a
	 * reservation in minutes.
	 */
	public static int RESERVATION_EXPIRES_IN_MINUTES;

	/**
	 * This function boots up the system and provides a UI to interact with the
	 * restaurant staff.
	 * 
	 * @param Args
	 */
	public static void main(String[] Args) {
		// TODO - implement RRPSS.main
		System.out.println("Main Method!");

		System.out.println("\n \t \t \t \tSystem starting up...");
		SERVICE_CHARGE = 0.10;
		GST = 0.07;
		RESERVATION_EXPIRES_IN_MINUTES = 1;
		System.out.print("\t\t");
		System.out.format("%-20s:", "Service Charge is");
		System.out.print(SERVICE_CHARGE * 100);
		System.out.print("%");
		System.out.println();
		System.out.print("\t\t");
		System.out.format("%-20s:", "GST is");
		System.out.print(String.format("%.2f", (GST * 100)) + "%");
		System.out.println();

		System.out.println("All reservations expires after "
				+ RESERVATION_EXPIRES_IN_MINUTES
				+ " mintues past the actual booking time.");
		System.out.println();

		Staff staff = null;
		try {
			System.out.println("Retrieving Authorized User ... ");
			staff = StaffUI.selectUser();
			System.out.println();
			System.out.println("Welcome " + staff.getName() + "!");
		}

		catch (Exception e) {
			System.out.println("System unable to start up!");
			System.out.println("Ending program!");
			return;
		}

		Scanner sc = new Scanner(System.in);
		int choice = 0;

		do {
			try {
				printMenu();
				System.out.print("\t\t");
				System.out.format("%-20s:", "Enter choice");

				choice = Integer.parseInt(sc.nextLine());

				switch (choice) {
				case 1:
					ItemUI.createMenuItem();
					break;

				case 2:
					ItemUI.updateMenuItem();
					break;

				case 3:
					ItemUI.removeMenuItem();
					break;

				case 4:
					PromotionalPackageUI.createPackage();
					break;

				case 5:
					PromotionalPackageUI.updatePackage();
					break;

				case 6:
					PromotionalPackageUI.removePackage();
					break;

				case 7:
					OrderUI.createOrder(staff);
					break;

				case 8:
					OrderUI.viewOrder();
					break;

				case 9:
					OrderUI.addOrderedItem();
					break;

				case 10:
					OrderUI.addOrderedPromotionalPackage();
					break;

				case 11:
					OrderUI.removeOrderedItem();
					break;

				case 12:
					OrderUI.removeOrderedPromotionalPackage();
					break;

				case 13:
					ReservationUI.createReservation();
					break;

				case 14:
					ReservationUI.checkReservation();
					break;

				case 15:
					ReservationUI.removeReservation();
					break;

				case 16:
					TableUI.checkTableAvailability();
					break;

				case 17:
					InvoiceUI.printOrderInvoice();
					break;

				case 18:
					InvoiceUI.printSaleRevenueReportByDay();
					break;

				case 19:
					InvoiceUI.printSaleRevenueReportByMonth();
					break;

				case 20:
					System.out.println("Program end!");
					break;

				default:
					System.out.println("Invalid Input!");
					break;
				}
			}

			catch (Exception e) {
				System.out.println("Invalid Input!");
			}
		} while (choice != 20);

	}

	/**
	 * This function prints a list of functionality of this system.
	 */
	private static void printMenu() {
		// TODO - implement RRPSS.printMenu
		System.out.println();
		System.out.println("1) Create Menu Item");
		System.out.println("2) Update Menu Item");
		System.out.println("3) Remove Menu Item");
		System.out.println("4) Create Promotion Package");
		System.out.println("5) Update Promotion Package");
		System.out.println("6) Remove Promotion Package");
		System.out.println("7) Create order");
		System.out.println("8) View order");
		System.out.println("9) Add ordered item/s to an order");
		System.out.println("10) Add ordered promotional package/s to an order");
		System.out.println("11) Remove ordered item/s from an order");
		System.out
				.println("12) Remove ordered promotional package/s from an order");
		System.out.println("13) Create Reservation booking");
		System.out.println("14) Check Reservation booking");
		System.out.println("15) Remove Reservation booking");
		System.out.println("16) Check table availablity");
		System.out.println("17) Print order invoice");
		System.out.println("18) Print sale revenue report by day");
		System.out.println("19) Print sale revenue report by month");
		System.out.println("20) Exit.");
	}

}