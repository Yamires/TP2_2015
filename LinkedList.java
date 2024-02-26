public class LinkedList {

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
    public LinkedList() {
    }

    // public void addFirst(int element): Ajoute un élément au début de la liste
    public void addFirst(int element) {
        head = new Node(element, head);

        if (isEmpty()) {
            tail = head;
        }
        size++;
    }
    // public boolean isEmpty(): Renvoie un boolean true si la liste est vide et faux sinon
    public boolean isEmpty() {
        return size == 0;
    }

    // public void addLast(int element): Ajoute un élément à la fin de la liste
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

    // public int removeFirst(): supprime le premier élément dans la liste
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

    // public int removeLast(): supprime le dernier élément dans la liste
    public void removeLast() {
        if (isEmpty()) {
            throw new IllegalStateException("The list is empty");
        }

        if (size == 1) {
            head = null;
            tail = null;
        } else {
            Node current = head;
            Node previous = null;
            while (current.next != null) {
                previous =current;
                current = current.next;
            }
            previous.next = null;
            tail = previous;
        }
        size--;
    }

    // public int size(): renvoie la taille de la liste
    public int size() {
        return size;
    }

    // public int first(): renvoie la première valeur dans la liste sans la supprimer.
    public int first() {
        if (isEmpty()) {
            throw new IllegalStateException("The list is empty");
        }
        return head.getElement();
    }

    // public int last(): renvoie la dernière valeur dans la liste sans la supprimer.
    public int last() {
        if (isEmpty()) {
            throw new IllegalStateException("The list is empty");
        }

        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        return current.getElement();
    }

    // public void removeValue(int value): retirer tous les éléments avec une certaine valeur (récursif)
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

    // public int returnNLast(int nLast): renvoie le n-ième élément en partant de la fin en O(n) (itératif)
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

    // public boolean checkInList(int value): renvoie vrai si l'élément est dans la liste, faux sinon (itératif)
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

    // public int maxValue: Renvoie la valeur maximale stockée quand la liste (récursif)
    public int maxValue() {
        if (isEmpty()) {
            throw new IllegalStateException("The list is empty");
        }
        return MaxValueRecursive(head, head.element);
    }
    private int MaxValueRecursive(Node current, int maxValue) {
        if (current == null) {
            return maxValue;
        }

        if (current.element > maxValue) {
            maxValue = current.element;
        }

        return MaxValueRecursive(current.next, maxValue);
    }

    // public void insertionSort(): trié dans la liste ordre croissant (itératif)
    public void insertionSort() {
        if (head == null || head.next == null) {
            return;
        }
        Node current = head;

        while (current != null) {
            Node next = current.next;
            addInOrder(current.getElement());
            current = next;
        }
        head = sortedHead;
    }


    // public void addInOrder(): ajouter un élément à une liste déjà trier (recursif)
    public void addInOrder(int value) {
        sortedHead = addInOrderRecursive(sortedHead, value);
    }

    // Recursive method to insert an element into a sorted list
    private Node addInOrderRecursive(Node sortedHead, int value) {
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
