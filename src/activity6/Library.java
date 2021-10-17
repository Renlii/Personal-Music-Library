package activity6;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a collection of songs, playlists, and albums.
 */
public class Library implements Iterable<Playable>
{
	private final List<Playable> aPlayables = new ArrayList<>();

	/**
	 * Creates a new empty library.
	 */
    public Library()
	{
		// Just for demonstration
		Song A = new Song(new File("A.mp3"), "Song A");
		Song B = new Song(new File("B.mp3"), "Song B");
		Playlist pl = new Playlist("My Playlist");
		pl.add(A);
		pl.add(B);
		addItem(A);
		addItem(B);
		addItem(new Song(new File("C.mp3"), "Song C"));
		addItem(pl);
	}

	/**
	 * Add an item to the library
	 * 
	 * @param pPlayable
	 * @pre pPlayable != null
	 */
	public void addItem(Playable pPlayable)
	{
		assert pPlayable != null;
		aPlayables.add(pPlayable);
	}

	/**
	 * Remove an item from the library
	 * 
	 * @param pPlayable
	 * @pre index >= 0 && index < aPlayables.size()
	 */
	public void removeItem(int index)
	{
		assert index >= 0 && index < aPlayables.size();
		aPlayables.remove(index);
	}

	@Override
	public Iterator<Playable> iterator()
	{
		return new ArrayList<>(aPlayables).iterator();
	}
}