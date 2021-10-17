package activity6;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TestConsolePanel
{
	Model aModel=new Model();
	Library aLibrary=new Library();
	ConsolePanel aCP = new ConsolePanel(aModel,aLibrary);
	
	@BeforeAll
	public static void setuoClass() {
		JavaFXLoader.load();
	}
	
	@Test
	void Test_addedItem()
	{
		File file1=new File("songForTesting");
		Song song1=new Song(file1,"songForTesting");
		aCP.addedItem(song1);
		String latestMessage=aCP.getLatestMessage();
		String[] parsedMessage=latestMessage.split("\\s+");
		assertEquals(parsedMessage[1],"ADD");
		assertEquals(parsedMessage[2],song1.getTitle());
	}
	
	@Test
	void Test_removedItem() 
	{
		File file1=new File("songForTesting");
		Song song1=new Song(file1,"songForTesting");
		aCP.addedItem(song1);
		aCP.removedItem(getInternalStorage().size()-1);
		String latestMessage=aCP.getLatestMessage();
		System.out.println("her");
		String[] parsedMessage=latestMessage.split("\\s+");
		assertEquals(parsedMessage[1],"REMOVE");
		assertEquals(parsedMessage[2],song1.getTitle());
	}
	
	private ArrayList<Playable> getInternalStorage() {
		try {
			Field listField = ConsolePanel.class.getDeclaredField("list");
			listField.setAccessible(true);
			return (ArrayList<Playable>) listField.get(aCP);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
			return null; //Will never return this
		}
	}

}