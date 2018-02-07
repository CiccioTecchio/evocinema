/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.accountCNT;

import database.UtenteRegistratoDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.UtenteBase;

/**
 * Servlet che gestisce la funzione di recupero password.
 *
 * @author Michele
 */
public class RecuperoPasswordCNT extends HttpServlet {

    /**
     * Gestisce i metodi HTTP <code>GET</code> e HTTP <code>POST</code>.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException in caso di errori specifici della Servlet
     * @throws IOException in caso di errori di I/O
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    /**
     * Gestisce il metodo HTTP <code>GET</code>.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException in caso di errori specifici della Servlet
     * @throws IOException in caso di errori di I/O
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Gestisce il metodo HTTP <code>POST</code>.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException in caso di errori specifici della Servlet
     * @throws IOException in caso di errori di I/O
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        HttpSession s = request.getSession();
        String email = (String) request.getParameter("emailRecupero");

        UtenteBase utente = null;
        String page = "";

        //restituisce l'oggetto utente la cui email è stata passata alla servlet per il rcupero password
        try {
            UtenteRegistratoDAO model = new UtenteRegistratoDAO();
            utente = (UtenteBase) model.foundUtenteBaseByEmail(email);
            System.out.print("account associato: " + utente);

        } catch (NamingException | SQLException | ParseException ex) {
            Logger.getLogger(RecuperoPasswordCNT.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (utente != null) {
            //Generazione della nuova password da inviare via email
            String caratteri = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
            StringBuilder salt = new StringBuilder();
            Random rnd = new Random();
            while (salt.length() < 10) { // length of the random string.
                int index = (int) (rnd.nextFloat() * caratteri.length());
                salt.append(caratteri.charAt(index));
            }
            String password = salt.toString();

            //Set della nuova password
            utente.setPassword(password);

            try {
                UtenteRegistratoDAO model = new UtenteRegistratoDAO();

                model.updateUtenteBase(utente);

                System.out.print("account aggiornato: " + utente);

            } catch (NamingException | SQLException | ParseException ex) {
                Logger.getLogger(RecuperoPasswordCNT.class.getName()).log(Level.SEVERE, null, ex);
            }

            //Invio della password via email
            String host = "smtp.gmail.com";
            String user = "evocinema2018@gmail.com";
            String pass = "cicciotecchio";
            String to = email;
            String from = "evocinema2018@gmail.com";
            String subject = "Evocinema - Recupero password";
            String messageText = "La tua nuova password associata a questo indirizzo email è: " + password;

            try {
                //create an instance of Properties Class   
                Properties props = System.getProperties();

                /* Specifies the IP address of your default mail server
                       for e.g if you are using gmail server as an email sever
                       you will pass smtp.gmail.com as value of mail.smtp host. 
                       As shown here in the code. 
                       Change accordingly, if your email id is not a gmail id
                 */
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.host", host);
                props.put("mail.smtp.port", "587");
                props.put("mail.smtp.user", user);

                props.put("mail.smtp.debug", "true");

                java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

                /* Pass Properties object(props) and Authenticator object   
                       for authentication to Session instance 
                 */
                Session mailSession = Session.getDefaultInstance(props, null);

                mailSession.setDebug(true);

                /* Create an instance of MimeMessage, 
                          it accept MIME types and headers 
                 */
                MimeMessage message = new MimeMessage(mailSession);

                message.setFrom(new InternetAddress(from));

                InternetAddress address = (new InternetAddress(to));

                message.setRecipient(Message.RecipientType.TO, address);

                message.setSubject(subject);

                message.setSentDate(new Date());

                message.setText(messageText);
                /* Transport class is used to deliver the message to the recipients */
                Transport transport;

                transport = mailSession.getTransport("smtp");

                transport.connect(host, user, pass);
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();

            } catch (MessagingException e) {
                e.printStackTrace();
            }

            s.setAttribute("emailNonValida", false);
            page = "/ControllaEmail.jsp";

        } else {
            s.setAttribute("emailNonValida", true);
            page = "/RecuperoPassword.jsp";
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);

    }

}
