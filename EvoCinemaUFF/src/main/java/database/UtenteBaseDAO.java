
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
import model.UtenteBase;
import model.UtenteRegistrato.ruolo;
import static model.UtenteRegistrato.ruolo.UTENTE;
import model.UtenteRegistrato.sesso;

/**
 *Classe di accesso al DB per il prelievo dei dati di tipo {@link UtenteBase}
 * @author sarad
 */
public class UtenteBaseDAO {
    
    private static Logger logger= Logger.getLogger("global");
    private Connection connection;
     
    public UtenteBaseDAO() throws NamingException, SQLException {
        connection=(Connection) SingletonDBConnection.getInstance().getConnInst();
    }
   
    public Connection getDAOConnection(){
        return this.connection;
    }
    /**
     * Metodo per la ricerca di oggetti di tipo {@link UtenteBase} nel DB
     * @return Lista di oggetti di tipo {@link UtenteBase}
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
    public synchronized Collection<UtenteBase> getAllUtentiBase() throws SQLException, ParseException, NamingException {
      
       PreparedStatement stmt=null;
       Collection<UtenteBase> utentiBase = new LinkedList<UtenteBase>();
       
       try {
           stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.utente WHERE ruolo='"+UTENTE+"'");
           ResultSet rs = stmt.executeQuery();
           while (rs.next()) {
               UtenteBase ut = new UtenteBase();
               
               ut.setEmail(rs.getString("email"));
               ut.setNomeUtente(rs.getString("nome_utente"));
               ut.setPassword(rs.getString("password"));
               ut.setRuolo(ruolo.valueOf(rs.getString("ruolo")));
               ut.setNome(rs.getString("nome"));
               Calendar dataNascita = Calendar.getInstance();
               dataNascita.setTime(rs.getDate("data_nascita"));
               ut.setDataNascita(dataNascita);
               ut.setSesso(sesso.valueOf(rs.getString("sesso")));
               ut.setCellulare(rs.getString("cellulare"));
               ut.setCittà(rs.getString("città"));
               ut.setIndirizzo(rs.getString("indirizzo"));
               ut.setSaldo(rs.getFloat("saldo"));
               utentiBase.add(ut);
           }
       } finally{
                if (stmt != null)
                    stmt.close();
		}
    return utentiBase;
   }
    
    /**
     * Metodo per la ricerca di un oggetto di tipo {@link UtenteBase} avente l'{@link UtenteBase.emailUtente} uguale al parametro passato.
     * @param email Identificativo dell'oggetto {@link UtenteBase}
     * @return Oggetto di tipo {@link UtenteBase}
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
   public synchronized UtenteBase foundByEmail(String email) throws SQLException, ParseException, NamingException {
      
       PreparedStatement stmt=null;
       UtenteBase utenteFound = new UtenteBase();
       
       try {
           stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.utente WHERE email='"+email+"'");
           ResultSet rs = stmt.executeQuery();
           utenteFound.setEmail(rs.getString("email"));
           utenteFound.setNomeUtente(rs.getString("nome_utente"));
           utenteFound.setPassword(rs.getString("password"));
           utenteFound.setRuolo(ruolo.valueOf(rs.getString("ruolo")));
           utenteFound.setNome(rs.getString("nome"));
           Calendar dataNascita = Calendar.getInstance();
           dataNascita.setTime(rs.getDate("data_nascita"));
           utenteFound.setDataNascita(dataNascita);
           utenteFound.setSesso(sesso.valueOf(rs.getString("sesso")));
           utenteFound.setCellulare(rs.getString("cellulare"));
           utenteFound.setCittà(rs.getString("città"));
           utenteFound.setIndirizzo(rs.getString("indirizzo"));
           utenteFound.setSaldo(rs.getFloat("saldo"));
           } finally{
                    if (stmt != null)
                        stmt.close();
                    }
    return utenteFound;
    }
   
   /**
    * Metodo per la modifica dei dati di un oggetto di tipo {@link UtenteBase} all'interno del DB
    * @param ut Oggetto di tipo {@link UtenteBase}
    * @return 'true' in caso di successo o 'false' in caso di fallimento
    * @throws SQLException
    * @throws ParseException
    * @throws NamingException 
    */
   public synchronized boolean updateUtenteBase(UtenteBase ut) throws SQLException, ParseException, NamingException{
        
       PreparedStatement stmt=null;
       boolean update= false;
       
       try {
            stmt = (PreparedStatement) connection.prepareStatement("UPDATE evo_cinema.utente SET nome_utente='"+ ut.getNomeUtente()+"', password='"+ ut.getPassword() +"', ruolo='"+ ut.getRuolo()+"', nome='"+ut.getNome()+"', cognome='"+ut.getCognome()+"', data_nascita='"+ut.getDataNascita()+"', sesso='"+ut.getSesso()+"', cellulare='"+ut.getCellulare()+"', città='"+ut.getCittà()+"', indirizzo='"+ut.getIndirizzo()+"', saldo='"+ut.getSaldo()+"' WHERE ( email='"+ ut.getEmail()+ "');");
            stmt.executeUpdate();
            update = true;
        } 
        finally {
                if (stmt != null)
                    stmt.close();
            }
    return update;
    }
   
   /**
    * Metodo per l'inserimento di un oggetto di tipo {@link Spettacolo} all'interno del DB.
    * @param u Oggetto di tipo {@link UtenteBase}
    * @return 'true' in caso di successo o 'false' in caso di fallimento
    * @throws SQLException
    * @throws ParseException
    * @throws NamingException 
    */
    public synchronized boolean createUtenteBase(UtenteBase u) throws SQLException, ParseException, NamingException{
       
       PreparedStatement stmt=null;
       boolean inserito= false;
       
       try {
            stmt = (PreparedStatement) connection.prepareStatement("INSERT INTO evo_cinema.utente ( email, nome_utente, password, ruolo, nome, cognome, data_nascita, sesso, cellulare, città, indirizzo, saldo)VALUES ('"+ u.getEmail() +"', '"+ u.getNomeUtente()+"', '"+u.getPassword()+"', '"+u.getRuolo()+"', '"+u.getNome()+"', '"+u.getCognome()+"', '"+u.getDataNascita()+"', '"+ u.getSesso()+"', '"+ u.getCellulare()+"', '"+u.getCittà()+"', '"+u.getIndirizzo()+"', '"+u.getSaldo()+"')");
            stmt.executeUpdate();
            inserito = true;
        } 
        finally {
                if (stmt != null)
                    stmt.close();
        }
    return inserito;
    }
    
    /**
     * Metodo per la cancellazione di un oggetto di tipo {@link Spettacolo}.
     * @param email identificativo dell' {@link UtenteBase} da cancellare.
     * @return 'true' in caso di successo o 'false' in caso di fallimento
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
     public synchronized boolean deleteUtenteBase(String email) throws SQLException, ParseException, NamingException{
       
       PreparedStatement stmt=null;
       boolean delete= false;
       
       try {
            stmt = (PreparedStatement) connection.prepareStatement("DELETE FROM evo_cinema.utente WHERE ( email='"+ email +"');");
            stmt.executeUpdate();
            delete = true;
        } 
        finally {
                if (stmt != null)
                    stmt.close();
            }
    return delete;
    }
    
}
