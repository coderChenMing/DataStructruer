package com.learn.array;

/**
 * Project: DataStructruer
 * File Created at 2022-11-01 21:51:21:51
 * {@link}
 * 自定义可变int 数组
 * @author <a href="mailto:charmFlightChen@gmail.com">chenming</a>
 * @version 1.0.0
 * @Type MyIntArrayList.java
 * @Description
 * @date 2022/11/1 21:51
 */
public class MyIntArrayList {

    public static final int DEFAULT_CAPACITY = 10;
    public static final int NOT_FOUND = -1;
    // 分析可变数组成员变量,首先需要一个放数据的数组
    // 存放数据的数组
    private int[] elements;

    // 数组中存放数据数量
    private int size;
    // 需要知道可变数组能干什么事情,也就是有哪些方法


    public int size() {
        return  size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(int element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == element) {
                return true;
            }
        }
        return false;
    }

    public int get(int index) {
        checkIndexNotContainSize(index);
        return elements[index];
    }

    public void add(int element) {
        // 直接添加数据只能在数组尾部追加
        add(size,element);
    }

    public void add(int index, int element) {
        // 指定索引位置添加元素,元素需要移动
        // 11 22 33 44 55 66 比如在索引2处添加77 -> 11 22 77 33 44 55 66
        // index 之前不动，index及之后整体后移

        // 索引虽然可以取到,但是向后移动赋值实现不了
        // elements[3]=elements[2],这样3的值就丢了
        // elements[4]=elements[3],都是赋同一个值
        /*for (int i = index ; i <= size; i++) {

        }*/
        // size =6 index=2 i可以取到6,5,4,3
        checkIndexContainSize(index);
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
            // 5的数据交给6 ,4的数据交给5，一直到2的数据交给3
        }
        elements[index] = element;
        size++;

    }

    private void checkIndexContainSize(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("size: " + size+" index: "+index);
        }
    }

    public int remove(int index) {
        // 删除元素
        // 11 22 33 44 55 66 删除index=2处数据
        // 删除操作不用做 44覆盖33,55覆盖44,66覆盖55
        checkIndexNotContainSize(index);
        int oldEle = elements[index];
        for (int i = index; i < size; i++) {
            elements[index] = elements[index + 1];
            // index 3赋值给index 2,index 4赋值给3。。。index size-1 赋值给index size-2
        }
        size --;
        return oldEle;
    }

    public int set(int index, int element) {
        checkIndexNotContainSize(index);
        int old = elements[index];
        elements[index] = element;
        return old;
    }

    private void checkIndexNotContainSize(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("size: " + size+" index: "+index);
        }
    }

    public MyIntArrayList() {
        // 默认大小
        this(DEFAULT_CAPACITY);
    }

    public MyIntArrayList(int capacity) {
        if (capacity <= DEFAULT_CAPACITY) {
            elements = new int[DEFAULT_CAPACITY];
        }else{
            elements = new int[capacity];
        }
    }

    @Override
    public String toString(){
        // 打印数组 格式 [11,22,33,44,55]
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i <size; i++) {
            if (i == 0) {
                sb.append(elements[0]);
            }else{
                sb.append(",").append(elements[i]);
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        MyIntArrayList myIntArrayList = new MyIntArrayList();
        myIntArrayList.add(11);
        myIntArrayList.add(22);
        myIntArrayList.add(33);
        myIntArrayList.add(44);
        myIntArrayList.add(55);
        myIntArrayList.add(66);
        System.out.println(myIntArrayList);
        myIntArrayList.add(2,77);
        System.out.println(myIntArrayList);
        myIntArrayList.remove(2);
        System.out.println(myIntArrayList);
        myIntArrayList.set(0, 88);
        System.out.println(myIntArrayList);
    }
}
