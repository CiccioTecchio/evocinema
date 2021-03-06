
package database;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;
import javax.naming.NamingException;
import model.Film;
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
    
    /**
     * Usato prevalentemente per i JUNIT
     * @throws NamingException
     * @throws SQLException 
     */
    public FilmValutazioneDAO(Connection conn) throws NamingException, SQLException{
        connection = conn;
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
    public synchronized List<FilmConValutazioneMedia> getAllFilmValutazioni( ) throws SQLException, ParseException, NamingException {
      
       PreparedStatement stmt=null;
       List<FilmConValutazioneMedia> filmConValutazione = new ArrayList<>(); 
       
       try {
            stmt = (PreparedStatement) connection.prepareStatement("select * from\n" +
                                                         "((select Opera.* , avg( valutazione ) as valutazione from "
                                                                  + "Recensioni,Opera "
                                                                    + "where Opera.idOpera = Recensioni.id_opera AND eliminato = 'FALSE' "
                                                                    + "group by idOpera order by titolo)  \n" +
                                                                        "union All\n" +
                                                                        "(select * , (null) as 'valutazione' from Opera WHERE eliminato = 'FALSE' ) ) as t group by t.idOpera");
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
