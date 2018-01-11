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
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Recensione;

/**
 *
 * @author GiuseppeDelGaudio
 */
@WebServlet(name = "recensioniFilm", urlPatterns = {"/recensioniFilm"})
public class RecensioniFilmCNT extends HttpServlet {

    /**
     * Gestione metodo HTTP <code>GET</code>
     * il metodo esegue il forward verso la pagina dettaglioFilm.jsp e carica nella request una Collection di {@link Recensione }
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException se la servlet lancia errori generici
     * @throws IOException se vengono lanciati errori IO
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        String film = request.getParameter("idFilm");
        String order = request.getParameter("order");
            
        if( order == null ) order = "data_recensione";
        
        RecensioneDAO daoRec = new RecensioneDAO(); 
        
        Collection<Recensione> recensioni = null; 
       
        try{
        
       recensioni = daoRec.foundByFilm(film);
       
       } catch (SQLException ex) {
            
            Logger.getLogger(RecensioneDAO.class.getName()).log(Level.SEVERE, "Sql Exception " );
        
        } catch (ParseException ex) {
            
            Logger.getLogger(RecensioneDAO.class.getName()).log(Level.SEVERE, " Parse Exception ");
        
        } catch (NamingException ex) {
            
            Logger.getLogger(RecensioneDAO.class.getName()).log(Level.SEVERE, "Naming Exception ");
        }
        
        
        request.setAttribute("recensioni", recensioni); // attributo di ritorno
        
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/dettaglioFilm.jsp");
        dispatcher.forward(request, response);
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