/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.acquistoCNT;

import database.ScontoDAO;
import database.SpettacoloDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Sconto;
import model.Spettacolo;

/**
 *
 * @author pietr
 */
@WebServlet(name = "GestioneAcquistiCNT", urlPatterns = {"/GestioneAcquistiCNT"})
public class GestioneAcquistiCNT extends HttpServlet {

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
            throws ServletException, IOException, SQLException, ParseException, NamingException {
        response.setContentType("text/html;charset=UTF-8");
       
        
            /*String myData = request.getParameter("dataRegistrazione");
                int anno = Integer.parseInt(myData.substring(0, 4));
                int mese = Integer.parseInt(myData.substring(5, 7)) - 1;
                int giorno = Integer.parseInt(myData.substring(8, 10));
                Calendar data = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"),Locale.ITALY);
                data.set(anno, mese, giorno);                

                u.setDataNascita(data);*/
        
            //FILM E SCONTI SONO SEMPRE GLI STESSI, VARIANO SOLTANTO ORE E DATA    
            
            SpettacoloDAO spdao= new SpettacoloDAO();
            Calendar cal = Calendar.getInstance();
                        
            
            List<Spettacolo> spettacoli = spdao.foundByDate(cal);
            
            
            ScontoDAO scdao = new ScontoDAO();
            List<Sconto> sconti = scdao.getAllSconti();
            
            request.setAttribute("SPETTACOLI", spettacoli);
            request.setAttribute("SCONTI", sconti);
        
        
        
        
         /*
        PrintWriter out=response.getWriter();
        
        request.setAttribute("Title", "Gestione acquisto");*/
        

        //RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/gestioneOperazioniSala.jsp");
	//		dispatcher.forward(request,response);
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
            Logger.getLogger(GestioneAcquistiCNT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(GestioneAcquistiCNT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(GestioneAcquistiCNT.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GestioneAcquistiCNT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(GestioneAcquistiCNT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(GestioneAcquistiCNT.class.getName()).log(Level.SEVERE, null, ex);
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
