/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;
import java.util.LinkedList;
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
    
    public SalaDAO() throws NamingException, SQLException {
        connection=(com.mysql.jdbc.Connection) SingletonDBConnection.getInstance().getConnInst();
    }
   
    public Connection getDAOConnection(){
        return this.connection;
    }
    /**
     * Permette di estrarre tuple di Sala dal DB.
     * @return Lista delle Sale presenti all'interno del DB.
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     * 
     * 
     */
    
    public synchronized Sala foundByID(int idSala) throws SQLException, ParseException, NamingException {
       
       try {
           stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.sala WHERE id_sala="+idSala+"");
           ResultSet rs = stmt.executeQuery();
           rs.next();
           salaFound.setIdSala(rs.getInt("id_sala"));
           salaFound.setNumeroPosti(rs.getInt("numero_posti"));
           salaFound.setConfigPosti(rs.getString("configurazione_posti"));
       }
       finally{
           if (stmt != null)
                        stmt.close();
           }
    return salaFound;
    }
    
    public synchronized Collection<Sala> getAllSale() throws SQLException, ParseException, NamingException {
      
       PreparedStatement stmt=null;
       Collection<Sala> sale = new LinkedList<Sala>();
       
       try {
           stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.sala");
           ResultSet rs = stmt.executeQuery();
           while (rs.next()) {
               Sala s = new Sala();
               
               s.setIdSala(rs.getInt("id_sala"));
               s.setNumeroPosti(rs.getInt("numero_posti"));
               s.setConfigPosti(rs.getString("configurazione_posti"));
               sale.add(s);
           }
       } finally{
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
            stmt = (PreparedStatement) connection.prepareStatement("INSERT INTO evo_cinema.sala (id_sala, numero_posti, configurazione_posti) VALUES ('"+ s.getIdSala() +"', '"+ s.getNumeroPosti()+"', '"+ s.getConfigPosti()+"')");
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
            stmt = (PreparedStatement) connection.prepareStatement("UPDATE evo_cinema.sala SET numero_posti='"+ s.getNumeroPosti()+"', configurazione_posti='"+ s.getConfigPosti()+"' WHERE ( id_sala='"+ s.getIdSala()+ "');");
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
            stmt = (PreparedStatement) connection.prepareStatement("DELETE FROM evo_cinema.sala WHERE ( id_sala='"+ idSala +"');");
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
