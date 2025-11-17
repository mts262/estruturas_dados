package br.edu.ifba.vca.bsi.ed.linkedlistdao.dao.repository;

/**
 * Implementção de uma Lista Dinâmica Genérica
 *
 * @param <T>, tipo genérico
 * @version 1.0
 * @since 30/10/2025
 */
public class LinkedList<T> implements Listable<T> {
    /**Indica o início da lista*/
    private DoubleNode<T> head;
    /**Indica o final da lista*/
    private DoubleNode<T> tail;
    /**Indica a capacidade da lista*/
    private int capacity;
    /**Indica a quantidade de elementos na lista*/
    private int amount;

    /** Ponteiros do cursor para selects sequenciais */
    private DoubleNode<T> cursorNode = null;
    private int cursorIndex = -1;

    /** Reseta o cursor sempre que a lista é modificada */
    private void resetCursor() {
        cursorNode = null;
        cursorIndex = -1;
    }

    /**
     * Construtor sem argurmentos, com inicialização da capacidade para 10 elementos
     */
    public LinkedList() {
        this(10);
    }

    /**
     * Construtor com argumneto que determinará a capacidade da lista
     *
     * @param capacity, número máximo de elementos que podem ser armazenados
     */
    public LinkedList(int capacity) {
        this.capacity = capacity;
        amount = 0;
        head = null;
        tail = null;
    }

    /**
     * Insere novo elemento no final da lista
     *
     * @param data, dado a ser inserido
     * @throws OverflowException, se a lista estiver cheia
     */
    @Override
    public void append(T data) {
        if (isFull()) {
            throw new OverflowException("List is full!");
        }
        DoubleNode<T> newNode = new DoubleNode<>(data);
        if (!isEmpty()) {
            tail.setNext(newNode);
        } else {
            head = newNode;
        }
        newNode.setPrevious(tail);
        tail = newNode;
        amount++;

        resetCursor();
    }

    /**
     * Insere um novo elemento dado uma posição a escolha
     *
     * @param index, posição de onde o dado será inserido
     * @param data, dado a ser inserido
     * @throws OverflowException se a lista estiver cheia
     * @throws IndexOutOfBoundsException se o indíce passado for inválido
     */
    @Override
    public void insert(int index, T data) {
        if (isFull()) {
            throw new OverflowException("List is full!");
        }
        if (index < 0 || index >= amount) {
            throw new IndexOutOfBoundsException("Invalid index!");
        }
        DoubleNode<T> newNode = new DoubleNode<>(data);
        DoubleNode<T> previous = null;
        DoubleNode<T> next = head;

        for (int i = 0; i < index; i++) {
            previous = next;
            next = next.getNext();
        }

        if (previous != null) {
            previous.setNext(newNode);
        } else {
            head = newNode;
        }

        if (next != null) {
            next.setPrevious(newNode);
        } else {
            tail = newNode;
        }

        newNode.setNext(next);
        newNode.setPrevious(previous);
        amount++;

        resetCursor();
    }

    /**
     * Reseta a lista para o estado inicial sem elementos
     */
    @Override
    public void clear() {
        amount = 0;
        head = null;
        tail = null;

        resetCursor();
    }

    /**
     * Deleta um dado da lista
     */
    @Override
    public T delete(int index) {
        T data = null;
        if (isEmpty()) {
            throw new UnderflowException("List is empty!");
        }
        if (index < 0 || index >= amount) {
            throw new IndexOutOfBoundsException("Invalid index!");
        }
        DoubleNode<T> aux;
        if (index <= (amount/2)) {
            aux = head;
            for (int i = 0; i < index; i++) {
                aux = aux.getNext();
            }
        } else {
            aux = tail;
            for (int i = 0; i < amount - 1 - index; i++) {
                aux = aux.getPrevious();
            }
        }

        DoubleNode<T> anterior = aux.getPrevious();
        DoubleNode<T> proximo = aux.getNext();

        if (anterior != null) {
            anterior.setNext(proximo);
        } else {
            head = head.getNext();
        }

        if (proximo != null) {
            proximo.setPrevious(anterior);
        } else {
            tail = tail.getPrevious();
        }

        amount--;

        resetCursor();
        return data;
    }

    /**
     * SELECT otimizado com ponteiro (cursor)
     */
    @Override
    public T select(int index) {
        if (isEmpty()) {
            throw new UnderflowException("List is empty!");
        }
        if (index < 0 || index >= amount) {
            throw new IndexOutOfBoundsException("Invalid index!");
        }

        // Caso 1: acessar o próximo elemento sequencialmente
        if (cursorNode != null && index == cursorIndex + 1) {
            cursorNode = cursorNode.getNext();
            cursorIndex++;
            return cursorNode.getData();
        }

        // Caso 2: seleção normal (calcula do zero)
        DoubleNode<T> aux;
        if (index <= (amount / 2)) {
            aux = head;
            for (int i = 0; i < index; i++) {
                aux = aux.getNext();
            }
        } else {
            aux = tail;
            for (int i = amount - 1; i > index; i--) {
                aux = aux.getPrevious();
            }
        }

        cursorNode = aux;
        cursorIndex = index;

        return aux.getData();
    }

    /**
     * Seleciona todos os dados
     */
    @Override
    public T[] selectAll() {
        T[] temp = (T[]) new Object[size()];
        DoubleNode<T> aux = head;

        for (int i = 0; i < amount; i++) {
            temp[i] = aux.getData();
            aux = aux.getNext();
        }

        return temp;
    }

    @Override
    public void update(int index, T data) {
        if (isEmpty()) {
            throw new UnderflowException("List is empty!");
        }
        if (index < 0 || index >= amount) {
            throw new IndexOutOfBoundsException("Invalid index!");
        }
        DoubleNode<T> aux;
        if (index <= (amount/2)) {
            aux = head;
            for (int i = 0; i < index; i++) {
                aux = aux.getNext();
            }
        } else {
            aux = tail;
            for (int i = amount - 1 - index; i > 0; i--) {
                aux = aux.getPrevious();
            }
        }
        aux.setData(data);

        resetCursor();
    }

    @Override
    public int size() {
        return amount;
    }

    @Override
    public boolean isFull() {
        return amount == capacity;
    }

    @Override
    public boolean isEmpty() {
        return amount == 0;
    }

    @Override
    public String print() {
        String result = "";
        DoubleNode<T> aux = head;

        for (int i = 0; i < amount; i++) {
            result += aux.getData();
            if (i != amount-1) {
                result += ", ";
            }
            aux = aux.getNext();
        }

        return "[" + result + "]";
    }
}
