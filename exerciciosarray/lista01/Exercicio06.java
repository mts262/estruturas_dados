package br.edu.ifba.bsi.ed.exerciciosarray.lista01;

import java.util.Scanner;

public class Exercicio06 {
    public static void main (String args[]) {
        Scanner input = new Scanner(System.in);

        float[] medias = new float[10];
        int quantidade = 0;

        for (int i = 0; i < medias.length; i++) {
            float soma = 0;

            for (int j = 0; j < 4; j++) {
                System.out.print("Informe a " + (j+1) + "ª nota do aluno " + (i+1) + ": ");
                float nota = input.nextFloat();
                soma += nota;
            }

            System.out.println();
            medias[i] = soma / 4;
            if (medias[i] >= 7) {
                quantidade++;
            }
        }

        System.out.println("\nA quantidade de alunos com média maior ou igual a 7 é: " + quantidade);
    }
}
