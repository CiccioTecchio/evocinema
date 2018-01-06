
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.logging.Logger;
import javax.naming.NamingException;
import model.Sconto;
import model.Sconto.disponibile;
import model.Sconto.tipo;
import model.Sconto.tipologia;
import model.Sconto.verificabile;

/**
 * Classe di accesso al DB per il prelievo dei dati di tipo {@link Sconto}
 * @author sarad
 */
public class ScontoDAO {
    private static Logger logger= Logger.getLogger("global");
    
    /**
     * Metodo per la ricerca degli Sconti presenti nel DB.
     * @return Lista di oggetti di tipo {@link Sconto} presenti nel DB.
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
    public synchronized Collection<Sconto> getAllSconti() throws SQLException, ParseException, NamingException {
      
       Connection connection=null;
       PreparedStatement stmt=null;
       Collection<Sconto> sconti = new LinkedList<Sconto>();
       connection = (Connection) SingletonDBConnection.getInstance().getConnInst();
       
       try {
            stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.sconto");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                Sconto s = new Sconto();
                s.setIdSconto(rs.getInt("idSconto"));
                s.setNome(rs.getString("nome"));
                s.setTipo(tipo.valueOf(rs.getString("tipo")));
                s.setPercentuale(rs.getInt("percentuale"));
                s.setPrezzo(rs.getFloat("prezzo"));
                s.setVerificabile(verificabile.valueOf(rs.getString("verificabile")));
                s.setDisponibile(disponibile.valueOf(rs.getString("disponibile")));
                s.setTipologia(tipologia.valueOf(rs.getString("tipologia")));
                s.setParametroTipologia(rs.getString("parametro_tipologia"));
                sconti.add(s);
            }		
        }finally {
		try {
                    if (stmt != null)
			stmt.close();
		} finally {
			if (connection != null)
                            connection.close();
                  }
	}
       logger.info(sconti+"");
       
    return sconti;
   }
    
    /**
     * Metodo per la ricerca nel DB dello {@link Sconto} avente l'ID specificato nei parametri.
     * @param idSconto Identificativo dello {@link Sconto}
     * @return Un oggetto di tipo {@link Sconto}
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
   public synchronized Sconto foundByID(int idSconto) throws SQLException, ParseException, NamingException {
      
       Connection connection=null;
       PreparedStatement stmt=null;
       Sconto scontoFound = new Sconto();
       connection = (Connection) SingletonDBConnection.getInstance().getConnInst();
       
       try {
           stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.sconto WHERE idSconto='"+idSconto+"'");
           ResultSet rs = stmt.executeQuery();
           scontoFound.setIdSconto(rs.getInt("idSconto"));
           scontoFound.setNome(rs.getString("nome"));
           scontoFound.setTipo(tipo.valueOf(rs.getString("tipo")));
           scontoFound.setPercentuale(rs.getInt("percentuale"));
           scontoFound.setPrezzo(rs.getFloat("prezzo"));
           scontoFound.setVerificabile(verificabile.valueOf(rs.getString("verificabile")));
           scontoFound.setDisponibile(disponibile.valueOf(rs.getString("disponibile")));
           scontoFound.setTipologia(tipologia.valueOf(rs.getString("tipologia")));
           scontoFound.setParametroTipologia(rs.getString("parametro_tipologia"));
           } finally{
                try {
                    if (stmt != null)
                        stmt.close();
                    } finally {
                        if (connection != null)
                            connection.close();
                       }
           }
    return scontoFound;
    }
    
   /**
    * Metodo per la ricerca all'interno del DB di oggetti di tipo {@link Sconto} disponibili.
    * @return Lista di oggetti di tipo {@link Sconto}
    * @throws SQLException
    * @throws ParseException
    * @throws NamingException 
    */
   public synchronized Collection<Sconto> foundByDisponibilità() throws SQLException, ParseException, NamingException {
      
       Connection connection=null;
       PreparedStatement stmt=null;
       Collection<Sconto> sconti = new LinkedList<Sconto>();
       connection = (Connection) SingletonDBConnection.getInstance().getConnInst();
       
       try {
            stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.sconto WHERE disponibile='" + true +"'");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                Sconto s = new Sconto();
                s.setIdSconto(rs.getInt("idSconto"));
                s.setNome(rs.getString("nome"));
                s.setTipo(tipo.valueOf(rs.getString("tipo")));
                s.setPercentuale(rs.getInt("percentuale"));
                s.setPrezzo(rs.getFloat("prezzo"));
                s.setVerificabile(verificabile.valueOf(rs.getString("verificabile")));
                s.setDisponibile(disponibile.valueOf(rs.getString("disponibile")));
                s.setTipologia(tipologia.valueOf(rs.getString("tipologia")));
                s.setParametroTipologia(rs.getString("parametro_tipologia"));
                sconti.add(s);
            }		
        }finally {
		try {
                    if (stmt != null)
			stmt.close();
		} finally {
			if (connection != null)
                            connection.close();
                  }
	}
       logger.info(sconti+"");
    return sconti;
    }
   
   /**
    * Metodo per la ricerca all'interno del DB di oggetti di tipo {@link Sconto} del tipo specificato come parametro.
    * @param tipoSelezionato Valore dell'enum {@link Sconto.tipo}
    * @return Lista di oggetti di tipo {@link Sconto}
    * @throws SQLException
    * @throws ParseException
    * @throws NamingException 
    */
   public synchronized Collection<Sconto> foundByTipo(String tipoSelezionato) throws SQLException, ParseException, NamingException {
       
       Connection connection=null;
       PreparedStatement stmt=null;
       Collection<Sconto> sconti = new LinkedList<Sconto>();
       connection = (Connection) SingletonDBConnection.getInstance().getConnInst();
       
       try {
            stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.sconto WHERE tipo='" + tipoSelezionato +"'");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                Sconto s = new Sconto();
                s.setIdSconto(rs.getInt("idSconto"));
                s.setNome(rs.getString("nome"));
                s.setTipo(tipo.valueOf(rs.getString("tipo")));
                s.setPercentuale(rs.getInt("percentuale"));
                s.setPrezzo(rs.getFloat("prezzo"));
                s.setVerificabile(verificabile.valueOf(rs.getString("verificabile")));
                s.setDisponibile(disponibile.valueOf(rs.getString("disponibile")));
                s.setTipologia(tipologia.valueOf(rs.getString("tipologia")));
                s.setParametroTipologia(rs.getString("parametro_tipologia"));
                sconti.add(s);
            }		
        }finally {
		try {
                    if (stmt != null)
			stmt.close();
		} finally {
			if (connection != null)
                            connection.close();
                }
	}
       logger.info(sconti+"");
    return sconti;
    }
   
   /**
    * Metodo per la ricerca all'interno del DB di oggetti di tipo {@link Sconto} della tipologia specificata come parametro.
    * @param tipologiaSelezionata Valore dell'enum {@link Sconto.tipologia}
    * @return Lista di oggetti di tipo {@link Sconto}
    * @throws SQLException
    * @throws ParseException
    * @throws NamingException 
    */
   public synchronized Collection<Sconto> foundByTipologia(String tipologiaSelezionata) throws SQLException, ParseException, NamingException {
       
       Connection connection=null;
       PreparedStatement stmt=null;
       Collection<Sconto> sconti = new LinkedList<Sconto>();
       connection = (Connection) SingletonDBConnection.getInstance().getConnInst();
       
       try {
            stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.sconto WHERE tipologia='" + tipologiaSelezionata +"'");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                Sconto s = new Sconto();
                s.setIdSconto(rs.getInt("idSconto"));
                s.setNome(rs.getString("nome"));
                s.setTipo(tipo.valueOf(rs.getString("tipo")));
                s.setPercentuale(rs.getInt("percentuale"));
                s.setPrezzo(rs.getFloat("prezzo"));
                s.setVerificabile(verificabile.valueOf(rs.getString("verificabile")));
                s.setDisponibile(disponibile.valueOf(rs.getString("disponibile")));
                s.setTipologia(tipologia.valueOf(rs.getString("tipologia")));
                s.setParametroTipologia(rs.getString("parametro_tipologia"));
                sconti.add(s);
            }		
        }finally {
		try {
                    if (stmt != null)
			stmt.close();
		} finally {
			if (connection != null)
                            connection.close();
                }
	}
       logger.info(sconti+"");
    return sconti;
    }
   
   /**
    * Metodo per la modifica di uno {@link Sconto} nel DB
    * @param s Oggetto di tipo {@link Sconto}
    * @return 'true' in caso di successo o 'false' in caso di fallimento
    * @throws SQLException
    * @throws ParseException
    * @throws NamingException 
    */
   public synchronized boolean updateSconto(Sconto s) throws SQLException, ParseException, NamingException{
        
       Connection connection=null;
       PreparedStatement stmt=null;
       boolean update= false;
       connection = (Connection) SingletonDBConnection.getInstance().getConnInst();
       
       try {
            stmt = (PreparedStatement) connection.prepareStatement("UPDATE evo_cinema.sconto SET nome='"+ s.getNome()+"', tipo='"+ s.getTipo()+"', percentuale='"+s.getPercentuale()+"', prezzo='"+s.getPrezzo()+"', verificabile='"+s.getVerificabile()+"', disponibile='"+s.getDisponibile()+"', tipologia='"+s.getTipologia()+"', parametro_tipologia='"+s.getParametroTipologia()+"' WHERE ( idSconto='"+ s.getIdSconto()+ "');");
            stmt.executeUpdate();
            update = true;
        } 
        finally {
            try {
                if (stmt != null)
                    stmt.close();
            } 
            finally {
                if (connection != null)
                    connection.close();
            }
        }
    return update;
    }
   
   /**
    * Metodo per l'inserimento di uno {@link Sconto} nel DB.
    * @param s Oggetto di tipo {@link Sconto}
    * @return 'true' in caso di successo o 'false' in caso di fallimento
    * @throws SQLException
    * @throws ParseException
    * @throws NamingException 
    */
   public synchronized boolean createSconto(Sconto s) throws SQLException, ParseException, NamingException{
        
       Connection connection=null;
       PreparedStatement stmt=null;
       boolean inserita= false;
       connection = (Connection) SingletonDBConnection.getInstance().getConnInst();
       
       try {
            stmt = (PreparedStatement) connection.prepareStatement("INSERT INTO evo_cinema.sconto (idSconto,nome,tipo,percentuale, prezzo, verificabile,disponibile,tipologia,parametro_tipologia)VALUES ('"+ s.getIdSconto() +"', '"+ s.getNome()+"', '"+s.getTipo()+"', '"+s.getPercentuale()+"', '"+s.getPrezzo()+"', '"+s.getVerificabile()+"', '"+s.getDisponibile()+"', '"+ s.getTipologia()+"', '"+ s.getParametroTipologia()+"')");
            stmt.executeUpdate();
            inserita = true;
        } 
        finally {
            try {
                if (stmt != null)
                    stmt.close();
            } 
            finally {
                if (connection != null)
                    connection.close();
            }
        }
    return inserita;
    }
   
   /**
    * Metodo per la cancellazione di uno {@link Sconto} all'interno del DB.
    * @param idSconto identificativo dello {@link Sconto}
    * @return 'true' in caso di successo o 'false' in caso di fallimento
    * @throws SQLException
    * @throws ParseException
    * @throws NamingException 
    */
   public synchronized boolean deleteSconto(int idSconto) throws SQLException, ParseException, NamingException{
        
       Connection connection=null;
       PreparedStatement stmt=null;
       boolean delete= false;
       connection = (Connection) SingletonDBConnection.getInstance().getConnInst();
       
       try {
            stmt = (PreparedStatement) connection.prepareStatement("DELETE FROM evo_cinema.sconto WHERE ( idSconto='"+ idSconto +"');");
            stmt.executeUpdate();
            delete = true;
        } 
        finally {
            try {
                if (stmt != null)
                    stmt.close();
            } 
            finally {
                if (connection != null)
                    connection.close();
                }
            }
    return delete;
    }
   
}
