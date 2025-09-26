// Dale Peligro
// CS2450
// Assignment 1
// 09/25/2025

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

class skateboardProduct {
    private String name;
    private int price;

    public skateboardProduct(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String toString() {
        return name + " $" + Integer.toString(price);
    }

    public int getPrice() {
        return price;
    }
}

public class TaskA extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("TaskA");
        Label decksLabel = new Label("Decks*");
        Label truckAssembliesLabel = new Label("Truck Assemblies*");
        Label wheelsLabel = new Label("Wheels*");
        Label miscLabel = new Label("Misc. (+CNTRL/CMD)");
        Label totalLabel = new Label("Total: N/A");

        VBox pageBody = new VBox();
        HBox bottomLayout = new HBox();
        GridPane menuLayout = new GridPane();

        Button showTotal = new Button("Finalize");

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

        skateboardProduct gripTape = new skateboardProduct("Grip Tape", 10);
        skateboardProduct bearings = new skateboardProduct("Bearings", 30);
        skateboardProduct riserPads = new skateboardProduct("Riser pads", 2);
        skateboardProduct nutsAndBolts = new skateboardProduct("Nuts & bolts kit", 3);

        ListView<skateboardProduct> decksList = new ListView<skateboardProduct>();
        decksList.getItems().addAll(deckOne, deckTwo, deckThree);
        ListView<skateboardProduct> trucksList = new ListView<skateboardProduct>();
        trucksList.getItems().addAll(truckOne, truckTwo, truckThree);
        ListView<skateboardProduct> wheelsList = new ListView<skateboardProduct>();
        wheelsList.getItems().addAll(wheelOne, wheelTwo, wheelThree, wheelFour);
        ListView<skateboardProduct> miscList = new ListView<skateboardProduct>();
        miscList.getItems().addAll(gripTape, bearings, riserPads, nutsAndBolts);
        miscList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Set the child elements for each parent
        pageBody.getChildren().add(0, menuLayout);
        pageBody.getChildren().add(1, bottomLayout);
        bottomLayout.getChildren().add(0, showTotal);
        bottomLayout.getChildren().add(1, totalLabel);
        menuLayout.add(decksLabel, 0, 0);
        menuLayout.add(truckAssembliesLabel, 1, 0);
        menuLayout.add(wheelsLabel, 2, 0);
        menuLayout.add(miscLabel, 3, 0);
        menuLayout.add(decksList, 0, 1);
        menuLayout.add(trucksList, 1, 1);
        menuLayout.add(wheelsList, 2, 1);
        menuLayout.add(miscList, 3, 1);

        pageBody.setPadding(new Insets(10));
        pageBody.setAlignment(Pos.CENTER);
        HBox.setMargin(showTotal, new Insets(10, 0, 0, 5));
        HBox.setMargin(totalLabel, new Insets(14, 0, 0, 10));

        pageBody.setId("page-body");
        showTotal.setId("show-total");
        totalLabel.setId("total-label");
        decksList.setId("decks-list");
        trucksList.setId("trucks-list");
        wheelsList.setId("wheels-list");
        miscList.setId("misc-list");

        decksLabel.getStyleClass().add("label");
        truckAssembliesLabel.getStyleClass().add("label");
        wheelsLabel.getStyleClass().add("label");

        menuLayout.setHgap(15);

        showTotal.setOnAction(e -> {
            skateboardProduct sel1 = decksList.getSelectionModel().getSelectedItem();
            skateboardProduct sel2 = trucksList.getSelectionModel().getSelectedItem();
            skateboardProduct sel3 = wheelsList.getSelectionModel().getSelectedItem();
            ObservableList<skateboardProduct> sel4 = miscList.getSelectionModel().getSelectedItems();
            int sel4Price = 0;

            if (sel1 == null || sel2 == null || sel3 == null) {
                totalLabel.setText("Error: Please select an option for the *required categories.");
                totalLabel.setStyle("-fx-text-fill: red;");
            }

            if (!sel4.isEmpty()) {
                for (skateboardProduct item : sel4) {
                    sel4Price += item.getPrice();
                }
            }

            int subtotal = sel1.getPrice() + sel2.getPrice() + sel3.getPrice() + sel4Price;
            double tax = subtotal * 0.07;
            double finalTotal = subtotal + tax;

            totalLabel.setStyle("-fx-text-fill: black;");
            totalLabel.setText(String.format("Total: $%.2f + $%.2f (tax) = $%.2f", 
                (double) subtotal, tax, finalTotal)); 
                
        });

        Scene scene = new Scene(pageBody);  
        scene.getStylesheets().add("TaskA.css");
        stage.setScene(scene);
        stage.show();
    }

}

