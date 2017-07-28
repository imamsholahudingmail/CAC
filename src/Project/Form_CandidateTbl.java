package Project;

/**
 *
 * @author Prashant
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;
import java.util.*;


public class Form_CandidateTbl extends JInternalFrame
{
    GroupLayout grpLayout = new GroupLayout(getContentPane());
    JPanel panel_Content = new JPanel();
    
    JPanel panel_Controls = new JPanel();
    JLabel lbl_Gender,lbl_Gender1,lbl_Name,lbl_Higher_edu,lbl_Division,lbl_Percentage,
            lbl_Expected_Salary,lbl_Preferred_Jobs,lbl_Exeperience,lbl_Preferred_Type,lbl_Empleyeed, lbl_Managable;
    JRadioButton rdo_Male,rdo_Female,rdo_Yes,rdo_No, rdo_Male1,rdo_Female1,rdo_All, rdo_Managable_Yes,rdo_Managable_No;
    JTextField txt_Name,txt_Higher_Edu,txt_Division, txt_Percentage, txt_Experience,  txt_Expected_Salary;
    JComboBox  cmb_Preferred_Type;
    JComboBox cmb_Preferred_Jobs;
    JButton   btn_Search,btn_Reset;
    static JButton btn_CandReportPrev=new JButton("Report",new ImageIcon("icons/report.png"));
    private JLayeredPane layeredPane;
    JTable table;
    int v,h;
    String codeT,nameT, genderT,cellT,homeT,emailT;
    JScrollPane jsp;

    static int dataCol,dataRow;
    static String mydata[][];
    static char employeed='\0';
    char managable='\0';
    public Form_CandidateTbl()
    {
        dataCol=dataRow=0;

        setSize(700,500);
        setTitle("Search Candidate");
        setMaximizable(true);
        setIconifiable(true);
        setClosable(true);
        setResizable(true);

        getContentPane().setLayout(grpLayout);
        grpLayout.setAutoCreateGaps(true);
        grpLayout.setAutoCreateContainerGaps(true);

        //getContentPane().add( panel_Content );
        controls_Setting();
        getTableData();

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
                    .addComponent(panel_Controls, GroupLayout.PREFERRED_SIZE, 310, GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(grpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(panel_Content))
            ));

    }
    
    void getTableData()
    {
                
                Vector<String> heading = new Vector<String>();
                heading.addElement("Code");
                heading.addElement("Name");
                heading.addElement("Gender");
                heading.addElement("Cell No.");
                heading.addElement("Home Phone");
                if(rdo_Yes.isSelected()==true)
                    heading.addElement("Recruited By");
                else
                    heading.addElement("Email Id");
               
                Vector data = new Vector();
                
                int i=0;
                try
                {

                   String table="tbl_cand_primary as t1 INNER JOIN tbl_cand_general as t2 ON t1.cand_code = t2.cand_code " +
                           "JOIN tbl_cand_professional as t3 " +
                            "on t1.cand_code=t3.cand_code "+
                            "JOIN tbl_cand_education as t4 " +
                            "on t1.cand_code=t4.cand_code ";
                   String fields="t1.cand_code,CONCAT(t1.fname,' ',t1.lname) as name," +
                           "t2.Cell_No,t2.Home_Phone,t2.Gender,t2.Email_Id, t3.RecruitedBy ";
                   String cond="";
                   if(!(txt_Name.getText().isEmpty()))
                   {
                       if(cond.isEmpty())
                           cond+=" (t1.FName like ('"+ txt_Name.getText() +"%') OR t1.LName like ('"+ txt_Name.getText()+"%')) ";
                       else
                           cond+=" AND (t1.FName like ('"+ txt_Name.getText() +"%') OR t1.LName like ('"+ txt_Name.getText()+"%')) ";
                   }
                   
                   if(!(txt_Experience.getText().isEmpty()))
                   {
                      
                       if(cond.isEmpty())
                           cond += " t3.Experience >= '"+ txt_Experience.getText()+"'";
                       else
                           cond +=" AND t3.Experience >= '"+ txt_Experience.getText()+"'";
                   }

                   if(!(txt_Higher_Edu.getText().isEmpty()))
                   {
                       if(cond.isEmpty())
                           cond += " t4.Higer_Edu = '"+ txt_Higher_Edu.getText()+"'";
                       else
                           cond +=" AND t4.Higer_Edu = '"+ txt_Higher_Edu.getText()+"'";
                   }
                   
                   if(!(String.valueOf(cmb_Preferred_Type.getSelectedItem()).isEmpty()))
                   {
                       if(cond.isEmpty())
                           cond += " t3.Preferred_Type = '"+ String.valueOf(cmb_Preferred_Type.getSelectedItem())+"'";
                       else
                           cond +=" AND t3.Preferred_Type = '"+ String.valueOf(cmb_Preferred_Type.getSelectedItem()) +"'";
                   }
                   if(!(txt_Division.getText().isEmpty()))
                   {
                       if(cond.isEmpty())
                           cond += " t4.Division > '"+ txt_Division.getText() +"'";
                       else
                           cond +=" AND t4.Division = '"+ txt_Division.getText() +"'";
                   }

                   if(!(cmb_Preferred_Jobs.getSelectedItem().equals("")))
                   {
                       if(cond.isEmpty())
                           cond += " t3.Preferred_Jobs like ('%"+ cmb_Preferred_Jobs.getSelectedItem()+"%')";
                       else
                           cond +=" AND t3.Preferred_Jobs like ('%"+ cmb_Preferred_Jobs.getSelectedItem()+"%')";
                   }

                   if(!(txt_Percentage.getText().isEmpty()))
                   {
                       if(cond.isEmpty())
                           cond += " t4.Percentage >= ('"+ txt_Percentage.getText()+"')";
                       else
                           cond +=" AND t4.Percentage >= ('"+ txt_Percentage.getText()+"')";
                   }

                   if(!(txt_Expected_Salary.getText().isEmpty()))
                   {
                       if(cond.isEmpty())
                           cond += " t3.Expected_Salary <= ('"+ txt_Expected_Salary.getText()+"')";
                       else
                           cond +=" AND t3.Expected_Salary <= ('"+ txt_Expected_Salary.getText()+"')";
                   }
                   
                   if(rdo_Yes.isSelected()==true)
                   {
                       employeed='1';
                   }
                   else if(rdo_No.isSelected()==true)
                   {
                       employeed='0';
                   }
                   else
                   {
                       employeed='\0';
                   }
                   
                   if(!(employeed=='\0'))
                   {
                       if(cond.isEmpty())
                           cond += " t1.Employeed = '"+ employeed +"'";
                       else
                           cond +=" AND t1.Employeed = '"+ employeed +"'";
                   }

                   //char managable;
                   if(rdo_Managable_Yes.isSelected()==true)
                   {
                       managable='Y';
                   }
                   else if(rdo_Managable_No.isSelected()==true)
                   {
                       managable='N';
                   }
                   else
                   {
                       managable='\0';
                   }
                   if(!(managable=='\0'))
                   {
                       if(cond.isEmpty())
                           cond += " t3.Managable = ( '"+ managable +"')";
                       else
                           cond +=" AND (t3.Managable = ('"+ managable +"'))";
                   }

                   char gender;
                   if(rdo_Female1.isSelected()==true)
                   {
                       gender='F';
                   }
                   else if(rdo_Male1.isSelected()==true)
                   {
                       gender='M';
                   }
                   else
                   {
                       gender='\0';
                   }
                   if(!(gender=='\0'))
                   {
                       if(cond.isEmpty())
                           cond += " t2.Gender = ( '"+ gender +"')";
                       else
                           cond +=" AND (t2.Gender = ('"+ gender +"'))";
                   }
                   if(cond=="")
                       cond=null;
                   DB_Query.selectFieldQuery(table,fields, cond);
                   dataCol=6;
                   DB_Query.rs.last();
                   dataRow=DB_Query.rs.getRow();
                    //System.out.println(dataRow);
                   mydata=new String[6][dataCol];
                   DB_Query.rs.beforeFirst();
                    while(DB_Query.rs.next())
                    {
                        
                        codeT = DB_Query.rs.getString("cand_code");
                        nameT = DB_Query.rs.getString("name");
                        cellT=DB_Query.rs.getString("Cell_No");
                        homeT=DB_Query.rs.getString("Home_Phone");
                        genderT=DB_Query.rs.getString("Gender");
                        if(employeed=='1')
                            emailT=DB_Query.rs.getString("RecruitedBy");
                        else
                            emailT=DB_Query.rs.getString("Email_Id");
                        Vector<String> tmp = new Vector<String>();
                        tmp.addElement(codeT);
                        mydata[i][0]=codeT;
                        tmp.addElement(nameT);
                        mydata[i][1]=nameT;
                        tmp.addElement(genderT);
                        if(genderT.equalsIgnoreCase("M"))
                            mydata[i][2]="Male";
                        else
                            mydata[i][2]="Female";
                        tmp.addElement(cellT);
                        mydata[i][3]=cellT;
                        tmp.addElement(homeT);
                        mydata[i][4]=homeT;
                        tmp.addElement(emailT);
                        mydata[i][5]=emailT;
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
                jsp.setBounds( 1, 45, 970,200 );
                panel_Content.add( jsp );
             
                 
    }

    void controls_Setting()
    {
        panel_Controls.setLayout(null);


        layeredPane = new JLayeredPane();
        layeredPane.setBorder(BorderFactory.createTitledBorder(" Search By "));
        layeredPane.setBounds( 1, 1, 970, 310);
        //getControlPaneContent();
        panel_Controls.add(layeredPane);



        lbl_Name = new JLabel( " Name:" );
        lbl_Name.setBounds( 50, 30, 80, 20 );
        layeredPane.add( lbl_Name );

        txt_Name = new JTextField();
        txt_Name.setBounds( 150, 30, 140, 20 );
        layeredPane.add( txt_Name );

         lbl_Higher_edu = new JLabel( "Higer Educatin:" );
         lbl_Higher_edu.setBounds( 50, 70, 150, 20 );
         layeredPane.add( lbl_Higher_edu );

         txt_Higher_Edu = new JTextField();
         txt_Higher_Edu.setBounds( 150, 70, 140, 20 );
         layeredPane.add( txt_Higher_Edu );

         lbl_Division = new JLabel( "Division/Class:" );
         lbl_Division.setBounds( 50,110, 220, 20 );
         layeredPane.add( lbl_Division );

         txt_Division = new JTextField();
         txt_Division.setBounds( 150, 110, 140, 20 );
         layeredPane.add( txt_Division );


        lbl_Percentage = new JLabel( "Percentage:" );
        lbl_Percentage.setBounds( 50, 150,290, 20 );
        layeredPane.add( lbl_Percentage );

        txt_Percentage = new JTextField();
        txt_Percentage.setBounds( 150,150, 140, 20 );
        layeredPane.add(txt_Percentage);


        lbl_Empleyeed = new JLabel( "Employeed:" );
        lbl_Empleyeed.setBounds(50, 190, 360, 20  );
        layeredPane.add( lbl_Empleyeed );
        ButtonGroup grpEmployeed = new ButtonGroup();
        rdo_Yes=new JRadioButton("Yes");
        
        rdo_Yes.setBounds(150,190,60,20);
        grpEmployeed.add( rdo_Yes );
        layeredPane.add(rdo_Yes);
        rdo_No=new JRadioButton("No");
        //rdo_No.setSelected(true);
        rdo_No.setBounds(210, 190, 60, 20 );
        layeredPane.add(rdo_No);
        grpEmployeed.add(rdo_No);

        rdo_All=new JRadioButton("All");
        rdo_All.setSelected(true);
        rdo_All.setBounds(270, 190, 60, 20 );
        layeredPane.add(rdo_All);
        grpEmployeed.add(rdo_All);


        lbl_Managable = new JLabel( "Managable:" );
        lbl_Managable.setBounds(50,230,430,20 );
        layeredPane.add( lbl_Managable );
        ButtonGroup grpManagable = new ButtonGroup();
        rdo_Managable_Yes=new JRadioButton("Yes");
        rdo_Managable_Yes.setBounds(150,230,60,20);
        grpManagable.add( rdo_Managable_Yes );
        layeredPane.add(rdo_Managable_Yes);
        rdo_Managable_No=new JRadioButton("No");
        rdo_Managable_No.setBounds(210,230 , 60, 20 );
         layeredPane.add(rdo_Managable_No);
        grpManagable.add(rdo_Managable_No);
        //rdo_Managable_Yes.setSelected(true);

        JRadioButton rdo_Managable_All=new JRadioButton("All");
        rdo_Managable_All.setBounds(270,230 , 60, 20 );
         layeredPane.add(rdo_Managable_All);
        grpManagable.add(rdo_Managable_All);
        rdo_Managable_All.setSelected(true);



        lbl_Exeperience = new JLabel( "Experience:" );
        lbl_Exeperience.setBounds( 550, 30, 73, 20 );
       layeredPane.add(lbl_Exeperience );

        txt_Experience = new JTextField();
        txt_Experience.setBounds( 650, 30, 140, 20 );
        layeredPane.add( txt_Experience );

        lbl_Preferred_Type = new JLabel( "Job Type:" );
        lbl_Preferred_Type.setBounds(550,70,285,20 );
       layeredPane.add( lbl_Preferred_Type );

        String preferred[]={"","Part Time","Full Time","Hour Basis"};
        cmb_Preferred_Type=new JComboBox(preferred);
        cmb_Preferred_Type.setBounds( 650, 70, 140, 20 );
        //cmb_Preferred_Type.setEditable(true);
        cmb_Preferred_Type.setFont(new Font("Serif", Font.PLAIN,12));
        layeredPane.add(cmb_Preferred_Type);


        lbl_Preferred_Jobs = new JLabel( "Preferred Jobs:" );
        lbl_Preferred_Jobs.setBounds(550, 110, 213, 20 );
        layeredPane.add( lbl_Preferred_Jobs );

        String preferredby[]={" ","Top Management- IT","Top Management- Non IT",
        "Web, Graphic Design","IT- Hardware","Industrial Products",
        "IT, Software Services","Marketing","Teaching, Education","Other"};
        cmb_Preferred_Jobs=new JComboBox(preferredby);
        cmb_Preferred_Jobs.setBounds(650, 110, 213, 20 );
        layeredPane.add( cmb_Preferred_Jobs );

        lbl_Expected_Salary = new JLabel( "Expected Salary:" );
        lbl_Expected_Salary.setBounds(550, 150, 255, 20 );
        layeredPane.add( lbl_Expected_Salary );

        txt_Expected_Salary = new JTextField();
        txt_Expected_Salary.setBounds( 650, 150, 140, 20 );
        layeredPane.add( txt_Expected_Salary );



        lbl_Gender1 = new JLabel( "Gender:" );
        lbl_Gender1.setBounds(550, 190, 355, 20 );
        layeredPane.add( lbl_Gender1 );
        ButtonGroup grpGender1 = new ButtonGroup();
        rdo_Male1=new JRadioButton("Male");
       // rdo_Male1.setSelected(true);
        rdo_Male1.setBounds(650,190,80,20);
        
        grpGender1.add( rdo_Male1 );
        layeredPane.add(rdo_Male1);
        rdo_Female1=new JRadioButton("Female");
        rdo_Female1.setBounds(730, 190, 80, 20 );
        layeredPane.add(rdo_Female1);
        grpGender1.add(rdo_Female1);
        rdo_All=new JRadioButton("Any");
        rdo_All.setBounds(810, 190, 80, 20 );
        layeredPane.add(rdo_All);
        grpGender1.add(rdo_All);
        rdo_All.setSelected(true);
        
         btn_Search=new JButton("Search",new ImageIcon("icons/search.png"));
         btn_Search.setBounds(300,260 ,105 ,30 );
         layeredPane.add( btn_Search );
         btn_Search.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                getTableData();
            }
        });

         btn_Reset=new JButton("Clear",new ImageIcon("icons/clear.png"));
         btn_Reset.setBounds(420,260 ,100 ,30 );
         layeredPane.add( btn_Reset );
          btn_Reset.addActionListener(new ActionListener()
          {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                 clearallfields();
            }
          });


         //btn_CandPrintPrev=new JButton(new ImageIcon("icons/print_preview.png"));
         btn_CandReportPrev.setBounds(860,1,100,30 );
         btn_CandReportPrev.setToolTipText("Get Report");
         panel_Content.add( btn_CandReportPrev );

         }
    void clearallfields()
    {
        txt_Division.setText("");
        txt_Expected_Salary.setText("");
        txt_Experience.setText("");
        txt_Higher_Edu.setText("");
        txt_Name.setText("");
        txt_Percentage.setText("");
       cmb_Preferred_Type.setSelectedIndex(0);
         cmb_Preferred_Jobs.setSelectedIndex(0);
     
    }
}
