/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads3.agenda;

/**
 *
 * @author Caio Gts
 */
import java.sql.*;

public class NovaConsulta {

    public static void main(String[] args) throws Exception {

        ConexaoBD connn = new ConexaoBD();
        connn.obterConexao();
        Connection conn;


        conn = DriverManager.getConnection(
                "jdbc:derby://localhost:1527/agendadb;SecurityMechanism=3",
                "app", // usuário BD
                "app"); // senha BD
        Statement stmt = conn.createStatement();
        String query = ("SELECT * FROM TB_CONTATO");
        

        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        
        
        

    }
}
