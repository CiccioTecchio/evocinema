
package database;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedList;
import javax.naming.NamingException;
import model.Film;
import model.FilmConValutazioneMedia;

/**
 * Classe di accesso al DB per il prelievo dei dati di tipo {@link FilmConValutazioneMedia}
 * @author GiuseppeDelGaudio
 */
public class FilmValutazioneDAO {
    
     /**
     * Permette di estrarre le tuple di tipo {@link FilmConValutazioneMedia} dal DB.
     * @param order tipo di ordinamento desiderato per l'output dal DB
     * @return Lista delle Opere di tipo "film" presenti nel DB con tanto di valutazione media degli utenti .
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
    public synchronized Collection<FilmConValutazioneMedia> getAllFilmValutazioni(String order ) throws SQLException, ParseException, NamingException {
      
       Connection connection=null;
       PreparedStatement stmt=null;
       Collection<FilmConValutazioneMedia> filmConValutazione = new LinkedList<FilmConValutazioneMedia>();
       connection = (Connection) SingletonDBConnection.getInstance().getConnInst();
       
       try {
           
            stmt = (PreparedStatement) connection.prepareStatement(" SELECT opera.* , AVG( valutazione ) AS valutazione FROM recensioni,opera "
                    + "                                                 WHERE opera.idOpera = recensioni.id_opera GROUP BY idOpera ORDER BY "+order);

            ResultSet rs = stmt.executeQuery();

            
            
            
		while (rs.next()) {
                   
                        Film f = new Film();
                        f.setIdFilm(rs.getInt("idOpera"));
                        f.setTipo(Film.tipo.valueOf(rs.getString("tipo")));
                        f.setTitolo(rs.getString("titolo"));
			f.setLocandina(rs.getString("locandina"));
                        f.setRegia(rs.getString("regia"));
                        f.setCast(rs.getString("cast"));
                        f.setGenere(rs.getString("genere"));
                        f.setDurata(rs.getTime("durata"));
                        
                        Calendar dataUscita = Calendar.getInstance();
                        dataUscita.setTime(rs.getDate("data"));
                        f.setDataUscita(dataUscita);
                        
                        f.setVistoCensura(Film.vistoCensura.valueOf(rs.getString("visto_censura")));
                        f.setDistribuzione(rs.getString("distribuzione"));
                        f.setProduzione(rs.getString("produzione"));
                        f.setTrama(rs.getString("trama"));
                        f.setTrailer(rs.getString("trailer"));
                        
                        FilmConValutazioneMedia filmValMedia = new FilmConValutazioneMedia(f , rs.getFloat("valutazione") );
                        
                        filmConValutazione.add(filmValMedia);
                        
                        
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
       
       
       
		return filmConValutazione;
   }
    
    
}
