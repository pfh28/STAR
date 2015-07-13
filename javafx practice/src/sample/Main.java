package sample;

import java.io.IOException;
import java.io.File;                    // I guess?
import java.util.prefs.Preferences;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.Image;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.Marshaller;

public class Main extends Application
{
    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Person> personData = FXCollections.observableArrayList();

                                                                                        //block deals with person list
    public Main()
    {                                                                                   //adds sample data
        personData.add(new Person("Hans", "Schumacker"));
        personData.add(new Person("Drexel", "Shaft"));
        personData.add(new Person("Cal","Hood"));
    }

    public ObservableList<Person> getPersonData()
    {
        return personData;
    }


                                                                                        //block deals with window
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Address App");

        this.primaryStage.getIcons().add(new Image("file:C:\\Users\\Patrick\\Documents\\repositories\\STAR\\javafx practice\\resources\\images\\icon.png"));

        initRootLayout();

        showPersonOverview();
    }

    public void initRootLayout()
    {
 try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("\\RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
        }catch(IOException e)
            {e.printStackTrace();}

        File file = getPersonFilePath();
        if (file != null)
            loadPersonDataFromFile(file);
    }

    public void showPersonOverview()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("\\PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            rootLayout.setCenter(personOverview);

            PersonOverviewController controller = loader.getController();
            controller.setMainApp(this);
        }catch(IOException e)
        {e.printStackTrace();}
    }

    public boolean showPersonEditDialouge(Person person)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("\\PersonEditDialouge.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialougeStage = new Stage();
            dialougeStage.setTitle("Edit person");
            dialougeStage.initModality(Modality.WINDOW_MODAL);
            dialougeStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialougeStage.setScene(scene);

            PersonEditDialougeController controller = loader.getController();
            controller.setDialougeStage(dialougeStage);
            controller.setPerson(person);

            dialougeStage.showAndWait();

            return controller.isOkClicked();
        }catch(IOException e)
        {
            e.printStackTrace();
            return false;
        }

    }

    public Stage getPrimaryStage()
    {
        return primaryStage;
    }

    public File getPersonFilePath()
    {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        String filePath = prefs.get("filePath",null);
        if (filePath != null)
            return new File(filePath);
        else
            return null;
    }

    public void setPersonFilePath(File file)
    {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        if(file != null)
        {
            prefs.put("filePath",file.getPath());

            primaryStage.setTitle("AddressApp - " + file.getName());
        }else
            prefs.remove("filePath");

        primaryStage.setTitle("AddressApp");
    }

    public void loadPersonDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(PersonListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            // Reading XML from the file and unmarshalling.
            PersonListWrapper wrapper = (PersonListWrapper) um.unmarshal(file);

            personData.clear();
            personData.addAll(wrapper.getPersons());

            // Save the file path to the registry.
            setPersonFilePath(file);

        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data");
            alert.setContentText("Could not load data from file:\n" + file.getPath());

            alert.showAndWait();
        }
    }

    public void savePersonDataToFile(File file)
    {
        try
        {
            JAXBContext context = JAXBContext.newInstance(PersonListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            PersonListWrapper wrapper = new PersonListWrapper();
            wrapper.setPersons(personData);

            m.marshal(wrapper,file);

            setPersonFilePath(file);
        }catch(Exception E)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save data");
            alert.setContentText("Could not save data ro file:\n"+file.getPath());

            alert.showAndWait();
        }

    }
}
