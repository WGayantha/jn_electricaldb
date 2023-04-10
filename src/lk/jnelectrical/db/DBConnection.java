
package lk.jnelectrical.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author ishan
 */
public class DBConnection {
    
    public Connection getConnection()throws SQLException{
        
        String url = "jdbc:mysql://localhost:3306/jnelectricaldb";
        String user = "root";
        String password = "root@123";
        
        return DriverManager.getConnection(url,user,password);
        
        
    }

    
    
    
}
