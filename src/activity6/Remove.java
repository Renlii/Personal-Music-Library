package activity6;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Remove extends HBox
{

	Button deleteButton = new Button("Remove item");
	private Model aModel;

	/**
	 * @param pModel
	 * @param pStage
	 * @param pLibrary
	 * @param pListViewPanel
	 * @pre pModel!=null && pStage!= null && pListViewPanel!=null
	 */
	Remove(Model pModel, Stage pStage, ListViewPanel pListViewPanel)
	{
		assert pModel != null && pStage != null && pListViewPanel != null;
		aModel = pModel;
		getChildren().add(deleteButton);
		deleteButton.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				final int selectedIdx = pListViewPanel.getListView().getSelectionModel().getSelectedIndex();
				if (selectedIdx != -1)
				{
					aModel.deleteItem(selectedIdx);
				}
			}
		});
	}
}
