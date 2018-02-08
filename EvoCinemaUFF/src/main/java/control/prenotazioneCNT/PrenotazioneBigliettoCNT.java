/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.prenotazioneCNT;

import database.OperazioneDAO;
import database.SalaDAO;
import database.ScontoDAO;
import database.SpettacoloDAO;
import database.UtenteRegistratoDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import static java.time.LocalDate.now;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Operazione;
import model.Prenotazione;
import model.Sala;
import model.Sconto;
import model.Spettacolo;
import model.UtenteBase;
import model.UtenteRegistrato;

/**
 *
 * @author giuseppeapuzzo
 * La classe definisce la logica che permette l'aggiunta di un oggetto di tipo {@link Prenotazione}
 */
public class PrenotazioneBigliettoCNT extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     * il metodo preleva dalla sessione l'email dell'utente e la coppia posto-sconto selezionati nella pagina precedente
     * crea una Prenotazione con i dati del form precedente e la rende persistente sul database.
     * Reindirizza l'utente alla visualizzazione delle prenotazioni effettuate dal suo account
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NamingException, SQLException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        System.out.println("Servlet prenotazioni cnt");
        HttpSession s = request.getSession();
        UtenteRegistrato user =(UtenteRegistrato) s.getAttribute("user");
        UtenteRegistratoDAO utDAO = new UtenteRegistratoDAO();
        String email = user.getEmail();
        
        UtenteBase utbase = utDAO.foundUtenteBaseByEmail(email);
        SalaDAO salaDAO = new SalaDAO();
        SpettacoloDAO spettDAO = new SpettacoloDAO();
        String PostiSconti = request.getParameter("stringaPostiESconti");
        int idSpettacolo = Integer.parseInt( request.getParameter("spettacoloScelto") );
        Spettacolo spettacolo = spettDAO.foundByID(idSpettacolo);
        String MatricePosti = spettacolo.getMatricePosti();
        Sala sala = salaDAO.foundByID( Integer.parseInt( request.getParameter("idSala") ) );
        int offset = Integer.parseInt(s.getAttribute("offset").toString()) / sala.getNumeroPosti();
        Operazione.prenotato prenotato = null;
        Operazione.acquistato acquistato = null;
        float prezzoSpettacolo = spettDAO.foundByID(idSpettacolo).getPrezzo();
        float prezzoFinale = 0; //DA FARE
        Calendar data = Calendar.getInstance();
        ScontoDAO scontoDAO = new ScontoDAO(); 
        char [] ArrayPosti = MatricePosti.toCharArray();
        while(!PostiSconti.equals("")){
            int posto = Integer.parseInt(PostiSconti.substring(0, PostiSconti.indexOf("-")));
            PostiSconti = PostiSconti.substring(PostiSconti.indexOf("-")+1);
            int Idsconto = Integer.parseInt(PostiSconti.substring(0, PostiSconti.indexOf("-")));
            PostiSconti = PostiSconti.substring(PostiSconti.indexOf("-")+1);
            Sconto sconto = scontoDAO.foundByID(Idsconto);

            float saldo = utbase.getSaldo();
                //Scalo il credito all'utente
            if(Idsconto == 41){ //Se non ci sono sconti
                prezzoFinale = prezzoSpettacolo;
                utbase.setSaldo( saldo - prezzoSpettacolo );
            }else{
                if(sconto.getTipo().equals(Sconto.tipo.FISSO) ){  //Se lo sconto e fisso
                    prezzoFinale = sconto.getPrezzo();
                }
                if(sconto.getTipo().equals(Sconto.tipo.PERCENTUALE) ){
                    float percentuale =(float) sconto.getPercentuale();
                    float dascalare = (prezzoSpettacolo * percentuale) /100;  
                    prezzoFinale = prezzoSpettacolo-dascalare;
                }
            }
            utbase.setSaldo(saldo - 2.0f);
            Prenotazione crea = new Prenotazione (email, idSpettacolo,posto,offset,sala,prenotato.TRUE, acquistato.FALSE, prezzoFinale, data, sconto);
            OperazioneDAO opDAO = new OperazioneDAO();
            opDAO.createOperazione(crea);
            utDAO.updateUtenteBase(utbase);
            s.removeAttribute("user");
            s.setAttribute("user", utbase);
            
            //Aggiornare lo stao del posto
            
            int daCambiare = ( offset * sala.getNumeroPosti() ) + posto ; 
            ArrayPosti[daCambiare] = 'p';
            
        }
        String newPosti = "";
        for(int i=0; i<ArrayPosti.length; i++){
            newPosti += (ArrayPosti[i]);
        }
        spettacolo.setMatricePosti(newPosti);
        spettDAO.updateSpettacolo(spettacolo);
        response.sendRedirect("VisualizzaPrenotazioni.jsp");
        
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
            Logger.getLogger(PrenotazioneBigliettoCNT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PrenotazioneBigliettoCNT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(PrenotazioneBigliettoCNT.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(PrenotazioneBigliettoCNT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PrenotazioneBigliettoCNT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(PrenotazioneBigliettoCNT.class.getName()).log(Level.SEVERE, null, ex);
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
