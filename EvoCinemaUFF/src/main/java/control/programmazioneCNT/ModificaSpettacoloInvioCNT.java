/*
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
import java.util.Calendar;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Giuseppe
 */
public class ModificaSpettacoloInvioCNT {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            HttpSession s = request.getSession();
            Spettacolo sp = (Spettacolo) s.getAttribute("spettacolo");
            sp.setPrezzo(Integer.parseInt((String)request.getParameter("prezzo")));
            sp.setOraInizio((Calendar) request.getParameter("orario"));
            sp.setDataInizio((Calendar) request.getParameter("orario"));
            sp.setDataFine((Calendar) request.getParameter("orario"));
            sp.setIdSala(Integer.parseInt((String)request.getParameter("sala")));
            SpettacoloDAO spettacoloDao = new SpettacoloDAO();
            spettacoloDao.updateSpettacolo(sp);
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
