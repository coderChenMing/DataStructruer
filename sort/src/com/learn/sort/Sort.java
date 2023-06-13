package com.learn.sort;

import java.awt.color.CMMException;
import java.text.DecimalFormat;
import java.util.Comparator;

public abstract class Sort<E extends Comparable<E>> implements Comparable<Sort<E>> {
    protected E[] array;
    private int cmpCount;
    private int swapCount;
    private long time;
    private DecimalFormat fmt = new DecimalFormat("#.00");

    protected Comparator<E> comparator;

    public Sort() {
        this(null);
    }

    public Sort(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public void sort(E[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        this.array = array;
        long begin = System.currentTimeMillis();
        sort();
        time = System.currentTimeMillis() - begin;
    }

    @Override
    public int compareTo(Sort o) {
        int result = (int) (time - o.time);
        if (result != 0) result = cmpCount - o.cmpCount;
        if (result != 0) result = swapCount - o.swapCount;
        return result;
    }

    protected abstract void sort();

    protected int cmp(int i1, int i2) {
        cmpCount++;
        if (comparator != null) {
            return comparator.compare(array[i1], array[i2]);
        } else {
            return array[i1].compareTo(array[i2]);
        }

    }

    protected int cmpElements(E e1, E e2) {
        cmpCount++;
        return e1.compareTo(e2);
    }

    protected void swap(int i1, int i2) {
        swapCount++;
        E temp = array[i1];
        array[i1] = array[i2];
        array[i2] = temp;
    }

    @Override
    public String toString() {
        String timeStr = "耗时：" + (time / 1000.0) + "s(" + time + "ms)";
        String compareCountStr = "比较：" + numberString(cmpCount);
        String swapCountStr = "交换：" + numberString(swapCount);
        //String stableStr = "稳定性：" + isStable();
        return "【" + getClass().getSimpleName() + "】\n"
                + timeStr + " \t"
                + compareCountStr + "\t "
                + swapCountStr + "\n"
                + "------------------------------------------------------------------";

    }

    private String numberString(int number) {
        if (number < 10000) return "" + number;

        if (number < 100000000) return fmt.format(number / 10000.0) + "万";
        return fmt.format(number / 100000000.0) + "亿";
    }
}
