/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.acquistoCNT;

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
import model.UtenteBase;
import model.UtenteRegistrato;
import org.json.JSONObject;


/**
 * La classe restituisce un oggetto json che rappresenta un esito positivo se l'utente è in grado di effettuare 
 * l'acquisto/prenotazione, un esito negativo altrimenti
 * @author PietroDell'Isola
 */
@WebServlet(name = "JSONDisponibilitaSaldo", urlPatterns = {"/JSONDisponibilitaSaldo"})
public class JSONDisponibilitaSaldo extends HttpServlet {

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

        
        response.setContentType("application/json;charset=UTF-8");
        //PrintWriter out = response.getWriter();
        
        String emailAcquirente=request.getParameter("emailAcquirente");
        UtenteRegistratoDAO urd = new UtenteRegistratoDAO();
        JSONObject jsonObject=new JSONObject();
        UtenteBase acquirente = urd.foundUtenteBaseByEmail(emailAcquirente);/*
        if(acquirente.getRuolo()==UtenteRegistrato.ruolo.UTENTE){
            //SE E' UN UTENTE BASE
            UtenteBase utenteb = (UtenteBase)urd.foundUtenteBaseByEmail(user.getEmail());*/
        
            if((Float.parseFloat((String)request.getParameter("importoTotale")))>acquirente.getSaldo())
                //SE HA IL CREDITO INSUFFICIENTE
                jsonObject.put("Saldo insufficiente", 0);
            else  jsonObject.put("Ok", 0); //SE HA IL CREDITO SUFFICIENTE
       // }
        
        
        //SE E' UN OPERATORE UTILIZZERA' PAGAMENTO IN CONTANTI
        //else  jsonObject.put("Ok", 0);
        
        
        response.getWriter().write(jsonObject.toString());

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
            Logger.getLogger(JSONDisponibilitaSaldo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JSONDisponibilitaSaldo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(JSONDisponibilitaSaldo.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(JSONDisponibilitaSaldo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JSONDisponibilitaSaldo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(JSONDisponibilitaSaldo.class.getName()).log(Level.SEVERE, null, ex);
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
