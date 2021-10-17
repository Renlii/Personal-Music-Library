package activity3;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.List;

/**
 * Represents an album with a title (mandatory) and 
 * artist (can be absent). An album can be incomplete,
 * in the sense that some tracks can be missing.
 */
public class Album
{
	private String aTitle;
	private Optional<Artist> aArtist;
	private Map<Integer, Song> aTracks;
	
	/**
	 * constructor to create Album object only by specifing title only
	 * @param pFile
	 */
	public Album(String pTitle)
	{
		aTitle = pTitle;
		// set null for optional data
		aArtist = Optional.empty();
	}

	public String getTitle()
	{
		return aTitle;
	}
	
	/**
	 * return artist if present otherwise the given parameter
	 * @return aArtist
	 */
	public Artist getArtist()
	{
		assert (aArtist.isPresent()): "Unknown artist";
		return aArtist.get();
	}

	/**
	 * set artist of an album
	 * @param pArtist
	 * @pre pArtist != null
	 */
	public void setArtist(Artist pArtist)
	{
		assert pArtist!=null;
		aArtist = Optional.of(pArtist);
	}
	
	/**
	 * Add a track to this album. If the track already exists,
	 * it is written over.
	 * 
	 * @param pNumber The track number. Must be greater than 0.
	 * @param pSong The song for this track. 
	 * @pre pNumber > 0 && pSong != null
	 */
	public void addTrack(int pNumber, Song pSong)
	{
		assert pNumber > 0 && pSong != null;
		aTracks.put(pNumber, pSong);
	}
	
	/**
	 * get ID of track
	 * @param pSong
	 * @return id of song track
	 * @pre pSong != null
	 * @pre aTracks.containsValue(pSong)
	 */
	private Integer getTrackID(Song pSong)
	{
		assert(pSong != null) : "must pass valid Song object";
		assert(aTracks.containsValue(pSong)): "must pass valid Song object";
		Set<Integer> songIDs = aTracks.keySet();
		Integer songID = null;
		for(Integer id : songIDs)
		{
			if (aTracks.get(id).equals(pSong))
			{
				songID = id;
			}
		}
		return songID;
	
	}
	
	/**
	 * returns list of songs in album
	 * @return arraylist of songs
	 */
	public ArrayList<Song> getSongList()
	{
		ArrayList<Song> songList = new ArrayList<>();
		for(Song song : aTracks.values())
		{
			songList.add(song);
		}
		return songList;
	}
	
	/**
	 * check if song is in album
	 * @param pSong
	 * @return true if song in album, false otherwise
	 * @pre pSong != null
	 */
	public boolean containsSong(Song pSong)
	{
		assert(pSong != null) : "Must pass valid Song object";
		if(aTracks.containsValue(pSong))
		{
			return true;
		}
		return false;
	}
	
	/**
	 * remove song from album
	 * @param pSong
	 * @pre pSong != null
	 * @pre this.containsSong(pSong)
	 */
	public void removeSong(Song pSong)
	{
		assert(pSong != null) : "must pass valid Song object";
		assert(this.containsSong(pSong)): "Song not in album";
		aTracks.remove(getTrackID(pSong));
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aArtist == null) ? 0 : aArtist.hashCode());
		result = prime * result + ((aTitle == null) ? 0 : aTitle.hashCode());
		result = prime * result + ((aTracks == null) ? 0 : aTracks.hashCode());
		return result;
	}

	@Override
	//This method represents the 'totally-equals' criteria 
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Album other = (Album) obj;
		if (aArtist == null)
		{
			if (other.aArtist != null)
				return false;
		}
		else if (!aArtist.equals(other.aArtist))
			return false;
		if (aTitle == null)
		{
			if (other.aTitle != null)
				return false;
		}
		else if (!aTitle.equals(other.aTitle))
			return false;
		if (aTracks == null)
		{
			if (other.aTracks != null)
				return false;
		}
		else if (!mapEquals(other.aTracks, this.aTracks))
			return false;
		return true;
	}
	
	//This method takes in two maps to check if they have tracks in the same order
	private static boolean mapEquals(Map<Integer,Song> obj1, Map<Integer,Song> obj2) {
		boolean same = true; 
		if(obj1.size()!=obj2.size()) {
			return same = false;
		}
		List<Integer> l1 = new ArrayList<Integer>(obj1.keySet());
		List<Integer> l2 = new ArrayList<Integer>(obj2.keySet());
		for(int i = 0; i<l1.size() ; i++) {
			//checking if keys are the same
			if(!l1.get(i).equals(l2.get(i)))
				same = false;
			//checking if values are the same; i.e if two songs are 'equal', referring to the same file
			if(!obj1.get(l1.get(i)).equals(obj2.get(l2.get(i))))
				same = false;
		}
		return same;
	}

	/**
	 * This method detects duplicates by comparing two albums for the content of their songlists, 
	 * irrespective of order/title/artist
	 * @param aAlbum1, aAlbum2
	 * @pre aAlbum1 != null && aAlbum != null
	 * @return true if contents of two albums equal, else return false
	 */
	public boolean contentEquals(Album aAlbum1, Album aAlbum2)
	{
	    assert(aAlbum1 != null && aAlbum2 != null) : "Must pass valid Album objects";
	    //check if two playlists have same size
	    if (aAlbum1.getSongList().size() != aAlbum2.getSongList().size())
	    {
	        return false;
	    }
	    else //see if they have same songs, order does not matter
	    {
	        for(Song song : aAlbum1.getSongList())
	        {
	            if(!aAlbum2.getSongList().contains(song))
	            {
	                return false;
	            }
	        }
	        return true;
	     }
	}
	
}
