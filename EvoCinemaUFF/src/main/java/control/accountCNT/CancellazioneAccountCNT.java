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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.UtenteRegistrato;

/**
 * Servlet per la cancellazione di un UtenteBase
 *
 * @author Michele
 */
public class CancellazioneAccountCNT extends HttpServlet {

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

    /**
     * Gestisce il metodo HTTP <code>GET</code>.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException in caso di errori specifici della Servlet
     * @throws IOException in caso di errori di I/O
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Gestisce il metodo HTTP <code>POST</code>.
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

        HttpSession s = request.getSession();
        UtenteRegistrato utente = (UtenteRegistrato) s.getAttribute("user");

        boolean cancellato = false;
        System.out.println("utente in sessione: " + utente);

        try {

            UtenteRegistratoDAO model = new UtenteRegistratoDAO();
            cancellato = model.deleteUtenteRegistrato(utente.getEmail());

        } catch (NamingException | SQLException | ParseException ex) {
            Logger.getLogger(CancellazioneAccountCNT.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (cancellato == true) {
            s.removeAttribute("user");
        }

        String page = "../index.jsp";
        response.sendRedirect(page);

    }

}
