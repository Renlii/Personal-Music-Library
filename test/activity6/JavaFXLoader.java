package activity6;
import javafx.embed.swing.JFXPanel;
/**
 * Loading this class into the JVM ensures the JavaFX toolkit
 * is available.
 */
@SuppressWarnings("unused")
public final class JavaFXLoader 
{
	private static JFXPanel aJFXPanel = new JFXPanel();
	
	private JavaFXLoader() {}
	
	public static void load() {}
}