
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
import model.Sconto;
import model.Sconto.disponibile;
import static model.Sconto.disponibile.TRUE;
import model.Sconto.tipo;
import model.Sconto.tipologia;
import model.Sconto.verificabile;

/**
 * Classe di accesso al DB per il prelievo dei dati di tipo {@link Sconto}
 * @author sarad
 */
public class ScontoDAO {
    private static Logger logger= Logger.getLogger("global");
    private Connection connection;
     
    /**
     * Costruttore della classe {@link ScontoDAO} che istanzia una connessione.
     * @throws NamingException
     * @throws SQLException 
     */
    public ScontoDAO() throws NamingException, SQLException {
        connection=(Connection) SingletonDBConnection.getInstance().getConnInst();
    }
   
    /**
     * Metodo per il prelievo della connessione attuale.
     * @return la connessione attuale.
     */
    public Connection getDAOConnection(){
        return this.connection;
    }
    
    /**
     * Metodo per la ricerca degli Sconti presenti nel DB.
     * @return Lista di oggetti di tipo {@link Sconto} presenti nel DB.
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
    public synchronized List<Sconto> getAllSconti() throws SQLException, ParseException, NamingException {
      
       PreparedStatement stmt=null;
       List<Sconto> sconti = new LinkedList<>();
       
       try {
            stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.Sconto");
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
                    if (stmt != null)
			stmt.close();
		}
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
      
       PreparedStatement stmt=null;
       Sconto scontoFound = new Sconto();
       
       try {
           stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.Sconto WHERE idSconto= ? ");
           stmt.setInt(1, idSconto);
           ResultSet rs = stmt.executeQuery();
           
           while (rs.next()) {
           scontoFound.setIdSconto(rs.getInt("idSconto"));
           scontoFound.setNome(rs.getString("nome"));
           scontoFound.setTipo(tipo.valueOf(rs.getString("tipo")));
           scontoFound.setPercentuale(rs.getInt("percentuale"));
           scontoFound.setPrezzo(rs.getFloat("prezzo"));
           scontoFound.setVerificabile(verificabile.valueOf(rs.getString("verificabile")));
           scontoFound.setDisponibile(disponibile.valueOf(rs.getString("disponibile")));
           scontoFound.setTipologia(tipologia.valueOf(rs.getString("tipologia")));
           scontoFound.setParametroTipologia(rs.getString("parametro_tipologia"));
           }
           } finally{
           if (stmt != null)
                        stmt.close();
       }        
    return scontoFound;
    }
    
   public synchronized Sconto foundByNome(String nomeSconto) throws SQLException, ParseException, NamingException {
      
       PreparedStatement stmt=null;
       Sconto scontoFound = new Sconto();
       
       
       try {
           stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.Sconto WHERE nome= ? ");
           stmt.setString(1, nomeSconto);
           ResultSet rs = stmt.executeQuery();
           
           while (rs.next()) {
           scontoFound.setIdSconto(rs.getInt("idSconto"));
           scontoFound.setNome(rs.getString("nome"));
           scontoFound.setTipo(tipo.valueOf(rs.getString("tipo")));
           scontoFound.setPercentuale(rs.getInt("percentuale"));
           scontoFound.setPrezzo(rs.getFloat("prezzo"));
           scontoFound.setVerificabile(verificabile.valueOf(rs.getString("verificabile")));
           scontoFound.setDisponibile(disponibile.valueOf(rs.getString("disponibile")));
           scontoFound.setTipologia(tipologia.valueOf(rs.getString("tipologia")));
           scontoFound.setParametroTipologia(rs.getString("parametro_tipologia"));
           }
           } finally{
           if (stmt != null)
                        stmt.close();
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
   public synchronized List<Sconto> foundByDisponibilità() throws SQLException, ParseException, NamingException {
      
       PreparedStatement stmt=null;
       List<Sconto> sconti = new LinkedList<>();
       
       try {
            stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.Sconto WHERE disponibile='" + TRUE +"'");
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
                    if (stmt != null)
			stmt.close();
		}
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
   public synchronized List<Sconto> foundByTipo(tipo tipoSelezionato) throws SQLException, ParseException, NamingException {
       
       PreparedStatement stmt=null;
       List<Sconto> sconti = new LinkedList<>();
       
       try {
            stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.Sconto WHERE tipo= ? ");
            stmt.setString(1,tipoSelezionato.toString());
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
                    if (stmt != null)
			stmt.close();
		}
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
   public synchronized List<Sconto> foundByTipologia(tipologia tipologiaSelezionata) throws SQLException, ParseException, NamingException {
       
       PreparedStatement stmt=null;
       List<Sconto> sconti = new LinkedList<>();
       
       try {
            stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.Sconto WHERE tipologia= ? ");
            stmt.setString(1, tipologiaSelezionata.toString());
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
                    if (stmt != null)
			stmt.close();
		}
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
        
       PreparedStatement stmt=null;
       boolean update= false;
       
       try {
            stmt = (PreparedStatement) connection.prepareStatement("UPDATE evo_cinema.Sconto SET nome= ? , tipo= ? , percentuale= ? , prezzo= ? , verificabile= ?  , disponibile= ? , "
                                                                + "tipologia= ? , parametro_tipologia= ? WHERE ( idSconto= ? );");
            stmt.setString(1, s.getNome());
            stmt.setString(2, s.getTipo().toString());
            stmt.setInt(3, s.getPercentuale());
            stmt.setFloat(4, s.getPrezzo());
            stmt.setString(5, s.getVerificabile().toString());
            stmt.setString( 6 , s.getDisponibile().toString() );
            stmt.setString(7, s.getTipologia().toString());
            stmt.setString( 8 , s.getParametroTipologia());
            stmt.setInt(9, s.getIdSconto());
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
    * Metodo per l'inserimento di uno {@link Sconto} nel DB.
    * @param s Oggetto di tipo {@link Sconto}
    * @return 'true' in caso di successo o 'false' in caso di fallimento
    * @throws SQLException
    * @throws ParseException
    * @throws NamingException 
    */
   public synchronized boolean createSconto(Sconto s) throws SQLException, ParseException, NamingException{
        
       PreparedStatement stmt=null;
       boolean inserita= false;
       
       try {
            stmt = (PreparedStatement) connection.prepareStatement("INSERT INTO evo_cinema.Sconto (idSconto,nome,tipo,percentuale, prezzo, verificabile,disponibile,tipologia,parametro_tipologia)"
                           + "VALUES (? , ? , ? , ? , ? , ? , ? , ? , ? )");
            stmt.setInt(1, s.getIdSconto());
            stmt.setString(2, s.getNome());
            stmt.setString(3, s.getTipo().toString());
            stmt.setInt(4, s.getPercentuale());
            stmt.setFloat(5, s.getPrezzo());
            stmt.setString(6, s.getVerificabile().toString());
            stmt.setString( 7 , s.getDisponibile().toString() );
            stmt.setString(8, s.getTipologia().toString());
            stmt.setString(9, s.getParametroTipologia());
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
    * Metodo per la cancellazione di uno {@link Sconto} all'interno del DB.
    * @param idSconto identificativo dello {@link Sconto}
    * @return 'true' in caso di successo o 'false' in caso di fallimento
    * @throws SQLException
    * @throws ParseException
    * @throws NamingException 
    */
   public synchronized boolean deleteSconto(int idSconto) throws SQLException, ParseException, NamingException{
        
       PreparedStatement stmt=null;
       boolean delete= false;
       
       try {
            stmt = (PreparedStatement) connection.prepareStatement("DELETE FROM evo_cinema.Sconto WHERE ( idSconto= ? );");
            stmt.setInt(1,  idSconto );
            stmt.executeUpdate();
            delete = true;
        } 
        finally {
                if (stmt != null)
                    stmt.close();
            }
    return delete;
    }
   
   
   
   /**
     * Metodo che restituisce una stringa che descrive gli sconti utilizzati con nome, disponibilità e tipologia
     * @return stringa che descrive sconti utilizzati con nome, disponibilità e tipologia
     * @throws SQLException
     * @throws ParseException
     * @throws NamingException 
     */
   public synchronized String analyticsFrequenzaSconti() throws SQLException, ParseException, NamingException {
      
       PreparedStatement stmt=null;
       String sconti="";
       
       try {
           stmt = (PreparedStatement) connection.prepareStatement(
                   " SELECT Sconto.idSconto,Sconto.nome,Sconto.disponibile, "+
                    " Sconto.tipologia,COUNT(Sconto.idSconto) as frequenza "+
           " FROM evo_cinema.Operazione INNER JOIN evo_cinema.Sconto ON Sconto.idSconto = Operazione.sconto_applicato "+
            " AND Sconto.idSconto!=41 GROUP BY Sconto.idSconto; ");
           ResultSet rs = stmt.executeQuery();
           
           while (rs.next()) {
               
           sconti+=rs.getString("idSconto")+"*";
           sconti+=rs.getString("nome")+"*";
           sconti+=rs.getString("disponibile")+"*";
           sconti+=rs.getString("tipologia")+"*";
           sconti+=rs.getString("frequenza")+"*";
           }
           } finally{
           if (stmt != null)
                        stmt.close();
       }       
       //System.out.println(sconti);
    return sconti;
    }
   
   
   
}
