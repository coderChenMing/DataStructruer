package 链表;

/**
 * Project: DataStructruer
 * File Created at 2022-01-18 00:26:0:26
 * {@link }
 *
 * @author <a href="mailto:">chenming</a>
 * @version 1.0.0
 * @type _206_反转一个链表.java
 * @desc 1->2->3->4->5 --> 5->4->3->2->1
 * @date 2022/1/18 0018 0:26
 */
public class _206_反转一个链表 {

    public ListNode reverseList1(ListNode head) {
        // 使用递归的方式
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList1(head.next);
        head.next.next= head;
        head.next = null;
        return newHead;
    }
    public ListNode reverseList2(ListNode head) {

        return null;
    }
}
