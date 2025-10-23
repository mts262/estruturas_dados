package br.edu.ifba.bsi.ed.exerciciosarray.lista01;

import java.util.Scanner;

public class Exercicio11 {
    public static void main (String srga[]) {
        Scanner input = new Scanner(System.in);
        int[] a = new int[10];
        int[] b = new int[10];
        int[] c = new int[10];
        int[] d = new int[30];
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

        System.out.print("\nInforme os números do vetor C\n");
        for (int i = 0; i < c.length; i++) {
            System.out.print("Digite o " +(i+1)+ "º número: ");
            c[i] = input.nextInt();
        }

        for (int i = 0; i < a.length; i++) {
            d[posicao++] = a[i];
            d[posicao++] = b[i];
            d[posicao++] = c[i];
        }

        System.out.print("\n-- Números do vetor D --\n");
        for (int i = 0; i < d.length; i++) {
            System.out.print("[" +d[i]+ "] ");
        }
    }
}