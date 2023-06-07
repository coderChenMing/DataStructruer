package com.learn.sort;

public class BubbleSort extends Sort {
    public BubbleSort(Integer[] array) {
        this.array = array;
    }

    @Override
    protected void sort() {
        for (int end = array.length - 1; end > 0; end--) {
            for (int begin = 1; begin <= end; begin++) {
                if (cmp(begin, begin - 1) < 0) {
                    swap(begin, begin - 1);
                }
            }
        }
    }
}
