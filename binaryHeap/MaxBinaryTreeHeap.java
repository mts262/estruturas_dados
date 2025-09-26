package br.edu.ifba.bsi.ed.binaryHeap;

import br.edu.ifba.bsi.ed.queue.Queueable;

public class MaxBinaryTreeHeap implements Queueable {
    private Object[] data;
    private int tail;
    private long counter = 999_999;

    public MaxBinaryTreeHeap (int length) {
        data = new Object[length];
        tail = -1;
    }

    @Override
    public void enqueue(Object data) {
        if (isFull()) {
            System.err.println("Heap is full!");
        } else {
            Object prioridade = data;
            long novaPrioridade = novaPrioridade((Integer) prioridade);

            tail++;
            this.data[tail] = novaPrioridade;
            heapifyUp(tail);
        }
    }

    @Override
    public Object dequeue() {
        Object temp = null;
        if (isEmpty()) {
            System.err.println("Heap is empty!");
        } else {
            temp = (long) data[0] / 1_000_000;
            data[0] = data[tail];
            tail--;
            heapifyDown(0);
        }
        return temp;
    }

    @Override
    public Object front() {
        Object temp = null;
        if (isEmpty()) {
            System.err.println("Heap is empty!");
        } else {
            temp = (long) data[0] / 1_000_000;
        }
        return temp;
    }

    private int parent(int child) {
        return (child - 1) / 2;
    }

    private int leftChild (int parent) {
        return parent * 2 + 1;
    }

    private int rigthChild (int parent) {
        return parent * 2 + 2;
    }

    private void swap(int index1, int index2) {
        Object aux = data[index1];
        data[index1] = data[index2];
        data[index2] = aux;
    }

    private void heapifyUp(int filho) {
        int pai = parent(filho);
        while (filho > 0 && (long) data[filho] > (long) data[pai]) {
            swap(filho, pai);
            filho = pai;
            pai = parent(filho);
        }
    }

    private void heapifyDown(int pai) {
        boolean ajustado = false;

        while (!ajustado) {
            int left = leftChild(pai);
            int rigth = rigthChild(pai);
            int maior = pai;

            if ( left <= tail && (long) data[left] > (long) data[maior] ) {
                maior = left;
            }
            if (rigth <= tail && (long) data[rigth] > (long) data[maior] ) {
                maior = rigth;
            }
            if (maior != pai) {
                swap(pai, maior);
                pai = maior;
            } else {
                ajustado = true;
            }
        }
    }


    public long novaPrioridade (int prioridadeInicial) {
        long novaPrioridade = prioridadeInicial * 1_000_000;
        novaPrioridade += counter;
        counter--;
        return novaPrioridade;
    }

    @Override
    public boolean isEmpty() {
        return tail == -1;
    }

    @Override
    public boolean isFull() {
        return tail == data.length-1;
    }

    @Override
    public String print() {
        String result = "";
        if (isEmpty()) {
            System.err.println("Heap is empty!");
        } else {
            for (int i = 0; i <= tail; i++) {
                result += (long) data[i] / 1_000_000;
                if (i != tail) {
                    result += ", ";
                }
            }
        }
        return "[" +result+ "]";
    }
}
