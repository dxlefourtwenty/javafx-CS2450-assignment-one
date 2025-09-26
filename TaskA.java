// Dale Peligro
// CS2450
// Assignment 1
// 09/25/2025

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

class skateboardProduct {
    String name;
    int price;

    public skateboardProduct(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String toString() {
        return name + " $" + Integer.toString(price);
    }
}

public class TaskA extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("TaskA");
        Label decks = new Label("Decks");
        Label truckAssemblies = new Label("Truck Assemblies");
        Label wheels = new Label("Wheels");

        VBox pageBody = new VBox();
        GridPane menuLayout = new GridPane();

        // Create skateboardProduct objects
        skateboardProduct deckOne = new skateboardProduct("The Master Thrasher", 60);
        skateboardProduct deckTwo = new skateboardProduct("The Dictator", 45);
        skateboardProduct deckThree = new skateboardProduct("The Street King", 50);

        skateboardProduct truckOne = new skateboardProduct("7.75-inch axle", 35);
        skateboardProduct truckTwo = new skateboardProduct("8-inch axle", 40);
        skateboardProduct truckThree = new skateboardProduct("8.5-inch axle", 45);

        skateboardProduct wheelOne = new skateboardProduct("51 mm", 20);
        skateboardProduct wheelTwo = new skateboardProduct("55 mm", 22);
        skateboardProduct wheelThree = new skateboardProduct("58 mm", 24);
        skateboardProduct wheelFour = new skateboardProduct("61 mm", 28);

        ListView<skateboardProduct> decksList = new ListView<skateboardProduct>();
        decksList.getItems().addAll(deckOne, deckTwo, deckThree);
        ListView<skateboardProduct> trucksList = new ListView<skateboardProduct>();
        trucksList.getItems().addAll(truckOne, truckTwo, truckThree);
        ListView<skateboardProduct> wheelsList = new ListView<skateboardProduct>();
        wheelsList.getItems().addAll(wheelOne, wheelTwo, wheelThree, wheelFour);

        // Set the child elements for each parent
        pageBody.getChildren().add(0, menuLayout);
        menuLayout.add(decks, 0, 0);
        menuLayout.add(truckAssemblies, 1, 0);
        menuLayout.add(wheels, 2, 0);
        menuLayout.add(decksList, 0, 1);
        menuLayout.add(trucksList, 1, 1);
        menuLayout.add(wheelsList, 2, 1);

        pageBody.setPadding(new Insets(10));
        pageBody.setId("page-body");

        menuLayout.setHgap(15);

        Scene scene = new Scene(pageBody);  
        scene.getStylesheets().add("TaskA.css");
        stage.setScene(scene);
        stage.show();
    }

}

