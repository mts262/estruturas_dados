package br.edu.ifba.bsi.ed.stack;

public class DoubleArrayStack {
    private Object[] data;
    private int pointerTop1;
    private int pointerTop2;

    public DoubleArrayStack(int length) {
        data = new Object[length];
        pointerTop1 = -1;
        pointerTop2 = data.length;
    }

    public DoubleArrayStack () {
        this(10);
    }


    public boolean isEmpty1() {
        return pointerTop1 == -1;
    }

    public boolean isEmpty2() {
        return pointerTop2 == data.length;
    }

    public boolean isFull1() {
        return pointerTop1 + 1 == pointerTop2;
    }

    public boolean isFull2() {
        return isFull1();
    }

    public String print1() {
        String result = "";
        for (int i = pointerTop1; i >= 0; i--) {
            result += data[i];
            if (i != 0)
                result += ";";
        }
        return "[" +result+"]";
    }

    public String print2() {
        String result = "";
        for (int i = pointerTop2; i < data.length; i++) {
            result += data[i];
            if (i != data.length-1)
                result += ";";
        }
        return "[" +result+"]";
    }

    public Object pop1() {
        Object temp = null;
        if (isEmpty1()) {
            System.out.println("Stack is empty");
        } else {
            temp = data[pointerTop1];
            pointerTop1--;
        }
        return temp;
    }

    public Object pop2() {
        Object temp = null;
        if (isEmpty2()) {
            System.out.println("Stack is empty");
        } else {
            temp = data[pointerTop2];
            pointerTop2++;
        }
        return temp;
    }

    public void push1(Object data) {
        if (isFull1()) {
            System.out.println("Stack is full");
        } else {
            pointerTop1++;
            this.data[pointerTop1] = data;
        }
    }

    public void push2(Object data) {

        if (isFull2()) {
            System.out.println("Stack is full");
        } else {
            pointerTop2--;
            this.data[pointerTop2] = data;
        }
    }

    public Object peek1() {
        if (isEmpty1()) {
            System.out.println("Stack is empty");
        } else {
            return data[pointerTop1];
        }
        return null;
    }

    public Object peek2() {
        if (isEmpty1()) {
            System.out.println("Stack is empty");
        } else {
            return data[pointerTop2];
        }
        return null;
    }
}
