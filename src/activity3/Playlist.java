package activity3;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a named list of songs.
 */
public class Playlist 
{
	
	private final List<Song> aSongs = new ArrayList<>();
	private String aTitle; 
	
	public Playlist(String pTitle)
	{
		aTitle = pTitle;
	}
	
	public void setTitle(String pTitle)
	{
		aTitle = pTitle;
	}
	
	public String getTitle()
	{
		return aTitle;
	}
	
	public void addSong(Song pSong)
	{
		aSongs.add(pSong);
	}
	
	public void backup() 
	{
	    System.out.println("Backing up the library");
	}
	
	/**
	 * check if playlist contains song
	 * @param pSong
	 * @return true if playlist contains song, false otherwise
	 * @pre pSong != null
	 */
	public boolean containsSong(Song pSong)
	{
		assert(pSong != null) : "must pass valid Song object";
		if(aSongs.contains(pSong))
		{
			return true;
		}
		return false;
	}
	
	/**
	 * remove song from playlist
	 * @param pSong
	 * @pre this.containsSong(pSong)
	 * @pre pSong != null
	 */
	public void removeSong(Song pSong)
	{
		assert(pSong != null) : "must pass valid Song object";
		assert(this.containsSong(pSong)) : "Song not in playlist";
		aSongs.remove(pSong);
	}
	
	/**
	 * get deep copy of songs in this playlist
	 * @post songList deep copy of aSongs
	 * 
	 */
	public ArrayList<Song> getSongList()
	{
		ArrayList<Song> songList = new ArrayList<>();
		for(Song song : aSongs)
		{
			songList.add(song);
		}
		return songList;
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aSongs == null) ? 0 : aSongs.hashCode());
		result = prime * result + ((aTitle == null) ? 0 : aTitle.hashCode());
		return result;
	}

	/**
	 * This method represents the totally-equal criteria when undergoing duplicate detection
	 * @return true if two playlists are totally equal, false other wise
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Playlist other = (Playlist) obj;
		if (aSongs == null)
		{
			if (other.aSongs != null)
				return false;
		}
		else if (!songsEqual(other.aSongs,this.aSongs))
			return false;
		if (aTitle == null)
		{
			if (other.aTitle != null)
				return false;
		}
		else if (!aTitle.equals(other.aTitle))
			return false;
		return true;
	}
	
	/**This song checks if two lists of songs are equal in their order
	 * 
	 * @param l1
	 * @param l2
	 * @pre l1 != null && l2 != null
	 * @return true if two playlists have same order of songs and same songs, false otherwise
	 */
	private static boolean songsEqual(List<Song> l1, List<Song> l2) 
	{
		assert(l1 != null && l2 != null) : "Must pass valid List<Song> as arguments";
		boolean same = true;
		if(l1.size()!=l2.size()) 
		{
			same = false;
		}
		for(int i = 0; i<l1.size(); i++) 
		{
			if(!l1.get(i).equals(l2.get(i))) 
			{
				same=false;
			}
		}
		return same;
	}
	
	
	/**This method detects duplicates by comparing two playlists for the content of their songlists, irrespective of order/title/artist
	 * 
	 * @param aPlaylist1
	 * @param aPlaylist2
	 * @pre aPlaylist1 != null && aPlaylist2 != null
	 * @return true if contents of two playlists equal, false otherwise
	 */
	public boolean contentEquals(Playlist aPlaylist1, Playlist aPlaylist2)
	{
		assert(aPlaylist1 != null && aPlaylist2 != null) : "Must pass valid Playlist objects";
	    //check if two playlists have same size
	    if (aPlaylist1.getSongList().size() != aPlaylist2.getSongList().size())
	    {
	        return false;
	    }
	    else //see if they have same songs, order does not matter
	    {
	        for(Song song : aPlaylist1.getSongList())
	        {
	            if(!aPlaylist2.getSongList().contains(song))
	            {
	                return false;
	            }
	        }
	        return true;
	     }
	}


	

}
