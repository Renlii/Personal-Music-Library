package activity3;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


/**
 * Represents a collection of songs, playlists, and albums.
 */
public class Library
{
	//aSongs contains pairs of (String aFile, Song song)
	//aPlaylistList contains (String aTitle, Playlist playlist)
	//aAlbumLisit contains (String aTitle, Album album)
	private boolean aIsBackedup = false;
	private final Map<String, Song> aSongs = new HashMap<>();
	private final Map<String, Playlist> aPlaylistList = new HashMap<>();
	private final Map<String, Album> aAlbumList = new HashMap<>();
 	
	public static final Library INSTANCE = new Library();
	
	
	/**
	 * initialize a singleton library instance with name pName
	 * 
	 */
	private Library() {}
	
	/**
	 * accessor method that returns the singleton library instance
	 * 
	 * @return library INSTANCE
	 */
	public static Library instance()
	{
		return INSTANCE;
	}

	/**
	 * add new Song object to library
	 * 
	 * @param pSong
	 * @pre pSong != null
	 */
	public void addSong(Song pSong)
	{
		assert(pSong != null ) : "Must pass valid Song object";
		if(!(aSongs.containsKey(pSong.getFile())))
		{
			aSongs.put(pSong.getFile(), pSong);
			aIsBackedup = false;
		}
	}
	
	/**
	 * if no song file in library with file pFile, create it and add it to library
	 * @param pFile
	 * @pre pFile != null
	 * @post add song with file pFile to library
	 */
	public void addSong(String pFile)
	{
		assert(pFile != null) : "Must pass valid string for song file";
		if(!aSongs.containsKey(pFile))
		{
			Song song = new Song(pFile);
			aSongs.put(pFile, song);
			aIsBackedup = false;
		}
	}
	
	/**
	 * removes song from library
	 * @param pSong
	 * @pre pSong != null
	 * @pre !aSongs.isEmpty()
	 * @pre aSongs.containsKey(pSong.getFile())
	 */
	public void removeSong(Song pSong)
	{
		assert(pSong != null): "must pass valid Song object";
		assert(!aSongs.isEmpty()) : "no songs in library";
		assert(aSongs.containsKey(pSong.getFile())) : "library does not contain this song";
		aSongs.remove(pSong.getFile(), pSong);
		aIsBackedup = false;
	}
	
	/**
	 * adds new playlist to library if library is not empty of songs
	 * and if library does not contain pPlaylist
	 * then add the songs in pPlaylist not already in library to library
	 * 
	 * @param pPlaylist
	 * @pre pPlaylist != null
	 * @pre !aSongs.isEmpty()
	 */
	public void addPlaylist(Playlist pPlaylist)
	{
		assert(pPlaylist != null): "Pass valid Playlist object";
		assert(!aSongs.isEmpty()): "Cannot add playlist, no songs in library";
		for (Playlist playlist : aPlaylistList.values())
		{
			if (!playlist.equals(pPlaylist))
			{
				aPlaylistList.put(pPlaylist.getTitle(), pPlaylist);
				aIsBackedup = false;
				for(Song song: pPlaylist.getSongList())
				{
					if(!aSongs.containsKey(song.getFile()))
					{
						aSongs.put(song.getFile(), song);
					}
				}
			}
		}
	}
	
	/**
	 * create playlist to library with title pTitle and add it to library
	 * @param pTitle
	 * @pre pTitle != null
	 * @pre !aSongs.isEmpty()
	 * @post create and add playlist to library
	 */
	public void addPlaylist(String pTitle)
	{
		assert(pTitle != null) : "Must pass valid string for title";
		assert(!aSongs.isEmpty()): "Cannot add playlist, no songs in library";
		Playlist playlist = new Playlist(pTitle);
		aPlaylistList.put(pTitle, playlist);
		aIsBackedup = false;
	}
	
	/**
	 * removes playlist from library
	 * @param pPlaylist
	 * @pre pPlaylist != null
	 * @pre !aPlaylistList.isEmpty()
	 * @pre aPlaylistList.contains(pPlaylist)
	 */
	public void removePlaylist(Playlist pPlaylist)
	{
		assert(pPlaylist != null) : "Must pass valid Playlist object";
		assert(!aPlaylistList.isEmpty()) : "no playlists in library";
		assert(aPlaylistList.containsValue(pPlaylist)) : "library does not contain this playlist";
		aPlaylistList.remove(pPlaylist.getTitle(), pPlaylist);
		aIsBackedup = false;
	}
	
	/**
	 * removes playlist from library and all its songs from library and 
	 * other albums and playlists in library
	 * @param pPlaylist
	 * @pre pPlaylist != null
	 * @pre !aPlaylistList.isEmpty()
	 * @pre aPlaylistList.contains(pPlaylist) 
	 */
	public void removePlaylistAndSongs(Playlist pPlaylist)
	{
		assert(pPlaylist != null) : "Must pass valid Playlist object";
		assert(!aPlaylistList.isEmpty()) : "no playlists in library";
		assert(aPlaylistList.containsValue(pPlaylist)) : "library does not contain this playlist";
		aPlaylistList.remove(pPlaylist.getTitle(), pPlaylist);
		aIsBackedup = false;
		
		for(Song song: pPlaylist.getSongList())
		{
			aSongs.remove(song.getFile());
			for(Album album : aAlbumList.values())
			{
				album.removeSong(song);
			}
			for(Playlist playlist: aPlaylistList.values())
			{
				playlist.removeSong(song);
			}
		}
	}
	
	/**
	 * adds new album to library if library is not empty of songs
	 * and if library does not contain pAlbum
	 * then add the songs in pAlbum not already in library to library
	 * 
	 * @param pAlbum
	 * @pre pAlbum != null
	 * @pre !aSongs.isEmpty()
	 */
	public void addAlbum(Album pAlbum)
	{
		assert(pAlbum != null) : "Must pass valid Album object";
		assert(!aSongs.isEmpty()) : "Library empty of songs";
		for (Album album : aAlbumList.values())
		{
			if (!album.equals(pAlbum))
			{
				aAlbumList.put(pAlbum.getTitle(), pAlbum);
				aIsBackedup = false;
				for(Song song: pAlbum.getSongList())
				{
					if(!aSongs.containsKey(song.getFile()))
					{
						aSongs.put(song.getFile(), song);
					}
				}
			}
		}
	}
	
	/**
	 * create album to library with title pTitle and add it to library
	 * @param pTitle
	 * @pre pTitle != null
	 * @pre !aSongs.isEmpty()
	 * @post create and add album to library
	 */
	public void addAlbum(String pTitle)
	{
		assert(pTitle != null) : "Must pass valid string for title";
		assert(!aSongs.isEmpty()) : "Library empty of songs, cannot add album";
		Album album = new Album(pTitle);
		aAlbumList.put(pTitle, album);
		aIsBackedup = false;
	}
	/**
	 * remove album from library
	 * @param pAlbum
	 * @pre pAlbum != null
	 * @pre !aAlbumList.isEmpty()
	 * @pre aAlbumList.contains(pAlbum)
	 */
	public void removeAlbum(Album pAlbum)
	{
		assert(pAlbum != null) : "Must pass valid Playlist object";
		assert(!aAlbumList.isEmpty()): "no albums in library";
		assert(aAlbumList.containsValue(pAlbum)) : "library does not contain this playlist";
		aAlbumList.remove(pAlbum.getTitle(), pAlbum);
		aIsBackedup = false;
	}
	
	/**
	 * removes album from library and all its songs from library and 
	 * other albums and playlists in library
	 * @param pAlbum
	 * @pre pAlbum != null
	 * @pre !aAlbumList.isEmpty()
	 * @pre aAlbumList.contains(pAlbum) 
	 */
	public void removeAlbumAndSongs(Album pAlbum)
	{
		assert(pAlbum != null) : "Must pass valid Playlist object";
		assert(!aAlbumList.isEmpty()) : "no albums in library";
		assert(aAlbumList.containsValue(pAlbum)) : "library does not contain this playlist";
		aAlbumList.remove(pAlbum.getTitle(), pAlbum);
		aIsBackedup = false;
		
		for(Song song: pAlbum.getSongList())
		{
			aSongs.remove(song.getFile());
			for(Album album : aAlbumList.values())
			{
				album.removeSong(song);
			}
			for(Playlist playlist: aPlaylistList.values())
			{
				playlist.removeSong(song);
			}
		}
	}
	
	/**
	 * get song with title pTitle
	 * @param pTitle
	 * @return Optional<song> with title pTitle if it exists, else return and Optional.empty
	 * @pre pTitle != null
	 */
	private Optional<Song> getSongOptional(String pTitle)
	{
		assert(pTitle != null) : "Must pass valid string value for song title";
		for(Song song : aSongs.values())
		{
			if(song.getTitle() == pTitle)
			{
				
				return Optional.of(new Song(song.getFile()));
						
			}
		}
		return Optional.empty();
	}
	
	/**
	 * get song with title pTitle
	 * @param pTitle
	 * @return song with title pTitle if it is present in library
	 * @pre pTitle != null
	 */
	public Song getSong(String pTitle)
	{
		assert(getSongOptional(pTitle).isPresent()) : "No song with this title in library";
		assert(pTitle != null) : "Must pass valid string value for song title";
		Optional<Song> song = getSongOptional(pTitle);
		return song.get();
	}

	/**
	 * returns a deep copy list of songs in library
	 * @post returns a deep copy hashmap of songs
	 */
	public HashMap<String, Song> getSongList()
	{
		HashMap<String, Song> songList = new HashMap<>();
		for(Song song : aSongs.values())
		{
			songList.put(song.getFile(), song);
		}
		return songList;
	}
	
	/**
	 * returns deep copy list of playlists in library
	 * @post returns list of playlists
	 */
	public HashMap<String, Playlist> getPlaylistList()
	{
		HashMap<String, Playlist> playlistList = new HashMap<>();
		for(Playlist playlist : aPlaylistList.values())
		{
			playlistList.put(playlist.getTitle(), playlist);
		}
		return playlistList;
	}
	
	/**
	 * returns deep copy list of albums in library
	 * @post returns list of albums
	 */
	public HashMap<String, Album> getAlbumList()
	{
		HashMap<String, Album> albumList = new HashMap<>();
		for(Album album : aAlbumList.values())
		{
			albumList.put(album.getTitle(), album);
		}
		return albumList;
	}
	
	/**
	 * backup library to a database using stub method in Database class
	 * @post back up library if not backed up and change backup state of library to true
	 * 
	 */
	public void backup()
	{
		if(!isBackedUp())
		{
			Database.backup(this);
			aIsBackedup = true;
		}
	}
	/**
	 * check if library is backed up
	 * @return aIsBackedup true if backed up, false if something change in library since last backup
	 */
	public boolean isBackedUp()
	{
		return aIsBackedup;
	}

}
