/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.acquistoCNT;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            throws ServletException, IOException {
        
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        /*RESTITUIAMO GLI ORARI DELLO SPETTACOLO SELEZIONATO*/
        String idSpettacolo = request.getParameter("idSpettacolo");
        String oraSpettacolo = request.getParameter("oraSpettacolo");
        JSONArray jsonArray = new JSONArray();
        //QUERY DAO PER REPERIRE GLI ORARI DELLO SPETACOLO IN BASE ALL'ID 
        
        
        //jsonArray.put(0, "03/01/18");
        if(idSpettacolo.equals("1"))
            jsonArray.put(0, "04/01/18");
        
        if(idSpettacolo.equals("2"))
            jsonArray.put(0, "05/01/18");
             
          
        //System.out.println("JSON "+jsonArray.toString());
        
        response.getWriter().write(jsonArray.toString());
        
        
        
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
