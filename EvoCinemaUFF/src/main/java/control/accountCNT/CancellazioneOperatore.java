/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.accountCNT;

import database.UtenteRegistratoDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
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
import model.UtenteRegistrato;

/**
 * Servlet che gestisce la cancellazione di un Operatore
 *
 * @author francescodefeo
 */
@WebServlet(name = "CancellazioneOperatore", urlPatterns = {"/gestore/cancellazioneOperatore"})
public class CancellazioneOperatore extends HttpServlet {

    /**
     * Processa le richieste dei metodi HTTP <code>GET</code> e
     * <code>POST</code>
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException in caso di errori specifici della Servlet
     * @throws IOException in caso di errori di I/O
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

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
     * Gestisce il metodo HTTP <code>GET</code>.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException in caso di errori specifici della Servlet
     * @throws IOException in caso di errori di I/O
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
