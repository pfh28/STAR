import javafx.application.Application;							//parent class
import javafx.event.ActionEvent;
import javafx.event.EventHandler;								//interface
import javafx.scene.Scene;										//the content pane
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import javafx.geometry.*;										//positioning?

public class AlertBox
{
	public static void display(String title, String message)	//static method makes window creation easier
	{
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);		//blocks action to other windows
		window.setTitle(title);
		window.setMinWidth(250);
		
		Label label = new Label();								//creates components
		label.setText(message);
		Button closeButton = new Button("OK");
		closeButton.setOnAction(e -> window.close());
		
		VBox layout = new VBox(10);								//creates layout
		layout.getChildren().addAll(label,closeButton);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();									//waits for close before returning to caller
	}
}