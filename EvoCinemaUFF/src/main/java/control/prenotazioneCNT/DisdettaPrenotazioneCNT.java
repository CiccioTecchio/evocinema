/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.prenotazioneCNT;

import database.OperazioneDAO;
import database.SpettacoloDAO;
import database.UtenteRegistratoDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
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
import model.Spettacolo;
import model.UtenteBase;
import model.UtenteRegistrato;

/**
 *
 * @author giuseppeapuzzo
 */
@WebServlet(name = "DisdettaPrenotazioneCNT", urlPatterns = {"/DisdettaPrenotazioneCNT"})
public class DisdettaPrenotazioneCNT extends HttpServlet {

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
            throws ServletException, IOException, NamingException, SQLException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        UtenteRegistrato utente =(UtenteRegistrato) session.getAttribute("user");
        String email = utente.getEmail();
        
        UtenteRegistratoDAO utenteDAO = new UtenteRegistratoDAO();
        UtenteBase utbase = utenteDAO.foundUtenteBaseByEmail(email);
        
        Operazione operazione = (Operazione) session.getAttribute("operazione");
        OperazioneDAO opDAO = new OperazioneDAO();
        
        opDAO.deleteOperazione(operazione.getIdOperazione());
        
        SpettacoloDAO spettDAO = new SpettacoloDAO();
        Spettacolo spettacolo = spettDAO.foundByID(operazione.getIdSpettacolo());
        Calendar dataSpettacolo = spettacolo.getDataInizio();
        Calendar oggi = Calendar.getInstance();
        if(dataSpettacolo.after(oggi) ){
            utbase.setSaldo(utbase.getSaldo() + 2.0f);
            utenteDAO.updateUtenteBase(utbase);
        }
        //else non recupera la cauzione di 2€
        response.sendRedirect("VisualizzaPrenotazioni.jsp");
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
            Logger.getLogger(DisdettaPrenotazioneCNT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DisdettaPrenotazioneCNT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(DisdettaPrenotazioneCNT.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DisdettaPrenotazioneCNT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DisdettaPrenotazioneCNT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(DisdettaPrenotazioneCNT.class.getName()).log(Level.SEVERE, null, ex);
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
