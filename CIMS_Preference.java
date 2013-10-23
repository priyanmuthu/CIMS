/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cims;

import java.util.prefs.Preferences;

/**
 *
 * @author Priyan
 */
public class CIMS_Preference {
    static Preferences pref;
    final static String prefNode = "CIMS_test";
    final static String firstRunKey = "firstRun";
    final static String uname = "username";
    final static String pass = "password";
    static boolean checkFirstRun(){
        pref = Preferences.userRoot().node(prefNode);
        return (pref.getBoolean(firstRunKey, false));
    }
    static void setfirstRun(boolean a){
        pref = Preferences.userRoot().node(prefNode);
        pref.putBoolean(firstRunKey, a);
    }
    
    static void setSQL(String username,String password){
        pref = Preferences.userRoot().node(prefNode);
        pref.put(uname, username);
        pref.put(pass, password);
    }
    
    static String[] getSQL(){
        pref = Preferences.userRoot().node(prefNode);
        String a[] = new String[2];
        a[0] = pref.get(uname, null);
        a[1] = pref.get(pass, null);
        return a;
    }
    
    static String getNode(){
        return prefNode;
    }
}
