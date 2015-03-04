package Data;

import java.util.List;

/**
 * This class implements the Table Data Access Object with the attributes fileName, fileNameForRunningID.
 * 
 * This class is solely responsible for Create/Retrieve/Update/Delete functions from our flat file(Text File).
 * 
 * This class is using Serialization to write Table objects into a text file.
 * 
 * @author Adrian Wang Wenjie
 * @version 1.0
 * @since 24th October 2014
 */
public class TableDAO 
{
	/**
	 * The file name where the Table object is stored.
	 */
	private String fileName;
	
	/**
	 * The file name for keeping track of the Table unique running id.
	 */
	private String fileNameForRunningID;

	/**
	 * Default constructor.
	 * 
	 *	fileName = "Table.txt";
	 *	fileNameForRunningID = "TableRunningID.txt";
	 */
	public TableDAO()
	{
		fileName = "Table.txt";
		fileNameForRunningID = "TableRunningID.txt";
	}

	/**
	 * This function retrieves all the tables
	 * @return A list of tables.
	 */
	public List retrieveAll() 
	{
		List listOfTables = null;
		try {

			listOfTables = SerializeDB.readSerializedObject(fileName);

		} catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
		}

		return listOfTables;
	}

	/**
	 * This function replace the existing table in the text file with an updated table.
	 * @param table - An table with updated values.
	 * @return Return TRUE if the update is successful. Return FALSE if the update fails.
	 */
	public boolean update(Table table)
	{
		boolean check = false;
		List listOfTables;

		try 
		{
			listOfTables = SerializeDB.readSerializedObject(fileName);

			for(int i = 0; i < listOfTables.size(); i++)
			{
				Table tempTable = (Table)listOfTables.get(i);

				if(table.getId() == tempTable.getId())
				{
					listOfTables.set(i, table);
					SerializeDB.writeSerializedObject(fileName, listOfTables);
					check = true;

					return check;
				}
			}
		} 

		catch (Exception e) 
		{
			System.out.println("Exception >> " + e.getMessage());
			check = false;
		}

		return check;
	}
}