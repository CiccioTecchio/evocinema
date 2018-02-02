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
import java.util.Arrays;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Spettacolo;

@WebServlet(name = "AggiungiSpettacolo", urlPatterns = {"/gestore/AggiungiSpettacolo"})
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
        response.setContentType("text/html;charset=UTF-8");
        Spettacolo s = new Spettacolo();
        String titolo, dataInizio, dataFine, oraInizio, oraFine;
        int idFilm = 0, sala = 0;
        float prezzo = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Logger logger = Logger.getLogger("global");
        logger.info("aggiunta spettacolo cnt");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/gestore/AggiuntaFallimento.jsp");
        
        try {
            try {
                idFilm = Integer.parseInt(request.getParameter("id-film"));
            } catch (NumberFormatException e){
                request.setAttribute("messaggio", "L'id del film da rappresentare, nel giusto formato, è obbligatorio.");
                dispatcher.forward(request,response);
            }
            s.setIdFilm(idFilm);
            
            if((titolo = request.getParameter("titolo")) != null){
                s.setTitolo(titolo);
            } else {
                request.setAttribute("messaggio", "Il titolo dello spettacolo, nel giusto formato, è obbligatorio.");
                dispatcher.forward(request,response);
            }
            
            try {
                sala = Integer.parseInt(request.getParameter("sala"));
            } catch (NumberFormatException e){
                request.setAttribute("messaggio", "L'id della sala in cui rappresentare il film, nel giusto formato, è obbligatorio.");
                dispatcher.forward(request,response);
            }
            s.setIdSala(sala);
            
            if((dataInizio = request.getParameter("data-inizio")) != null)
                s.setDataInizio(toCalendar(sdf.parse(dataInizio)));
            else {
                request.setAttribute("messaggio", "La data di inizio rappresentazione, nel giusto formato, è obbligatoria.");
                dispatcher.forward(request,response);
            }

            if((dataFine = request.getParameter("data-fine")) != null)
                s.setDataFine(toCalendar(sdf.parse(dataFine)));
            else {
                request.setAttribute("messaggio", "La data di fine rappresentazione, nel giusto formato, è obbligatoria.");
                dispatcher.forward(request,response);
            }
            
            sdf = new SimpleDateFormat("HH:mm");
            
            if((oraInizio = request.getParameter("ora-inizio")) != null)
                s.setOraInizio(toCalendar(sdf.parse(oraInizio)));
            else {
                request.setAttribute("messaggio", "L'orario di inizio spettacolo, nel giusto formato, è obbligatorio.");
                dispatcher.forward(request,response);
            }
            
            if((oraFine = request.getParameter("ora-fine")) != null)
                s.setOraFine(toCalendar(sdf.parse(oraFine)));
            else {
                request.setAttribute("messaggio", "L'orario di fine spettacolo, nel giusto formato, è obbligatorio.");
                dispatcher.forward(request,response);
            }
            
            try {
                prezzo = Float.parseFloat(request.getParameter("prezzo"));
            } catch (NumberFormatException e){
                request.setAttribute("messaggio", "Il prezzo dello spettacolo, nel giusto formato, è obbligatorio.");
                dispatcher.forward(request,response);
            }
            s.setPrezzo(prezzo);
            
            String posti = "d";
            for(int i = 0; i < 18000; i++){
                posti += "d";
            }
            s.setMatricePosti(posti);
            
            SpettacoloDAO spettacoloDao = new SpettacoloDAO();
            spettacoloDao.createSpettacolo(s);
            
            request.setAttribute("spettacolo", s);
            request.setAttribute("title", "Aggiunta avvenuta con Successo");            
            
            dispatcher = getServletContext().getRequestDispatcher("/gestore/AggiuntaSuccesso.jsp");
            dispatcher.forward(request,response);
            
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
