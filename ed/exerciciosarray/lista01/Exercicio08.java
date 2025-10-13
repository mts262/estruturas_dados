package br.edu.ifba.bsi.ed.exerciciosarray.lista01;

import java.util.Scanner;

public class Exercicio08 {
    public static void main (String args[]) {
        Scanner input = new Scanner(System.in);
        int[] idade = new int[5];
        float[] altura = new float[5];

        for (int i = 0; i < idade.length; i++) {
            System.out.print("Informe a idade da pessoa " +(i+1)+ ": ");
            idade[i] = input.nextInt();
            System.out.print("Informe a altura dessa pessoa: ");
            altura[i] = input.nextFloat();
            System.out.println();
        }

        System.out.println("\n-- Idade e altura na ordem inversa --");
        for (int i = idade.length-1; i >= 0; i--) {
            System.out.printf("Idade: %d, altura: %.2f%n", idade[i], altura[i]);
        }

    }
}
