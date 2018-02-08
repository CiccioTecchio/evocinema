/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.libreriaCNT;

import database.RecensioneDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
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
import model.Recensione;
import model.UtenteRegistrato;

/**
 * La classe definisce la logica che permette l'aggiunta di un oggetto di tipo {@link Recensione}
 * @author GiuseppeDelGaudio
 */

@WebServlet(name = "RecensioniCNT", urlPatterns = {"/insertRecensioniFilm"})
public class RecensioniCNT extends HttpServlet {

    /**
     * Gestione metodo HTTP <code>GET</code>
     * meotdo http per inserimento di una recensione;
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
     * Metodo che inserisce una recensione all'interno del DB ricevendo dalla request i parametri "rate" per la valutazione, 
     * "testo" per la recensione e "index" per l'indice dell' ArrayList; 
     * Restituisce una stringa "messaggio" per confermare o meno l'inserimento del messaggio ; 
     * @param request servlet request
     * @param response servlet response
     * 
     * @throws ServletException se la servlet lancia errori generici
     * @throws IOException se vengono lanciati errori IO
     * 
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        UtenteRegistrato ut = (UtenteRegistrato) request.getSession().getAttribute("user"); 
        String recensione = request.getParameter("testo"); 
        float rate = Float.parseFloat(request.getParameter("rate")); 
        String messaggio = "Recensione Inserita Correttamente"; 
        String index = request.getParameter("index"); 
        ArrayList<FilmConValutazioneMedia> array = (ArrayList<FilmConValutazioneMedia>) request.getSession().getAttribute("listaFilmValutazione");
        Film film = array.get(Integer.parseInt(index));
        
        if( recensione== null ) recensione=""; 
        
        if( ut != null ){
        
            try { 
                RecensioneDAO dao  = new RecensioneDAO();
                
                Recensione rec = new Recensione(ut.getEmail() , film , rate , recensione , new GregorianCalendar());
                
                dao.createRecensione(rec);
                
                
                
            } catch (NamingException ex) {
                messaggio="Errore nell'inserimento";
                Logger.getLogger(RecensioniCNT.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                 messaggio="Hai Già Recensito Questo Film";
                Logger.getLogger(RecensioniCNT.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                 messaggio="Errore nell'inserimento";
                Logger.getLogger(RecensioniCNT.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }else {
        
        messaggio = "Devi essere loggato per recensire  <a href='Login.jsp'>Effettua Il login ora </a>"; 
        
        }
        
        request.setAttribute("messaggio", messaggio);
        request.setAttribute("index", index );
        RequestDispatcher res = request.getRequestDispatcher("/SchedaFilm.jsp"); 
        res.forward(request, response);
        
    }
    
}
