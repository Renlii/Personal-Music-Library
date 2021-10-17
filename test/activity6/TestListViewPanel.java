package activity6;
//Testing by @melena1
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;

class TestListViewPanel
{
	private Model aModel ;
	private Library aLibrary;
	private ListViewPanel test;

//Note: aLibrary already starts with 4 objects that we placed in it for demonstration
	@BeforeEach
	void Initialize() {
		aModel = new Model();
		aLibrary = new Library();
		JavaFXLoader.load();
		test = new ListViewPanel(aModel,aLibrary);

	}

	//helper method that returns number of playables in library
	private int getSizeOfLibraryPlayables(Library pLibrary) {
		ArrayList<Playable> lib = new ArrayList<>();
		for (Playable pPlayable : pLibrary) {
			lib.add(pPlayable);
		}
		return lib.size();
	}
	//checks that when an Item is added, this change appears in both GUI and Library Object
	@Test
	void Test_addedItem() {
		Song s2 = new Song(new File ("test"),"Test1");
		test.addedItem(s2);
		assertEquals(5, this.getSizeOfLibraryPlayables(aLibrary));
		assertEquals(5, test.getListView().getItems().size());
		//checks if the GUI returns the same thing in the Library
	}
	
	//checks that when an Item is removed, this change appears in both GUI and Library Object
	@Test
	void Test_removedItem() {
		test.removedItem(2);
		assertEquals(3, this.getSizeOfLibraryPlayables(aLibrary));
		assertEquals(3, test.getListView().getItems().size());
		//checks if the GUI returns the same thing in the Library
	}
}
