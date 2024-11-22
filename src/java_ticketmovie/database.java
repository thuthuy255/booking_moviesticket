/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_ticketmovie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ngthuthuy
 */
public class database {

    public static Connection connectDb() {

        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connect = DriverManager.getConnection(
        "jdbc:mysql://localhost/movies_management", "root", "");


            return connect;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}

