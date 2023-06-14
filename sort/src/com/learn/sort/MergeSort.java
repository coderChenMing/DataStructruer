package com.learn.sort;

/**
 * 归并排序:冯诺依曼提出
 * 核心思想:先不断分割,直到不可分割,再不断合并,属于递归操作
 * 时间复杂度:O(nlog(n))
 * 空间复杂度:O(n/2+log(n))=O(n)
 */
@SuppressWarnings("unchecked")
public class MergeSort<E extends Comparable<E>> extends Sort<E> {

    private E[] leftArray;// 自定义数组用于存储分割大数组后的左边元素

    @Override
    protected void sort() {
        /*leftArray = (E[]) new Object[array.length >> 1];*/
        leftArray = (E[]) new Comparable[array.length >> 1];
        sort(0, array.length);
    }

    private void sort(int begin, int end) {
        // end -begin 即为元素个数,元素个数为1不再排序
        if (end - begin < 2) return;
        int mid = (begin + end) >> 1;
        sort(begin, mid);
        sort(mid, end);
        merge(begin, mid, end);
    }

    private void merge(int begin, int mid, int end) {
        //merge 过程,按照左右两个数组元素大小的随机性,可划分为合并时左边先合并结束,合并时右边先合并结束
        // 左边先合并结束,右边无需额外操作即合并完成
        // 右边先合并结束,右边re先越界,不再自增,左边直接将li位置之后元素移动到右边,li++,ai++
        // 整个合并过程中,利用存储大数组左边元素的数组leftArray和大数组右边元素数组进行比较一一确定整个数组的新元素位置
        int li = 0;// 左边数组起始索引
        int le = mid - begin;// 左边数组结束索引
        int ri = mid;// 右边数组起始索引
        int re = end;//右边数组结束索引
        int ai = begin;// 整个大数组中左边数组覆盖位置
        // 备份左边数组
        for (int i = li; i < le; i++) {
            leftArray[i] = array[begin + i];
        }
        /*while (li < le) {//如果左边还没结束
            if (cmpElements(leftArray[li], array[ri]) <= 0) {// =保持稳定性
               *//* array[ai] = leftArray[li];
                ai++;
                li++;*//*
                array[ai++] = array[li++];
            } else {
               *//* array[ai] = array[ri];
                ai++;
                ri++;*//*
                array[ai++] = array[ri++];
            }
        }*/
        while (li < le) {//如果左边还没结束
            if (ri < re && cmpElements(array[ri], leftArray[li]) < 0) {// 调整比较顺序保持稳定性
                 /* array[ai] = array[ri];
                ai++;
                ri++;*/
                array[ai++] = array[ri++];
            } else {
              /* array[ai] = leftArray[li];
                ai++;
                li++;*/
                array[ai++] = array[li++];
            }
        }
    }
}
