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
    }

    /**
     * Reseta a lista para o estado inicial sem elementos
     */
    @Override
    public void clear() {
        amount = 0;
        head = null;
        tail = null;
    }

    /**
     * Deleta um dado da lista, escolhendo o que deseja remover
     *
     * @param index, indíce do dado a ser removido
     * @return dado removido
     * @throws UnderflowException, se a lista estiver vazia
     * @throws IndexOutOfBoundsException, se o passado indíce for inválido
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
        if (index <= (amount/2)) {     //começa pelo head
            aux = head;
            for (int i = 0; i < index; i++) {
                aux = aux.getNext();
            }
        } else {                     //começa pelo tail
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
        return data;
    }

    /**
     * Seleciona um dado da lista a escolha
     *
     * @param index, indíce do dado a ser selecionado
     * @return dado selecionado
     * @throws UnderflowException, se a lista estiver vazia
     * @throws IndexOutOfBoundsException, se o idíce for inválido
     */
    @Override
    public T select(int index) {
        T data = null;
        if (isEmpty()) {
            throw new UnderflowException("List is empty!");
        }
        if (index < 0 || index >= amount) {
            throw new IndexOutOfBoundsException("Invalid index!");
        }
        DoubleNode<T> aux;
        if (index <= (amount/2)) {     //começa pelo head
            aux = head;
            for (int i = 0; i < index; i++) {
                aux = aux.getNext();
            }
        } else {                     //começa pelo tail
            aux = tail;
            for (int i = 0; i < amount - 1 - index; i++) {
                aux = aux.getPrevious();
            }
        }
        data = aux.getData();
        return data;
    }

    /**
     * Seleciona todos os dados da lista e o retornam na forma de array
     *
     * @return array com os dados
     */
    @Override
    public T[] selectAll() {
        T[] temp = (T[])new Object[size()];
        DoubleNode<T> aux = head;

        for (int i = 0; i < amount; i++) {
            temp[i] = aux.getData();
            aux = aux.getNext();
        }

        return temp;
    }

    /**
     * Atualiza um dado da lista a escolha
     *
     * @param index, indíce do dado a ser atualizado
     * @param data, novo dado
     * @throws UnderflowException, se a lista estiver vazia
     * @throws IndexOutOfBoundsException, se o indíce for inválido
     */
    @Override
    public void update(int index, T data) {
        if (isEmpty()) {
            throw new UnderflowException("List is empty!");
        }
        if (index < 0 || index >= amount) {
            throw new IndexOutOfBoundsException("Invalid index!");
        }
        DoubleNode<T> aux;
        if (index <= (amount/2)) {     //começa pelo head
            aux = head;
            for (int i = 0; i < index; i++) {
                aux.getNext();
            }
        } else {                     //começa pelo tail
            aux = tail;
            for (int i = 0; i < amount - 1 - index; i++) {
                aux = aux.getPrevious();
            }
        }
        aux.setData(data);
    }

    /**
     * Quantidade de elementos que formam o tamanho da lista
     *
     * @return número de elementos na lista
     */
    @Override
    public int size() {
        return amount;
    }

    /**
     * Verifica se a lista está cheia
     *
     * @return true se a lista estiver cheia, e false caso o contrário
     */
    @Override
    public boolean isFull() {
        return amount == capacity;
    }

    /**
     * Verifica se a lista está vazia
     *
     * @return true se a lista estiver vazia, e false caso o contrário
     */
    @Override
    public boolean isEmpty() {
        return amount == 0;
    }

    /**
     * Imprime os dados da fila
     *
     * @return String com todos os dados entre colchetes e separados por vígula
     */
    @Override
    public String print() {
        String result = "";
        DoubleNode<T> aux = head;

        for (int i = 0; i < amount; i++) {
            result += aux.getData();
            if (i != amount-1) {
                result+=", ";
            }
            aux = aux.getNext();
        }

        return "[" +result+ "]";
    }
}
