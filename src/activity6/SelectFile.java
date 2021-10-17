package activity6;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SelectFile extends HBox
{

	FileChooser fileChooser = new FileChooser();
	Button button = new Button("Add Song");
	private Model aModel;
	     
		/**
		 * @param pModel
		 * @param pStage
		 * @pre pModel != null && pStage != null
		 */
		SelectFile(Model pModel, Stage pStage)
		{
			assert pModel != null && pStage != null;

			aModel = pModel;
			getChildren().add(button);
			
			button.setOnAction(new EventHandler<ActionEvent>() {
				// want to check if 'cancel' was triggered, will return nullfile
	            public void handle(ActionEvent event) {
	            	File selectedFile = fileChooser.showOpenDialog(pStage);
	            	if(selectedFile !=null) {
						Song pSong = new Song(selectedFile, selectedFile.getName());
						aModel.setItem(pSong);}
	            }
	        });
		}
}
