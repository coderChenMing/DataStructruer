package com.learn;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * <p> 二叉搜索树:
 * <p> 任意节点的左子树所有节点值小于当前节点值，右子树所有节点值大于当前节点值
 * <p> 节点必须是可以比较的（直接实现Comparable接口,或者自定义比较器内容，如果是引用对象，引用对象要入二叉树的属性符合前面两种情况）
 * <p> 应用：可以思考下，mysql数据库按照某个字段进行排序查询的过程
 */
public class BinarySearchTree<E> extends BinaryTree<E> {
    //public class BinarySearchTree<E extends Comparable<E>> implements BinaryTreeInfo {
    // 使用BinarySearchTree<E extends Comparable<E>> 方式，第三方调用必须保证添加的元素实现Comparable接口，不太友好
    // 可以增加比较器，由第三方确定比较规则，这样可以比较灵活调用

    protected Comparator<E> comparator;

    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    /**
     * <p>判断二叉树中是否存在某个元素
     */
    public boolean contains(E element) {
        return null != node(element);
    }

    /**
     * <p>添加元素
     */
    public void add(E element) {
        checkElement(element);
        // 添加根节点
        if (root == null) {
            root = createNode(element, null);
            size++;
            // 对二叉树进行平衡
            afterAdd(root);
            return;
        }
        // 添加其他节点
        Node<E> parent = root;
        Node<E> node = root;
        int result = 0;
        while (node != null) {
            // 当 node == null ,退出循环,需要找到叶子节点并作为新节点的父节点，使用parent记录原叶子节点
            result = this.compare(element, node.element);
            parent = node;
            if (result > 0) {
                node = node.right;
            } else if (result < 0) {
                node = node.left;
            } else {
                node.element = element;// 是否有意义？有的，比如同龄不同名，直接替换新名
                return;
            }
        }
        Node<E> newNode = createNode(element, parent);
        if (result > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;
        afterAdd(newNode);
    }

    protected Node<E> createNode(E element, Node<E> parent) {
        return new Node<E>(element, parent);
    }

    protected void afterAdd(Node<E> node) {
        // 由子类重写,定义具体平衡内容
    }

    /**
     * <p>比较逻辑 : result >0 :e1> e2;
     * <p>result=0 :e1=e2
     * <p>result<0 : e1<e2
     */
    private int compare(E e1, E e2) {
        if (null != comparator) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable) e1).compareTo(e2);
    }

    private void checkElement(E element) {
        if (null == element) {
            throw new IllegalArgumentException("调用 add 函数参数有误!!");
        }
    }

    /**
     * <p>根据元素返回节点
     */
    public Node<E> node(E element) {
        Node<E> node = root;
        while (node != null) {
            int result = this.compare(element, node.element);
            if (result == 0) {
                return node;
            } else if (result > 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return node;
    }

    /**
     * 根据指定值删除任意节点
     */
    public void remove(E e) {
        this.remove(node(e));
    }

    /**
     * <p>删除指定节点:
     * <p>以remove.txt中的二叉树为例
     * <p>删除度==2的节点,要使用其前驱或者后继节点替换其位置,以删除节点4为例
     * <p>删除度==1的节点,比如删除节点11 要区分是parent的左子节点还是右子节点，parent.left=node.left node.left.parent=node.parent
     * <p>删除度==0的节点:即删除叶子节点 要区分是parent的左叶子还是右叶子，分别设置parent.left=null,parent.right=null
     */
    public void remove(Node<E> node) {
        if (node == null) return;
        if (node.hasTwoChild()) {
            // 删除度==2的节点
            // 以删除节点4为例,4的后继节点是5
            // 一个节点的后继节点度==0或者度==1，只有这两种可能
            // 使用其后继节点替换要被删除节点元素值
            Node<E> preNode = this.successorNode(node);
            node.element = preNode.element;// 直接替换
            //接下来删除后继节点就行了，而后继节点的度要么0要么1
            node = preNode;
        }
        // 删除度==0或者度==1 的节点
        // 1.找到替换被删除节点的子节点
        Node<E> replace = node.left != null ? node.left : node.right;
        // 对于度==1 replace!=null,而度==0的叶子节点replace==null
        if (replace != null) {
            //删除度==1的节点
            if (node.parent != null) {
                replace.parent = node.parent;
                if (node == node.parent.left) {
                    node.parent.left = replace;
                } else {
                    node.parent.right = replace;
                }
                afterRemove(node);
            } else {
                // 比如 9 11 12 这种链表式二叉树
                root = replace;
                replace.parent = null;
            }
        } else {
            if (node.parent != null) {
                //删除度==0的叶子节点
                //if (node.parent.left != null) {
                if (node == node.parent.left) {
                    // 删除左叶子
                    node.parent.left = null;
                } else {
                    // 删除右叶子
                    node.parent.right = null;
                }
                afterRemove(node);
            } else {
                root = null;
                //afterRemove(root);
            }
        }
        size--;
    }

    protected void afterRemove(Node<E> node) {

    }

    /**
     * <p> 根据四种遍历实现 toString打印
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        //preorderTraversalToString(root, sb,"");
        //inorderTraversalToString(root, sb,"");
        //postorderTraversalToString(root, sb, "");
        levelOrderTraversalToString(root, sb, "");
        return sb.toString();
    }

    /**
     * <p> 前序遍历打印
     */
    private void preorderTraversalToString(Node<E> node, StringBuilder sb, String prefix) {
        if (node == null) {
            return;
        }
        //sb.append(node.element).append("\n");
        // 增加前缀进行区别
        sb.append(prefix).append(node.element).append("\n");
        preorderTraversalToString(node.left, sb, prefix + "L____");
        preorderTraversalToString(node.right, sb, prefix + "R____");
    }

    /**
     * <p> 中序遍历打印
     */
    private void inorderTraversalToString(Node<E> node, StringBuilder sb, String prefix) {
        if (node == null) {
            return;
        }
        inorderTraversalToString(node.left, sb, prefix + "L__");
        sb.append(prefix).append(node.element).append("\n");
        inorderTraversalToString(node.right, sb, prefix + "R__");
    }

    /**
     * <p> 后序遍历打印
     */
    private void postorderTraversalToString(Node<E> node, StringBuilder sb, String prefix) {
        if (node == null) {
            return;
        }
        postorderTraversalToString(node.left, sb, prefix + "L__");
        postorderTraversalToString(node.right, sb, prefix + "R__");
        sb.append(prefix).append(node.element).append("\n");
    }

    /**
     * 层序遍历打印
     */
    private void levelOrderTraversalToString(Node<E> node, StringBuilder sb, String prefix) {
        if (null == node) {
            return;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            node = queue.poll();
            sb.append(prefix).append(node.element).append("\n");
            if (null != node.left) {
                queue.offer(node.left);
                prefix += "L__";
            }
            if (null != node.right) {
                queue.offer(node.right);
                prefix += "R__";
            }
        }
    }
}
