package br.edu.ifba.bsi.ed.exerciciosarray.lista01;

import java.util.Scanner;

public class Exercicio09 {
    public static void main (String args[]) {
        Scanner input = new Scanner(System.in);
        int[] A = new int[10];
        int soma=0;

        for (int i =0; i < A.length; i++) {
            System.out.print("Digite o " +(i+1)+ "º número: ");
            A[i] = input.nextInt();
            soma = soma + (A[i] * A[i]);
        }

        System.out.println("\nA soma dos quadrados do números informados é: " +soma);

    }
}