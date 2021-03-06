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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Gestore;
import model.UtenteRegistrato;

/**
 * Servlet che gestisce la registrazione di un nuovo gestore.
 *
 * @author Michele
 */
@WebServlet(name = "RegistrazioneGestoreCNT", urlPatterns = {"/gestore/RegistrazioneGestoreCNT"})
public class RegistrazioneGestoreCNT extends HttpServlet {

    private static final long serialVersionUID = 1L;

    UtenteRegistratoDAO model = null;

    public RegistrazioneGestoreCNT() {
        super();
    }

    /**
     * Gestisce il metodo HTTP <code>GET</code>.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException in caso di errori specifici della Servlet
     * @throws IOException in caso di errori di I/O
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Gestisce il metodo HTTP <code>POST</code>.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException in caso di errori specifici della Servlet
     * @throws IOException in caso di errori di I/O
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            model = new UtenteRegistratoDAO();
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(RegistrazioneCNT.class.getName()).log(Level.SEVERE, null, ex);
        }

        HttpSession s = request.getSession();
        boolean aggiunto = false;

        Gestore u = new Gestore();

        String ruolo = ((String) request.getParameter("ruoloRegistrazione"));
        if (ruolo.equalsIgnoreCase("gestore")) {
            u.setRuolo(UtenteRegistrato.ruolo.GESTORE);
        } else if (ruolo.equalsIgnoreCase("operatore")) {
            u.setRuolo(UtenteRegistrato.ruolo.OPERATORE);
        }

        u.setNome((String) request.getParameter("nomeRegistrazione"));
        u.setCognome((String) request.getParameter("cognomeRegistrazione"));

        String myData = request.getParameter("dataRegistrazione");
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"));
        
        try {
           Date date = sdf.parse(myData);
           cal.setTime(date);
        } catch (ParseException ex) {
            Logger.getLogger(RegistrazioneGestoreCNT.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        

        u.setDataNascita(cal);

        u.setCellulare((String) request.getParameter("cellulareRegistrazione"));
        u.setCittà((String) request.getParameter("cittaRegistrazione"));
        u.setEmail((String) request.getParameter("emailRegistrazione"));
        u.setIndirizzo((String) request.getParameter("indirizzoRegistrazione"));

        String sesso = request.getParameter("sessoRegistrazione");
        if (sesso.equalsIgnoreCase("maschio")) {
            u.setSesso(UtenteRegistrato.sesso.M);
        } else if (sesso.equalsIgnoreCase("femmina")) {
            u.setSesso(UtenteRegistrato.sesso.F);
        }

        u.setNomeUtente((String) request.getParameter("userRegistrazione"));
        String password = (String) request.getParameter("passwordRegistrazione");
        String password1 = (String) request.getParameter("password1Registrazione");
        //if(password.equals(password1)){
        u.setPassword(password);
        /* } else {
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("../admin/AggiungiOperatore.jsp");
                    dispatcher.forward(request, response);
                }           */

        try {
            aggiunto = model.createUtenteRegistrato(u);

        } catch (SQLException | ParseException | NamingException ex) {
            Logger.getLogger(RegistrazioneCNT.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (aggiunto != true) {
            aggiunto = false;
        }
        s.setAttribute("accountRegistrato", aggiunto);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/gestore/AggiungiOperatore.jsp");

    }

}
