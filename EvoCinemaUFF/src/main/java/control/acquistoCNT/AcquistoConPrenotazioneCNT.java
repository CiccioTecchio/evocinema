/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.acquistoCNT;

import database.OperazioneDAO;
import database.UtenteRegistratoDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Operazione;
import model.UtenteBase;
import model.UtenteRegistrato;
import model.UtenteRegistrato.ruolo;

/**
 *
 * @author giuseppeapuzzo
 */
@WebServlet(name = "AcquistoConPrenotazioneCNT", urlPatterns = {"/AcquistoConPrenotazioneCNT"})
public class AcquistoConPrenotazioneCNT extends HttpServlet {

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
            throws ServletException, IOException, SQLException, NamingException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        
        String email =""; 
       
        HttpSession session = request.getSession();
        UtenteRegistrato utente =(UtenteRegistrato) session.getAttribute("user");
        if( (utente.getRuolo()).equals(ruolo.UTENTE) ) {
            email = utente.getEmail();
            UtenteRegistratoDAO utenteDAO = new UtenteRegistratoDAO();
            UtenteBase utbase = utenteDAO.foundUtenteBaseByEmail(email);

            Operazione operazione = (Operazione) session.getAttribute("operazione");
            OperazioneDAO opDAO = new OperazioneDAO();
            float price = operazione.getPrezzoFinale();
            float saldo = utbase.getSaldo();
            utbase.setSaldo(saldo - price + 2.0f);
            boolean responso = utenteDAO.updateUtenteBase(utbase);
            if(responso){
                operazione.setAcquistato(Operazione.acquistato.TRUE);
                opDAO.updateOperazione(operazione);
                response.sendRedirect("ResocontoAcquisto.jsp");
            }else{
                response.sendRedirect("VisualizzaPrenotazioni.jsp");
            }
        
        }
        if( (utente.getRuolo()).equals(ruolo.OPERATORE)){
        
            Operazione operazione = (Operazione) session.getAttribute("operazione");
            OperazioneDAO opDAO = new OperazioneDAO();
        
            operazione.setAcquistato(Operazione.acquistato.TRUE);
            opDAO.updateOperazione(operazione);
            response.sendRedirect("ResocontoAcquisto.jsp");
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AcquistoConPrenotazioneCNT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(AcquistoConPrenotazioneCNT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AcquistoConPrenotazioneCNT.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException ex) {
            Logger.getLogger(AcquistoConPrenotazioneCNT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(AcquistoConPrenotazioneCNT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AcquistoConPrenotazioneCNT.class.getName()).log(Level.SEVERE, null, ex);
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
