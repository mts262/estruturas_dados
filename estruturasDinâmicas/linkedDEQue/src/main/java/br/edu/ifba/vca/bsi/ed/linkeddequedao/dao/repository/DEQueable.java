package br.edu.ifba.vca.bsi.ed.linkeddequedao.dao.repository;

public interface DEQueable <T> {
    void beginEnqueue(T data);
    void endEnqueue(T data);
    T beginDequeue();
    T endDequeue();
    T front();
    T rear();

    boolean isFull();
    boolean isEmpty();
    String printFrontToRear();
    String printRearToFront();
}
