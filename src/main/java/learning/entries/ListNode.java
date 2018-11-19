package learning.entries;

import java.util.LinkedList;
import java.util.List;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        ListNode node = this;
        List<String> result = new LinkedList<>();

        while (node != null) {
            result.add(String.valueOf(node.val));
            node = node.next;
        }

        return String.join("->", result);
    }
}