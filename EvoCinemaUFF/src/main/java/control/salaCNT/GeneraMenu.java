/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.salaCNT;

import database.SalaDAO;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import model.Sala;

/**
 *
 * @author luca
 */
public class GeneraMenu {
    public static List<Sala> getSale(){
        try {
            SalaDAO salaDao = new SalaDAO();
            return salaDao.getAllSale();
        } catch (NamingException ex) {
            Logger.getLogger(GeneraMenu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GeneraMenu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(GeneraMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
