package br.edu.ifba.bsi.ed.exerciciosarray.lista01;

import java.util.Scanner;

public class Exercicio02 {
    public static void main (String args[]) {
        Scanner input = new Scanner(System.in);

        float[] num = new float[10];

        for (int i=0; i<num.length; i++) {
            System.out.print("Digite o " +(i+1)+ "º número real: ");
            num[i] = input.nextFloat();
        }

        System.out.println("\n-- Números do vetor invertido --");
        for (int i=num.length-1; i>=0; i--) {
            System.out.println(num[i]);
        }
    }
}
