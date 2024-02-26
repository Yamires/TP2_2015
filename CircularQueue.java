public class CircularQueue implements QueueInterface {
    private static final int maxCapacity = 100;
    private int front;
    private int storedSize;
    private int[] array;

    public CircularQueue(final int capacity) {
        array = new int[capacity];
    }

    // Renvoie la taille de la file
    public int size() {
        return this.storedSize;
    }

    //Renvoie si la file est vide ou non
    public boolean isEmpty() {
        return this.storedSize == 0;
    }

    public boolean isFull() {
        return this.storedSize == this.array.length;
    }

    //Permet d'obtenir le premier élément de la file
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

    //Permet d'obtenir le dernier élément de la file
    public int rear() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return array[(front + storedSize - 1) % array.length];
    }

    // Insère une nouvelle valeur dans la file
    public void enqueue(int e) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full");
        }
        int avail = (this.front + this.storedSize) % array.length;
        array[avail] = e;
        this.storedSize++;
    }

    // Supprime un élément de la file
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

    //Imprime la file d'attente circulaire
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
    public void reverse(){
        if (isEmpty()){
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
    //Renvoie vrai si l'élément est dans la file, faux sinon
    public boolean checkInQueue(int value) {
        for (int i = 0; i < storedSize; i++) {
            if (array[(front + i) % array.length] == value) {
                return true;
            }
        }
        return false;
    }

    // Retire la première apparition de la valeur
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
}
