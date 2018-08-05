package com.sda.javafx.controller;

import com.sda.javafx.Main;
import com.sda.javafx.model.Person;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class PersonController {

    private Main main;

    @FXML
    private TableView<Person> personTable;

    @FXML
    private Label firstNamelabel;

    @FXML
    private Label lastNamelabel;

    @FXML
    private Label streetLabel;

    @FXML
    private Label citylabel;

    @FXML
    private Label postalCodelabel;

    @FXML
    private Label telephoneLabel;

    @FXML
    private TableColumn<Person, String> firstNameCol;

    @FXML
    private TableColumn<Person, String> lastNameCol;

    @FXML
    private Button newButton;

    @FXML
    public void handleNewPerson(){
        System.out.println("testest");
        this.main.loadNewPerson();
    }


    @FXML
    public void handlePersonEdit(){

        Person selectPerson = personTable.getSelectionModel().getSelectedItem();
        if(selectPerson != null) {
            System.out.println(selectPerson.getName());
            this.main.loadPersonEdit(selectPerson);
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getStage());
            alert.setTitle("Brak ososby");
            alert.setContentText("Nikt nie został wybrany");
            alert.showAndWait();
        }
    }

    @FXML
    public void handleDeletePerson(){

        int index = personTable.getSelectionModel().getSelectedIndex();
        System.out.println(index);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Delete " + index + " ?", ButtonType.YES,
                ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {

            if(index >= 0) {
                personTable.getItems().remove(index);
            }
        }
    }

    @FXML
    public void initialize(){
        firstNameCol.setCellValueFactory(cell -> cell.getValue().nameProperty());
        lastNameCol.setCellValueFactory(cell -> cell.getValue().lastNameProperty());

        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldField, newField) -> showPersonDetails(newField));
    }

    private void showPersonDetails(Person person){
        firstNamelabel.setText(person.getName());
        lastNamelabel.setText(person.getLastName());
    }

    public void setMain(Main main) {
        this.main = main;
        personTable.setItems(this.main.getPersonList());
    }
}
