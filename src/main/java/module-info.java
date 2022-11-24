module com.example.sarandibalint_javafxrestclientdolgozat {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens hu.petrik.sarandibalint_javafxrestclientdolgozat to javafx.fxml, com.google.gson;
    exports hu.petrik.sarandibalint_javafxrestclientdolgozat;
}