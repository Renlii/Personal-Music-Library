package activity3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents the musician or musical formation
 * that performed the song.
 */
public class Artist
{
	

	private static List<Artist> ArtistList = new ArrayList<>();
	
	private final String aName;
	private final List<String> aAlternativeNames = new ArrayList<>();
	
	/**
	 * private constructor to create an Artist with artistName, and a list of alternative names
	 * @param artistName
	 * @param pAlternativeNames
	 * @pre pArtistName != null
	 * @pre pAlternativeNames != null
	 */
	private Artist(String pArtistName, List<String> pAlternativeNames) 
	{
		assert(pArtistName != null) : "Must pass valid string as artist name";
		assert(pAlternativeNames != null) : "Must pass valid List<String>";
		aName = pArtistName;
		
		for (String s : pAlternativeNames) 
		{
			aAlternativeNames.add(s);
		}
		
	}
	
	
	/** 
	 * get an artist if it is created, else create it and return it
	 * @pre pArtistName != null
	 */
	public static Artist getArtist(String pArtistName) 
	{
		assert (pArtistName != null) : "Must enter valid string";
		// remove trailing whitespace and replace double space to single space
		pArtistName = pArtistName.trim().replaceAll("\\s+", " ");
		// remove spaces if hyphen is in between
		pArtistName = pArtistName.replaceAll(" - ", "-");
		for (Artist artist: ArtistList) 
		{
			if (pArtistName.equalsIgnoreCase(artist.aName)) 
			{
				return artist;
			}
			else 
			{
				for (String s : artist.aAlternativeNames) 
				{
					if (pArtistName.equalsIgnoreCase(s)) 
					{
						return artist;
					}
				}
			}
		}
		List<String> alternativeNames = new ArrayList<>();
		generateAlternatives(pArtistName, alternativeNames);
		Artist newArtist = new Artist(pArtistName, alternativeNames);
		ArtistList.add(newArtist);
		return newArtist;
	}
	
	/**
	 * generate list of alternative names for artist
	 * @param pName
	 * @param pAlternativeNames
	 * @pre pName != null
	 * @pre pAlternativeNames != null
	 */
	private static void generateAlternatives(String pName, List<String> pAlternativeNames) 
	{
		assert(pName != null) : "must pass valid string for name";
		assert(pAlternativeNames != null) : "Must pass valid List<String> for alternative names";
		// swap space for hyphen
		pAlternativeNames.add(pName.replaceAll("\\s", "-"));
		// swap hyphen for space
		pAlternativeNames.add(pName.replaceAll("-", " "));
		// no separators
		pAlternativeNames.add(pName.replaceAll("\\s", "").replaceAll("-", ""));
	}
	
	/**
	 * get a list of artists
	 * @return Collections.unmodifiableList(ArtistList)
	 */
	public List<Artist> getArtistList() 
	{
		return Collections.unmodifiableList(ArtistList);
	}
	
	/**
	 * get name of artist
	 * @pre pArtist != null
	 */
	public String getName(Artist pArtist) 
	{
		assert (pArtist != null): "must pass valid Artist object";
		return pArtist.aName;
	}
	
	/**
	 * get list of alternative names
	 * @pre pArtist != null
	 */
	public List<String> getAlternativeNames(Artist pArtist) 
	{
		assert (pArtist != null) : "must pass valid Artist object";
		return pArtist.aAlternativeNames;
	}

	
	@Override
	//provids a numeric representation of an object's contents.
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aAlternativeNames == null) ? 0 : aAlternativeNames.hashCode());
		result = prime * result + ((aName == null) ? 0 : aName.hashCode());
		return result;
	}

	@Override
	//Checks if two seperate objects are equal 
	//Prevents client codes to create two seperate objects that are equal 
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artist other = (Artist) obj;
		if (aAlternativeNames == null)
		{
			if (other.aAlternativeNames != null)
				return false;
		}
		else if (!listEquals(other.aAlternativeNames, this.aAlternativeNames))
			return false;
		if (aName == null)
		{
			if (other.aName != null)
				return false;
		}
		else if (!aName.equals(other.aName))
			return false;
		return true;
	}
	
	//Used to determine if the aAlternativeNames List attributes are equal 
	/**
	 * @pre: list1 != null && list2 != null
	 */
	private boolean listEquals(List<String> list1, List<String> list2) 
	{
		assert(list1 != null && list2 != null);
		boolean same = true;
		if(list1.size()!=list2.size()) 
		{
			return same;
		}
		int i=0;
		for(String s: list1) 
		{
			if(!s.equals(list2.get(i)))
				same = false;
			i++;
		}
		return same;
	}
	
}
