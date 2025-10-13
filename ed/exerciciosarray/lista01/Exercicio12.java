package br.edu.ifba.bsi.ed.exerciciosarray.lista01;

import java.util.Scanner;

public class Exercicio12 {
    public static void main (String[] args){
        Scanner input = new Scanner(System.in);
        int[] idade = new int[30];
        float[] altura = new float[30];
        float soma=0;

        System.out.println("Informe idade e altura dos alunos");
        for (int i = 0; i < idade.length; i++) {
            System.out.print("\nIdade do aluno " +(i+1)+ ": ");
            idade[i] = input.nextInt();
            System.out.print("Informe a altura desse aluno: ");
            altura[i] = input.nextFloat();
            soma += altura[i];
        }
        double media = soma/30;
        int quantidade=0;

        for (int i = 0; i < idade.length; i++) {
            if (idade[i] > 13 && altura[i] < media) {
                quantidade++;
            }
        }
        System.out.println("\nA quantidade de alunos maiores de 13 anos com altura inferior a média de alturas é: " +quantidade);
    }
}
