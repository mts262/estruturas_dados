package br.edu.ifba.vca.bsi.ed.linkedstackdao.dao.repository;

public class DoubleNode <T> {
    private T data;
    private DoubleNode<T> next;
    private DoubleNode<T> previous;

    public DoubleNode<T> getNext() {
        return next;
    }

    public DoubleNode<T> getPrevious() {
        return previous;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setNext(DoubleNode<T> next) {
        this.next = next;
    }

    public void setPrevious(DoubleNode<T> previous) {
        this.previous = previous;
    }
}
