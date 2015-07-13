import javafx.application.Application;							//parent class
import javafx.event.ActionEvent;
import javafx.event.EventHandler;								//interface
import javafx.scene.Scene;										//the content pane
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MultiScene extends Application
{
	Scene scene1,scene2;										//variable declarations
	Label label;
	Button button1,button2;
	VBox layout1;
	StackPane layout2;
	Stage window;
	
	
	public static void main(String[] args)						//main calls parent
	{
		launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception		//parent calls start
	{
		window = primaryStage;									//class variable to make stage more accessable
		buildScenes();											//calls method to build scenes
		
		window.setTitle("multi-Scene");							//sets up window
		window.setScene(scene1);
		window.show();
	}
	
	private void buildScenes()
	{
		layout1 = new VBox(20);									//code for scene 1
		
		Label label = new Label("this is the first scene");
		button1 = new Button("go to scene 2");
		button1.setOnAction(e -> window.setScene(scene2));
		
		layout1.getChildren().addAll(label,button1);
		scene1 = new Scene(layout1,200,200);
		
		
		layout2 = new StackPane();								//code for scene 2
		button2 = new Button("go to scene 1");
		button2.setOnAction(e -> window.setScene(scene1));
		
		layout2.getChildren().add(button2);
		scene2 = new Scene(layout2,600,300);
	}
}