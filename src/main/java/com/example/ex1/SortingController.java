package com.example.ex1;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

import java.net.URL;
import java.util.ResourceBundle;

public class SortingController implements Initializable {
    @FXML private Label best;
    @FXML private Label average;
    @FXML private Label worst;
    @FXML private Button resumeSorting;
    @FXML private Slider sliderSpeed;
    @FXML private ComboBox <String> comboBoxSortingAlgorithms;
    @FXML private Canvas mainCanvas;
    @FXML private ComboBox <Integer> arraySize;

    private AbstractSortMaster sortMaster;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        arraySize.getItems().clear();
        arraySize.getItems().addAll(10, 25, 30, 50, 75, 100, 200, 300, 400, 500, 800);
        arraySize.getSelectionModel().selectFirst();

        comboBoxSortingAlgorithms.getItems().clear();
        comboBoxSortingAlgorithms.getItems().addAll("Insertion Sort", "Bubble Sort", "Selection Sort");
        comboBoxSortingAlgorithms.getSelectionModel().selectFirst();

        sortMaster = new SelectionSort(mainCanvas);
        sortMaster.erase();

    }

    public void generateNewConfiguration() {
        resumeSorting.setText("Start");
        if(this.sortMaster.isStarted()){sortMaster.stop();}
        int size = arraySize.getSelectionModel().getSelectedItem();
        this.sortMaster = getSortingSimulation(comboBoxSortingAlgorithms.getValue());
        System.out.println(comboBoxSortingAlgorithms.getValue());
        displayPerformance(comboBoxSortingAlgorithms.getValue());
        System.out.println(comboBoxSortingAlgorithms.getValue());
        if (this.sortMaster != null) {
            this.sortMaster.erase();
            this.sortMaster.setScansPerSecond((int) sliderSpeed.getValue());
            this.sortMaster.setSizes(size);
            this.sortMaster.drawRectangleArray();
        }
    }

    public void stop(){
        sortMaster.stop();
        resumeSorting.setText("Resume");
    }

    public void run(){
        sortMaster.start();
    }

    private void displayPerformance(String strategy){
        switch (strategy) {
            case "Bubble Sort", "Insertion Sort" -> {
                best.setText("Ω(n)");
                average.setText("θ(n^2)");
                worst.setText("O(n^2)");
            }
            case "Selection Sort" -> {
                best.setText("Ω(n^2)");
                average.setText("θ(n^2)");
                worst.setText("O(n^2)");
            }
        }
    }


    private AbstractSortMaster getSortingSimulation(String strategy) {
        return switch (strategy) {
            case "Bubble Sort" -> new BubbleSort(mainCanvas);
            case "Insertion Sort" -> new InsertionSort(mainCanvas);
            //case "Merge Sort" -> new MergeSort(mainCanvas);
            case "Selection Sort" -> new SelectionSort(mainCanvas);
            default -> null;
        };
    }
}
