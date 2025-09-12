package br.edu.ifba.bsi.ed.list;

public class ArrayList implements Listable{
    private Object[] data;
    private int head;
    private int tail;
    private int numberElements;

    public ArrayList() {
        this(10);
    }

    public ArrayList(int length) {
        data = new Object[length];
        int head = 0;
        int tail = -1;
        int numberElements = 0;
    }

    @Override
    public boolean isEmpty() {
        return numberElements == 0;
    }

    @Override
    public boolean isFull() {
        return numberElements == data.length;
    }

    @Override
    public String print() {
        String result = " ";
        int aux = head;

        for (int i = 0; i < numberElements; i++) {
            result += data[aux];
            if (i != numberElements-1) {
                result += ", ";
            }
            aux = next(aux);
        }

        return "[" +result+ "]";
    }

    private int next(int index) {
        return (index+1) % data.length;
    }

    private int prior(int index) {
        return (index-1 + data.length) % data.length;
    }
    @Override
    public void insert(Object data, int logicalIndex) {

    }

    @Override
    public void append(Object data) {
        if (isFull()) {
            System.err.println("List is full!");
        } else {
            tail = next(tail);
            this.data[tail] = data;
            numberElements++;
        }
    }

    @Override
    public Object select(int logicalIndex) {
        Object temp = null;
        if (isEmpty()) {
            System.err.println("List is empty!");
        }
        else if (logicalIndex < 0 || logicalIndex > numberElements-1) {
            System.err.println("Invalid index");
        } else {
            int phyicalIndex = map(logicalIndex);
            temp = data[phyicalIndex];
        }
        return temp;
    }

    private int map(int index) {
        return (head + index) % data.length;
    }


    @Override
    public Object[] selectAll() {
        Object[] result = new Object[numberElements];
        int aux = head;

        for (int i = 0; i < numberElements; i++) {
            result[i] = data[aux];
            aux = next(aux);
        }
        return result;
    }

    @Override
    public void update(Object data, int logicalIndex) {
        if (isEmpty()) {
            System.err.println("List is empty!");
        }
        else if (logicalIndex < 0 || logicalIndex > numberElements-1) {
            System.err.println("Invalid index");
        } else {
            int phyicalIndex = map(logicalIndex);
            this.data[phyicalIndex] = data;
        }
    }

    @Override
    public Object delete(int logicalIndex) {
        return null;
    }

    @Override
    public void clear() {
        head = 0;
        tail = -1;
        numberElements = 0;
    }
}
