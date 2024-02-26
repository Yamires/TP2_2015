
public class Main {

 
  public static void main(String[] args){
        SinglyLinkedList SinglyLinkedList=new SinglyLinkedList();
    
        //On crée une liste ayant 6 valeur différente et non trier 
        SinglyLinkedList.addFirst(40);
        SinglyLinkedList.addLast(20);
        SinglyLinkedList.addLast(5);
        SinglyLinkedList.addLast(10);
        SinglyLinkedList.addLast(15);
        SinglyLinkedList.addLast(25);
    
        //on commence par trier la liste
        SinglyLinkedList.insertionSort();
    
        //on enlève plusieurs éléments de la liste de différentes façons
        SinglyLinkedList.removeValue(10);
        SinglyLinkedList.removeFirst();
        SinglyLinkedList.removeLast();
    
        //on imprime les autres opérations pour voir ce qui nous reste de la liste
        System.out.println(SinglyLinkedList.size());
        System.out.println(SinglyLinkedList.first());
        System.out.println(SinglyLinkedList.last());
        System.out.println(SinglyLinkedList.isEmpty());
        System.out.println(SinglyLinkedList.maxValue());
        System.out.println(SinglyLinkedList.checkInList(40));
        System.out.println(SinglyLinkedList.returnNLast(2));
    }
}