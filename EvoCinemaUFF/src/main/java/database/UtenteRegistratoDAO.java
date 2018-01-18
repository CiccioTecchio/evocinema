/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedList;
import java.util.logging.Logger;
import javax.naming.NamingException;
import model.Gestore;
import model.Operatore;
import model.UtenteBase;
import model.UtenteRegistrato;
import model.UtenteRegistrato.ruolo;
import static model.UtenteRegistrato.ruolo.GESTORE;
import static model.UtenteRegistrato.ruolo.UTENTE;

/**
 *
 * @author sarad
 */
public class UtenteRegistratoDAO {

    private static Logger logger = Logger.getLogger("global");
    private Connection connection;

    public UtenteRegistratoDAO() throws NamingException, SQLException {
        connection = (Connection) SingletonDBConnection.getInstance().getConnInst();
    }

    public Connection getDAOConnection() {
        return this.connection;
    }

    /**
     * Metodo per la ricerca di oggetti di tipo {@link UtenteBase} nel DB
     *
     * @return Lista di oggetti di tipo {@link UtenteBase}
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException
     */
    public synchronized Collection<UtenteRegistrato> getAllUtenti(String tipo) throws SQLException, ParseException, NamingException {

        PreparedStatement stmt = null;
        Collection<UtenteRegistrato> utentiRegistrati = new LinkedList<UtenteRegistrato>();

        try {
            stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.utente WHERE ruolo=" + tipo + "");
            ResultSet rs = stmt.executeQuery();

            //rs per Gestore
            if (tipo.equals(ruolo.GESTORE)) {
                while (rs.next()) {
                    Gestore ut = new Gestore();
                    ut.setEmail(rs.getString("email"));
                    ut.setNomeUtente(rs.getString("nome_utente"));
                    ut.setPassword(rs.getString("password"));
                    ut.setRuolo(UtenteRegistrato.ruolo.valueOf(rs.getString("ruolo")));
                    ut.setNome(rs.getString("nome"));
                    Calendar dataNascita = Calendar.getInstance();
                    dataNascita.setTime(rs.getDate("data_nascita"));
                    ut.setDataNascita(dataNascita);
                    ut.setSesso(UtenteRegistrato.sesso.valueOf(rs.getString("sesso")));
                    ut.setCellulare(rs.getString("cellulare"));
                    ut.setCittà(rs.getString("città"));
                    ut.setIndirizzo(rs.getString("indirizzo"));
                    utentiRegistrati.add(ut);
                }
            } //rs per Operatore
            else if (tipo.equals(ruolo.OPERATORE)) {
                while (rs.next()) {
                    Operatore ut = new Operatore();
                    ut.setEmail(rs.getString("email"));
                    ut.setNomeUtente(rs.getString("nome_utente"));
                    ut.setPassword(rs.getString("password"));
                    ut.setRuolo(UtenteRegistrato.ruolo.valueOf(rs.getString("ruolo")));
                    ut.setNome(rs.getString("nome"));
                    Calendar dataNascita = Calendar.getInstance();
                    dataNascita.setTime(rs.getDate("data_nascita"));
                    ut.setDataNascita(dataNascita);
                    ut.setSesso(UtenteRegistrato.sesso.valueOf(rs.getString("sesso")));
                    ut.setCellulare(rs.getString("cellulare"));
                    ut.setCittà(rs.getString("città"));
                    ut.setIndirizzo(rs.getString("indirizzo"));
                    utentiRegistrati.add(ut);
                }
            } //rs per UtenteBase
            else if (tipo.equals(ruolo.UTENTE)) {
                while (rs.next()) {
                    UtenteBase ut = new UtenteBase();
                    ut.setEmail(rs.getString("email"));
                    ut.setNomeUtente(rs.getString("nome_utente"));
                    ut.setPassword(rs.getString("password"));
                    ut.setRuolo(UtenteRegistrato.ruolo.valueOf(rs.getString("ruolo")));
                    ut.setNome(rs.getString("nome"));
                    Calendar dataNascita = Calendar.getInstance();
                    dataNascita.setTime(rs.getDate("data_nascita"));
                    ut.setDataNascita(dataNascita);
                    ut.setSesso(UtenteRegistrato.sesso.valueOf(rs.getString("sesso")));
                    ut.setCellulare(rs.getString("cellulare"));
                    ut.setCittà(rs.getString("città"));
                    ut.setIndirizzo(rs.getString("indirizzo"));
                    ut.setSaldo(rs.getFloat("saldo"));
                    utentiRegistrati.add(ut);
                }
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return utentiRegistrati;
    }

    /**
     * Metodo per la ricerca di un oggetto di tipo {@link UtenteBase} avente
     * l'{@link UtenteBase.emailUtente} uguale al parametro passato.
     *
     * @param email Identificativo dell'oggetto {@link UtenteBase}
     * @return Oggetto di tipo {@link UtenteBase}
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException
     */
    public synchronized UtenteRegistrato foundByEmail(String email) throws SQLException, ParseException, NamingException {

        PreparedStatement stmt = null;
        //TODO modificare e inserire 3 costruttori a seconda del ruolo
        UtenteRegistrato utenteFound = new UtenteBase();

        try {
            stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.utente WHERE email='" + email + "'");
            ResultSet rs = stmt.executeQuery();
            utenteFound.setEmail(rs.getString("email"));
            utenteFound.setNomeUtente(rs.getString("nome_utente"));
            utenteFound.setPassword(rs.getString("password"));
            utenteFound.setRuolo(UtenteRegistrato.ruolo.valueOf(rs.getString("ruolo")));
            utenteFound.setNome(rs.getString("nome"));
            Calendar dataNascita = Calendar.getInstance();
            dataNascita.setTime(rs.getDate("data_nascita"));
            utenteFound.setDataNascita(dataNascita);
            utenteFound.setSesso(UtenteRegistrato.sesso.valueOf(rs.getString("sesso")));
            utenteFound.setCellulare(rs.getString("cellulare"));
            utenteFound.setCittà(rs.getString("città"));
            utenteFound.setIndirizzo(rs.getString("indirizzo"));
            ((UtenteBase)utenteFound).setSaldo(rs.getFloat("saldo"));
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return utenteFound;
    }

    /**
     * Metodo per la modifica dei dati di un oggetto di tipo {@link UtenteBase}
     * all'interno del DB
     *
     * @param ut Oggetto di tipo {@link UtenteBase}
     * @return 'true' in caso di successo o 'false' in caso di fallimento
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException
     */
    public synchronized boolean updateUtenteRegistrato(UtenteRegistrato ut) throws SQLException, ParseException, NamingException {

        PreparedStatement stmt = null;
        boolean update = false;

        //TO DO modificare statement
        try {
            stmt = (PreparedStatement) connection.prepareStatement("UPDATE evo_cinema.utente SET nome_utente='" + ut.getNomeUtente() + "', password='" + ut.getPassword() + "', ruolo='" + ut.getRuolo() + "', nome='" + ut.getNome() + "', cognome='" + ut.getCognome() + "', data_nascita='" + ut.getDataNascita() + "', sesso='" + ut.getSesso() + "', cellulare='" + ut.getCellulare() + "', città='" + ut.getCittà() + "', indirizzo='" + ut.getIndirizzo() + "', saldo='" + ut.getSaldo() + "' WHERE ( email='" + ut.getEmail() + "');");
            stmt.executeUpdate();
            update = true;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return update;
    }

    /**
     * Metodo per l'inserimento di un oggetto di tipo {@link Spettacolo}
     * all'interno del DB.
     *
     * @param u Oggetto di tipo {@link UtenteBase}
     * @return 'true' in caso di successo o 'false' in caso di fallimento
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException
     */
    public synchronized boolean createUtente(UtenteRegistrato u) throws SQLException, ParseException, NamingException {

        PreparedStatement stmt = null;
        boolean inserito = false;

        //TODO modificate statement
        try {
            stmt = (PreparedStatement) connection.prepareStatement("INSERT INTO evo_cinema.utente ( email, nome_utente, password, ruolo, nome, cognome, data_nascita, sesso, cellulare, città, indirizzo, saldo)VALUES ('" + u.getEmail() + "', '" + u.getNomeUtente() + "', '" + u.getPassword() + "', '" + u.getRuolo() + "', '" + u.getNome() + "', '" + u.getCognome() + "', '" + u.getDataNascita() + "', '" + u.getSesso() + "', '" + u.getCellulare() + "', '" + u.getCittà() + "', '" + u.getIndirizzo() + "', '" + u.getSaldo() + "')");
            stmt.executeUpdate();
            inserito = true;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return inserito;
    }

    /**
     * Metodo per la cancellazione di un oggetto di tipo {@link Spettacolo}.
     *
     * @param email identificativo dell' {@link UtenteBase} da cancellare.
     * @return 'true' in caso di successo o 'false' in caso di fallimento
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException
     */
    public synchronized boolean deleteUtente(String email) throws SQLException, ParseException, NamingException {

        PreparedStatement stmt = null;
        boolean delete = false;

        try {
            stmt = (PreparedStatement) connection.prepareStatement("DELETE FROM evo_cinema.utente WHERE ( email='" + email + "');");
            stmt.executeUpdate();
            delete = true;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return delete;
    }
    
    /**
     * Metodo per controllo utente nel DB per login
     *
     * @return tipo dell'utente
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException
     */
    public synchronized UtenteRegistrato controllaLogin(String username, String password) throws SQLException, ParseException, NamingException {

        PreparedStatement stmt = null;
        UtenteRegistrato utente = null;
        
        try {
            stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.utente WHERE"
                    + " nome_utente=" + username + "AND password =" + password);
            ResultSet rs = stmt.executeQuery();
            String tipo = rs.getString("ruolo");
            
            if (tipo.equals("GESTORE")) {
                utente = new Gestore();

                utente.setEmail(rs.getString("email"));
                utente.setNomeUtente(rs.getString("nome_utente"));
                utente.setPassword(rs.getString("password"));
                utente.setRuolo(UtenteRegistrato.ruolo.valueOf(rs.getString("ruolo")));
                utente.setNome(rs.getString("nome"));
                Calendar dataNascita = Calendar.getInstance();
                dataNascita.setTime(rs.getDate("data_nascita"));
                utente.setDataNascita(dataNascita);
                utente.setSesso(UtenteRegistrato.sesso.valueOf(rs.getString("sesso")));
                utente.setCellulare(rs.getString("cellulare"));
                utente.setCittà(rs.getString("città"));
                utente.setIndirizzo(rs.getString("indirizzo"));

                return utente;
            } else if (tipo.equals("OPERATORE")) {
                utente = new Operatore();

                utente.setEmail(rs.getString("email"));
                utente.setNomeUtente(rs.getString("nome_utente"));
                utente.setPassword(rs.getString("password"));
                utente.setRuolo(UtenteRegistrato.ruolo.valueOf(rs.getString("ruolo")));
                utente.setNome(rs.getString("nome"));
                Calendar dataNascita = Calendar.getInstance();
                dataNascita.setTime(rs.getDate("data_nascita"));
                utente.setDataNascita(dataNascita);
                utente.setSesso(UtenteRegistrato.sesso.valueOf(rs.getString("sesso")));
                utente.setCellulare(rs.getString("cellulare"));
                utente.setCittà(rs.getString("città"));
                utente.setIndirizzo(rs.getString("indirizzo"));

                return utente;
            } else if (tipo.equals("UTENTE")) {
                utente = new UtenteBase();
                
                utente.setEmail(rs.getString("email"));
                utente.setNomeUtente(rs.getString("nome_utente"));
                utente.setPassword(rs.getString("password"));
                utente.setRuolo(UtenteRegistrato.ruolo.valueOf(rs.getString("ruolo")));
                utente.setNome(rs.getString("nome"));
                Calendar dataNascita = Calendar.getInstance();
                dataNascita.setTime(rs.getDate("data_nascita"));
                utente.setDataNascita(dataNascita);
                utente.setSesso(UtenteRegistrato.sesso.valueOf(rs.getString("sesso")));
                utente.setCellulare(rs.getString("cellulare"));
                utente.setCittà(rs.getString("città"));
                utente.setIndirizzo(rs.getString("indirizzo"));
                ((UtenteBase)utente).setSaldo(rs.getFloat("saldo"));

                return utente;
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return utente;
    }

}
