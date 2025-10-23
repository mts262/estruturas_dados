package br.edu.ifba.bsi.ed.list;

public interface Listable {
    void insert (Object data, int logicalIndex);
    void append(Object data);
    Object select (int logicalIndex);
    Object[] selectAll();
    void update(Object data, int logicalIndex);
    Object delete (int logicalIndex);
    void clear();

    boolean isEmpty();
    boolean isFull();

    String print();
}
