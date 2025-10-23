package br.edu.ifba.bsi.ed.exerciciosarray.lista01;

import java.util.ArrayList;
import java.util.Scanner;

public class Exercicio15 {
    public static void main (String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Float> valores = new ArrayList<>();
        double soma=0;

        System.out.println("Informe as notas (para encerrar digite '-1')");
        while (true) {
            System.out.print("Nota: ");
            float valor = input.nextFloat();
            if (valor == -1) break;
            valores.add(valor);
            soma+=valor;
        }

        int quantidade = valores.size();
        System.out.println("\nQuantidade de valores informados: " +quantidade);
        System.out.println("\nValores informados: ");
        for (float v : valores ) {
            System.out.print(v+ "   ");
        }

        System.out.println();
        System.out.println("\nValores informados, na ordem inversa:");
        for (int j = valores.size()-1; j >= 0; j--) {
            System.out.println(valores.get(j));
        }

        System.out.printf("\nA soma dos valores informados é: %.2f\n", soma);

        double media = soma / quantidade;
        System.out.printf("\nA média dos valores informados é: %.2f\n", media);


        int quantAcimaMed=0;
        for (float v : valores) {
            if (v > media) {
                quantAcimaMed++;
            }
        }
        System.out.printf("\nQuantidade de valores acima da média (%.2f): %d\n", media, quantAcimaMed);

        int quantAbaixoSete=0;
        for (float v : valores) {
            if (v < 7)
                quantAbaixoSete++;
        }
        System.out.println("\nQuantidade de valores abaixo de sete: " +quantAbaixoSete);

        System.out.println("\n=== Fim do Programa ===");


    }
}
