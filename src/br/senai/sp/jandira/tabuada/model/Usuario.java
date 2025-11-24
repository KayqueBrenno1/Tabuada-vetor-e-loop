package br.senai.sp.jandira.tabuada.model;

import java.util.Scanner;

public class Usuario {
    public int multiplicando;
    public int multiplicadorInicial;
    public int multiplicadorFinal;
    public String[] tabuada;
    public int tamanho;
    public int apoio;
    public int resultado;
    public int i;

    public void obterDados(){
        Scanner leitor = new Scanner(System.in);

        System.out.print("Informe o valor do multiplicando: ");
        multiplicando = leitor.nextInt();

        System.out.print("Informe o valor do multiplicador inicial: ");
        multiplicadorInicial = leitor.nextInt();

        System.out.print("Informe o valor do multiplicador final: ");
        multiplicadorFinal = leitor.nextInt();

        calcularTabuada();
    }
    public String[] calcularTabuada(){
        if (multiplicadorInicial > multiplicadorFinal) {
            apoio = multiplicadorInicial;
            multiplicadorInicial = multiplicadorFinal;
            multiplicadorFinal = apoio;
        }
        tamanho = multiplicadorFinal - multiplicadorInicial + 1;
        tabuada = new String[tamanho];

        while (i < tamanho){
            resultado = multiplicando * multiplicadorInicial;
            tabuada[i] = multiplicando + " x " + multiplicadorInicial + " = " + resultado;
            multiplicadorInicial++;
            i = i + 1;


        }
        return tabuada;
        //exibirTabuada();
    }
    public void exibirTabuada(){
        System.out.println("Resultado da sua tabuada:");

        i = 0;
        while (i < tamanho){
            System.out.println(tabuada[i]);
            i++; //é a mesma coisa de "i = i + 1"
        }
    }

}
