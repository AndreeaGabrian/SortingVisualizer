package com.example.ex1;

import javafx.scene.canvas.Canvas;

public class InsertionSort extends AbstractSortMaster {

    private int nextUnsortedIndex;
    private int previousLeftIndex;

    public InsertionSort(Canvas canvas) {
        super(canvas);
        this.nextUnsortedIndex = 1;
        this.previousLeftIndex = 1;

    }

    @Override
    public boolean executeOneSortingStep() {
        if (nextUnsortedIndex == this.getIntegersToSort().length + 1) {
            return true;
        }
        if (previousLeftIndex != 0) {
            if (this.getIntegersToSort()[previousLeftIndex - 1] > this.getIntegersToSort()[previousLeftIndex]) {
                swap(previousLeftIndex - 1, previousLeftIndex);
            }else{
                nextUnsortedIndex++;
                previousLeftIndex = nextUnsortedIndex - 1;
            }
        }else{
            nextUnsortedIndex++;
            previousLeftIndex = nextUnsortedIndex - 1;
        }
        return false;
    }

    @Override
    protected void swap(int firstIndex, int secondIndex) {
        super.swap(firstIndex, secondIndex);
        previousLeftIndex--;
    }

    @Override
    public void redraw() {
        super.redraw();
        if (this.nextUnsortedIndex != this.getIntegersToSort().length) {
            if (nextUnsortedIndex != this.getIntegersToSort().length)
                drawOneRectangle(nextUnsortedIndex, this.getCurrentRectangleColor());
            if (previousLeftIndex != this.getIntegersToSort().length)
                drawOneRectangle(previousLeftIndex, this.getTargetRectangleColor());
        }
    }
}
