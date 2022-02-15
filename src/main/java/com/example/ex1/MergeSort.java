package com.example.ex1;

import javafx.scene.canvas.Canvas;

public class MergeSort extends AbstractSortMaster{
    MergeSort(Canvas canvas) {
        super(canvas);
    }

    @Override
    public boolean executeOneSortingStep() {
        return false;
    }

    @Override
    public void redraw() {
        super.redraw();
    }


    //to be continued

}

