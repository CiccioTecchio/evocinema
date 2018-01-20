
package database;

import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 
@WebServlet ("/TryServlet")
public class ConfigServlet extends HttpServlet{
    
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
      super.init();
      Logger logger=Logger.getLogger("global");
      
      logger.info("pongo");
      
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
  }
  
}