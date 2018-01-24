package database;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedList;
import java.util.logging.Logger;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;
import model.Acquisto;
import model.Operazione;
import model.Operazione.acquistato;
import model.Operazione.prenotato;
import model.Prenotazione;
import model.Sala;
import model.Sconto;

/**
 * Classe di accesso al DB per il prelievo dei dati di tipo {@link Operazione}
 * @author micheledellipaoli
 */
public class OperazioneDAO {
   
   private static Logger logger= Logger.getLogger("global");
   private SalaDAO salaDAO = new SalaDAO();
   private ScontoDAO scontoDAO = new ScontoDAO();
   
   private Connection connection;
   private PreparedStatement stmt=null;
   private Collection<Prenotazione> prenotazioni = new LinkedList<>();
   
    /*
     * Metodo costruttore della classe.
     * @throws SQLException
     * @throws NamingException
     */
    public OperazioneDAO() throws NamingException, SQLException {
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
     * Permette di estrarre le tuple di tipo {@link Operazione} dal DB.
     * @return Lista delle Operazioni di tipo {@link Acquisto} e {@link Prenotazione} presenti all'interno del DB.
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
   public synchronized List<Operazione> getAllOperazioni() throws SQLException, ParseException, NamingException {
      
       PreparedStatement stmt=null;
       List<Operazione> operazioni = new LinkedList<>();
       boolean prenotazione=false;
       try {
           
            stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.Operazione;");
            ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
                    prenotato x = prenotato.valueOf(rs.getString("prenotato"));
                    acquistato y = acquistato.valueOf(rs.getString("acquistato"));
                    
                    if((x.name().equals("TRUE"))&&(y.name().equals("FALSE"))){
                        Prenotazione p = new Prenotazione();
                        p.setIdOperazione(rs.getInt("id_Operazione"));
                        p.setEmail(rs.getString("email"));
                        p.setIdSpettacolo(rs.getInt("idSpettacolo"));
			p.setPostoColonna(rs.getInt("posto_colonna"));
                        p.setPostoRiga(rs.getInt("posto_riga"));
                        p.setPrenotato(x);
                        p.setAcquistato(y);
                        p.setPrezzoFinale(rs.getFloat("prezzo_finale"));
                        Calendar data = Calendar.getInstance();
                        data.setTime(rs.getDate("data"));
                        p.setData(data);
                        int idSala = rs.getInt("idSala");
                        Sala sala = salaDAO.foundByID(idSala);
                        p.setSala(sala);
                        int idSconto = rs.getInt("sconto_applicato");
                        Sconto sconto = scontoDAO.foundByID(idSconto);
                        p.setSconto(sconto);
                        
                        operazioni.add(p);
                        
                    }
                    if (y.name().equals("TRUE")){
			Acquisto a = new Acquisto();
                        a.setIdOperazione(rs.getInt("id_Operazione"));
			a.setEmail(rs.getString("email"));
                        a.setIdSpettacolo(rs.getInt("idSpettacolo"));
			a.setPostoColonna(rs.getInt("posto_colonna"));
                        a.setPostoRiga(rs.getInt("posto_riga"));
                        
                        int idSala = rs.getInt("idSala");
                        Sala sala = salaDAO.foundByID(idSala);
                        a.setSala(sala);
                        
                        a.setPrenotato(x);
                        a.setAcquistato(y);
                        a.setPrezzoFinale(rs.getFloat("prezzo_finale"));
                        
                        Calendar data = Calendar.getInstance();
                        data.setTime(rs.getDate("data"));
                        a.setData(data);
                        
                        int idSconto = rs.getInt("sconto_applicato");
                        Sconto sconto = scontoDAO.foundByID(idSconto);
                        a.setSconto(sconto);
                        
                    operazioni.add(a);
                    
                    }			
	}
	} finally {
            if (stmt != null)
		stmt.close();	
	}
	return operazioni;
   }
   
   /**
     * Permette di estrarre le tuple di tipo {@link Prenotazione} dal DB.
     * @return Lista di oggetti di tipo {@link Prenotazione} presenti all'interno del DB.
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
   public List<Prenotazione> getPrenotazioni() throws SQLException, ParseException, NamingException {
      
       PreparedStatement stmt=null;
       List<Prenotazione> prenotazioni = new LinkedList<>();
       try {
            stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.Operazione WHERE prenotato= 'TRUE' AND acquistato='FALSE'");

            ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
                   
                        Prenotazione p = new Prenotazione();
                        p.setIdOperazione(rs.getInt("id_Operazione"));
			p.setEmail(rs.getString("email"));
                        p.setIdSpettacolo(rs.getInt("idSpettacolo"));
			p.setPostoColonna(rs.getInt("posto_colonna"));
                        p.setPostoRiga(rs.getInt("posto_riga"));
                        p.setPrenotato(prenotato.valueOf(rs.getString("prenotato")));
                        p.setAcquistato(acquistato.valueOf(rs.getString("acquistato")));
                        p.setPrezzoFinale(rs.getFloat("prezzo_finale"));
                        Calendar data = Calendar.getInstance();
                        Date newDate = rs.getTimestamp("data");
                        data.setTime(newDate);
                        p.setData(data);
                        int idSala = rs.getInt("idSala");
                        int idSconto = rs.getInt("sconto_applicato");
                        Sala sala = salaDAO.foundByID(idSala);
                        p.setSala(sala);
                        Sconto sconto = scontoDAO.foundByID(idSconto);
                        p.setSconto(sconto);
                        
                        prenotazioni.add(p);
                    }
            } catch(Exception e){
                e.printStackTrace();
            } finally {
		if (stmt != null)
			stmt.close();
		}
    return prenotazioni;
   }
   
      /**
     * Permette di estrarre le tuple di tipo {@link Prenotazione} dal DB di ogni utnte.
     * @return Lista di oggetti di tipo {@link Prenotazione} presenti all'interno del DB.
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
   public List<Prenotazione> getPrenotazioniUtente(String emailParam) throws SQLException, ParseException, NamingException {
      
       PreparedStatement stmt=null;
       List<Prenotazione> prenotazioni = new LinkedList<>();
       try {
            stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.Operazione WHERE prenotato= 'TRUE' AND acquistato='FALSE' AND email= '"+emailParam+"'");

            ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
                   
                        Prenotazione p = new Prenotazione();
                        p.setIdOperazione(rs.getInt("id_Operazione"));
			p.setEmail(rs.getString("email"));
                        p.setIdSpettacolo(rs.getInt("idSpettacolo"));
			p.setPostoColonna(rs.getInt("posto_colonna"));
                        p.setPostoRiga(rs.getInt("posto_riga"));
                        p.setPrenotato(prenotato.valueOf(rs.getString("prenotato")));
                        p.setAcquistato(acquistato.valueOf(rs.getString("acquistato")));
                        p.setPrezzoFinale(rs.getFloat("prezzo_finale"));
                        Calendar data = Calendar.getInstance();
                        Date newDate = rs.getTimestamp("data");
                        data.setTime(newDate);
                        p.setData(data);
                        int idSala = rs.getInt("idSala");
                        int idSconto = rs.getInt("sconto_applicato");
                        Sala sala = salaDAO.foundByID(idSala);
                        p.setSala(sala);
                        Sconto sconto = scontoDAO.foundByID(idSconto);
                        p.setSconto(sconto);
                        
                        prenotazioni.add(p);
                    }
            } catch(Exception e){
                e.printStackTrace();
            } finally {
		if (stmt != null)
			stmt.close();
		}
    return prenotazioni;
   }
   
   /**
     * Permette di estrarre le tuple di tipo {@link Acquisto} dal DB.
     * @return Lista di oggetti di tipo {@link Acquisto} presenti all'interno del DB.
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
   public synchronized List<Acquisto> getAcquisti() throws SQLException, ParseException, NamingException {
      
       PreparedStatement stmt=null;
       List<Acquisto> acquisti = new LinkedList<>();
       
       try {
            stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.Operazione WHERE acquistato= 'TRUE'");
            ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
                        Acquisto a = new Acquisto();
                        a.setIdOperazione(rs.getInt("id_Operazione"));
			a.setIdOperazione(rs.getInt("id_Operazione"));
			a.setEmail(rs.getString("email"));
                        a.setIdSpettacolo(rs.getInt("idSpettacolo"));
			a.setPostoColonna(rs.getInt("posto_colonna"));
                        a.setPostoRiga(rs.getInt("posto_riga"));
                        
                        int idSala = rs.getInt("idSala");
                        Sala sala = salaDAO.foundByID(idSala);
                        a.setSala(sala);
                        
                        a.setPrenotato(prenotato.valueOf(rs.getString("prenotato")));
                        a.setAcquistato(acquistato.valueOf(rs.getString("acquistato")));
                        a.setPrezzoFinale(rs.getFloat("prezzo_finale"));
                        
                        Calendar data = Calendar.getInstance();
                        data.setTime(rs.getDate("data"));
                        a.setData(data);
                        
                        int idSconto = rs.getInt("sconto_applicato");
                        Sconto sconto = scontoDAO.foundByID(idSconto);
                        a.setSconto(sconto);
                        
                        acquisti.add(a);
                    }  
		} finally{
			if (stmt != null)
                            stmt.close();
                    }
	return acquisti;
   }
   
   /**
     * Metodo per la ricerca nel DB di una {@link Operazione}.
     * @param idOperazione identificativo dell' {@link Operazione} di tipo intero.
     * @return Oggetto di tipo [@link Operazione} che ha come id il parametro passato.
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
   public synchronized Operazione foundByID(int idOperazione) throws SQLException, ParseException, NamingException{
       
        
       PreparedStatement stmt=null;
       boolean prenotazione=false;
       Prenotazione p = new Prenotazione();
       Acquisto a = new Acquisto();
       Operazione o = new Operazione(){};
       
       try {
           
            stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.Operazione WHERE id_Operazione='"+ idOperazione +"'");

            ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
                    
                    prenotato x = prenotato.valueOf(rs.getString("prenotato"));
                    acquistato y = acquistato.valueOf(rs.getString("acquistato"));
                    
                    if((x.name().equals("TRUE"))&&(y.name().equals("FALSE"))){
                        
                        p.setIdOperazione(rs.getInt("id_Operazione"));
                        p.setEmail(rs.getString("email"));
                        p.setIdSpettacolo(rs.getInt("idSpettacolo"));
			p.setPostoColonna(rs.getInt("posto_colonna"));
                        p.setPostoRiga(rs.getInt("posto_riga"));
                        
                        int idSala = rs.getInt("idSala");
                        Sala sala = salaDAO.foundByID(idSala);
                        p.setSala(sala);
                        
                        p.setPrenotato(prenotato.valueOf(rs.getString("prenotato")));
                        p.setAcquistato(acquistato.valueOf(rs.getString("acquistato")));
                        p.setPrezzoFinale(rs.getFloat("prezzo_finale"));
                        
                        Calendar data = Calendar.getInstance();
                        data.setTime(rs.getDate("data"));
                        p.setData(data);
                        
                        int idSconto = rs.getInt("sconto_applicato");
                        Sconto sconto = scontoDAO.foundByID(idSconto);
                        p.setSconto(sconto);
                        
                        prenotazione=true;
                    }
                    if (y.name().equals("TRUE")){
			
                        a.setIdOperazione(rs.getInt("id_Operazione"));
			a.setEmail(rs.getString("email"));
                        a.setIdSpettacolo(rs.getInt("idSpettacolo"));
			a.setPostoColonna(rs.getInt("posto_colonna"));
                        a.setPostoRiga(rs.getInt("posto_riga"));
                        
                        int idSala = rs.getInt("idSala");
                        Sala sala = salaDAO.foundByID(idSala);
                        a.setSala(sala);
                        
                        a.setPrenotato(prenotato.valueOf(rs.getString("prenotato")));
                        a.setAcquistato(acquistato.valueOf(rs.getString("acquistato")));
                        a.setPrezzoFinale(rs.getFloat("prezzo_finale"));
                        
                        Calendar data = Calendar.getInstance();
                        data.setTime(rs.getDate("data"));
                        a.setData(data);
                        
                        int idSconto = rs.getInt("sconto_applicato");
                        Sconto sconto = scontoDAO.foundByID(idSconto);
                        a.setSconto(sconto);
                        
                    }
                    

		}} finally {
				if (stmt != null)
					stmt.close();
			
		}
       if(prenotazione==true){
            return p;
       }
       else{
            return a;
    }
   }
   
   /**
     * Metodo per l'inserimento di una nuova {@link Operazione} nel DB
     * @param p Oggetto di Operazione {@link Operazione}
     * @return 'true' per il corretto inserimento o 'false' in caso di inserimento fallito.
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
   public synchronized boolean createOperazione(Operazione p) throws SQLException, ParseException, NamingException{
        
       boolean inserito= false;
       PreparedStatement stmt=null;       
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       
       try {
           
            stmt = (PreparedStatement) connection.prepareStatement("INSERT INTO evo_cinema.Operazione (id_Operazione, email, idSpettacolo, posto_colonna, posto_riga, idSala, prenotato, acquistato, prezzo_finale, data, sconto_applicato) VALUES ('"+ p.getIdOperazione() +"', '"+ p.getEmail()+"', '"+ p.getIdSpettacolo() +"', '"+ p.getPostoColonna()+"', '"+p.getPostoRiga()+"', '"+p.getSala().getIdSala()+"', '"+p.getPrenotato()+"', '"+p.getAcquistato()+"', '"+p.getPrezzoFinale()+"', '"+sdf.format(p.getData().getTime())+"', '"+p.getSconto().getIdSconto()+"')");
            stmt.executeUpdate();
            
            inserito = true;
            } finally {
				if (stmt != null)
					stmt.close();
			
		}
		return inserito;
	}
   
   /**
     * Metodo per la modifica dei dati di una {@link Operazione} nel DB.
     * @param p Oggetto di tipo {@link Operazione}
     * @return 'true' per il corretto update o 'false' in caso di update fallito.
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
   public synchronized boolean updateOperazione(Operazione p) throws SQLException, ParseException, NamingException{
       
       boolean modificato= false;
       PreparedStatement stmt=null;       
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       
       try {
           
            stmt = (PreparedStatement) connection.prepareStatement("UPDATE evo_cinema.Operazione SET email='"+ p.getEmail()+"', idSpettacolo='"+ p.getIdSpettacolo() +"', posto_colonna='"+ p.getPostoColonna()+"', posto_riga='"+ p.getPostoRiga()+"', idSala='"+ p.getSala().getIdSala()+"', prenotato='"+ p.getPrenotato()+"', acquistato='"+ p.getAcquistato() +"', prezzo_finale='"+ p.getPrezzoFinale()+"', data='"+ sdf.format(p.getData().getTime())+"', sconto_applicato='"+ p.getSconto().getIdSconto()+"' WHERE id_Operazione='"+p.getIdOperazione()+"';");
            stmt.executeUpdate();
            
            modificato = true;
            } finally {
				if (stmt != null)
					stmt.close();
			}
		return modificato;
	}
   
   /**
     * Metodo per la cancellazione di una {@link Operazione} all'interno del DB
     * @param idOperazione identificativo di tipo intero dell' {@link Operazione}
     * @return 'true' per la corretta rimozione o 'false' in caso di rimozione fallita.
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
   public synchronized boolean deleteOperazione(int idOperazione) throws SQLException, ParseException, NamingException{
       
       boolean eliminato= false;
       PreparedStatement stmt=null;       
        
       
       try {
           
            stmt = (PreparedStatement) connection.prepareStatement("DELETE FROM evo_cinema.Operazione WHERE (id_Operazione='"+ idOperazione +"');");
            stmt.executeUpdate();
            
            eliminato = true;
            } finally {
				if (stmt != null)
					stmt.close();
			
		}
		return eliminato;
	}
}
   

