/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.acquistoCNT;

import database.SalaDAO;
import database.SpettacoloDAO;
import database.UtenteRegistratoDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Acquisto;
import model.Operazione;
import model.Sala;
import model.Sconto;
import model.UtenteRegistrato;

/**
 *
 * @author Michele
 */
public class AcquistoBigliettoCNT extends HttpServlet {

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
            throws ServletException, IOException, NamingException, SQLException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        
        System.out.println("Servlet acquistobigliettocnt");
        
        HttpSession s = request.getSession();
        UtenteRegistrato user =(UtenteRegistrato) s.getAttribute("user");
        SalaDAO salaDAO = new SalaDAO();
        SpettacoloDAO spettDAO = new SpettacoloDAO();
        //int numeroBiglietti = Integer.parseInt( request.getParameter("numeroBiglietti") );
        System.out.println("Numero biglietti nell servlet:"+request.getParameter("numeroBiglietti"));
        System.out.println("Id sala nella servlet:"+request.getParameter("idSala"));
        
        String email;
        int idSpettacolo = Integer.parseInt( request.getParameter("spettacoloScelto") );
        int posto = 1;  //DA FARE
        int offset = 1; //DA FARE
        Sala sala = salaDAO.foundByID( Integer.parseInt( request.getParameter("idSala") ) );
        Operazione.prenotato prenotato = null;
        Operazione.acquistato acquistato = null;
        float prezzoSpettacolo = spettDAO.foundByID(idSpettacolo).getPrezzo();
        float prezzoFinale; //DA FARE
        Calendar data = Calendar.getInstance();
        Sconto sconto = null; //DA FARE
        
        /*
        
        Sconto sconto = operazione.getSconto();
            if(sconto.getIdSconto() == 0){
                utbase.setSaldo(saldo - price + 2.0f);
            }
            if(sconto.getTipo().equals(tipo.FISSO)){
                utbase.setSaldo(saldo - sconto.getPrezzo() + 2.0f);
            }
            if(sconto.getTipo().equals(tipo.PERCENTUALE)){
               float percentuale =(float) operazione.getSconto().getPercentuale();
               float dascalare = (price * percentuale) /100;  
               utbase.setSaldo(saldo - (price-dascalare) + 2.0f);
            }
        
        */
        
        if(user.getRuolo()==UtenteRegistrato.ruolo.UTENTE){
            email = user.getEmail();
        }
        else{ //OPERATORE
            email = request.getParameter("emailUtenteBase");
        }
        Acquisto crea = new Acquisto (email, idSpettacolo,posto,offset,sala,prenotato.FALSE, acquistato.TRUE, prezzoSpettacolo, data, sconto);
        System.out.println("Acquisto creato nella servlet:\n"+crea.toString());
        
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
        try {
            processRequest(request, response);
        } catch (NamingException ex) {
            Logger.getLogger(AcquistoBigliettoCNT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AcquistoBigliettoCNT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AcquistoBigliettoCNT.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            processRequest(request, response);
        } catch (NamingException ex) {
            Logger.getLogger(AcquistoBigliettoCNT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AcquistoBigliettoCNT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AcquistoBigliettoCNT.class.getName()).log(Level.SEVERE, null, ex);
        }
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
