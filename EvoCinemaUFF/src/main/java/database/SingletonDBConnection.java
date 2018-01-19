package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author sarad
 */
public class SingletonDBConnection {
    
    
    
    private static SingletonDBConnection singleInstance;
    private static DataSource dataSource;
    private static Connection dbConnect;

   
    private SingletonDBConnection() throws NamingException, SQLException{
      try{
        Context initContext = new InitialContext();
        Context envContext  = (Context) initContext.lookup("java:/comp/env");
        dataSource       = (DataSource) envContext.lookup("jdbc/evo_cinema");

        try{
        Class.forName("com.mysql.jdbc.Driver"); 
        String url2 = "jdbc:mysql://evocinema.cddgmzg8k9r4.us-west-2.rds.amazonaws.com:3306/evo_cinema?user=user&password=pippofranco";
        dbConnect = DriverManager.getConnection(url2);
        if (dbConnect != null) {
            System.out.println("Connected to the database evocinema");
        }
        }
        catch(SQLException e){
          e.printStackTrace();
        } catch (ClassNotFoundException ex) {  
              Logger.getLogger(SingletonDBConnection.class.getName()).log(Level.SEVERE, null, ex);
          }  
      }
      catch (NamingException e)
      {
        e.printStackTrace();
      }
    }
   
  public static SingletonDBConnection getInstance() throws NamingException, SQLException
  {
    if(singleInstance == null)
    {
      synchronized (SingletonDBConnection.class)
      {
        if(singleInstance == null)
        {
          singleInstance = new SingletonDBConnection();
        }
      }
    }
 
    return singleInstance;
  }
   
  public static Connection getConnInst() throws SQLException{
 
    if(dbConnect == null){
      try{
        Context initContext = new InitialContext();
        Context envContext  = (Context) initContext.lookup("java:/comp/env");
        dataSource        =  (DataSource) envContext.lookup("jdbc/evo_cinema");
         
        try{
        Class.forName("com.mysql.jdbc.Driver"); 
        String url2 = "jdbc:mysql://evocinema.cddgmzg8k9r4.us-west-2.rds.amazonaws.com:3306/evo_cinema?user=user&password=pippofranco";
        dbConnect = DriverManager.getConnection(url2);

        }
        catch (SQLException e){
          e.printStackTrace();
        } catch (ClassNotFoundException ex) {  
              Logger.getLogger(SingletonDBConnection.class.getName()).log(Level.SEVERE, null, ex);
          }  
      }
      catch (NamingException e){
        e.printStackTrace();
      }
    }
    return dbConnect;   
  }
}
