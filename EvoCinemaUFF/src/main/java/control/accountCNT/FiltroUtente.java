/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.accountCNT;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.UtenteRegistrato;

/**
 *
 * @author Angelo
 */
public class FiltroUtente implements Filter {
    
    
    
    public FiltroUtente() {
    }    

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
        String config = filterConfig.getInitParameter("test-param");
                
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
     
          
           UtenteRegistrato user = (UtenteRegistrato) ( (HttpServletRequest) request).getSession().getAttribute("user"); 
           
           if( user != null  ){
           
         
               if(user.getRuolo() == UtenteRegistrato.ruolo.UTENTE) chain.doFilter(request, response);
               else ((HttpServletResponse)response).sendRedirect("../index.jsp");
            
           }else  ((HttpServletResponse)response).sendRedirect("../Login.jsp");
       
    }

    @Override
    public void destroy() {
       
        
    }
    
}
