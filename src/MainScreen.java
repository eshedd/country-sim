import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.*;

public class MainScreen extends Application {

	Text countryName;
	
    @Override
    public void start(Stage stage) {
    	// Main Pane
		StackPane mainPane = new StackPane();
		
		// Country Name Text
		countryName = new Text("Your Country");
		mainPane.getChildren().add(countryName);
		
        Scene scene = new Scene(mainPane, 640, 480);
        
        stage.setScene(scene);
        stage.setTitle("Country Sim");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}