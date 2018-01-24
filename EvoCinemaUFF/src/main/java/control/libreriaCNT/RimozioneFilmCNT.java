/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.libreriaCNT;

import database.FilmDAO;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.Clock;
import java.util.ArrayList;
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

/**
 *
 * @author GiuseppeDelGaudio
 */

@WebServlet( name = "RimozioneFilm" , urlPatterns = { "/admin/rimuoviFilm" } )
public class RimozioneFilmCNT extends HttpServlet {

    /**
     * Gestisce le richieste HTTP inviate con <code>GET</code>.
     *
     * Recupera dalla sessione un array di {@link Film} e prelevando il parametro position recupera l'elemento da eliminare sul DB 
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        int position = Integer.parseInt(request.getParameter("position"));
        
        ArrayList<Film> array = (ArrayList<Film>) request.getSession().getAttribute("listaFilmValutazione");
        
        Film film = array.get(position);
        
        String messageDelete = ""; 
        
        try { 
            
            FilmDAO dao = new FilmDAO();
            
            dao.deleteFilm(film.getIdFilm()); 
            String locandina = film.getLocandina();
            String namefile = locandina.substring(locandina.lastIndexOf("/")+1);
            String path =  request.getServletContext().getRealPath("")+"images"+File.separator+"locandine"+File.separator+namefile;
            System.out.println("la path del file è -->"+path);
            File file = new File(path); 
            
            if( ! file.delete()) { messageDelete="Problemi nell'eliminazione della locandina";  } 
            
            messageDelete="Eliminato con successo";
            array.remove(position);
            request.getSession().setAttribute("listaFilmValutazione",array);
            
        } catch (NamingException ex) {
            
            messageDelete = "Errore durante l'eliminazione NamingException ";
            
            Logger.getLogger(RimozioneFilmCNT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            messageDelete="Errore durante l'eliminazione SQLException";
            Logger.getLogger(RimozioneFilmCNT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
             messageDelete="Errore durante l'eliminazione ParseException";
            Logger.getLogger(RimozioneFilmCNT.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        RequestDispatcher res = request.getRequestDispatcher("/admin/ModificaLibreria.jsp");
        request.setAttribute("messageDelete", messageDelete);
        
        res.forward(request, response);
        
        
    }

    /**
     * Gestisce le richieste HTTP inviate con <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        doGet(request, response);
    }

    
}
