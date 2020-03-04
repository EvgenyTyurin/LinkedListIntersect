/**
 * Find intersection of two linked lists.
 * First we find size of lists and tails, if tails not match - no intersection.
 * Second we cycle a lists from head, but shorter list with delay = sizeBig - sizeSmall
 * until pointer meat at intersection.
 */

public class LinkedListIntersect {

    // Run point
    public static void main(String[] args) {
        // Prepare intersecting lists
        MyLinkedList.Node<Integer> node0 = new MyLinkedList.Node<>(0);
        MyLinkedList.Node<Integer> node1 = new MyLinkedList.Node<>(1);
        MyLinkedList.Node<Integer> node2 = new MyLinkedList.Node<>(2);
        MyLinkedList.Node<Integer> node3 = new MyLinkedList.Node<>(3);
        MyLinkedList.Node<Integer> node4 = new MyLinkedList.Node<>(4);
        MyLinkedList<Integer> list1 = new MyLinkedList<>();
        list1.add(node2);
        list1.add(node3);
        list1.add(node4);
        MyLinkedList<Integer> list2 = new MyLinkedList<>();
        list2.add(node0);
        list2.add(node1);
        list2.add(node3);
        System.out.println(list1); // [2->3->4]
        System.out.println(list2); // [0->1->3->4]
        // Find last node and size of each list
        TailAndSize result1 = getTailAndSize(list1); // tail = 4
        TailAndSize result2 = getTailAndSize(list2); // tail = 4
        if (result1.tail != result2.tail) {
            System.out.println("Tails not the same - lists NOT intersect");
            return;
        }
        // Two pointers: shorter will start later
        MyLinkedList.Node shorter = result1.size < result2.size ? list1.head : list2.head;
        MyLinkedList.Node longer = result1.size < result2.size ? list2.head : list1.head;
        // Delay till shorter starts
        int delta = Math.abs(result1.size - result2.size);
        // Pointers starts from heads of lists until they meat at intersection
        while (shorter != longer) {
            longer = longer.next();
            // Time to start shorter
            if (delta == 0) {
                shorter = shorter.next();
            }
            delta--;
        }
        System.out.println("Lists intersect at " + shorter.getData()); // 3
    }

    // We want get last node and size of list in one piece of data
    static class TailAndSize {
        MyLinkedList.Node tail;
        int size;

        public TailAndSize(MyLinkedList.Node tail, int size) {
            this.tail = tail;
            this.size = size;
        }
    }

    // Returns last node and size of list
    static TailAndSize getTailAndSize(MyLinkedList list) {
        MyLinkedList.Node node = list.head;
        int length = 0;
        while (node.next() != null) {
            node = node.next();
            length++;
        }
        return new TailAndSize(node, length);
    }

}
