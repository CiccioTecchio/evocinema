/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.programmazioneCNT;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.*;
import database.*;

/**
 *
 * @author Michele
 */
public class ModificaSpettacoloCNT extends HttpServlet {

    /**
     *Servlet che prende lo spettacolo in base all'id passato dalla visualizzazione dettagli spettacolo e lo passa alla jsp per modificarlo
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            SpettacoloDAO spettacoloDao = new SpettacoloDAO();            
            int idS = Integer.parseInt(request.getParameter("idSpettacolo"));            
            Spettacolo spettacolo = spettacoloDao.foundByID(idS);
            FilmDAO filmDao = new FilmDAO();
            int idF = spettacolo.getIdFilm();
            Film film = filmDao.foundByID(idF);
            request.setAttribute("spettacolo", spettacolo);
            request.setAttribute("film", film);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/modificaProdotto.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException | ParseException | NamingException e) {
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
