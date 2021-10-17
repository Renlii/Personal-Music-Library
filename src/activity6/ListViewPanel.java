package activity6;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

public class ListViewPanel extends HBox implements Observer
{
	private Model aModel;
	private Library aLibrary;
	private ListView<Playable> aListView = new ListView<Playable>();

	/**
	 * @param pModel
	 * @param pLibrary
	 * @pre pModel!=null && pLibrary != null
	 */
	public ListViewPanel(Model pModel, Library pLibrary)
	{
		assert pModel != null && pLibrary != null;
		aModel = pModel;
		aLibrary = pLibrary;
		aModel.addObserver(this);

		// init view of library
		aListView = createLibraryView(aLibrary);
		getChildren().add(aListView);

	}

	/*
	 * Encapsulates the view of a collection of Playable objects as a list view.
	 */
	/**
	 * @param pPlayables
	 * @pre pPlayables != null
	 */
	private static ListView<Playable> createLibraryView(Iterable<Playable> pPlayables)
	{
		assert pPlayables != null;
		ObservableList<Playable> list = FXCollections.observableArrayList();
		for (Playable playable : pPlayables)
		{
			list.add(playable);
		}
		return new ListView<>(list);
	}

	protected ListView<Playable> getListView()
	{
		return this.aListView;
	}

	/**
	 * @pre pPlayable != null
	 */
	@Override
	public void addedItem(Playable pPlayable)
	{
		assert pPlayable != null;
		aLibrary.addItem(pPlayable);
		getChildren().remove(aListView);
		aListView = createLibraryView(aLibrary);
		getChildren().add(aListView);
	}

	/**
	 * @pre itemIndex>=0
	 */
	@Override
	public void removedItem(int itemIndex)
	{
		assert itemIndex >= 0;
		aLibrary.removeItem(itemIndex);
		getChildren().remove(aListView);
		aListView = createLibraryView(aLibrary);
		getChildren().add(aListView);
	}
}