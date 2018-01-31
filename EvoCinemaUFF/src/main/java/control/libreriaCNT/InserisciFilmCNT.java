/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.libreriaCNT;

import database.FilmDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import model.Film;
import model.Film.vistoCensura; 
import java.text.DateFormat; 
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import model.FilmConValutazioneMedia;

/**
 *
 * @author GiuseppeDelGaudio
 *
 */

@WebServlet(name = "inserisciLibreria" , urlPatterns = { "/admin/inserisciLibreria" })
public class InserisciFilmCNT extends HttpServlet {

 

    
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
        
        doPost(request, response);
       
        
    }

    /**
     * Gestisce una richiesta HTTP GET 
     * Questo metodo inserisce un film all'interno della libreria acquisendo i vari parametri dalla Request per poi effettuare il forward
     * al chiamante e restituire un messaggio con lo stato dell'inserimento.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String goBack = request.getHeader("referer"); 
        String rh = goBack.substring(goBack.lastIndexOf("EvoCinemaUFF")+12);
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
        
        String messageInsert = ""; 
        int tempo = Integer.parseInt(durata); 
       
        ArrayList<FilmConValutazioneMedia> array = (ArrayList<FilmConValutazioneMedia>) request.getSession().getAttribute("listaFilmValutazione");
        Time minuti = calcolaTime(tempo); 
        System.err.println("la data è "+dataUscita);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd"); 
        FilmDAO dao = null; 
        Calendar cal = Calendar.getInstance();
        try { 
            
            Date date = sdf.parse(dataUscita);
            
            
           cal.setTime(date);
           
            FilmConValutazioneMedia film = new FilmConValutazioneMedia( Film.tipo.valueOf(tipo)  , titolo, "images/locandine/"+locandina, regia, cast, genere, minuti , cal , vistoCensura.valueOf(censura) , distribuzione, produzione, trama, trailer,0f);
               
            dao = new FilmDAO();
            
            dao.createFilm(film);
            
            messageInsert = "Inserimento Completato con Successo"; 
            
            array.add(film);

            request.getSession().setAttribute("listaFilmValutazione",array);
                        
        } catch (ParseException ex) {
            Logger.getLogger(InserisciFilmCNT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
           // Logger.getLogger(InserisciFilmCNT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            messageInsert = "Elemento già esistente"; 
            ex.printStackTrace();
            
        }
        
       request.setAttribute("messaggioInsert", messageInsert);
                
       RequestDispatcher dispacher = request.getRequestDispatcher(rh);
       dispacher.forward(request, response);
        
        
        
        
    }
    
    
    private Time calcolaTime(int tempo) {
    
    int ore = (int) tempo/60;
    
    int minuti = (int) tempo - ( ore*60 ); 
    
    return new Time(ore, minuti, 0);
    
    }


}
