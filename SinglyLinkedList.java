public class SinglyLinkedList {
    // nested Node Class
    private static class Node {
        private int element;
        private Node next;

        public Node(int e, Node n) {
            element = e;
            next = n;
        }

        public Node(int e) {
            this(e, null);
        }

        public int getElement() {
            return element;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node n) {
            next = n;
        }
    }

    private Node head = null;
    private Node tail = null;
    private Node sortedHead = null;
    private int size = 0;

    // constructor
    public SinglyLinkedList() {
    }

    public void addFirst(int element) {
        head = new Node(element, head);

        if (isEmpty()) {
            tail = head;
        }
        size++;
    }

    private boolean isEmpty() {
        return size == 0;
    }

    public void addLast(int element) {
        Node newest = new Node(element);
        if (isEmpty()) {
            head = newest;
        } else {
            tail.setNext(newest);
        }
        tail = newest;
        size++;
    }

    public int removeFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("The list empty");
        }
        int answer = head.getElement();
        head = head.getNext();
        size--;
        if (size == 0) {
            tail = null;
        }
        return answer;
    }

    public void removeLast() {
        if (isEmpty()) {
            throw new IllegalStateException("The list is empty");
        }

        if (size == 1) {
            head = null;
            tail = null;
        } else {
            Node current = head;
            while (current.next != tail) {
                current = current.next;
            }
            current.next = null;
            tail = current;
        }
        size--;
    }

    public int size() {
        return size;
    }

    public int first() {
        if (isEmpty()) {
            throw new IllegalStateException("The list is empty");
        }
        return head.element;
    }

    public int last() {
        if (isEmpty()) {
            throw new IllegalStateException("The list is empty");
        }

        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        return current.element;
    }

    public void removeValue(int value) {
        head = removeValueRecur(head, value);
    }

    private Node removeValueRecur(Node current, int value) {
        if (current == null) {
            return null;
        }
        if (current.element == value) {
            size--;
            return removeValueRecur(current.next, value);
        }
        current.next = removeValueRecur(current.next, value);
        return current;
    }

    public int returnNLast(int nLast) {
        if (nLast <= 0 || nLast > size) {
            throw new IllegalArgumentException("Invalid index" + nLast);
        }

        int positionFromStart = size - nLast;

        Node current = head;

        for (int i = 0; i < positionFromStart; i++) {
            current = current.next;
        }

        return current.element;
    }

    public boolean checkInList(int value) {
        Node current = head;
        while (current != null) {
            if (current.element == value) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public int findMaxValue() {
        if (isEmpty()) {
            throw new IllegalStateException("The list is empty");
        }
        return findMaxValueRecursive(head, head.element);
    }

    private int findMaxValueRecursive(Node current, int maxValue) {
        if (current == null) {
            return maxValue;
        }

        if (current.element > maxValue) {
            maxValue = current.element;
        }

        return findMaxValueRecursive(current.next, maxValue);
    }

    public void insertionSort() {
        if (head == null || head.next == null) {
            return;
        }

        Node sortedHead = null;
        Node current = head;

        while (current != null) {
            Node next = current.next;
            addInOrder(current.element); // Insert current node's data in order
            current = next;
        }

        head = sortedHead;
    }
    public void addInOrder(int value) {
        sortedHead = addInOrderRecursive(sortedHead, value);
    }

    public Node addInOrderRecursive(Node sortedHead, int value) {
        if (sortedHead == null || sortedHead.getElement() >= value) {
            Node newNode = new Node(value);
            newNode.next = sortedHead;
            return newNode;
        } else {
            sortedHead.next = addInOrderRecursive(sortedHead.next, value);
            return sortedHead;
        }
    }


}
