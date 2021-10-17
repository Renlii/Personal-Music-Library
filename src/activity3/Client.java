package activity3;

import java.nio.file.InvalidPathException;

public class Client
{

	public static void main(String[] args)
	{
		
		try {
			Song s1 = new Song("test1.mp3");
			Song s2 = new Song("test2.mp3");
			Song s3 = new Song("test3.mp3");
			Song s4 = new Song("test4.mp3");
			Song s8 = new Song("test8.mp3");
			Song s9 = new Song("test9.mp3");
			
			System.out.println("all files valid\n");
		}
		catch (InvalidPathException e){
			System.out.println("invalid file(s) exist(s)\n");
		}
		System.out.println("test passed");
	}

}
