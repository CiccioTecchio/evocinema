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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.UtenteBase;
import model.UtenteRegistrato;

/**
 *
 * @author Michele
 */
public class RicaricaSaldoOperatoreCNT extends HttpServlet {

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
        
        HttpSession s = request.getSession();
        UtenteRegistrato u = (UtenteRegistrato) s.getAttribute("user");
        UtenteBase utente=null;
        float ricarica = 0.0f;
        ricarica = Float.parseFloat(request.getParameter("ricaricaSaldo"));
        
        String emailUtente = request.getParameter("ricaricaEmail");
        
        
        try {
            UtenteRegistratoDAO model = new UtenteRegistratoDAO();
            utente = model.foundUtenteBaseByEmail(emailUtente);
            } catch (NamingException | SQLException | ParseException ex) {
                Logger.getLogger(GestioneSaldoCNT.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(utente==null){
                s.setAttribute("emailNonValida", "true");
                s.setAttribute("ricaricaEffettuata", "false");
                String page="/operatore/RicaricaSaldoOperatore.jsp";
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            }else{
            
                if(ricarica+utente.getSaldo()>=999.99){
                    s.setAttribute("ImportoNonValido", "true");
                    s.setAttribute("ricaricaEffettuata", "false");
                    String page="/operatore/RicaricaSaldoOperatore.jsp";
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
                }else{

                    if(ricarica>0){
                        float saldo = utente.getSaldo();
                        System.out.println("saldo: "+saldo);
                        System.out.println("importo da ricaricare: "+ricarica);
                        saldo = saldo + ricarica;
                        System.out.println("saldo dopo ricarica: "+saldo);
                        utente.setSaldo(saldo);
                        System.out.println("saldo utente: "+utente.getSaldo());

                        try{
                            UtenteRegistratoDAO model = new UtenteRegistratoDAO();
                            model.updateUtenteRegistrato(utente);}
                        catch (NamingException | SQLException | ParseException ex) {
                            Logger.getLogger(GestioneSaldoCNT.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        s.setAttribute("emailNonValida", "false");
                        s.setAttribute("ImportoNonValido", "false");
                        s.setAttribute("ricaricaEffettuata", "true");
                    }
                }
            

            String page="/operatore/RicaricaSaldoOperatore.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
    }}

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
