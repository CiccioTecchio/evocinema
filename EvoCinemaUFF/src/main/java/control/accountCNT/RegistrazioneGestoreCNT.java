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
import java.util.Calendar;
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
 *
 * @author Michele
 */
@WebServlet(name="RegistrazioneGestoreCNT", urlPatterns={"/gestore/RegistrazioneGestoreCNT"})
public class RegistrazioneGestoreCNT extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    
    UtenteRegistratoDAO model = null;
    
     public RegistrazioneGestoreCNT() {
         super();
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
                model = new UtenteRegistratoDAO();
            } catch (NamingException | SQLException ex) {
                Logger.getLogger(RegistrazioneCNT.class.getName()).log(Level.SEVERE, null, ex);
            }

            HttpSession s = request.getSession();
            boolean aggiunto=false;

           
            
            Gestore u = new Gestore() ;
            
            String ruolo=((String)request.getParameter("ruoloRegistrazione"));
                if(ruolo.equalsIgnoreCase("gestore")){
                    u.setRuolo(UtenteRegistrato.ruolo.GESTORE);
                }else if(ruolo.equalsIgnoreCase("operatore")){
                    u.setRuolo(UtenteRegistrato.ruolo.OPERATORE);
                }
                

                u.setNome((String) request.getParameter("nomeRegistrazione"));
                u.setCognome((String) request.getParameter("cognomeRegistrazione"));
                
                
                
                    

                String myData = request.getParameter("dataRegistrazione");
                
                int giorno = Integer.parseInt(myData.substring(3, 5));
                int mese = Integer.parseInt(myData.substring(0, 2))-1 ;
                int anno = Integer.parseInt(myData.substring(6, 10));
                Calendar data = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"),Locale.ITALY);
                data.set(anno, mese, giorno);                

                u.setDataNascita(data);

                u.setCellulare((String) request.getParameter("cellulareRegistrazione"));
                u.setCittà((String) request.getParameter("cittaRegistrazione"));
                u.setEmail((String) request.getParameter("emailRegistrazione"));
                u.setIndirizzo((String) request.getParameter("indirizzoRegistrazione"));


                String sesso = request.getParameter("sessoRegistrazione");
                if(sesso.equalsIgnoreCase("maschio")){
                    u.setSesso(UtenteRegistrato.sesso.M);
                }else if(sesso.equalsIgnoreCase("femmina")){
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
                    aggiunto=model.createUtenteRegistrato(u);
                    
                } catch (SQLException | ParseException | NamingException ex) {
                    Logger.getLogger(RegistrazioneCNT.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if(aggiunto!=true){
                    aggiunto=false;
                }
                s.setAttribute("accountRegistrato", aggiunto);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/gestore/AggiungiOperatore.jsp");
                

                
    }

   

}
