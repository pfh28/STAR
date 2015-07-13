import javafx.application.Application;							//parent class
import javafx.event.ActionEvent;
import javafx.event.EventHandler;								//interface
import javafx.scene.Scene;										//the content pane
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import javafx.geometry.*;

public class Confirmation extends Application
{
	Scene scene;												//variable declarations
	Button button;
	Label label;
	VBox layout;
	
	public static void main (String[] args)
	{
		launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception
	{
		button = new Button("open popup");						//creats button and label
		button.setOnAction(e -> 
		{
			boolean result = DialougeBox.display("are you sure","are you sure?");	//action for button
			System.out.println(result);
		});
		label = new Label("this is a test");
		
		layout = new VBox(20);									//makes layout
		layout.getChildren().addAll(button,label);
		
		scene = new Scene(layout, 300,200);						//creates scene
		
		primaryStage.setScene(scene);							//sets up window
		primaryStage.setTitle("multi-window");
		primaryStage.show();
	}
}