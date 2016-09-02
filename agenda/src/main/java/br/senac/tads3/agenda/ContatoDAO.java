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
    private static ResultSet rs;
    private static Statement stmt;

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

    public void altera(Contato contato) {

        Connection con = new ConnectionFactory().getConnection();
        System.out.println("Insira o nome a ser alterado");
        String nome = entrada.nextLine();


        System.out.println("Insira o ID do registro no banco de dados");
        int id = entrada.nextInt();





        String sql = "update TB_CONTATO set NM_CONTATO=? where ID_CONTATO=?";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            stmt.setInt(2, id);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void consultaContato() throws SQLException, ClassNotFoundException {
        // pega a conexão e o Statement

        Connection con = new ConnectionFactory().getConnection();

        PreparedStatement stmt = con.prepareStatement("select * from TB_CONTATO");

// executa um select
        ResultSet rs = stmt.executeQuery();


// itera no ResultSet
        while (rs.next()) {

            System.out.println(rs.getString("NM_CONTATO"));

        }

        rs.close();
        stmt.close();
        con.close();
    }

    public void remove(Contato contato) {
        Connection con = new ConnectionFactory().getConnection();
        
        System.out.println("Insira o ID do registro no banco de dados");
        int id = entrada.nextInt();




        try {
            PreparedStatement stmt = con.prepareStatement(
                    "delete from TB_CONTATO where ID_CONTATO=?");
            stmt.setLong(1, id);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
