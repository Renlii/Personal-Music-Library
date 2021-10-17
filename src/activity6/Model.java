package activity6;

import java.util.ArrayList;

public class Model
{
	private ArrayList<Observer> aObservers = new ArrayList<Observer>();
	private Playable aPlayable;

	public void addObserver(Observer pObserver)
	{
		aObservers.add(pObserver);
	}

	private void addNotification()
	{
		for (Observer observer : aObservers)
		{
			observer.addedItem(aPlayable);
		}

	}

	private void removeNotification(int itemIndex)
	{

		for (Observer observer : aObservers)
		{
			observer.removedItem(itemIndex);
		}
	}
	
	/**
	 * @param index
	 * @pre index>=0
	 */
	void deleteItem(int index)
	{
		assert index >= 0;
		removeNotification(index);
	}

	/**
	 * @param pPlayable
	 * @pre pPlayable != null
	 */
	void setItem(Playable pPlayable)
	{
		assert pPlayable != null;
		aPlayable = pPlayable;
		addNotification();
	}

}