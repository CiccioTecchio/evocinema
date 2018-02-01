/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import javax.naming.NamingException;
import model.Gestore;
import model.Operatore;
import model.Spettacolo;
import model.UtenteBase;
import model.UtenteRegistrato;
import model.UtenteRegistrato.ruolo;
import static model.UtenteRegistrato.ruolo.GESTORE;
import static model.UtenteRegistrato.ruolo.OPERATORE;
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
    
    /**
     * Usato prevalentemente per i JUNIT
     * @throws NamingException
     * @throws SQLException 
     */
    public UtenteRegistratoDAO(Connection conn) throws NamingException, SQLException {
        connection = conn;
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
    public synchronized List<UtenteRegistrato> getUtentiByRuolo(ruolo tipo) throws SQLException, ParseException, NamingException {

        PreparedStatement stmt = null;
        List<UtenteRegistrato> utentiRegistrati = new LinkedList<>();

        try {
            stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.Utente WHERE ruolo= ? ");
            stmt.setString(1, tipo.toString());
            ResultSet rs = stmt.executeQuery();

            if (tipo.equals(ruolo.GESTORE)) {
                while (rs.next()) {
                    Gestore ut = new Gestore();
                    ut.setEmail(rs.getString("email"));
                    ut.setNomeUtente(rs.getString("nome_utente"));
                    ut.setPassword(rs.getString("password"));
                    ut.setRuolo(UtenteRegistrato.ruolo.valueOf(rs.getString("ruolo")));
                    ut.setNome(rs.getString("nome"));
                    ut.setCognome(rs.getString("cognome"));
                    Calendar dataNascita = Calendar.getInstance();
                    dataNascita.setTime(rs.getDate("data_nascita"));
                    ut.setDataNascita(dataNascita);
                    ut.setSesso(UtenteRegistrato.sesso.valueOf(rs.getString("sesso")));
                    ut.setCellulare(rs.getString("cellulare"));
                    ut.setCittà(rs.getString("città"));
                    ut.setIndirizzo(rs.getString("indirizzo"));
                    utentiRegistrati.add(ut);
                }
            }
            else if (tipo.equals(ruolo.OPERATORE)) {
                while (rs.next()) {
                    Operatore ut = new Operatore();
                    ut.setEmail(rs.getString("email"));
                    ut.setNomeUtente(rs.getString("nome_utente"));
                    ut.setPassword(rs.getString("password"));
                    ut.setRuolo(UtenteRegistrato.ruolo.valueOf(rs.getString("ruolo")));
                    ut.setNome(rs.getString("nome"));
                    ut.setCognome(rs.getString("cognome"));
                    Calendar dataNascita = Calendar.getInstance();
                    dataNascita.setTime(rs.getDate("data_nascita"));
                    ut.setDataNascita(dataNascita);
                    ut.setSesso(UtenteRegistrato.sesso.valueOf(rs.getString("sesso")));
                    ut.setCellulare(rs.getString("cellulare"));
                    ut.setCittà(rs.getString("città"));
                    ut.setIndirizzo(rs.getString("indirizzo"));
                    utentiRegistrati.add(ut);
                }
            }
            else if (tipo.equals(ruolo.UTENTE)) {
                while (rs.next()) {
                    UtenteBase ut = new UtenteBase();
                    ut.setEmail(rs.getString("email"));
                    ut.setNomeUtente(rs.getString("nome_utente"));
                    ut.setPassword(rs.getString("password"));
                    ut.setRuolo(UtenteRegistrato.ruolo.valueOf(rs.getString("ruolo")));
                    ut.setNome(rs.getString("nome"));
                    ut.setCognome(rs.getString("cognome"));
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
     * Metodo per la ricerca di un oggetto di tipo {@link UtenteRegistrato} avente
     * l'{@link UtenteBase.emailUtente} uguale al parametro passato.
     *
     * @param email Identificativo dell'oggetto {@link UtenteRegistrato}
     * @return Oggetto di tipo {@link UtenteBase}
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException
     */
    public synchronized UtenteRegistrato foundByEmail(String email) throws SQLException, ParseException, NamingException {

        PreparedStatement stmt = null;
        UtenteRegistrato utenteToReturn= null;
        try {
            stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.Utente WHERE email= ? ");
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            
                
                if(rs.getString("ruolo").equals(OPERATORE.name())){
                    while(rs.next()){
                        Operatore utenteFound= new Operatore();
                        utenteFound.setEmail(rs.getString("email"));
                        utenteFound.setNomeUtente(rs.getString("nome_utente"));
                        utenteFound.setPassword(rs.getString("password"));
                        utenteFound.setRuolo(UtenteRegistrato.ruolo.valueOf(rs.getString("ruolo")));
                        utenteFound.setNome(rs.getString("nome"));
                        utenteFound.setCognome(rs.getString("cognome"));
                        Calendar dataNascita = Calendar.getInstance();
                        dataNascita.setTime(rs.getDate("data_nascita"));
                        utenteFound.setDataNascita(dataNascita);
                        utenteFound.setSesso(UtenteRegistrato.sesso.valueOf(rs.getString("sesso")));
                        utenteFound.setCellulare(rs.getString("cellulare"));
                        utenteFound.setCittà(rs.getString("città"));
                        utenteFound.setIndirizzo(rs.getString("indirizzo"));
                        utenteToReturn=utenteFound;
                    }
                }
                else if(rs.getString("ruolo").equals(GESTORE.name())){
                    while(rs.next()){
                        Gestore utenteFound= new Gestore();
                        utenteFound.setEmail(rs.getString("email"));
                        utenteFound.setNomeUtente(rs.getString("nome_utente"));
                        utenteFound.setPassword(rs.getString("password"));
                        utenteFound.setRuolo(UtenteRegistrato.ruolo.valueOf(rs.getString("ruolo")));
                        utenteFound.setNome(rs.getString("nome"));
                        utenteFound.setCognome(rs.getString("cognome"));
                        Calendar dataNascita = Calendar.getInstance();
                        dataNascita.setTime(rs.getDate("data_nascita"));
                        utenteFound.setDataNascita(dataNascita);
                        utenteFound.setSesso(UtenteRegistrato.sesso.valueOf(rs.getString("sesso")));
                        utenteFound.setCellulare(rs.getString("cellulare"));
                        utenteFound.setCittà(rs.getString("città"));
                        utenteFound.setIndirizzo(rs.getString("indirizzo"));
                        utenteToReturn=utenteFound;
                    }
                }
        }finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return utenteToReturn;
    }
    
    public synchronized UtenteBase foundUtenteBaseByEmail(String email) throws SQLException, ParseException, NamingException {

        PreparedStatement stmt = null;
        UtenteBase utenteFound= new UtenteBase();
        try {
            stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.Utente WHERE email= ? ");
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                    utenteFound.setEmail(rs.getString("email"));
                    utenteFound.setNomeUtente(rs.getString("nome_utente"));
                    utenteFound.setCognome(rs.getString("cognome"));
                    utenteFound.setPassword(rs.getString("password"));
                    utenteFound.setRuolo(UtenteRegistrato.ruolo.valueOf(rs.getString("ruolo")));
                    utenteFound.setNome(rs.getString("nome"));
                    utenteFound.setCognome(rs.getString("cognome"));
                    Calendar dataNascita = Calendar.getInstance();
                    dataNascita.setTime(rs.getDate("data_nascita"));
                    utenteFound.setDataNascita(dataNascita);
                    utenteFound.setSesso(UtenteRegistrato.sesso.valueOf(rs.getString("sesso")));
                    utenteFound.setCellulare(rs.getString("cellulare"));
                    utenteFound.setCittà(rs.getString("città"));
                    utenteFound.setIndirizzo(rs.getString("indirizzo"));
                    utenteFound.setSaldo(rs.getFloat("saldo"));
            }
        }finally {
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
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        String dataN=sdf.format(ut.getDataNascita().getTime());
        try {
            if(ut.getRuolo().equals(UTENTE)){
                UtenteBase u= (UtenteBase) ut;

                stmt = (PreparedStatement) connection.prepareStatement("UPDATE evo_cinema.Utente SET nome_utente= ? , password= ? , ruolo= ? , nome= ? , cognome= ?, data_nascita= ? , "
                        +" sesso= ? , cellulare= ? , città= ? , indirizzo= ? , saldo= ?  WHERE ( email= ? );");
                stmt.setFloat(11, u.getSaldo());
                stmt.setString(12, ut.getEmail());

               //stmt = (PreparedStatement) connection.prepareStatement("UPDATE evo_cinema.Utente SET nome_utente='" + u.getNomeUtente() + "', password='" + u.getPassword() + "', ruolo='" + u.getRuolo() + "', nome='" + u.getNome() + "', cognome='" + u.getCognome() + "', data_nascita='" + dataN+ "', sesso='" + u.getSesso() + "', cellulare='" + u.getCellulare() + "', città='" + u.getCittà() + "', indirizzo='" + u.getIndirizzo() + "', saldo='"+u.getSaldo()+"' WHERE ( email='" + u.getEmail() + "');");

            }
            else{
                stmt = (PreparedStatement) connection.prepareStatement("UPDATE evo_cinema.Utente "
                                                                        + "SET nome_utente= ? , password= ? , ruolo= ? , nome= ? , cognome= ?, data_nascita= ? , "
                        + "                                             sesso= ? , cellulare= ? , città= ? , indirizzo= ?  WHERE ( email= ? );");
                stmt.setString(11, ut.getEmail());
            }
            stmt.setString(1, ut.getNomeUtente());
            stmt.setString(2, ut.getPassword());
            stmt.setString(3, ut.getRuolo().toString());
            stmt.setString(4, ut.getNome());
            stmt.setString(5, ut.getCognome());
            stmt.setString(6, dataN);
            stmt.setString(7, ut.getSesso().toString());
            stmt.setString(8, ut.getCellulare());
            stmt.setString(9, ut.getCittà());
            stmt.setString(10,ut.getIndirizzo());
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
     * Metodo per la modifica dei dati di un oggetto di tipo {@link UtenteBase}
     * all'interno del DB
     *
     * @param ut Oggetto di tipo {@link UtenteBase}
     * @return 'true' in caso di successo o 'false' in caso di fallimento
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException
     */
    public synchronized boolean updateUtenteBase(UtenteBase ut) throws SQLException, ParseException, NamingException {

        PreparedStatement stmt = null;
        boolean update = false;
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        String dataN=sdf.format(ut.getDataNascita().getTime());
        try {

            stmt = (PreparedStatement) connection.prepareStatement("UPDATE evo_cinema.Utente SET nome_utente= ? , password= ? , ruolo= ? , nome= ? , cognome= ?, data_nascita= ? , "
                        +" sesso= ? , cellulare= ? , città= ? , indirizzo= ? , saldo= ?  WHERE ( email= ? );");
            stmt.setFloat(11, ut.getSaldo());
            stmt.setString(12, ut.getEmail());

          //  stmt = (PreparedStatement) connection.prepareStatement("UPDATE evo_cinema.Utente SET nome_utente='" + ut.getNomeUtente() + "', password='" + ut.getPassword() + "', ruolo='" + ut.getRuolo() + "', nome='" + ut.getNome() + "', cognome='" + ut.getCognome() + "', data_nascita='" + dataN+ "', sesso='" + ut.getSesso() + "', cellulare='" + ut.getCellulare() + "', città='" + ut.getCittà() + "', indirizzo='" + ut.getIndirizzo() + "', saldo='"+ut.getSaldo()+"' WHERE ( email='" + ut.getEmail() + "');");
            stmt.setString(1, ut.getNomeUtente());
            stmt.setString(2, ut.getPassword());
            stmt.setString(3, ut.getRuolo().toString());
            stmt.setString(4, ut.getNome());
            stmt.setString(5, ut.getCognome());
            stmt.setDate(6, new Date(ut.getDataNascita().getTimeInMillis()));
            stmt.setString(7, ut.getSesso().toString());
            stmt.setString(8, ut.getCellulare());
            stmt.setString(9, ut.getCittà());
            stmt.setString(10,ut.getIndirizzo());
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
    public synchronized boolean createUtenteRegistrato(UtenteRegistrato u) throws SQLException, ParseException, NamingException {

        PreparedStatement stmt = null;
        boolean inserito = false;
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        String dataN=sdf.format(u.getDataNascita().getTime());
        try {
            if(u.getRuolo().equals(UTENTE)){
                UtenteBase ut= (UtenteBase) u;
                stmt = (PreparedStatement) connection.prepareStatement("INSERT INTO evo_cinema.Utente ( email, nome_utente, password, ruolo, nome, cognome, data_nascita, sesso, cellulare, città, indirizzo, saldo)VALUES ('" + ut.getEmail() + "', '" + ut.getNomeUtente() + "', '" + ut.getPassword() + "', '" + ut.getRuolo() + "', '" + ut.getNome() + "', '" + ut.getCognome() + "', '" + dataN + "', '" + ut.getSesso() + "', '" + ut.getCellulare() + "', '" + ut.getCittà() + "', '" + ut.getIndirizzo() + "' , '"+ut.getSaldo()+"')");
            }else{
                stmt = (PreparedStatement) connection.prepareStatement("INSERT INTO evo_cinema.Utente ( email, nome_utente, password, ruolo, nome, cognome, data_nascita, sesso, cellulare, città, indirizzo)VALUES ('" + u.getEmail() + "', '" + u.getNomeUtente() + "', '" + u.getPassword() + "', '" + u.getRuolo() + "', '" + u.getNome() + "', '" + u.getCognome() + "', '" + dataN + "', '" + u.getSesso() + "', '" + u.getCellulare() + "', '" + u.getCittà() + "', '" + u.getIndirizzo() + "')");
            }
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
     * Metodo per la cancellazione di un oggetto di tipo {@link UtenteRegistrato}.
     *
     * @param email identificativo dell' {@link UtenteBase} da cancellare.
     * @return 'true' in caso di successo o 'false' in caso di fallimento
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException
     */
    public synchronized boolean deleteUtenteRegistrato(String email) throws SQLException, ParseException, NamingException {

        PreparedStatement stmt = null;
        boolean delete = false;

        try {
            stmt = (PreparedStatement) connection.prepareStatement("DELETE FROM evo_cinema.Utente WHERE ( email='" + email + "');");
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
    public synchronized UtenteRegistrato controllaLogin(String email, String password) throws SQLException, ParseException, NamingException {

        PreparedStatement stmt = null;
        UtenteRegistrato utente = null;

        try {
            stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.Utente WHERE"
                    + " email = '" + email + " ' AND password = '" + password + "' ");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                String tipo = rs.getString("ruolo");

                if (tipo.equals("GESTORE")) {
                    utente = new Gestore();

                    utente.setEmail(rs.getString("email"));
                    utente.setNomeUtente(rs.getString("nome_utente"));
                    utente.setPassword(rs.getString("password"));
                    utente.setRuolo(UtenteRegistrato.ruolo.valueOf(rs.getString("ruolo")));
                    utente.setNome(rs.getString("nome"));
                    utente.setCognome(rs.getString("cognome"));
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
                    utente.setCognome(rs.getString("cognome"));
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
                    utente.setCognome(rs.getString("cognome"));
                    Calendar dataNascita = Calendar.getInstance();
                    dataNascita.setTime(rs.getDate("data_nascita"));
                    utente.setDataNascita(dataNascita);
                    utente.setSesso(UtenteRegistrato.sesso.valueOf(rs.getString("sesso")));
                    utente.setCellulare(rs.getString("cellulare"));
                    utente.setCittà(rs.getString("città"));
                    utente.setIndirizzo(rs.getString("indirizzo"));
                    ((UtenteBase) utente).setSaldo(rs.getFloat("saldo"));

                    return utente;
                }
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return utente;
    }
    
    public synchronized Boolean controllaUtente(String username) throws SQLException {
        
        PreparedStatement stmt = null;
        Boolean flag = false;
        
        try{
            stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.Utente WHERE"
                    + " nome_utente = '" + username + "' ");
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                flag = true;
            }
            
            return flag;
        }finally{
            if (stmt != null){
                stmt.close();
            }
        }
        
    }

}
