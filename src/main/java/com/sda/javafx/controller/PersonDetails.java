package com.sda.javafx.controller;

import com.sda.javafx.model.Person;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PersonDetails {

    @FXML
    private TextField name;
    @FXML
    private TextField lastname;
    @FXML
    private TextField street;
    @FXML
    private TextField postalCode;
    @FXML
    private TextField telephoneNumber;
    @FXML
    private TextField city;

    private Person person;

    private Stage stage;

    @FXML
    public void initialize(){
        name.setText("To jest test");
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void setPerson(Person person){

        this.person = person;
        name.setText(person.getName());
        lastname.setText(person.getLastName());

    }

    public void handleOk(){

        person.setName(name.getText());
        person.setLastName(lastname.getText());
        System.out.println("Zapisz");
        this.stage.close();

    }

}
