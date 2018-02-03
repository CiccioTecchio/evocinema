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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.UtenteBase;
import model.UtenteRegistrato;

/**
 *
 * @author Michele
 */
public class ModificaDettagliCNT extends HttpServlet {

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
        
        HttpSession s = request.getSession();
        UtenteRegistrato utente = (UtenteRegistrato) s.getAttribute("user");

        
        
        String nome = request.getParameter("modificaNome");
        utente.setNome(nome);
        String cognome = request.getParameter("modificaCognome");
        utente.setCognome(cognome);
        
        String città = request.getParameter("modificaCitta");
        utente.setCittà(città);
               
        
        
        String indirizzo = request.getParameter("modificaIndirizzo");
        utente.setIndirizzo(indirizzo);
        
        String cellulare = request.getParameter("modificaCellulare");
        utente.setCellulare(cellulare);
        
        
        String sesso = request.getParameter("modificaSesso");
        if(sesso.equalsIgnoreCase("maschio")){
            utente.setSesso(UtenteRegistrato.sesso.M);
        }else if(sesso.equalsIgnoreCase("femmina")){
            utente.setSesso(UtenteRegistrato.sesso.F);
        }
        
        String myData = request.getParameter("modificaData");
        System.out.println(myData);
               int giorno = Integer.parseInt(myData.substring(3, 5));
               int mese = Integer.parseInt(myData.substring(0, 2));
                int anno = Integer.parseInt(myData.substring(6, 10));
                Calendar data = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"),Locale.ITALY);
                data.set(anno, mese-1, giorno); 
               
                            
                utente.setDataNascita(data);
        
                
        
                //utente.setNomeUtente((String) request.getParameter("modificaUser"));
                String password = (String) request.getParameter("modificaPassword");
                if(!password.isEmpty()){
                
                    String password1 = (String) request.getParameter("modificaPassword1");
                    if( password.equals(password1)){
                        utente.setPassword(password);
                        System.out.println("le password coincidono");
                    } else {
                        s.setAttribute("passwordCoincidono", "false");
                        System.out.println("le password non coincidono");
                        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("AccountVisualizzazioneAccount.jsp");
                        dispatcher.forward(request, response);
                    }
                }
        
        
        try {
            UtenteRegistratoDAO dao = new UtenteRegistratoDAO();
            dao.updateUtenteRegistrato(utente);
            
            
        } catch (NamingException | SQLException | ParseException ex) {
            Logger.getLogger(CancellazioneAccountCNT.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        s.removeAttribute("user");
        s.setAttribute("user", utente);
        
        String page="";
        
        int ruolo = utente.getRuolo().ordinal();
          switch (ruolo){
              case 0 : page+="utente/AccountVisualizzazioneAccount.jsp";
              case 1: page+="operatore/AccountVisualizzazioneAccount.jsp";
              case 2: page+="gestore/AccountVisualizzazioneAccount.jsp";
        }
        
        
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
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
