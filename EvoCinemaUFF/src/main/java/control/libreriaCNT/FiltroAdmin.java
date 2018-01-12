/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.libreriaCNT;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author GiuseppeDelGaudio
 */
public class FiltroAdmin implements Filter {
    
    
    
    public FiltroAdmin() {
    }    

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
        String config = filterConfig.getInitParameter("test-param");
        
        System.out.println(config);
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
     
        
        String par = request.getParameter("p"); 
       
        if(par == null ) par = "null"; 
        
        if (  par.equals("passa") ){
        
            System.out.println("Sono dentro al filtro");
            
            
            chain.doFilter(request, response);
            
            
            
        }else{
        
           ((HttpServletResponse) response).sendRedirect("http://localhost:8080/EvoCinemaUFF/VisualizzazioneProgrammazione.jsp");
        
        }
        
    }

    @Override
    public void destroy() {
       
        
    }
    
}