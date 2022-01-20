package com.learn;

/**
 * Project: DataStructruer
 * File Created at 2022-01-16 15:26:15:26
 * {@link }
 * 数组设计及实现:需要哪些结构? 需要哪些方法?
 *
 * @author <a href="mailto:">chenming</a>
 * @version 1.0.0
 * @type ArrayIntList.java
 * @desc
 * @date 2022/1/16 0016 15:26
 */
public class ArrayIntList {
    /**
     * 定义数组元素大小
     */
    private int size;
    /**
     * 定义存储元素的空间
     */
    private int[] elements;

    private static final int DEFAULT_CAPACITY = 10;
    private static final int ELEMENT_NOT_FOUND = -1;
    private static final double CAPACITY_PERCENT = 0.75;

    /**
     * 根据指定容量大小初始化元素空间
     */
    public ArrayIntList(int capacity) {
        //capacity=(capacity < DEFAULT_CAPACITY) ? DEFAULT_CAPACITY : capacity;
        capacity = Math.max(capacity, DEFAULT_CAPACITY);
        elements = new int[capacity];
    }

    /**
     * 无参构造,给出空间默认值,自定义数组空间大小,
     */
    public ArrayIntList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * 元素个数
     */
    public int size() {
        return size;
    }

    /**
     * 是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 获取指定索引位置的元素
     */
    public int get(int index) {
        checkIndex(index);
        return elements[index];
    }

    /**
     * 校验索引值合法性
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            outOfBounds(index);
            return;
        }
    }

    private void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("数组越界!! index = [" + index + "] size = [" + size + "]");
    }

    private void checkIndexForAddIndex(int index) {
        // 索引添加,允许index=size
        if (index < 0 || index > size) {
            outOfBounds(index);
        }
    }

    /**
     * 设置指定索引位置的值,同时返回索引位置原来的值
     */
    public int set(int index, int element) {
        checkIndex(index);
        int old = elements[index];
        elements[index] = element;
        return old;
    }

    /**
     * 添加元素
     */
    public void add(int element) {
        // 观察规律,添加元素的位置就是index=size的位置
        /*elements[size] = element;
        size++;*/
        //优化
        //elements[size++] = element;
        //最终版
        add(size, element);
    }

    /**
     * 指定索引位置添加元素
     * 索引位置及后面元素整体右移一位
     */
    public void add(int index, int element) {
        checkIndexForAddIndex(index);
        //扩容:当size到达数组一定阈值时进行数组扩容,扩到原数组的两倍容量
        reSize();
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        size++;
    }

    private void reSize() {
        int oldCapacity = elements.length;
        if (size >= oldCapacity * CAPACITY_PERCENT) {
            //1.扩容:按照1.5倍扩容 >> 1 :右移一位,缩小两倍
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            int[] newArray = new int[newCapacity];
            //2.复制
            if (size >= 0) {
                System.arraycopy(elements, 0, newArray, 0, size);
            }
            elements = newArray;
            //System.out.println(oldCapacity+"-->"+newCapacity);
        }

    }

    /**
     * 删除指定索引位置元素,返回原索引位置元素
     * 删除:指定索引后面元素往前移动,最后一个元素由于访问不到,无需处理
     */
    public int remove(int index) {
        checkIndex(index);
        int old = elements[index];
        for (int i = index; i < size; i++) {
            elements[i] = elements[i + 1];
        }
        size--;
        return old;
    }

    /**
     * 定位元素在数组中的索引位置
     */
    public int indexOf(int element) {
        //注意:size:实际元素个数,elements.length:数组空间大小
        for (int i = 0; i < size; i++) {
            if (element == elements[i]) {
                return i;
            }
        }
        // -1表示没有找到
        return ELEMENT_NOT_FOUND;
    }

    /**
     * 是否包含指定元素
     */
    public boolean contains(int element) {
        return ELEMENT_NOT_FOUND != indexOf(element);
    }

    /**
     * 清除所有元素
     */
    public void clear() {
        size = 0;
        // size=0的优势:无需再申请内存空间,下次添加元素直接使用
    }

    public int[] getElements() {
        return elements;
    }

    @Override
    public String toString() {
        //打印效果 ArrayIntList{ size = 3, elements = [1 , 2 , 2 ]}
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName()).append("{ size = ").append(size).append(", elements = [");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append(" , ");
            }
            sb.append(elements[i]);

        }
        sb.append(" ]}");
        return sb.toString();
    }
}
