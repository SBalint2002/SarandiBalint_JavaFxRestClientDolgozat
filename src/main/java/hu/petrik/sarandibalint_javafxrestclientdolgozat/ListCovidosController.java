package hu.petrik.sarandibalint_javafxrestclientdolgozat;

import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class ListCovidosController extends Controller{

    @FXML
    private Button insertButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TableView<Person> peopleTable;
    @FXML
    private TableColumn<Person, String> nameCol;
    @FXML
    private TableColumn<Person, Integer> oltasCol;
    @FXML
    private TableColumn<Person, Integer> ageCol;
    @FXML
    private TableColumn<Person, Boolean> elkaptaCol;

    @FXML
    private void initialize() {

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        oltasCol.setCellValueFactory(new PropertyValueFactory<>("oltas"));
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
        elkaptaCol.setCellValueFactory(new PropertyValueFactory<>("elkapta"));

        Platform.runLater(() -> {
            try {
                loadPeopleFromServer();
            } catch (IOException e) {
                error("Couldn't get data from server",e.getMessage());
                Platform.exit();
            }
        });
    }

    private void loadPeopleFromServer() throws IOException {
        Response response = RequestHandler.get(App.BASE_URL);
        String content = response.getContent();
        Gson converter = new Gson();
        Person[] people = converter.fromJson(content, Person[].class);
        peopleTable.getItems().clear();
        for (Person person : people) {
            peopleTable.getItems().add(person);
        }
    }

    @FXML
    public void insertClick(ActionEvent actionEvent) {

    }

    @FXML
    public void updateClick(ActionEvent actionEvent) {

    }

    @FXML
    public void deleteClick(ActionEvent actionEvent) {

    }
}
