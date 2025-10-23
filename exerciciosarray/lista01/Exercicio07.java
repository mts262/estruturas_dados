package br.edu.ifba.bsi.ed.exerciciosarray.lista01;

import java.util.Scanner;

public class Exercicio07 {
    public static void main (String args[]) {
        Scanner input = new Scanner(System.in);

        int[] num = new int[5];
        int soma=0, multi=1;

        for (int i =0; i < num.length; i++) {
            System.out.print("Digite o " +(i+1)+ "º número: ");
            num[i] = input.nextInt();

            soma+=num[i];
            multi*=num[i];
        }

        System.out.println("\n-- Números informados --");
        for (int i =0; i < num.length; i++) {
            System.out.print("["+num[i]+"] ");
        }
        System.out.println("\nA soma dos números informados é: " +soma);
        System.out.println("A multiplicação dos números informados é: " +multi);

    }
}