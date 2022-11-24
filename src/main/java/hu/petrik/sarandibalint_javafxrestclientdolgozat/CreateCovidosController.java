package hu.petrik.sarandibalint_javafxrestclientdolgozat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class CreateCovidosController extends Controller{

    @FXML
    private TextField nameField;
    @FXML
    private Spinner<Integer> oltasField;
    @FXML
    private Spinner<Integer> ageField;
    @FXML
    private Button submitButton;
    @FXML
    private CheckBox yes;
    @FXML
    private CheckBox no;

    @FXML
    private void initialize() {
        SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 200, 0);
        ageField.setValueFactory(valueFactory);
        SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 200, 18);
        oltasField.setValueFactory(valueFactory2);
    }

    @FXML
    public void submitClick(ActionEvent actionEvent) {
        String name = nameField.getText().trim();
        int oltas = oltasField.getValue();
        int age = ageField.getValue();
        boolean elkapta = false;
        if (name.isEmpty()) {
            warning("Name is required");
            return;
        }
        if ((yes.isSelected() && no.isSelected() ) || (yes.isSelected() == false && no.isSelected() == false)) {
            warning("Egyet válassz!");
            return;
        }else if (yes.isSelected()){
            elkapta = true;
        } else if (no.isSelected()) {
            elkapta = false;
        }
        Person newPerson = new Person(0, name, oltas, age, elkapta);
        Gson converter = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String json = converter.toJson(newPerson);
        try {
            Response response = RequestHandler.post(App.BASE_URL, json);
            if (response.getResponseCode() == 201) {
                warning("Person added!");
                nameField.setText("");
                oltasField.getValueFactory().setValue(0);
                ageField.getValueFactory().setValue(30);
            } else {
                String content = response.getContent();
                error(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
