package 二叉树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * homebrew开发者面试google失败的题目
 * 翻转：根节点的左右子树进行互换
 * 关键点在于要遍历整个 二叉树 的所有节点
 * 所以既可以使用递归实现也可以使用层序遍历进行迭代
 */
public class _226_翻转二叉树 {

    /**
     * 前序遍历进行翻转
     */
    public TreeNode invertTree1(TreeNode root) {
        if (root == null) return root;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree1(root.left);
        invertTree1(root.right);
        return root;
    }

    /**
     * 中序遍历进行翻转
     */
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) return root;

        invertTree1(root.left);
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree1(root.left);// 由于发生了交换，需要递归修改后的root.left
        return root;
    }

    /**
     * 后序遍历进行翻转
     */
    public TreeNode invertTree3(TreeNode root) {
        if (root == null) return root;
        invertTree1(root.left);
        invertTree1(root.right);
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        return root;
    }

    /**
     * 层序遍历进行翻转
     */
    public TreeNode invertTree4(TreeNode root) {
        if (root == null) return root;
        TreeNode node = root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            node = queue.poll();
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return root;
    }
}
