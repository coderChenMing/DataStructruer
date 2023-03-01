package com.learn;

import com.learn.printer.BinaryTreeInfo;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉搜索树:
 * 任意节点的左子树所有节点值小于当前节点值，右子树所有节点值大于当前节点值
 * <p>
 * 节点必须是可以比较的（直接实现Comparable接口,或者自定义比较器内容，如果是引用对象，引用对象要入二叉树的属性符合前面两种情况）
 * <p>
 * 应用：可以思考下，mysql数据库按照某个字段进行排序查询的过程
 */
public class BinarySearchTree<E> implements BinaryTreeInfo {
    //public class BinarySearchTree<E extends Comparable<E>> implements BinaryTreeInfo {
    // 使用BinarySearchTree<E extends Comparable<E>> 方式，第三方调用必须保证添加的元素实现Comparable接口，不太友好
    // 可以增加比较器，由第三方确定比较规则，这样可以比较灵活调用
    private int size;
    private Node<E> root;

    private Comparator<E> comparator;

    public BinarySearchTree() {

    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    // 添加元素
    public void add(E element) {
        checkElement(element);
        // 添加根节点
        if (root == null) {
            root = new Node<E>(element, null);
            size++;
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
        Node<E> newNode = new Node<>(element, parent);
        if (result > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;
    }

    /**
     * 比较逻辑 : result >0 :e1> e2;
     * result=0 :e1=e2
     * result<0 : e1<e2
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

    // 四种遍历方式: 前中后序遍历,层序遍历
    // 不局限于二叉搜索树，适用于所有二叉树

    /**
     * 前序遍历
     * 前序遍历:先遍历根节点，再遍历左子树，再遍历右子树
     * 对于左子树：先遍历左子树根节点，再遍历左子树的左子树，再遍历左子树的右子树。。。。
     * 建议画图走流程
     */
    public void preorderTraversal() {
        Node<E> node = root;
        preorderTraversal(node);
    }

    private void preorderTraversal(Node<E> node) {
        if (null == node) {
            return;
        }
        System.out.println(node.element);
        preorderTraversal(node.left);
        preorderTraversal(node.right);
    }

    /**
     * 中序遍历
     * 先遍历左子树，再遍历根节点，再遍历右子树
     * 遍历左子树，先遍历左子树的左子树，再遍历左子树的根节点，再遍历左子树的右子树 ....
     * 对于二叉搜索树:中序遍历 各节点数据会从小到大，或者从大到小排序（由比较器比较规则控制）
     */
    public void inorderTraversal() {
        Node<E> node = root;
        inorderTraversal(node);
    }

    private void inorderTraversal(Node<E> node) {
        if (null == node) {
            return;
        }
        inorderTraversal(node.left);
        System.out.println(node.element);
        inorderTraversal(node.right);
    }

    /**
     * 后序遍历
     * 先遍历左子树，再遍历右子树，最后遍历根节点
     * 遍历左子树：先遍历左子树的左子树，再遍历左子树的右子树，再遍历左子树的根节点
     */
    public void postorderTraversal() {
        Node<E> node = root;
        postorderTraversal(node);
    }

    private void postorderTraversal(Node<E> node) {
        if (null == node) {
            return;
        }
        postorderTraversal(node.left);
        postorderTraversal(node.right);
        System.out.println(node.element);
    }

    /**
     * 层序遍历
     * 从根节点开始从上向下，从左向右一层一层的遍历
     * 遍历过程类似队列入队出队，使用队列实现
     */
    public void levelOrderTraversal() {
        Node<E> node = root;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            node = queue.poll();
            System.out.println(node.element);
            // 从左向右，先向队尾加左
            if (node.left != null) {
                queue.offer(node.left);
            }
            // 从左向右，后向队尾加右节点
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    /**
     * 遍历增强
     * 以上四种遍历,执行的都是sout打印，对于调用方来说，有时候需要定制化打印，那么如何实现呢？
     * 方法增加接口/抽象类，完成功能扩展
     */
    public static abstract class Visitor<E> {
        boolean stop;

        abstract boolean visit(E element);
    }

    /**
     * 前序遍历增强
     */
    public void preorderTraversalExt(Visitor<E> visitor) {
        if (null == visitor) {
            // 如果你没有传，我就全部遍历打印
            preorderTraversal();
            return;
        }
        // 传了visitor,我就根据实现进行操作
        this.preorderTraversalExt(root, visitor);
    }

    private void preorderTraversalExt(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) {
            return;
        }
        visitor.stop = visitor.visit(node.element);
        preorderTraversalExt(node.left, visitor);
        preorderTraversalExt(node.right, visitor);

    }

    /**
     * 中序遍历增强
     */
    public void inorderTraversalExt(Visitor<E> visitor) {
        if (null == visitor) {
            // 如果你没有传，我就全部遍历打印
            inorderTraversal();
            return;
        }
        // 传了visitor,我就根据实现进行操作
        this.inorderTraversalExt(root, visitor);
    }

    private void inorderTraversalExt(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) {
            return;
        }
        inorderTraversalExt(node.left, visitor);
        if (visitor.stop) {
            return;
        }
        visitor.stop = visitor.visit(node.element);
        inorderTraversalExt(node.right, visitor);

    }

    /**
     * 后序遍历增强
     */
    public void postorderTraversalExt(Visitor<E> visitor) {
        if (null == visitor) {
            // 如果你没有传，我就全部遍历打印
            postorderTraversal();
            return;
        }
        // 传了visitor,我就根据实现进行操作
        this.postorderTraversalExt(root, visitor);
    }

    private void postorderTraversalExt(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) {
            return;
        }
        postorderTraversalExt(node.left, visitor);
        postorderTraversalExt(node.right, visitor);
        if (visitor.stop) {
            return;
        }
        visitor.stop = visitor.visit(node.element);
    }

    /**
     * 层序遍历增强
     */
    public void levelOrderTraversalExt(Visitor<E> visitor) {
        if (null == visitor) {
            levelOrderTraversal();
            return;
        }
        Node<E> node = root;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            node = queue.poll();
            if (visitor.visit(node.element)) return;
            // 从左向右，先向队尾加左
            if (node.left != null) {
                queue.offer(node.left);
            }
            // 从左向右，后向队尾加右节点
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    /**
     * 计算二叉树的高度
     */
    public int height() {
        if (root == null) return 0;
        return height(root);
    }

    /**
     * 计算 二叉树 任一 节点的高度
     * 使用递归实现：画图归纳,任意节点高度=Math.max(左子节点高度，右子节点高度)+1
     */
    private int height(Node<E> node) {
        // 一致向左递归或者向右递归，最后到达叶子节点的左右节点 ,不存在，返回0
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }


    /**
     * 层序遍历计算树高
     * 归纳: 初始height=0,每一层完成出队,height++,关键在于每一层节点全部出队，下一层的levelSize=queue.size()
     */
    public int height2() {
        //从根节点开始层序遍历
        Node<E> node = root;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(node);
        int height = 0;//记录树高
        int levelSize = 1;// 记录每一层节点个数,根节点所在第一层LevelSize=1
        while (!queue.isEmpty()) {
            node = queue.poll();
            levelSize--;
            // 左右节点全部入队
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            if (levelSize == 0) {
                levelSize = queue.size();
                height++;
            }
        }
        return height;
    }

    /**
     * 判断一颗 二叉树 是否是 完全二叉树
     * 完全二叉树:节点的度可能是0,1,2 ，如果有度为1的节点只能有一个，且向左靠齐
     * 需要遍历整个二叉树的每个节点,根据左右子节点是否为空进行区分
     */
    public boolean isComplete() {
        if (root == null) return false;
        // 层序遍历方式判断是否完全二叉树
        Node<E> node = root;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(node);
        boolean isLeaf = false;
        while (!queue.isEmpty()) {
            node = queue.poll();
            if (isLeaf && !node.isLeaf()) {
                return false;
            }
            if (node.left != null) {
                queue.offer(node.left);
            } else if (node.right != null) {
                // 一个节点，左子节点为空,右子节点不为空，一定不是完全二叉树
                return false;
            }
            if (node.right != null) {
                queue.offer(node.right);
            } else {
                // 当前出队节点的左子节点可能为空也可能不空,右子节点为空
                // 无论左子节点是否为空,只有保证之后出队的节点都是叶子节点才能是 完全二叉树
                isLeaf = true;
            }
        }
        return true;
    }

    /**
     * 根据四种遍历实现 toString打印
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
     * 前序遍历打印
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
     * 中序遍历打印
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
     * 后序遍历打印
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

    // 工具类打印器的使用
    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>) node).right;
    }

    @Override
    public Object string(Object node) {
        return ((Node<E>) node).element;
        // 添加打印父节点元素
       /* Node<E> newNode = (Node<E>) node;
        String parent = newNode.parent == null ? "null" : newNode.parent.element+"";
        return " parent_" + parent + " > ele_"+ newNode.element;*/
    }

    private static class Node<E> {
        private E element;
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;

        /**
         * 除了根节点，任意节点的parent一定非空,而左右节点有可能是空的，比较适合提供如下实例方法
         */
        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public boolean hasTwoChild() {
            return left != null && right != null;
        }
    }
}
