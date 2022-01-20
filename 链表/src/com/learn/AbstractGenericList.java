package com.learn;

/**
 * Project: DataStructruer
 * File Created at 2022-01-17 20:54:20:54
 * {@link }
 *
 * @author <a href="mailto:">chenming</a>
 * @version 1.0.0
 * @type AbstractGenericList.java
 * @desc
 * @date 2022/1/17 0017 20:54
 */
public abstract class AbstractGenericList<E> implements GenericList<E> {
    /**
     * 定义数组元素大小
     */
    protected int size;

    /**
     * 校验索引值合法性
     */
    protected void checkIndex(int index) {
        if (index < 0 || index >= size) {
            outOfBounds(index);
            return;
        }
    }

    /**
     * 指定索引添加元素时校验索引
     */
    protected void checkIndexForAddIndex(int index) {
        // 索引添加,允许index=size
        if (index < 0 || index > size) {
            outOfBounds(index);
        }
    }

    /**
     * 数组越界异常
     */
    protected void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("数组越界!! index = [" + index + "] size = [" + size + "]");
    }
}
