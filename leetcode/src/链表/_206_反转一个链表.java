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
            /*
            以链表1->2->3->4->5举例
            第一轮出栈，head为5，head.next为空，返回5
            第二轮出栈，head为4，head.next为5，执行head.next.next=head也就是5.next=4，
                      把当前节点的子节点的子节点指向当前节点
                      此时链表为1->2->3->4<->5，由于4与5互相指向，所以此处要断开4.next=null
                      此时链表为1->2->3->4<-5
                      返回节点5
            第三轮出栈，head为3，head.next为4，执行head.next.next=head也就是4.next=3，
                      此时链表为1->2->3<->4<-5，由于3与4互相指向，所以此处要断开3.next=null
                      此时链表为1->2->3<-4<-5
                      返回节点5
            第四轮出栈，head为2，head.next为3，执行head.next.next=head也就是3.next=2，
                      此时链表为1->2<->3<-4<-5，由于2与3互相指向，所以此处要断开2.next=null
                      此时链表为1->2<-3<-4<-5
                      返回节点5
            第五轮出栈，head为1，head.next为2，执行head.next.next=head也就是2.next=1，
                      此时链表为1<->2<-3<-4<-5，由于1与2互相指向，所以此处要断开1.next=null
                      此时链表为1<-2<-3<-4<-5
                      返回节点5
            出栈完成，最终头节点5->4->3->2->1
         */
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public ListNode reverseList2(ListNode head) {
        /*
            在遍历链表时，将当前节点的 next 指针改为指向前一个节点。由于节点没有引用其前一个节点，
            因此必须事先存储其前一个节点。在更改引用之前，还需要存储后一个节点。最后返回新的头引用。
         */
        // 链接：https://leetcode.cn/problems/reverse-linked-list/solutions/551596/fan-zhuan-lian-biao-by-leetcode-solution-d1k2/
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            /*我们可以申请两个指针，第一个指针叫 pre，最初是指向 null 的。
            第二个指针 cur 指向 head，然后不断遍历 cur。
            每次迭代到 cur，都将 cur 的 next 指向 pre，然后 pre 和 cur 前进一位。
            都迭代完了(cur 变成 null 了)，pre 就是最后一个节点了。*/
            ListNode next = cur.next;// 保留cur.next
            cur.next = pre;//cur 的 next 指向 pre
            pre = cur;//pre前进一位
            cur = next;//cur 前进一位
        }
        return pre;
    }
}
