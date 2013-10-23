/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cims;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Priyan
 */
public class User {
    String userId;
    String name;
    String email;
    String userType;
    String contact;
    private String password;
    
    /*
     * This function is the constuctor
     * Returns an object of User class 
     */
    User(){
        
    }
    /*
     * This function will create a object of chemical class
     * Returns an object of Chemical class which matches the search result
     * @param userID user id of the user
     * @param name name of the user
     * @param email email id of the user
     * @param userType user type of the user
     * @param contact contact no 
     * @param password password of the User required.
     */
    User(String userId,String name,String email,String userType,String contact, String password){
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.userType = userType;
        this.contact = contact;
        this.password = password;
    }
    /*
     * This function will add a new user with its details to the database
     * Returns an boolean variable to specify whether new user is added or not
     * @param userID user id of the user
     * @param name name of the user
     * @param email email id of the user
     * @param userType user type of the user
     * @param contact contact no 
     * @param password password of the User required.
     */
    static boolean addUser(String userId,String name,String email,String userType,String contact, String password){
        try{
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con = DriverManager.getConnection("jdbc:odbc:CIMSDatabase","root","root");
            Statement smt = con.createStatement();
            String hash = Password.getSaltedHash(password);
            smt.executeUpdate("insert into "+Constant.user_table+" values ('"+userId+"','"+name+"','"+email+"','"+contact+"','"+hash+"','"+userType+"');");
            return true;
        }catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
    /*
     * This function will edit an existing user and add its edited details to the database
     * Returns an boolean variable to specify whether user is edited or not
     * @param userID user id of the user
     * @param name name of the user
     * @param email email id of the user
     * @param userType user type of the user
     * @param contact contact no 
     * @param password password of the User required.
     */
    static boolean editUser(String userId,String name,String email,String userType,String contact, String password){
        try{
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con = DriverManager.getConnection("jdbc:odbc:CIMSDatabase","root","root");
            Statement smt = con.createStatement();
            if(password.equals("")){
                smt.executeUpdate("update "+Constant.user_table+" set uname = '"+name+"', email = '"+email+"',contact = '"+contact+"',utype = '"+userType+"' where uid = '"+userId+"';");
            }
            else{
                String hash = Password.getSaltedHash(password);
                smt.executeUpdate("update "+Constant.user_table+" set uname = '"+name+"', email = '"+email+"',contact = '"+contact+"', password = '"+hash+"', utype = '"+userType+"' where uid = '"+userId+"';");
            }
            return true;
        }catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
    /*
     * This function will delete user with its details from  the database
     * Returns an boolean variable to specify whether user is deleted or not
     * @param userID user id of the user required.
     */
    static boolean deleteUser(String userId){
         try{
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con = DriverManager.getConnection("jdbc:odbc:CIMSDatabase","root","root");
            Statement smt = con.createStatement();
            smt.executeUpdate("delete from "+Constant.user_table+" where uid = '"+userId+"';");
            return true;
        }catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
    /*
     * This function will change the password of an existing user in the database
     * Returns an boolean variable to specify whether password is changed or not
     * @param userId userId of the user.
     * @param newPassword new password of the User required.
     */
    static boolean passwordChange(String userId,String newPassword){
        try{
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con = DriverManager.getConnection("jdbc:odbc:CIMSDatabase","root","root");
            Statement smt = con.createStatement();
            smt.executeUpdate("update "+Constant.user_table+" set password = '"+newPassword+"' where uid = '"+userId+"';");
            return true;
        }catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
    /*
     * This function will log in a valid user to use the application
     * Returns a object of User class
     * @param userID user id of the user
     * @param password password of the User required.
     */
    static User logIn(String userId,String password){
        try{
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con = DriverManager.getConnection("jdbc:odbc:CIMSDatabase","root","root");
            Statement smt = con.createStatement();
            ResultSet rs = smt.executeQuery("select * from "+Constant.user_table+" where uid = '"+userId+"';");
            rs.next();
            String name = rs.getString("uname");
            String email = rs.getString("email");
            String contact = rs.getString("contact");
            String pass = rs.getString("password");
            String userType = rs.getString("utype");
            if(Password.check(password,pass)){
                return new User(userId, name, email, userType, contact, password);
            }
            
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    /*
     * This function will search a user from the database
     * Returns a object of the User class
     * @param userID user id of the user required.
     */
    static User searchUser(String userId){
        try{
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con = DriverManager.getConnection("jdbc:odbc:CIMSDatabase","root","root");
            Statement smt = con.createStatement();
            ResultSet rs = smt.executeQuery("select * from "+Constant.user_table+" where uid = '"+userId+"';");
            rs.next();
            String name = rs.getString("uname");
            String email = rs.getString("email");
            String contact = rs.getString("contact");
            String pass = rs.getString("password");
            String userType = rs.getString("utype");
            return new User(userId, name, email, userType, contact, pass);
            
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    /*
     * This function will check the user id from the database
     * Returns an boolean variable to specify whether the user id is valid or not
     * @param userID user id of the user required.
     */
    static boolean checkUserID(String userId){
        try{
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con = DriverManager.getConnection("jdbc:odbc:CIMSDatabase","root","root");
            Statement smt = con.createStatement();
            ResultSet rs = smt.executeQuery("select uid from "+Constant.user_table+" where uid = '"+userId+"';");
            while(rs.next()){
                if(userId.equals(rs.getString("uid"))){
                    return true;
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
    
    /*
     * This function will checks both the password mathches or not in password change.
     * Returns an boolean variable to specify whether both the password mathches or not.
     * @param password password of the User required.
     * @param confirmPassword The second password to match with password.
     */
    static boolean checkPassword(String password,String confirmPassword){
        return password.equals(confirmPassword);
    }
    
    /*
     * This function will checks whether the password entered is matching with the respective user id or not.
     * Returns an boolean variable to specify whether user id and password mathches.
     * @param password password of the User required.
     * @param confirmPassword The second password to match with password.
     */
    static boolean verifyPassword(String  userId, String password){
        try{
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con = DriverManager.getConnection("jdbc:odbc:CIMSDatabase","root","root");
            Statement smt = con.createStatement();
            ResultSet rs = smt.executeQuery("select * from "+Constant.user_table+" where uid = '"+userId+"';");
            rs.next();
            String pass = rs.getString("password");
            if(Password.check(password,pass)){
                return true;
            }
            
        }catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
    
    String to_String(User a){
        String s = a.name+" "+a.email;
        return s;
        
    }
}
