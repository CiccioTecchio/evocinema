/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.libreriaCNT;

import database.FilmValutazioneDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.FilmConValutazioneMedia;

/**
 * La classe definisce la logica che permette il caricamento di una {@link Collection} di tipo {@link FilmConValutazioneMedia}
 * @author GiuseppeDelGaudio
 */
@WebServlet(name = "visualizzaValutazioni", urlPatterns = {"/visualizzaValutazioni"})
public class VisualizzaValutazioniCNT extends HttpServlet {

   
    /**
     * Gestione metodo HTTP <code>GET</code>
     * viene caricata nella request un attributo "listaFilmValutazione" che rappresenta 
     * una {@link Collection} di {@link FilmConValutazioneMedia}
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException se la servlet lancia errori generici
     * @throws IOException se vengono lanciati errori IO
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
    
            
        
        Collection<FilmConValutazioneMedia> arrayList = new ArrayList<FilmConValutazioneMedia>(); 

        try { 
            FilmValutazioneDAO query = new FilmValutazioneDAO();
            arrayList = query.getAllFilmValutazioni();
        } catch (SQLException ex) {
            Logger.getLogger(VisualizzaValutazioniCNT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(VisualizzaValutazioniCNT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(VisualizzaValutazioniCNT.class.getName()).log(Level.SEVERE, null, ex);
        }

       
        request.setAttribute("listaFilmValutazione", arrayList );
        

        
        
        
        
        
        
        
        
        
        
    }

    /**
     * Gestione metodo HTTP <code>POST</code>
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException se la servlet lancia errori generici
     * @throws IOException se vengono lanciati errori IO
     * 
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        doGet(request, response);
    }


}
