// Dale Peligro
// CS2450
// Assignment 1
// 09/25/2025

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

class phoneProduct {
    private String name;
    private double price;

    phoneProduct(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name;
    }
}

public class TaskB extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        final double[] planPrice = {0};
        final double[] modelPrice = {0};
        final double[] miscPrice = {0};

        stage.setTitle("Cal Poly Cell");
        Label title = new Label("Cal Poly Cell");
        Label planLabel = new Label("*Data Plan");
        Label modelLabel = new Label("*Phone Models");
        Label miscLabel = new Label("Add-ons");
        Label informationLabel = new Label("Please select from *required");

        Label outputLabel = new Label("Total: $0.00");
        Label planPriceLabel = new Label("Plan: $0.00");
        Label modelPriceLabel = new Label("Model: $0.00");
        Label miscPriceLabel = new Label("Add-ons: $0.00");

        VBox pageLayout = new VBox();
        VBox planSelections = new VBox();
        VBox modelSelections = new VBox();
        VBox miscSelections = new VBox();
        VBox planContainer = new VBox();
        VBox modelContainer = new VBox();
        VBox miscContainer = new VBox();
        VBox priceContainer = new VBox();

        phoneProduct plan1 = new phoneProduct("8GB per month", 20);
        phoneProduct plan2 = new phoneProduct("16GB per month", 30);
        phoneProduct plan3 = new phoneProduct("20GB per month", 40);
        phoneProduct model1 = new phoneProduct("Model I", 299.95);
        phoneProduct model2 = new phoneProduct("Model J", 399.95);
        phoneProduct model3 = new phoneProduct("Model K", 499.95);
        phoneProduct misc1 = new phoneProduct("Phone Replacement Insurance", 1);
        phoneProduct misc2 = new phoneProduct("WiFi Hotspot Capability", 10);

        ToggleGroup dataGroup = new ToggleGroup();
        RadioButton plan1Selection = new RadioButton(plan1.toString()); 
        RadioButton plan2Selection = new RadioButton(plan2.toString()); 
        RadioButton plan3Selection = new RadioButton(plan3.toString()); 
        dataGroup.getToggles().addAll(plan1Selection, plan2Selection, plan3Selection);

        ToggleGroup modelGroup = new ToggleGroup();
        RadioButton model1Selection = new RadioButton(model1.toString()); 
        RadioButton model2Selection = new RadioButton(model2.toString()); 
        RadioButton model3Selection = new RadioButton(model3.toString()); 
        modelGroup.getToggles().addAll(model1Selection, model2Selection, model3Selection);

        CheckBox misc1Selection = new CheckBox(misc1.toString()); 
        CheckBox misc2Selection = new CheckBox(misc2.toString()); 

        planContainer.getChildren().addAll(planLabel, planSelections);
        modelContainer.getChildren().addAll(modelLabel, modelSelections);
        miscContainer.getChildren().addAll(miscLabel, miscSelections);

        pageLayout.getChildren().addAll(
            title,
            informationLabel,
            planContainer,
            modelContainer,
            miscContainer,
            priceContainer
        );

        pageLayout.setSpacing(20);
        pageLayout.setPadding(new Insets(10, 0, 10, 20));

        planSelections.getChildren().addAll(plan1Selection, plan2Selection, plan3Selection);
        modelSelections.getChildren().addAll(model1Selection, model2Selection, model3Selection);
        miscSelections.getChildren().addAll(misc1Selection, misc2Selection);

        priceContainer.getChildren().addAll(
            planPriceLabel,
            modelPriceLabel,
            miscPriceLabel,
            outputLabel
        );

        // Set nodes to their corresponding objects
        plan1Selection.setUserData(plan1);
        plan2Selection.setUserData(plan2);
        plan3Selection.setUserData(plan3);

        model1Selection.setUserData(model1);
        model2Selection.setUserData(model2);
        model3Selection.setUserData(model3);

        misc1Selection.setUserData(misc1);
        misc2Selection.setUserData(misc2);

        title.setId("page-title");
        informationLabel.setId("information-label");
        planLabel.getStyleClass().add("small-label");
        modelLabel.getStyleClass().add("small-label");
        miscLabel.getStyleClass().add("small-label");
        planSelections.getStyleClass().add("selections");
        modelSelections.getStyleClass().add("selections");
        miscSelections.getStyleClass().add("selections");
        priceContainer.setId("price-container");

        dataGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            if (newToggle != null) {
                phoneProduct selectedPlan =  (phoneProduct) newToggle.getUserData();
                planPrice[0] = selectedPlan.getPrice();
                planPriceLabel.setText("Plan: $" + 
                    String.format("%.2f", planPrice[0]) + " per month");
                outputLabel.setText("Total: $" + 
                    String.format("%.2f per month +", planPrice[0] + miscPrice[0]) + 
                    String.format(" $%.2f", modelPrice[0]));
            }
        });

        modelGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            if (newToggle != null) {
                phoneProduct selectedPlan = (phoneProduct) newToggle.getUserData();
                modelPrice[0] = selectedPlan.getPrice();
                modelPriceLabel.setText("Model: $" +
                    String.format("%.2f", modelPrice[0]));
                outputLabel.setText("Total: $" +
                    String.format("%.2f per month +", planPrice[0] + miscPrice[0]) +
                    String.format(" $%.2f", modelPrice[0]));
            }
        });

        misc1Selection.setOnAction(e -> {
            if (misc1Selection.isSelected()) {
                miscPrice[0] += misc1.getPrice(); // add price
            } else {
                miscPrice[0] -= misc1.getPrice(); // remove price
            }

            miscPriceLabel.setText("Add-ons: $" +
                String.format("%.2f", miscPrice[0]) + " per month");

            outputLabel.setText("Total: $" + 
                String.format("%.2f per month +", planPrice[0] + miscPrice[0]) + 
                String.format(" $%.2f", modelPrice[0]));
        });

        misc2Selection.setOnAction(e -> {
            if (misc2Selection.isSelected()) {
                miscPrice[0] += misc2.getPrice();
            } else {
                miscPrice[0] -= misc2.getPrice();
            }

            miscPriceLabel.setText("Add-ons: $" +
                String.format("%.2f", miscPrice[0]) + " per month");

            outputLabel.setText("Total: $" + 
                String.format("%.2f per month +", planPrice[0] + miscPrice[0]) + 
                String.format(" $%.2f", modelPrice[0]));
        });

        Scene scene = new Scene(pageLayout, 400, 525);  
        scene.getStylesheets().add("TaskB.css");
        stage.setScene(scene);
        stage.show();
    }
}

