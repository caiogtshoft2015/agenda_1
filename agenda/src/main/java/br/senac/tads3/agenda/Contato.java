/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads3.agenda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Caio Gts
 */
public class Contato {
    
    DateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
            Date dataNasc = null;

    private String nome;
    private String strDataNasc;
    private String email;
    private String telefone;

    public Contato(String nome, String strDataNasc, String email, String telefone) {
        this.nome = nome;
        this.strDataNasc = strDataNasc;
        this.email = email;
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getStrDataNasc() {
        return strDataNasc;
    }

    public void setStrDataNasc(String strDataNasc) {
        this.strDataNasc = strDataNasc;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
