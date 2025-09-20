package br.edu.ifba.bsi.ed.exerciciosarray.lista01;

import java.util.Scanner;

public class Exercicio16 {
    public static void main (String[] args) {
        Scanner input = new Scanner(System.in);
        float bruto;
        int[] contador =new int[9];
        String[] intervalo = {"$200 - $299",
                "$300 - $399",
                "$400 - $499",
                "$500 - $599",
                "$600 - $699",
                "$700 - $799",
                "$800 - $899",
                "$900 - $999",
                "$1000 em diante"};


        String continuar = "s";
        do  {
            System.out.println("Informe o valor bruto das suas vendas da semana: ");
            bruto = input.nextFloat();
            input.nextLine();

            float salario = ((bruto * 9) / 100) + 200;

            if (salario >= 200 && salario <= 299) {
                contador[0]++;
            } else if (salario >= 300 && salario <= 399) {
                contador[1]++;
            } else if (salario >= 400 && salario <= 499) {
                contador[2]++;
            } else if (salario >= 500 && salario <= 599) {
                contador[3]++;
            } else if (salario >= 600 && salario <= 699) {
                contador[4]++;
            } else if (salario >= 700 && salario <= 799) {
                contador[5]++;
            } else if (salario >= 800 && salario <= 899) {
                contador[6]++;
            } else if (salario >= 900 && salario <= 999) {
                contador[7]++;
            } else if (salario >= 1000) {
                contador[8]++;
            }

            System.out.println("Deseja continuar? (s/n)");
            continuar = input.nextLine();
        } while (continuar.equalsIgnoreCase("s") || continuar.equalsIgnoreCase("sim"));

        for (int i = 0; i < 9; i++) {
            System.out.println("\nQuantidade de funcionarios que receberam no intervalo: ");
            System.out.println(intervalo[i]+ ": " +contador[i]+ " funcionario(s)");
        }
    }
}
