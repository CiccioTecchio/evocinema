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
import model.UtenteBase;
import model.UtenteRegistrato;

/**
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

            String username = (String) request.getParameter("userRegistrazione");

            Boolean flag = null;
            try {
                flag = model.controllaUtente(username);
            } catch (SQLException ex) {
                Logger.getLogger(RegistrazioneCNT.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (flag) {
                s.setAttribute("registrazioneImpossibile", true);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Registrazione.jsp");
                dispatcher.forward(request, response);
            } else {
                s.removeAttribute("registrazioneImpossibile");
                UtenteBase u = new UtenteBase();

                u.setNome((String) request.getParameter("nomeRegistrazione"));
                u.setCognome((String) request.getParameter("cognomeRegistrazione"));

                String myData = request.getParameter("dataRegistrazione");
                int anno = Integer.parseInt(myData.substring(0, 4));
                int mese = Integer.parseInt(myData.substring(5, 7)) - 1;
                int giorno = Integer.parseInt(myData.substring(8, 10));
                Calendar data = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"),Locale.ITALY);
                data.set(anno, mese, giorno);                

                u.setDataNascita(data);

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
                u.setPassword((String) request.getParameter("passwordRegistrazione"));

                s.setAttribute("utenteRegistrazione", u);
                
                try {
                    model.createUtenteRegistrato(u);
                } catch (SQLException ex) { 
                    Logger.getLogger(RegistrazioneCNT.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(RegistrazioneCNT.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NamingException ex) {
                    Logger.getLogger(RegistrazioneCNT.class.getName()).log(Level.SEVERE, null, ex);
                }

                RequestDispatcher dispatcher = getServletContext()
                        .getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
            }

        }

        

    }
       
    

