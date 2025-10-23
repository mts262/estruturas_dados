package br.edu.ifba.bsi.ed.queue;

public class ArrayDEQue {
    private Object[] data;
    private int head;
    private int tail;
    private int numberElements;

    public ArrayDEQue() {
        this(10);
    }

    public ArrayDEQue(int length) {
       this.data = new Object[length];
        head = 0;
        tail = -1;
        numberElements = 0;
    }



    public void endEnqueue(Object data) {
        if (isFull()) {
            System.err.println("Queue is full!");
        } else {
            tail = next(tail);
            this.data[tail] = data;
            numberElements++;
        }
    }

    public void beginEnqueue(Object data) {
        if (isFull()) {
            System.err.println("Queue is full!");
        } else {
            head = prior(head);
            this.data[head] = data;
            numberElements++;
        }
    }

    public Object beginDequeue() {
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

    public Object endDequeue() {
        Object aux = null;
        if (isEmpty()) {
            System.err.print("Queue is empty!");
        } else {
            aux = data[tail];
            tail = prior(tail);
            numberElements--;
        }
        return aux;
    }

    private int next(int head) {
        return (head+1) % data.length;

    }

    private int prior(int head) {
        return ((head--) + data.length) % data.length;
    }

    public Object front() {
        Object aux = null;
        if (isEmpty()) {
            System.err.println("Queue is empty!");
        } else {
            aux = data[head];
        }
        return aux;
    }

    public Object rear() {
        Object aux = null;
        if (isEmpty()) {
            System.err.println("Queue is empty!");
        } else {
            aux = data[tail];
        }
        return aux;
    }


    public boolean isEmpty() {
        return (numberElements == 0);
    }


    public boolean isFull() {
        return (numberElements == data.length);
    }


    public String printFrontToRear() {
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

    public String printRearToFront() {
        String result = " ";
        int temp = tail;
        for (int i = 0; i < numberElements; i++) {
            result += data[temp];
            temp = prior(temp);
            if (temp != head) {
                result += ", ";
            }
        }
        return "["+result+"]";
    }
}
