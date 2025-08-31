package br.edu.ifba.bsi.ed.exerciciosarray.lista01;

import java.util.Scanner;

public class Exercicio03 {
    public static void main (String args[]) {
        Scanner input = new Scanner(System.in);
        float[] notas = new float[4];
        float media;
        float soma=0;

        for (int i=0; i<notas.length; i++) {
            System.out.print("Digite a " +(i+1)+ "ª nota: ");
            notas[i] = input.nextFloat();
            soma+=notas[i];
        }

        media = soma/4;
        System.out.println("\n-- Notas --");
        for (int i=0; i< notas.length; i++) {
            System.out.println(notas[i]);
        }
        System.out.print("Média: " +media);

    }
}
