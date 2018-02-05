/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.programmazioneCNT;

import database.FilmDAO;
import database.SalaDAO;
import database.SpettacoloDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Film;
import model.Sala;
import model.Spettacolo;

/**
 *
 * @author Michele
 */
public class VisualizzazioneDettagliSpettacoloCNT extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Logger logger = Logger.getLogger("global");
        try {
            int id = Integer.parseInt(request.getParameter("idSpettacolo"));
            SpettacoloDAO spettacoloDao = new SpettacoloDAO();
            SalaDAO salaDao = new SalaDAO();
            Spettacolo spettacolo = spettacoloDao.foundByID(id);
            Sala sala = salaDao.foundByID(spettacolo.getIdSala());
            Calendar now = new GregorianCalendar();
            now = new GregorianCalendar(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
            Calendar start = spettacolo.getDataInizio();
            int offset = (int) ((now.getTimeInMillis() - start.getTimeInMillis()) / (1000*60*60*24));
            int offsetGiorno = Integer.parseInt(request.getParameter("offset-giorno"));
            FilmDAO filmDao = new FilmDAO();
            Film film = filmDao.foundByID(spettacolo.getIdFilm());
            
            request.setAttribute("spettacolo", spettacolo);
            request.setAttribute("film", film);
            request.setAttribute("sala", sala);
            request.setAttribute("offset", offset + offsetGiorno);
            request.setAttribute("title", "Dettagli Spettacolo");
        } catch (SQLException | ParseException | NamingException e){
            Logger.getLogger(VisualizzazioneDettagliSpettacoloCNT.class.getName()).log(Level.SEVERE, null, e);
        }    
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
