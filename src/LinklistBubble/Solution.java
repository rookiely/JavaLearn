package LinklistBubble;

/**
 * Created by john on 2018/5/29.
 */

class ListNode{
    int val;
    ListNode next;

    ListNode(int value) {
        val = value;
    }
}

public class Solution {

    /**
     * 实现单链表的冒泡排序
     *
     * @param
     */

    public static ListNode construct(ListNode head) {
        /**
         * 关于交换可以分为
         * 1、交换值
         * 2、交换节点
         */
        /*交换值*/
        ListNode curnode = head;
        ListNode tailnode = null;
        while (curnode != tailnode) {
            while (curnode.next != tailnode) {
                if (curnode.next.val < curnode.val) {
                    int temp = curnode.val;
                    curnode.val = curnode.next.val;
                    curnode.next.val = temp;
                }
                curnode = curnode.next;
            }
            tailnode = curnode;
            curnode = head;
        }
        return head;
    }



    public static void main(String[] args) {

    }
}
