package com.learn;

/**
 * 单向链表
 * 链表相比数组：内存不必连续
 **/
public class SingleLinkedList<E> extends AbstractList<E> {

    private Node<E> first;

    private static class Node<E> {
        E element;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }

        public String toString() {
            /*StringBuilder sb = new StringBuilder();
            sb.append("Node = [ ").append(element).append("_");
            if (null != next) {
                sb.append(next.element);
            }else{
                sb.append("null");
            }
            return sb.append(" ] ").toString();*/
            StringBuilder sb = new StringBuilder();
            sb.append(element).append("_");
            if (null != next) {
                sb.append(next.element);
            } else {
                sb.append("null");
            }
            return sb.toString();
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
        first = null;// 头节点指向Null，后面的对象自动被回收
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    @Override
    public int indexOf(E element) {
        /*
        // 也可以正常获取，就是性能比下面的低
        for (int i = 0; i < size; i++) {
            if (null != element) {
                if (element.equals(node(i).element)) {
                    return i;
                }
            } else {
                if (null == node(i).element) {
                    return i;
                }
            }
        }*/

        Node<E> node = first;
        if (null == element) {
            for (int i = 0; i < size; i++) {
                if (node.element == null) {
                    return i;
                }
                node = node.next;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element)) {
                    return i;
                }
                node = node.next;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    /*
     * 通过索引位置获取节点
     * */
    private Node<E> node(int index) {
        check4Index(index);
        // 对于链表 0 1 2 3 4 5  获取索引2 对应的节点2 需要first.next.next 及next两次
        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public void add(int index, E element) {
        check4Add(index);
        if (index == 0) {
            // 添加为第一个节点,first指向新节点
            first = new Node<>(element, first);
        } else {
            // 对于链表 0 1 2 3 4  -> 0 1 5 2 3 4
            // 必须先获取添加节点的前一个节点才能完成添加操作
            Node<E> node = node(index - 1);
            // 新节点
            node.next = new Node<>(element, node.next);
        }
        size++;
    }

    @Override
    public void add(E element) {
        add(size, element);
    }

    @Override
    public E remove(int index) {
        check4Index(index);
        // 获取第一个节点
        Node<E> node = first;
        if (index == 0) {
            first = node.next;
        } else {
            // 0 1 2 3 4 -> 0 2 3 4
            // 获取要删除节点的前一个节点
            Node<E> prev = node(index - 1);
            node = prev.next;
            prev.next = node.next;
        }
        size--;
        return node.element;
    }

    @Override
    public void remove(E element) {
        remove(indexOf(element));
    }

    @Override
    public E get(int index) {
        return node(index).element;
    }

    @Override
    public E set(int index, E element) {
        Node<E> oldNode = node(index);
        oldNode.element = element;
        return oldNode.element;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("size = [ ");
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            if (i == 0) {
                sb.append(node.element);
            } else {
                sb.append(",").append(node.element);
            }
            node = node.next;
        }

       /*
       // 打印后面数据
       for (int i = 0; i < size; i++) {
            if (i == 0) {
                sb.append(node);
            } else {
                sb.append(" , ").append(node);
            }
            node = node.next;
        }*/

        return sb.append(" ]").toString();
    }

    public static void main(String[] args) {
        List<Integer> singleLinkedList = new SingleLinkedList<>();
        singleLinkedList.add(10);
        singleLinkedList.add(11);
        singleLinkedList.add(12);
        singleLinkedList.add(0, 13);
        singleLinkedList.add(0, 14);
        System.out.println("初始化: " + singleLinkedList);
        singleLinkedList.remove(0);
        System.out.println("删除第一个: " + singleLinkedList);
        singleLinkedList.remove(singleLinkedList.size() - 1);
        System.out.println("删除最后一个: " + singleLinkedList);
        System.out.println("10 的索引: " + singleLinkedList.indexOf(10));
        singleLinkedList.set(1, 100);
        System.out.println("set 1=100 : " + singleLinkedList);
        System.out.println("contain 100 : " + singleLinkedList.contains(100));

    }
}
