package com.learn.topk;

import com.learn.tools.Integers;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Practise {
    public static void main(String[] args) {
        Integer[] array = Integers.random(15, 10, 50);
        Integers.println(array);
        List<Integer> topK = topK(array, 5);
        System.out.println(topK);

    }

    public static List<Integer> topK(Integer[] array, int k) {
        List<Integer> topkList = new ArrayList<>();
        if (k < 1 || k > array.length) {
            return topkList;
        }
        // 使用完全二叉树逻辑实现的优先级队列
        Queue<Integer> queue = new PriorityQueue<>();

        for (int i = 0; i < array.length ; i++) {
            if (queue.size() < k) {
                queue.add(array[i]);
            }else{
                if (queue.peek() < array[i]) {//堆顶元素小于数组中元素，则替换堆顶元素
                    queue.poll();
                    queue.add(array[i]);
                }
            }
        }
        while (k-- > 0) {
            topkList.add(queue.poll());
        }
       /* while (!queue.isEmpty()){
            topkList.add(queue.poll());
        }*/
        return topkList;
    }
}
