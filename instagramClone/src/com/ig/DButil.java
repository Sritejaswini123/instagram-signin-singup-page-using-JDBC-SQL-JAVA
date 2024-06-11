package com.ig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DButil {

    public static String signUpInstagramUser(UserDetails userData) throws SQLException {
        Connection connection = getDataBaseConnection();
        PreparedStatement pst = connection.prepareStatement("Insert into insta_users values(?,?,?,?)");
        pst.setString(1, userData.getFirstName());
        pst.setString(2, userData.getLastName());
        pst.setString(3, userData.getUsername());
        pst.setString(4, userData.getPassword());
        int insertedRow = pst.executeUpdate();
        return "Sign Up successful!!! Yayyyy!!";
    }

    public static boolean validateUserLogin(String userId, String password) throws SQLException {
        Connection connection = getDataBaseConnection();
        PreparedStatement pst = connection
               .prepareStatement("select count(*) from insta_users where username=? and password=?");
        pst.setString(1, userId);
        pst.setString(2, password);
        ResultSet rs = pst.executeQuery();
        while(rs.next()) {
            int response = rs.getInt(1);
            if(response > 0) {
                System.out.println("Authentic User!!");
                // update failed_count=0;
                // exception throw even credentials are correct but account is locked
                return true;
            }
        }
        return false;
    }

    public static void updateFailedCount(String userId) throws SQLException {
        Connection connection = getDataBaseConnection();
        PreparedStatement pst = connection
               .prepareStatement("update insta_users set failed_count = 0 where username=?");
        pst.setString(1, userId);
        pst.executeUpdate();
    }

    private static Connection getDataBaseConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USERNAME, Constants.DB_PASSWORD);
        return connection;
    }
}