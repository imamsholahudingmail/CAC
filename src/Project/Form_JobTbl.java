 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Project;

/**
 *
 * @author SHRUTI
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;
import java.util.*;

public class Form_JobTbl extends JInternalFrame

{
    GroupLayout grpLayout = new GroupLayout(getContentPane());
    JPanel panel_Content = new JPanel();

    JPanel panel_Controls = new JPanel();
    JLabel   lbl_OpenDate,   lbl_CloseDate,lbl_Name,lbl_Designation,    lbl_ann;

    JTextField txt_Name,txt_AnnuSal;
    JComboBox  cmb_Day,cmb_Month,cmb_Year,cmb_Day1,cmb_Month1,cmb_Year1,cmb_Designation;
    JTextArea txa_Preferred_Jobs;
    JButton   btn_Search,btn_Reset;
    static JButton btn_JobReportPrev=new JButton("Report",new ImageIcon("icons/report.png"));
 private JLayeredPane layeredPane;
    JTable table;
    int v,h;
    String job_codeT,companyT,designationT,opening_dateT,closeing_dateT,ann_salaryT;
    JScrollPane jsp;

    static int dataCol,dataRow;
    static String mydata[][];

    GregorianCalendar d = new GregorianCalendar();
    public Form_JobTbl()
    {
        dataCol=dataRow=0;

        setSize(700,500);
        setTitle("Search Job");
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
                    .addComponent(panel_Controls, GroupLayout.PREFERRED_SIZE, 310, GroupLayout.PREFERRED_SIZE))
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
        heading.addElement("Designation");
        heading.addElement("Opening Date");
        heading.addElement("Closing Date");
        heading.addElement("Ann. Salary");

        Vector data = new Vector();

        int i=0;
        try
        {

            String table="tbl_job";
           String fields="job_code,company,designation,opening_date,closeing_date,ann_salary" ;
           String cond="";
           if(!(txt_Name.getText().isEmpty()))
           {
               if(cond.isEmpty())
                   cond += " company like ('%"+ txt_Name.getText()+ "%')";
               else
                   cond += " AND company like ('%"+ txt_Name.getText()+"%')";
           }

           if(!(txt_AnnuSal.getText().isEmpty()))
           {
               if(cond.isEmpty())
                   cond += " ann_salary >='"+ txt_AnnuSal.getText()+ "'";
               else
                   cond += " AND ann_salary >='"+ txt_AnnuSal.getText()+ "'";
           }

           if(!(String.valueOf(cmb_Designation.getSelectedItem()).isEmpty()))
           {
               if(cond.isEmpty())
                   cond += " designation ='"+ String.valueOf(cmb_Designation.getSelectedItem())+ "'";
               else
                   cond += " AND designation >='"+ String.valueOf(cmb_Designation.getSelectedItem())+ "'";
           }

           if(!(String.valueOf(cmb_Year.getSelectedItem()).isEmpty()))
           {
               if(cond.isEmpty())
                   cond += " opening_date >='"+ String.valueOf(cmb_Year.getSelectedItem()) +"-"+
                           String.valueOf(cmb_Month.getSelectedItem())+ "-"+
                           String.valueOf(cmb_Day.getSelectedItem()) + "'";
               else
                   cond += " AND opening_date >='"+ String.valueOf(cmb_Year.getSelectedItem()) +"-"+
                           String.valueOf(cmb_Month.getSelectedItem())+ "-"+
                           String.valueOf(cmb_Day.getSelectedItem()) + "'";
           }

           if(!(String.valueOf(cmb_Year1.getSelectedItem()).isEmpty()))
           {
               if(cond.isEmpty())
                   cond += " closeing_date <='"+ String.valueOf(cmb_Year1.getSelectedItem()) +"-"+
                           String.valueOf(cmb_Month1.getSelectedItem())+ "-"+
                           String.valueOf(cmb_Day1.getSelectedItem()) + "'";
               else
                   cond += " AND closeing_date <='"+ String.valueOf(cmb_Year1.getSelectedItem()) +"-"+
                           String.valueOf(cmb_Month1.getSelectedItem())+ "-"+
                           String.valueOf(cmb_Day1.getSelectedItem()) + "'";
           }



           DB_Query.selectFieldQuery(table,fields, cond);
           dataCol=6;
           DB_Query.rs.last();
           dataRow=DB_Query.rs.getRow();

           mydata=new String[dataRow][dataCol];
           DB_Query.rs.beforeFirst();

            while(DB_Query.rs.next())
            {

                job_codeT = DB_Query.rs.getString("job_code");
                companyT = DB_Query.rs.getString("company");
                designationT=DB_Query.rs.getString("designation");
                opening_dateT=DB_Query.rs.getString("opening_date");
                closeing_dateT=DB_Query.rs.getString("closeing_date");
                ann_salaryT=DB_Query.rs.getString("ann_salary");
                Vector<String> tmp = new Vector<String>();
                //JOptionPane.showMessageDialog(null, DB_Query.rs.getString("name"));
                tmp.addElement(job_codeT);
                mydata[i][0]=job_codeT;
                tmp.addElement(companyT);
                mydata[i][1]=companyT;
                tmp.addElement(designationT);
                mydata[i][2]=designationT;
                tmp.addElement(opening_dateT);
                mydata[i][3]=opening_dateT;
                tmp.addElement(closeing_dateT);
                mydata[i][4]=closeing_dateT;
                tmp.addElement(ann_salaryT);
                mydata[i][5]=ann_salaryT;
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
        jsp.setBounds( 1, 50, 970,200 );
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



        lbl_Name = new JLabel( " Company Name:" );
        lbl_Name.setBounds( 50, 30, 100, 20 );
        layeredPane.add( lbl_Name );

        txt_Name = new JTextField();
        txt_Name.setBounds( 170, 30, 140, 20 );
        layeredPane.add( txt_Name );

        lbl_ann = new JLabel( " Annual Salary:" );
        lbl_ann.setBounds( 50, 70, 150, 20 );
        layeredPane.add( lbl_ann );
        lbl_ann.setVisible(false);

        txt_AnnuSal = new JTextField();
        txt_AnnuSal.setBounds( 170, 70, 140, 20  );
        layeredPane.add( txt_AnnuSal );
        txt_AnnuSal.setVisible(false);

         lbl_Designation = new JLabel( " Designation:" );
        lbl_Designation.setBounds( 50, 70, 150, 20 );

        layeredPane.add(lbl_Designation);



        String[] sal_size = { "","Trainee","Analyst","Senior Analyst","Team Leader","Project Manager"};

        cmb_Designation=new JComboBox(sal_size);

        cmb_Designation.setFont(new Font("Serif", Font.PLAIN,12));
        cmb_Designation.setBounds( 170, 70, 140, 20 );
        layeredPane.add( cmb_Designation );

        lbl_OpenDate = new JLabel( " Opening Date:" );
        lbl_OpenDate.setBounds(50, 110, 150, 20 );
        layeredPane.add(lbl_OpenDate);
        String[] dob_day;
        dob_day=new String[31];
        for(int i=0;i<31;i++)
            dob_day[i]=String.valueOf(i+1);
        cmb_Day=new JComboBox(dob_day);
        cmb_Day.setFont(new Font("Serif", Font.PLAIN,12));
        cmb_Day.setBounds( 170, 110, 38, 20 );
        layeredPane.add( cmb_Day );

        d.roll(Calendar.DAY_OF_YEAR, -1);
        int day = d.get(Calendar.DAY_OF_MONTH);

        cmb_Day.setSelectedItem(String.valueOf(day));

        String[] dob_month;
        dob_month=new String[12];
        for(int i=0;i<12;i++)
            dob_month[i]=String.valueOf(i+1);
        cmb_Month=new JComboBox(dob_month);
        cmb_Month.setFont(new Font("Serif", Font.PLAIN,12));
        cmb_Month.setBounds(220, 110, 38, 20 );
        layeredPane.add( cmb_Month );
        cmb_Month.setSelectedItem(String.valueOf(d.get(Calendar.MONTH)+1));

        String[] dob_year;
        dob_year=new String[60];
        for(int j=0;j<60;j++)
            dob_year[j]=String.valueOf(j+1950);
        cmb_Year=new JComboBox(dob_year);
        cmb_Year.setFont(new Font("Serif", Font.PLAIN,12));
        cmb_Year.setBounds( 270, 110,60, 20 );
        layeredPane.add( cmb_Year );
        cmb_Year.setSelectedItem(String.valueOf(d.get(Calendar.YEAR)));
        
         lbl_CloseDate = new JLabel( " Closing Date:" );
        lbl_CloseDate.setBounds(50, 150, 200, 20);
        layeredPane.add(lbl_CloseDate);

      

        String[] dob_day1;
        dob_day1=new String[31];
        for(int i=0;i<31;i++)
            dob_day1[i]=String.valueOf(i+1);
        cmb_Day1=new JComboBox(dob_day1);
        cmb_Day1.setFont(new Font("Serif", Font.PLAIN,12));
        cmb_Day1.setBounds( 170, 150, 38, 20 );
        layeredPane.add( cmb_Day1 );

        d.roll(Calendar.DAY_OF_MONTH, +1);
        int day1 = d.get(Calendar.DAY_OF_MONTH);

        cmb_Day1.setSelectedItem(String.valueOf(day1));

        String[] dob_month1;
        dob_month1=new String[12];
        for(int i=0;i<12;i++)
            dob_month1[i]=String.valueOf(i+1);
        cmb_Month1=new JComboBox(dob_month1);
        cmb_Month1.setFont(new Font("Serif", Font.PLAIN,12));
        cmb_Month1.setBounds( 220, 150, 38, 20 );
        layeredPane.add( cmb_Month1 );
        cmb_Month1.setSelectedItem(String.valueOf(d.get(Calendar.MONTH)+1));

        String[] dob_year1;
        dob_year1=new String[60];
        for(int j=0;j<60;j++)
            dob_year1[j]=String.valueOf(j+1950);
        cmb_Year1=new JComboBox(dob_year1);
        cmb_Year1.setFont(new Font("Serif", Font.PLAIN,12));
        cmb_Year1.setBounds( 270, 150, 60, 20 );
        layeredPane.add( cmb_Year1 );
        cmb_Year1.setSelectedItem(String.valueOf(d.get(Calendar.YEAR)));

         btn_Search=new JButton("Search",new ImageIcon("icons/search.png"));
         btn_Search.setBounds(300,260 ,105 ,30 );
         layeredPane.add( btn_Search );
         btn_Search.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                getReport();
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
         btn_JobReportPrev.setBounds(860,1,100,30 );
         btn_JobReportPrev.setToolTipText("Get Report");
         panel_Content.add( btn_JobReportPrev );

    }
    void clearallfields()
    {
        txt_AnnuSal.setText("");
        txt_Name.setText("");
        
        cmb_Day.setSelectedIndex(0);
        cmb_Day1.setSelectedIndex(0);
        cmb_Designation.setSelectedIndex(0);
        cmb_Month.setSelectedIndex(0);
        cmb_Month1.setSelectedIndex(0);
        cmb_Year.setSelectedIndex(0);
        cmb_Year1.setSelectedIndex(0);       
    }
}