package br.edu.ifba.bsi.ed.stack;

public class InvertedArrayStack implements Stackable{
    private Object[] data;
    private int pointerTop;

    public InvertedArrayStack(int length) {
        data = new Object[length];
        pointerTop = length;
    }

    public InvertedArrayStack() {
        this(10);
    }

    @Override
    public boolean isEmpty() {
        return (pointerTop == data.length);
    }

    @Override
    public boolean isFull() {
        return (pointerTop == 0);
    }

    @Override
    public String print() {
        String result = "";
        for (int i = pointerTop; i < data.length-1; i++) {
            result += data[i];
            if (i != data.length-1)
                result += ";";
        }
        return "[" +result+"]";
    }

    @Override
    public Object pop() {
        Object temp = null;
        if (isEmpty()) {
            System.out.println("Stack is empty");
        } else {
            temp = data[pointerTop];
            pointerTop++;
        }
        return temp;
    }

    @Override
    public void push(Object data) {

        if (isFull()) {
            System.out.println("Stack is full");
        } else {
            pointerTop--;
            this.data[pointerTop] = data;
        }

    }

    @Override
    public Object peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
        } else {
            return data[pointerTop];
        }
        return null;
    }
}