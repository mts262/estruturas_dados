package br.edu.ifba.bsi.ed.exerciciosarray.lista01;

import java.util.Scanner;

public class Exercicio14 {
    public static void main (String[] args) {
        Scanner input = new Scanner(System.in);
        String[] perguntas = {"Telefonou para a vítima?",
                "Esteve no local do crime?",
                "Mora perto da vítima?",
                "Devia para a vítima?",
                "Já trabalhou com a vítima?"};
        String respostas;
        int quantidade=0;

        System.out.println("Responda com 'sim' ou 'não' as seguintes perguntas\n");
        for (int i = 0; i < perguntas.length; i++) {
            System.out.println(perguntas[i]);
            respostas = input.nextLine();

            if (respostas.equalsIgnoreCase("sim") || respostas.equalsIgnoreCase("s")) {
                quantidade++;
            }
        }

        if (quantidade == 2)
            System.out.println("\nClassificação: suspeito");
        else if (quantidade == 3 || quantidade == 4)
            System.out.println("\nClassificação: cúmplice");
        else if (quantidade == 5)
            System.out.println("\nClassificação: assassino");
        else
            System.out.println("\nClassificação: inocente");
    }
}
