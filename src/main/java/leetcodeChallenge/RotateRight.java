package leetcodeChallenge;

public class RotateRight {
    public static class ListNode {
        int val;
        ListNode next;

        public ListNode (int x) { val = x; }

        public void setNext(ListNode next) {
            this.next = next;
        }

        public ListNode getNext() { return next; }

        public int getVal() { return val; }
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) return null;

        ListNode ptr = head;
        int size = 1;

        // get the size of the list
        while (ptr.next != null) {
            ptr = ptr.next;
            ++size;
        }

        ptr.next = head;

        // detach and rearrange
        ptr = head;
        for (int i = 0; i < size - k % size - 1; ++i) ptr = ptr.next;

        ListNode newHead = ptr.next;
        ptr.next = null;
        return newHead;
    }
}
