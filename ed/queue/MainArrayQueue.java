package br.edu.ifba.bsi.ed.queue;

public class MainArrayQueue {
    public static void main (String[] args) {
        ArrayQueue queue = new ArrayQueue(5);
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("D");
        String state = (String)queue.dequeue();
        System.out.println(state);
        queue.enqueue("C");
        queue.enqueue("E");
        String temp = (String)queue.front();
        System.out.println(temp);
        temp = queue.print();
        System.out.println(temp);
    }
}
