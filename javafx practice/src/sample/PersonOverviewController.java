package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class PersonOverviewController
{
    @FXML private TableView<Person> personTable;                        //components attached to scene
    @FXML private TableColumn<Person, String> firstNameColumn;
    @FXML private TableColumn<Person, String> lastNameColumn;
    @FXML private Label firstNameLabel;
    @FXML private Label lastNameLabel;
    @FXML private Label streetLabel;
    @FXML private Label postalCodeLabel;
    @FXML private Label cityLabel;
    @FXML private Label birthdayLabel;

    private Main mainApp;

    public PersonOverviewController(){}

    private void showPersonDetails(Person person)
    {
        if(person != null)                                          //diaplays data of selected person
        {
            firstNameLabel.setText(person.getFirstName());
            lastNameLabel.setText(person.getLastName());
            streetLabel.setText(person.getStreet());
            postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
            cityLabel.setText(person.getCity());
            birthdayLabel.setText(DateUtil.format(person.getBirthday()));
        }else
        {
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");
            birthdayLabel.setText("");
        }

    }

    @FXML private void initialize()                                         //handles item selection
    {
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        showPersonDetails(null);

        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldvalue, newValue) -> showPersonDetails(newValue));
    }

    public void setMainApp(Main mainApp)
    {
        this.mainApp = mainApp;

        personTable.setItems(mainApp.getPersonData());
    }

    @FXML private void handleDeletePerson()                                     //handles deletion
    {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if(selectedIndex >= 0)
            personTable.getItems().remove(selectedIndex);
        else                                                                    //error for no selection
        {
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");
            alert.showAndWait();
        }
    }

    @FXML private void handleNewPerson()                                        //deals with adding people
    {
        Person tempPerson = new Person();
        boolean okClicked = mainApp.showPersonEditDialouge(tempPerson);
        if(okClicked)
            mainApp.getPersonData().add(tempPerson);
    }

    @FXML private void handleEditPerson()                                       //deals with editing people
    {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if(selectedPerson != null)
        {
            boolean okClicked = mainApp.showPersonEditDialouge(selectedPerson);
            if(okClicked)
                showPersonDetails(selectedPerson);
        }else
        {
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person from the table");

            alert.showAndWait();
        }

    }
}
