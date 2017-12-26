
package Database;

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



public class FilmDAO {
    private static Logger logger= Logger.getLogger("global");
    
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
    
    public synchronized Film foundByID(int idFilm) throws SQLException, ParseException, NamingException{
        
       Connection connection=null;
       PreparedStatement stmt=null;
       Film film = new Film();
       connection = (Connection) SingletonDBConnection.getInstance().getConnInst();
       
       try {
           
            stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.opera where idOpera= '"+ idFilm +"' ");

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
}
