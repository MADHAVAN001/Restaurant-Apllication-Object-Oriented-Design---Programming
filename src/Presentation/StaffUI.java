package Presentation;

import java.util.List;
import java.util.Scanner;

import Business.*;
import Data.*;

/**
 * This class implements the boundary Staff UI to interact with the user.
 * @author Pathangi Janardhanan Jatin Shravan
 * @version 1.0
 * @since 6th November 2014
 */
public class StaffUI 
{
	/**
	 * This function loops to ask the user to select which staff is using this system from the list of staff until a valid input is selected.
	 * @return Selected staff
	 */
	public static Staff selectUser()
	{
		StaffManager staffManager = new StaffManager();
		Staff staff = null;
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		boolean check = false;

		do
		{
			try
			{
				List listOfStaff = staffManager.onStartUp();
				
				for(int i = 0; i < listOfStaff.size(); i++)
				{
					staff = (Staff)listOfStaff.get(i);

					System.out.println((i+1) + ") ID: " + staff.getId() + " | Name: " + staff.getName() + " | Job Title: " + staff.getJobTitle());
				}
				System.out.println();
				System.out.print("\t\t");
				System.out.format("%-20s:","Select User");
				
				choice = Integer.parseInt(sc.nextLine());

				staff = (Staff)listOfStaff.get(choice-1);

				check = true;
			}

			catch(Exception e)
			{
				System.out.println("Invalid Input!");
				check = false;
			}

		}while(check == false);

		return staff;
	}
}
