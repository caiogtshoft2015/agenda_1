/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads3.agenda;

import java.beans.Statement;
import java.sql.*;
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
    Contato add = new Contato("a", "a", "a", "a");
    private String String;

    public void incluir() {


        System.out.print("Digite o nome completo do contato: ");
        add.setNome(entrada.nextLine());


        System.out.print("Digite a data de nascimento no formato dd/mm/aaaa: ");
        add.setStrDataNasc(entrada.nextLine());


        System.out.print("Digite o e-mail");
        add.setEmail(entrada.nextLine());



        System.out.print("Digite o telefone no formato 99 99999-9999");
        add.setTelefone(entrada.nextLine());


        // 1) Abrir conexao
        PreparedStatement stmt = null;
        Connection conn = null;

        String sql = "INSERT INTO TB_CONTATO (NM_CONTATO, DT_NASCIMENTO, "
                + "VL_TELEFONE, VL_EMAIL, DT_CADASTRO) "
                + "VALUES (?, ?, ?, ?, ?)";

        try {
            conn = obterConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, add.getNome());

            DateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
            Date dataNasc = null;
            try {
                dataNasc = formatador.parse(add.getStrDataNasc());
            } catch (ParseException ex) {
                System.out.println("Data de nascimento inválida.");
                return;
            }
            stmt.setDate(2, new java.sql.Date(dataNasc.getTime()));
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

    public void alterar() throws SQLException, ClassNotFoundException {

        PreparedStatement stmt;
        Connection conn = null;
        String consulta = entrada.nextLine();


        conn = obterConexao();



        try {
            // cria um preparedStatement
            stmt = conn.prepareStatement("select * from TB_CONTATO (NM_CONTATO) values (?)");
            // preenche os valores
            stmt.setString(1, consulta);

            // executa
            stmt.execute();
            stmt.close();
            System.out.println("Gravado!");
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
