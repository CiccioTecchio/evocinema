
package database;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedList;
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
    
     /**
     * Permette di estrarre le tuple di tipo {@link FilmConValutazioneMedia} dal DB.
     * @return Lista delle Opere di tipo "film" presenti nel DB con tanto di valutazione media degli utenti .
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
    public synchronized ArrayList<FilmConValutazioneMedia> getAllFilmValutazioni( ) throws SQLException, ParseException, NamingException {
      
       Connection connection=null;
       PreparedStatement stmt=null;
       ArrayList<FilmConValutazioneMedia> filmConValutazione = new ArrayList<FilmConValutazioneMedia>();
       connection = (Connection) SingletonDBConnection.getInstance().getConnInst();
       
       try {
           
            stmt = (PreparedStatement) connection.prepareStatement(" SELECT opera.* , AVG( valutazione ) AS valutazione FROM recensioni,opera "
                    + "                                                 WHERE opera.idOpera = recensioni.id_opera GROUP BY idOpera");

            ResultSet rs = stmt.executeQuery();

            int idOpera;
            String  titolo , locandina , regia , cast ,genere ;
            Time durata;
            Calendar dataUscita;
            vistoCensura vistocensura; 
            tipo tipo;
            String distribuzione; 
            String produzione; 
            String trama ;
            String trailer ; 
            float valutazione; 
            
		while (rs.next()) {
                   
                        
                        idOpera = rs.getInt("idOpera");
                        tipo = Film.tipo.valueOf(rs.getString("tipo")); 
                        titolo = rs.getString("titolo");
			locandina = rs.getString("locandina");
                        regia = rs.getString("regia");
                        cast = rs.getString("cast");
                        genere = rs.getString("genere");
                        durata = rs.getTime("durata");
                        
                        dataUscita = Calendar.getInstance();
                        dataUscita.setTime(rs.getDate("data_uscita"));
                        
                        
                        vistocensura = Film.vistoCensura.valueOf(rs.getString("visto_censura"));
                        distribuzione = rs.getString("distribuzione");
                        produzione = rs.getString("produzione");
                        trama = rs.getString("trama");
                        trailer = rs.getString("trailer");
                        
                        valutazione = rs.getFloat("valutazione"); 
                        
                        FilmConValutazioneMedia filmValMedia = new FilmConValutazioneMedia(idOpera , tipo , titolo , locandina , regia , cast , genere , durata , dataUscita , vistocensura , distribuzione,produzione,trama,trailer,valutazione);
                        
                        filmConValutazione.add(filmValMedia);
                        
                        
                    }
                    

		}finally{
       
                    if( stmt != null  ) stmt.close();
       
       }
       
       
       
		return filmConValutazione;
   }
    
    
}
