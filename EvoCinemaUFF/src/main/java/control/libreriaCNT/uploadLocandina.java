/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.libreriaCNT;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.json.JSONObject; 



/**
 * La classe definisce la logica che permette l'upload di una locandina sul Server
 * @author GiuseppeDelGaudio
 */
@WebServlet( name ="uploadLocandina" , 
			 urlPatterns = { "/gestore/uploadLocandina" })
@MultipartConfig ( fileSizeThreshold = 1024 * 1024 * 2 , // Threshold  1mb
						
					maxFileSize = 1024*1024*5, //  5mb file max size 
					maxRequestSize = 1024*1024*50 ) // 50 mb max server
		
public class uploadLocandina extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
        /**
     * Gestione metodo HTTP <code>GET</code>
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException se la servlet lancia errori generici
     * @throws IOException se vengono lanciati errori IO
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		PrintWriter out = response.getWriter(); 
		response.setContentType("text/plain");
		
		out.write("Il metodo get non può essere utilizzato per l'Upload");
		out.close(); 
	}

        /**
     * Gestione metodo HTTP <code>POST</code>
     * 
     * Viene effettuato l'upload della locandina sul server, 
     * successivamente viene restituito un JSON che all'indice "nomeFile" contiene il nome 
     * della locandina caricata. Le locandine sono caricate in ../images/locandine.
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException se la servlet lancia errori generici
     * @throws IOException se vengono lanciati errori IO
     * 
     */


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                String browserType = request.getHeader("User-Agent");
            
		response.setContentType("application/json");
		String pathServer = request.getServletContext().getRealPath("")+"images"+File.separator+"locandine"; 
		File fileSave = new File(pathServer);
		String nameFile="vuoto"; 
                
                String action = (String) request.getParameter("action");
                
                
                
                if( action.equals("change") ){
                
                    String old = (String) request.getParameter("old");
            
                    String namefile = old.substring(old.lastIndexOf("/")+1);
                    String path =  request.getServletContext().getRealPath("")+"images"+File.separator+"locandine"+File.separator+namefile;

                    File oldFile = new File(path); 
                    
                    if(oldFile.exists()) oldFile.delete(); 
                
                }
		
		try{
		
		if( ! fileSave.exists() ) { fileSave.mkdir(); }
		
		
	    
		
		if( request.getParts() != null && request.getParts().size() > 0 ) {
			
			Part part = request.getPart("locandina"); 
						
			nameFile = this.extractFileName(part , browserType );

                        part.write(pathServer+File.separator+nameFile );
           
			JSONObject json = new JSONObject();
                        
                        json.put("nomeFile", nameFile); 
                        
			
                        response.getWriter().print(json.toString());
			
		}else {
			
			                 System.err.println("Input null");
			
		}
		
		}catch(IOException e){
			
			e.printStackTrace();
			
			
		}
			
		
	}
	
	private String extractFileName(Part part , String broswer) {
		
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
                    
			if (s.trim().startsWith("filename")) {
                                if( broswer.contains("Edge") || broswer.contains("Trident") ){ return s.substring(s.lastIndexOf(File.separator)+1 , s.length()-1 ); }
                                else return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}
	

}
