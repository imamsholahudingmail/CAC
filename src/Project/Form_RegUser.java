/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Project;

/**
 *
 * @author Prashant
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.sql.*;

public class Form_RegUser extends JInternalFrame
{
    GroupLayout grpLayout = new GroupLayout(getContentPane());
    JPanel panel_Content = new JPanel();

     private JLayeredPane layeredPane,layeredPane1,layeredPane2,layeredPane3;
    JPanel panel_Button = new JPanel();
    JLabel lbl_User_Name, lbl_Password, lbl_Reenter, lbl_Edit_Authen,
            lbl_Control,lbl_Profile,lbl_Report;
    JTextField txt_User_Name, txt_Password, txt_Reenter;;
 
   JPanel topPanel = new JPanel();
   JCheckBox chkb_01,chkb_02, chkb_03,chkb_04,chkb_05,chkb_06,
           chkb_07,chkb_08,chkb_09,chkb_10,chkb_11,chkb_12,
           chkb_13,chkb_14,chkb_15,chkb_16,chkb_17;
   JButton btn_New,btn_Search,btn_Save,btn_Update,btn_Delete;

   String checked,str;
    public Form_RegUser()
    {
        checked="";

        setSize(700,500);
        setTitle("Register New User");
        setMaximizable(true);
        setIconifiable(true);
        setClosable(true);
        setResizable(true);



        getContentPane().setLayout(grpLayout);
        grpLayout.setAutoCreateGaps(true);
        grpLayout.setAutoCreateContainerGaps(true);


        //getContentPane().add( panel_Content );
        content_Setting();


        grpLayout.setHorizontalGroup(
        grpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(grpLayout.createSequentialGroup()
                .addGap(20,20, 20)
                .addComponent(panel_Content, GroupLayout.PREFERRED_SIZE, 1500, GroupLayout.PREFERRED_SIZE)

             .addGroup(grpLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panel_Button, GroupLayout.PREFERRED_SIZE, 900, GroupLayout.PREFERRED_SIZE)

            ))
        );
        grpLayout.setVerticalGroup(
        grpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(grpLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(grpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(panel_Content)

                .addGap(20, 20, 20)
                .addGroup(grpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(panel_Button, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)))
        ));
    }


    void content_Setting()
    {
        panel_Content.setLayout(null);
        lbl_User_Name = new JLabel( "User Name:" );
        lbl_User_Name.setBounds( 50, 25, 80, 20 );
        panel_Content.add( lbl_User_Name );

        txt_User_Name= new JTextField();
        txt_User_Name.setBounds( 160, 25, 150, 20 );
        panel_Content.add( txt_User_Name );

        lbl_Password = new JLabel( "Password:" );
        lbl_Password.setBounds( 50, 75, 80, 20 );
        panel_Content.add( lbl_Password );

        txt_Password= new JPasswordField();
        txt_Password.setBounds( 160, 75, 150, 20 );
        panel_Content.add( txt_Password );

        lbl_Reenter = new JLabel( "Retype Password:" );
        lbl_Reenter.setBounds( 50, 125, 130,20 );
        panel_Content.add( lbl_Reenter );

        txt_Reenter = new JPasswordField();
        txt_Reenter.setBounds( 160, 125, 150, 20 );
        panel_Content.add( txt_Reenter );

        lbl_Edit_Authen= new JLabel( "Authentication:" );
        lbl_Edit_Authen.setBounds( 50, 175, 150, 20 );
        panel_Content.add( lbl_Edit_Authen );

        layeredPane = new JLayeredPane();
        layeredPane.setBorder(BorderFactory.createTitledBorder(" User Register "));
        layeredPane.setBounds( 160, 175, 750, 300);
        getControlPaneContent();
        panel_Content.add(layeredPane);

    }
    void getControlPaneContent()
    {
        layeredPane1 = new JLayeredPane();
        layeredPane1.setBorder(BorderFactory.createTitledBorder(" Control "));
        layeredPane1.setBounds( 35, 25, 140, 200 );
        layeredPane.add(layeredPane1);

        chkb_01= new JCheckBox("User Settings");
        chkb_01.setBounds(15,35,110,20);
        layeredPane1.add(chkb_01);
        /*chkb_01.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(chkb_01.isSelected()== true)
                    checked+="01,";
                else
                    checked+="";
            }
        });*/

        chkb_02= new JCheckBox("User Logs");
        chkb_02.setBounds(15,65 ,100,20);
        layeredPane1.add(chkb_02);
       
        layeredPane2 = new JLayeredPane();
        layeredPane2.setBorder(BorderFactory.createTitledBorder(" Profile "));
        layeredPane2 .setBounds(200,25, 370, 200);
        layeredPane.add( layeredPane2 );

        chkb_03= new JCheckBox("Candidate" );
        chkb_03.setBounds(15,15,90, 20);
        layeredPane2.add( chkb_03);
       
       chkb_04= new JCheckBox( "Save" );
        chkb_04.setBounds(25,45 , 70, 20);
       layeredPane2.add( chkb_04);
       chkb_04.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(chkb_04.isSelected()==true)
                    if(chkb_03.isSelected()==false)
                        chkb_03.setSelected(true);
            }
        });

       chkb_05= new JCheckBox( "Update" );
        chkb_05.setBounds(25,75 , 70, 20);
        layeredPane2.add( chkb_05);
         chkb_05.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(chkb_05.isSelected()==true)
                    if(chkb_03.isSelected()==false)
                        chkb_03.setSelected(true);
            }
        });


        chkb_06= new JCheckBox( "Delete" );
        chkb_06.setBounds(25,105 , 70, 20);
        layeredPane2.add( chkb_06);
         chkb_06.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(chkb_06.isSelected()==true)
                    if(chkb_03.isSelected()==false)
                        chkb_03.setSelected(true);
            }
        });
        chkb_03.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(chkb_03.isSelected()==false)
                {
                    chkb_04.setSelected(false);
                    chkb_05.setSelected(false);
                    chkb_06.setSelected(false);
                }
            }
        });
        
        chkb_07= new JCheckBox( "Company" );
        chkb_07.setBounds(150,15 , 90, 20);
        layeredPane2.add( chkb_07);

        chkb_08= new JCheckBox( "Save" );
        chkb_08.setBounds(160,45 , 70, 20);
        layeredPane2.add( chkb_08);
         chkb_08.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(chkb_08.isSelected()==true)
                    if(chkb_07.isSelected()==false)
                        chkb_07.setSelected(true);
            }
        });

        chkb_09= new JCheckBox( "Update" );
        chkb_09.setBounds(160,75 , 70, 20);
       layeredPane2.add( chkb_09);
        chkb_09.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(chkb_09.isSelected()==true)
                    if(chkb_07.isSelected()==false)
                        chkb_07.setSelected(true);
            }
        });


        chkb_10= new JCheckBox( "Delete" );
        chkb_10.setBounds(160,105, 70, 20);
        layeredPane2.add( chkb_10);
        chkb_10.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(chkb_10.isSelected()==true)
                    if(chkb_07.isSelected()==false)
                        chkb_07.setSelected(true);
            }
        });

        chkb_07.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(chkb_07.isSelected()==false)
                {
                    chkb_08.setSelected(false);
                    chkb_09.setSelected(false);
                    chkb_10.setSelected(false);
                }
            }
        });

       chkb_11= new JCheckBox( "Jobs" );
       chkb_11.setBounds(285,15 , 70, 20);
       layeredPane2.add( chkb_11);


       chkb_12= new JCheckBox( "Save" );
       chkb_12.setBounds(295,45 , 70,20);
       layeredPane2.add( chkb_12);
        chkb_12.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(chkb_12.isSelected()==true)
                    if(chkb_11.isSelected()==false)
                        chkb_11.setSelected(true);
            }
        });

       chkb_13= new JCheckBox( "Update" );
       chkb_13.setBounds(295,75 , 70, 20);
       layeredPane2.add( chkb_13);
        chkb_13.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(chkb_13.isSelected()==true)
                    if(chkb_11.isSelected()==false)
                        chkb_11.setSelected(true);
            }
        });


       chkb_14= new JCheckBox( "Delete" );
       chkb_14.setBounds(295,105, 70,20);
       layeredPane2.add( chkb_14);
        chkb_14.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(chkb_14.isSelected()==true)
                    if(chkb_11.isSelected()==false)
                        chkb_11.setSelected(true);
            }
        });

        chkb_11.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(chkb_11.isSelected()==false)
                {
                    chkb_12.setSelected(false);
                    chkb_13.setSelected(false);
                    chkb_14.setSelected(false);
                }
            }
        });

        layeredPane3 = new JLayeredPane();
        layeredPane3.setBorder(BorderFactory.createTitledBorder(" Report "));
        layeredPane3 .setBounds( 590, 25, 120, 200 );
        layeredPane.add(layeredPane3 );

       chkb_15= new JCheckBox( "Candidate" );
      chkb_15.setBounds(15,35 , 90, 20);
       layeredPane3.add( chkb_15);

   chkb_16= new JCheckBox( "Company" );
      chkb_16.setBounds(15,65 , 100, 20);
       layeredPane3.add( chkb_16);

        chkb_17= new JCheckBox( "Job" );
       chkb_17.setBounds(15,95 , 90, 20);
        layeredPane3.add( chkb_17);

        btn_New=new JButton("New",new ImageIcon("icons/btn_new.png"));
        btn_New.setBounds(170,520 ,80 ,30 );
        btn_New.setMargin(new Insets(1,1,1,1));
        btn_New.setHorizontalAlignment(SwingConstants.CENTER);
        panel_Content.add( btn_New );
        
        
        btn_New.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                clearallfields();
                //search_UserInfo();
            }
        });

        btn_Search=new JButton("Search",new ImageIcon("icons/search.png"));
        btn_Search.setBounds(270,520 ,80 ,30 );
        btn_Search.setMargin(new Insets(1,1,1,1));
        btn_Search.setHorizontalAlignment(SwingConstants.CENTER);
        panel_Content.add( btn_Search );
        btn_Search.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                search_UserInfo();
            }
        });

        btn_Save=new JButton("Save",new ImageIcon("icons/btn_save.png"));
        btn_Save.setBounds(370,520 ,65 ,30 );
        btn_Save.setMargin(new Insets(1,1,1,1));
        btn_Save.setHorizontalAlignment(SwingConstants.CENTER);
        panel_Content.add( btn_Save );
        
        btn_Save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if(checkValidation()==true)
                {                   
                    save_UserInfo();
                    clearallfields();
                    
                }
            }
        });


         btn_Update=new JButton("Update",new ImageIcon("icons/btn_update.png"));
         btn_Update.setBounds(455,520 ,80 ,30 );
         btn_Update.setMargin(new Insets(1,1,1,1));
         btn_Update.setHorizontalAlignment(SwingConstants.CENTER);
         panel_Content.add( btn_Update );

         btn_Update.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if(checkValidation()==true)
                {
                    update_UserInfo();
                }
        }});

         btn_Delete=new JButton("Delete",new ImageIcon("icons/btn_delete.png"));
         btn_Delete.setBounds(555,520 ,75 ,30 );
         btn_Delete.setMargin(new Insets(1,1,1,1));
         btn_Delete.setHorizontalAlignment(SwingConstants.CENTER);
         panel_Content.add( btn_Delete );
         
         btn_Delete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {                 
                delete_UserInfo();
                clearallfields();
            }
        });

    }

    void save_UserInfo()
    {
        //Insert in the tbl_user_auth
        try
        {
           str="Insert into  tbl_user_auth values(?,?,?)";
           DBConnect.connect();
           DBConnect.conn.setAutoCommit(false);
           DB_Query.ps = DBConnect.conn.prepareStatement(str);

           DB_Query.ps.setString(1, txt_User_Name.getText());
           DB_Query.ps.setString(2, txt_Password.getText() );
           checked="";
           if(chkb_01.isSelected()== true)
                    checked+="01,";
           else
                checked+="";
           if(chkb_02.isSelected()== true)
                    checked+="02,";
           else
                checked+="";
           if(chkb_03.isSelected()== true)
                    checked+="03,";
           else
                checked+="";
           if(chkb_04.isSelected()== true)
                    checked+="04,";
           else
                checked+="";
           if(chkb_05.isSelected()== true)
                    checked+="05,";
           else
                checked+="";
           if(chkb_06.isSelected()== true)
                    checked+="06,";
           else
                checked+="";
           if(chkb_07.isSelected()== true)
                    checked+="07,";
           else
                checked+="";
           if(chkb_08.isSelected()== true)
                    checked+="08,";
           else
                checked+="";
           if(chkb_09.isSelected()== true)
                    checked+="09,";
           else
                checked+="";

                checked+="";
           if(chkb_10.isSelected()== true)
                    checked+="10,";
           else
                checked+="";
           if(chkb_11.isSelected()== true)
                    checked+="11,";
           else
                checked+="";
           if(chkb_12.isSelected()== true)
                    checked+="12,";
           else
                checked+="";
           if(chkb_13.isSelected()== true)
                    checked+="13,";
           else
                checked+="";
           if(chkb_14.isSelected()== true)
                    checked+="14,";
           else
                checked+="";
           if(chkb_15.isSelected()== true)
                    checked+="15,";
           else
                checked+="";
           if(chkb_16.isSelected()== true)
                    checked+="16,";
           else
                checked+="";
           if(chkb_17.isSelected()== true)
                    checked+="17,";
           else
                checked+="";
           DB_Query.ps.setString(3, checked);
           DB_Query.dclQuery("insert");
           if(DB_Query.duplicate!=true)
           JOptionPane.showMessageDialog(null, "Data Has Been Inserted Successfully.");
        }
        catch(SQLException e)
        {
           JOptionPane.showMessageDialog(null, "Error Occured :" + e);
        }
    }
    void search_UserInfo()
    {
        String cond,table;
       table="tbl_user_auth";

                cond= "user_name = '" + txt_User_Name.getText() +"'";


        DB_Query.selectAllQuery(table, cond);
        try
        {

            if(DB_Query.rs.next())
            {
               String user_name  = DB_Query.rs.getString("user_name");
               String password = DB_Query.rs.getString("password");
               String authentications = DB_Query.rs.getString("authentications");

               txt_User_Name.setText(user_name);
               txt_Password.setText(password);
               txt_Reenter.setText(password);
               if(authentications != null)
               {

                   if(authentications.indexOf("01")>=0)
                   {
                       chkb_01.setSelected(true);
                   }
                   if(authentications.indexOf("02")>=0)
                   {
                       chkb_02.setSelected(true);
                   }
                   if(authentications.indexOf("03")>=0)
                   {
                       chkb_03.setSelected(true);
                   }
                   if(authentications.indexOf("04")>=0)
                   {
                       chkb_04.setSelected(true);
                   }
                   if(authentications.indexOf("05")>=0)
                   {
                       chkb_05.setSelected(true);
                   }
                   if(authentications.indexOf("06")>=0)
                   {
                       chkb_06.setSelected(true);
                   }
                   if(authentications.indexOf("07")>=0)
                   {
                       chkb_07.setSelected(true);
                   }
                   if(authentications.indexOf("08")>=0)
                   {
                       chkb_08.setSelected(true);
                   }
                   if(authentications.indexOf("09")>=0)
                   {
                       chkb_09.setSelected(true);
                   }
                   if(authentications.indexOf("09")>=0)
                   {
                       chkb_09.setSelected(true);
                   }
                   if(authentications.indexOf("10")>=0)
                   {
                       chkb_10.setSelected(true);
                   }
                   if(authentications.indexOf("11")>=0)
                   {
                       chkb_11.setSelected(true);
                   }
                   if(authentications.indexOf("12")>=0)
                   {
                       chkb_12.setSelected(true);
                   }
                   if(authentications.indexOf("13")>=0)
                   {
                       chkb_13.setSelected(true);
                   }
                   if(authentications.indexOf("14")>=0)
                   {
                       chkb_14.setSelected(true);
                   }
                   if(authentications.indexOf("15")>=0)
                   {
                       chkb_15.setSelected(true);
                   }
                   if(authentications.indexOf("16")>=0)
                   {
                       chkb_16.setSelected(true);
                   }
                   if(authentications.indexOf("17")>=0)
                   {
                       chkb_17.setSelected(true);
                   }
               }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "User not found.");
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        catch(NumberFormatException err)
        {
            //JOptionPane.showMessageDialog(null, "Please enter numeric parameter.");
        }
        finally
        {
           if (DBConnect.conn != null)
           {
               try
               {
                   DB_Query.rs.close();
                   DB_Query.s.close ();
                   DBConnect.conn.close();

               }
               catch (Exception e) {
                //Ignore
                }
           }
        }
    }

    void update_UserInfo()
    {
        try
       {
           str="Update tbl_user_auth set password=?, authentications=? Where user_name=?";
           DBConnect.connect();
           DBConnect.conn.setAutoCommit(false);
           DB_Query.ps = DBConnect.conn.prepareStatement(str);
           DB_Query.ps.setString(1, txt_Password.getText());
           checked="";
           if(chkb_01.isSelected()== true)
                    checked+="01,";
           else
                checked+="";
           if(chkb_02.isSelected()== true)
                    checked+="02,";
           else
                checked+="";
           if(chkb_03.isSelected()== true)
                    checked+="03,";
           else
                checked+="";
           if(chkb_04.isSelected()== true)
                    checked+="04,";
           else
                checked+="";
           if(chkb_05.isSelected()== true)
                    checked+="05,";
           else
                checked+="";
           if(chkb_06.isSelected()== true)
                    checked+="06,";
           else
                checked+="";
           if(chkb_07.isSelected()== true)
                    checked+="07,";
           else
                checked+="";
           if(chkb_08.isSelected()== true)
                    checked+="08,";
           else
                checked+="";
           if(chkb_09.isSelected()== true)
                    checked+="09,";
           else
                checked+="";

           if(chkb_10.isSelected()== true)
                    checked+="10,";
           else
                checked+="";
           if(chkb_11.isSelected()== true)
                    checked+="11,";
           else
                checked+="";
           if(chkb_12.isSelected()== true)
                    checked+="12,";
           else
                checked+="";
           if(chkb_13.isSelected()== true)
                    checked+="13,";
           else
                checked+="";
           if(chkb_14.isSelected()== true)
                    checked+="14,";
           else
                checked+="";
           if(chkb_15.isSelected()== true)
                    checked+="15,";
           else
                checked+="";
           if(chkb_16.isSelected()== true)
                    checked+="16,";
           else
                checked+="";
           if(chkb_17.isSelected()== true)
                    checked+="17,";
           else
                checked+="";
           DB_Query.ps.setString(2, checked);
           DB_Query.ps.setString(3, txt_User_Name.getText());
           DB_Query.dclQuery("update");
           JOptionPane.showMessageDialog(null, "Data Has Been Updated Successfully.");
       }
       catch(SQLException e)
       {
           JOptionPane.showMessageDialog(null, "Error Occured :" + e);
       }
    }
     void clearallfields()
    {
         txt_Password.setText("");
         txt_Reenter.setText("");
         txt_User_Name.setText("");
         chkb_01.setSelected(false);
         chkb_02.setSelected(false);
         chkb_03.setSelected(false);
         chkb_04.setSelected(false);
         chkb_05.setSelected(false);
         chkb_06.setSelected(false);
         chkb_07.setSelected(false);
         chkb_08.setSelected(false);
         chkb_09.setSelected(false);
          chkb_10.setSelected(false);
         chkb_11.setSelected(false);
         chkb_12.setSelected(false);
         chkb_13.setSelected(false);
         chkb_14.setSelected(false);
          chkb_15.setSelected(false);
         chkb_16.setSelected(false);
          chkb_17.setSelected(false);

     }
    void delete_UserInfo()
    {
       String str;

       try
       {
           //Delete From The tbl_cand_general
           DBConnect.connect();
           str="Delete from tbl_user_auth where user_name=?;";
           DBConnect.conn.setAutoCommit(false);
           DB_Query.ps = DBConnect.conn.prepareStatement(str);
           DB_Query.ps.setString(1, txt_User_Name.getText());
           DB_Query.dclQuery("delete");

           JOptionPane.showMessageDialog(null, "Data Has Been Deleted Successfully.");
       }
       catch(SQLException e)
       {
           JOptionPane.showMessageDialog(null, "Error Occured :" + e);
       }
    }

    boolean checkValidation()
    {
        Validation objValid= new Validation();
        if(objValid.validUserName(txt_User_Name.getText())==false)
        {
            txt_User_Name.requestFocus();
            return false;
        }
        if(objValid.validPassWord(txt_Password.getText(),txt_Reenter.getText())==false)
        {
            txt_Reenter.requestFocus();
            return false;
        }
        return true;
    }     
}