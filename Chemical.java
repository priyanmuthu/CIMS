/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cims;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.prefs.Preferences;

/**
 *
 * @author Priyan
 */
public class Chemical {
    String cas;
    String IUPACName;
    String commonName;
    String molecularFormula;
    int rack;
    String storageType;
    static Preferences pref;
    static String uname;
    static String pass;
    
    Chemical(){
        pref = Preferences.userRoot().node(CIMS_Preference.getNode());
        uname = CIMS_Preference.getUname();
        pass = CIMS_Preference.getPass();
    }
    Chemical(String cas,String IUPACName, String commonName,String molecularFormula,int rack, String storageType){
        this.cas = cas;
        this.IUPACName = IUPACName;
        this.commonName = commonName;
        this.molecularFormula = molecularFormula;
        this.rack = rack;
        this.storageType = storageType;
        pref = Preferences.userRoot().node(CIMS_Preference.getNode());
        uname = CIMS_Preference.getUname();
        pass = CIMS_Preference.getPass();
    }
    /*
     * This function will search the Database by the entered Cas No.
     * Returns an object of Chemical class which matches the search result
     * @param cas The CAS no. of the Chemical required.
     */
    static Chemical serachChemCas(String cas){
        try{
            pref = Preferences.userRoot().node(CIMS_Preference.getNode());
            uname = CIMS_Preference.getUname();
            pass = CIMS_Preference.getPass();
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cims",uname,pass);
            Statement smt = con.createStatement();
            ResultSet rs = smt.executeQuery("select * from "+Constant.chemical_table+" where casno = '"+cas+"';");
            rs.next();
            String IUPACName = rs.getString("iname");
            String commonName = rs.getString("cname");
            String molecularFormula = rs.getString("mfor");
            int rack = Integer.parseInt(rs.getString("rno"));
            String storageType = rs.getString("stype");
            Chemical c = new Chemical(cas, IUPACName, commonName, molecularFormula, rack, storageType);
            return c;
        }catch(Exception e){
            
        }
        return null;
    }
    /*
     * This function will search the Database by the entered Common Name.
     * Returns an object of Chemical class which matches the search result
     * @param commonName The Common name of the chemical required.
     */
    static Chemical searchChemCommonName(String commonName){
        try{
            pref = Preferences.userRoot().node(CIMS_Preference.getNode());
            uname = CIMS_Preference.getUname();
            pass = CIMS_Preference.getPass();
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cims",uname,pass);
            Statement smt = con.createStatement();
            ResultSet rs = smt.executeQuery("select * from "+Constant.chemical_table+" where cname = '"+commonName+"';");
            rs.next();
            String cas = rs.getString("casno");
            String IUPACName = rs.getString("iname");
            String molecularFormula = rs.getString("mfor");
            int rack = Integer.parseInt(rs.getString("rno"));
            String storageType = rs.getString("stype");
            Chemical c = new Chemical(cas, IUPACName, commonName, molecularFormula, rack, storageType);
            return c;
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    /*
     * This function will search the Database by the entered Molecular Formula
     * Returns an object of Chemical class which matches the search result.
     * @param molecularFormula The Molecular Formula of the chemical required.
     */
    static Chemical searchChemMolecularFormula(String molecularFormula){
        try{
            pref = Preferences.userRoot().node(CIMS_Preference.getNode());
            uname = CIMS_Preference.getUname();
            pass = CIMS_Preference.getPass();
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cims",uname,pass);
            Statement smt = con.createStatement();
            ResultSet rs = smt.executeQuery("select * from "+Constant.chemical_table+" where mfor = '"+molecularFormula+"';");
            rs.next();
            String cas = rs.getString("casno");
            String IUPACName = rs.getString("iname");
            String commonName = rs.getString("cname");
            int rack = Integer.parseInt(rs.getString("rno"));
            String storageType = rs.getString("stype");
            Chemical c = new Chemical(cas, IUPACName, commonName, molecularFormula, rack, storageType);
            return c;
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    /*
     * This function will search the Database by the entered Cas No.
     * Returns an object of Chemical class which matches the search result.
     * @param IUPACName The IUPAC Name of the chemical required.
     */
    static Chemical searchIUPACName(String IUPACName){
        try{
            pref = Preferences.userRoot().node(CIMS_Preference.getNode());
            uname = CIMS_Preference.getUname();
            pass = CIMS_Preference.getPass();
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cims",uname,pass);
            Statement smt = con.createStatement();
            ResultSet rs = smt.executeQuery("select * from "+Constant.chemical_table+" where iname = '"+IUPACName+"';");
            rs.next();
            String cas = rs.getString("casno");
            String commonName = rs.getString("cname");
            String molecularFormula = rs.getString("mfor");
            int rack = Integer.parseInt(rs.getString("rno"));
            String storageType = rs.getString("stype");
            Chemical c = new Chemical(cas, IUPACName, commonName, molecularFormula, rack, storageType);
            return c;
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    /*
     * This function add a Chemical to the database with the given details if it doesnt exist.
     * Returns a boolean variable to specify whether the database addition was successful or not.
     * @param cas The CAS No. of the Chemical
     * @param IUPACName The IUPAC Name of the chemical required.
     * @param commonName The common name of the chemical (Optional).
     * @param molecularFormula The molecular formula of the chemical.
     * @param rack The Rack No. in which the chemical is kept.
     * @param storageType To specify the medium of storage.
     */
    static boolean addNewChem(String cas,String IUPACName, String commonName,String molecularFormula,int rack, String storageType){
        
        try{
            pref = Preferences.userRoot().node(CIMS_Preference.getNode());
            uname = CIMS_Preference.getUname();
            pass = CIMS_Preference.getPass();
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cims",uname,pass);
            Statement smt = con.createStatement();
            if(cas.length()>0 && IUPACName.length()>0 && molecularFormula.length()>0){
                smt.executeUpdate("insert into "+Constant.chemical_table+" values ('"+cas+"','"+IUPACName+"',"+rack+",'"+commonName+"','"+molecularFormula+"','"+storageType+"');");
                return true;
            }
            else{
                return false;
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
    /*
     * This function edits a Chemical with the given CAS No. to the database with the given details if it exists.
     * Returns a boolean variable to specify whether the database addition was successful or not.
     * @param cas The CAS No. of the Chemical
     * @param IUPACName The IUPAC Name of the chemical required.
     * @param commonName The common name of the chemical (Optional).
     * @param molecularFormula The molecular formula of the chemical.
     * @param rack The Rack No. in which the chemical is kept.
     * @param storageType To specify the medium of storage.
     */
    static boolean editChem(String cas,String IUPACName, String commonName,String molecularFormula,int rack, String storageType){
        try{
            pref = Preferences.userRoot().node(CIMS_Preference.getNode());
            uname = CIMS_Preference.getUname();
            pass = CIMS_Preference.getPass();
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cims",uname,pass);
            Statement smt = con.createStatement();
            if(cas.length()>0 && IUPACName.length()>0 && molecularFormula.length()>0){
                smt.executeUpdate("update "+ Constant.chemical_table+" set iname = '"+IUPACName+"', cname = '"+commonName+"', mfor = '"+molecularFormula+"',rno = "+rack+", stype = '"+storageType+"' where casno = '"+cas+"';");
                return true;
            }else{
                return false;
            }
            
        }catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
    
    /*
     * This function deletes the data entry of the given chemiacal from the database by CAS No.
     * @param cas The CAS No. of the chemical to be deleted.
     */
    static boolean deleteChem(String cas){
        try{
            pref = Preferences.userRoot().node(CIMS_Preference.getNode());
            uname = CIMS_Preference.getUname();
            pass = CIMS_Preference.getPass();
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cims",uname,pass);
            Statement smt = con.createStatement();
            smt.executeUpdate("delete from "+ Constant.chemical_table+" where casno = '"+cas+"';");
            return true;
        }catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
    
    @Override
    public String toString(){
        String print;
        print = IUPACName+"  "+cas+"  "+molecularFormula+"  "+commonName;
        return print;
        
    }
    
     /*
     * This function returns true if the CAS No. exists in the database
     * @param cas The CAS No. of the chemical.
     */
    static boolean checkCAS(String cas){
        try{
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cims",uname,pass);
            Statement smt = con.createStatement();
            ResultSet rs = smt.executeQuery("select casno from "+Constant.chemical_table+";");
            while(rs.next()){
                if(cas.equals(rs.getString("casno"))){
                    return true;
                }
            }
            return false;
        }catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
    
}
