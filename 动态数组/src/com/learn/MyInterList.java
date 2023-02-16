package com.learn;

public class MyInterList {
    // 自定义int类型list
    // 成员变量 size ,和 int数组
    private int size;
    private int[] elements;
    private static final int DEFAULT_CAPACITY = 10;
    private static final int ELEMENT_NOT_FOUND = -1;

    public MyInterList() {
        this(DEFAULT_CAPACITY);
    }

    public MyInterList(int capacity) {
        elements = new int[Math.max(capacity, DEFAULT_CAPACITY)];
    }

    // 定义对外的方法
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        size = 0;
    }

    public boolean contains(int element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    public void check4Add(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("索引参数非法");
        }
    }

    public void check4Index(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("索引参数非法");
        }
    }

    // 重要方法，添加，删除
    //指定位置添加
    public void add(int index, int element) {
        check4Add(index);
        // 扩容:保证至少能添加一个
        ensureCapacity();


        // 中间位置添加  0 1 2 3 4 -> 0 1 5 2 3 4
        // 第一步 先将 2 3 4 向后移动，第二部设置 索引位置 2的值为5
        // 移动方式1 ： 2-> 3 3 ->4 4 ->最后 ，从前往后挪动，后面的值会被同一个值覆盖 不可行
        // 移动方式2 ： 先移动最后的元素，一次向前遍历移动
        /*
        size -> index+1
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
            // 第一次 element[5]=element[4],最后一次 elements[3]=elements[2]
            // 执行次数 ：size -index=5-2=3次
        }
        */

        //while循环方式 size -> index+1
        int init = size;
        while (init > index) {
            elements[init] = elements[init - 1];
            init--;
        }
        // 设置索引位置参数为新元素
        elements[index] = element;
        size++;
        // 头部添加,上面亦满足
        // 尾部添加，上面亦满足
    }

    private void ensureCapacity() {
        // 数组当前元素数量:size  数组容量:elements.length
        int capacity = elements.length;
        if (size < capacity) {
            return;
        } else {
            int newCapacity = capacity + (capacity >> 1);
            int[] newElements = new int[newCapacity];
            System.arraycopy(elements, 0, newElements, 0, capacity);
            System.out.println(capacity + " 扩容为:" + newCapacity);
            elements = newElements;
        }

    }

    // 尾部添加
    public void add(int element) {
        add(size, element);
    }

    // 删除指定索引位置元素
    public int remove(int index) {
        check4Index(index);
        // 0 1 2 3 4 -> 0 2 3 4 (4)
        // 移动过程:递增式向前移动，最后一个元素因size--而不能访问
        // 指定索引位置被其后面的元素覆盖就行，后面元素依次向前移动
        int old = elements[index];
        /*
        for循环方式
        for (int i = index + 1; i < size; i++) {
            elements[i - 1] = elements[i];
            // 从elements[1]=elements[2]-> elements[size-2]=elements[size-1]及elements[3]=elements[4]
            // 执行了三次 size -index-1次
        }
        */
        // while循环方式
        while (index++ < size - 1) {
            elements[index] = elements[index + 1];
        }
        /*for (int i = index; i < size; i++) {
            elements[i] = elements[i+1];
            // elements[1]=elements[2] 最后一轮 elements[size-1]=elements[size] 显然是错误的，索引会越界，所以i<size-1
        }*/
        size--;
        return old;
    }

    // 获取索引位置元素
    public int get(int index) {
        check4Index(index);
        return elements[index];
    }

    // 获取元素对应的索引
    public int indexOf(int element) {
        for (int i = 0; i < elements.length; i++) {
            if (element == elements[i]) {
                return i;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    // 设置指定位置索引对应元素,返回原始值
    public int set(int index, int element) {
        check4Index(index);
        int old = elements[index];
        elements[index] = element;
        return old;
    }

    @Override
    public String toString() {
        // size=[0,1,2,3]
        StringBuilder sb = new StringBuilder();
        sb.append("size = [ ");
        for (int i = 0; i < size; i++) {
            if (i == 0) {
                sb.append(elements[i]);
            } else {
                sb.append(" , ").append(elements[i]);
            }
        }
        return sb.append(" ]").toString();
    }
}
