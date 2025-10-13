package br.edu.ifba.bsi.ed.exerciciosarray.lista01;

import java.util.Scanner;

public class Exercicio13 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        float[] tempMeses = new float[12];
        String[] meses = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        double soma=0;
        
        for (int i = 0; i < tempMeses.length; i++) {
            System.out.print("Informe a temperatura média do mês de " +meses[i]+ ": ");
            tempMeses[i] = input.nextFloat();
            soma += tempMeses[i];
        }

        double media = soma/tempMeses.length;

        System.out.printf("\n-- Meses com temperatura acima da média anual (%.2fºC) --\n", media);
        for (int i = 0; i < tempMeses.length; i++) {
            if (tempMeses[i] > media) {
                System.out.println((i+1)+ " - " +meses[i]+ " com temperatura de: " +tempMeses[i]+ "ºC");
            }
        }
    }
}