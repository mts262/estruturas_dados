package br.edu.ifba.bsi.ed.binaryHeap;

public class MainHeap {
    public static void main(String[] args) {
        MaxBinaryTreeHeap heap = new MaxBinaryTreeHeap(10);

        System.out.println("Inserindo elementos...");
        heap.enqueue(10);
        heap.enqueue(5);
        heap.enqueue(30);
        heap.enqueue(40);
        heap.enqueue(20);
        heap.enqueue(40);

        System.out.println("Heap (array interno): " + heap.print());
        System.out.println("Front (maior elemento): " + heap.front());

        System.out.println("\nRemovendo elementos...");
        while (!heap.isEmpty()) {
            System.out.println("\nDequeue: " + heap.dequeue());
            System.out.println("Heap agora: " + heap.print());
        }
    }
}
