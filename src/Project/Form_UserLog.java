/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Project;

/**
 *
 * @author Prashant
 */
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.swing.border.Border;

public class Form_UserLog extends JInternalFrame
{
    GroupLayout grpLayout = new GroupLayout(getContentPane());
    JPanel panel_Content = new JPanel();

    JPanel panel_Controls = new JPanel();
    JLabel lbl_UserName;


    JTextField txt_UserName;

    static JButton btn_LogReportPrev=new JButton("Report",new ImageIcon("icons/report.png"));
    JButton  btn_GetLog;

    JTable table;
    int v,h;
    String rowT,loginT, logoutT;
    JScrollPane jsp;
    static int dataCol,dataRow;
    static String mydata[][];
    public Form_UserLog()
    {

        setSize(700,500);
        setTitle("User Logs");
        setMaximizable(true);
        setIconifiable(true);
        setClosable(true);
        setResizable(true);





        getContentPane().setLayout(grpLayout);
        grpLayout.setAutoCreateGaps(true);
        grpLayout.setAutoCreateContainerGaps(true);


        //getContentPane().add( panel_Content );
        controls_Setting();
        getReport();

       grpLayout.setHorizontalGroup(
            grpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

            .addGroup(grpLayout.createSequentialGroup()
                .addGap(10,10, 10)
                .addComponent(panel_Controls, GroupLayout.PREFERRED_SIZE, 1100, GroupLayout.PREFERRED_SIZE))
             .addGroup(grpLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(panel_Content, GroupLayout.PREFERRED_SIZE, 1100, GroupLayout.PREFERRED_SIZE)
            )
        );
        grpLayout.setVerticalGroup(
            grpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(grpLayout.createSequentialGroup()
                .addGap(10, 10, 10)

                .addGroup(grpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(panel_Controls,GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(grpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(panel_Content))
            ));

    }

   void getReport()
   {

        Vector<String> heading = new Vector<String>();
        heading.addElement("Serial No.");
        heading.addElement("Login Date - Time");
         heading.addElement("Logout Date - Time");

        Vector data = new Vector();

        int i=0;
       try
       {


           String table= "tbl_user_login as t1 Left Outer Join " +
                   "tbl_user_logout as t2 on t1.Log_Id=t2.Log_Id";
           String fields="t1.login,t2.logout" ;
           String cond="t1.User_Name='"+ txt_UserName.getText()+"'";
           DB_Query.selectFieldQuery(table,fields, cond);
           dataCol=3;
           DB_Query.rs.last();
           dataRow=DB_Query.rs.getRow();

           mydata=new String[dataRow][dataCol];
           DB_Query.rs.beforeFirst();

            while(DB_Query.rs.next())
            {

                rowT = String.valueOf(i+1);
                loginT = DB_Query.rs.getString("login");
                logoutT=DB_Query.rs.getString("logout");
               
                Vector<String> tmp = new Vector<String>();
                //JOptionPane.showMessageDialog(null, DB_Query.rs.getString("login"));
                tmp.addElement(rowT);
                mydata[i][0]=rowT;
                tmp.addElement(loginT);
                mydata[i][1]=loginT;
                tmp.addElement(logoutT);
                mydata[i][2]=logoutT;
                data.addElement(tmp);
                i++;
            }
        }
        catch(SQLException e)
        {
             //
        }
        finally
        {
           if (DBConnect.conn != null)
           {
               try
               {
                   DB_Query.rs.close();
                   DB_Query.s.close ();
                   DBConnect.conn.close ();

               }
               catch (Exception e) {
                //Ignore
                }
           }
        }
        
        table = new JTable(data, heading);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        v=ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
        h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
        jsp = new JScrollPane(table,v,h);
        panel_Content.setLayout(null);
        jsp.setBounds( 1, 40, 970,400 );
        panel_Content.add(jsp);
    }



    void controls_Setting()
    {
        panel_Controls.setLayout(null);



        lbl_UserName = new JLabel( "  UserName:" );
        lbl_UserName.setBounds( 40, 30, 80, 20 );
        panel_Controls.add( lbl_UserName );

        txt_UserName = new JTextField();
        txt_UserName.setBounds( 120, 30, 250, 25 );
        panel_Controls.add( txt_UserName );

        btn_GetLog=new JButton("Get Logs",new ImageIcon("icons/user_log.png"));
        btn_GetLog.setBounds(420,25 ,120 ,30 );
        panel_Controls.add( btn_GetLog );

        btn_GetLog.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                getReport();
            }
        });
         btn_LogReportPrev.setBounds(870,0,100,30 );
         btn_LogReportPrev.setToolTipText("Get Report");
         panel_Content.add( btn_LogReportPrev );

    }
}
