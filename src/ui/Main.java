package ui;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * @version September 21th 2020
 * @author 
 *
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception{
		try {
			Parent root=FXMLLoader.load(getClass().getResource("PrincipalWindow.fxml"));
			Scene scene= new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
