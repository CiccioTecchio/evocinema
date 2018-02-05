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
import java.sql.Date;
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
    
    /**
     * Usato prevalentemente per i JUNIT
     * @throws NamingException
     * @throws SQLException 
     */
    public OperazioneDAO(Connection conn) throws NamingException, SQLException {
        connection = conn;
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
			p.setPosto(rs.getInt("posto"));
                        p.setOffset(rs.getInt("offset"));
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
			a.setPosto(rs.getInt("posto"));
                        a.setOffset(rs.getInt("offset"));
                        
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
			p.setPosto(rs.getInt("posto"));
                        p.setOffset(rs.getInt("offset"));
                        p.setPrenotato(prenotato.valueOf(rs.getString("prenotato")));
                        p.setAcquistato(acquistato.valueOf(rs.getString("acquistato")));
                        p.setPrezzoFinale(rs.getFloat("prezzo_finale"));
                        Calendar data = Calendar.getInstance();
                        Date newDate = rs.getDate("data");
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
            stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.Operazione WHERE prenotato= 'TRUE' AND acquistato='FALSE' AND email= ? ");
            stmt.setString(1,  emailParam);
            ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
                   
                        Prenotazione p = new Prenotazione();
                        p.setIdOperazione(rs.getInt("id_Operazione"));
			p.setEmail(rs.getString("email"));
                        p.setIdSpettacolo(rs.getInt("idSpettacolo"));
			p.setPosto(rs.getInt("posto"));
                        p.setOffset(rs.getInt("offset"));
                        p.setPrenotato(prenotato.valueOf(rs.getString("prenotato")));
                        p.setAcquistato(acquistato.valueOf(rs.getString("acquistato")));
                        p.setPrezzoFinale(rs.getFloat("prezzo_finale"));
                        Calendar data = Calendar.getInstance();
                        Date newDate = rs.getDate("data");
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
			a.setPosto(rs.getInt("posto"));
                        a.setOffset(rs.getInt("offset"));
                        
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
     * Permette di estrarre le tuple di tipo {@link Acquisto} dal DB di ogni utnte.
     * @return Lista di oggetti di tipo {@link Prenotazione} presenti all'interno del DB.
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
   public List<Acquisto> getAcquistiUtente(String emailParam) throws SQLException, ParseException, NamingException {
      
       PreparedStatement stmt=null;
       List<Acquisto> acquisti = new LinkedList<>();
       try {
            stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.Operazione WHERE acquistato='TRUE' AND email= '"+emailParam+"'");

            ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
                   
                        Acquisto p = new Acquisto();
                        p.setIdOperazione(rs.getInt("id_Operazione"));
			p.setEmail(rs.getString("email"));
                        p.setIdSpettacolo(rs.getInt("idSpettacolo"));
			p.setPosto(rs.getInt("posto"));
                        p.setOffset(rs.getInt("offset"));
                        p.setPrenotato(prenotato.valueOf(rs.getString("prenotato")));
                        p.setAcquistato(acquistato.valueOf(rs.getString("acquistato")));
                        p.setPrezzoFinale(rs.getFloat("prezzo_finale"));
                        Calendar data = Calendar.getInstance();
                        data.setTime(rs.getDate("data"));
                        p.setData(data);
                        int idSala = rs.getInt("idSala");
                        int idSconto = rs.getInt("sconto_applicato");
                        Sala sala = salaDAO.foundByID(idSala);
                        p.setSala(sala);
                        Sconto sconto = scontoDAO.foundByID(idSconto);
                        p.setSconto(sconto);
                        
                        acquisti.add(p);
                    }
            } catch(Exception e){
                e.printStackTrace();
            } finally {
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
           
            stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.Operazione WHERE id_Operazione= ? ");
            stmt.setInt(1, idOperazione );
            ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
                    
                    prenotato x = prenotato.valueOf(rs.getString("prenotato"));
                    acquistato y = acquistato.valueOf(rs.getString("acquistato"));
                    
                    if((x.name().equals("TRUE"))&&(y.name().equals("FALSE"))){
                        
                        p.setIdOperazione(rs.getInt("id_Operazione"));
                        p.setEmail(rs.getString("email"));
                        p.setIdSpettacolo(rs.getInt("idSpettacolo"));
			p.setPosto(rs.getInt("posto"));
                        p.setOffset(rs.getInt("offset"));
                        
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
			a.setPosto(rs.getInt("posto"));
                        a.setOffset(rs.getInt("offset"));
                        
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
           
            stmt = (PreparedStatement) connection.prepareStatement("INSERT INTO evo_cinema.Operazione (id_Operazione, email, idSpettacolo, posto, offset, idSala, prenotato, acquistato, prezzo_finale, data, sconto_applicato) "
                                                            + "VALUES ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?  )");
            stmt.setInt(1, p.getIdOperazione());
            stmt.setString(2, p.getEmail());
            stmt.setInt(3, p.getIdSpettacolo());
            stmt.setInt(4, p.getPosto());
            stmt.setInt(5, p.getOffset());
            stmt.setInt(6, p.getSala().getIdSala());
            stmt.setString(7, p.getPrenotato().toString());
            stmt.setString(8, p.getAcquistato().toString());
            stmt.setFloat(9, p.getPrezzoFinale());
            Date date = new Date(p.getData().getTimeInMillis());
            stmt.setDate( 10, date );
            stmt.setInt(11, p.getSconto().getIdSconto());
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
           
            stmt = (PreparedStatement) connection.prepareStatement("UPDATE evo_cinema.Operazione SET email= ? , idSpettacolo= ? , posto= ?"
                                    + ", offset= ? , idSala= ? , prenotato= ?, acquistato= ? , prezzo_finale= ?, data= ? , sconto_applicato= ? "
                                    + "WHERE id_Operazione= ? ;");
            stmt.setString(1, p.getEmail()  );
            stmt.setInt(2, p.getIdSpettacolo());
            stmt.setInt(3, p.getPosto());
            stmt.setInt(4, p.getOffset());
            stmt.setInt(5, p.getSala().getIdSala());
            stmt.setString(6, p.getPrenotato().toString());
            stmt.setString(7, p.getAcquistato().toString());
            stmt.setFloat(8, p.getPrezzoFinale());
            Date date = new Date(p.getData().getTimeInMillis());
            stmt.setDate( 9, date );
            stmt.setInt(10, p.getSconto().getIdSconto());
            stmt.setInt( 11, p.getIdOperazione());
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
           
            stmt = (PreparedStatement) connection.prepareStatement("DELETE FROM evo_cinema.Operazione WHERE (id_Operazione= ? );");
            stmt.setInt(1,  idOperazione);
            stmt.executeUpdate();
            
            eliminato = true;
            } finally {
				if (stmt != null)
					stmt.close();
			
		}
		return eliminato;
	}



/**
     * Permette di estrarre le tuple di tipo {@link Operazione} con data compresa tra mese corrente ed il mese corrente 
     * dell'anno prima
     * @param scelta che rappresenta i dati da visualizzare acquisti, prenotazioni, operazioni(entrambi)
     * @return stringa che rappresenta il numero delle operazioni fatte in un determinato periodo
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
   public synchronized String analyticsGetDatiOperazioni(String scelta) throws SQLException, ParseException, NamingException {
    PreparedStatement stmt=null;
       String datiAcquisti =  "",datiPrenotazioni="";
       Calendar dataCorrente = Calendar.getInstance();
       ResultSet rs=null;     
       try {
            if((scelta.equals("Operazioni"))||(scelta.equals("Acquisti"))){
            //calcolo acquisti
            stmt = (PreparedStatement) connection.prepareStatement("SELECT YEAR(Operazione.data) as anno, "
                    + " MONTH(Operazione.data) as mese, "
                    + " COUNT(Operazione.id_Operazione) as num FROM evo_cinema.Operazione where Operazione.data>? "
                    + " AND Operazione.acquistato='true' group by YEAR(Operazione.data), MONTH(Operazione.data);");
            stmt.setString(1, (dataCorrente.get(Calendar.YEAR)-1)+"-"+dataCorrente.get(Calendar.MONTH)+
                    "-"+dataCorrente.get(Calendar.DAY_OF_MONTH));
            rs = stmt.executeQuery();

		while (rs.next()) {
                    datiAcquisti=datiAcquisti+rs.getString("anno")+"_";
                    datiAcquisti=datiAcquisti+rs.getString("mese")+"_";
                    datiAcquisti=datiAcquisti+rs.getString("num")+"_";
                }
            }//end if
            
            datiPrenotazioni = "10000_";//DATO FITTIZIO PER SPEZZARE L'ARRAY
            
            if((scelta.equals("Operazioni"))||(scelta.equals("Prenotazioni"))){
            //calcolo prenotazioni    
            stmt = (PreparedStatement) connection.prepareStatement("SELECT YEAR(Operazione.data) as anno, "
                    + " MONTH(Operazione.data) as mese, "
                    + " COUNT(Operazione.id_Operazione) as num FROM evo_cinema.Operazione where Operazione.data>? "
                    + " AND Operazione.prenotato='true' group by YEAR(Operazione.data), MONTH(Operazione.data);");

            stmt.setString(1, (dataCorrente.get(Calendar.YEAR)-1)+"-"+dataCorrente.get(Calendar.MONTH)+
                    "-"+dataCorrente.get(Calendar.DAY_OF_MONTH));
             rs = stmt.executeQuery();

                while (rs.next()) {
                    datiPrenotazioni=datiPrenotazioni+rs.getString("anno")+"_";
                    datiPrenotazioni=datiPrenotazioni+rs.getString("mese")+"_";
                    datiPrenotazioni=datiPrenotazioni+rs.getString("num")+"_";
                }
            }//end if    
            
            } catch(Exception e){
                e.printStackTrace();
            } finally {
		if (stmt != null)
			stmt.close();
		}
       //System.out.println(datiAcquisti+datiPrenotazioni);
       return datiAcquisti+datiPrenotazioni;

    }
   
   
   
/**
     * Permette di estrarre il numero di biglietti venduti per ogni spettacolo in programmazione oppure no
     * dell'anno prima
     * @param minAffluenza che rappresenta il minimo numero di biglietti venduti per ogni film  
     * @return stringa  rappresenta numero di biglietti venduti per ogni spettacolo
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
   public synchronized String analyticsGetDatiAffluenzeSpettacolo(String minAffluenza) throws SQLException, ParseException, NamingException {
   
       PreparedStatement stmt=null;
       String datiAffluenze =  "";
       int maxAffluenza=0;
       int numeroFilm=0;
       try {
            stmt = (PreparedStatement) connection.prepareStatement("SELECT Spettacolo.titolo, "
                    + " COUNT(Operazione.id_Operazione) as num FROM evo_cinema.Spettacolo INNER JOIN "
                    + " evo_cinema.Operazione ON Spettacolo.idSpettacolo=Operazione.idSpettacolo "+
            " group by Spettacolo.titolo having num>?; ");
            stmt.setString(1,minAffluenza);
            ResultSet rs = stmt.executeQuery();
                
		while (rs.next()) {
                    datiAffluenze=datiAffluenze+rs.getString("titolo")+"_";
                    int numeroAffluenze=Integer.parseInt(rs.getString("num"));
                    datiAffluenze=datiAffluenze+numeroAffluenze+"_";
                    if(numeroAffluenze>maxAffluenza)
                        maxAffluenza=numeroAffluenze;
                    
                    numeroFilm++;
                }
                
            } catch(Exception e){
                e.printStackTrace();
            } finally {
		if (stmt != null)
			stmt.close();
		}
       //System.out.println(maxAffluenza+"_"+numeroFilm+"_"+datiAffluenze);
       return maxAffluenza+"_"+numeroFilm+"_"+datiAffluenze;
       
    }
   
      
/**
     * Permette di estrarre il guadagno mensile per ogni biglietto acquistato 
     * dell'anno prima
     * @param sceltaMinIncassi rappresenta il minimo incasso per ogni mese 
     * @return stringa che rappresenta data, mese e utile guadagnato
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
   public synchronized String analyticsGetDatiIncassi(String sceltaMinIncassi) throws SQLException, ParseException, NamingException {
      
    PreparedStatement stmt=null;
       String datiIncassi =  "";
      
       try {
            stmt = (PreparedStatement) connection.prepareStatement(
                    " SELECT YEAR(Operazione.data) as anno, MONTH(Operazione.data) as mese, "+ 
                    " SUM(Operazione.prezzo_finale) as utile "+
                    " FROM evo_cinema.Operazione where Operazione.data>'2017-02-02' "+
                    " AND Operazione.acquistato='true' group by YEAR(Operazione.data), MONTH(Operazione.data)"
                            + "having utile>?; "
                    );
            stmt.setString(1,sceltaMinIncassi);
            ResultSet rs = stmt.executeQuery();
                
		while (rs.next()) {
                    datiIncassi = datiIncassi+rs.getString("anno")+"_";
                    datiIncassi = datiIncassi+rs.getString("mese")+"_";
                    datiIncassi = datiIncassi+rs.getString("utile")+"_";
                }
                
            } catch(Exception e){
                e.printStackTrace();
            } finally {
		if (stmt != null)
			stmt.close();
		}
       //System.out.println(datiIncassi);
       return datiIncassi;
       
    }
   
   
}