package com.learn;

/**
 * Project: DataStructruer
 * File Created at 2022-01-16 15:26:15:26
 * {@link }
 * 数组设计及实现:需要哪些结构? 需要哪些方法?
 * 支持泛型自定义数组列表
 *
 * @author <a href="mailto:">chenming</a>
 * @version 1.0.0
 * @type ArrayIntList.java
 * @desc
 * @date 2022/1/16 0016 15:26
 */
@SuppressWarnings("unchecked")
public class ArrayGenericList<E> extends AbstractGenericList<E> {

    /**
     * 定义存储元素的空间
     */
    private E[] elements;

    static final int DEFAULT_CAPACITY = 10;
    static final double CAPACITY_PERCENT = 0.75;

    /**
     * 根据指定容量大小初始化元素空间
     */
    public ArrayGenericList(int capacity) {
        //capacity=(capacity < DEFAULT_CAPACITY) ? DEFAULT_CAPACITY : capacity;
        capacity = Math.max(capacity, DEFAULT_CAPACITY);
        elements = (E[]) new Object[capacity];
    }

    /**
     * 无参构造,给出空间默认值,自定义数组空间大小,
     */
    public ArrayGenericList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * 元素个数
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * 是否为空
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 获取指定索引位置的元素
     */
    @Override
    public E get(int index) {
        checkIndex(index);
        return elements[index];
    }

    /**
     * 设置指定索引位置的值,同时返回索引位置原来的值
     */
    @Override
    public E set(int index, E element) {
        checkIndex(index);
        E old = elements[index];
        elements[index] = element;
        return old;
    }

    /**
     * 添加元素
     */
    @Override
    public void add(E element) {
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
    @Override
    public void add(int index, E element) {
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
            E[] newArray = (E[]) new Object[newCapacity];
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
    @Override
    public E remove(int index) {
        checkIndex(index);
        E old = elements[index];
        for (int i = index; i < size; i++) {
            elements[i] = elements[i + 1];
        }
        // 清空旧数组的最后一个元素 size
        // 清空新数组的最后一个元素 size
        // 先--后清空
        elements[--size] = null;
        return old;
    }

    /**
     * 删除指定元素
     */
    @Override
    public E remove(E element) {
        return remove(indexOf(element));
    }

    /**
     * 定位元素在数组中的索引位置
     */
    @Override
    public int indexOf(E element) {
        //注意:size:实际元素个数,elements.length:数组空间大小
        // 数组元素可以存储Null值时处理方式
        if (null == element) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    return i;
                }

            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i])) {
                    return i;
                }
            }
        }
        // -1表示没有找到
        return ELEMENT_NOT_FOUND;
    }

    /**
     * 是否包含指定元素
     */
    @Override
    public boolean contains(E element) {
        return ELEMENT_NOT_FOUND != indexOf(element);
    }

    /**
     * 清除所有元素
     */
    @Override
    public void clear() {
        // 除了size=0外,还要置null,因为对象是很占空间的
        for (int i = 0; i < size; i++) {
            elements[i] = null;

        }
        size = 0;

    }

    public E[] getElements() {
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
        sb.append("]}");
        return sb.toString();
    }
}
