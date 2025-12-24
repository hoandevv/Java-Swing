/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
public class DBConnection {
    public static Connection getConnection () throws Exception {
        String url ="jdbc:mysql://localhost:3306/ql_hocvien";
        String user="root";
        String pass="123456789";
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url,user,pass);
    }
}

