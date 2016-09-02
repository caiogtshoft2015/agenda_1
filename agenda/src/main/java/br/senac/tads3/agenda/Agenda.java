/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads3.agenda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fernando.tsuda
 */
public class Agenda extends ConexaoBD {

    private static Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        ContatoDAO add = new ContatoDAO();
        Contato add1 = new Contato("", "", "", "");





        Agenda instancia = new Agenda();

        do {
            System.out.println("***** DIGITE UMA OPÇÃO *****");
            System.out.println("(1) Listar contatos");
            System.out.println("(2) Incluir novo contato");
            System.out.println("(3) Deleta contato");
            System.out.println("(4) Altera contato");
            System.out.println("(9) Sair");
            System.out.print("Opção: ");

            String strOpcao = entrada.nextLine();
            int opcao = Integer.parseInt(strOpcao);
            switch (opcao) {
                case 1:

                    add.consultaContato();

                    break;
                case 2:
                    add.incluir();

                    break;
                case 3:
                    add.remove(add1);


                    break;
                case 4:
                    add.altera(add1);




                    break;
                case 9:
                    System.exit(0);
                    break;
                default:
                    System.out.println("OPÇÃO INVÁLIDA");
            }
        } while (true);

    }
}
