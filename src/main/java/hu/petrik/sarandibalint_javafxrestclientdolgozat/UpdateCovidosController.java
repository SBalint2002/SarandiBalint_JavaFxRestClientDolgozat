package hu.petrik.sarandibalint_javafxrestclientdolgozat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class UpdateCovidosController extends Controller{

    @FXML
    private TextField nameField;
    @FXML
    private Spinner<Integer> ageField;

    private Person person;
    @FXML
    private Button updateButton;
    @FXML
    private Spinner<Integer> oltasField;
    @FXML
    private CheckBox yes;
    @FXML
    private CheckBox no;

    public void setPerson(Person person) {
        this.person = person;
        nameField.setText(this.person.getName());
        oltasField.getValueFactory().setValue(this.person.getOltas());
        ageField.getValueFactory().setValue(this.person.getAge());
        if (this.person.isElkapta()){
            yes.selectedProperty().setValue(true);
            no.selectedProperty().setValue(false);
        }else{
            yes.selectedProperty().setValue(false);
            no.selectedProperty().setValue(true);
        }
    }

    @FXML
    private void initialize() {
        SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 200, 0);
        ageField.setValueFactory(valueFactory);
        SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 200, 18);
        oltasField.setValueFactory(valueFactory2);
    }

    @FXML
    public void updateClick(ActionEvent actionEvent) {
        String name = nameField.getText().trim();
        int oltas = oltasField.getValueFactory().getValue();
        int age = ageField.getValue();
        boolean elkapta = false;
        if (name.isEmpty()) {
            warning("Name is required");
            return;
        }
        if ((yes.isSelected() && no.isSelected() ) || (!yes.isSelected() && !no.isSelected())) {
            warning("Egyet válassz!");
            return;
        }else if (yes.isSelected()){
            elkapta = true;
        } else if (no.isSelected()) {
            elkapta = false;
        }
        this.person.setName(name);
        this.person.setOltas(oltas);
        this.person.setAge(age);
        this.person.setElkapta(elkapta);
        Gson converter = new Gson();
        String json = converter.toJson(this.person);
        try {
            String url = App.BASE_URL + "/" + this.person.getId();
            Response response = RequestHandler.put(url, json);
            if (response.getResponseCode() == 200) {
                Stage stage = (Stage) this.updateButton.getScene().getWindow();
                stage.close();
            } else {
                String content = response.getContent();
                error(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
