
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
import model.Film;
import model.Operazione;
import model.Prenotazione;

 
@WebServlet ("/TryServlet")
public class ConfigServlet extends HttpServlet{
    
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
      super.init();
      Logger logger=Logger.getLogger("global");
      try {
          logger.info("pongo");
          
          /*OperazioneDAO operazione= new OperazioneDAO();
          List<Prenotazione> listaPrenotazioni = (List<Prenotazione>) operazione.getPrenotazioni();
          operazione.getDAOConnection().close();
          for(Operazione la : listaPrenotazioni){
              System.out.println(la);
          }*/
          
          FilmDAO film= new FilmDAO();
          List<Film> listaFilm= (List<Film>) film.getAllFilm();
          film.getDAOConnection().close();
          for(Film fm : listaFilm){
              System.out.println(fm);
          }
      } catch (NamingException | SQLException | ParseException ex) {
          Logger.getLogger(ConfigServlet.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
  
}