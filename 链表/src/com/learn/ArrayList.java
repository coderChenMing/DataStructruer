package com.learn;

import java.util.Arrays;

public class ArrayList<E> extends AbstractList<E> {
    private Object[] elements;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int capacity) {
        elements = new Object[Math.max(capacity, DEFAULT_CAPACITY)];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        size = 0;
        Arrays.fill(elements, null);
    }


    public int indexOf(E element) {
        if (null != element) {
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i])) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (null == (elements[i])) {
                    return i;
                }
            }
        }
        return ELEMENT_NOT_FOUND;
    }


    public boolean contains(E element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    private void ensureCapacity() {
        // 数组当前元素数量:size  数组容量:elements.length
        int capacity = elements.length;
        if (size < capacity) {
            return;
        } else {
            int newCapacity = capacity + (capacity >> 1);
            Object[] newElements = new Object[newCapacity];
            System.arraycopy(elements, 0, newElements, 0, capacity);
            System.out.println(capacity + " 扩容为:" + newCapacity);
            elements = newElements;
        }
    }

    public void add(int index, E element) {
        check4Add(index);
        // 扩容:保证至少能添加一个
        ensureCapacity();
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
            // 第一次 element[5]=element[4],最后一次 elements[3]=elements[2]
            // 执行次数 ：size -index=5-2=3次
        }
        elements[index] = element;
        size++;
    }

    public void add(E element) {
        add(size, element);
    }

    public E remove(int index) {
        check4Index(index);
        E old = (E) elements[index];
        for (int i = index + 1; i < size; i++) {
            elements[i - 1] = elements[i];
        }
        size--;
        return old;
    }

    @Override
    public void remove(E element) {
        remove(indexOf(element));
    }

    public E get(int index) {
        check4Index(index);
        return (E) elements[index];
    }

    public E set(int index, E element) {
        check4Index(index);
        E old = (E) elements[index];
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
