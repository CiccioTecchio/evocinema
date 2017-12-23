
package Database;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Acquisto;

 
@WebServlet ("/TryServlet")
public class ConfigServlet extends HttpServlet{
    
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
      super.init();
      Logger logger=Logger.getLogger("global");
      try {
          logger.info("pongo");
          
          AcquistoDAO acquisto= new AcquistoDAO();
          List<Acquisto> listaAcquisti = (List<Acquisto>) acquisto.getAllAcquisti();
          for(Acquisto la : listaAcquisti){
              System.out.println(la);
          }
      } catch (NamingException | SQLException | ParseException ex) {
          Logger.getLogger(ConfigServlet.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
  
}