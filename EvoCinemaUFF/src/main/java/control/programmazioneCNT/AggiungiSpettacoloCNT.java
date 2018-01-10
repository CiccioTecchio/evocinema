/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.programmazioneCNT;

import database.SpettacoloDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Spettacolo;

/**
 *
 * @author Michele
 */
public class AggiungiSpettacoloCNT extends HttpServlet {

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
        try {
            response.setContentType("text/html;charset=UTF-8");
            SpettacoloDAO spettacoloDao = new SpettacoloDAO();
            SimpleDateFormat sdf = new SimpleDateFormat("DD-MM-YYYY");
            
            int sala = Integer.parseInt(request.getParameter("id_sala"));
            int opera = Integer.parseInt(request.getParameter("idOpera"));
            Calendar dataInizio = this.toCalendar(sdf.parse(request.getParameter("data_inizio")));
            Calendar dataFine = this.toCalendar(sdf.parse(request.getParameter("data_fine")));
            float prezzo = Float.parseFloat(request.getParameter("prezzo"));
            Calendar oraInizio = this.toCalendar(sdf.parse(request.getParameter("ora_inizio")));
            Calendar oraFine = this.toCalendar(sdf.parse(request.getParameter("ora_fine")));
            spettacoloDao.createSpettacolo(new Spettacolo(sala, opera, dataInizio, dataFine, prezzo, oraInizio, oraFine));
        } catch (SQLException | ParseException | NamingException e){
            Logger.getLogger(VisualizzazioneDettagliSpettacoloCNT.class.getName()).log(Level.SEVERE, null, e);
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
    public static Calendar toCalendar(Date date){ 
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

}
