package activity3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a musical genre, for example classical or 
 * jazz.
 */
public class Genre
{
	
	private static List<Genre> GenreList = new ArrayList<>();
	
	private final String aName;
	private final List<String> aSynonyms = new ArrayList<>();
	
	/**
	 * private constructor for creating a Genre with genreName, and a list of synonyms
	 * 
	 * @param genreName
	 * @param pSynonyms
	 * @pre genreName != null
	 * @pre pSynonyms != null
	 * @post creates a Genre
	 */
	private Genre(String pGenreName, List<String> pSynonyms) 
	{
		assert(pGenreName != null) : "Must pass valid string for genre name";
		assert(pSynonyms != null) : "Must pass valid List<String> of synonyms";
		aName = pGenreName;
		
		for (String s : pSynonyms) 
		{
			aSynonyms.add(s);
		}
		
	}
	
	/**
	 * returns genre name if it is already created, else create it and add it to list of genre
	 * @pre pGenreName != null
	 * @return newGenre or genre
	 */
	public static Genre getGenre(String pGenreName) 
	{
		assert (pGenreName != null) : "Must pass valid string as genre name";
		// remove trailing whitespace and replace double space to single space
		pGenreName = pGenreName.trim().replaceAll("\\s+", " ");
		// remove spaces if hyphen is in between
		pGenreName = pGenreName.replaceAll(" - ", "-");
		for (Genre genre: GenreList) 
		{
			if (pGenreName.equalsIgnoreCase(genre.aName)) 
			{
				return genre;
			}
			else 
			{
				for (String s : genre.aSynonyms) 
				{
					if (pGenreName.equalsIgnoreCase(s)) 
					{
						return genre;
					}
				}
			}
		}
		List<String> synonyms = new ArrayList<>();
		generateSynonyms(pGenreName, synonyms);
		Genre newGenre = new Genre(pGenreName, synonyms);
		GenreList.add(newGenre);
		return newGenre;
	}
	
	/**
	 * generate a list of synonyms for a genre name
	 * @param pGenreName
	 * @param pSynonyms
	 * @pre pGenreName != null
	 * @pre pSynonyms != null;
	 */
	public static void generateSynonyms(String pGenreName, List<String> pSynonyms) 
	{
		assert(pGenreName != null) : "Must pass valid string for genre name";
		assert(pSynonyms != null) : "Must pass valid List<String> of synonyms";
		// swap space for hyphen
		pSynonyms.add(pGenreName.replaceAll("\\s", "-"));
		// swap hyphen for space
		pSynonyms.add(pGenreName.replaceAll("-", " "));
		// no separators
		pSynonyms.add(pGenreName.replaceAll("\\s", "").replaceAll("-", ""));
	}
	
	/**
	 * get copy of list of genre
	 * @return Collections.unmodifiableList(GenreList)
	 */
	public List<Genre> getGenreList() 
	{
		return Collections.unmodifiableList(GenreList);
	}
	
	/**
	 * get name of genre
	 * @param pGenre
	 * @pre pGenre != null
	 */
	public String getName(Genre pGenre) 
	{
		assert (pGenre != null) : "Must pass valid Genre object";
		return pGenre.aName;
	}
	
	/**
	 * get list of alternative genre names
	 * @param pGenre != null
	 * @pre pGenre != null
	 */
	public List<String> getAlternativeNames(Genre pGenre) 
	{
		assert (pGenre != null) : "Must pass valid Genre object";
		return pGenre.aSynonyms;
	}
}
