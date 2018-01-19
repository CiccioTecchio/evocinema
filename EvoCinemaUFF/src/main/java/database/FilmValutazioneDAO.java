
package database;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedList;
import java.util.logging.Logger;
import javax.naming.NamingException;
import model.Film;
import model.Film.tipo;
import model.Film.vistoCensura;
import model.FilmConValutazioneMedia;

/**
 * Classe di accesso al DB per il prelievo dei dati di tipo {@link FilmConValutazioneMedia}
 * @author GiuseppeDelGaudio
 */
public class FilmValutazioneDAO {
    private static Logger logger= Logger.getLogger("global");
    private Connection connection;
    
    /*
     * Metodo costruttore della classe.
     * @throws SQLException
     * @throws NamingException
     */
    public FilmValutazioneDAO() throws NamingException, SQLException {
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
     * Permette di estrarre le tuple di tipo {@link FilmConValutazioneMedia} dal DB.
     * @return Lista delle Opere di tipo "film" presenti nel DB con tanto di valutazione media degli utenti .
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
    public synchronized Collection<FilmConValutazioneMedia> getAllFilmValutazioni( ) throws SQLException, ParseException, NamingException {
      
      
       PreparedStatement stmt=null;
       Collection<FilmConValutazioneMedia> filmConValutazione = new ArrayList<FilmConValutazioneMedia>(); 
       connection = (Connection) SingletonDBConnection.getInstance().getConnInst();
       
       try {
           
            stmt = (PreparedStatement) connection.prepareStatement(" SELECT Opera.* , AVG( valutazione ) AS valutazione FROM Recensioni,Opera "
                    + "                                                 WHERE Opera.idOpera = Recensioni.id_opera GROUP BY idOpera");

            ResultSet rs = stmt.executeQuery();
 
            
		while (rs.next()) {
                   
                        FilmConValutazioneMedia filmValMedia = new FilmConValutazioneMedia();
                        
                        filmValMedia.setIdFilm(rs.getInt("idOpera"));
                        filmValMedia.setTipo(Film.tipo.valueOf(rs.getString("tipo"))); 
                        filmValMedia.setTitolo(rs.getString("titolo"));
			filmValMedia.setLocandina(rs.getString("locandina"));
                        filmValMedia.setRegia(rs.getString("regia"));
                        filmValMedia.setCast(rs.getString("cast"));
                        filmValMedia.setGenere(rs.getString("genere"));
                        filmValMedia.setDurata(rs.getTime("durata"));
                        
                        Calendar dataUscita = Calendar.getInstance();
                        dataUscita.setTime(rs.getDate("data_uscita"));
                        filmValMedia.setDataUscita(dataUscita);
                        
                        filmValMedia.setVistoCensura(Film.vistoCensura.valueOf(rs.getString("visto_censura")));
                        filmValMedia.setDistribuzione(rs.getString("distribuzione"));
                        filmValMedia.setProduzione(rs.getString("produzione"));
                        filmValMedia.setTrama(rs.getString("trama"));
                        filmValMedia.setTrailer(rs.getString("trailer"));
                        
                        filmValMedia.setValutazioneMedia(rs.getFloat("valutazione")); 
                        
                        
                        filmConValutazione.add(filmValMedia);
                        
                        
                    }
                    

		}finally{
       
                    if( stmt != null  ) stmt.close();
       
       }
       
       
       
		return filmConValutazione;
   }
    
    
}
