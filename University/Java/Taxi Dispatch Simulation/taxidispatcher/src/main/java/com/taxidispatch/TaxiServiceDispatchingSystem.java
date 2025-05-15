package com.taxidispatch;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.*;

public class TaxiServiceDispatchingSystem extends Application {
    // GUI components
    ListView<ClientRequest> incomingList; // ListView for incoming client requests
    ListView<Taxi> enRouteList; // ListView for taxis en route
    ListView<Taxi>[] areaLists; // ListViews for taxis in different areas
    Button dispatchButton; // Button to dispatch taxis

    // Model components
    private DispatchCenter dispatchCenter; // Central dispatch system
    private ArrayList<ClientRequest> incomingQueue; // Queue of incoming client requests
    private Timeline incomingClientTimer; // Timer to generate new client requests
    private Timeline updateTimer; // Timer to update the GUI
    private int clientsPerSecond; // Rate of client request generation

    public void start(Stage primaryStage) {
        // Initialize the dispatch center and incoming queue
        dispatchCenter = new DispatchCenter();
        incomingQueue = new ArrayList<>();

        VBox aPane = new VBox();

        // Create a grid layout for the GUI
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        // Add labels for the areas
        Label label = new Label("Incoming");
        grid.setHalignment(label, HPos.LEFT);
        grid.add(label, 0, 0, 1, 1);
        for (int i = 0; i < DispatchCenter.AREA_NAMES.length; i++) {
            label = new Label(DispatchCenter.AREA_NAMES[i]);
            grid.add(label, 2 + i, 0, 1, 1);
            label.setMinHeight(20);
            label.setMinWidth(50);
        }

        // Add the Dispatch button
        dispatchButton = new Button("Dispatch");
        grid.add(dispatchButton, 0, 3, 1, 1);
        dispatchButton.setMinHeight(30);
        dispatchButton.setMinWidth(100);
        dispatchButton.setOnAction(event -> handleTaxiDispatch());

        // Add ListViews for incoming requests and area taxis
        incomingList = new ListView<>();
        incomingList.setPrefHeight(400);
        grid.add(incomingList, 0, 1, 1, 1);

        areaLists = new ListView[6];
        for (int i = 0; i < areaLists.length; i++) {
            areaLists[i] = new ListView<>();
            areaLists[i].setPrefHeight(400);
            grid.add(areaLists[i], 2 + i, 1, 1, 1);
        }

        // Create the Simulation menu
        Menu simMenu = new Menu("Simula_tion");
        MenuItem start = new MenuItem("Start Clients Coming");
        MenuItem stop = new MenuItem("Stop Clients From Coming");
        simMenu.getItems().addAll(start, stop);

        // Timer to generate new client requests
        clientsPerSecond = 2; // Default rate: 2 clients per second
        incomingClientTimer = new Timeline(new KeyFrame(Duration.millis(1000), event -> {
            // Generate a random client request
            incomingQueue.add(new ClientRequest(
                DispatchCenter.AREA_NAMES[(int) (Math.random() * 6)],
                DispatchCenter.AREA_NAMES[(int) (Math.random() * 6)]
            ));
            update();
        }));
        incomingClientTimer.setCycleCount(Timeline.INDEFINITE);
        incomingClientTimer.setRate(clientsPerSecond);

        // Start and stop actions for the simulation
        start.setOnAction(event -> incomingClientTimer.play());
        stop.setOnAction(event -> incomingClientTimer.stop());

        // Timer to update the GUI and manage taxis
        updateTimer = new Timeline(new KeyFrame(Duration.millis(500), event -> {
            // Update busy taxis and make them available if they reach their destination
            for (Taxi x : dispatchCenter.getBusyTaxis()) {
                x.decreaseEstimatedTimeToDest();
                if (x.getEstimatedTimeToDest() == 0)
                    x.setAvailable(true);
            }
            update();
        }));
        updateTimer.setCycleCount(Timeline.INDEFINITE);
        updateTimer.play();

        // Create the Settings menu
        Menu settingsMenu = new Menu("_Settings");
        MenuItem arrivalRate = new MenuItem("Simulation Rate ...");
        settingsMenu.getItems().addAll(arrivalRate);
        arrivalRate.setOnAction(event -> {
            // Allow the user to set the rate of client requests
            try {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Input Required");
                dialog.setContentText("Iterations Per Second (1 to 20)");
                dialog.setResult("" + clientsPerSecond);
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()) {
                    int input = Integer.parseInt(dialog.getResult());
                    if (input > 0 && input <= 20) {
                        clientsPerSecond = input;
                        incomingClientTimer.setRate(clientsPerSecond);
                    } else {
                        throw new Exception();
                    }
                }
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Please choose a number from 1 to 20");
                alert.showAndWait();
            }
        });

        // Create the Admin menu
        Menu adminMenu = new Menu("_Admin");
        MenuItem stats = new MenuItem("Statistics");
        adminMenu.getItems().addAll(stats);
        stats.setOnAction(event -> new DispatchStatsDialog(null, dispatchCenter.getStats()).showAndWait());

        // Add the menubar
        MenuBar menuBar = new MenuBar();
        aPane.getChildren().addAll(menuBar, grid);
        menuBar.getMenus().addAll(simMenu, settingsMenu, adminMenu);

        // Set the window size and show the stage
        primaryStage.setTitle("Taxi Service Dispatching System");
        primaryStage.setScene(new Scene(aPane, 850, 400));
        primaryStage.show();

        update();
    }

    // Update the GUI components
    private void update() {
        for (int i = 0; i < areaLists.length; i++) {
            areaLists[i].setItems(FXCollections.observableArrayList(
                new ArrayList<>(dispatchCenter.getAreas().get(DispatchCenter.AREA_NAMES[i]))
            ));
            areaLists[i].refresh();
        }
        incomingList.setItems(FXCollections.observableArrayList(new ArrayList<>(incomingQueue)));
        incomingList.refresh();
    }

    // Handle the dispatch of a taxi
    private void handleTaxiDispatch() {
        if (incomingQueue.isEmpty())
            return;
        ClientRequest c = incomingQueue.get(0);
        Taxi t = dispatchCenter.sendTaxiForRequest(c);
        if (t != null)
            incomingQueue.remove(0);
        update();
    }

    public static void main(String[] args) {
        launch(args);
    }
}