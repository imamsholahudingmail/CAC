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

class Form_Jobs extends JInternalFrame
{
    JPanel mainPanel;


    private JTabbedPane tabbedPane;
    private JPanel panel_Job_Info,panel_Info_Content,panel_Primary_Info;
    private JPanel panel_Buttons;

    GridBagLayout mainLayout=new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();


    GroupLayout grpLayout = new GroupLayout(getContentPane());
    JPanel topPanel = new JPanel();


    JLabel lbl_Search,lbl_Name,lbl_Code,lbl_Designation,lbl_OpenDate,lbl_CloseDate,lbl_Shifts,lbl_AnnSal,lbl_Vacancy;
    JTextField  txt_Search,txt_Code,txt_Shifts,txt_AnnSal,txt_Vacancy;
    JComboBox cmb_Name,cmb_Sal, cmb_SearchBy,cmb_Day,cmb_Month,cmb_Year,cmb_Day1,cmb_Month1,cmb_Year1,cmb_Designation;


    JButton btn_Search,btn_Logo_Change,btn_New,btn_Save,btn_Update,
            btn_Edit,btn_Delete,btn_Cancel;

    private String global_Code;
     boolean found=false;
     GregorianCalendar d = new GregorianCalendar();

    public Form_Jobs()
    {
        setSize(700,500);
        setTitle("Job Profile");
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
        /*lbl_Name=new JLabel("Company:");
        txt_Name=new JTextField();*/

         panel_Job_Info=new JPanel();
         createJobInfo();
         panel_Info_Content=new JPanel();
         panel_Info_Content.setLayout(new BorderLayout());
         getContentPane().add( panel_Job_Info );
         tabbedPane.addTab("Job Info",panel_Job_Info);
         panel_Info_Content.add(tabbedPane);




        String[] searchBy = {"Code","Name"};
        getContentPane().setLayout(grpLayout);
        grpLayout.setAutoCreateGaps(true);
        grpLayout.setAutoCreateContainerGaps(true);


        lbl_Search=new JLabel(new ImageIcon("icons/search.png"));
        txt_Search=new JTextField();
        Font font = new Font("Serif", Font.PLAIN,12);
        cmb_SearchBy=new JComboBox(searchBy);
        cmb_SearchBy.setFont(font);
        btn_Search=new JButton( "Search");
         btn_Search.addActionListener(new ActionListener() {
            @Override

         public void actionPerformed(ActionEvent ae) {
         {
              if(txt_Search.getText()!=null)
                {

                    disableAllButtons();
                    disableAllFields();
                    show_Job();
                  //  showProfessionalData();
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

                .addGap(40,40,40)
                .addGroup(grpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(panel_Primary_Info, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))

                 .addGap(10, 10, 10)
                .addGroup(grpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(panel_Info_Content)
                    .addGroup(grpLayout.createSequentialGroup()
                    .addGap(65, 65, 65)
                    .addComponent(panel_Buttons)))
            ));

        disableAllFields();
        disableAllButtons();
     }
void primaryInfoSettings()
{
    lbl_Code=new JLabel("Job Code :");
    txt_Code=new JTextField();
    lbl_Name=new JLabel("Company Name :");
    cmb_Name=new JComboBox();
    getCompanyName();

    panel_Primary_Info=new JPanel();
    panel_Primary_Info.setLayout(null);
    lbl_Code.setBounds(10,10,75,20);
    panel_Primary_Info.add(lbl_Code);
    txt_Code.setBounds(100,10,200,20);
    panel_Primary_Info.add(txt_Code);
    lbl_Name.setBounds(10,40,75,20);
    panel_Primary_Info.add(lbl_Name);
    cmb_Name.setBounds(100,40,200,20);
    panel_Primary_Info.add(cmb_Name);

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

            cmb_Name.requestFocus();
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
                insertJob();
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
               updateJob();
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
                deleteJob();
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

        if(Main.user_auth.indexOf("12")>=0)
        {
            btn_New.setVisible(true);
            btn_Save.setVisible(true);
        }
        else
        {
            btn_New.setVisible(false);
            btn_Save.setVisible(false);
        }
        if(Main.user_auth.indexOf("13")>=0)
        {
            btn_Edit.setVisible(true);
            btn_Update.setVisible(true);
        }
        else
        {
            btn_Edit.setVisible(false);
            btn_Update.setVisible(false);
        }
        if(Main.user_auth.indexOf("14")>=0)
        {
            btn_Delete.setVisible(true);
        }
        else
        {
            btn_Delete.setVisible(false);
        }
    }


    void createJobInfo()
    {
        panel_Job_Info.setLayout(null);

        lbl_Designation = new JLabel( "Designation:" );
        lbl_Designation.setBounds(50, 25, 100, 20 );
       panel_Job_Info.add( lbl_Designation );

        String preferred[]={"Top Management- IT","Top Management- Non IT",
        "Web, Graphic Design","IT- Hardware","Industrial Products",
        "IT, Software Services","Marketing","Teaching, Education","Other"};
        cmb_Designation=new JComboBox(preferred);
        cmb_Designation.setBounds( 150, 25, 100, 20 );
        cmb_Designation.setFont(new Font("Serif", Font.PLAIN,12));
         panel_Job_Info.add(cmb_Designation);


         lbl_OpenDate = new JLabel( "Opening Date:" );
        lbl_OpenDate.setBounds(50, 75, 100, 20 );
        panel_Job_Info.add(lbl_OpenDate);
        String[] dob_day;
        dob_day=new String[31];
        for(int i=0;i<31;i++)
            dob_day[i]=String.valueOf(i+1);
        cmb_Day=new JComboBox(dob_day);
        cmb_Day.setFont(new Font("Serif", Font.PLAIN,12));
        cmb_Day.setBounds( 150, 75, 38, 20 );
        panel_Job_Info.add( cmb_Day );

        

        String[] dob_month;
        dob_month=new String[12];
        for(int i=0;i<12;i++)
            dob_month[i]=String.valueOf(i+1);
        cmb_Month=new JComboBox(dob_month);
        cmb_Month.setFont(new Font("Serif", Font.PLAIN,12));
        cmb_Month.setBounds(200, 75, 38, 20 );
        panel_Job_Info.add( cmb_Month );         

        String[] dob_year;
        dob_year=new String[50];
        for(int j=0;j<50;j++)
            dob_year[j]=String.valueOf(d.get(Calendar.YEAR)-j);
        cmb_Year=new JComboBox(dob_year);
        cmb_Year.setFont(new Font("Serif", Font.PLAIN,12));
        cmb_Year.setBounds( 250, 75,50, 20 );
        panel_Job_Info.add( cmb_Year );

        d.roll(Calendar.DAY_OF_MONTH, -1);
        int day = d.get(Calendar.DAY_OF_MONTH);
        cmb_Day.setSelectedItem(String.valueOf(day));
        cmb_Month.setSelectedItem(String.valueOf(d.get(Calendar.MONTH)+1));
        cmb_Year.setSelectedItem(String.valueOf(d.get(Calendar.YEAR)));
        
         lbl_CloseDate = new JLabel( "Closing Date:" );
        lbl_CloseDate.setBounds(50, 125, 100, 20 );
        panel_Job_Info.add(lbl_CloseDate);
        String[] dob_day1;
        dob_day1=new String[31];
        for(int i=0;i<31;i++)
            dob_day1[i]=String.valueOf(i+1);
        cmb_Day1=new JComboBox(dob_day1);
        cmb_Day1.setFont(new Font("Serif", Font.PLAIN,12));
        cmb_Day1.setBounds( 150, 125, 38, 20 );
        panel_Job_Info.add( cmb_Day1 );
        String[] dob_month1;
        dob_month1=new String[12];
        for(int i=0;i<12;i++)
            dob_month1[i]=String.valueOf(i+1);
        cmb_Month1=new JComboBox(dob_month1);
        cmb_Month1.setFont(new Font("Serif", Font.PLAIN,12));
        cmb_Month1.setBounds( 200, 125, 38, 20 );
        panel_Job_Info.add( cmb_Month1 );
        String[] dob_year1;
        dob_year1=new String[50];
        for(int j=0;j<50;j++)
            dob_year1[j]=String.valueOf(d.get(Calendar.YEAR)-j);
        cmb_Year1=new JComboBox(dob_year1);
        cmb_Year1.setFont(new Font("Serif", Font.PLAIN,12));
        cmb_Year1.setBounds( 250, 125, 50, 20 );
        panel_Job_Info.add( cmb_Year1 );

        //d.roll(Calendar.DAY_OF_MONTH, -1);
        cmb_Day1.setSelectedItem(String.valueOf(day));
        cmb_Month1.setSelectedItem(String.valueOf(d.get(Calendar.MONTH)+1));
        cmb_Year1.setSelectedItem(String.valueOf(d.get(Calendar.YEAR)));

        lbl_Shifts = new JLabel( "Shifts:" );
        lbl_Shifts.setBounds( 50, 175, 100, 20 );
        panel_Job_Info.add( lbl_Shifts );
        lbl_Shifts.setVisible(false);
        txt_Shifts= new JTextField();
        txt_Shifts.setBounds( 150, 175, 100, 20 );
        panel_Job_Info.add( txt_Shifts );
        txt_Shifts.setVisible(false);

        lbl_AnnSal= new JLabel( "Annual Salary:" );
        lbl_AnnSal.setBounds( 50, 175, 100, 20 );
        panel_Job_Info.add( lbl_AnnSal );

        txt_AnnSal= new JTextField();
        txt_AnnSal.setBounds( 150, 175, 100, 20 );
        panel_Job_Info.add(txt_AnnSal );

        lbl_Vacancy= new JLabel( "No.of Vacancy:" );
        lbl_Vacancy.setBounds(50, 225, 100, 20 );
        panel_Job_Info.add( lbl_Vacancy );

        txt_Vacancy= new JTextField();
        txt_Vacancy.setBounds( 150, 225, 100, 20 );
        panel_Job_Info.add(txt_Vacancy );

    }
    void getCompanyName()
    {
       String cond,table,field;
       table="tbl_comp_primary";
        cond=null;
        field="Name";
        DB_Query.selectFieldQuery(table,field,cond);
        try
        {
            cmb_Name.addItem("");
            while(DB_Query.rs.next())
            {
              cmb_Name.addItem(DB_Query.rs.getString("Name"));
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
    void show_Job()
    {
       String cond,table;
       table="tbl_job";
        if(cmb_SearchBy.getSelectedIndex()== 0)
       {
                cond= "job_Code = '" + txt_Search.getText()+"'";
       }
       else
       {
           cond="company like ('"+ txt_Search.getText() +"%')";
       }
        DB_Query.selectAllQuery(table, cond);
        try
        {

            if(DB_Query.rs.next())
            {
               String Code= DB_Query.rs.getString("Job_Code");
               String Name = DB_Query.rs.getString("company");
               String Designation = DB_Query.rs.getString("designation");
               String Opening_Date=DB_Query.rs.getString("opening_date");
               if(Opening_Date!=null)
               {
                   StringTokenizer dt = new StringTokenizer(Opening_Date, "-");
                  cmb_Year.setSelectedItem(dt.nextToken());
                  cmb_Month.setSelectedItem(String.valueOf(Integer.valueOf(dt.nextToken())));
                  cmb_Day.setSelectedItem(String.valueOf(Integer.valueOf(dt.nextToken())));
               }
               String Closeing_Date=DB_Query.rs.getString("closeing_date");
               if(Closeing_Date!=null)
               {
                   StringTokenizer dt1 = new StringTokenizer(Closeing_Date, "-");
                  cmb_Year1.setSelectedItem(dt1.nextToken());
                  cmb_Month1.setSelectedItem(String.valueOf(Integer.valueOf(dt1.nextToken())));
                  cmb_Day1.setSelectedItem(String.valueOf(Integer.valueOf(dt1.nextToken())));
               }


               String Shift = DB_Query.rs.getString("shifts");
               String Salary = DB_Query.rs.getString("ann_salary");
               String Vacancy_No = DB_Query.rs.getString("no_of_vac");

               txt_Code.setText(Code);
               cmb_Name.setSelectedItem(String.valueOf(Name));
               cmb_Designation.setSelectedItem(Designation);
               txt_Shifts.setText(Shift);
               txt_AnnSal.setText(Salary);
               txt_Vacancy.setText(Vacancy_No);
               
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
    void getNewCode()
    {

        try
        {

           String table;
           table="tbl_job";
           DB_Query.selectFieldQuery(table,"(MAX(job_Code)+1)  as Cand_Code" ,null);

            if(DB_Query.rs.next())
            {
                txt_Code.setText(DB_Query.rs.getString("Cand_Code"));
            }
            if((DB_Query.rs.getString("Cand_Code"))==null)
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
    void insertJob()
    {
       String str;

       try
       {
           str="Insert into  tbl_job values(?,?,?,?,?,?,?,?)";
           DBConnect.connect();
           DBConnect.conn.setAutoCommit(false);
           DB_Query.ps = DBConnect.conn.prepareStatement(str);

           DB_Query.ps.setString(1, txt_Code.getText());
           DB_Query.ps.setString(2, String.valueOf(cmb_Name.getSelectedItem()));
           DB_Query.ps.setString(3, String.valueOf(cmb_Designation.getSelectedItem()));
           DB_Query.ps.setString(4, String.valueOf(cmb_Year.getSelectedItem())+"-"+
                                    String.valueOf(cmb_Month.getSelectedItem())+"-"+
                                    String.valueOf(cmb_Day.getSelectedItem()));
           DB_Query.ps.setString(5, String.valueOf(cmb_Year1.getSelectedItem())+"-"+
                                    String.valueOf(cmb_Month1.getSelectedItem())+"-"+
                                    String.valueOf(cmb_Day1.getSelectedItem()));
           DB_Query.ps.setString(6, txt_Shifts.getText());
           DB_Query.ps.setString(7, txt_AnnSal.getText());
           DB_Query.ps.setString(8, txt_Vacancy.getText());


            DB_Query.dclQuery("insert");


           JOptionPane.showMessageDialog(null, "Data Has Been Inserted Successfully.");
       }
       catch(SQLException e)
       {
           JOptionPane.showMessageDialog(null, "Error Occured :" + e);
       }
    }

        void updateJob()
    {
       try
       {
           String str="Update tbl_job set company=?, designation=?, opening_date=?, " +
                   "closeing_date=?,shifts=?,ann_salary=?,no_of_vac=? Where job_Code=?";
           DBConnect.connect();
           DBConnect.conn.setAutoCommit(false);
           DB_Query.ps = DBConnect.conn.prepareStatement(str);
           DB_Query.ps.setString(1, String.valueOf(cmb_Name.getSelectedItem()));
           DB_Query.ps.setString(2,  String.valueOf(cmb_Designation.getSelectedItem() ));
           DB_Query.ps.setString(3, String.valueOf(cmb_Year.getSelectedItem())+"-"+
                                    String.valueOf(cmb_Month.getSelectedItem())+"-"+
                                    String.valueOf(cmb_Day.getSelectedItem()));
           DB_Query.ps.setString(4, String.valueOf(cmb_Year1.getSelectedItem())+"-"+
                                    String.valueOf(cmb_Month1.getSelectedItem())+"-"+
                                    String.valueOf(cmb_Day1.getSelectedItem()));
           DB_Query.ps.setString(5, txt_Shifts.getText());
           DB_Query.ps.setString(6, txt_AnnSal.getText());
           DB_Query.ps.setString(7, txt_Vacancy.getText());
           DB_Query.ps.setString(8, txt_Code.getText());
           DB_Query.dclQuery("update");

           JOptionPane.showMessageDialog(null, "Data Has Been Updated Successfully.");
       }
       catch(SQLException e)
       {
           JOptionPane.showMessageDialog(null, "Error Occured :" + e);
       }
    }

    void deleteJob()
    {
       String str;
       try
       {
           int close = JOptionPane.showConfirmDialog(null,"Do you want delete this record?",
                                                        "Delete Record",JOptionPane.YES_NO_OPTION);
           if(close == JOptionPane.YES_OPTION)
           {
               //Delete From The tbl_job
               DBConnect.connect();
               str="Delete from tbl_job where job_code=?;";
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
        cmb_Name.setEnabled(false);
        txt_Code.setEditable(false);
        txt_AnnSal.setEditable(false);
        txt_Shifts.setEditable(false);
        txt_Vacancy.setEditable(false);
        cmb_Designation.setEnabled(false);
        cmb_Day.setEnabled(false);
        cmb_Day1.setEnabled(false);
        cmb_Month.setEnabled(false);
        cmb_Month1.setEnabled(false);
        cmb_Year.setEnabled(false);
        cmb_Year1.setEnabled(false);
    }

    void enableAllFields()
    {
        cmb_Name.setEnabled(true);
        txt_AnnSal.setEditable(true);
        
        txt_Shifts.setEditable(true);
        txt_Vacancy.setEditable(true);
        cmb_Designation.setEnabled(true);
         cmb_Day.setEnabled(true);
        cmb_Day1.setEnabled(true);
        cmb_Month.setEnabled(true);
        cmb_Month1.setEnabled(true);
        cmb_Year.setEnabled(true);
        cmb_Year1.setEnabled(true);
    }

    void clearAllFields()
    {
        txt_Code.setText("");
        cmb_Name.setSelectedItem(null);
        txt_Search.setText("");
        txt_AnnSal.setText("");
        txt_Shifts.setText("");
        txt_Vacancy.setText("");
        cmb_Designation.setSelectedIndex(0);

        int day = d.get(Calendar.DAY_OF_MONTH);
        cmb_Day.setSelectedItem(String.valueOf(day));
        cmb_Month.setSelectedItem(String.valueOf(d.get(Calendar.MONTH)+1));
        cmb_Year.setSelectedItem(String.valueOf(d.get(Calendar.YEAR)));
        cmb_Day1.setSelectedItem(String.valueOf(day));
        cmb_Month1.setSelectedItem(String.valueOf(d.get(Calendar.MONTH)+1));
        cmb_Year1.setSelectedItem(String.valueOf(d.get(Calendar.YEAR)));
    }

    void disableAllButtons()
    {
        btn_Save.setEnabled(false);
        btn_Edit.setEnabled(false);
        btn_Update.setEnabled(false);
        btn_Delete.setEnabled(false);
        btn_Cancel.setEnabled(false);
    }
    
     boolean checkValidation()
    {
        Validation objValid= new Validation();

        if(objValid.validJcode(txt_Code.getText())== false)
        {
            txt_Code.requestFocus();
            return false;
        }
         /*if(objValid.validJname(txt_Name.getText())== false)
        {
            txt_Name.requestFocus();
            return false;
        }*/

        // if(objValid.validJshift(txt_Shifts.getText())== false)
        //{
          //  txt_Shifts.requestFocus();
            //return false;
        //}

         if(objValid.validJannsal(txt_AnnSal.getText())== false)
        {
            txt_AnnSal.requestFocus();
            return false;
        }
         if(objValid.validVacancy(txt_Vacancy.getText())== false)
        {
            txt_Vacancy.requestFocus();
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