/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.acquistoCNT;

import database.SpettacoloDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Spettacolo;
import org.json.JSONArray;

/**
 *
 * @author pietr
 */
@WebServlet(name = "JSONDataSpettacolo", urlPatterns = {"/JSONDataSpettacolo"})
public class JSONDataSpettacolo extends HttpServlet {

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
            throws ServletException, IOException, NamingException, SQLException {
        
        response.setContentType("application/json;charset=UTF-8");/*
        PrintWriter out = response.getWriter();
        /*RESTITUIAMO GLI ORARI DELLO SPETTACOLO SELEZIONATO
        String idOpera = request.getParameter("idOpera");
        String oraSpettacolo = request.getParameter("oraSpettacolo");
        JSONArray jsonArray = new JSONArray();
        QUERY DAO PER REPERIRE GLI ORARI DELLO SPETACOLO IN BASE ALL'ID 
        SpettacoloDAO sdao=new SpettacoloDAO();
        List<Spettacolo> spettacoli = sdao.foundByOpera(Integer.parseInt(idOpera));
        
        Logger logger=Logger.getLogger("global");
        for(Spettacolo s : spettacoli){
            logger.info("data "+s.get);
            
        }
        
        jsonArray.put(0, "03/01/18");
        if(idSpettacolo.equals("1"))
            jsonArray.put(0, "04/01/18");
        
        if(idSpettacolo.equals("2"))
            jsonArray.put(0, "05/01/18");
             
          
        System.out.println("JSON "+jsonArray.toString());
        
        response.getWriter().write(jsonArray.toString());
        */
        
        
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
            Logger.getLogger(JSONDataSpettacolo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JSONDataSpettacolo.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(JSONDataSpettacolo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JSONDataSpettacolo.class.getName()).log(Level.SEVERE, null, ex);
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
