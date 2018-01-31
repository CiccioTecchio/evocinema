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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.UtenteRegistrato;
import static model.UtenteRegistrato.ruolo.GESTORE;
import static model.UtenteRegistrato.ruolo.OPERATORE;

/**
 *
 * @author Michele
 */
public class CancellazioneGestore extends HttpServlet {

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
        Boolean cancellato = false;
        String page="";
        
        String utenteDaCancellare = (String) request.getParameter("emailCancellaUtente");
        String email = utenteDaCancellare.substring(1);
        String ruolo =utenteDaCancellare.substring(0,1);
        
        System.out.println("email da cancellare: " +utenteDaCancellare);
        if(u!=null){

            try {
                UtenteRegistratoDAO model = new UtenteRegistratoDAO();
                
                cancellato = model.deleteUtenteRegistrato(email);
                
                
               
                
                
                

                if(ruolo.equals("o")){
                    page="VisualizzaOperatoriCNT";
                }else{
                    if(ruolo.equals("g")){
                        page="VisualizzaGestoriCNT";
                    }
                }

                
            } catch (NamingException | SQLException | ParseException ex) {
                Logger.getLogger(VisualizzaRecensioniCNT.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
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