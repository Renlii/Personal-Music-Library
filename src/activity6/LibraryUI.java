package activity6;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Mock-up of a partial user interface for the music library.
 * 
 * This code makes a simplistic use of the JavaFX library to limit the code comprehension efforts not directly related
 * to the course objectives.
 *
 */
public class LibraryUI extends Application
{
	// field to initilize the size of the pop up window
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;

	/**
	 * Launches the application.
	 * 
	 * @param pArgs
	 *            This program takes no argument.
	 */
	public static void main(String[] pArgs)
	{
		launch(pArgs);
	}

	@Override
	public void start(Stage pStage)
	{
		Library library = new Library();
		Model model = new Model();

		BorderPane root = new BorderPane();
		pStage.setTitle("TEAM 11 - LIBRARY");

		// Create button to add files to the library, with column index 0, row index 0, column span 1, row span 1
		root.setTop(new SelectFile(model, pStage));

		// Create the view of all playables at the start of command, with position (0,2)
		ListViewPanel view = new ListViewPanel(model, library);
		root.setCenter(view);

		// Create button to delete files from the library, with position (1,0)
		// root.add(new Remove(model, pStage, view), 1, 0, 1, 1);
		VBox bottom = new VBox();
		bottom.getChildren().add(new Remove(model, pStage, view));
		root.setBottom(bottom);

		// Create Console
		ConsolePanel panel = new ConsolePanel(model, library);
		bottom.getChildren().add(panel);

		// set stage
		pStage.setScene(new Scene(root, WIDTH, HEIGHT));
		pStage.show();

	}

}
