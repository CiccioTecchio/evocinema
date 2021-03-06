/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.salaCNT;

import database.SalaDAO;
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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Sala;

/**
 *
 * @author luca
 */
@WebServlet(name = "ModificaSala", urlPatterns = {"/gestore/ModificaSala"})
public class ModificaSalaCNT extends HttpServlet {

    /**
     * Processa le richieste sia per il metodo HTTP <code>GET</code> che per quello <code>POST</code>.
     * Recupera dal database i dati della sala che il gestore ha intenzione di modificare.
     * Esegue su di essi le elaborazioni per renderli fruibili alla jsp e setta i risultati
     * negli attributi sulla richiesta in modo che questa possa recuperarli.
     * Stampa un messaggio di errore se fallisce nel recuperare l'id dalla richiesta http.
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException se si verifica un errore specifico delle servlet
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try{
            int id = 0;
            SalaDAO salaDao = new SalaDAO();
            try {
                id = Integer.parseInt(request.getParameter("id"));
            } catch(NumberFormatException e){
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/gestore/AggiuntaFallimento.jsp");
                request.setAttribute("messaggio", "ID della sala da modificare non pervenuto.");
                dispatcher.forward(request,response);
            }
            Sala sala = salaDao.foundByID(id);
            char[] matPosti = sala.getConfigPosti().toCharArray();
            //Conversione dell'array in una matrice
            char[][] matSala = new char[30][30];
            int z = 0;
            for(int i = 0; i < 30; i++){
                for(int j = 0; j < 30; j++){
                    matSala[i][j] = matPosti[z];
                    z++;
                }
            }
            request.setAttribute("matSala", matSala);
            request.getSession().setAttribute("sala", sala);
        } catch (NamingException ex) {
            Logger.getLogger(ModificaSalaCNT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ModificaSalaCNT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ModificaSalaCNT.class.getName()).log(Level.SEVERE, null, ex);
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
