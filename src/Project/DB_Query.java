/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Project;

/**
 *
 * @author prashant-lappy
 */
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JOptionPane;
class DB_Query {
    static ResultSet rs=null;
    static Statement s;
    static PreparedStatement  ps;
    static boolean  duplicate;
    public static void selectAllQuery(String table, String cond)
    {
        try
        {
            DBConnect.connect();
            s = DBConnect.conn.createStatement();
            
            String str= "Select * from " + table;
            //if(!cond.isEmpty())
            if(cond != null)
            {
                str+=" Where " + cond;
            }
            //System.out.println(str);
            rs = s.executeQuery (str);
        }
        catch(SQLException err)
        {
            JOptionPane.showMessageDialog(null, "SQL Error :" + err);   
        }    
    }
    public static void selectFieldQuery(String table,String field, String cond)
    {
        try
        {
            DBConnect.connect();
            s = DBConnect.conn.createStatement();
            
            String str= "Select " + field + " from " + table;
            //if(!cond.isEmpty())
            if(cond != null)
            {
                str+=" Where " + cond;
            }
           //System.out.println(str);
            rs = s.executeQuery (str);
        }
        catch(SQLException err)
        {
            JOptionPane.showMessageDialog(null, "SQL Error :" + err);   
        }    
    }
    static void dclQuery(String qryType)
    {
        try
        {                                  
            ps.executeUpdate();
            DBConnect.conn.commit();  
            DBConnect.conn.setAutoCommit(true);
        }
        catch(SQLException e)
        {
            if(e.getErrorCode()==1062)
            {
                JOptionPane.showMessageDialog(null, "Dublicate Data Entry..");
                duplicate=true;
            }
            else
            {
                e.printStackTrace();
            }
        }
        catch(NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null, "Please enter numeric parameter.");
        }
        finally
        {
           if (DBConnect.conn != null)
           {
               try
               {
                   DBConnect.conn.close ();
                  
               }
               catch (Exception e) { 
                //Ignore
               }
           }
        }
    }
    
}
