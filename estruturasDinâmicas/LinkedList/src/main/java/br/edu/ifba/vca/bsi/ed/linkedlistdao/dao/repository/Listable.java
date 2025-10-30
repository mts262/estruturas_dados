package br.edu.ifba.vca.bsi.ed.linkedlistdao.dao.repository;

public interface Listable<T> {
    void append(T data);
    void insert(int index, T data);
    void clear();
    T delete(int index);
    T select(int index);
    T[] selectAll();
    void update(int index, T data);

    int size();
    boolean isFull();
    boolean isEmpty();
    String print();
}
