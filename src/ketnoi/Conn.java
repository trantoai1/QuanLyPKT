/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ketnoi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author trantoai
 */
public class Conn {
    
    
    public static Connection conn() {
        String server = "jdbc:sqlserver://;databaseName=QLPKT";
        
        String user = "sa";
        String pass = "2231304";
        Connection con = null;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(server,user,pass);
            
        }catch (ClassNotFoundException|SQLException e)
        {
            System.out.println("Khong the ket noi den csdl");
        }
        
        return con;
    }
    
}
