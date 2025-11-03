package br.senai.sp.jandira.tabuada.model;

import java.util.Scanner;

public class Usuário {
    int multiplicando;
    int multiplicadorInicial;
    int multiplicadorFinal;
    int temporario;
    int resultado;

    public void obterDados(){
        Scanner leitor = new Scanner(System.in);

        System.out.println("Informe o valor do multiplicando: ");
        multiplicando = leitor.nextInt();

        System.out.println("Informe o valor do multiplicador inicial: ");
        multiplicadorInicial = leitor.nextInt();

        System.out.println("Informe o valor do multiplicador final: ");
        multiplicadorFinal = leitor.nextInt();

    }

}
