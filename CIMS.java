/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cims;

/**
 *
 * @author Priyan
 */
public class CIMS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        CIMS_Preference check = new CIMS_Preference();
        if(!check.checkFirstRun()){
            First_Run.main(null);
        }
        
    }
        
}
