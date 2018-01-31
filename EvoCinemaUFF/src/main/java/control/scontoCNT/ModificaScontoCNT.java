/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.scontoCNT;

import database.ScontoDAO;
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
import model.Sconto;

/**
 *
 * @author Michele
 */
@WebServlet( name = "ModificaSconto" , urlPatterns = { "/admin/modificaSconto" } )

public class ModificaScontoCNT extends HttpServlet {

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
        
        String messaggio = "Impossibile completare la modifica";
        
        int idSconto = Integer.parseInt(request.getParameter("idSconto"));
        
        String nome = request.getParameter("nomeSconto");
                
        String tipo = request.getParameter("optTipo");
        int percentuale = 0;
        float prezzo = 0;
        Sconto.tipo tipoSconto;
        switch (tipo){
            case "percentuale": percentuale = Integer.parseInt(request.getParameter("percentualeSconto"));
                                tipoSconto = Sconto.tipo.PERCENTUALE;
                                break;
            case "fisso": prezzo = Float.parseFloat(request.getParameter("cifraSconto"));
                          tipoSconto = Sconto.tipo.FISSO;
                          break;
            default: throw new IOException();
        }
        
        String ver = request.getParameter("optVerificabile");
        Sconto.verificabile verificabile;      

        if (null == ver) verificabile = Sconto.verificabile.FALSE;
        else{
            switch(ver){
                case "True": verificabile = Sconto.verificabile.TRUE;
                             break;
                case "False": verificabile = Sconto.verificabile.FALSE;
                              break;
                default: throw new IOException();
            }
        }
        
        String tip = request.getParameter("optTipologia");
        String parametroTipologia = null;       
        Sconto.tipologia tipologia = null;
        switch (tip){
            case "giorno": parametroTipologia = request.getParameter("giornoDellaSettimana");
                           tipologia = Sconto.tipologia.GIORNO_SETTIMANA;
                           break;
            case "genere": parametroTipologia = request.getParameter("genere");
                           tipologia = Sconto.tipologia.GENERE;
                           break;
            case "film": parametroTipologia = request.getParameter("film");
                         tipologia = Sconto.tipologia.FILM;
                         break;
            case "spettacolo": parametroTipologia = request.getParameter("spettacolo");
                               tipologia = Sconto.tipologia.SPETTACOLO;
                               break;
            case "eta": parametroTipologia = request.getParameter("eta")+Integer.parseInt(request.getParameter("cifraEta"));
                        tipologia = Sconto.tipologia.ETA;
                        break;
            case "data": parametroTipologia = request.getParameter("data");
                         tipologia = Sconto.tipologia.DATA;
                         break;
            case "sesso": parametroTipologia = request.getParameter("sesso");
                          tipologia = Sconto.tipologia.SESSO;
                          break;
            case "altro": parametroTipologia = request.getParameter("altro");
                          tipologia = Sconto.tipologia.ALTRO;
                          break;              
                          
            
            default: throw new IOException();
        }
                    
        try {
            
            Sconto sconto = new Sconto(idSconto, nome, percentuale, prezzo, parametroTipologia, verificabile, tipoSconto, Sconto.disponibile.TRUE, tipologia);

            ScontoDAO dao = new ScontoDAO();
            
            dao.updateSconto(sconto);
            
            messaggio = "Modifica completata con successo";
            
        } catch (NamingException | SQLException | ParseException ex) {
            Logger.getLogger(AggiuntaScontoCNT.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        request.setAttribute("messaggioSconto", messaggio);
        response.sendRedirect("VisualizzaSconti.jsp");
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
