
package control.libreriaCNT;
import database.RecensioneDAO;
import model.Recensione; 
import java.io.IOException;
import javax.servlet.RequestDispatcher;
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

/**
 * La classe definisce la logica che permette l'aggiunta di un oggetto di tipo {@link Recensione}
 * @author GiuseppeDelGaudio
 */
@WebServlet(name = "AggiungiRecensioneCNT", urlPatterns = {"/AggiungiRecensioneCNT"})
public class AggiungiRecensioneCNT extends HttpServlet {

   /**
     * Gestione metodo HTTP <code>GET</code>
     * la servlet invia utilizzando POST  
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException se la servlet lancia errori generici
     * @throws IOException se vengono lanciati errori IO
     */
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Gestione metodo HTTP <code>POST</code>
     * Il metodo preleva dalla request il parametro di tipo {@link Recensione} e una volta completato l'inserimento esegue il forward verso dettaglioFilm.jsp 
     * viene restituito inoltre come attributo un oggetto di tipo String "messaggio" con la riuscita o meno dell'operazione 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException se la servlet lancia errori generici
     * @throws IOException se vengono lanciati errori IO
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Object obj = request.getParameter("recensione"); 
        String messaggio = "Operazione Riuscita"; 
        String re = "/dettaglioFilm.jsp"; // pagina per il forward
        if ( obj instanceof Recensione ) {
        
           
                Recensione recensione = ( Recensione ) obj;
                RecensioneDAO dao = null;
                
             try {    
                dao = new RecensioneDAO();

                dao.updateRecensione(recensione);
                
            } catch (SQLException ex) {
                
                        Logger.getLogger(AggiungiRecensioneCNT.class.getName()).log(Level.INFO," La recensione non esiste ... creo record... ");
                        
                        try{
                        
                            dao.createRecensione(recensione);
                                
                        } catch (SQLException a) {
            
                        Logger.getLogger(VisualizzaValutazioniCNT.class.getName()).log(Level.SEVERE, "Sql Exception " );
                        
                         messaggio = "Operazione Non Riuscita"; 
        
                        } catch (ParseException a) {
            
                        Logger.getLogger(VisualizzaValutazioniCNT.class.getName()).log(Level.SEVERE, " Parse Exception ");
                        
                        messaggio = "Operazione Non Riuscita"; 
        
                        } catch (NamingException a) {
            
                        Logger.getLogger(VisualizzaValutazioniCNT.class.getName()).log(Level.SEVERE, "Naming Exception ");
                        
                        messaggio = "Operazione Non Riuscita"; 
                        
                        }       
            
            } catch (ParseException ex) {
                Logger.getLogger(AggiungiRecensioneCNT.class.getName()).log(Level.SEVERE, null, ex);
                
                messaggio = "Operazione Non Riuscita"; 
                
            } catch (NamingException ex) {
                
                Logger.getLogger(AggiungiRecensioneCNT.class.getName()).log(Level.SEVERE, null, ex);
                
                messaggio = "Operazione Non Riuscita"; 
            
            }
            
        
        }else{
        
            // Errore ----> forward cambiare parametro re 
        
            
            
        }
        
        request.setAttribute("messaggio", messaggio);
        
        RequestDispatcher dispacher = getServletContext().getRequestDispatcher(re);
        dispacher.forward(request, response);
        
    }

    
}


