import javafx.application.Application;							//parent class
import javafx.event.ActionEvent;
import javafx.event.EventHandler;								//interface
import javafx.scene.Scene;										//the content pane
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import javafx.geometry.*;										//positioning?

public class DialougeBox
{
	static boolean answer;
	
	public static boolean display(String title, String message)
	{
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		
		Label label = new Label();
		label.setText(message);
		
		Button yesBotton = new Button("yes");
		yesBotton.setOnAction(e ->
		{
			answer = true;
			window.close();
		});
		
		Button noButton = new Button("no");
		noButton.setOnAction(e ->
		{
			answer = false;
			window.close();
		});
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label,yesBotton,noButton);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		
		return answer;
	}
}