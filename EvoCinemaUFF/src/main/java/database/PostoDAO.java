
package database;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
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
    private Connection connection;
    
    /*
     * Metodo costruttore della classe.
     * @throws SQLException
     * @throws NamingException
     */
    public PostoDAO() throws NamingException, SQLException {
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
     * Permette di estrarre le tuple di tipo {@link Posto} dal DB.
     * @return Lista dei Posti di tipo {@link Posto} presenti all'interno del DB.
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
    public synchronized List<Posto> getAllPosti() throws SQLException, ParseException, NamingException {
      
       PreparedStatement stmt=null;
       List<Posto> posti = new LinkedList<>();
      
       try {
           
            stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.Posto");

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
				if (stmt != null)
					stmt.close();
			}
		return posti;
   }
    
    /**
     * Metodo per la ricerca nel DB di un {@link Posto}.
     * @param riga identificativo della riga di tipo intero all'interno della {@link Sala}.
     * @param colonna identificativo della colonna di tipo intero all'interno della {@link Sala}.
     * @param sala identificativo della sala di tipo intero.
     * @return Oggetto di Posto [@link Posto} identificato dai parametri passati.
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
    public synchronized Posto foundByID(int riga, int colonna, int sala) throws SQLException, ParseException, NamingException{
       
       PreparedStatement stmt=null;
       Posto p = new Posto();
       
       try {
           
            stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.Posto WHERE riga='"+ riga +"' AND colonna='"+ colonna +"' AND idSala='"+ sala +"' ");

            ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
                   
                        
                        p.setRiga(rs.getInt("riga"));
                        p.setColonna(rs.getInt("colonna"));
                        
                        int idSala = rs.getInt("idSala");
                        Sala salaFound;
                        salaFound = salaDAO.foundByID(idSala);
                        p.setSala(salaFound);
                        
                        p.setStato(stato.valueOf(rs.getString("stato")));
                        
                    }
                    

		} finally {
				if (stmt != null)
					stmt.close();
			}
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
       PreparedStatement stmt=null;
       
       try {
           
            stmt = (PreparedStatement) connection.prepareStatement("INSERT INTO evo_cinema.Posto (riga, colonna, idSala, stato) VALUES ('"+ p.getRiga() +"', '"+ p.getColonna()+"', '"+ p.getSala().getIdSala() +"', '"+ p.getStato()+"')");
            stmt.executeUpdate();
            
            inserito = true;
            } finally {
			if (stmt != null)
                            stmt.close();
			}
		return inserito;
	}
    
    
    /**
     * Metodo per la modifica dei dati di un {@link Posto} nel DB.
     * @param daInserire Oggetto di tipo {@link Posto} che sar� inserito nel DB.
     * @param daModificare Oggetto di tipo {@link Posto} che sar� modificato nel DB.
     * @return 'true' per il corretto update o 'false' in caso di update fallito.
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
    public synchronized boolean updatePosto(Posto daInserire, Posto daModificare) throws SQLException, ParseException, NamingException{
       
       boolean modificato= false;
       PreparedStatement stmt=null;
       
       try {
           
            stmt = (PreparedStatement) connection.prepareStatement("UPDATE evo_cinema.posto SET Riga='"+ daInserire.getRiga() +"', colonna='"+ daInserire.getColonna()+"', idSala='"+ daInserire.getSala().getIdSala() +"', stato='"+ daInserire.getStato()+"' WHERE ( riga='"+ daModificare.getRiga() +"' AND colonna='"+ daModificare.getColonna()+"' AND idSala='"+ daModificare.getSala().getIdSala() +"');");
            stmt.executeUpdate();
            
            modificato = true;
            } finally {
			if (stmt != null)
                            stmt.close();
			}
	return modificato;
}
    
    /**
     * Metodo per la cancellazione di un {@link Posto} all'interno del DB.
     * @param p oggetto di tipo {@link Posto} che sar� cancellato dal DB.
     * @return 'true' per la corretta rimozione o 'false' in caso di rimozione fallita.
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
    public synchronized boolean deletePosto(Posto p) throws SQLException, ParseException, NamingException{
       
       boolean eliminato= false;
       PreparedStatement stmt=null;
       
       try {
           
            stmt = (PreparedStatement) connection.prepareStatement("DELETE FROM evo_cinema.Posto WHERE (riga='"+ p.getRiga() +"' AND colonna='"+ p.getColonna() +"' AND idSala='"+ p.getSala().getIdSala() +"');");
            stmt.executeUpdate();
            
            eliminato = true;
            } finally {
				if (stmt != null)
					stmt.close();
			}
	return eliminato;
    }
    
}