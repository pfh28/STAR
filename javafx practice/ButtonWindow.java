import javafx.application.Application;														//parent class
import javafx.event.ActionEvent;
import javafx.event.EventHandler;															//interface
import javafx.scene.Scene;																	//the content pane
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;																	//the window

public class ButtonWindow extends Application implements EventHandler<ActionEvent>			//interface only necessary for classic setup
{
	Scene scene;																			//label for content pane
	VBox layout;																			//layout
	Button classicButton,innerButton,lambdaButton;
	
	public static void main(String[] args)
	{
		launch(args);																		//calls parent class method for setup
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception									//method called from launch
	{
		primaryStage.setTitle("Title of the Window");										//sets window label
		
		classicButton = new Button();														//creates a button
		classicButton.setText("Classic setup");
		classicButton.setOnAction(this);													//calls listener in this class
		
		innerButton = new Button();															//creates a button
		innerButton.setText("anonomys inner class");
		innerButton.setOnAction(new EventHandler<ActionEvent>()								//creates a listener
			{@Override
				public void handle(ActionEvent event)
				{
					System.out.println("I am an anonomys inner class");
				}
			});
		
		lambdaButton = new Button();														//creates a button
		lambdaButton.setText("lambda");
		lambdaButton.setOnAction(e -> System.out.println("Lambda button"));					//uses lambda notation to execute
		
		layout = new VBox();																//creates layout
		
		layout.getChildren().add(classicButton);											//adds items to layout
		layout.getChildren().add(innerButton);
		layout.getChildren().add(lambdaButton);
		
		scene = new Scene(layout,300,250);													//adds layout to scene
		primaryStage.setScene(scene);														//adds scene to stage
		primaryStage.show();																//shows stage
	}
	
	@Override
	public void handle(ActionEvent event)													//action listener
	{
		if(event.getSource() == classicButton)												//specifies which button
		{
			System.out.println("Swing style action event");
		}
	}
}