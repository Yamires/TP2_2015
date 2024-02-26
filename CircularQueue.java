public class CircularQueue implements QueueInterface {

    private static final int maxCapacity = 100;
    private int front;
    private int storedSize;
    private int[] array;

    public CircularQueue(final int capacity) {
        array = new int[capacity];
    }

    @Override
    public int size() {
        return this.storedSize;
    }

    @Override
    public boolean isEmpty() {
        return this.storedSize == 0;
    }

    public boolean isFull() {
        return this.storedSize == this.array.length;
    }

    public int front() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return array[front];
    }

    public Integer first(){
        if (isEmpty()) {
            return null;
        }
        return array[front];
    }

    public int rear() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return array[(front + storedSize - 1) % array.length];
    }

    @Override
    public void enqueue(int e) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full");
        }
        int avail = (this.front + this.storedSize) % array.length;
        array[avail] = e;
        this.storedSize++;
    }

    @Override
    public int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        int element = first();
        array[front] = 0; // Assuming 0 represents an empty slot
        this.front = (this.front + 1) % array.length;
        this.storedSize--;
        return element;
    }

    public boolean checkInQueue(int value) {
        for (int i = 0; i < storedSize; i++) {
            if (array[(front + i) % array.length] == value) {
                return true;
            }
        }
        return false;
    }

    public void remove(int value) {
        for (int i = 0; i < storedSize; i++) {
            if (array[(front + i) % array.length] == value) {
                int indexToRemove = (front + i) % array.length;
                System.arraycopy(array, indexToRemove + 1, array, indexToRemove, storedSize - i - 1);
                array[(front + storedSize - 1) % array.length] = 0;
                storedSize--;
                return;
            }
        }
    }

    public void print() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        int current = front;
        for (int i = 0; i < storedSize; i++) {
            System.out.println(array[current]);
            current = (current + 1) % array.length;
        }
        // Reset front to its original value
        front = (front + storedSize) % array.length;
    }


    //Inverser l'ordre de la file d'attente circulaire
    public void reverse() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        int[] tempArray = new int[storedSize];
        int index = 0;

        while (!isEmpty()) {
            tempArray[index++] = dequeue();
        }

        for (int i = index - 1; i >= 0; i--) {
            enqueue(tempArray[i]);
        }
    }


}








