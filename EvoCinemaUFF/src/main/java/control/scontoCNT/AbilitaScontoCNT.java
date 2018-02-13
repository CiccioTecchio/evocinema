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
import org.json.JSONObject;

/**
 * La classe definisce la logica che permette di abilitare e disabilitare un oggetto di tipo {@link Sconto}
 * @author Michele
 */
@WebServlet(name = "abilitaSconto", urlPatterns = {"/gestore/abilitaSconto"})
public class AbilitaScontoCNT extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
        response.setContentType("application/json");
        String position = request.getParameter("position");
        String swi = request.getParameter("switch");
        String messaggio = "Cambiato correttamente";

        List<Sconto> array = (List<Sconto>) request.getSession().getAttribute("listaSconti");

        Sconto s = array.get(Integer.parseInt(position));

        if (swi.equals("off")) {
            s.setDisponibile(Sconto.disponibile.TRUE);
        }

        if (swi.equals("on")) {
            s.setDisponibile(Sconto.disponibile.FALSE);
        }

        array.set(Integer.parseInt(position), s);
        String errore = "false"; 
        try {
            ScontoDAO dao = new ScontoDAO();
            dao.updateSconto(s);
            request.getSession().setAttribute("listaSconti", array);

        } catch (NamingException ex) {
            Logger.getLogger(AbilitaScontoCNT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            messaggio = "SQL Exception";
            errore="true";
            Logger.getLogger(AbilitaScontoCNT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            messaggio = "Parse Exception";
            errore="true";
            Logger.getLogger(AbilitaScontoCNT.class.getName()).log(Level.SEVERE, null, ex);
        }

        JSONObject json = new JSONObject();
        json.put("messaggio", messaggio);
        json.put("posizione", position);
        json.put("errore", errore);
        response.getWriter().print(json.toString());
    }

}
