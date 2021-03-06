/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.programmazioneCNT;

import database.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Film;
import model.Sala;
import model.Spettacolo;

/**
 *
 * @author luca
 */
@WebServlet(name = "PopolamentoListe", urlPatterns = {"/gestore/PopolamentoListe"})
public class PopolamentoListe extends HttpServlet {

    /**
     * Processa le richieste sia per il metodo HTTP <code>GET</code> che per quello <code>POST</code>.
     * Preleva dal database le sale e i film tra cui il gestore potrà scegliere nel momento in cui inserisce un nuovo spettacolo.
     * Prima di terminare setta gli attributi sulla richiesta per permettere alle pagine che includono questa servlet
     * di recuperare i dati elaborati.
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException se si verifica un errore specifico delle servlet
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String id;
        try {
            SalaDAO salaDao = new SalaDAO();
            List<Sala> sale = salaDao.getAllSale();
            FilmDAO filmDao = new FilmDAO();
            List<Film> film = filmDao.getFilmNameAndDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if((id = request.getParameter("idSpettacolo")) != null){
                SpettacoloDAO spettacoloDao = new SpettacoloDAO();
                Spettacolo s = spettacoloDao.foundByID(Integer.parseInt(id));
                request.setAttribute("spettacolo", s);
            }
            request.setAttribute("sdf", sdf);
            request.setAttribute("sale", sale);
            request.setAttribute("film", film);
        } catch (NamingException ex) {
            Logger.getLogger(PopolamentoListe.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PopolamentoListe.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(PopolamentoListe.class.getName()).log(Level.SEVERE, null, ex);
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
