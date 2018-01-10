
package database;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedList;
import java.util.logging.Logger;
import javax.naming.NamingException;
import model.Film;
import model.Film.tipo;
import model.Film.vistoCensura;

 /**
 * Classe di accesso al DB per il prelievo dei dati di tipo {@link Film}
 * @author micheledellipaoli
 */
public class FilmDAO {
    private static Logger logger= Logger.getLogger("global");
    
    /**
     * Permette di estrarre le tuple di tipo {@link Film} dal DB.
     * @return Lista delle Opere {@link Film} presenti all'interno del DB.
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
    public synchronized Collection<Film> getAllOpere() throws SQLException, ParseException, NamingException {
      
       Connection connection=null;
       PreparedStatement stmt=null;
       Collection<Film> film = new LinkedList<Film>();
       connection = (Connection) SingletonDBConnection.getInstance().getConnInst();
       
       try {
           
            stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.opera");

            ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
                   
                        Film f = new Film();
                        f.setIdFilm(rs.getInt("idOpera"));
                        f.setTipo(tipo.valueOf(rs.getString("tipo")));
                        f.setTitolo(rs.getString("titolo"));
			f.setLocandina(rs.getString("locandina"));
                        f.setRegia(rs.getString("regia"));
                        f.setCast(rs.getString("cast"));
                        f.setGenere(rs.getString("genere"));
                        f.setDurata(rs.getTime("durata"));
                        
                        Calendar dataUscita = Calendar.getInstance();
                        dataUscita.setTime(rs.getDate("data"));
                        f.setDataUscita(dataUscita);
                        
                        f.setVistoCensura(vistoCensura.valueOf(rs.getString("visto_censura")));
                        f.setDistribuzione(rs.getString("distribuzione"));
                        f.setProduzione(rs.getString("produzione"));
                        f.setTrama(rs.getString("trama"));
                        f.setTrailer(rs.getString("trailer"));
                        film.add(f);
                    }
                    

		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
       
       logger.info(film+"");
       
		return film;
   }
    
    /**
     * Permette di estrarre le tuple di tipo {@link Film} dal DB.
     * @return Lista delle Opere che sono di tipo "film" presenti all'interno del DB.
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
    public synchronized Collection<Film> getAllFilm() throws SQLException, ParseException, NamingException {
      
       Connection connection=null;
       PreparedStatement stmt=null;
       Collection<Film> film = new LinkedList<Film>();
       connection = (Connection) SingletonDBConnection.getInstance().getConnInst();
       
       try {
           
            stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.opera where tipo= 'film' ");

            ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
                   
                        Film f = new Film();
                        f.setIdFilm(rs.getInt("idOpera"));
                        f.setTipo(tipo.valueOf(rs.getString("tipo")));
                        f.setTitolo(rs.getString("titolo"));
			f.setLocandina(rs.getString("locandina"));
                        f.setRegia(rs.getString("regia"));
                        f.setCast(rs.getString("cast"));
                        f.setGenere(rs.getString("genere"));
                        f.setDurata(rs.getTime("durata"));
                        
                        Calendar dataUscita = Calendar.getInstance();
                        dataUscita.setTime(rs.getDate("data"));
                        f.setDataUscita(dataUscita);
                        
                        f.setVistoCensura(vistoCensura.valueOf(rs.getString("visto_censura")));
                        f.setDistribuzione(rs.getString("distribuzione"));
                        f.setProduzione(rs.getString("produzione"));
                        f.setTrama(rs.getString("trama"));
                        f.setTrailer(rs.getString("trailer"));
                        film.add(f);
                    }
                    

		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
       
       logger.info(film+"");
       
		return film;
   }
    
    /**
     * Permette di estrarre le tuple di tipo {@link Film} dal DB.
     * @return Lista delle Opere che sono di tipo "teatro" presenti all'interno del DB.
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
    public synchronized Collection<Film> getAllTeatro() throws SQLException, ParseException, NamingException {
      
       Connection connection=null;
       PreparedStatement stmt=null;
       Collection<Film> film = new LinkedList<Film>();
       connection = (Connection) SingletonDBConnection.getInstance().getConnInst();
       
       try {
           
            stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.opera where tipo= 'teatro' ");

            ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
                   
                        Film f = new Film();
                        f.setIdFilm(rs.getInt("idOpera"));
                        f.setTipo(tipo.valueOf(rs.getString("tipo")));
                        f.setTitolo(rs.getString("titolo"));
			f.setLocandina(rs.getString("locandina"));
                        f.setRegia(rs.getString("regia"));
                        f.setCast(rs.getString("cast"));
                        f.setGenere(rs.getString("genere"));
                        f.setDurata(rs.getTime("durata"));
                        
                        Calendar dataUscita = Calendar.getInstance();
                        dataUscita.setTime(rs.getDate("data"));
                        f.setDataUscita(dataUscita);
                        
                        f.setVistoCensura(vistoCensura.valueOf(rs.getString("visto_censura")));
                        f.setDistribuzione(rs.getString("distribuzione"));
                        f.setProduzione(rs.getString("produzione"));
                        f.setTrama(rs.getString("trama"));
                        f.setTrailer(rs.getString("trailer"));
                        film.add(f);
                    }
                    

		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
       
       logger.info(film+"");
       
		return film;
   }
    
    /**
     * Metodo per la ricerca nel DB di un' opera.
     * @param idFilm identificativo dell' opera.
     * @return Oggetto di tipo Film che ha come id il parametro passato.
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
    public synchronized Film foundByID(int idFilm) throws SQLException, ParseException, NamingException{
        
       Connection connection=null;
       PreparedStatement stmt=null;
       Film film = new Film();
       connection = (Connection) SingletonDBConnection.getInstance().getConnInst();
       
       try {
           
            stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.opera WHERE idOpera= '"+ idFilm +"' ");

            ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
                   
                        Film f = new Film();
                        f.setIdFilm(rs.getInt("idOpera"));
                        f.setTipo(tipo.valueOf(rs.getString("tipo")));
                        f.setTitolo(rs.getString("titolo"));
			f.setLocandina(rs.getString("locandina"));
                        f.setRegia(rs.getString("regia"));
                        f.setCast(rs.getString("cast"));
                        f.setGenere(rs.getString("genere"));
                        f.setDurata(rs.getTime("durata"));
                        
                        Calendar dataUscita = Calendar.getInstance();
                        dataUscita.setTime(rs.getDate("data"));
                        f.setDataUscita(dataUscita);
                        
                        f.setVistoCensura(vistoCensura.valueOf(rs.getString("visto_censura")));
                        f.setDistribuzione(rs.getString("distribuzione"));
                        f.setProduzione(rs.getString("produzione"));
                        f.setTrama(rs.getString("trama"));
                        f.setTrailer(rs.getString("trailer"));
                        
                    }
                    

		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
       
       logger.info(film+"");
       
		return film;
        
    }
    
    /**
     * Metodo per l'inserimento di una nuova Opera nel DB
     * @param f Oggetto di Film {@link Film}
     * @return 'true' per il corretto inserimento o 'false' in caso di inserimento fallito.
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
    public synchronized boolean createFilm(Film f) throws SQLException, ParseException, NamingException{
        
       boolean inserito= false;
       Connection connection=null;
       PreparedStatement stmt=null;
       connection = (Connection) SingletonDBConnection.getInstance().getConnInst();
       
        
       
       try {
           
            stmt = (PreparedStatement) connection.prepareStatement("INSERT INTO evo_cinema.opera (idOpera, tipo, titolo, locandina, regia, cast, genere, durata, data_uscita, visto_censura, distribuzione, produzione, trama, trailer) VALUES ('"+ f.getIdFilm() +"', '"+ f.getTipo()+"', '"+ f.getTitolo()+"', '"+ f.getLocandina()+"', '"+ f.getRegia()+"', '"+ f.getCast()+"', '"+ f.getGenere()+"', '"+ f.getDurata()+"', '"+ f.getDataUscita().getTime()+"', '"+ f.getVistoCensura()+"', '"+ f.getDistribuzione()+"', '"+ f.getProduzione()+"', '"+ f.getTrama()+"', '"+ f.getTrailer()+")");
            stmt.executeUpdate();
            
            inserito = true;
            } finally {
			try {
				if (stmt != null)
					stmt.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return inserito;
	}
    
    
    /**
     * Metodo per la modifica dei dati di un {@link Film} nel DB.
     * @param f Oggetto di tipo {@link Film}
     * @return 'true' per il corretto update o 'false' in caso di update fallito.
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
    public synchronized boolean updateFilm(Film f) throws SQLException, ParseException, NamingException{
       
       boolean modificato= false;
       Connection connection=null;
       PreparedStatement stmt=null;
       connection = (Connection) SingletonDBConnection.getInstance().getConnInst();
       
        
       
       try {
           
            stmt = (PreparedStatement) connection.prepareStatement("UPDATE evo_cinema.opera SET idOpera='"+ f.getIdFilm() +"', tipo='"+ f.getTipo()+"', titolo='"+ f.getTitolo() +"', locandina='"+ f.getLocandina()+"', regia='"+ f.getRegia()+"', cast='"+ f.getCast()+"', genere='"+ f.getGenere()+"', durata='"+ f.getDurata() +"', data_uscita='"+ f.getDataUscita().getTime()+"', visto_censura='"+ f.getVistoCensura()+"', distribuzione='"+f.getDistribuzione()+"', produzione='"+f.getProduzione()+"', trama='"+f.getTrama()+"', trailer='"+f.getTrailer()+"'  WHERE ( idOpera='"+ f.getIdFilm() +"');");
            stmt.executeUpdate();
            
            modificato = true;
            } finally {
			try {
				if (stmt != null)
					stmt.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return modificato;
	}
    
    /**
     * Metodo per la cancellazione di un {@link Film} all'interno del DB
     * @param idOpera ID di tipo intero del {@link Film}
     * @return 'true' per la corretta rimozione o 'false' in caso di rimozione fallita.
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
     public synchronized boolean deleteFilm(int idOpera) throws SQLException, ParseException, NamingException{
       
       boolean eliminato= false;
       Connection connection=null;
       PreparedStatement stmt=null;
       connection = (Connection) SingletonDBConnection.getInstance().getConnInst();
       
        
       
       try {
           
            stmt = (PreparedStatement) connection.prepareStatement("DELETE FROM evo_cinema.opera WHERE (idOpera='"+ idOpera +"');");
            stmt.executeUpdate();
            
            eliminato = true;
            } finally {
			try {
				if (stmt != null)
					stmt.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return eliminato;
	}
}