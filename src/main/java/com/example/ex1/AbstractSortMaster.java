package com.example.ex1;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public abstract class AbstractSortMaster extends AnimationTimer {

    private Integer[] integersToSort;
    private long lastDrawn = 0;
    private int rectangleWidth;
    private int rectangleHeight;
    private double padding;
    private final Canvas canvas;
    private int speedPerSecond = 2;
    private final Color backgroundColor = Color.rgb(41, 36, 41);
    private final Color defaultRectangleColor = Color.rgb(147,189,214);
    private final Color currentRectangleColor = Color.rgb(171,44,73);
    private final Color targetRectangleColor = Color.rgb(160, 219, 142);
    private final Color minRectangleColor = Color.rgb(218,165,32);
    private boolean started = false;

    public boolean isStarted() {
        return started;
    }

    AbstractSortMaster(Canvas canvas) {
        this.canvas = canvas;
    }

    public Color getCurrentRectangleColor() {
        return currentRectangleColor;
    }

    public Color getTargetRectangleColor() {
        return targetRectangleColor;
    }

    public Color getMinRectangleColor() {
        return minRectangleColor;
    }

    public Integer[] getIntegersToSort() {return integersToSort;}

    public void setScansPerSecond(int scansPerSecond) {
        this.speedPerSecond = scansPerSecond;
    }

    public abstract boolean executeOneSortingStep();

    @Override
    public void start() {
        super.start();
       this.started = true;
    }

    @Override
    public void stop() {
        super.stop();
        this.started = false;
    }

    @Override
    public void handle(long now) {
        int framesPerSecond = 60;
        if ((now - this.lastDrawn) > ((10 - this.speedPerSecond) * 1000000000L / this.integersToSort.length)) {
            this.lastDrawn = now;
            int nrDrawn;
            if (this.integersToSort.length <= framesPerSecond)
                nrDrawn = 1;
            else
                nrDrawn = this.speedPerSecond * this.integersToSort.length / framesPerSecond + 1;
            boolean finish;
            for (int i = 0; i < nrDrawn; i++) {
                finish = executeOneSortingStep();
                if (finish) {
                    redraw();
                    stop();
                }
            }
            redraw();
        }
    }

    private void generateRandomIntegerArray() {
        integersToSort = Arrays.stream(new Random().ints(integersToSort.length, 1, integersToSort.length).toArray()).boxed().toList().toArray(new Integer[0]);
    }

    protected void setSizes(int size) {
        this.integersToSort = new Integer[size];
        generateRandomIntegerArray();
        this.rectangleHeight = (int)(this.canvas.getHeight() / Collections.max(Arrays.asList(integersToSort)));
        this.rectangleWidth = (int)(this.canvas.getWidth() / integersToSort.length);
        if (this.integersToSort.length >= 100)
            this.padding = 0.3;
        else
            this.padding = 1.5;
    }

    protected void drawOneRectangle(int index, Color color) {
        GraphicsContext canvasGraphicsContext2D = this.canvas.getGraphicsContext2D();
        double x = index * this.rectangleWidth + this.padding;
        double y = (int) this.canvas.getHeight() + padding - this.integersToSort[index] * rectangleHeight;
        double rectangleHeight = this.rectangleHeight * this.integersToSort[index] - (4 * this.padding);
        double rectangleWidth = this.rectangleWidth - (4 * padding);
        canvasGraphicsContext2D.setFill(color);
        canvasGraphicsContext2D.fillRect(x, y, rectangleWidth, rectangleHeight);
    }

    protected void erase() {
        GraphicsContext canvasGraphicsContext2D = canvas.getGraphicsContext2D();
        canvasGraphicsContext2D.setFill(this.backgroundColor);
        canvasGraphicsContext2D.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    protected void redraw() {
        erase();
        if (this.integersToSort != null)
            drawRectangleArray();
    }

    protected void drawRectangleArray() {
        for (int i = 0; i < integersToSort.length; i++) {
            drawOneRectangle(i, this.defaultRectangleColor);
        }
    }

    protected void swap(int firstIndex, int secondIndex) {
        int temp = integersToSort[firstIndex];
        this.integersToSort[firstIndex] = this.integersToSort[secondIndex];
        this.integersToSort[secondIndex] = temp;
    }

}
