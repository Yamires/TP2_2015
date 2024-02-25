public class Queue implements QueueInterface {

    private SinglyLinkedList list = new SinglyLinkedList();


    @Override
    public void enqueue(int element) {
        list.addLast(element);
    }

    @Override
    public int dequeue() {
        return list.removeFirst();
    }

    @Override
    public int size() {
         return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}


