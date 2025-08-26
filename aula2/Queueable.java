package aula2;

public interface Queueable {

    void enqueue(Object data);
    Object dequeue();
    Object front();

    boolean isEmpty();
    boolean isFull();
    String print();
}
