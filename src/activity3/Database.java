package activity3;

/**
 * This class is a mock-up for a database facade. 
 */
public class Database 
{
	/**
	 * stub method for backing up library
	 * @param pLibrary
	 * @pre pLibrary != null
	 */
    public static void backup(Library pLibrary) 
    {
    	assert(pLibrary != null) : "Must pass valid Library instance";
        System.out.println("copying information to a database");
    }
}