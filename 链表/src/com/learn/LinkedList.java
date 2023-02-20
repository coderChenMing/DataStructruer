package com.learn;

/*双向链表*/
public class LinkedList<E> extends AbstractList<E> {
    private Node<E> first;
    private Node<E> last;

    @Override
    public void clear() {
        size = 0;
        first = null;
        last = null;
    }

    @Override
    public int indexOf(E element) {
        if (element == null) {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (null == node.element) {
                    return i;
                }
                node = node.next;
            }
        } else {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element)) {
                    return i;
                }
                node = node.next;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    private Node<E> node(int index) {
        check4Index(index);
        // 双向链表，可以根据索引位置选择循环方向
        if (index < (size >> 2)) {
            // 从first开始寻找
            Node<E> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        } else {
            Node<E> node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
            return node;
        }
    }

    @Override
    public void add(int index, E element) {
        check4Add(index);
        // 最好画图示意比较清楚
        // 先考虑一般情况，再处理边界情况
        if ((index == size)) {
            //添加第一个节点和向链表最后添加节点同理
            Node<E> node = last;
            Node<E> newNode = new Node<>(node, element, null);
            last = newNode;
            if (node == null) {
                // 说明在添加整个链表的第一个节点
                first = newNode;
            } else {
                node.next = newNode;
            }
        } else {
            Node<E> next = node(index);
            Node<E> prev = next.prev;
            Node<E> newNode = new Node<>(prev, element, next);
            next.prev = newNode;
            if (prev == null) {
                // prev=null 说明是头节点
                first = newNode;
            } else {
                prev.next = newNode;
            }
        }
        size++;
    }

    @Override
    public E remove(int index) {
        check4Index(index);
        // 先处理一般节点，后处理临界节点
        Node<E> oldNode = node(index);
        Node<E> prev = oldNode.prev;
        Node<E> next = oldNode.next;
        if (prev == null) {
            // 删除头节点
            first = next;
        } else {
            prev.next = next;
        }
        if (next == null) {
            // 删除尾节点
            last = prev;
        } else {
            next.prev = prev;
        }
        size--;
        return oldNode.element;
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
        Node<E> node = node(index);
        E oldEle = node.element;
        node.element = element;
        return oldEle;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("size = ").append(size).append(" first = ").append(first.element).append(" last = ").append(last.element).append(" , [ ");
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append(" , ");
            }
            //sb.append(node.element);
            sb.append(node);
            node = node.next;
        }
        return sb.append(" ] ").toString();
    }

    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        list.add(10);
        list.add(11);
        list.add(12);
        list.add(15);
        list.add(22);
        list.add(55);
        System.out.println("init: "+list);
        list.remove(2);
        System.out.println("remove index=2 :"+list);
        list.set(4, 100);
        System.out.println("set index=4 =====" + list+"====="+list.get(4));


    }

    private static class Node<E> {
        E element;
        Node<E> prev;
        Node<E> next;

        Node(Node<E> prev, E element, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Node = [ ").append(prev == null ? null : prev.element).append("_").append(element).append("_").append(next == null ? null : next.element);
            return sb.append(" ] ").toString();

        }
    }


}
