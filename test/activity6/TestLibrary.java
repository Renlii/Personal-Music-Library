package activity6;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javafx.scene.control.ListView;

public class TestLibrary
{
    Song aSong = new Song(new File("testSong.mp3"), "songTest");
    Playlist aPlaylist = new Playlist("playlistTest");
    Model aModel = new Model();
    Library aLibrary = new Library();
    ListViewPanel lvp = new ListViewPanel(aModel, aLibrary);
    int before;

    @BeforeAll
    public static void setupClass()
    {
        JavaFXLoader.load();
    }

    /*
     * return number of playables in library
     */
    private int sizeOfLibrary(Library pLibrary) {
        ArrayList<Playable> lib = new ArrayList<>();
        for (Playable pPlayable : pLibrary) {
            lib.add(pPlayable);
        }
        return lib.size();
    }

    /*
     * tests whether `Once the file is added to the library, all appropriate views of the library should be added`.
     */
    @Test
    public void test_AddSong()
    {
        before = sizeOfLibrary(aLibrary);
        aLibrary.addItem(aSong);
        assertEquals(before + 1, sizeOfLibrary(aLibrary));
        assertEquals(before + 1, lvp.getListView().getItems().size());

        aLibrary.addItem(aPlaylist);
        assertEquals(before + 2, sizeOfLibrary(aLibrary));
        assertEquals(before + 2, lvp.getListView().getItems().size());

    }

    /*
     * test for showing current library state after the item is deleted.
     */
    @Test
    public void test_deleteSong()
    {
        before = sizeOfLibrary(aLibrary);
        aLibrary.removeItem(1);
        assertEquals(before - 1, sizeOfLibrary(aLibrary));
        assertEquals(before - 1, lvp.getListView().getItems().size());

    }
}
