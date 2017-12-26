package Database;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedList;
import java.util.logging.Logger;
import javax.naming.NamingException;
import model.Acquisto;
import model.Operazione;
import model.Operazione.acquistato;
import model.Operazione.prenotato;
import model.Prenotazione;
import model.Sala;
import model.Sconto;


public class OperazioneDAO {
   
   private static Logger logger= Logger.getLogger("global");
    
   public synchronized Collection<Operazione> getAllOperazioni() throws SQLException, ParseException, NamingException {
      
       Connection connection=null;
       PreparedStatement stmt=null;
       Collection<Operazione> operazioni = new LinkedList<>();
       connection = (Connection) SingletonDBConnection.getInstance().getConnInst();
       
       try {
           
            stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.operazione");

            ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
                    prenotato x = prenotato.valueOf(rs.getString("prenotato"));
                    acquistato y = acquistato.valueOf(rs.getString("acquistato"));
                    
                    if((x.equals(true))){
                        Prenotazione p = new Prenotazione();
                        p.setIdOperazione(rs.getInt("id_Operazione"));
                        p.setEmail(rs.getString("email"));
                        p.setIdSpettacolo(rs.getInt("idSpettacolo"));
			p.setPostoColonna(rs.getInt("posto_colonna"));
                        p.setPostoRiga(rs.getInt("posto_riga"));
                        
                        int idSala = rs.getInt("idSala");
                        Sala sala = SalaDAO.foundByID(idSala);
                        p.setSala(sala);
                        
                        p.setPrenotato(prenotato.valueOf(rs.getString("prenotato")));
                        p.setAcquistato(acquistato.valueOf(rs.getString("acquistato")));
                        p.setPrezzoFinale(rs.getFloat("prezzo_finale"));
                        
                        Calendar data = Calendar.getInstance();
                        data.setTime(rs.getDate("data"));
                        p.setData(data);
                        
                        String idSconto = rs.getString("sconto_applicato");
                        Sconto sconto = ScontoDAO.foundByID(idSconto);
                        p.setSconto(sconto);
                        
                        operazioni.add(p);
                    }
                    if (y.equals(true)){
			Acquisto a = new Acquisto();
                        a.setIdOperazione(rs.getInt("id_Operazione"));
			a.setEmail(rs.getString("email"));
                        a.setIdSpettacolo(rs.getInt("idSpettacolo"));
			a.setPostoColonna(rs.getInt("posto_colonna"));
                        a.setPostoRiga(rs.getInt("posto_riga"));
                        
                        int idSala = rs.getInt("idSala");
                        Sala sala = SalaDAO.foundByID(idSala);
                        a.setSala(sala);
                        
                        a.setPrenotato(prenotato.valueOf(rs.getString("prenotato")));
                        a.setAcquistato(acquistato.valueOf(rs.getString("acquistato")));
                        a.setPrezzoFinale(rs.getFloat("prezzo_finale"));
                        
                        Calendar data = Calendar.getInstance();
                        data.setTime(rs.getDate("data"));
                        a.setData(data);
                        
                        String idSconto = rs.getString("sconto_applicato");
                        Sconto sconto = ScontoDAO.foundByID(idSconto);
                        a.setSconto(sconto);
                        
                    operazioni.add(a);
                    }
				
                                
				
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
       
       logger.info(operazioni+"");
       
		return operazioni;
   }
   
   public synchronized Collection<Prenotazione> getPrenotazioni() throws SQLException, ParseException, NamingException {
      
       Connection connection=null;
       PreparedStatement stmt=null;
       Collection<Prenotazione> prenotazioni = new LinkedList<>();
       connection = (Connection) SingletonDBConnection.getInstance().getConnInst();
       
       try {
           
            stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.operazione WHERE prenotato= '" + true +"'");

            ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
                   
                        Prenotazione p = new Prenotazione();
                        p.setIdOperazione(rs.getInt("id_Operazione"));
			p.setEmail(rs.getString("email"));
                        p.setIdSpettacolo(rs.getInt("idSpettacolo"));
			p.setPostoColonna(rs.getInt("posto_colonna"));
                        p.setPostoRiga(rs.getInt("posto_riga"));
                        
                        int idSala = rs.getInt("idSala");
                        Sala sala = SalaDAO.foundByID(idSala);
                        p.setSala(sala);
                        
                        p.setPrenotato(prenotato.valueOf(rs.getString("prenotato")));
                        p.setAcquistato(acquistato.valueOf(rs.getString("acquistato")));
                        p.setPrezzoFinale(rs.getFloat("prezzo_finale"));
                        
                        Calendar data = Calendar.getInstance();
                        data.setTime(rs.getDate("data"));
                        p.setData(data);
                        
                        String idSconto = rs.getString("sconto_applicato");
                        Sconto sconto = ScontoDAO.foundByID(idSconto);
                        p.setSconto(sconto);	
                        
                        prenotazioni.add(p);
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
       
       logger.info(prenotazioni+"");
       
		return prenotazioni;
   }
   
   public synchronized Collection<Acquisto> getAcquisti() throws SQLException, ParseException, NamingException {
      
       Connection connection=null;
       PreparedStatement stmt=null;
       Collection<Acquisto> acquisti = new LinkedList<>();
       connection = (Connection) SingletonDBConnection.getInstance().getConnInst();
       
       try {
           
            stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.operazione WHERE acquistato= '" + true +"'");

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
                        Sala sala = SalaDAO.foundByID(idSala);
                        a.setSala(sala);
                        
                        a.setPrenotato(prenotato.valueOf(rs.getString("prenotato")));
                        a.setAcquistato(acquistato.valueOf(rs.getString("acquistato")));
                        a.setPrezzoFinale(rs.getFloat("prezzo_finale"));
                        
                        Calendar data = Calendar.getInstance();
                        data.setTime(rs.getDate("data"));
                        a.setData(data);
                        
                        String idSconto = rs.getString("sconto_applicato");
                        Sconto sconto = ScontoDAO.foundByID(idSconto);
                        a.setSconto(sconto);
                        
                        acquisti.add(a);
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
       
       logger.info(acquisti+"");
       
		return acquisti;
   }
}

