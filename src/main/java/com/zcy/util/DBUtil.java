package com.zcy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    public static Connection getConnection(){
        Connection conn=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String sql="jdbc:mysql://192.168.1.249:3306/ykcs_114_70?useSSL=false";
            conn= DriverManager.getConnection(sql,"work","WORK!@#$%");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
