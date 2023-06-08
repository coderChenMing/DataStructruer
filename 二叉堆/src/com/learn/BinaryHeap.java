package com.learn;

import com.learn.printer.BinaryTreeInfo;
import com.learn.tools.Integers;

import java.util.*;

public class BinaryHeap<E> extends AbstractHeap<E> implements BinaryTreeInfo {

    private E[] elements;

    private static final int DefaultCapacity = 10;

    public BinaryHeap(Comparator<E> comparator) {
        this(null, comparator);
    }

    public BinaryHeap(E[] elements) {
        this(elements, null);
    }

    public BinaryHeap() {
        this(null, null);
    }

    public BinaryHeap(E[] elements, Comparator<E> comparator) {
        super(comparator);
        if (elements == null || elements.length == 0) {
            this.elements = (E[]) new Object[DefaultCapacity];// 泛型不能直接new E[]
        } else {
            int capacity = Math.max(DefaultCapacity, elements.length);
            this.elements = (E[]) new Object[capacity];// 泛型不能直接new E[]
            System.arraycopy(elements, 0, this.elements, 0, elements.length);
            size = elements.length;
            //原地建堆
            heapiFy();
        }
    }

    private void heapiFy() {
        //自上而下的上滤,从数组索引1开始，索引0无意义 时间复杂度o(nlog(n))
        /*for (int i = 1; i < elements.length ; i++) {
            siftUp(i);
        }*/
        // 自下而上的下滤，从最后一个非叶子节点开始下滤,时间复杂度O(n)
        for (int i = (size >> 1) - 1; i >= 0; i--) {
            //i必须>=0 ，等于0表示堆顶元素也要下滤
            siftDown(i);
        }
        // 注意:自上而下的下滤,自下而上的上滤,无法形成大顶堆或者小顶堆结构,分析执行过程即可判断
    }

    public static void main(String[] args) {
        /*Integer[] array = Integers.random(10,1,100);
        Integers.println(array);
        BinaryHeap<Integer> binaryHeap = new BinaryHeap<>(array, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        BinaryTrees.print(binaryHeap);
        System.out.println();*/

        Integer[] array2 = Integers.random(10, 1, 100);
        Integers.println(array2);
        BinaryHeap<Integer> binaryHeap2 = new BinaryHeap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;//小顶堆
            }
            // 1.上滤和下滤，元素数值大的在顶部.的条件是当前索引元素>二叉堆中元素,进行交换即 o1>o2时进行交换,最终堆顶是最大元素，即大顶堆
            // 比较器更换位置 o2 - o1 ,按照1 此时当前元素小的向上交换
        });
        List<Integer> topList = binaryHeap2.topKSelf(array2, 5);
        System.out.println(topList);

    }

    @Override
    public E get() {
        emptyCheck();
        return elements[0];
    }

    private void emptyCheck() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("heap is null");
        }
    }

    public void clear() {
        if (size == 0) {
            return;
        }
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public void add(E e) {
        elementCheck(e);
        //扩容
        ensureCapacity(size + 1);
        // 添加到二叉堆尾部,然后上滤
        elements[size++] = e;
        // 上滤最后一个元素
        // siftUp(size - 1);
        siftUp2(size - 1);
    }

    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);// 扩容1.5倍
        if (capacity <= oldCapacity) return;
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < oldCapacity; i++) {
            newElements[i] = elements[i];
        }
        System.out.println(oldCapacity + "扩容为" + newCapacity);
        elements = newElements;
    }

    /**
     * 上滤
     */
    private void siftUp(int index) {
        // 要上滤的元素
        //E e = elements[index];
        while (index > 0) {// 必须有父节点才能进行上滤,索引index要>0一直上滤到堆顶
            // 获取父节点索引
            int parentIndex = (index - 1) >> 1;
            if (compare(elements[index], elements[parentIndex]) <= 0) return;
            // 如果要上滤的元素值比较大
            E temp = elements[parentIndex];
            elements[parentIndex] = elements[index];
            elements[index] = temp;
            index = parentIndex;
        }
    }


    /**
     * 上滤优化:相比siftUp方法
     * 循环内少执行了
     * elements[index] = parent;
     * index = parentIndex;
     * 循环外多执行了一次
     * elements[index] = now;
     * 相当于
     * siftUp:3倍的O(logN)
     * siftUp2: O(logN)+1
     */
    private void siftUp2(int index) {
        // 要上滤的元素
        E now = elements[index];
        while (index > 0) {// 一直上滤到堆顶
            // 获取父节点索引: (index-1)/2
            int parentIndex = (index - 1) >> 1;
            E parent = elements[parentIndex];
            if (compare(now, parent) <= 0) break;
            // break:跳出当前循环,return:结束整个方法
            // 如果要上滤的元素值比较大
            elements[index] = parent;
            index = parentIndex;// index一直向堆顶替换
        }
        elements[index] = now;
    }

    private void elementCheck(E e) {
        if (null == e) {
            throw new IllegalArgumentException("param is null");
        }
    }

    @Override
    public E remove() {
        emptyCheck();
        E top = elements[0];
        int last = --size;
       /* elements[0] = elements[size - 1];
        elements[size - 1] = null;// 删除最后一个元素
        size--;*/
        elements[0] = elements[last];
        elements[last] = null;// 删除最后一个元素
        // 堆顶下滤
        siftDown(0);
        return top;
    }

    private void siftDown(int index) {
        E now = elements[index];
        // 必须保证index位置是非叶子节点才能下滤,叶子节点下滤有什么意义
        // 第一个叶子节点索引 = 非叶子节点数量
        // index < 第一个叶子节点索引
        while (index < (size >> 1)) {// index 必须有子节点才能下滤,即index必须是非叶子节点的索引 ,即index<非叶子节点数量
            // 获取左右子节点
            // 完全二叉树非叶子节点:1.只有左子节点,2有左右子节点
            int left = (index << 1) + 1;
            E leftE = elements[left];
            int right = left + 1;
            // 如果右子节点存在且大于左子节点则右子节点替换左子节点
            if (right < size && compare(elements[right], leftE) > 0) {
                E rightE = elements[right];
                left = right;
                leftE = rightE;
            }
            // 比较当前元素与左右子节点中比较大的一个
            if (compare(now, leftE) >= 0) break;
            elements[index] = leftE;
            index = left;
        }
        elements[index] = now;
    }

    /*private void siftDown(int index) {
        E element = elements[index];
        int half = size >> 1;
        // 第一个叶子节点的索引 == 非叶子节点的数量
        // index < 第一个叶子节点的索引
        // 必须保证index位置是非叶子节点
        while (index < half) {
            // index的节点有2种情况
            // 1.只有左子节点
            // 2.同时有左右子节点

            // 默认为左子节点跟它进行比较
            int childIndex = (index << 1) + 1;
            E child = elements[childIndex];

            // 右子节点
            int rightIndex = childIndex + 1;

            // 选出左右子节点最大的那个
            if (rightIndex < size && compare(elements[rightIndex], child) > 0) {
                child = elements[childIndex = rightIndex];
            }

            if (compare(element, child) >= 0) break;

            // 将子节点存放到index位置
            elements[index] = child;
            // 重新设置index
            index = childIndex;
        }
        elements[index] = element;
    }*/

    @Override
    public E replace(E e) {
        elementCheck(e);
        E root = null;
        if (size == 0) {
            elements[0] = e;
            size++;
        } else {
            root = elements[0];
            elements[0] = e;
            siftDown(0);
        }
        return root;
    }

    /*
     * topk问题使用优先级队列
     */
    public static List<Integer> topK(Integer[] array, int k) {
        List<Integer> topkList = new ArrayList<>();
        if (k < 1 || k > array.length) {
            return topkList;
        }
        // 使用完全二叉树逻辑实现的优先级队列
        Queue<Integer> queue = new PriorityQueue<>();

        for (int i = 0; i < array.length; i++) {
            if (queue.size() < k) {
                queue.add(array[i]);
            } else {
                if (queue.peek() < array[i]) {//堆顶元素小于数组中元素，则替换堆顶元素
                    queue.poll();
                    queue.add(array[i]);
                }
            }
        }
      /*  while (k-- > 0) {
            topkList.add(queue.poll());
        }*/
        while (!queue.isEmpty()) {
            topkList.add(queue.poll());
        }
        return topkList;
    }

    public List<E> topKSelf(E[] array, int k) {
        List<E> topkList = new ArrayList<>();
        if (k < 1 || k > array.length) {
            return topkList;
        }
        for (int num = 0; num < array.length; num++) {
            if (size < k) {
                add(array[num]);//n(log(K))
            } else if (compare(array[num], get()) < 0) {
                // 满足 get()- array[num]<0 ,即当前元素比堆顶大
                replace(array[num]);//n(log(K))
            }
        }
        while (!isEmpty()) {
            topkList.add(remove());
        }
        return topkList;
    }

    @Override
    public Object root() {
        return 0;
    }

    @Override
    public Object left(Object node) {
        int index = (int) node;
        if (((index << 1) + 1) < size) {
            return (index << 1) + 1;
        } else {
            return null;
        }
    }

    @Override
    public Object right(Object node) {
        int index = (int) node;
        if (((index << 1) + 2) < size) {
            return (index << 1) + 2;
        } else {
            return null;
        }
    }

    @Override
    public Object string(Object node) {
        int index = (int) node;
        return elements[index];
    }
}
