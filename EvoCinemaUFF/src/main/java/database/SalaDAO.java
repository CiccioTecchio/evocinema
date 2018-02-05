/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import javax.naming.NamingException;
import model.Sala;

/**
 * Classe di accesso al DB per il prelievo dei dati di tipo {@link Sala}
 * @author sarad
 */
public class SalaDAO {
    
    private static Logger logger= Logger.getLogger("global");
    
    private Connection connection;
    private PreparedStatement stmt = null;
    private Sala salaFound = new Sala();
    
    /**
     * Costruttore della classe {@link SalaDAO} che istanzia una connection.
     * @throws NamingException
     * @throws SQLException 
     */
    public SalaDAO() throws NamingException, SQLException {
        connection=(com.mysql.jdbc.Connection) SingletonDBConnection.getInstance().getConnInst();
    }
    
    /**
     * Usato prevalentemente per i JUNIT
     * @throws NamingException
     * @throws SQLException 
     */
    public SalaDAO(com.mysql.jdbc.Connection conn) {
        connection = conn;
    }
   
    /**
     * Metodo di prelievo della connessione attuale.
     * @return l'istanza attuale della connessione.
     */
    public Connection getDAOConnection(){
        return this.connection;
    }
    /**
     * Permette di estrarre tuple di tipo {@link Sala} dal DB.
     * @return Lista delle Sale presenti all'interno del DB.
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     * 
     * 
     */
    public synchronized List<Sala> getAllSale() throws SQLException, ParseException, NamingException {
      
       PreparedStatement stmt=null;
       List<Sala> sale = new LinkedList<>();
       
       try {
           stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.Sala");
           ResultSet rs = stmt.executeQuery();
           while (rs.next()) {
               Sala s = new Sala();
               s.setIdSala(rs.getInt("id_sala"));
               s.setNumeroPosti(rs.getInt("numero_posti"));
               s.setConfigPosti(rs.getString("configurazione_posti"));
               sale.add(s);
           }
        } finally {
            if (stmt != null)
                stmt.close();
	}
    return sale;
   }
   
   /**
    * Metodo per la ricerca nel DB della {@link Sala} avente l'ID specificato nei parametri.
    * @param idSala Identificativo della Sala
    * @return Un oggetto di tipo {@link Sala}
    * @throws SQLException
    * @throws ParseException
    * @throws NamingException 
    */
   public synchronized Sala foundByID(int idSala) throws SQLException, ParseException, NamingException {
       try {
           stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.Sala WHERE id_sala= ? ");
           stmt.setInt(1, idSala);
           ResultSet rs = stmt.executeQuery();
           rs.next();
           salaFound.setIdSala(rs.getInt("id_sala"));
           salaFound.setNumeroPosti(rs.getInt("numero_posti"));
           salaFound.setConfigPosti(rs.getString("configurazione_posti"));
       }finally{
           if (stmt != null)
                stmt.close();
           }
        return salaFound;
    }
    
   /**
    * Metodo per l'inserimento di una {@link Sala} all'interno del DB.
    * @param s Oggetto di tipo {@link Sala}
    * @return 'true' in caso di successo o 'false' in caso di fallimento.
    * @throws SQLException
    * @throws ParseException
    * @throws NamingException 
    */
    public synchronized boolean createSala(Sala s) throws SQLException, ParseException, NamingException{
        
        PreparedStatement stmt=null;
        boolean inserita= false;

        try {
            stmt = (PreparedStatement) connection.prepareStatement("INSERT INTO evo_cinema.Sala (id_sala, numero_posti, configurazione_posti) VALUES ( ? , ? , ? )");
            stmt.setInt(1, s.getIdSala());
            stmt.setInt(2, s.getNumeroPosti());
            stmt.setString(3, s.getConfigPosti());
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
     * Metodo per la modifica dei dati della {@link Sala} passata come parametro.
     * @param s Oggetto di tipo {@link Sala}
     * @return 'true' in caso di successo o 'false' in caso di fallimento.
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
    public synchronized boolean updateSala(Sala s) throws SQLException, ParseException, NamingException{
        
       
       PreparedStatement stmt=null;
       boolean update= false;       
       try {
            stmt = (PreparedStatement) connection.prepareStatement("UPDATE evo_cinema.Sala SET numero_posti= ? , configurazione_posti= ?  WHERE ( id_sala= ? );");
            stmt.setInt(1, s.getNumeroPosti());
            stmt.setString(2, s.getConfigPosti());
            stmt.setInt(3, s.getIdSala());
            
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
     * Metodo per la cancellazione di una {@link Sala} passata come parametro.
     * @param idSala Identificativo della {@link Sala}
     * @return 'true' in caso di successo o 'false' in caso di fallimento.
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
    public synchronized boolean deleteSale(int idSala) throws SQLException, ParseException, NamingException{
        
       PreparedStatement stmt=null;
       boolean delete= false;       
       try {
            stmt = (PreparedStatement) connection.prepareStatement("DELETE FROM evo_cinema.Sala WHERE ( id_sala= ? );");
            stmt.setInt(1, idSala);
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
