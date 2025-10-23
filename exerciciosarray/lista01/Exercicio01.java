package br.edu.ifba.bsi.ed.exerciciosarray.lista01;

import java.util.Scanner;

public class Exercicio01 {
    public static void main (String args[]) {
        Scanner input = new Scanner(System.in);

        int[] num = new int[5];

        for (int i=0; i<num.length; i++) {
            System.out.print("Digite o " +(i+1)+ "º número inteiro: ");
            num[i] = input.nextInt();
        }

        System.out.println("\n-- Números do vetor --");
        for (int i=0; i<num.length; i++) {
            System.out.println(num[i]);
        }
    }
}
