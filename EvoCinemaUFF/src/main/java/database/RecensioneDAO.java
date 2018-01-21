package database;

import java.sql.Connection;
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
import model.Film;
import model.Recensione;


/**
 * Classe di accesso al DB per il prelievo dei dati di tipo {@link Recensione}
 * @author sarad
 */
public class RecensioneDAO {
    
    private static Logger logger= Logger.getLogger("global");
    private FilmDAO filmDAO = new FilmDAO();
    private Connection connection;
    
    /*
     * Metodo costruttore della classe.
     * @throws SQLException
     * @throws NamingException
     */
    public RecensioneDAO() throws NamingException, SQLException {
        connection=(Connection) SingletonDBConnection.getInstance().getConnInst();
    }
    
    /*
     * Metodo che restituisce la connessione di tipo {@link Connection}.
     * @return oggetto connessione di tipo {@link Connection}
     */
    public Connection getDAOConnection(){
        return this.connection;
    }
    /**
     * Permette di estrarre le tuple di Recensione dal DB.
     * @return Lista delle Recensioni presenti all'interno del DB.
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
    public synchronized List<Recensione> getAllRecensioni() throws SQLException, ParseException, NamingException {
      
       PreparedStatement stmt=null;
       List<Recensione> recensioni = new LinkedList<>();
       
       try {
            stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.Recensioni");
            ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
                    Recensione r = new Recensione();
                    r.setEmailUtente(rs.getString("email"));
                    int idFilm = rs.getInt("id_opera");
                    Film film = filmDAO.foundByID(idFilm);
                    r.setFilm(film);
                    r.setValutazione(rs.getFloat("valutazione"));
                    r.setTesto(rs.getString("testo"));
                    
                    Calendar data = Calendar.getInstance();
                    data.setTime(rs.getDate("data_recensione"));
                    r.setDataImmissione(data);
                        
                    recensioni.add(r);
                    }
                    

		} finally {
				if (stmt != null)
					stmt.close();
			}
       
		return recensioni;
   }
    
    /**
     * Metodo per la ricerca nel DB delle recensioni di un utente.
     * @param emailUtente identificativo dell'utente
     * @return Una collezione delle recensioni effettuate dall'utente passato come parametro
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
    public synchronized List<Recensione> foundByEmail(String emailUtente) throws SQLException, ParseException, NamingException{
        
       PreparedStatement stmt=null;
       List<Recensione> recensioni = new LinkedList<>();
       
       try {
            stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.Recensioni WHERE email= '"+ emailUtente +"' AND data_recensione IS NOT NULL AND testo IS NOT NULL");
            ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
                    Recensione r= new Recensione();
                    r.setEmailUtente(rs.getString("email"));
                    int idFilm = rs.getInt("id_opera");
                    Film film = filmDAO.foundByID(idFilm);
                    r.setFilm(film);
                    r.setValutazione(rs.getFloat("valutazione"));
                    r.setTesto(rs.getString("testo"));
                    
                    Calendar data = Calendar.getInstance();
                    data.setTime(rs.getDate("data_recensione"));
                    r.setDataImmissione(data);
                    
                    recensioni.add(r);
                    }
		} finally {
				if (stmt != null)
					stmt.close();
			}
    return recensioni;
    }
    
    /**
     * Consente di ricavare tutte le recensioni del film specificato.
     * @param idFilm Codice del Film
     * @return Lista delle recensioni associate all'id passato
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
    public synchronized List<Recensione> foundByFilm(int idFilm) throws SQLException, ParseException, NamingException{
        
       PreparedStatement stmt=null;
       List<Recensione> recensioni = new LinkedList<>();
       
       try {
            stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.Recensioni WHERE id_opera= '"+ idFilm +"' AND data_recensione IS NOT NULL AND testo IS NOT NULL");
            ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
                    Recensione r= new Recensione();
                    r.setEmailUtente(rs.getString("email"));
                    int idOpera = rs.getInt("id_opera");
                    Film film = filmDAO.foundByID(idOpera);
                    r.setFilm(film);
                    r.setValutazione(rs.getFloat("valutazione"));
                    r.setTesto(rs.getString("testo"));
                    
                    Calendar data = Calendar.getInstance();
                    data.setTime(rs.getDate("data_recensione"));
                    r.setDataImmissione(data);
                    
                    recensioni.add(r);
                    }
		} finally {
                        if (stmt != null)
					stmt.close();
			}
    return recensioni;
    }
    
    /**
     * Metodo per l'inserimento di una nuova Recensione nel DB
     * @param r Oggetto di tipo {@link Recensione}
     * @return 'true' per il corretto inserimento o 'false' in caso di inserimento fallito.
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
    public synchronized boolean createRecensione(Recensione r) throws SQLException, ParseException, NamingException{
       
       PreparedStatement stmt=null;
       boolean inserita= false;
       Calendar today = Calendar.getInstance();
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       try {
            stmt = (PreparedStatement) connection.prepareStatement("INSERT INTO evo_cinema.Recensioni (email, id_opera, valutazione, testo, data_recensione) VALUES ('"+ r.getEmailUtente() +"', '"+ r.getFilm().getIdFilm()+"', '"+ r.getValutazione() +"', '"+ r.getTesto()+"', '"+sdf.format(today.getTime())+"')");
            stmt.executeUpdate();
            inserita = true;
        } 
        finally {
                if (stmt != null)
                    stmt.close();
            }
        return inserita;
	}
    
    /**
     * Metodo per la modifica dei dati di una {@link Recensione} nel DB.
     * @param r Oggetto di tipo {@link Recensione}
     * @return 'true' per il corretto update o 'false' in caso di update fallito.
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
    public synchronized boolean updateRecensione(Recensione r) throws SQLException, ParseException, NamingException{
        
       PreparedStatement stmt=null;
       boolean update= false;
       Calendar today = Calendar.getInstance();
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       try {
            stmt = (PreparedStatement) connection.prepareStatement("UPDATE evo_cinema.Recensioni SET valutazione='"+ r.getValutazione() +"', testo='"+ r.getTesto()+"', data_recensione='"+sdf.format(today.getTime()) +"' WHERE ( email='"+ r.getEmailUtente()+"' AND id_opera='"+ r.getFilm().getIdFilm() + "');");
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
     * Metodo per la cancellazione di una {@link Recensione} all'interno del DB
     * @param r Oggetto di tipo {@link Recensione}
     * @return 'true' per la corretta rimozione o 'false' in caso di rimozione fallita.
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
    public synchronized boolean deleteRecensione(Recensione r) throws SQLException, ParseException, NamingException{
       
       PreparedStatement stmt=null;
       boolean delete= false;
       
       try {
            stmt = (PreparedStatement) connection.prepareStatement("DELETE FROM evo_cinema.Recensioni WHERE ( email='" +r.getEmailUtente()+ "' AND id_opera='"+ r.getFilm().getIdFilm() +"');");
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
