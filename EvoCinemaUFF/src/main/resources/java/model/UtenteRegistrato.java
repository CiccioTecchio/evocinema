/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java.model;

import java.util.GregorianCalendar;

/**
 *
 * @author francescodefeo
 */
public abstract class UtenteRegistrato {

    private String nomeUtente;
    private String password;
    private String nome;
    private String cognome;
    private String email;
    private String cellulare;
    private String indirizzo;
    private GregorianCalendar dataNascita;
    private String sesso;

    public UtenteRegistrato(String nomeUtente, String password, String nome, String cognome, String email, String cellulare, String indirizzo, GregorianCalendar dataNascita, String sesso) {
        this.nomeUtente = nomeUtente;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.cellulare = cellulare;
        this.indirizzo = indirizzo;
        this.dataNascita = dataNascita;
        this.sesso = sesso;
    }

    public void setNomeUtente(String nomeUtente) {
        this.nomeUtente = nomeUtente;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCellulare(String cellulare) {
        this.cellulare = cellulare;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public void setDataNascita(GregorianCalendar dataNascita) {
        this.dataNascita = dataNascita;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public String getNomeUtente() {
        return nomeUtente;
    }

    public String getPassword() {
        return password;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getEmail() {
        return email;
    }

    public String getCellulare() {
        return cellulare;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public GregorianCalendar getDataNascita() {
        return dataNascita;
    }

    public String getSesso() {
        return sesso;
    }

}
