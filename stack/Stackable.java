package br.edu.ifba.bsi.ed.stack;

public interface Stackable {
    Object pop();
    void push(Object data);
    Object peek();

    boolean isEmpty();
    boolean isFull();
    String print();
}
