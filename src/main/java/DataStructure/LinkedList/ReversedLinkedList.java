package DataStructure.LinkedList;

public class ReversedLinkedList {

    public static SinglyLinkedList getRevertLinkedList(SinglyLinkedList linkedList)
    {
        Node current = linkedList.first;
        Node prev = null;

        while(current != null)
        {
            Node nextTemp = current.next;
            current.next = prev;
            prev = current;
            current = nextTemp;
        }

        linkedList.first = prev;
        return linkedList;
    }

    public static void main(String[] args) {
        // Given a linked list {1,2,3,4,5}
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);

        SinglyLinkedList result = getRevertLinkedList(list);
        // return a reversed linked list {5,4,3,2,1}
        Node current = result.first;
        while(current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }
}
