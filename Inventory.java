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
public class Inventory {
    public String bottleNo;
    public String cas;
    public String transactionId;
    public String purchaseDate;
    public String expiryDate;
    public String purchaseQty;
    public String remainingQty;
    public String company;
    public String cost;
    public String borrow;
    
    Inventory(){
        
    }
    
    Inventory(String bottleNo, String cas, String transactionId, String purchaseDate, String expiryDate, String purchaseQty, String remainingQty, String company, String cost, String borrow){
        this.bottleNo = bottleNo;
        this.cas = cas;
        this.borrow = borrow;
        this.company = company;
        this.cost = cost;
        this.expiryDate = expiryDate;
        this.purchaseDate = purchaseDate;
        this.purchaseQty = purchaseQty;
        this.remainingQty = remainingQty;
        this.transactionId = transactionId;
    }
    
    static boolean addInventory(String bottleNo, String cas, String transactionId, String purchaseDate, String expiryDate, String purchaseQty, String remainingQty, String company, String cost, String borrow){
        try{
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con = DriverManager.getConnection("jdbc:odbc:CIMSDatabase","root","root");
            Statement smt = con.createStatement();
            smt.executeUpdate("insert into "+Constant.inventory_table+" values ("
                        +bottleNo+",'"
                        +cas+"','"
                        +transactionId+"','"
                        +purchaseDate+"','"
                        +expiryDate+"','"
                        +company+"',"
                        +cost+","
                        +purchaseQty+","
                        +remainingQty+","
                        +borrow+");");
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
        
    }
    
    static boolean editInventory(String bottleNo, String cas, String transactionId, String purchaseDate, String expiryDate, String purchaseQty, String remainingQty, String company, String cost, String borrow){
        try{
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con = DriverManager.getConnection("jdbc:odbc:CIMSDatabase","root","root");
            Statement smt = con.createStatement();
            smt.executeUpdate("update "+Constant.inventory_table+" set casno = "
                        +cas+", tid = '"
                        +transactionId+"', purdate = '"
                        +purchaseDate+"',expirydate = '"
                        +expiryDate+"',company = '"
                        +company+"',cost = "
                        +cost+",purqty = "
                        +purchaseQty+",remqty = "
                        +remainingQty+",borrow = "
                        +borrow+"  where botno = "+bottleNo+";");
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    
    static Inventory searchInventory(String bottleNo){
        try{
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con = DriverManager.getConnection("jdbc:odbc:CIMSDatabase","root","root");
            Statement smt = con.createStatement();
            ResultSet rs = smt.executeQuery("select * from "+Constant.inventory_table+" where botno = "+bottleNo+";");
            rs.next();
            String cas = rs.getString("casno");
            String transactionId = rs.getString("tid");
            String purchaseDate = rs.getString("purdate");
            String expiryDate = rs.getString("expirydate");
            String purchaseQty = rs.getString("purqty");
            String remainingQty = rs.getString("remqty");
            String company = rs.getString("company");
            String cost = rs.getString("cost");
            String borrow = rs.getString("borrow");
            return new Inventory(bottleNo, cas, transactionId, purchaseDate, expiryDate, purchaseQty, remainingQty, company, cost, borrow);
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    
    static boolean deleteInventory(String bottleNo){
        try{
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con = DriverManager.getConnection("jdbc:odbc:CIMSDatabase","root","root");
            Statement smt = con.createStatement();
            smt.executeUpdate("delete from "+Constant.inventory_table+" where botno = "+bottleNo+";");
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    
    static boolean checkBottleNo(String bottleNo){
        try{
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con = DriverManager.getConnection("jdbc:odbc:CIMSDatabase","root","root");
            Statement smt = con.createStatement();
            ResultSet rs = smt.executeQuery("select botno from "+ Constant.inventory_table);
            while(rs.next()){
                if(bottleNo.equals(rs.getString("botno"))){
                    return true;
                }
            }
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
        return false;
    }
}
