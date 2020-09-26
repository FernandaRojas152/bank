package ui;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

/**
 * @version September 21th 2020
 * @author 
 *
 */
public class Main extends Application {
	@SuppressWarnings("unused")
	private PrincipalWindowController principal;
	public Main() {
		principal= new PrincipalWindowController();
	}
	@Override
	public void start(Stage primaryStage) throws Exception{
		try {
			Parent root=FXMLLoader.load(getClass().getResource("PrincipalWindow.fxml"));
			Scene scene= new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("bank-flat.png")));
			primaryStage.setTitle("Bank S.A");
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
