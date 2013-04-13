/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cims;

/**
 *
 * @author Priyan
 */
public class Chemical {
    int cas;
    String IUPACName;
    String commonName;
    String molecularFormula;
    int rack;
    char storageType;
    
    Chemical(){
        
    }
    Chemical(int cas,String IUPACName, String commonName,String molecularFormula,int rack, char storageType){
        this.cas = cas;
        this.IUPACName = IUPACName;
        this.commonName = commonName;
        this.molecularFormula = molecularFormula;
        this.rack = rack;
        this.storageType = storageType;
    }
    /*
     * This function will search the Database by the entered Cas No.
     * Returns an object of Chemical class which matches the search result
     * @param cas The CAS no. of the Chemical required.
     */
    static Chemical serachChem_cas(int cas){
        return null;
    }
    /*
     * This function will search the Database by the entered Common Name.
     * Returns an object of Chemical class which matches the search result
     * @param commonName The Common name of the chemical required.
     */
    static Chemical searchChemCommonName(String commonName){
        return null;
    }
    /*
     * This function will search the Database by the entered Molecular Formula
     * Returns an object of Chemical class which matches the search result.
     * @param molecularFormula The Molecular Formula of the chemical required.
     */
    static Chemical searchChemMolecularFormula(String molecularFormula){
        return null;
    }
    /*
     * This function will search the Database by the entered Cas No.
     * Returns an object of Chemical class which matches the search result.
     * @param IUPACName The IUPAC Name of the chemical required.
     */
    static Chemical searchIUPACName(String IUPACName){
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
    static boolean addNewChem(int cas,String IUPACName, String commonName,String molecularFormula,int rack, char storageType){
        return true;
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
    static boolean editChem(int cas,String IUPACName, String commonName,String molecularFormula,int rack, char storageType){
        return true;
    }
    
    @Override
    public String toString(){
        String print;
        print = IUPACName+"  "+cas+"  "+molecularFormula+"  "+commonName;
        return print;
        
    }
}
