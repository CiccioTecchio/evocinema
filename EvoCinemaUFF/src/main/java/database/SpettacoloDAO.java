
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
import model.Film;
import model.Spettacolo;

/**
 *Classe di accesso al DB per il prelievo dei dati di tipo {@link Spettacolo}
 * @author sarad
 */
public class SpettacoloDAO {
    
    private static Logger logger= Logger.getLogger("global");
    private Connection connection;
     
    public SpettacoloDAO() throws NamingException, SQLException {
        connection=(Connection) SingletonDBConnection.getInstance().getConnInst();
    }
   
    public Connection getDAOConnection(){
        return this.connection;
    }
    
    /**
     * Metodo per la ricerca di Spettacoli presenti nel DB.
     * @return Lista di oggetti di tipo {@link Spettacolo}.
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
    public synchronized Collection<Spettacolo> getAllSpettacoli() throws SQLException, ParseException, NamingException {
      
       PreparedStatement stmt=null;
       Collection<Spettacolo> spettacoli = new LinkedList<Spettacolo>();
       
       try {
           stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.spettacolo");
           ResultSet rs = stmt.executeQuery();
           while (rs.next()) {
               Spettacolo s = new Spettacolo();
               
               s.setIdSpettacolo(rs.getInt("idSpettacolo"));
               s.setIdSala(rs.getInt("id_sala"));
               s.setIdFilm(rs.getInt("idOpera"));
               Calendar dataInizio = Calendar.getInstance();
               dataInizio.setTime(rs.getDate("data_inizio"));
               s.setDataInizio(dataInizio);
               Calendar dataFine = Calendar.getInstance();
               dataFine.setTime(rs.getDate("data_fine"));
               s.setDataFine(dataFine);
               s.setPrezzo(rs.getFloat("prezzo"));
               Calendar oraInizio= Calendar.getInstance();
               oraInizio.setTime(rs.getTime("ora_inizio"));
               s.setOraInizio(oraInizio);
               Calendar oraFine= Calendar.getInstance();
               oraInizio.setTime(rs.getTime("ora_fine"));
               s.setOraFine(oraFine);
               spettacoli.add(s);
           }
       } finally{
                if (stmt != null)
                    stmt.close();
		}
    return spettacoli;
   }
    
    /**
     * Metodo per la ricerca di uno {@link Spettacolo} avente l'ID specificato nel parametro.
     * @param idSpettacolo Identificativo dello {@link Spettacolo}
     * @return Oggetto di tipo {@link Spettacolo}
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
    public synchronized Spettacolo foundByID(int idSpettacolo) throws SQLException, ParseException, NamingException {
      
       PreparedStatement stmt=null;
       Spettacolo spettacoloFound = new Spettacolo();
       
       try {
           stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.spettacolo WHERE idSpettacolo='"+idSpettacolo+"'");
           ResultSet rs = stmt.executeQuery();
           
           spettacoloFound.setIdSpettacolo(rs.getInt("idSpettacolo"));
           spettacoloFound.setIdSala(rs.getInt("id_sala"));
           spettacoloFound.setIdFilm(rs.getInt("idOpera"));
           Calendar dataInizio = Calendar.getInstance();
           dataInizio.setTime(rs.getDate("data_inizio"));
           spettacoloFound.setDataInizio(dataInizio);
           Calendar dataFine = Calendar.getInstance();
           dataFine.setTime(rs.getDate("data_fine"));
           spettacoloFound.setDataFine(dataFine);
           spettacoloFound.setPrezzo(rs.getFloat("prezzo"));
           Calendar oraInizio= Calendar.getInstance();
           oraInizio.setTime(rs.getTime("ora_inizio"));
           spettacoloFound.setOraInizio(oraInizio);
           Calendar oraFine= Calendar.getInstance();
           oraInizio.setTime(rs.getTime("ora_fine"));
           spettacoloFound.setOraFine(oraFine);
           
           } finally{
                    if (stmt != null)
                        stmt.close();
                    }
    return spettacoloFound;
    }
    
    /**
     * Metodo per la ricerca di oggetti di tipo {@link Spettacolo} aventi l'ID dell'attributo {@link Spettacolo.idSala} uguale al parametro passato.
     * @param idSala Identificativo della {@link Sala}
     * @return Lista di oggetti di tipo {@link Spettacolo}
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
    public synchronized Collection<Spettacolo> foundBySala(int idSala) throws SQLException, ParseException, NamingException {
      
       PreparedStatement stmt=null;
       Collection<Spettacolo> spettacoli = new LinkedList<Spettacolo>();
       
       try {
           stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.spettacolo WHERE id_sala='"+idSala+"'");
           ResultSet rs = stmt.executeQuery();
           
           while (rs.next()) {
                Spettacolo s=new Spettacolo();
                
                s.setIdSpettacolo(rs.getInt("idSpettacolo"));
                s.setIdSala(rs.getInt("id_sala"));
                s.setIdFilm(rs.getInt("idOpera"));
                Calendar dataInizio = Calendar.getInstance();
                dataInizio.setTime(rs.getDate("data_inizio"));
                s.setDataInizio(dataInizio);
                Calendar dataFine = Calendar.getInstance();
                dataFine.setTime(rs.getDate("data_fine"));
                s.setDataFine(dataFine);
                s.setPrezzo(rs.getFloat("prezzo"));
                Calendar oraInizio= Calendar.getInstance();
                oraInizio.setTime(rs.getTime("ora_inizio"));
                s.setOraInizio(oraInizio);
                Calendar oraFine= Calendar.getInstance();
                oraInizio.setTime(rs.getTime("ora_fine"));
                s.setOraFine(oraFine);
                spettacoli.add(s);
                }
           } finally{
                    if (stmt != null)
                        stmt.close();
                    }
    return spettacoli;
    }
    
    /**
     * Metodo per la ricerca di oggetti di tipo {@link Spettacolo} per cui la data passata come parametro è compresa tra quella di inizio e quella di fine.
     * @param date La data per la quale cercare gli spettacoli
     * @return Lista di oggetti di tipo {@link Spettacolo}
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
    public synchronized Collection<Spettacolo> foundByDate(Calendar date) throws SQLException, ParseException, NamingException {
      
       Connection connection=null;
       PreparedStatement stmt=null;
       Collection<Spettacolo> spettacoli = new LinkedList<Spettacolo>();
       connection = (Connection) SingletonDBConnection.getInstance().getConnInst();
       
       try {
           stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.spettacolo WHERE data_inizio <= ? AND data_fine >= ?");
           stmt.setDate(1, new java.sql.Date(date.getTimeInMillis()));
           stmt.setDate(2, new java.sql.Date(date.getTimeInMillis()));
           ResultSet rs = stmt.executeQuery();
           
           while (rs.next()) {
                Spettacolo s=new Spettacolo();
                
                s.setIdSpettacolo(rs.getInt("idSpettacolo"));
                s.setIdSala(rs.getInt("id_sala"));
                s.setIdFilm(rs.getInt("idOpera"));
                Calendar dataInizio = Calendar.getInstance();
                dataInizio.setTime(rs.getDate("data_inizio"));
                s.setDataInizio(dataInizio);
                Calendar dataFine = Calendar.getInstance();
                dataFine.setTime(rs.getDate("data_fine"));
                s.setDataFine(dataFine);
                s.setPrezzo(rs.getFloat("prezzo"));
                Calendar oraInizio= Calendar.getInstance();
                oraInizio.setTime(rs.getTime("ora_inizio"));
                s.setOraInizio(oraInizio);
                Calendar oraFine= Calendar.getInstance();
                oraInizio.setTime(rs.getTime("ora_fine"));
                s.setOraFine(oraFine);
                s.setMatricePosti(rs.getString("matrice_posti"));
                spettacoli.add(s);
                }
           } finally{
                try {
                    if (stmt != null)
                        stmt.close();
                    } finally {
                        if (connection != null)
                            connection.close();
                       }
           }
    return spettacoli;
    }
    
    /**
     * Metodo per la ricerca di oggetti di tipo {@link Spettacolo} aventi l'ID dell'attributo {@link Spettacolo.idFilm} uguale al parametro passato.
     * @param idOpera Identificativo del {@link Film}
     * @return  Lista di oggetti di tipo {@link Spettacolo}
     * @throws SQLException
     * @throws ParseExceptionstring
     * @throws NamingException 
     */
    public synchronized Collection<Spettacolo> foundByOpera(int idOpera) throws SQLException, ParseException, NamingException {
      
       PreparedStatement stmt=null;
       Collection<Spettacolo> spettacoli = new LinkedList<Spettacolo>();
       
       try {
           stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.spettacolo WHERE idOpera='"+idOpera+"'");
           ResultSet rs = stmt.executeQuery();
           
           while (rs.next()) {
                Spettacolo s=new Spettacolo();
                
                s.setIdSpettacolo(rs.getInt("idSpettacolo"));
                s.setIdSala(rs.getInt("id_sala"));
                s.setIdFilm(rs.getInt("idOpera"));
                Calendar dataInizio = Calendar.getInstance();
                dataInizio.setTime(rs.getDate("data_inizio"));
                s.setDataInizio(dataInizio);
                Calendar dataFine = Calendar.getInstance();
                dataFine.setTime(rs.getDate("data_fine"));
                s.setDataFine(dataFine);
                s.setPrezzo(rs.getFloat("prezzo"));
                Calendar oraInizio= Calendar.getInstance();
                oraInizio.setTime(rs.getTime("ora_inizio"));
                s.setOraInizio(oraInizio);
                Calendar oraFine= Calendar.getInstance();
                oraInizio.setTime(rs.getTime("ora_fine"));
                s.setOraFine(oraFine);
                spettacoli.add(s);
                }
           } finally{
                    if (stmt != null)
                        stmt.close();
                    }
    return spettacoli;
    }
    
    /**
     * Metodo per l'inserimento di un oggetto di tipo {@link Spettacolo} all'interno del DB.
     * @param s Oggetto di tipo {@link Spettacolo}
     * @return 'true' in caso di successo o 'false' in caso di fallimento
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
    public synchronized boolean createSpettacolo(Spettacolo s) throws SQLException, ParseException, NamingException{
        
        PreparedStatement stmt=null;
        boolean inserito= false;

        try {
            stmt = (PreparedStatement) connection.prepareStatement("INSERT INTO evo_cinema.spettacolo (idSpettacolo, id_sala, idOpera, data_inizio, data_finr, prezzo, ora_inizio, ora_fine) VALUES ('"+ s.getIdSpettacolo() +"', '"+ s.getIdSala()+"', '"+ s.getIdFilm()+"', '"+ s.getDataInizio()+"', '"+ s.getDataFine()+"', '"+ s.getPrezzo()+"', '"+ s.getOraInizio()+"', '"+ s.getOraFine()+"')");
            stmt.executeUpdate();
            inserito= true;
        } 
        finally {
                if (stmt != null)
                     stmt.close();
            }
        return inserito;
    }
    
    /**
     * Metodo per la modifica dei dati di un oggetto di tipo {@link Spettacolo} all'interno del DB
     * @param s Oggetto di tipo {@link Spettacolo} contenente le modifiche
     * @return 'true' in caso di successo o 'false' in caso di fallimento
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
    public synchronized boolean updateSpettacolo(Spettacolo s) throws SQLException, ParseException, NamingException{
        
       PreparedStatement stmt=null;
       boolean update= false;
       
       try {
            stmt = (PreparedStatement) connection.prepareStatement("UPDATE evo_cinema.spettacolo SET id_sala='"+ s.getIdSala()+"', idOpera='"+ s.getIdFilm()+"', data_inizio='"+s.getDataInizio()+"', data_fine='"+s.getDataFine()+"', prezzo='"+s.getPrezzo()+"', ora_inizio='"+s.getOraInizio()+"', ora_fine='"+s.getOraFine()+"' WHERE ( idSpettacolo='"+ s.getIdSpettacolo()+ "');");
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
     * Metodo per la cancellazione di un oggetto di tipo {@link Spettacolo}.
     * @param idSpettacolo identificativo dello {@link Spettacolo} da cancellare.
     * @return 'true' in caso di successo o 'false' in caso di fallimento
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
    public synchronized boolean deleteSpettacolo(int idSpettacolo) throws SQLException, ParseException, NamingException{
        
       PreparedStatement stmt=null;
       boolean delete= false;
       
       try {
            stmt = (PreparedStatement) connection.prepareStatement("DELETE FROM evo_cinema.spettacolo WHERE ( idSpettacolo='"+ idSpettacolo +"');");
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
