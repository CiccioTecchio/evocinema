/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.acquistoCNT;

import database.UtenteRegistratoDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Acquisto;
import model.Operazione;
import model.UtenteRegistrato;

/**
 *
 * @author Michele
 */
public class AcquistoBigliettoCNT extends HttpServlet {

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
            throws ServletException, IOException, NamingException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        
        System.out.println("Servlet acquistobigliettocnt");
        /*
        HttpSession s = request.getSession();
        UtenteRegistrato user =(UtenteRegistrato) s.getAttribute("user");
        String emailAcquirente="";
        Acquisto op = new Acquisto();
        
        //L'EMAIL LA PRENDO NELLA JSP, NON LA PRELEVO QUI DALLA SESSIONE
        
        if(user.getRuolo()==UtenteRegistrato.ruolo.UTENTE)
        {
            //IL CREDITO E' SUFFICIENTE, CONTROLLO EFFETTUATO PRIMA CON JSON
            emailAcquirente=user.getEmail();
            System.out.println("IDSPETTAOOLO"+request.getAttribute("idSpettacolo"));
            //GET EMAIL UTENTE DA SESSION
            //SCALA SOLDI
        }
        else //OPERATORE
        {
            emailAcquirente=request.getParameter("emailAcquirente");
            //soldi in contanti
            //op.set..
            //operazioneDAO.createOperazione(op);
        }
        */
        
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
        try {
            processRequest(request, response);
        } catch (NamingException ex) {
            Logger.getLogger(AcquistoBigliettoCNT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AcquistoBigliettoCNT.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (NamingException ex) {
            Logger.getLogger(AcquistoBigliettoCNT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AcquistoBigliettoCNT.class.getName()).log(Level.SEVERE, null, ex);
        }
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
