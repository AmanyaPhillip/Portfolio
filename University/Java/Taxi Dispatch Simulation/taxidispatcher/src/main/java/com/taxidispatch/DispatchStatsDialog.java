package com.taxidispatch;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * A dialog window that displays dispatch statistics in a grid format.
 * It highlights the highest and lowest values in the statistics table.
 */
public class DispatchStatsDialog extends Dialog {
    TextField[][] statsFields; // 2D array of text fields to display statistics

    /**
     * Constructor to initialize the DispatchStatsDialog.
     *
     * @param owner The parent stage of the dialog.
     * @param stats A 2D array containing dispatch statistics.
     */
    public DispatchStatsDialog(Stage owner, int[][] stats) {
        setTitle("Dispatch Statistics"); // Set the title of the dialog window

        // Add an OK button to close the dialog
        getDialogPane().getButtonTypes().add(ButtonType.OK);

        // Create a grid layout for displaying the statistics
        GridPane grid = new GridPane();
        grid.setHgap(10); // Horizontal gap between grid cells
        grid.setVgap(10); // Vertical gap between grid cells
        grid.setPadding(new Insets(10, 10, 10, 10)); // Padding around the grid

        // Initialize the 2D array of text fields
        statsFields = new TextField[DispatchCenter.AREA_NAMES.length][DispatchCenter.AREA_NAMES.length];

        // Add a header label for the grid
        grid.add(new Label("FROM \\ TO"), 0, 0);

        // Add labels for area names along the top and left sides of the grid
        for (int i = 0; i < DispatchCenter.AREA_NAMES.length; i++) {
            Label aLabel = new Label(DispatchCenter.AREA_NAMES[i]);
            aLabel.setAlignment(Pos.CENTER);
            grid.add(aLabel, i + 1, 0, 1, 1); // Top row labels
            aLabel = new Label(DispatchCenter.AREA_NAMES[i]);
            aLabel.setAlignment(Pos.CENTER);
            grid.add(aLabel, 0, i + 1, 1, 1); // Left column labels
        }

        // Populate the grid with statistics values
        for (int i = 0; i < DispatchCenter.AREA_NAMES.length; i++) {
            for (int j = 0; j < DispatchCenter.AREA_NAMES.length; j++) {
                statsFields[i][j] = new TextField("" + stats[i][j]); // Set the value
                statsFields[i][j].setAlignment(Pos.CENTER_RIGHT); // Align text to the right
                statsFields[i][j].setEditable(false); // Make the text field read-only
                statsFields[i][j].setMaxWidth(50); // Set a fixed width for the text field
                grid.add(statsFields[i][j], i + 1, j + 1, 1, 1); // Add to the grid
            }
        }

        // Find the highest and lowest values in the statistics and highlight them
        int max = -1, min = Integer.MAX_VALUE;
        TextField maxF = null, minF = null;

        for (int i = 0; i < DispatchCenter.AREA_NAMES.length; i++) {
            for (int j = 0; j < DispatchCenter.AREA_NAMES.length; j++) {
                if (stats[i][j] > max) {
                    max = stats[i][j];
                    maxF = statsFields[i][j];
                } else if (stats[i][j] < min) {
                    min = stats[i][j];
                    minF = statsFields[i][j];
                }
            }
        }

        // Highlight the highest value in green
        if (maxF != null) {
            maxF.setStyle("-fx-base: rgb(0,150,0); -fx-text-fill: rgb(255,255,255);");
        }

        // Highlight the lowest value in red
        if (minF != null) {
            minF.setStyle("-fx-base: rgb(200,0,0); -fx-text-fill: rgb(255,255,255);");
        }

        // Set the size of the dialog window
        getDialogPane().setMaxSize(640, 640);

        // Add the grid to the dialog's content pane
        getDialogPane().setContent(grid);
    }
}
