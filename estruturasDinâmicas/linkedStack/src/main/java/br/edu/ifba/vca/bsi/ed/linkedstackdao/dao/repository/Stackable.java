package br.edu.ifba.vca.bsi.ed.linkedstackdao.dao.repository;

public interface Stackable <T> {
    void push(T data);
    T pop();
    T peek();
    void update (T newData);

    boolean isEmpty();
    boolean isFull();
    String print();
}
