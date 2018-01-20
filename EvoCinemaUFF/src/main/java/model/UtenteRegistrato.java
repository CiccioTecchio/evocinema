
package model;

import java.util.Calendar;

public abstract class UtenteRegistrato {

    public enum ruolo{ UTENTE,OPERATORE,GESTORE}; 
    public enum sesso{M,F};
    private String nomeUtente;
    private String password;
    private String nome;
    private String cognome;
    private String email;
    private String cellulare;
    private String città;
    private String indirizzo;
    private Calendar dataNascita;
    private ruolo ruolo;
    private sesso sesso;

    public UtenteRegistrato(String email,String nomeUtente,String password,ruolo ruolo,String nome, String cognome, Calendar dataNascita, sesso sesso, String cellulare, String città, String indirizzo) {
        this.nomeUtente = nomeUtente;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.cellulare = cellulare;
        this.indirizzo = indirizzo;
        this.città= città;
        this.dataNascita = dataNascita;
        this.ruolo=ruolo;
        this.sesso=sesso;
    }
    
    public UtenteRegistrato(){
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

    public void setDataNascita(Calendar dataNascita) {
        this.dataNascita = dataNascita;
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

    public Calendar getDataNascita() {
        return dataNascita;
    }

    public ruolo getRuolo() {
        return ruolo;
    }

    public sesso getSesso() {
        return sesso;
    }

    public void setRuolo(ruolo ruolo) {
        this.ruolo = ruolo;
    }

    public void setSesso(sesso sesso) {
        this.sesso = sesso;
    }

    public String getCittà() {
        return città;
    }

    public void setCittà(String città) {
        this.città = città;
    }

    @Override
    public String toString() {
        return getClass().getName()+"{" + "nomeUtente=" + nomeUtente + ", password=" + password + ", nome=" + nome + ", cognome=" + cognome + ", email=" + email + ", cellulare=" + cellulare + ", indirizzo=" + indirizzo + ", dataNascita=" + dataNascita + ", ruolo=" + ruolo + ", sesso=" + sesso+ ", città=" + città +"}";
    }
    
}
