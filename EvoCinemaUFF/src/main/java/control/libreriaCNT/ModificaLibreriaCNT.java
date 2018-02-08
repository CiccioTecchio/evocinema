
package control.libreriaCNT;

import database.FilmDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Film;
import model.FilmConValutazioneMedia;

/**
 * La classe definisce la logica che permette la modifica di un oggetto di tipo {@link Film}
 * @author GiuseppeDelGaudio
 */
@WebServlet(name = "ModificaLibreriaCNT", urlPatterns = {"/gestore/modificaLibreria"})
public class ModificaLibreriaCNT extends HttpServlet {

    
    /**
     * Gestisce una richiesta HTTP GET
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * 
     * Gestisce una richiesta HTTP POST . Questo metodo modifcia un film
     * all'interno della libreria acquisendo i vari parametri dalla Request per
     * poi effettuare il forward ModificaFilm.jsp e restituisce un attributo 
     * "messaggioUpdate" con lo stato dell'inserimento .
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        String tipo = request.getParameter("tipo");
        String titolo = request.getParameter("titolo"); 
        String durata = request.getParameter("durata"); 
        String genere = request.getParameter("genere");
        String locandina = request.getParameter("Nomelocandina"); 
        String trama = request.getParameter("trama");
        String produzione = request.getParameter("produzione");
        String trailer=request.getParameter("trailer"); 
        String distribuzione = request.getParameter("distribuzione");
        String dataUscita = request.getParameter("dataUscita"); 
        String censura = request.getParameter("censura");
        String cast = request.getParameter("cast");
        String regia = request.getParameter("regia");
        String idOpera = request.getParameter("idOpera");
        String ritorno = request.getParameter("index");
        String messageUpdate = ""; 
        int tempo = Integer.parseInt(durata); 
        ArrayList<FilmConValutazioneMedia> array = (ArrayList<FilmConValutazioneMedia>) request.getSession().getAttribute("listaFilmValutazione");
        FilmConValutazioneMedia filmedia = array.get(Integer.parseInt(ritorno));
        float media = filmedia.getValutazioneMedia(); 
        Time minuti = calcolaTime(tempo); 
        System.err.println("la data è "+dataUscita);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd"); 
        
        Calendar cal = Calendar.getInstance();
        try { 
            
            Date date = sdf.parse(dataUscita);
            
            
           cal.setTime(date);
           
            FilmConValutazioneMedia film = new FilmConValutazioneMedia( Integer.parseInt(idOpera) ,Film.tipo.valueOf(tipo)  , titolo, locandina, regia, cast, genere, minuti , cal , Film.vistoCensura.valueOf(censura) , distribuzione, produzione, trama, trailer,media );
               
            FilmDAO dao = new FilmDAO();
            
            dao.updateFilm(film);
            
            messageUpdate = "Modifica Completata con Successo"; 
            
            array.set(Integer.parseInt(ritorno), film);
            
            request.getSession().setAttribute("listaFilmValutazione",array);
            
            System.err.println("Stampaaa -->" + messageUpdate );
            
        } catch (ParseException ex) {
            Logger.getLogger(InserisciFilmCNT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
           // Logger.getLogger(InserisciFilmCNT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            messageUpdate = "Aggiornamento Fallito"; 
            Logger.getLogger(InserisciFilmCNT.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        System.err.println("Stampaaa -->" + messageUpdate );
        
       request.setAttribute("messaggioUpdate", messageUpdate);
       
       request.setAttribute("index", ritorno );
       
       RequestDispatcher dispacher = request.getRequestDispatcher("/gestore/ModificaFilm.jsp");
       dispacher.forward(request, response);
        
        
        
        
    }
    
    
    private Time calcolaTime(int tempo) {
    
    int ore = (int) tempo/60;
    
    int minuti = (int) tempo - ( ore*60 ); 
    
    return new Time(ore, minuti, 0);
    
    }
        
    }

   
    


