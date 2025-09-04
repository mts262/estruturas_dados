package br.edu.ifba.bsi.ed.queue;

public class CircularArrayQueue implements Queueable{
    private Object[] data;
    private int head;
    private int tail;
    private int numberElements;

    public CircularArrayQueue() {
        this(10);
    }

    public CircularArrayQueue(int length) {
        this.data = new Object[length];
        head = 0;
        tail = -1;
        numberElements = 0;
    }


    @Override
    public void enqueue(Object data) {
        if (isFull()) {
            System.err.println("Queue is full!");
        } else {
            tail = next(tail);
            this.data[tail] = data;
            numberElements++;
        }
    }

    @Override
    public Object dequeue() {
        Object aux = null;
        if (isEmpty()) {
            System.err.println("Queue is empty!");
        } else {
            aux = data[head];
            head = next(head);
            numberElements--;
        }
        return aux;
    }

    private int next(int head) {
        return (head++) % data.length;

    }

    @Override
    public Object front() {
        Object aux = null;
        if (isEmpty()) {
            System.err.println("Queue is empty!");
        } else {
            aux = data[head];
        }
        return aux;
    }

    @Override
    public boolean isEmpty() {
        return (numberElements == 0);
    }

    @Override
    public boolean isFull() {
        return (numberElements == data.length);
    }

    @Override
    public String print() {
        String result = " ";
        int temp = head;
        for (int i = 0; i < numberElements; i++) {
            result += data[temp];
            temp = next(temp);
            if (temp != tail) {
                result += ", ";
            }
        }
        return "["+result+"]";
    }
}
