/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cims;

import java.text.*;
import java.util.*;
public class currentTime
{
    public static Date getTodayDateWithTime()
    {
        Date dat=new Date();
        return dat;
    }
    public static String getDateAsString()
    {
        String myString = SimpleDateFormat.getDateInstance().format(new Date());
        System.out.println(myString);
        return myString;
    }
    public static String getDateOnly()
    {
        Date dat1=new Date();
        int i=dat1.getDate();
        return Integer.toString(i);
    }
    public static String getYearOnly()
    {
        Date dat2=new Date();
        int j=dat2.getYear()+1900;
        return Integer.toString(j);
    }
    public static String getMonthOnly()
    {
        Date dat3=new Date();
        int j=dat3.getMonth()+1;
        return Integer.toString(j);
    }
}
