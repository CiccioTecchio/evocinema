
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
import model.Sala;
import model.Spettacolo;

/**
 *Classe di accesso al DB per il prelievo dei dati di tipo {@link Spettacolo}
 * @author sarad
 */
public class SpettacoloDAO {
    
    private static Logger logger= Logger.getLogger("global");
    private Connection connection;
     
    /**
     * Costruttore della classe {@link SpettacoloDAO}
     * @throws NamingException
     * @throws SQLException 
     */
    public SpettacoloDAO() throws NamingException, SQLException {
        connection=(Connection) SingletonDBConnection.getInstance().getConnInst();
    }
   
    /**
     * Metodo per il preliezo dell'istanza della connessione attuale.
     * @return la connessione attuale.
     */
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
    public synchronized List<Spettacolo> getAllSpettacoli() throws SQLException, ParseException, NamingException {
      
       PreparedStatement stmt=null;
       List<Spettacolo> spettacoli = new LinkedList<>();
       
       try {
           stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.Spettacolo");
           ResultSet rs = stmt.executeQuery();
           while (rs.next()) {
               Spettacolo s = new Spettacolo();
               
               s.setIdSpettacolo(rs.getInt("idSpettacolo"));
               s.setIdSala(rs.getInt("id_sala"));
               s.setIdFilm(rs.getInt("idOpera"));
               s.setTitolo(rs.getString("titolo"));
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
           stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.Spettacolo WHERE idSpettacolo = ?");
           stmt.setInt(1, idSpettacolo);
           ResultSet rs = stmt.executeQuery();
           
           while(rs.next()){
                spettacoloFound.setIdSpettacolo(rs.getInt("idSpettacolo"));
                spettacoloFound.setIdSala(rs.getInt("id_sala"));
                spettacoloFound.setIdFilm(rs.getInt("idOpera"));
                spettacoloFound.setTitolo(rs.getString("titolo"));
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
                spettacoloFound.setMatricePosti(rs.getString("matrice_posti"));
           }
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
    public synchronized List<Spettacolo> foundBySala(int idSala) throws SQLException, ParseException, NamingException {
      
       PreparedStatement stmt=null;
       List<Spettacolo> spettacoli = new LinkedList<>();
       
       try {
           stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.Spettacolo WHERE id_sala='"+idSala+"'");
           ResultSet rs = stmt.executeQuery();
           
           while (rs.next()) {
                Spettacolo s=new Spettacolo();
                
                s.setIdSpettacolo(rs.getInt("idSpettacolo"));
                s.setIdSala(rs.getInt("id_sala"));
                s.setIdFilm(rs.getInt("idOpera"));
                s.setTitolo(rs.getString("titolo"));
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
    public synchronized List<Spettacolo> foundByDate(Calendar date) throws SQLException, ParseException, NamingException {
      
       PreparedStatement stmt=null;
       List<Spettacolo> spettacoli = new LinkedList<>();
       
       try {
           stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.Spettacolo WHERE data_inizio <= ? AND data_fine >= ?");
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
                if (stmt != null)
                    stmt.close();
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
    public synchronized List<Spettacolo> foundByOpera(int idOpera) throws SQLException, NamingException {
      
       PreparedStatement stmt=null;
       List<Spettacolo> spettacoli = new LinkedList<>();
       
       try {
           stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.Spettacolo WHERE idOpera='"+idOpera+"'");
           ResultSet rs = stmt.executeQuery();
           
           while (rs.next()) {
                Spettacolo s=new Spettacolo();
                
                s.setIdSpettacolo(rs.getInt("idSpettacolo"));
                s.setIdSala(rs.getInt("id_sala"));
                s.setIdFilm(rs.getInt("idOpera"));
                s.setTitolo(rs.getString("titolo"));
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
        SimpleDateFormat sdfg= new SimpleDateFormat("yyyy-MM-dd");
        String dataInizio=sdfg.format(s.getDataInizio().getTime());
        String dataFine=sdfg.format(s.getDataFine().getTime());
        SimpleDateFormat sdfh= new SimpleDateFormat("HH:mm:ss");
        String oraInizio= sdfh.format(s.getOraInizio().getTime());
        String oraFine= sdfg.format(s.getOraFine().getTime());
        
        try {
            stmt = (PreparedStatement) connection.prepareStatement("INSERT INTO evo_cinema.Spettacolo (idSpettacolo, id_sala, idOpera, titolo, data_inizio, data_fine, prezzo, ora_inizio, ora_fine, matrice_posti) VALUES ('"+ s.getIdSpettacolo() +"', '"+ s.getIdSala()+"', '"+ s.getIdFilm()+"', '"+s.getTitolo()+"', '"+dataInizio+"' , '"+dataFine+"', '"+ s.getPrezzo()+"', '"+oraInizio+"', '"+oraFine+"' , '"+s.getMatricePosti()+"')");
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
       SimpleDateFormat sdfg= new SimpleDateFormat("yyyy-MM-dd");
       String dataInizio=sdfg.format(s.getDataInizio().getTime());
       String dataFine=sdfg.format(s.getDataFine().getTime());
       SimpleDateFormat sdfh= new SimpleDateFormat("HH:mm:ss");
       String oraInizio= sdfh.format(s.getOraInizio().getTime());
       String oraFine= sdfg.format(s.getOraFine().getTime());
       
       try {
            stmt = (PreparedStatement) connection.prepareStatement("UPDATE evo_cinema.Spettacolo SET id_sala='"+ s.getIdSala()+"', idOpera='"+ s.getIdFilm()+"', titolo='"+s.getTitolo()+"', data_inizio= '"+dataInizio+"', data_fine='"+dataFine+"', prezzo='"+s.getPrezzo()+"', ora_inizio='"+oraInizio+"', ora_fine='"+oraFine+"', matrice_posti='"+s.getMatricePosti()+"' WHERE ( idSpettacolo='"+ s.getIdSpettacolo()+ "');");
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
            stmt = (PreparedStatement) connection.prepareStatement("DELETE FROM evo_cinema.Spettacolo WHERE ( idSpettacolo='"+ idSpettacolo +"');");
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
