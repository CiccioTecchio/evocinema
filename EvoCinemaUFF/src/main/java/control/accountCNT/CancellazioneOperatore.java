/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.accountCNT;

import database.UtenteRegistratoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Film;
import model.UtenteRegistrato;
import static model.UtenteRegistrato.ruolo.GESTORE;
import static model.UtenteRegistrato.ruolo.OPERATORE;

/**
 *
 * @author francescodefeo
 */
@WebServlet(name = "CancellazioneOperatore", urlPatterns = {"/gestore/cancellazioneOperatore"})
public class CancellazioneOperatore extends HttpServlet {

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
        int position = Integer.parseInt(request.getParameter("position"));

        List<UtenteRegistrato> array = (List<UtenteRegistrato>) request.getSession().getAttribute("operatori");

        UtenteRegistrato ut = array.get(position);

        String messageDelete = "";
        if (ut != null) {

            try {
                UtenteRegistratoDAO model = new UtenteRegistratoDAO();
                model.deleteUtenteRegistrato(ut.getEmail());
                messageDelete = "Eliminato con successo";
                array.remove(position);
                request.getSession().setAttribute("operatori", array);

            } catch (NamingException | SQLException | ParseException ex) {
                messageDelete = "Errore durante l'eliminazione NamingException ";
                Logger.getLogger(VisualizzaRecensioniCNT.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/gestore/Operatori.jsp");
        request.setAttribute("messageDelete", messageDelete);
        dispatcher.forward(request, response);
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
