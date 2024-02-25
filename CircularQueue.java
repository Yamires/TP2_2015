public class CircularQueue {

    // nested node
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

    //
    private Node tail = null;
    private int size = 0;

    // methods
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int front() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        return tail.getNext().getNext().getElement();
    }

    public int rear() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return tail.getElement();
    }


    public void addFirst(Node e) {
        if (size == 0) {
            tail = new Node(e.getElement(), null);
            tail.setNext(tail);
        } else {
            Node newest = new Node(e.getElement(), tail.getNext());
            tail.setNext(newest);
        }
        size++;
    }

    public void enqueue(Node n) {
        addFirst(n);
        tail = tail.getNext();
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        Node head = tail.getNext();
        if (head == tail) {
            tail = null;
        } else {
            tail.setNext(head.getNext());
            size--;
        }
        return head.getElement();
    }

    public void reverse() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }

        Node prev = null;
        Node current = tail.getNext();
        Node next;

        do {
            next = current.getNext();
            current.setNext(prev); // Fixing this line
            prev = current;
            current = next;
        } while (current != tail.getNext());

        tail.setNext(prev); // Fixing this line
    }

    public boolean checkInQueue(int value) {
        if (isEmpty()) return false;

        Node current = tail.getNext();

        do {
            if (current.getElement() == value) {
                return true;
            }
            current = current.getNext();
        } while (current != tail.getNext());

        return false;
    }

    public void remove(int value) {
        if (isEmpty()) return;

        tail.setNext(remove(tail.getNext(), value));

        if (tail.getNext().getElement() == value) {
            tail.setNext(tail.getNext().getNext());
        }
    }

    public Node remove(Node current, int value) {
        if (current.getNext().getElement() == value) {
            current.setNext(current.getNext().getNext());
            return current; // Return the current node after removal
        }
        return remove(current.getNext(), value); // Return the result of the recursive call
    }
}









