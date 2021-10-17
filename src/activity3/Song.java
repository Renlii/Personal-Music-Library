package activity3;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.io.File;
import java.nio.file.InvalidPathException;

public class Song
{
	private final String aFile;
	//optional fields
	private Optional<String> aTitle;
	private Optional<Integer> aDuration;
	private Optional<Artist> aArtist;
	private Optional<Genre> aGenre;
	private Optional<Map<String, String>> aTags;

	/**
	 * constructor to create Song object only by specified file
	 * @param pFile
	 */
	public Song(String pFile)
	{
		if (isValidCreate(pFile)) 
		{
			if (isValidExtension(pFile)) 
			{
				aFile = pFile;
				//initialize null
				aTitle = Optional.empty();
				aDuration = Optional.empty();
				aArtist = Optional.empty();
				aGenre = Optional.empty();
				//initialize with empty map
				aTags = Optional.of(new HashMap<>());

			} 
			else 
			{
				throw new InvalidPathException(pFile, "File extension not mp3\n");
			}
		}
		else
		{
			throw new InvalidPathException(pFile,"Song does not exist on the OS\n");
		}
	}
	
	public String getFile()
	{
		return aFile;
	}

	//getters

	/**
	 * gets title of song
	 * @return title of song if present otherwise the given parameter
	 */
	public String getTitle()
	{
		return aTitle.orElse("Unknown Title");
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
	 * return Genre if present
	 * @return aGenre
	 */
	public Genre getGenre()
	{
		assert (aGenre.isPresent()): "Unknown genre";
		return aGenre.get();
	}
	
	/**
	 * return duration of Song if present, otherwise -1
	 * @return aDuration
	 */
	public Integer getDuration()
	{
		return aDuration.orElse(-1);
	}

	//setters

	@SuppressWarnings("null")
	/**
	 * set title of a song
	 * @param pTitle
	 * @pre pTitle != null
	 */
	public void setTitle(String pTitle)
	{
		assert pTitle!=null || !pTitle.isEmpty();
		aTitle = Optional.of(pTitle);
	}

	/**
	 * set artist of a song
	 * @param pArtist
	 * @pre pArtist != null
	 */
	public void setArtist(Artist pArtist)
	{
		assert pArtist!=null;
		aArtist = Optional.of(pArtist);
	}
	
	/**
	 * set genre of a song
	 * @param pGenre
	 * @pre pGenre != null
	 */
	public void setGenre(Genre pGenre)
	{
		assert pGenre!=null;
		aGenre = Optional.of(pGenre);
	}
	
	/**
	 * set duration of a song
	 * @param pDuration
	 * @pre pDuration != null
	 */
	public void setDuration(int pDuration)
	{
		aDuration = Optional.of(pDuration);
	}

	/**
	 * @param tag Name of the tag.
	 * @return tag in T as string or empty string if can't cast
	 * @pre Arguments are not null.
	 */
	public String getTag(String pTag) {
		try 
		{
			// Note: aTags is Optional<Map>, get Map first, then get value of the key
			return aTags.get().get(pTag);
		} 
		catch (ClassCastException |  NullPointerException e) 
		{
			return "Wrong";
		}
	}

	//method for optional tag and absent value
	/**
	 * @param tag     Name of the tag.
	 * @param content Content of tag in T.
	 * @pre Arguments are not null.
	 */
	public void editTag(Optional<String> pTag, Optional<String> pValue)
	{
		if(pTag.isPresent())
		{
			aTags.get().put(pTag.get(), pValue.get());
		}
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aArtist == null) ? 0 : aArtist.hashCode());
		result = prime * result + aDuration.hashCode();
		result = prime * result + ((aFile == null) ? 0 : aFile.hashCode());
		result = prime * result + ((aGenre == null) ? 0 : aGenre.hashCode());
		result = prime * result + ((aTags == null) ? 0 : aTags.hashCode());
		result = prime * result + ((aTitle == null) ? 0 : aTitle.hashCode());
		return result;
	}


	/**
	 * Compare two songs by their file,
	 * the same way we'd compare two strings
	 * returns boolean for whether the files are the same
	 * @param pObj: the other song the songs to be compared with
	 */
	@Override
	//Detecting duplicate Songs with same file criteria, overriding equals
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Song other = (Song) obj;
		if (aFile == null)
		{
			if (other.aFile != null)
				return false;
		}
		else if (!aFile.equals(other.aFile))
			return false;
	
		return true;
	}

	//This method compares two Songs to check for duplicates using criteria same artist and same title 
	public boolean compareTitleArtist(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Song other = (Song) obj;
		if (aTitle == null)
		{
			if (other.aTitle != null)
				return false;
		}
		else if (!aTitle.equals(other.aTitle))
			return false;
		if (aArtist== null)
		{
			if (other.aArtist != null)
				return false;
		}
		else if (!aArtist.equals(other.aArtist))
			return false;
	
		return true;
	}


	/**
	 * checks if a song is valid
	 * @return boolean - true if the song is valid, false otherwise
	 */
	public boolean isValidSong()
	{
		String filepath = aFile.toString();
		File f = new File(filepath);
		if (f.exists()) 
		{
			return true;
		}
		else 
		{
			return false;
		}
	}


	/**
	 * to check a song's validity before creating it
	 * @param pFilePath
	 * @pre pFilePath != null
	 * @return boolean - true if the file path is valid, false otherwise
	 */
	private static boolean isValidCreate(String pFilePath)
	{
		assert(pFilePath != null) : "Must pass valid string file path";
		String filepath = pFilePath;
		File f = new File(filepath);
		if (f.exists()) 
		{
			return true;
		}
		else 
		{
			return false;
		}
	}

	/**
	 * to check if the extention of a file path is valid
	 * @param pFilePath
	 * @pre pFilePath != null
	 * @return boolean - true if the extention format is valid, false otherwise
	 */
	public static boolean isValidExtension(String pFilePath)
	{
		assert(pFilePath != null);
		String[] l = pFilePath.split("\\.");
		int s = l.length;
		String ext = l[s-1];
		return ext.equals("mp3");
	}

}








