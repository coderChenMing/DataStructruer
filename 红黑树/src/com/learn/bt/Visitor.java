package com.learn.bt;

/**
 * <p>遍历增强
 * <p>以上四种遍历,执行的都是sout打印，对于调用方来说，有时候需要定制化打印，那么如何实现呢？
 * <p>方法增加接口/抽象类，完成功能扩展
 */
public abstract class Visitor<E> {
    boolean stop;

    abstract boolean visit(E element);
}
