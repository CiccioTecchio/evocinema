
package database;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;
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
import model.Film;
import static model.Film.tipo.TEATRO;
import model.FilmConValutazioneMedia;
import model.Operazione;
import model.Posto;
import static model.Posto.stato.DISPONIBILE;
import static model.Posto.stato.OCCUPATO;
import model.Prenotazione;
import model.Recensione;
import model.Sala;

 
@WebServlet ("/TryServlet")
public class ConfigServlet extends HttpServlet{
    
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
      super.init();
      Logger logger=Logger.getLogger("global");
      
      try {
          logger.info("pongo");
          
          RecensioneDAO rDAO= new RecensioneDAO();
          
          //foundByEmail
          List<Recensione> r = (List<Recensione>) rDAO.foundByEmail("ELombar@alice.it");
          System.out.println("Numero recensioni: "+r.size());
          for(int i=0;i<r.size();i++){
                  System.out.println(r.get(i));
          }
          
          //foundByFilm
          /*List<Recensione> r = (List<Recensione>) rDAO.foundByFilm(1);
          System.out.println("Numero recensioni: "+r.size());
          for(int i=0;i<r.size();i++){
                  System.out.println(r.get(i));
          }*/
          
          
          //getAllRecensioni
          /*List<Recensione> r = (List<Recensione>) rDAO.getAllRecensioni();
          System.out.println("Numero recensioni: "+r.size());
          for(int i=0;i<r.size();i++){
                  System.out.println(r.get(i));
          }*/
          
          
          
          //createRecensione
          /*FilmDAO fD=new FilmDAO();
          Film film=fD.foundByID(16);
          
          List <Recensione> recensioni =(List<Recensione>) rDAO.foundByFilm(1);
          Recensione y = recensioni.get(0);
          y.setTesto("Prova Create");
          y.setFilm(film);
          boolean inserito=rDAO.createRecensione(y);
          System.out.println("inserito= "+inserito);
          */        
          
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
          
          
          rDAO.getDAOConnection().close();
          
          
      } catch (NamingException | SQLException | ParseException ex) {
          Logger.getLogger(ConfigServlet.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
  
}