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
import model.Acquisto;

/**
 *
 * @author sarad
 */
public class AcquistoDAO {
   
   private static Logger logger= Logger.getLogger("global");
    
   public synchronized Collection<Acquisto> getAllAcquisti() throws SQLException, ParseException, NamingException {
      
       Connection connection=null;
       PreparedStatement stmt=null;
       Collection<Acquisto> acquisti=new LinkedList<Acquisto>();
       connection = (Connection) SingletonDBConnection.getInstance().getConnInst();
       
       try {
           
            stmt = (PreparedStatement) connection.prepareStatement("SELECT * FROM evo_cinema.operazione");

            ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			Acquisto p = new Acquisto();

				p.setIdOperazione(rs.getInt("id_Operazione"));
				//p.setEmail(rs.getString("email"));
				//p.setIdSpettacolo(rs.getInt("idSpettacolo"));
				//p.setPostoColonna(rs.getInt("posto_colonna"));
                                //p.setPostoRiga(rs.getInt("posto_riga"));
                                //p.setSala(rs.getInt("idSala"));
                                //p.setPrenotato(rs.getEnum("prenotato"));
                                //p.setAcquistato(rs.getEnum("acquistato"));
                                //p.setPrezzoFinale(rs.getFloat("prezzo_finale"));
                                //p.setData(rs.getDate("data"));
                                //p.setSconto(rs.getString("sconto_applicato"));
                                
				acquisti.add(p);
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
