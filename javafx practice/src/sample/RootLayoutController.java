package sample;

/**
 * Created by Patrick on 7/13/2015.
 */

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;

public class RootLayoutController
{
    private Main mainApp;

    public void setMainApp(Main mainApp)
    {
        this.mainApp = mainApp;
    }

    @FXML private void handleNew()                                      //creates new addressbook
    {
        mainApp.getPersonData().clear();
        mainApp.setPersonFilePath(null);
    }

    @FXML private void handleOpen()                                    //opens a saved addressbook
    {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)","*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if(file != null)
            mainApp.loadPersonDataFromFile(file);
    }

    @FXML private void handleSave()                                    //saves the current addressbook
    {
        File personFile = mainApp.getPersonFilePath();
        if(personFile != null)
            mainApp.savePersonDataToFile(personFile);
        else
            handleSaveAs();
    }

    @FXML private void handleSaveAs()                                   //saveas for current addressbook
    {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML file(*.xml)","*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

        if (file != null)
        {
            if (!file.getPath().endsWith(".xml"))
                file = new File(file.getPath() + ".xml");
            mainApp.savePersonDataToFile(file);
        }
    }

    @FXML private void handleAbout()                                   //gives informative splashScreen
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("AddressApp");
        alert.setHeaderText("About");
        alert.setContentText("Product of AddressBook Group");

        alert.showAndWait();
    }

    @FXML private void handleExit()
    {
        System.exit(0);
    }

    @FXML private void handleShowBirthdayStatistics()
    {
        mainApp.showBirthdayStatistics();
    }
}
