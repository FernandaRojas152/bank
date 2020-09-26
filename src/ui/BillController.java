package ui;

import java.io.FileNotFoundException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class BillController {
	private Pane view;
	
	 public Pane getPane(String name) {
			try {
				URL fileUrl = Main.class.getResource("/ui"+ name+ ".fxml");
					if(fileUrl== null) {
						throw new FileNotFoundException("Can't be found");
					}
					new FXMLLoader();
					view= FXMLLoader.load(fileUrl);
			} catch (Exception e) {
				System.out.println("Error");
			}
	    	return view;
		}
}
