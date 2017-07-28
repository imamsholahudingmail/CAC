/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
import java.io.*;
import java.util.Date;
import java.util.*;

class Form_Company extends JInternalFrame
{
    JPanel mainPanel;


    private JTabbedPane tabbedPane;
    private JPanel      panel_General_Info,panel_Info_Content,panel_Professional_Info,panel_Primary_Info;
    private             JPanel          panel_Buttons;

    GridBagLayout mainLayout=new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();


    GroupLayout grpLayout = new GroupLayout(getContentPane());
    JPanel topPanel = new JPanel();


    JLabel lbl_Search,lbl_Name,lbl_Code,lbl_Address,lbl_Contact1,lbl_Contact2,lbl_Size,lbl_Sal,lbl_Web,lbl_Hr,lbl_Email,lbl_Emp,lbl_Contact_No,lbl_Fax,lbl_Logo;
    JTextField  txt_Search,txt_Name,txt_Code,txt_Contact1,txt_Contact2,txt_Size,txt_Web,txt_Sal,txt_Hr,txt_Email,txt_Contact_No,txt_Fax;
    JComboBox cmb_Sal, cmb_SearchBy;
    JTextArea txa_Address;
    JCheckBox chkb_Dental,chkb_Medical,chkb_Retirement,chkb_Site;
    JFileChooser flc_Picture;
    JButton btn_Search,btn_Logo_Change,btn_New,btn_Save,btn_Update,
            btn_Edit,btn_Delete,btn_Cancel;

    JFileChooser flc_Logo;

    String dental,medical,retirement,site;
    String logo,logoLocation,searched_logo,benefits,found_logo;
     private String global_Code;
     boolean found=false;
    public Form_Company()
    {
        benefits=searched_logo=logo=null;
        
        setSize(700,500);
        setTitle("Data Staf");
        setMaximizable(true);
        setIconifiable(true);
        setClosable(true);
        setResizable(true);
        topPanel.setLayout( new BorderLayout() );
        getContentPane().add( topPanel);



        tabbedPane = new JTabbedPane();


        getContentPane().setLayout(grpLayout);
        grpLayout.setAutoCreateGaps(true);
        grpLayout.setAutoCreateContainerGaps(true);

        lbl_Code=new JLabel("Code No :");
        txt_Code=new JTextField();
        lbl_Name=new JLabel("Name:");
        txt_Name=new JTextField();

         panel_General_Info=new JPanel();
         createGeneralInfo();
         panel_Info_Content=new JPanel();
         panel_Info_Content.setLayout(new BorderLayout());
         getContentPane().add( panel_General_Info );
         tabbedPane.addTab("General Info",panel_General_Info);
         panel_Info_Content.add(tabbedPane);

          panel_Professional_Info=new JPanel();
         createProfessionalInfo();
         panel_Info_Content=new JPanel();
         panel_Info_Content.setLayout(new BorderLayout());
         getContentPane().add( panel_Professional_Info );
         tabbedPane.addTab("Professional Info",panel_Professional_Info);
         panel_Info_Content.add(tabbedPane);


        String[] searchBy = {"Kode","Nama"};
        getContentPane().setLayout(grpLayout);
        grpLayout.setAutoCreateGaps(true);
        grpLayout.setAutoCreateContainerGaps(true);


        lbl_Search=new JLabel(new ImageIcon("icons/search.png"));
        txt_Search=new JTextField();
        Font font = new Font("Serif", Font.PLAIN,12);
        cmb_SearchBy=new JComboBox(searchBy);
        cmb_SearchBy.setFont(font);
        btn_Search=new JButton( "Cari");
         btn_Search.addActionListener(new ActionListener() {
            @Override

         public void actionPerformed(ActionEvent ae) {

           if(checkSearchValidation()==true)
            {
               showPrimaryData();
                if(global_Code!=null)
                {
                    
                    disableAllButtons();
                    disableAllFields();
                    showGeneralData();
                    showProfessionalData();
                    txt_Search.setText("");
                    if(found==true)
                    {
                        btn_Edit.setEnabled(true);
                        btn_Delete.setEnabled(true);
                        btn_Cancel.setEnabled(true);
                        found=false;
                    }

                }
            }
          }
        });
        primaryInfoSettings();
        panelButtonsSettting();
       // panelPrimaryInfoSetting();



        grpLayout.setHorizontalGroup(
            grpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(grpLayout.createSequentialGroup()
            .addGap(250,250, 250)
                .addComponent(lbl_Search,GroupLayout.PREFERRED_SIZE, 24,GroupLayout.PREFERRED_SIZE)
                .addComponent(txt_Search, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                .addComponent(cmb_SearchBy, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                .addComponent(btn_Search, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))

             .addGroup(grpLayout.createSequentialGroup()
            .addGap(70,70,70)
                .addComponent(panel_Primary_Info))

             .addGroup(grpLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panel_Info_Content, GroupLayout.PREFERRED_SIZE, 900, GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(panel_Buttons, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
            )
        );



        grpLayout.setVerticalGroup
                (
            grpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(grpLayout.createSequentialGroup()
                .addGap(10,10,10)
                .addGroup(grpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_Search,GroupLayout.PREFERRED_SIZE, 25,GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Search, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_SearchBy, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Search, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))

                .addGap(50,50,50)
                .addGroup(grpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(panel_Primary_Info, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))

                 .addGap(10, 10, 10)
                .addGroup(grpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(panel_Info_Content)
                    .addGroup(grpLayout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(panel_Buttons)))
            ));
        
        disableAllFields();
        disableAllButtons();
     }
void primaryInfoSettings()
{
    lbl_Code=new JLabel("Kode :");
    txt_Code=new JTextField();
    lbl_Name=new JLabel("Nama :");
    txt_Name=new JTextField();
    ImageIcon pic = new ImageIcon("Logo/none.jpg");
    lbl_Logo=new JLabel(pic);
    lbl_Logo.setBorder(BorderFactory.createLineBorder(Color.white));
    lbl_Logo.setVisible(false);
    btn_Logo_Change = new JButton("Change");
    //
    ImageIcon log = new ImageIcon("Logo/none.jpg");
    lbl_Logo=new JLabel(log);
    lbl_Logo.setBorder(BorderFactory.createLineBorder(Color.white));
    btn_Logo_Change = new JButton("Change");
    btn_Logo_Change.setVisible(false);
    flc_Logo=new JFileChooser();
    //FileFilter type1 = new ExtensionFilter("Image files",new String[] { ".jpg", ".gif", ".jpeg", ".png" });
    //flc_Logo.addChoosableFileFilter(type1);
    btn_Logo_Change.addActionListener(new ActionListener() {
        @Override
    public void actionPerformed(ActionEvent ae) {
        int returnVal = flc_Logo.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = flc_Logo.getSelectedFile();
            //This is where a real application would open the file.
            lbl_Logo.setIcon(new ImageIcon(file.getAbsolutePath()));
            logo=file.getName();
            logoLocation=file.getAbsolutePath();

        } else {
            logo=null;
        }
      }
    });


    panel_Primary_Info=new JPanel();
    panel_Primary_Info.setLayout(null);
    lbl_Code.setBounds(10,10,75,20);
    panel_Primary_Info.add(lbl_Code);
    txt_Code.setBounds(100,10,200,20);
    panel_Primary_Info.add(txt_Code);
    lbl_Name.setBounds(10,40,75,20);
    panel_Primary_Info.add(lbl_Name);
    txt_Name.setBounds(100,40,200,20);
    panel_Primary_Info.add(txt_Name);
    //lbl_Logo.setBounds(735,0,150,150);
    //panel_Primary_Info.add(lbl_Logo);
    //btn_Logo_Change.setBounds(775,155,80,20);
    //panel_Primary_Info.add(btn_Logo_Change);
    
}
void panelButtonsSettting()
    {
        panel_Buttons=new JPanel();
        panel_Buttons.setLayout( null );
        panel_Buttons.setBorder(BorderFactory.createLineBorder(Color.black));
        btn_New=new JButton(new ImageIcon("icons/add.png"));
        btn_New.setBounds( 5, 5, 40, 40 );

        btn_New.setToolTipText("New Record");
        btn_New.addActionListener(new ActionListener() {
            @Override
        public void actionPerformed(ActionEvent ae) {
            
            txt_Name.requestFocus();
            disableAllButtons();
            enableAllFields();
            btn_Save.setEnabled(true);
            btn_Cancel.setEnabled(true);
            clearAllFields();
            getNewCode();
          }
        });
        panel_Buttons.add(btn_New);
        btn_Save=new JButton(new ImageIcon("icons/save.png"));
        btn_Save.setBounds( 5, 50, 40, 40 );
        btn_Save.setToolTipText("Save Record");
        panel_Buttons.add(btn_Save);
        btn_Save.addActionListener(new ActionListener() {
            @Override
        public void actionPerformed(ActionEvent ae) {
            if(checkValidation()==true)
            {
                insertCompany();
                clearAllFields();
                disableAllFields();
            }
            
          }
        });
        btn_Edit=new JButton(new ImageIcon("icons/edit.png"));
        btn_Edit.setBounds( 5, 95, 40, 40 );
        btn_Edit.setToolTipText("Edit Record");
        btn_Edit.addActionListener(new ActionListener() {
            @Override
        public void actionPerformed(ActionEvent ae) {
                disableAllButtons();
                btn_Update.setEnabled(true);
                btn_Cancel.setEnabled(true);
                enableAllFields();
          }
        });
        panel_Buttons.add(btn_Edit);
        btn_Update=new JButton(new ImageIcon("icons/update.png"));
        btn_Update.setBounds( 5, 140, 40, 40 );
        btn_Update.setToolTipText("Update Record");
        btn_Update.addActionListener(new ActionListener() {
            @Override
        public void actionPerformed(ActionEvent ae) {
                updateCandidate();
                disableAllButtons();
                disableAllFields();
                clearAllFields();
          }
        });
        panel_Buttons.add(btn_Update);
        btn_Delete=new JButton(new ImageIcon("icons/delete.png"));
        btn_Delete.addActionListener(new ActionListener() {
            @Override
        public void actionPerformed(ActionEvent ae) {
                deleteCompany();
                disableAllButtons();
                disableAllFields();
                clearAllFields();
          }
        });
        btn_Delete.setBounds( 5, 185, 40, 40 );
        btn_Delete.setToolTipText("Delete Record");
        panel_Buttons.add(btn_Delete);

        btn_Cancel=new JButton(new ImageIcon("icons/cancel.png"));
        btn_Cancel.addActionListener(new ActionListener() {
            @Override
        public void actionPerformed(ActionEvent ae) {
            disableAllButtons();
            disableAllFields();
            clearAllFields();
          }
        });
        btn_Cancel.setBounds( 5, 230, 40, 40 );
        btn_Cancel.setToolTipText("Cancel Operation");
        panel_Buttons.add(btn_Cancel);


        if(Main.user_auth.indexOf("08")>=0)
        {
            btn_New.setVisible(true);
            btn_Save.setVisible(true);
        }
        else
        {
            btn_New.setVisible(false);
            btn_Save.setVisible(false);
        }
        if(Main.user_auth.indexOf("09")>=0)
        {
            btn_Edit.setVisible(true);
            btn_Update.setVisible(true);
        }
        else
        {
            btn_Edit.setVisible(false);
            btn_Update.setVisible(false);
        }
        if(Main.user_auth.indexOf("10")>=0)
        {
            btn_Delete.setVisible(true);
        }
        else
        {
            btn_Delete.setVisible(false);
        }
    }


    void createGeneralInfo()
    {
        panel_General_Info.setLayout(null);

        lbl_Contact1 = new JLabel( "Contact No1:" );
        lbl_Contact1.setBounds( 50, 25, 80, 20 );
        panel_General_Info.add( lbl_Contact1 );

        txt_Contact1= new JTextField();
        txt_Contact1.setBounds( 150, 25, 150, 20 );
        panel_General_Info.add( txt_Contact1 );

        lbl_Contact2 = new JLabel( "Contact No2:" );
        lbl_Contact2.setBounds( 50, 75, 80, 20 );
        panel_General_Info.add( lbl_Contact2 );



        txt_Contact2= new JTextField();
        txt_Contact2.setBounds( 150, 75, 150, 20 );
        panel_General_Info.add( txt_Contact2 );

        lbl_Web = new JLabel( "Web Site:" );
        lbl_Web.setBounds( 50, 125, 70, 20 );
        panel_General_Info.add( lbl_Web );

        txt_Web= new JTextField();
        txt_Web.setBounds( 150, 125, 150, 20 );
        panel_General_Info.add( txt_Web );

        lbl_Size= new JLabel( "Size of Organization:" );
        lbl_Size.setBounds( 50, 175, 80, 20 );
        panel_General_Info.add( lbl_Size );

        txt_Size= new JTextField();
        txt_Size.setBounds( 150, 175, 150, 20 );
        panel_General_Info.add( txt_Size );

        lbl_Address= new JLabel( "Address:" );
        lbl_Address.setBounds( 600, 25, 100, 20 );
        panel_General_Info.add( lbl_Address );

        txa_Address = new JTextArea();
        txa_Address.setLineWrap(true);
        txa_Address.setWrapStyleWord(true);
        JScrollPane scrollPane_Address = new JScrollPane(txa_Address);
        scrollPane_Address.setVerticalScrollBarPolicy(
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_Address.setPreferredSize(new Dimension(150, 150));
        scrollPane_Address.setBounds(700, 25, 150, 80);
        panel_General_Info.add(scrollPane_Address);

    }


   void createProfessionalInfo()
    {
        panel_Professional_Info.setLayout(null);

        lbl_Sal = new JLabel( "Salary Scale:" );
        lbl_Sal.setBounds( 50, 25, 80, 20 );

        panel_Professional_Info.add(lbl_Sal);
        lbl_Sal.setVisible(false);



        String[] sal_size = { "10000-20000", "20000-30000", "30000-40000", "40000-50000", "50000-60000", "50000-More" };

        cmb_Sal=new JComboBox(sal_size);

        cmb_Sal.setFont(new Font("Serif", Font.PLAIN,12));
        cmb_Sal.setBounds( 150, 25, 100, 20 );
        panel_Professional_Info.add( cmb_Sal );
        cmb_Sal.setVisible(false);

         lbl_Hr= new JLabel( "HR Manager:" );
        lbl_Hr.setBounds( 50, 25, 80, 20 );
        panel_Professional_Info.add( lbl_Hr );

        txt_Hr= new JTextField();
        txt_Hr.setBounds( 150, 25, 150, 20 );
        panel_Professional_Info.add( txt_Hr );


        lbl_Email= new JLabel( "Email Id:" );
        lbl_Email.setBounds( 50, 75, 80, 20 );
        panel_Professional_Info.add( lbl_Email );

        txt_Email= new JTextField();
        txt_Email.setBounds( 150, 75, 150, 20);
        panel_Professional_Info.add( txt_Email );

        lbl_Contact_No = new JLabel( "Contact No:" );
        lbl_Contact_No.setBounds( 50, 125, 80, 20);
        panel_Professional_Info.add( lbl_Contact_No );

        txt_Contact_No= new JTextField();
        txt_Contact_No.setBounds( 150, 125, 150, 20 );
        panel_Professional_Info.add( txt_Contact_No );

        lbl_Emp = new JLabel( "Employee benefit:" );
        lbl_Emp.setBounds( 600, 25, 100, 20 );
        panel_Professional_Info.add( lbl_Emp );



        chkb_Dental=new JCheckBox( "Dental Plan" );
        chkb_Dental.setBounds(725, 25, 150,20);
        panel_Professional_Info.add( chkb_Dental );
        /*chkb_Dental.addActionListener(new ActionListener() {
            @Override
        public void actionPerformed(ActionEvent ae) {
            if(chkb_Dental.isSelected()==true)
                if(benefits!=null)
                    benefits+="d,";
                else
                    benefits="d,";
          }
        });*/

        chkb_Medical=new JCheckBox( "Medical Plan" );
        chkb_Medical.setBounds(725, 75, 150,20);
        panel_Professional_Info.add( chkb_Medical );
        /*chkb_Medical.addActionListener(new ActionListener() {
            @Override
        public void actionPerformed(ActionEvent ae) {
            if(chkb_Medical.isSelected()==true)
                if(benefits!=null)
                    benefits+="m,";
                else
                    benefits="m,";
          }
        });*/

        chkb_Retirement=new JCheckBox( "Retirement Plan" );
        chkb_Retirement.setBounds(725, 125, 150,20);
        panel_Professional_Info.add( chkb_Retirement );
        /*chkb_Retirement.addActionListener(new ActionListener() {
            @Override
        public void actionPerformed(ActionEvent ae) {
            if(chkb_Retirement.isSelected()==true)
                if(benefits!=null)
                    benefits+="r,";
                else
                    benefits="r,";
          }
        });*/

         chkb_Site=new JCheckBox( "On Site day care" );
        chkb_Site.setBounds(725, 175, 150,20);
        panel_Professional_Info.add( chkb_Site );
        /*chkb_Site.addActionListener(new ActionListener() {
            @Override
        public void actionPerformed(ActionEvent ae) {
            if(chkb_Site.isSelected()==true)
                if(benefits!=null)
                    benefits+="s,";
                else
                    benefits="s,";
          }
        });*/

         lbl_Fax = new JLabel( "Fax:" );
        lbl_Fax.setBounds( 50, 175, 80, 20 );
        panel_Professional_Info.add( lbl_Fax );

        txt_Fax= new JTextField();
        txt_Fax.setBounds( 150, 175, 150, 20 );
        panel_Professional_Info.add( txt_Fax );

   }

   void getNewCode()
    {
        try
        {
           String table;
           table="tbl_comp_primary";
           DB_Query.selectFieldQuery(table,"(MAX(Comp_Code)+1)  as Comp_Code" ,null);

            if(DB_Query.rs.next())
            {
                txt_Code.setText(DB_Query.rs.getString("Comp_Code"));
            }
            if((DB_Query.rs.getString("Comp_Code"))==null)
            {
               txt_Code.setText("1");
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
   void showPrimaryData()
    {
       String cond,table;
       table="tbl_comp_primary";
       if(cmb_SearchBy.getSelectedIndex()== 0)
       {
                cond= "Comp_Code = '" + txt_Search.getText()+"'";
       }
       else
       {
           cond="Name like ('"+ txt_Search.getText() +"%') OR Name like ('"+ txt_Search.getText()+"%')";
       }

        DB_Query.selectAllQuery(table, cond);
        try
        {

            if(DB_Query.rs.next())
            {
               //JOptionPane.showMessageDialog(null, table +":"+cond);
               String code = DB_Query.rs.getString("comp_Code");
               global_Code=code;
               txt_Code.setText(code);
               String name = DB_Query.rs.getString("Name");               
               txt_Name.setText(name);
               if(DB_Query.rs.getString("Logo")==null || DB_Query.rs.getString("Logo").isEmpty())
               {
                   ImageIcon pic = new ImageIcon("Logo/none.jpg");
                   lbl_Logo.setIcon(pic);
               }
               else
               {
                   logo=DB_Query.rs.getString("Picture");
                   ImageIcon pic = new ImageIcon("Logo/"+logo);
                   lbl_Logo.setIcon(pic);
                   searched_logo=logo;
                   found_logo=logo;
               }
               found=true;

            }
            else
            {
               JOptionPane.showMessageDialog(null, "Sorry, No Data Found!!");
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        catch(NumberFormatException err)
        {
            JOptionPane.showMessageDialog(null, "Please enter numeric parameter.");
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

   void showGeneralData()
    {
       String cond,table;
       table="tbl_comp_general";
       if(cmb_SearchBy.getSelectedIndex()== 0)
       {
                cond= "Comp_Code = '" + txt_Search.getText()+"'";
       }
       else
       {
           cond="Name like ('"+ txt_Search.getText() +"%') OR Name like ('"+ txt_Search.getText()+"%')";
       }

        DB_Query.selectAllQuery(table, cond);
        try
        {

            if(DB_Query.rs.next())
            {
               //JOptionPane.showMessageDialog(null, table +":"+cond);
               txt_Contact1.setText(DB_Query.rs.getString("contactno1"));
               txt_Contact2.setText(DB_Query.rs.getString("contactno2"));
               txt_Web.setText(DB_Query.rs.getString("website"));
               txt_Size.setText(DB_Query.rs.getString("size"));
               txa_Address.setText(DB_Query.rs.getString("address"));
            }
            else
            {
               //JOptionPane.showMessageDialog(null, "Sorry, No Data Found!!");
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        catch(NumberFormatException err)
        {
            JOptionPane.showMessageDialog(null, "Please enter numeric parameter.");
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
   void showProfessionalData()
    {
       String cond,table;
       table="tbl_comp_professional";
       if(cmb_SearchBy.getSelectedIndex()== 0)
       {
                cond= "Comp_Code = '" + txt_Search.getText()+"'";
       }
       else
       {
           cond="Name like ('"+ txt_Search.getText() +"%') OR Name like ('"+ txt_Search.getText()+"%')";
       }

        DB_Query.selectAllQuery(table, cond);
        try
        {

            if(DB_Query.rs.next())
            {
               //JOptionPane.showMessageDialog(null, table +":"+cond);
               cmb_Sal.setSelectedItem(DB_Query.rs.getString("Sal_scale"));
               txt_Hr.setText(DB_Query.rs.getString("Hrm"));
               txt_Email.setText(DB_Query.rs.getString("Email_id"));
               txt_Contact_No.setText(DB_Query.rs.getString("Contact_no"));
               txt_Fax.setText(DB_Query.rs.getString("Fax"));
               
               if(DB_Query.rs.getString("Emp_Benefits")!= null)
               {
                   String benefits=(DB_Query.rs.getString("Emp_Benefits"));
                   if(benefits.indexOf('d')>=0)
                   {
                       chkb_Dental.setSelected(true);
                   }
                   else
                       chkb_Dental.setSelected(false);
                   if(benefits.indexOf('m')>=0)
                   {
                       chkb_Medical.setSelected(true);
                   }
                   else
                       chkb_Medical.setSelected(false);
                   if(benefits.indexOf('r')>=0)
                   {
                       chkb_Retirement.setSelected(true);
                   }
                   else
                       chkb_Retirement.setSelected(false);
                   if(benefits.indexOf('s')>=0)
                   {
                       chkb_Site.setSelected(true);
                   }
                   else
                       chkb_Site.setSelected(false);
               }
               else
               {
                   chkb_Dental.setSelected(false);
                   chkb_Medical.setSelected(false);
                   chkb_Retirement.setSelected(false);
                   chkb_Site.setSelected(false);

               }
            }
            else
            {
               //JOptionPane.showMessageDialog(null, "Sorry, No Data Found!!");
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        catch(NumberFormatException err)
        {
            JOptionPane.showMessageDialog(null, "Please enter numeric parameter.");
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
   
    void insertCompany()
    {
       String str;

       try
       {
           str="Insert into  tbl_comp_primary values(?,?,?)";
           DBConnect.connect();
           DBConnect.conn.setAutoCommit(false);
           DB_Query.ps = DBConnect.conn.prepareStatement(str);

           DB_Query.ps.setString(1, txt_Code.getText());
           DB_Query.ps.setString(2, txt_Name.getText());
           DB_Query.ps.setString(3, logo);

           if(logo!=null)
           {
                CreateThumbnail ct = new CreateThumbnail(logoLocation);
                ct.getThumbnail(120, CreateThumbnail.HORIZONTAL);
                ct.saveThumbnail(new File("Logo/"+ logo), CreateThumbnail.IMAGE_JPEG);
           }
            DB_Query.dclQuery("insert");

           //Insert in the tbl_comp_general
           str="Insert into  tbl_comp_general values(?,?,?,?,?,?)";
           DBConnect.connect();
           DBConnect.conn.setAutoCommit(false);
           DB_Query.ps = DBConnect.conn.prepareStatement(str);

           DB_Query.ps.setString(1, txt_Code.getText());
           DB_Query.ps.setString(2, txt_Contact1.getText() );
           DB_Query.ps.setString(3, txt_Contact2.getText());
           DB_Query.ps.setString(4, txt_Web.getText());
           DB_Query.ps.setString(5, txt_Size.getText());
           DB_Query.ps.setString(6, txa_Address.getText());
           DB_Query.dclQuery("insert");


           //Insert in the tbl_cand_professional
           str="Insert into  tbl_comp_professional values(?,?,?,?,?,?,?)";
           DBConnect.connect();
           DBConnect.conn.setAutoCommit(false);
           DB_Query.ps = DBConnect.conn.prepareStatement(str);

           
           DB_Query.ps.setString(2, String.valueOf(cmb_Sal.getSelectedItem()));
           DB_Query.ps.setString(3, txt_Hr.getText());
           DB_Query.ps.setString(4, txt_Email.getText());
           DB_Query.ps.setString(5, txt_Contact_No.getText());
           DB_Query.ps.setString(6, txt_Fax.getText());
           benefits="";
           if(chkb_Dental.isSelected()==true)
                if(benefits!=null)
                    benefits+="d,";
                else
                    benefits="d,";
           if(chkb_Medical.isSelected()==true)
                if(benefits!=null)
                    benefits+="m,";
                else
                    benefits="m,";
           if(chkb_Retirement.isSelected()==true)
                if(benefits!=null)
                    benefits+="r,";
                else
                    benefits="r,";
           if(chkb_Site.isSelected()==true)
                if(benefits!=null)
                    benefits+="s,";
                else
                    benefits="s,";
           DB_Query.ps.setString(7, benefits);
           DB_Query.ps.setString(1, txt_Code.getText());
           DB_Query.dclQuery("insert");



           JOptionPane.showMessageDialog(null, "Data Has Been Inserted Successfully.");
       }
       catch(SQLException e)
       {
           JOptionPane.showMessageDialog(null, "Error Occured :" + e);
       }
    }
    
    void updateCandidate()
    {
       String str;
       if(logo!=null)
            if(!(searched_logo==(logo)))
                logo=getDistinctName()+".jpg";
       
       try
       {
           str="Update tbl_comp_primary set name=?, Logo=? Where Comp_Code=?";
           DBConnect.connect();            
           DBConnect.conn.setAutoCommit(false);
           DB_Query.ps = DBConnect.conn.prepareStatement(str);           
           DB_Query.ps.setString(1, txt_Name.getText());
           

           DB_Query.ps.setString(2, logo);
           if(logo!=null )
               if(!(searched_logo==(logo)))
               {
                    File target = new File("Pictures/"+ found_logo);
                    target.delete();
                    CreateThumbnail ct = new CreateThumbnail(logoLocation);
                    ct.getThumbnail(120, CreateThumbnail.HORIZONTAL);
                    ct.saveThumbnail(new File("Logo/"+ logo), CreateThumbnail.IMAGE_JPEG);
               }
            DB_Query.ps.setString(3, txt_Code.getText());
            DB_Query.dclQuery("update");
            
         

           str="Update tbl_comp_general set contactno1=?, contactno2=?, website=?, size=?,address=?  Where comp_Code=?";
           DBConnect.connect();
           DBConnect.conn.setAutoCommit(false);
           DB_Query.ps = DBConnect.conn.prepareStatement(str);
           DB_Query.ps.setString(1, txt_Contact1.getText() );
           DB_Query.ps.setString(2, txt_Contact2.getText());
           DB_Query.ps.setString(3, txt_Web.getText());
           DB_Query.ps.setString(4, txt_Size.getText());
           DB_Query.ps.setString(5, txa_Address.getText());
           DB_Query.ps.setString(6, txt_Code.getText());
           DB_Query.dclQuery("update");

          str="Update tbl_comp_professional set Sal_scale=?, Hrm=?, Email_id=?,Contact_no=?, Fax=?,Emp_Benefits=?  Where comp_Code=?";
           DBConnect.connect();
           DBConnect.conn.setAutoCommit(false);
           DB_Query.ps = DBConnect.conn.prepareStatement(str);
           DB_Query.ps.setString(1, String.valueOf(cmb_Sal.getSelectedItem() ));
           DB_Query.ps.setString(2, txt_Hr.getText());
           DB_Query.ps.setString(3, txt_Email.getText());
           DB_Query.ps.setString(4, txt_Contact_No.getText());
           DB_Query.ps.setString(5, txt_Fax.getText());
           benefits="";
           if(chkb_Dental.isSelected()==true)
                if(benefits!=null)
                    benefits+="d,";
                else
                    benefits="d,";
           if(chkb_Medical.isSelected()==true)
                if(benefits!=null)
                    benefits+="m,";
                else
                    benefits="m,";
           if(chkb_Retirement.isSelected()==true)
                if(benefits!=null)
                    benefits+="r,";
                else
                    benefits="r,";
           if(chkb_Site.isSelected()==true)
                if(benefits!=null)
                    benefits+="s,";
                else
                    benefits="s,";
           DB_Query.ps.setString(6, benefits);
           DB_Query.ps.setString(7,txt_Code.getText());

           DB_Query.dclQuery("update");

           JOptionPane.showMessageDialog(null, "Data Has Been Updated Successfully.");
       }
       catch(SQLException e)
       {
           JOptionPane.showMessageDialog(null, "Error Occured :" + e);
       }        
    }

    void deleteCompany()
    {
       String str;

       try
       {
           int close = JOptionPane.showConfirmDialog(null,"Do you want delete this record?",
                                                        "Delete Record",JOptionPane.YES_NO_OPTION);
           if(close == JOptionPane.YES_OPTION)
           {
               //Delete From The tbl_cand_general
               DBConnect.connect();
               str="Delete from tbl_comp_general where Comp_Code=?;";
               DBConnect.conn.setAutoCommit(false);
               DB_Query.ps = DBConnect.conn.prepareStatement(str);
               DB_Query.ps.setString(1, txt_Code.getText());
               DB_Query.dclQuery("delete");



                //Delete from the tbl_cand_professional table
               str="Delete from tbl_comp_professional where Comp_Code=?;";
               DBConnect.connect();
               DBConnect.conn.setAutoCommit(false);
               DB_Query.ps = DBConnect.conn.prepareStatement(str);
               DB_Query.ps.setString(1, txt_Code.getText());
               DB_Query.dclQuery("delete");

               //Delete from the tbl_cand_primary table
               str="Delete from tbl_comp_primary where Comp_Code=?;";
               DBConnect.connect();
               DBConnect.conn.setAutoCommit(false);
               DB_Query.ps = DBConnect.conn.prepareStatement(str);
               DB_Query.ps.setString(1, txt_Code.getText());
               DB_Query.dclQuery("delete");

               JOptionPane.showMessageDialog(null, "Data Has Been Deleted Successfully.");
           }
       }
       catch(SQLException e)
       {
           JOptionPane.showMessageDialog(null, "Error Occured :" + e);
       }
    }

    void disableAllFields()
    {

        //txt_Search.setEditable(false);
        txt_Name.setEditable(false);
        txt_Code.setEditable(false);
        //txt_Address.setEditable(false);
        txt_Contact1.setEditable(false);
        txt_Contact2.setEditable(false);
        //txt_Sal.setEditable(false);
        txt_Web.setEditable(false);
        txt_Hr.setEditable(false);
        txt_Email.setEditable(false);
        txt_Contact_No.setEditable(false);
        txt_Fax.setEditable(false);
        txa_Address.setEditable(false);
        txt_Size.setEditable(false);

        chkb_Dental.setEnabled(false);
        chkb_Medical.setEnabled(false);
        chkb_Retirement.setEnabled(false);
        chkb_Site.setEnabled(false);


        //btn_Logo_Change.setEnabled(false);
    }



    void enableAllFields()
    {
         txt_Search.setEditable(true);
        txt_Name.setEditable(true);
        //txt_Code.setEditable(true);

        txt_Contact1.setEditable(true);
        txt_Contact2.setEditable(true);

        txt_Web.setEditable(true);
        txt_Hr.setEditable(true);
        txt_Email.setEditable(true);
        txt_Contact_No.setEditable(true);
        txt_Fax.setEditable(true);
        txa_Address.setEditable(true);
        txt_Size.setEditable(true);

        chkb_Dental.setEnabled(true);
        chkb_Medical.setEnabled(true);
        chkb_Retirement.setEnabled(true);
        chkb_Site.setEnabled(true);



    }

    void clearAllFields()
    {
        ImageIcon pic = new ImageIcon("Logo/none.jpg");
       benefits=searched_logo=logo=null;

        txt_Code.setText("");
        txt_Name.setText("");
        txt_Search.setText("");
       // txt_Address.setText("");
        txt_Contact1.setText("");
        txt_Contact2.setText("");
        //txt_Sal.setText("");
        txt_Web.setText("");
        txt_Hr.setText("");
        txt_Email.setText("");
        txt_Contact_No.setText("");
        txt_Fax.setText("");
        txt_Size.setText("");
        txa_Address.setText("");

        //cmb_Candidate_Type.setEditable(false);
        //cmb_Sal.setSelectedIndex(0);
        //cmb_SearchBy.setSelectedIndex(0);

        chkb_Dental.setSelected(false);
        chkb_Medical.setSelected(false);
        chkb_Retirement.setSelected(false);
        chkb_Site.setSelected(false);


    }

    void disableAllButtons()
    {
        btn_Save.setEnabled(false);
        btn_Edit.setEnabled(false);
        btn_Update.setEnabled(false);
        btn_Delete.setEnabled(false);
        btn_Cancel.setEnabled(false);
    }

    String getDistinctName()
    {
        Date now = new Date();
          String Name =String.valueOf(now.getTime());
          return Name;
    }
    boolean checkValidation()
    {
        Validation objValid= new Validation();
        if(objValid.validCcode(txt_Code.getText())==false)
        {
            txt_Code.requestFocus();
            return false;
        }
        if(objValid.validCname(txt_Name.getText())== false)
        {
            txt_Name.requestFocus();
            return false;
        }

        if(objValid.validContactNo1(txt_Contact1.getText())== false)
        {
            txt_Contact1.requestFocus();
            return false;
        }

         if(objValid.validContactNo2(txt_Contact2.getText())== false)
        {
            txt_Contact2.requestFocus();
            return false;
        }

        if(objValid.validWebSite(txt_Web.getText())== false)
        {
            txt_Web.requestFocus();
            return false;
        }

        if(objValid.validSize(txt_Size.getText())== false)
        {
            txt_Size.requestFocus();
            return false;
        }
         if(objValid.validHrMngr(txt_Hr.getText())== false)
        {
            txt_Hr.requestFocus();
            return false;
        }
         if(objValid.validEmail(txt_Email.getText())== false)
        {
            txt_Email.requestFocus();
            return false;
        }
        if(objValid.validContactNo(txt_Contact_No.getText())== false)
        {
            txt_Contact_No.requestFocus();
            return false;
        }
         if(objValid.validFax(txt_Fax.getText())== false)
        {
            txt_Fax.requestFocus();
            return false;
        }
        return true;
    }
    boolean checkSearchValidation()
    {
        Validation objValid= new Validation();
        if(objValid.validSearch(txt_Search.getText())== false)
        {
           txt_Search.requestFocus();
            return false;

        }
        return true;
    }
}
