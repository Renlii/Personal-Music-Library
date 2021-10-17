package activity6;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class TestModel
{
	private Model aModel = new Model();
	private TestObserver testObserver = new TestObserver();
	
	/**
	 * Simple observer to test Model's methods. Note that added and removed
	 * are both accessible from TestModel
	 */
	private class TestObserver implements Observer {
		int added = 0;
		int removed = 0;
		//so we can have a list of the removed indexes
		ArrayList<Integer> removedIndex = new ArrayList<>();
		
		@Override
		public void addedItem(Playable pPlayable)
		{
			added++;
		}

		@Override
		public void removedItem(int itemIndex)
		{
			//this would just be the sums of the indexes we removed..
			//I would do the following 
			removedIndex.add(itemIndex);
			
			removed+=itemIndex;
		}
	}
	
	/**
	 * Getters for the private fields aObservers, and Playable in Model
	 */
	private ArrayList<Observer> getObservers() {
		try {
			Field observersField = Model.class.getDeclaredField("aObservers");
			observersField.setAccessible(true);
			
			return (ArrayList<Observer>) observersField.get(aModel);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
			return null; //Will never return this
		}
	}
	
	private Playable getPlayable() {
		try {
			Field playableField = Model.class.getDeclaredField("aPlayable");
			playableField.setAccessible(true);
			//we have some unchecked type casts,
			return (Playable) playableField.get(aModel);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
			return null; //Will never return this
		}
	}
	
	/**
	 * Resets aModel + testObserver
	 */
	@BeforeEach
	private void reset()
	{
		aModel = new Model();
		testObserver = new TestObserver();
	}
	
	/**
	 * Tests that all Observers in aObeservers are notified
	 */
	@Test
	public void Test_addNotification()
	{
		int oracle = 10;
		try {
			//Getting method
			Method addNotification = Model.class.getDeclaredMethod("addNotification", null);
			addNotification.setAccessible(true);
			
			//Adding an Observer to the model			
			for (int i = 0; i < 10; i++) getObservers().add(testObserver);
			
			//Testing that method performs as required
			addNotification.invoke(aModel);
			assertEquals(testObserver.added,oracle); //To check for off by 1 errors
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Tests that all Observers in aObeservers are notified
	 */
	@Test
	public void Test_removeNotification()
	{
		try {
			int oracle = 2*10;
			//Getting method
			Method removeNotification = Model.class.getDeclaredMethod("removeNotification", int.class);
			removeNotification.setAccessible(true);
			
			//Adding an Observer to the model
			for (int i = 0; i < 10; i++) getObservers().add(testObserver);
			
			//Testing that method performs as required
			removeNotification.invoke(aModel,2);
			assertEquals(testObserver.removed,oracle);	//Since each observer is the same, 
		} catch (Exception e) {						//we should have removed = 2*10=20
			e.printStackTrace();
			fail();
		}
	}
	
	
	/**
	 * Tests that deleteItem calls removeNotification on the
	 * correct index
	 */	
	@Test
	public void Test_deleteItem()
	{		
		int oracle = 5;
		for (int i = 0; i < 10; i++) getObservers().add(testObserver);
		aModel.deleteItem(oracle);
		
		oracle = oracle *10;
		assertEquals(testObserver.removed, oracle);
	}
	
	/**
	 * Tests that the correct item is set and addNotification 
	 * is called
	 */
	@Test
	public void Test_setItem() {
		
		int oracle = 10;
		Playlist testPlaylist = new Playlist("OurPlaylist");
		for (int i = 0; i < oracle; i++) getObservers().add(testObserver);
		
		aModel.setItem(testPlaylist);
		
		assertEquals(testPlaylist, (Playlist) getPlayable());
		
		assertEquals(testObserver.added,oracle);
	}
}
