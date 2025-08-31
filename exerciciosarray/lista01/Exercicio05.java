package br.edu.ifba.bsi.ed.exerciciosarray.lista01;

import java.util.Scanner;

public class Exercicio05 {
    public static void main (String args[]) {
        Scanner input  = new Scanner(System.in);
        int[] num = new int[20];
        int[] pares = new int[20];
        int[] impares = new int[20];
        int par=0, impar=0;

        for (int i = 0; i < num.length; i++) {
            System.out.print("Digite o " +(i+1)+ "º número: ");
            num[i] = input.nextInt();
            
            if ((num[i]%2)==0) {
                pares[par] = num[i];
                par++;
            } else {
                impares[impar] = num[i];
                impar++;
            }
        }

        System.out.println("\n-- Números informados --");
        for (int i = 0; i < num.length; i++) {
            System.out.print("["+num[i]+"] ");
        }

        System.out.println("\n--Números pares--");
        for (int i =0; i < par; i++) {
            System.out.print("["+pares[i]+"] ");
        }

        System.out.println("\n--Números impares--");
        for (int i =0; i < impar; i++) {
            System.out.print("["+impares[i]+"] ");
        }
    }
}
