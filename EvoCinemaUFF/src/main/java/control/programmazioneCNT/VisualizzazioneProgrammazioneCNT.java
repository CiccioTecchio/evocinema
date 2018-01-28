/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.programmazioneCNT;

import database.FilmDAO;
import database.SpettacoloDAO;
import model.Spettacolo;
import java.util.*;
import java.io.IOException;
import java.sql.*;
import java.text.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Film;

/**
 *
 * @author luca
 */
@WebServlet(name = "VisualizzazioneProgrammazioneCNT", urlPatterns = {"/VisualizzazioneProgrammazioneCNT"})
public class VisualizzazioneProgrammazioneCNT extends HttpServlet {

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
        try {
            Calendar now = new GregorianCalendar();
            now = new GregorianCalendar(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
            now.add(Calendar.DAY_OF_MONTH, -1);
            FilmDAO filmDao = new FilmDAO();
            SpettacoloDAO spettacoloDao = new SpettacoloDAO();
            Collection<Spettacolo> spettacoli;
            Collection<Film> film;
            for(int i = 1; i <= 3; i++){
                now.add(Calendar.DAY_OF_MONTH, 1);
                spettacoli = spettacoloDao.foundByDate(now);
                film = new LinkedList<Film>();
                //scarto gli spettacoli che non rappresentano alcun opera
                spettacoli = spettacoli.stream().filter((s) -> s.getIdFilm() != null).collect(Collectors.toList());
                //ordinamento degli spettacoli in base all'id dell'opera
                spettacoli = spettacoli.stream().sorted((s1, s2) -> Integer.compare(s1.getIdFilm(), s2.getIdFilm())).collect(Collectors.toList());
                //prelevo dal db le opere che mi servono
                int newId, id = spettacoli.stream().findFirst().get().getIdFilm();
                film.add(filmDao.foundByID(id));
                for(Spettacolo s : spettacoli){
                    if((newId = s.getIdFilm()) != id){
                        film.add(filmDao.foundByID(newId));
                        id = newId;
                    }
                }
                request.setAttribute("spettacoli" + i, spettacoli);
                request.setAttribute("film" + i, film);
            }
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
        return "Preleva tutti gli spettacoli della data specificata dal database.";
    }// </editor-fold>

}
