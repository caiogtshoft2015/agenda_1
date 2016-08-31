/*
 * To change this template, choose Tools | Templates
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

/**
 *
 * @author Caio Gts
 */
public class ContatoDAO extends ConexaoBD {

    static Scanner entrada = new Scanner(System.in);

    public void cadastrar() {
        Contato add = new Contato("", "", "", "");
        System.out.println("Nome:");
        add.setNome(entrada.nextLine());


        System.out.println("Data");

        add.setStrDataNasc(entrada.nextLine());



        System.out.println("Telefone");
        add.setTelefone(entrada.nextLine());
        System.out.println("Email");
        add.setEmail(entrada.nextLine());



        incluir();

    }
    // 1) Abrir conexao
    PreparedStatement stmt = null;
    Connection conn = null;
    String sql = "INSERT INTO TB_CONTATO (NM_CONTATO, DT_NASCIMENTO, "
            + "VL_TELEFONE, VL_EMAIL, DT_CADASTRO) "
            + "VALUES (?, ?, ?, ?, ?)";

    public void incluir() {

        Contato add = new Contato("", "", "", "");





        try {
            conn = obterConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, add.getNome());


            DateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
            Date dataNasc;
            
            try {
                dataNasc = formatador.parse(add.getStrDataNasc());
                
                
                System.out.println(dataNasc);

            } catch (ParseException ex) {
                System.out.println("Data de nascimento inválida.");
                return;
            }
            stmt.setDate(2, new java.sql.Date(dataNasc.getTime()));

            System.out.println(dataNasc.getTime());



            stmt.setString(3, add.getTelefone());
            stmt.setString(4, add.getEmail());
            stmt.setDate(5, new java.sql.Date(System.currentTimeMillis()));




            // 2) Executar SQL
            stmt.executeUpdate();
            System.out.println("Contato cadastrado com sucesso");

        } catch (SQLException e) {
            System.out.println("Não foi possível executar.");
        } catch (ClassNotFoundException e) {
            System.out.println("Não foi possível executar.");
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar stmt.");
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar conn.");
                }
            }
        }

    }
}
