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
 *
 * @author GiuseppeDelGaudio
 */
@WebServlet( name ="uploadLocandina" , 
			 urlPatterns = { "/admin/uploadLocandina" })
@MultipartConfig ( fileSizeThreshold = 1024 * 1024 * 2 , // Threshold  1mb
						
					maxFileSize = 1024*1024*5, //  5mb file max size 
					maxRequestSize = 1024*1024*50 ) // 50 mb max server
		
public class uploadLocandina extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		PrintWriter out = response.getWriter(); 
		response.setContentType("text/plain");
		
		out.write("Il metodo get non può essere utilizzato per l'Upload");
		out.close(); 
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("application/json");
		String pathServer = request.getServletContext().getRealPath("")+"images"+File.separator+"locandine"; 
                System.out.println("path server-->"+pathServer);
		File fileSave = new File(pathServer);
		String nameFile="vuoto"; 
                
                String action = (String) request.getParameter("action");
                
                if( action.equals("change") ){
                
                    String old = (String) request.getParameter("old");
            
                    String namefile = old.substring(old.lastIndexOf("/")+1);
                    String path =  request.getServletContext().getRealPath("")+"images"+File.separator+"locandine"+File.separator+namefile;
                    System.out.println("la path del file è -->"+path);
                    File oldFile = new File(path); 
                    
                    if(oldFile.exists()) oldFile.delete(); 
                
                }
		
		try{
		
		if( ! fileSave.exists() ) { fileSave.mkdir(); }
		
		
	    
		
		if( request.getParts() != null && request.getParts().size() > 0 ) {
			
			Part part = request.getPart("locandina"); 
			
			System.out.println("lungezza parti "+part.getContentType()+part.getName());
			
			nameFile = this.extractFileName(part);
			System.out.println("Nome File --> "+nameFile);
			part.write(pathServer+File.separator+nameFile );
			System.out.println("Nome inseriemtn ..."+pathServer+File.separator+nameFile);
           
			JSONObject json = new JSONObject();
                        
                        json.put("nomeFile", nameFile); 
                        
                        System.out.println("Ho creato il json " + json.toString() );
			
                        response.getWriter().print(json.toString());
			
		}else {
			
			                 System.err.println("Input null");
			
		}
		
		}catch(IOException e){
			
			e.printStackTrace();
			
			
		}
		
		
		
		
		
                
		
		
		
	}
	
	private String extractFileName(Part part) {
		
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}
	

}
