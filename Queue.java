public class Queue implements QueueInterface {

    // Liste chaînée pour implémenter la file
    private LinkedList list = new LinkedList();

    // Ajouter un élément à la file
    @Override
    public void enqueue(int element) {
        list.addLast(element);
    }

    // Retirer et renvoyer le premier élément de la file
    @Override
    public int dequeue() {
        return list.removeFirst();
    }

    // Obtenir la taille de la file
    @Override
    public int size() {
        return list.size();
    }

    // Vérifier si la file est vide
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
