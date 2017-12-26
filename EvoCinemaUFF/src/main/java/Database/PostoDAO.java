
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


public class PostoDAO {
    private static Logger logger= Logger.getLogger("global");
    
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
                        Sala sala = SalaDAO.foundByID(idSala);
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
}
