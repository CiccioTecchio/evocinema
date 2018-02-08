/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.accountCNT;

import database.UtenteRegistratoDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import model.UtenteBase;
import model.UtenteRegistrato;

/**
 * Servlet che gestisce la registrazione di un nuovo UtenteBase.
 *
 * @author Antonio
 */
@WebServlet(name = "RegistrazioneCNT", urlPatterns = {"/RegistrazioneCNT"})
public class RegistrazioneCNT extends HttpServlet {

    private static final long serialVersionUID = 1L;

    UtenteRegistratoDAO model = null;

    public RegistrazioneCNT() {
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

        String username = (String) request.getParameter("userRegistrazione");
        String email = (String) request.getParameter("emailRegistrazione");
        
        Boolean flag = null;
        try {
            flag = model.controllaUtente(username);
        } catch (SQLException ex) {
            Logger.getLogger(RegistrazioneCNT.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Boolean flag1=null;
        try {
            flag1 = model.controllaEmailUtente(email);
        } catch (SQLException ex) {
            Logger.getLogger(RegistrazioneCNT.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        if(flag1){
            s.setAttribute("emailInvalida", true);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("../Registrazione.jsp");
            dispatcher.forward(request, response);
        }else{
            if (flag) {
                s.setAttribute("userInvalido", true);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("../Registrazione.jsp");
                dispatcher.forward(request, response);


                } else if(!flag1 && !flag){
                   
                    s.setAttribute("userInvalido", false);
                    UtenteBase u = new UtenteBase();

                    u.setNome((String) request.getParameter("nomeRegistrazione"));
                    u.setCognome((String) request.getParameter("cognomeRegistrazione"));

                    String myData = request.getParameter("dataRegistrazione");


                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"));


                    u.setCellulare((String) request.getParameter("cellulareRegistrazione"));
                    u.setCittà((String) request.getParameter("cittaRegistrazione"));
                    u.setEmail((String) request.getParameter("emailRegistrazione"));
                    u.setIndirizzo((String) request.getParameter("indirizzoRegistrazione"));
                    u.setSaldo(0);


                    String sesso = request.getParameter("sessoRegistrazione");
                    if(sesso.equalsIgnoreCase("maschio")){
                        u.setSesso(UtenteRegistrato.sesso.M);
                    }else if(sesso.equalsIgnoreCase("femmina")){
                        u.setSesso(UtenteRegistrato.sesso.F);
                    }

                    u.setRuolo(UtenteRegistrato.ruolo.UTENTE);

                    u.setNomeUtente((String) request.getParameter("userRegistrazione"));
                    String password = (String) request.getParameter("passwordRegistrazione");
                    String password1 = (String) request.getParameter("password1Registrazione");
                    if(password.equals(password1)){
                        u.setPassword(password);                    
                    } else {
                        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Registrazione.jsp");
                        dispatcher.forward(request, response);
                    }               



                    try {

                        Date date = sdf.parse(myData);

                        cal.setTime(date);

                        u.setDataNascita(cal);
                        model.createUtenteRegistrato(u);

                        s.setAttribute("user", u);

                    } catch (SQLException | ParseException | NamingException ex) {
                        Logger.getLogger(RegistrazioneCNT.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    response.getWriter().write("index.jsp"); 

                }
            }
    }

}
