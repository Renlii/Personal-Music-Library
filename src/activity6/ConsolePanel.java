package activity6;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

/**
 * Panel for displaying the console
 */
public class ConsolePanel extends HBox implements Observer
{

	private Model aModel;
	private Library aLibrary;
	private Label aLabel;

	private ArrayList<Playable> list = new ArrayList<>();
	private ObservableList<ConsoleObj> aConsoleObjs = FXCollections.observableArrayList();

	/**
	 * The immutable inner class ConsoleObj contains the systemTime, action, and the playable being Added/Removed
	 */
	private static class ConsoleObj
	{
		private enum Operation
		{
			ADD, REMOVE
		}

		String aSystemTime;
		Operation aOperation;
		Playable aPlayable;

		/**
		 * @param pOperation
		 *            Operation to use
		 * @param pPlayable
		 *            Playable that this operation was used on
		 * @pre pOperation != null && pPlayable != null
		 */
		private ConsoleObj(Operation pOperation, Playable pPlayable)
		{
			assert pOperation != null && pPlayable != null;

			aOperation = pOperation;
			aPlayable = pPlayable;
			aSystemTime = java.time.LocalTime.now().toString();
		}

		/**
		 * @return description of this ConsoleObj
		 */
		@Override
		public String toString()
		{
			return String.format("[%s] %s %s", aSystemTime, aOperation.toString(), aPlayable.description());
		}
	}

	/**
	 * @param pModel
	 *            Model to be used
	 * @param pLibrary
	 *            Library to be used
	 * @pre pLibrary != null && pModel != null
	 **/
	public ConsolePanel(Model pModel, Library pLibrary)
	{
		// Setting attributes and checking assertions
		assert pModel != null && pLibrary != null;

		aModel = pModel;
		aLibrary = pLibrary;

		aModel.addObserver(this);
		aLabel = new Label();

		for (Playable playable : pLibrary)
		{
			list.add(playable);
		}

		// Setting console dimensions
		getChildren().add(aLabel);
		aLabel.setText("Console:");
	}

	/**
	 * @pre itemIndex >= 0
	 */
	@Override
	public void removedItem(int itemIndex)
	{
		assert itemIndex >= 0;
		ConsoleObj remove = new ConsoleObj(ConsoleObj.Operation.REMOVE, list.get(itemIndex));
		aConsoleObjs.add(remove);
		aLabel.setText(remove.toString());
		System.out.println(remove.toString());
		list.remove(itemIndex);
	}

	/**
	 * @pre pPlayable != null
	 */
	@Override
	public void addedItem(Playable pPlayable)
	{
		assert pPlayable != null;
		ConsoleObj add = new ConsoleObj(ConsoleObj.Operation.ADD, pPlayable);
		aConsoleObjs.add(add);
		aLabel.setText(add.toString());
		System.out.println(add.toString());
		list.add(pPlayable);
	}
	
	//getter for JUnit testing purposes
	public String getLatestMessage() {
		assert aConsoleObjs.size()>0;
		return aConsoleObjs.get(aConsoleObjs.size()-1).toString();
	}
	
}
