package com.example.ex1;

import javafx.scene.canvas.Canvas;

public class SelectionSort extends AbstractSortMaster {

    private int currentItemIndex;
    private int minimumItemIndex;
    private int sortedItemIndex;

    public SelectionSort(Canvas canvas) {
        super(canvas);
        this.currentItemIndex = 0;
        this.minimumItemIndex = 0;
        this.sortedItemIndex = 0;
    }

    @Override
    public boolean executeOneSortingStep() {
        currentItemIndex++;
        if (currentItemIndex != this.getIntegersToSort().length && minimumItemIndex != this.getIntegersToSort().length)
            updateMinimumItemIndex();
        else if ((minimumItemIndex != this.getIntegersToSort().length))
            this.swap(minimumItemIndex, sortedItemIndex);

        return (sortedItemIndex == this.getIntegersToSort().length);
    }

    private void updateMinimumItemIndex() {
        if (this.getIntegersToSort()[currentItemIndex] < this.getIntegersToSort()[minimumItemIndex])
            minimumItemIndex = currentItemIndex;
    }

    @Override
    protected void swap(int indexOne, int secondIndex) {
        super.swap(indexOne, secondIndex);
        sortedItemIndex++;
        currentItemIndex = sortedItemIndex;
        minimumItemIndex = sortedItemIndex;
    }

    @Override
    public void redraw() {
        super.redraw();
        if (currentItemIndex != this.getIntegersToSort().length)
            this.drawOneRectangle(currentItemIndex, this.getCurrentRectangleColor());
        if (sortedItemIndex != this.getIntegersToSort().length)
            this.drawOneRectangle(sortedItemIndex, this.getTargetRectangleColor());
        if (minimumItemIndex != this.getIntegersToSort().length)
            this.drawOneRectangle(minimumItemIndex, this.getMinRectangleColor());
    }
}
