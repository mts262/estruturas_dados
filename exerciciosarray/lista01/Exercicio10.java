package br.edu.ifba.bsi.ed.exerciciosarray.lista01;

import java.util.Scanner;

public class Exercicio10 {
    public static void main (String srga[]) {
        Scanner input = new Scanner(System.in);
        int[] a = new int[10];
        int[] b = new int[10];
        int[] c = new int[20];
        int posicao=0;

        System.out.print("Informe os números do vetor A\n");
        for (int i = 0; i < a.length; i++) {
            System.out.print("Digite o " +(i+1)+ "º número: ");
            a[i] = input.nextInt();
        }

        System.out.print("\nInforme os números do vetor B\n");
        for (int i = 0; i < b.length; i++) {
            System.out.print("Digite o " +(i+1)+ "º número: ");
            b[i] = input.nextInt();
        }

        for (int i = 0; i < a.length; i++) {

            c[posicao++] = a[i];
            c[posicao++] = b[i];
        }

        System.out.print("\n-- Números do vetor C --\n");
        for (int i = 0; i < c.length; i++) {
        System.out.print("[" +c[i]+ "] ");
        }
    }
}