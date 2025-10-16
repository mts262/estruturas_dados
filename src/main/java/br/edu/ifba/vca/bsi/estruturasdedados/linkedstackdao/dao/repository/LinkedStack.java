package br.edu.ifba.vca.bsi.estruturasdedados.linkedstackdao.dao.repository;

import java.util.NoSuchElementException;

/**
 * Implemaentação de uma Pilha Dinâmica Genérica
 *
 * @author azul
 * @version 1.0
 * @since 06/10/2025
 */
public class LinkedStack <T> implements Stackable<T> {
    /**Aponta para o topo da pilha*/
    private DoubleNode<T> topPointer;
    /**Indica o número atual da pilha*/
    private int numberElements;
    /**Indica o número máximo de elementos da pilha*/
    private int maxElements;

    /**
     * Construtor que não recebe parametro
     *
     */
    public LinkedStack () {
        this(10);
    }

    /**
     * Construtor que recebe um inteiro
     *
     * @param  maxElements, contém o número máximo de elementos que podem ser armazenados
     */
    public LinkedStack (int maxElements) {
        topPointer = null;
        numberElements = 0;
        this.maxElements = maxElements;
    }

    /**
     * Empilha na pilha
     *
     * @param data, elemento que será empilhado na plha
     * @throws NoSuchElementException, quando a pilha está cheia
     */
    @Override
    public void push(T data) {
        if (isFull()) {
            throw new NoSuchElementException("Stack is full!");
        }
        DoubleNode<T> newNode = new DoubleNode<>();
        newNode.setData(data);
        topPointer.setNext(newNode);
        newNode.setPrevious(topPointer);
        topPointer = newNode;
        numberElements++;
    }

    /**
     * Desempilha da pilha
     *
     * @return desempilha o elemento do topo
     * @throws NoSuchElementException, quando a pilha está vazia
     */
    @Override
    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty!");
        }
        T auxData = topPointer.getData();
        numberElements--;
        topPointer = topPointer.getPrevious();
        topPointer.setNext(null);
        return auxData;
    }

    /**
     * Retorno o elemento do topo da pilha
     *
     * @return retorna o elemento do topo
     * @throws NoSuchElementException, quando a pilha está vazia
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty!");
        }
        return topPointer.getData();
    }

    /**
     * Atualiza na pilha
     *
     * @param newData, dado a ser empilhado
     * @throws NoSuchElementException, quando a pilha está vazia
     */
    @Override
    public void update(T newData) {
        pop();
        push(newData);
    }

    /**
     * Verifica se a pilha está vazia
     *
     * @return retorna true se a pilha estiver vazia
     */
    @Override
    public boolean isEmpty() {
        return numberElements == 0;
    }

    /**
     * Verifica se a pilha está cheia
     *
     * @return retorna true se a pilha estiver cheia
     */
    @Override
    public boolean isFull() {
        return numberElements == maxElements;
    }

    /**
     * Imprime os elemento da piha
     *
     * @return String com os dados entre colchete separdos por vigurla
     */
    @Override
    public String print() {
        String result = "";
        DoubleNode<T> auxTopPointer = topPointer;
        for (int i = 0; i < numberElements; i++) {
            result += auxTopPointer.getData();
            auxTopPointer = auxTopPointer.getPrevious();

            if (i != numberElements-1) {
                result += ", ";
            }
        }
        return "[" +result+ "]";
    }
}
