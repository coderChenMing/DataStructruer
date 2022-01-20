package 链表;

/**
 * Project: DataStructruer
 * File Created at 2022-01-18 00:19:0:19
 * {@link }
 *
 * @author <a href="mailto:">chenming</a>
 * @version 1.0.0
 * @type _237_删除链表中的节点.java
 * @desc
 * @date 2022/1/18 0018 0:19
 */
public class _237_删除链表中的节点 {
    public void deleteNode(ListNode node) {

        // 思路: 9覆盖1 ,next指向9的next
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public static void main(String[] args) {

    }
}
