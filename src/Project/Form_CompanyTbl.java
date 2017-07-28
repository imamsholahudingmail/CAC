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

public class Form_CompanyTbl extends JInternalFrame
{
    GroupLayout grpLayout = new GroupLayout(getContentPane());
    JPanel panel_Content = new JPanel();

    JPanel panel_Controls = new JPanel();
    JLabel lbl_Name,lbl_Address,lbl_Size,lbl_Sal;


    JTextField txt_Name,txt_Size;
    JComboBox  cmb_Sal;
    JTextArea txa_Address;
    JButton   btn_Search,btn_Reset;
    static JButton btn_CompReportPrev=new JButton("Report",new ImageIcon("icons/report.png"));
     private JLayeredPane layeredPane;
    JTable table;
    int v,h;
    String codeT, nameT, addressT,hrmT,emailT,contactT;
    JScrollPane jsp;
    static int dataCol,dataRow;
    static String mydata[][];
    public Form_CompanyTbl()
    {

        setSize(1000,500);
        setTitle("Search Company");
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
                .addGap(20,20, 20)
                .addComponent(panel_Controls, GroupLayout.PREFERRED_SIZE, 1000, GroupLayout.PREFERRED_SIZE))
             .addGroup(grpLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panel_Content, GroupLayout.PREFERRED_SIZE, 1000, GroupLayout.PREFERRED_SIZE)
            )
        );
        grpLayout.setVerticalGroup(
            grpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(grpLayout.createSequentialGroup()
                .addGap(10, 10, 10)

                .addGroup(grpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(panel_Controls,GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(grpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(panel_Content))
            ));

    }

 void getReport()
 {

        Vector<String> heading = new Vector<String>();
        heading.addElement("Code");
        heading.addElement("Comapany Name");
        heading.addElement("Address");
        heading.addElement("HR Manager");
        heading.addElement("Email Id");
        heading.addElement("Contact Number");

        Vector data = new Vector();

        int i=0;
        try
        {

            String table="tbl_comp_primary as t1 JOIN tbl_comp_general as t2 " +
                    "ON t1.comp_code = t2.comp_code JOIN tbl_comp_professional as t3 " +
                    "on t1.comp_code=t3.comp_code";
           String fields="t1.comp_code,t1.name,t2.address,t3.hrm,t3.Email_id,t3.Contact_No" ;
           String cond="";
           if(!(txt_Name.getText().isEmpty()))
           {
               if(cond.isEmpty())
                   cond += "t1.Name like ('%"+ txt_Name.getText()+ "%')";
               else
                   cond += "AND t1.Name like ('%"+ txt_Name.getText()+"%')";
           }

           if(!(txt_Size.getText().isEmpty()))
           {
               if(cond.isEmpty())
                   cond += "t2.size >='"+ txt_Size.getText()+ "'";
               else
                   cond += "AND t2.size >='"+ txt_Size.getText()+ "'";
           }

           if(!(String.valueOf(cmb_Sal.getSelectedItem()).isEmpty()))
           {
               if(cond.isEmpty())
                   cond += "t3.Sal_scale >='"+ String.valueOf(cmb_Sal.getSelectedItem())+ "'";
               else
                   cond += "AND t3.Sal_scale >='"+ String.valueOf(cmb_Sal.getSelectedItem())+ "'";
           }

           if(!(txa_Address.getText().isEmpty()))
           {
               if(cond.isEmpty())
                   cond += "t2.address like ('%"+ txa_Address.getText()+ "%')";
               else
                   cond += "AND t2.address like ('%"+ txa_Address.getText()+"%')";
           }

           DB_Query.selectFieldQuery(table,fields, cond);
           dataCol=6;
           DB_Query.rs.last();
           dataRow=DB_Query.rs.getRow();

           mydata=new String[dataRow][dataCol];
           DB_Query.rs.beforeFirst();

            while(DB_Query.rs.next())
            {

                codeT = DB_Query.rs.getString("comp_code");
                nameT = DB_Query.rs.getString("name");
                addressT=DB_Query.rs.getString("address");
                hrmT=DB_Query.rs.getString("Hrm");
                emailT=DB_Query.rs.getString("Email_id");
                contactT=DB_Query.rs.getString("Contact_no");
Vector<String> tmp = new Vector<String>();
                //JOptionPane.showMessageDialog(null, DB_Query.rs.getString("name"));
                tmp.addElement(codeT);
                mydata[i][0]=codeT;
                tmp.addElement(nameT);
                mydata[i][1]=nameT;
                tmp.addElement(addressT);
                mydata[i][2]=addressT;
                tmp.addElement(hrmT);
                mydata[i][3]=hrmT;
                tmp.addElement(emailT);
                mydata[i][4]=emailT;
                tmp.addElement(contactT);
                mydata[i][5]=contactT;
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
        v=ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
        h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
        jsp = new JScrollPane(table,v,h);

        panel_Content.setLayout(null);
        jsp.setBounds( 1, 50, 970,250 );
        panel_Content.add( jsp );
    }


    void controls_Setting()
    {
        panel_Controls.setLayout(null);


        layeredPane = new JLayeredPane();
        layeredPane.setBorder(BorderFactory.createTitledBorder(" Search By "));
        layeredPane.setBounds( 1, 1, 960, 250);
        //getControlPaneContent();
        panel_Controls.add(layeredPane);

        lbl_Name = new JLabel( " Name:" );
        lbl_Name.setBounds( 50, 30, 80, 20 );
        layeredPane.add( lbl_Name );

        txt_Name = new JTextField();
        txt_Name.setBounds( 170, 30, 140, 20 );
        layeredPane.add( txt_Name );




        lbl_Size= new JLabel( "Size of Organization:" );
        lbl_Size.setBounds( 50, 70, 150, 20 );
        layeredPane.add( lbl_Size );

        txt_Size= new JTextField();
        txt_Size.setBounds( 170, 70, 140, 20 );
        layeredPane.add( txt_Size );

        lbl_Sal = new JLabel( "Salary Scale:" );
        lbl_Sal.setBounds( 50, 110, 220, 20 );

        layeredPane.add(lbl_Sal);
        lbl_Sal.setVisible(false);



        String[] sal_size = { "10000-20000", "20000-30000", "30000-40000", "40000-50000", "50000-60000", "60000-More" };

        cmb_Sal=new JComboBox(sal_size);

        cmb_Sal.setFont(new Font("Serif", Font.PLAIN,12));
        cmb_Sal.setBounds( 170, 110, 140, 20 );
        layeredPane.add( cmb_Sal );
         cmb_Sal.setVisible(false);

        lbl_Address= new JLabel( "Address:" );
        lbl_Address.setBounds(  50, 110, 220, 20 );
        layeredPane.add( lbl_Address );

        txa_Address = new JTextArea();
        txa_Address.setLineWrap(true);
        txa_Address.setWrapStyleWord(true);
        JScrollPane scrollPane_Address = new JScrollPane(txa_Address);
        scrollPane_Address.setVerticalScrollBarPolicy(
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_Address.setPreferredSize(new Dimension(150, 150));
        scrollPane_Address.setBounds(170, 110, 140, 50);
        layeredPane.add(scrollPane_Address);

         btn_Search=new JButton("Search",new ImageIcon("icons/search.png"));
         btn_Search.setBounds(50,180 ,110 ,30 );
         layeredPane.add( btn_Search );
         btn_Search.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                getReport();
            }
        });
         btn_Reset=new JButton("Clear",new ImageIcon("icons/clear.png"));
         btn_Reset.setBounds(170,180 ,110 ,30 );
         layeredPane.add( btn_Reset );

          btn_Reset.addActionListener(new ActionListener()
          {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                 clearallfields();
            }
          });

         btn_CompReportPrev.setBounds(860,1,100,30 );
         btn_CompReportPrev.setToolTipText("Get Report");
         panel_Content.add( btn_CompReportPrev );
    }

    void   clearallfields()
    {
        txt_Name.setText("");
        txt_Size.setText("");
        cmb_Sal.setSelectedIndex(0);
        txa_Address.setText("");
    }
}
