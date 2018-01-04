
package Database;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.logging.Logger;
import javax.naming.NamingException;
import model.Posto;
import model.Posto.stato;
import model.Sala;

/**
 * Classe di accesso al DB per il prelievo dei dati di tipo {@link Posto}
 * @author micheledellipaoli
 */
public class PostoDAO {
    private static Logger logger= Logger.getLogger("global");
    private SalaDAO salaDAO = new SalaDAO();
    
    /**
     * Permette di estrarre le tuple di tipo {@link Posto} dal DB.
     * @return Lista dei Posti di tipo {@link Posto} presenti all'interno del DB.
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
    public synchronized Collection<Posto> getAllPosti() throws SQLException, ParseException, NamingException {
      
       Connection connection=null;
       PreparedStatement stmt=null;
       Collection<Posto> posti = new LinkedList<>();
       connection = (Connection) SingletonDBConnection.getInstance().getConnInst();
       
        
       
       try {
           
            stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.posto");

            ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
                   
                        Posto p = new Posto();
                        p.setRiga(rs.getInt("riga"));
                        p.setColonna(rs.getInt("colonna"));
                        
                        int idSala = rs.getInt("idSala");
                        Sala sala = salaDAO.foundByID(idSala);
                        p.setSala(sala);
                        
                        p.setStato(stato.valueOf(rs.getString("stato")));

                        posti.add(p);
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
       
       logger.info(posti+"");
       
		return posti;
   }
    
    /**
     * Metodo per la ricerca nel DB di un {@link Posto}.
     * @param riga identificativo della riga di tipo intero all'interno della {@link Sala}.
     * @param colonna identificativo della colonna di tipo intero all'interno della {@link Sala}.
     * @return Oggetto di Posto [@link Posto} identificato dai parametri passati.
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
    public synchronized Posto foundByID(int riga, int colonna) throws SQLException, ParseException, NamingException{
       
        
       Connection connection=null;
       PreparedStatement stmt=null;
       Posto p = new Posto();
       connection = (Connection) SingletonDBConnection.getInstance().getConnInst();
       
        
       
       try {
           
            stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.posto WHERE riga='"+ riga +"' AND colonna='"+ colonna +"' ");

            ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
                   
                        
                        p.setRiga(rs.getInt("riga"));
                        p.setColonna(rs.getInt("colonna"));
                        
                        int idSala = rs.getInt("idSala");
                        Sala sala;
                        sala = salaDAO.foundByID(idSala);
                        p.setSala(sala);
                        
                        p.setStato(stato.valueOf(rs.getString("stato")));
                        
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
       
       logger.info(p+"");
       
		return p;
   }
    
    /**
     * Metodo per l'inserimento di un nuovo {@link Posto} nel DB.
     * @param p Oggetto di Posto {@link Posto}.
     * @return 'true' per il corretto inserimento o 'false' in caso di inserimento fallito.
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
    public synchronized boolean createPosto(Posto p) throws SQLException, ParseException, NamingException{
        
       boolean inserito= false;
       Connection connection=null;
       PreparedStatement stmt=null;
       connection = (Connection) SingletonDBConnection.getInstance().getConnInst();
       
        
       
       try {
           
            stmt = (PreparedStatement) connection.prepareStatement("INSERT INTO evo_cinema.posto (riga, colonna, idSala, stato) VALUES ('"+ p.getRiga() +"', '"+ p.getColonna()+"', '"+ p.getSala().getIdSala() +"', '"+ p.getStato()+"')");
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
     * Metodo per la modifica dei dati di un {@link Posto} nel DB.
     * @param p Oggetto di tipo {@link Posto}.
     * @return 'true' per il corretto update o 'false' in caso di update fallito.
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
    public synchronized boolean updatePosto(Posto p) throws SQLException, ParseException, NamingException{
       
       boolean modificato= false;
       Connection connection=null;
       PreparedStatement stmt=null;
       connection = (Connection) SingletonDBConnection.getInstance().getConnInst();
       
        
       
       try {
           
            stmt = (PreparedStatement) connection.prepareStatement("UPDATE evo_cinema.posto SET riga='"+ p.getRiga() +"', colonna='"+ p.getColonna()+"', idSala='"+ p.getSala().getIdSala() +"', stato='"+ p.getStato()+"' WHERE ( riga=riga='"+ p.getRiga() +"' AND colonna='"+ p.getColonna()+"');");
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
     * Metodo per la cancellazione di un {@link Posto} all'interno del DB.
     * @param riga identificativo della riga di tipo intero all'interno della {@link Sala}.
     * @param colonna identificativo della colonna di tipo intero all'interno della {@link Sala}.
     * @return 'true' per la corretta rimozione o 'false' in caso di rimozione fallita.
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
    public synchronized boolean deletePosto(int riga, int colonna) throws SQLException, ParseException, NamingException{
       
       boolean eliminato= false;
       Connection connection=null;
       PreparedStatement stmt=null;
       connection = (Connection) SingletonDBConnection.getInstance().getConnInst();
       
        
       
       try {
           
            stmt = (PreparedStatement) connection.prepareStatement("DELETE FROM evo_cinema.posto WHERE (riga='"+ riga +"' AND colonna='"+ colonna +"');");
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
