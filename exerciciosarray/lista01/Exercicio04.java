package br.edu.ifba.bsi.ed.exerciciosarray.lista01;

import java.util.Scanner;

public class Exercicio04 {
    public static void main (String args[]) {
        Scanner input = new Scanner(System.in);
        char[] caracteres = new char[10];
        char[] consoantes = new char[10];
        int quantidade=0;

        for (int i=0; i< caracteres.length; i++) {
            System.out.print("Digite o " + (i + 1) + "º caracter: ");
            caracteres[i] = input.next().charAt(0);

            char c = Character.toLowerCase(caracteres[i]);
            if (c >= 'a' && c <= 'z') {
                if (c != 'a' && c != 'e'  && c != 'i' && c != 'o' && c != 'u') {
                    consoantes[quantidade] = caracteres[i];
                    quantidade++;
                }
            }
        }

        System.out.println("\nQuantidade de consoantes lidas: " +quantidade);
        System.out.println("-- Consoantes Lidas --");
        for (int i = 0; i < quantidade; i++) {
            System.out.print(consoantes[i]+ " ");
        }
    }
}
