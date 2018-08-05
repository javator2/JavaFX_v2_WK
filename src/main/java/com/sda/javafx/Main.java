package com.sda.javafx;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.javafx.controller.PersonController;
import com.sda.javafx.controller.PersonDetails;
import com.sda.javafx.model.Person;
import com.sda.javafx.model.PersonJSON;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private Stage stage;
    private VBox layout;

    private ObservableList<Person> personList = FXCollections.observableArrayList();
    private List<PersonJSON> personJSONList = new ArrayList<PersonJSON>();

    public Main() throws IOException{
        personJSONList.add(new PersonJSON("JanTest", "Kowalski"));
        personJSONList.add(new PersonJSON("JanTest", "Kowalski"));
        personJSONList.add(new PersonJSON("Jan324", "Kowalski"));
        personJSONList.add(new PersonJSON("Jan325", "Kowalski"));
        personJSONList.add(new PersonJSON("Jan325", "Kowalski"));


        ObjectMapper mapper = new ObjectMapper();
        File filename = new File("person.json");
        filename.createNewFile();
        mapper.writeValue(filename, personJSONList);

        PersonJSON[] readorders = mapper.readValue(new File("person.json"), PersonJSON[].class);

        for(PersonJSON p:  readorders){
            System.out.println(p.getName());
            personList.add(new Person(p.getName(), p.getLastname()));
//            personListFX.add(new PersonFX(p.getName(), p.getLastName()));
        }
    }

    public Stage getStage() {
        return stage;
    }

    public ObservableList<Person> getPersonList() {
        return personList;
    }

    public static void main(String[] args) {
        launch();
    }

    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        this.stage.setTitle("Moja aplikacja w JavaFX");
        loadView();
    }

    public void loadView(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(
                    "/RootView.fxml"));
            layout = (VBox) loader.load();

            Scene scene = new Scene(layout);
            stage.setScene(scene);
            stage.show();

            PersonController controller = loader.getController();
            controller.setMain(this);

        }catch (IOException err){
            err.printStackTrace();
        }
    }

    public void loadNewPerson(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(
                    "/NewPerson.fxml"));
            VBox window = (VBox) loader.load();

            Stage editStage = new Stage();
            editStage.setTitle("edytuj osobe");
            Scene scene = new Scene(window);
            editStage.setScene(scene);
            editStage.show();

        }catch (IOException err){
            err.printStackTrace();
        }
    }

    public void loadPersonEdit(Person person){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(
                    "/PersonEdit.fxml"));
            VBox window = (VBox) loader.load();

            PersonDetails personDetails = loader.getController();
            personDetails.setPerson(person);

            Stage editStage = new Stage();
            editStage.setTitle("edytuj osobe");

            personDetails.setStage(editStage);

            Scene scene = new Scene(window);
            editStage.setScene(scene);
            editStage.show();

        }catch (IOException err){
            err.printStackTrace();
        }
    }
}
