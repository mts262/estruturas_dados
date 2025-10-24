package br.edu.ifba.vca.bsi.ed.linkeddequedao.dao.repository;

import java.util.NoSuchElementException;

/**
 * Implementação de um Fila com Dupla Terminação Genérica
 *
 * @param <T>
 * @author Matheus
 * @version 1.0
 * @since 24/10/2025
 */
public class LinkedDEQue <T> implements DEQueable <T>{

    /**Indica a quantidae de elementos da fila*/
    private int amount;
    /**Indica a capidade máxima da fila*/
    private int capacity;
    /**Indica o início da fila*/
    private DoubleNode<T> head;
    /**Indica o fim da fila*/
    private DoubleNode<T> tail;

    /**
     * Construtor que não recebe parâmetros
     */
    public LinkedDEQue() {
        this(10);
    }

    /**
     * Construtor que é passado como parâmetro um inteiro
     *
     * @param capacity, número máximo de elementos que podem ser armazenados
     */
    public LinkedDEQue(int capacity) {
        amount = 0;
        this.capacity = capacity;
        head = null;
        tail = null;
    }

    /**
     * Enfileira no início da fila
     * 
     * @param data, dado a ser enfileirado
     * @throws java.util.NoSuchElementException, se a fila estiver cheia
     */
    @Override
    public void beginEnqueue(T data) {
        if (isFull()) {
            throw new NoSuchElementException("Queue is full!");
        }
        DoubleNode<T> newNode = new DoubleNode<>();
        newNode.setData(data);
        if (isEmpty()) {
            tail = newNode;
            head = newNode;
        } else {
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = head.getPrevious();
        }
        amount++;
    }

    /**
     * Enfileira no final da fila
     *
     * @param data, dado a ser enfileirado
     * @throws java.util.NoSuchElementException, se a fila estiver cheia
     */
    @Override
    public void endEnqueue(T data) {
        if (isFull()) {
            throw new NoSuchElementException("Queue is full!");
        }
        DoubleNode<T> newNode = new DoubleNode<>();
        newNode.setData(data);
        if (isEmpty()) {
            tail = newNode;
            head = newNode;
        } else {
            newNode.setPrevious(tail);
            tail.setNext(newNode);
            tail = tail.getNext();
        }
        amount++;
    }

    /**
     * Desenfileira do início da fila
     *
     * @return dado desenfileirado
     * @throws java.util.NoSuchElementException, se a fila estiver vazia
     */
    @Override
    public T beginDequeue(){
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty!");
        }
        amount--;
        T auxData = head.getData();
        head = head.getNext();
        if (!isEmpty()) {
            head.setPrevious(null);
        } else {
            tail = null;
        }
        return auxData;
    }

    /**
     * Desenfileira do final da fila
     *
     * @return dado desenfileirado
     * @throws java.util.NoSuchElementException, se a fila estiver vazia
     */
    @Override
    public T endDequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty!");
        }
        amount--;
        T auxData = tail.getData();
        tail = tail.getPrevious();
        if (!isEmpty()) {
            tail.setNext(null);
        } else {
            head = null;
        }
        return auxData;
    }

    /**
     * Retorna o dado do início da fila
     *
     * @return dado do início da fila
     * @throws java.util.NoSuchElementException, se a fila estiver vazia
     */
    @Override
    public T front(){
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty!");
        }
        return head.getData();

    }

    /**
     * Retorna o dado do final da fila
     *
     * @return dado do final da fila
     * @throws java.util.NoSuchElementException, se a fila estiver vazia
     */
    @Override
    public T rear() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty!");
        }
        return tail.getData();
    }

    /**
     * Verifica se a fila está cheia
     *
     * @return true se a fila está vazia, e false caso contrário
     */
    @Override
    public boolean isFull() {
        return amount == capacity;
    }

    /**
     * Verifica se a fila está vazia
     *
     * @return true se a fila está vazia, e false caso contrário
     */
    @Override
    public boolean isEmpty() {
        return amount == 0;
    }

    /**
     * Imprime os dados armazenados da fila, do começo para o final
     *
     * @return String com os dados entre colchetes e separados por vígula
     */
    @Override
    public String printFrontToRear() {
        String result = "";
        DoubleNode<T> aux = head;

        for (int i = 0; i < amount; i++) {
            result += aux.getData();
            aux = aux.getNext();
            if (i != amount-1) {
                result += ", ";
            }
        }
        return "[" +result+ "]";
    }

    /**
     * Imprime os dados armazenados da fila, do final para o começo
     *
     * @return String com os dados entre colchetes e separados por vígula
     */
    @Override
    public String printRearToFront() {
        String result = "";
        DoubleNode<T> aux = tail;

        for (int i = 0; i < amount; i++) {
            result += aux.getData();
            aux = aux.getPrevious();
            if (i != amount-1) {
                result += ", ";
            }
        }
        return "[" +result+ "]";
    }
}
