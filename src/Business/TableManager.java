package Business;

import java.util.List;

import Data.*;

/**
 * This class implements the Table Manager controller
 * @author Adrian Wang Wenjie
 *	@version 1.0
 * @since 7th November 2014
 */
public class TableManager 
{
	/**
	 * This function retrieve all the tables.
	 * @return A list of tables.
	 */
	public List onStartUp() 
	{
		// TODO - implement TableManager.onStartUp
		TableDAO tableDAO = new TableDAO();

		List listOfTables = tableDAO.retrieveAll();

		return listOfTables;
	}	
	
	/**
	 * This function updates the table values.
	 * @param table - A table to be updated.
	 * @return TRUE if the update is successful. Return FALSE if the update fails.
	 */
	public boolean updateTable(Table table)
	{
		TableDAO tableDAO = new TableDAO();
		boolean check = false;

		check = tableDAO.update(table);

		return check;
	}
}