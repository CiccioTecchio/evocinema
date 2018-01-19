/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.acquistoCNT;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Sconto;
import model.Spettacolo;

/**
 *
 * @author pietr
 */
@WebServlet(name = "GestioneAcquistiCNT", urlPatterns = {"/GestioneAcquistiCNT"})
public class GestioneAcquistiCNT extends HttpServlet {

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
       
        
        
            //DATI TEMPORANEI
            ArrayList<Spettacolo> spettacoli = new ArrayList<>();

            spettacoli.add(new Spettacolo());
            spettacoli.get(0).setIdFilm(1);
            spettacoli.get(0).setTitolo("Thor");
            spettacoli.add(new Spettacolo());
            spettacoli.get(1).setIdFilm(2);
            spettacoli.get(1).setTitolo("Justice League");
            
            ArrayList<Sconto> sconti = new ArrayList<>();
            sconti.add(new Sconto(0, "sconto1", 0, 0, "", Sconto.verificabile.FALSE, 
                    Sconto.tipo.FISSO, Sconto.disponibile.TRUE, Sconto.tipologia.TERMINE));
            sconti.add(new Sconto(1, "prendi 2 paghi 1", 0, 0, "", Sconto.verificabile.FALSE, 
                    Sconto.tipo.FISSO, Sconto.disponibile.TRUE, Sconto.tipologia.TERMINE));
            
            //FILM E SCONTI SONO SEMPRE GLI STESSI, VARIANO SOLTANTO ORE E DATA    
        
            request.setAttribute("SPETTACOLI", spettacoli);
            request.setAttribute("SCONTI", sconti);
        
        
        
        
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
