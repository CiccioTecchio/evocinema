/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.acquistoCNT;

import database.ScontoDAO;
import database.SpettacoloDAO;
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
 *
 * @author pietr
 */
@WebServlet(name = "JSONCalcoloPrezzoTotale", urlPatterns = {"/JSONCalcoloPrezzoTotale"})
public class JSONCalcoloPrezzoTotale extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ParseException, NamingException {
        response.setContentType("text/html;charset=UTF-8");
       
         response.setContentType("application/json;charset=UTF-8");
        //PrintWriter out = response.getWriter();
        String idSconti = ""+request.getParameter("stringaIdSconti");
        int idSpettacolo = Integer.parseInt(request.getParameter("spettacoloScelto"));
        
        String operazione = request.getParameter("operazione");
        
        
        SpettacoloDAO sdao= new SpettacoloDAO();
        ScontoDAO scdao= new ScontoDAO();
        
        float prezzoBiglietto = sdao.foundByID(idSpettacolo).getPrezzo();
        float prezzoTotale=0;
        int idScontoAttuale=0;
        Sconto sc = new Sconto();
        
        if(operazione.equals("Acquista")){
            System.out.println("Acquisto");
        while(!idSconti.equals("")){
            idScontoAttuale = Integer.parseInt(idSconti.substring(0,idSconti.indexOf('-')));
            idSconti=idSconti.substring(idSconti.indexOf('-')+1);
            
            if(idScontoAttuale==0) 
                prezzoTotale+=prezzoBiglietto; //SE NESSUNO SCONTO E' STATO SELEZIONATO
            else{
                sc=scdao.foundByID(idScontoAttuale);
                if(sc.getTipo()==Sconto.tipo.FISSO)
                    prezzoTotale+=sc.getPrezzo();
                else {
                    prezzoTotale+=(prezzoBiglietto*(1-((float)sc.getPercentuale()/100)));
                }
            }
        
        }
        }
        else{
            System.out.println("Prenotazione");
            while(!idSconti.equals("")){
            idSconti=idSconti.substring(idSconti.indexOf('-')+1);
            prezzoTotale+=2;
            }
        }
        
        JSONObject jsonObject = new JSONObject();
        
        jsonObject.put(prezzoTotale+"", 0);
        
        response.getWriter().write(jsonObject.toString());
         /*
        PrintWriter out=response.getWriter();
        
        request.setAttribute("Title", "Gestione acquisto");*/
        

        //RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/gestioneOperazioniSala.jsp");
	//		dispatcher.forward(request,response);
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
        } catch (SQLException ex) {
            Logger.getLogger(GestioneAcquistiCNT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(GestioneAcquistiCNT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(GestioneAcquistiCNT.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException ex) {
            Logger.getLogger(GestioneAcquistiCNT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(GestioneAcquistiCNT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(GestioneAcquistiCNT.class.getName()).log(Level.SEVERE, null, ex);
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
