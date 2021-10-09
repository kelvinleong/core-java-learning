package algorithms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CycleListDetect {
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Node {
        int value;
        Node next;

        public boolean hasNext() { return next != null; }
    }

    public boolean isCycleList(Node head) {
        var slow = head;
        var fast = head;

        while (slow.hasNext() && fast.hasNext()) {
            slow = slow.getNext();
            fast = fast.getNext();

            if (fast.hasNext()) {
                fast = fast.getNext();
            } else {
                return false;
            }

            if (slow.getValue() == fast.getValue() && slow.getNext() == fast.getNext()) {
                return true;
            }
        }

        return false;
    }
}
