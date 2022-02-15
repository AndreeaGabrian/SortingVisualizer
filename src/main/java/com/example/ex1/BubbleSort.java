package com.example.ex1;

import javafx.scene.canvas.Canvas;

public class BubbleSort extends AbstractSortMaster {

    private Integer left;
    private Integer right;
    private Integer nrOfSwaps;

    public BubbleSort(Canvas canvas) {
        super(canvas);
        this.nrOfSwaps = 0;
        this.left = 0;
        this.right = 1;
    }

    @Override
    public boolean executeOneSortingStep() {
        if (right == this.getIntegersToSort().length) {
            if (nrOfSwaps == 0) {
                left++;
                return true;
            }else {
                left = 0;
                right = 1;
                nrOfSwaps = 0;
            }
        }
        else if (this.getIntegersToSort()[left] > this.getIntegersToSort()[right]) {
            swap(left, right);
        }else {
            left++;
            right++;
        }
        return false;
    }

    @Override
    protected void swap(int firstIndex, int secondIndex) {
        super.swap(firstIndex, secondIndex);
        this.right++;
        this.left++;
        this.nrOfSwaps++;
    }

    @Override
    public void redraw() {
        super.redraw();
        if (this.right != this.getIntegersToSort().length ) {
            this.drawOneRectangle(right, this.getTargetRectangleColor());
            this.drawOneRectangle(left, this.getCurrentRectangleColor());
        }
    }
}
