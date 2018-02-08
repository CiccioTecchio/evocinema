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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.UtenteRegistrato;
 
/**
 * Servlet che gestisce la modifica del profilo di un Utente.
 *
 * @author Michele
 */
public class ModificaDettagliCNT extends HttpServlet {

    /**
     * Gestisce i metodi HTTP <code>GET</code> e HTTP <code>POST</code>.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException in caso di errori specifici della Servlet
     * @throws IOException in caso di errori di I/O
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

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
        processRequest(request, response);
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
       
        processRequest(request, response);
        
        HttpSession s = request.getSession();
        UtenteRegistrato utente = (UtenteRegistrato) s.getAttribute("user");
        
        String nome = request.getParameter("nomeRegistrazioneVisualizza");   
        utente.setNome(nome);
 
        String cognome = request.getParameter("cognomeRegistrazioneVisualizza");
        utente.setCognome(cognome);
        
        String citta= request.getParameter("cittaRegistrazioneVisualizza");  
        utente.setCittà(citta);
  
        String indirizzo = request.getParameter("indirizzoRegistrazioneVisualizza");       
        utente.setIndirizzo(indirizzo);
        
        String cellulare = request.getParameter("cellulareRegistrazioneVisualizza");
        utente.setCellulare(cellulare);

         String sesso = request.getParameter("sessoRegistrazioneVisualizza");
        if(sesso.equalsIgnoreCase("maschio")){
            utente.setSesso(UtenteRegistrato.sesso.M);
        }else if(sesso.equalsIgnoreCase("femmina")){
            utente.setSesso(UtenteRegistrato.sesso.F);
        }
        
        String myData = request.getParameter("dataRegistrazioneVisualizza");
        System.out.println(myData);
               int giorno = Integer.parseInt(myData.substring(3, 5));
               int mese = Integer.parseInt(myData.substring(0, 2));
                int anno = Integer.parseInt(myData.substring(6, 10));
                Calendar data = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"),Locale.ITALY);
                data.set(anno, mese-1, giorno); 
                                    
                utente.setDataNascita(data);

                String password = (String) request.getParameter("passwordRegistrazioneVisualizza");
                if(!password.isEmpty()){
                
                    String password1 = (String) request.getParameter("passwordRegistrazione2Visualizza");
                        if( password.equals(password1)){
                            utente.setPassword(password);
                            System.out.println("le password coincidono");
                        } else {
                            System.out.println("Le password non coincidono");
                            response.getWriter().write("passworderror");

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
              case 0: response.getWriter().write("AccountVisualizzazioneAccount.jsp"); break;
              case 1: response.getWriter().write("AccountVisualizzazioneAccount.jsp"); break;
              case 2: response.getWriter().write("AccountVisualizzazioneAccount.jsp"); break;
          }
        
        
        //response.getWriter().write("AccountVisualizzazioneAccount.jsp");
    
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