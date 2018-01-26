
package database;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.UtenteBase;

 
@WebServlet ("/TryServlet")
public class ConfigServlet extends HttpServlet{
    
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
      super.init();
      try {
          UtenteRegistratoDAO model = new UtenteRegistratoDAO();
          UtenteBase a =model.foundUtenteBaseByEmail("Daespo44@gmail.com");
          System.out.print(a);
          
          //updateRecensione
          /*List <Recensione> recensioni =(List<Recensione>) rDAO.foundByFilm(16);
          Recensione y = recensioni.get(0);
          
          y.setTesto("Prova Update");
          y.setValutazione(1.0f);
          System.out.println(y);
          
          boolean modificato = rDAO.updateRecensione(y);
          System.out.println("modificato= "+modificato);
          */
          
          //deleteOperazione
          /*List <Recensione> recensioni =(List<Recensione>) rDAO.foundByFilm(16);
          Recensione y = recensioni.get(0);
          
          boolean cancellato1=rDAO.deleteRecensione(y);
          System.out.println("cancellato1 ="+cancellato1);*/
      } catch (NamingException ex) {
          Logger.getLogger(ConfigServlet.class.getName()).log(Level.SEVERE, null, ex);
      } catch (SQLException ex) {
          Logger.getLogger(ConfigServlet.class.getName()).log(Level.SEVERE, null, ex);
      } catch (ParseException ex) {
          Logger.getLogger(ConfigServlet.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
  
}