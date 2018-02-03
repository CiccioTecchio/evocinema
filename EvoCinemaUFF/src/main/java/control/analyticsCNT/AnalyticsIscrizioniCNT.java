/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.analyticsCNT;

import database.UtenteRegistratoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author GiuseppeDelGaudio
 */
@WebServlet( name = "AnalyticsIscrizioniCNT" , urlPatterns = {  "/gestore/IscrizioniUtenti"  } )
public class AnalyticsIscrizioniCNT extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        doPost(request, response);
        
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
        
        response.setContentType("text/html");
        
        String json = "[\n";
        
        Date date; 
        Integer occorrenze; 
        
        try {
            
             
            UtenteRegistratoDAO dao = new UtenteRegistratoDAO();
            
            ArrayList array = dao.getIscrizioniAnalytics();
            
            int i= 0; 
            
            while( i < array.size()-2){
            
                date= (Date) array.get(i);
                i++;
                occorrenze = (int) array.get(i); 
                
                json=json+"[\n"+date.getTime()+",\n";
                json=json+occorrenze+"\n],\n";
                i++; 
            }
            
            date= (Date) array.get(i);
                i++;
                occorrenze = (int) array.get(i); 
                
                json=json+"[\n" +date.getTime()+",\n";
                json=json+occorrenze+"\n]\n";
            
        } catch (NamingException ex) {
            Logger.getLogger(AnalyticsIscrizioniCNT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AnalyticsIscrizioniCNT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AnalyticsIscrizioniCNT.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        json=json+"\n]";
        
        
        
        
        
        
        response.getWriter().print(json);
        
        
    }

  
}
