package database;

import java.sql.Connection;
import java.sql.SQLException;
import javax.ejb.Singleton;
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
          dbConnect  = dataSource.getConnection();
        }
        catch(SQLException e){
          e.printStackTrace();
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
    try{
      dbConnect = dataSource.getConnection();
    }
    catch (SQLException e1){
      e1.printStackTrace();
    }
     
    if(dbConnect == null){
      try{
        Context initContext = new InitialContext();
        Context envContext  = (Context) initContext.lookup("java:/comp/env");
        dataSource        = (DataSource) envContext.lookup("jdbc/evo_cinema");
         
        try{
          dbConnect  = dataSource.getConnection();
        }
        catch (SQLException e){
          e.printStackTrace();
        }  
      }
      catch (NamingException e){
        e.printStackTrace();
      }
    }
     
    return dbConnect;   
  }
}
