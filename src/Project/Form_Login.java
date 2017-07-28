/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Project;

/**
 *
 * @author prashant-lappy
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;


class Form_Login extends JFrame{
    private JLabel lbl_UserName,lbl_Password,lbl_Title,lbl_Image,lbl_Err_Msg;
	
    public JTextField txt_UserName;
    private JPasswordField txt_Password;
    private JButton btn_Login,btn_Cancel;

    private JPanel panel_Main;
    private Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    public Form_Login()
    {
        super ("SIS Login");
         //MainMenu mainmenu=new MainMenu();
        lbl_Err_Msg=new JLabel("");
        lbl_Err_Msg.setForeground(Color.red);
        lbl_Err_Msg.setHorizontalAlignment(0);
        lbl_UserName=new JLabel("User Name:");
        lbl_Password=new JLabel("Password:");               
        txt_UserName=new JTextField(100);
        txt_Password=new JPasswordField(100);
        
        btn_Login=new JButton ("Login");
        btn_Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                checkAuthentication();
              }
        });
        btn_Cancel=new JButton("Cancel");
        btn_Cancel.addActionListener(new ActionListener() {
            @Override
        public void actionPerformed(ActionEvent ae) {
            System.exit(0);
          }
        });
        lbl_Title=new JLabel("Login Pengguna");
        lbl_Title.setFont(new Font("Serif", Font.BOLD, 24));
        lbl_Title.setHorizontalAlignment(0);



        lbl_Image=new JLabel(new ImageIcon("icons/login.png"));

        panel_Main=new JPanel (null);
        getContentPane().add(panel_Main);
        
        //panel_Main.setPreferredSize(new Dimension(200,200));
        lbl_Title.setBounds(0, 5, 300, 40);
        panel_Main.add(lbl_Title);
        lbl_Image.setBounds(10, 15, 40, 40);
        panel_Main.add(lbl_Image);
        lbl_UserName.setBounds(40, 50, 70, 20);
        panel_Main.add(lbl_UserName);
        txt_UserName.setBounds(120, 50, 150, 20);
        panel_Main.add(txt_UserName);
        lbl_Password.setBounds(40, 90, 70, 20);
        panel_Main.add(lbl_Password);
        txt_Password.setBounds(120, 90, 150, 20);
        panel_Main.add(txt_Password);
        lbl_Err_Msg.setBounds(0,115,300,20);
        panel_Main.add(lbl_Err_Msg);
        btn_Login.setBounds(60, 135, 80, 20);
        panel_Main.add(btn_Login);
        btn_Cancel.setBounds(150, 135, 80, 20);
        panel_Main.add(btn_Cancel);

        txt_UserName.setText("");
        txt_Password.setText("");
        addWindowListener(new WindowAdapter() {
           @Override
           public void windowClosing(WindowEvent e)
           {
                System.exit(0);				 
           }
           
        });
        txt_UserName.addKeyListener(new KeyAdapter() {
           @Override
           public void keyPressed(KeyEvent e)
           {
               if(e.getKeyCode()==10)
               {
                   checkAuthentication();
               }
           }
           
        });
        txt_Password.addKeyListener(new KeyAdapter() {
           @Override
           public void keyPressed(KeyEvent e)
           {
               if(e.getKeyCode()==10)
               {
                   checkAuthentication();
               }
           }
           
        });
        
        setVisible(true);
        setDefaultLookAndFeelDecorated(true);
        setSize(300, 200);
        setResizable(false);

        setLocation((this.screen.width/2)-150,(screen.height/2)-100);
    }     
        
    
    void checkAuthentication()
    {
        String cond,table;
       table="tbl_user_auth";

        cond= "user_name = '" + txt_UserName.getText() +"'";


        DB_Query.selectAllQuery(table, cond);
        try
        {

            if(DB_Query.rs.next())
            {
               String user_name  = DB_Query.rs.getString("user_name");
               String password = DB_Query.rs.getString("password");
               //Main.user_auth="";
               Main.user_auth = DB_Query.rs.getString("authentications");
               //JOptionPane.showMessageDialog(null, Main.user_auth);
               if(txt_UserName.getText().equalsIgnoreCase(user_name))
                {
                    if(txt_Password.getText().equals(password))
                    {
                        this.dispose();
                        //MenuControl mc=new MenuControl(null);
                        authControl();
                        Main.mainClass.setVisible(true);
                        getLogId();
                        logIn();
                        StatusBar.userName.setText("  Current User: " + user_name);
                    }
                    else
                    {
                        lbl_Err_Msg.setText("Please enter correct Password.");
                    }
                }
                else
                {
                    lbl_Err_Msg.setText("Please enter correct User Name.");
                }        
               
            }
            else
            {
                lbl_Err_Msg.setText("Please enter correct User Name.");
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
    void getLogId()
    {

        try
        {

           String table;
           table="tbl_user_login";
           DB_Query.selectFieldQuery(table,"(MAX(Log_Id)+1)  as Log_Id" ,null);

            if(DB_Query.rs.next())
            {   
                if(DB_Query.rs.getInt("Log_Id")>0)
                    Main.global_LogId=DB_Query.rs.getInt("Log_Id");
                else
                {                    
                    Main.global_LogId=1;
                }
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
    }
    void logIn()
    {
        try
       {
           String str="Insert into  tbl_user_login values(?,?,?)";
           DBConnect.connect();
           DBConnect.conn.setAutoCommit(false);
           DB_Query.ps = DBConnect.conn.prepareStatement(str);
           DB_Query.ps.setInt(1, Main.global_LogId);
           DB_Query.ps.setString(2, txt_UserName.getText() );
           DB_Query.ps.setString(3, null);
           DB_Query.dclQuery("insert");
       }
       catch(SQLException e)
       {
           JOptionPane.showMessageDialog(null, "Error Occured :" + e);
       }
    }
    void authControl()
    {
        
        if(Main.user_auth.indexOf("01")<0 && Main.user_auth.indexOf("02")<0)
        {
            Main.mc.setVisible(false);
        }
        else
        {
             Main.mc.setVisible(true);
            if(Main.user_auth.indexOf("01")>=0)
            {
                Main.mc.mnu_UserPreferences.setVisible(true);
            }
            else
            {
                Main.mc.mnu_UserPreferences.setVisible(false);
            }
            if(Main.user_auth.indexOf("02")>=0)
            {
                Main.mc.mnu_UserLogs.setVisible(true);
            }
            else
            {
                Main.mc.mnu_UserLogs.setVisible(false);
            }
        }
        if(Main.user_auth.indexOf("03")<0 && Main.user_auth.indexOf("07")<0 && Main.user_auth.indexOf("11")<0)
        {
            Main.mp.setVisible(false);
            Main.toolbar.btn_Candidate.setVisible(false);
            Main.toolbar.btn_Company.setVisible(false);
            Main.toolbar.btn_Job.setVisible(false);
        }
        else
        {

            Main.mp.setVisible(true);
            if(Main.user_auth.indexOf("03")>=0)
            {
                Main.mp.mnu_Candidate.setVisible(true);
                Main.toolbar.btn_Candidate.setVisible(true);
            }
            else
            {
                Main.mp.mnu_Candidate.setVisible(false);
                Main.toolbar.btn_Candidate.setVisible(false);
            }
            if(Main.user_auth.indexOf("07")>=0)
            {
                Main.mp.mnu_Company.setVisible(true);
                Main.toolbar.btn_Company.setVisible(true);
            }
            else
            {
                Main.mp.mnu_Company.setVisible(false);
                Main.toolbar.btn_Company.setVisible(false);
            }
            if(Main.user_auth.indexOf("11")>=0)
            {
                Main.mp.mnu_Job.setVisible(true);
                Main.toolbar.btn_Job.setVisible(true);
            }
            else
            {
                Main.mp.mnu_Job.setVisible(false);
                Main.toolbar.btn_Job.setVisible(false);
            }
        }
       
        if(Main.user_auth.indexOf("15")<0 && Main.user_auth.indexOf("16")<0 && Main.user_auth.indexOf("17")<0)
        {
            Main.mr.setVisible(false);
            Main.toolbar.btn_Candidate_Report.setVisible(false);
            Main.toolbar.btn_Company_Report.setVisible(false);
            Main.toolbar.btn_Job_Report.setVisible(false);
        }
        else
        {
            Main.mr.setVisible(true);
            if(Main.user_auth.indexOf("15")>=0)
            {
                Main.mr.mnu_Candidate.setVisible(true);
                Main.toolbar.btn_Candidate_Report.setVisible(true);
            }
            else
            {
                Main.mr.mnu_Candidate.setVisible(false);
                Main.toolbar.btn_Candidate_Report.setVisible(false);
            }
            if(Main.user_auth.indexOf("16")>=0)
            {
                Main.mr.mnu_Company.setVisible(true);
                Main.toolbar.btn_Company_Report.setVisible(true);
            }
            else
            {
                Main.mr.mnu_Company.setVisible(false);
                Main.toolbar.btn_Company_Report.setVisible(false);
            }
            if(Main.user_auth.indexOf("17")>=0)
            {
                Main.mr.mnu_Job.setVisible(true);
                Main.toolbar.btn_Job_Report.setVisible(true);
            }
            else
            {
                Main.mr.mnu_Job.setVisible(false);
                Main.toolbar.btn_Job_Report.setVisible(false);
            }
        }
        
    }
              
}
