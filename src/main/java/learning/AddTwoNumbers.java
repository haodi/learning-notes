package learning;

import learning.entries.ListNode;

class AddTwoNumbers {

    public static void main(String[] args) {

//        ListNode l1 = new ListNode(2);
//        l1.next = new ListNode(4);
//        l1.next.next = new ListNode(3);
//        System.out.println("l1：" + l1);
//
//        ListNode l2 = new ListNode(5);
//        l2.next = new ListNode(6);
//        l2.next.next = new ListNode(4);
//        System.out.println("l2：" + l2);
//
//        System.out.println("result：" + addTwoNumbers(l1, l2));

        System.out.println(addTwoLargestNumbers("222222", "99999"));

    }


    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;

        while (p != null || q != null) {
            int x = p != null ? p.val : 0;
            int y = q != null ? q.val : 0;
            int sum = carry + x + y;

            // 是否需要进位
            carry = sum / 10;

            curr.next = new ListNode(sum % 10);
            curr = curr.next;

            if (p != null) {
                p = p.next;
            }

            if (q != null) {
                q = q.next;
            }
        }

        if (carry > 0) {
            curr.next = new ListNode(carry);
        }

        return dummyHead.next;
    }

    /**
     *  变形，两个超大的整数相加
     */
    public static String addTwoLargestNumbers(String s1, String s2) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();

        int carry = 0;
        String result = "";

        for (int i = 0; i < c1.length || i < c2.length; i++) {
            int p = c1.length - 1 - i;
            int q = c2.length - 1 - i;

            int x = p >= 0 ? c1[p] - '0' : 0;
            int y = q >= 0 ? c2[q] - '0' : 0;

            int sum = x + y + carry;
            carry = sum / 10;

            result = sum % 10 + result;
        }

        if (carry > 0) {
            result = carry + result;
        }
        return result;
    }
}