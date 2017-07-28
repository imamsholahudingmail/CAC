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

import javax.swing.JOptionPane;


import javax.swing.filechooser.FileFilter;

class Form_Candidate extends JInternalFrame
{
    JPanel mainPanel;
    MDIDesktopManager desktop;
    private JTabbedPane tabbedPane;
    private		JPanel		panel_General_Info;
    private		JPanel		panel_Educational_Info;
    private		JPanel		panel_Professional_Info;
    private             JPanel          panel_Buttons;
    private             JPanel          panel_Primary_Info;
          
    GridBagLayout mainLayout=new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();
      
    GroupLayout grpLayout = new GroupLayout(getContentPane());
    JPanel topPanel = new JPanel();
       
    JLabel lbl_FName,lbl_LName,lbl_Code,lbl_Address,lbl_Phone,
            lbl_Search,lbl_Image,lbl_Resume,lbl_Resume_Source,
            lbl_Cell_No,lbl_Home_Phone,lbl_Gender,lbl_DOB,
            lbl_Exeperience,lbl_Education,lbl_Candidate_Type
            ,lbl_Managable, lbl_Preferred_Type,lbl_Empleyeed,
            lbl_Collected_Amount,lbl_Passport_No,lbl_Email_Id,
            lbl_Higher_edu,lbl_University,lbl_Division,lbl_Percentage,
            lbl_Expected_Salary,lbl_Preferred_Jobs,lbl_recruited;
    JTextField  txt_FName,txt_LName,txt_Cand_Code,txt_Address,txt_Phone,
                txt_Search,txt_Resume,txt_Cell_No,txt_Home_Phone,
                txt_Passport_No,txt_Email_Id,txt_Expected_Salary,
                txt_Experience,txt_Education,txt_Job_Type,
                tx_Colleced_Amount,txt_Higher_Edu,txt_University,
                txt_Division,txt_Percentage;
    JTextArea txa_Address,txa_Preferred_Jobs;
    JComboBox cmb_Candidate_Type,cmb_Preferred_Type,cmb_recruited,cmb_Preferred_Jobs;
    JRadioButton rdo_Male,rdo_Female,rdo_Managable_Yes,rdo_Managable_No,
                rdo_Employeed_Yes,rdo_Employeed_No, rdo_Managable_Lain;
    JComboBox cmb_SearchBy,cmb_Day,cmb_Month,cmb_Year;
    JButton btn_Search,btn_Resume_Change,btn_Picture_Change,btn_New,btn_Save,btn_Update,
            btn_Edit,btn_Delete,btn_Cancel;
    JFileChooser flc_Resume,flc_Picture;
    
    private String global_Cand_Code;
    int employeed=0;
    String picture,resume,pictureLocation,resumeLocation,searched_picture,
            searched_resume,gender, managable, found_pic;
    boolean found=false;
    //private Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    GregorianCalendar d = new GregorianCalendar();
    int DOB_year;
    public Form_Candidate()
    {
        searched_picture=picture=null;
        searched_resume=resume=found_pic=null;
        managable="Y";
        gender="M";
        setSize(500,400);
        setTitle("Data Pribadi Siswa");
        setMaximizable(true);
        setIconifiable(true);
        setClosable(true);
        setResizable(true);
        
        topPanel.setLayout( new BorderLayout() );
        getContentPane().add( topPanel );
        createGeneralInfo();
	createEducationalInfo();
	createProfessionalInfo();

        // Create a tabbed pane
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab( "Data Ayah", panel_General_Info );
        tabbedPane.addTab( "Data Ibu", panel_Educational_Info );
        tabbedPane.addTab( "Data Wali", panel_Professional_Info );
        topPanel.add( tabbedPane, BorderLayout.CENTER );
      
        //panel_General_Info.add(getContentPane());
        String[] searchBy = {"No Induk","Nama"};
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
                if(global_Cand_Code!=null)
                {

                    //clearAllFields();
                    disableAllButtons();
                    disableAllFields();
                    showGeneralData();
                    showEducationData();
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
        
        panelPrimaryInfoSetting();
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
                .addGap(50,50, 50)
                .addComponent(panel_Primary_Info))                
             .addGroup(grpLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(topPanel, GroupLayout.PREFERRED_SIZE, 900, GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(panel_Buttons, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
            )
        );
        grpLayout.setVerticalGroup(
            grpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(grpLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(grpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_Search,GroupLayout.PREFERRED_SIZE, 25,GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Search)
                    .addComponent(cmb_SearchBy, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Search, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(grpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(panel_Primary_Info, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(grpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(topPanel)
                    .addGroup(grpLayout.createSequentialGroup()
                    .addGap(45, 45, 45)
                    .addComponent(panel_Buttons)))                   
            ));
        panel_Buttons.add(btn_Search);
        disableAllFields();
        disableAllButtons();
     }   
    void createGeneralInfo()
    {
        panel_General_Info = new JPanel();
        panel_General_Info.setLayout( null );

        lbl_Cell_No = new JLabel( "No Handphone:" );
        lbl_Cell_No.setBounds( 50, 25, 80, 20 );
        panel_General_Info.add( lbl_Cell_No );

        txt_Cell_No = new JTextField();
        txt_Cell_No.setBounds( 150, 25, 150, 20 );
        panel_General_Info.add( txt_Cell_No );

        lbl_Home_Phone = new JLabel( "No Telpon:" );
        lbl_Home_Phone.setBounds(50, 75, 80, 20 );
        panel_General_Info.add( lbl_Home_Phone );

        txt_Home_Phone = new JTextField();
        txt_Home_Phone.setBounds( 150, 75, 150, 20 );
        panel_General_Info.add( txt_Home_Phone );

        lbl_Gender = new JLabel( "Jenis Kelamin:" );
        lbl_Gender.setBounds(50, 125, 150, 20 );
        panel_General_Info.add( lbl_Gender );
        ButtonGroup grpGender = new ButtonGroup();
        rdo_Male=new JRadioButton("Male");
        rdo_Male.setSelected(true);
        rdo_Male.setBounds(150,125,80,20);
        grpGender.add( rdo_Male );
        panel_General_Info.add(rdo_Male);
        rdo_Female=new JRadioButton("Female");
        rdo_Female.setBounds(230, 125, 80, 20 );
        panel_General_Info.add(rdo_Female);
        grpGender.add(rdo_Female); 
         rdo_Male.addActionListener(new ActionListener() {
            @Override
        public void actionPerformed(ActionEvent ae) {
            gender="M";
          }
        });
        rdo_Female.addActionListener(new ActionListener() {
            @Override
        public void actionPerformed(ActionEvent ae) {
            gender="F";
          }
        });

        lbl_Address = new JLabel( "Alamat:" );
        lbl_Address.setBounds(50, 175, 80, 20 );
        panel_General_Info.add( lbl_Address );

        txa_Address = new JTextArea();
        txa_Address.setLineWrap(true);
        txa_Address.setWrapStyleWord(true);
        JScrollPane scrollPane_Address = new JScrollPane(txa_Address);
        scrollPane_Address.setVerticalScrollBarPolicy(
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_Address.setPreferredSize(new Dimension(250, 250));
        scrollPane_Address.setBounds(150, 175, 150, 80);
        panel_General_Info.add(scrollPane_Address);

        lbl_DOB = new JLabel( "Tanggal Lahir" );
        lbl_DOB.setBounds(600, 15, 100, 20 );
        panel_General_Info.add(lbl_DOB);
        String[] dob_day;
        dob_day=new String[31];
        for(int i=0;i<31;i++)
            dob_day[i]=String.valueOf(i+1);
        cmb_Day=new JComboBox(dob_day);
        cmb_Day.setFont(new Font("Serif", Font.PLAIN,12));
        cmb_Day.setBounds( 700, 15, 38, 20 );
        panel_General_Info.add( cmb_Day );
        String[] dob_month;
        dob_month=new String[12];
        for(int i=0;i<12;i++)
            dob_month[i]=String.valueOf(i+1);
        cmb_Month=new JComboBox(dob_month);
        cmb_Month.setFont(new Font("Serif", Font.PLAIN,12));
        cmb_Month.setBounds( 745, 15, 38, 20 );
        panel_General_Info.add( cmb_Month );
        String[] dob_year;
        dob_year=new String[50];
        for(int j=0;j<50;j++)
            dob_year[j]=String.valueOf(d.get(Calendar.YEAR)-j-20);
        cmb_Year=new JComboBox(dob_year);
        cmb_Year.setFont(new Font("Serif", Font.PLAIN,12));
        cmb_Year.setBounds( 790, 15, 50, 20 );
        panel_General_Info.add( cmb_Year );
        d.roll(Calendar.YEAR, -23);
        DOB_year= d.get(Calendar.YEAR);

        cmb_Year.setSelectedItem(String.valueOf(DOB_year));

        lbl_Passport_No = new JLabel( "No KTP.:" );
        lbl_Passport_No.setBounds( 600, 75, 80, 20 );
        panel_General_Info.add( lbl_Passport_No );

        txt_Passport_No = new JTextField();
        txt_Passport_No.setBounds( 700, 75, 150, 20 );
        panel_General_Info.add( txt_Passport_No );
        
        lbl_Email_Id = new JLabel( "email:" );
        lbl_Email_Id.setBounds( 600, 125, 80, 20 );
        panel_General_Info.add( lbl_Email_Id );

        txt_Email_Id = new JTextField();
        txt_Email_Id.setBounds( 700, 125, 150, 20 );
        panel_General_Info.add( txt_Email_Id );
    }
    void createProfessionalInfo()
    {
        panel_Professional_Info = new JPanel();
        panel_Professional_Info.setLayout( null );

        lbl_Exeperience = new JLabel( "Nama Wali:" );
        lbl_Exeperience.setBounds( 50, 25, 80, 20 );
        panel_Professional_Info.add(lbl_Exeperience );

        txt_Experience = new JTextField();
        txt_Experience.setBounds( 150, 25, 150, 20 );
        panel_Professional_Info.add( txt_Experience );

        lbl_Expected_Salary = new JLabel( "No Telp:" );
        lbl_Expected_Salary.setBounds(50, 75, 100, 20 );
        panel_Professional_Info.add( lbl_Expected_Salary );

        txt_Expected_Salary = new JTextField();
        txt_Expected_Salary.setBounds( 150, 75, 150, 20 );
        panel_Professional_Info.add( txt_Expected_Salary );
        
        lbl_Preferred_Type = new JLabel( "Pengeluaran per bulan:" );
        lbl_Preferred_Type.setBounds(50, 125, 100, 20 );
        panel_Professional_Info.add( lbl_Preferred_Type );
        
        String preferred[]={"<500.000","500.000- 999.999","1.000.000-2.999.999","3.000.00 ke atas"};
        cmb_Preferred_Type=new JComboBox(preferred);
        cmb_Preferred_Type.setBounds( 150, 125, 150, 20 );
        cmb_Preferred_Type.setFont(new Font("Serif", Font.PLAIN,12));
        panel_Professional_Info.add(cmb_Preferred_Type);

        lbl_Managable = new JLabel( "Status Anak:" );
        lbl_Managable.setBounds(50, 175, 150, 20 );
        panel_Professional_Info.add( lbl_Managable );
        ButtonGroup grpManagable = new ButtonGroup();
        rdo_Managable_Yes=new JRadioButton("Kandung");
        rdo_Managable_Yes.setBounds(150,175,80,20);
        grpManagable.add( rdo_Managable_Yes );
        panel_Professional_Info.add(rdo_Managable_Yes);
        rdo_Managable_No=new JRadioButton("Tiri");
        rdo_Managable_No.setBounds(230, 175, 80, 20 );
        panel_Professional_Info.add(rdo_Managable_No);
        grpManagable.add(rdo_Managable_No);
        rdo_Managable_Lain=new JRadioButton("Saudara");
        rdo_Managable_Lain.setBounds(310, 175, 80, 20 );
        panel_Professional_Info.add(rdo_Managable_Lain);
	grpManagable.add(rdo_Managable_Lain);	

        rdo_Managable_Yes.setSelected(true);
        rdo_Managable_Yes.addActionListener(new ActionListener() {
            @Override
        public void actionPerformed(ActionEvent ae) {
            managable="Y";
          }
        });
        rdo_Managable_No.addActionListener(new ActionListener() {
            @Override
        public void actionPerformed(ActionEvent ae) {
            managable="N";
          }
        });

        lbl_Preferred_Jobs = new JLabel( "Pekerjaan:" );
        lbl_Preferred_Jobs.setBounds(600, 25, 100, 20 );
        panel_Professional_Info.add( lbl_Preferred_Jobs );

        String preferredby[]={"Top Management- IT","Top Management- Non IT",
        "Web, Graphic Design","IT- Hardware","Industrial Products",
        "IT, Software Services","Marketing","Teaching, Education","Other"};
        cmb_Preferred_Jobs=new JComboBox(preferredby);
        cmb_Preferred_Jobs.setBounds( 700, 25, 150, 20 );
        cmb_Preferred_Jobs.setFont(new Font("Serif", Font.PLAIN,12));
        panel_Professional_Info.add(cmb_Preferred_Jobs);

        lbl_recruited = new JLabel( "Pendidikan:" );
        lbl_recruited.setBounds(600, 75, 100, 20 );
        panel_Professional_Info.add( lbl_recruited );

        //String recruited[]={"Wipro","TCS","Ocwen","Accenture","Infosys","Dell","IBM"};
        cmb_recruited=new JComboBox();
        getCompanyName();
        cmb_recruited.setBounds( 700, 75, 150, 20 );
        cmb_recruited.setFont(new Font("Serif", Font.PLAIN,12));
        panel_Professional_Info.add(cmb_recruited);


    }
    void createEducationalInfo()
    {
        panel_Educational_Info = new JPanel();
        panel_Educational_Info.setLayout( null );

        lbl_Higher_edu = new JLabel( "Nama Ibu:" );
        lbl_Higher_edu.setBounds( 50, 25, 100, 20 );
        panel_Educational_Info.add( lbl_Higher_edu );

        txt_Higher_Edu = new JTextField();
        txt_Higher_Edu.setBounds( 150, 25, 150, 20 );
        panel_Educational_Info.add( txt_Higher_Edu );

        lbl_University = new JLabel( "No Telepon:" );
        lbl_University.setBounds( 50, 75, 100, 20 );
        panel_Educational_Info.add( lbl_University );

        txt_University = new JTextField();
        txt_University.setBounds( 150, 75, 150, 20 );
        panel_Educational_Info.add( txt_University );

        lbl_Division = new JLabel( "No KTP:" );
        lbl_Division.setBounds( 50, 125, 100, 20 );
        panel_Educational_Info.add( lbl_Division );

        txt_Division = new JTextField();
        txt_Division.setBounds( 150, 125, 150, 20 );
        panel_Educational_Info.add( txt_Division );

        lbl_Percentage = new JLabel( "email:" );
        lbl_Percentage.setBounds( 50, 175, 100, 20 );
        panel_Educational_Info.add( lbl_Percentage );

        txt_Percentage = new JTextField();
        txt_Percentage.setBounds( 150, 175, 150, 20 );
        panel_Educational_Info.add(txt_Percentage);

		
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
            disableAllButtons();
            enableAllFields();
            btn_Save.setEnabled(true);
            btn_Cancel.setEnabled(true);
            clearAllFields();
            getNewId();
            txt_FName.requestFocus();
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
                insertCandidate();
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
                 if(checkValidation()==true)
                {
                    updateCandidate();
                    disableAllButtons();
                    disableAllFields();
                    clearAllFields();
                 }
          }
        });
        panel_Buttons.add(btn_Update);
        btn_Delete=new JButton(new ImageIcon("icons/delete.png"));
        btn_Delete.addActionListener(new ActionListener() {
            @Override
        public void actionPerformed(ActionEvent ae) {
            deleteCandidate();
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

        
        if(Main.user_auth.indexOf("04")>=0)
        {
            btn_New.setVisible(true);
            btn_Save.setVisible(true);
        }
        else
        {
            btn_New.setVisible(false);
            btn_Save.setVisible(false);
        }
        if(Main.user_auth.indexOf("05")>=0)
        {
            btn_Edit.setVisible(true);
            btn_Update.setVisible(true);
        }
        else
        {
            btn_Edit.setVisible(false);
            btn_Update.setVisible(false);
        }
        if(Main.user_auth.indexOf("06")>=0)
        {
            btn_Delete.setVisible(true);
        }
        else
        {
            btn_Delete.setVisible(false);
        }

    }
    void panelPrimaryInfoSetting()
    {
        flc_Resume = new JFileChooser();
        FileFilter type1 = new ExtensionFilter("PDF or DOC",new String[] { ".pdf", ".doc", ".docx"});
        flc_Resume.addChoosableFileFilter(type1);
        lbl_Code=new JLabel("No Induk :");
        txt_Cand_Code=new JTextField();
        lbl_FName=new JLabel("Nama :");
        txt_FName=new JTextField();
        lbl_LName=new JLabel("Kelas :");
        txt_LName=new JTextField();
        lbl_Empleyeed=new JLabel("Aktif");
        rdo_Employeed_Yes=new JRadioButton("Ya");
        rdo_Employeed_No=new JRadioButton("Tidak");
        rdo_Employeed_No.setSelected(true);
        lbl_Resume=new JLabel("Scan Ijazah :");
       
        lbl_Resume_Source=new JLabel("Null");
        btn_Resume_Change = new JButton("Change");
        btn_Resume_Change.addActionListener(new ActionListener() {
            @Override
        public void actionPerformed(ActionEvent ae) {
            int returnVal = flc_Resume.showOpenDialog(null);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = flc_Resume.getSelectedFile();
                //This is where a real application would open the file.
                lbl_Resume_Source.setText(file.getName());
                resume=file.getName();
                resumeLocation=file.getAbsolutePath();
            } else {
                lbl_Resume_Source.setText("Null");
            }
          }
        });
        ImageIcon pic = new ImageIcon("Pictures/none.jpg");
        lbl_Image=new JLabel(pic);
        lbl_Image.setBorder(BorderFactory.createLineBorder(Color.white));
        btn_Picture_Change = new JButton("Change");
        flc_Picture=new JFileChooser();
        FileFilter type2 = new ExtensionFilter("Image files",new String[] { ".jpg", ".gif", ".jpeg", ".png" });
        flc_Picture.addChoosableFileFilter(type2);
        btn_Picture_Change.addActionListener(new ActionListener() {
            @Override
        public void actionPerformed(ActionEvent ae) {
            int returnVal = flc_Picture.showOpenDialog(null);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = flc_Picture.getSelectedFile();
                //This is where a real application would open the file.
                lbl_Image.setIcon(new ImageIcon(file.getAbsolutePath()));
                picture=file.getName();
                pictureLocation=file.getAbsolutePath();
                
            } else {
                picture=null;
            }
          }
        });

       
        panel_Primary_Info=new JPanel();
        panel_Primary_Info.setLayout(null);
        lbl_Code.setBounds(10,10,75,20);
        panel_Primary_Info.add(lbl_Code);
        txt_Cand_Code.setBounds(100,10,200,20);
        panel_Primary_Info.add(txt_Cand_Code);
        lbl_FName.setBounds(10,40,75,20);
        panel_Primary_Info.add(lbl_FName);
        txt_FName.setBounds(100,40,200,20);
        panel_Primary_Info.add(txt_FName);
        lbl_LName.setBounds(10,70,75,20);
        panel_Primary_Info.add(lbl_LName);
        txt_LName.setBounds(100,70,200,20);
        panel_Primary_Info.add(txt_LName);
        
        lbl_Resume.setBounds(10,130,75,20);
        panel_Primary_Info.add(lbl_Resume);
        lbl_Resume_Source.setBounds(100,130,200,20);
        panel_Primary_Info.add(lbl_Resume_Source);
        btn_Resume_Change.setBounds(100,150,80,20);
        panel_Primary_Info.add(btn_Resume_Change);
        panel_Primary_Info.add(txt_LName);
        
        lbl_Empleyeed.setBounds(10,100,75,20);
        panel_Primary_Info.add(lbl_Empleyeed);
        ButtonGroup grpEmployeed=new ButtonGroup();
        grpEmployeed.add(rdo_Employeed_Yes);
        grpEmployeed.add(rdo_Employeed_No);
        rdo_Employeed_Yes.setBounds(100,100,50,20);
        panel_Primary_Info.add(rdo_Employeed_Yes);
        rdo_Employeed_No.setBounds(150,100,50,20);
        panel_Primary_Info.add(rdo_Employeed_No);
        
        lbl_Image.setBounds(750,0,120,150);
        panel_Primary_Info.add(lbl_Image);
        btn_Picture_Change.setBounds(775,155,80,20);
        panel_Primary_Info.add(btn_Picture_Change);
    }
    void getNewId()
    {
      
        try
        {
           String table;
           table="tbl_cand_primary";
           DB_Query.selectFieldQuery(table,"(MAX(cand_Code)+1)  as Cand_Code" ,null);

            if(DB_Query.rs.next())
            {
                txt_Cand_Code.setText(DB_Query.rs.getString("Cand_Code"));
            }
            if((DB_Query.rs.getString("Cand_Code"))==null)
            {
               txt_Cand_Code.setText("1");
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
    void getCompanyName()
    {
       String cond,table,field;
       table="tbl_comp_primary";
        cond=null;
        field="Name";
        DB_Query.selectFieldQuery(table,field,cond);
        try
        {
            cmb_recruited.addItem("");
            while(DB_Query.rs.next())
            {
              cmb_recruited.addItem(DB_Query.rs.getString("Name"));
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
    void showPrimaryData()
    {
       String cond,table;
       table="tbl_cand_primary";
       if(cmb_SearchBy.getSelectedIndex()== 0)
       {                    
                cond= "Cand_Code = '" + txt_Search.getText()+"'";               
       }
       else
       {
           cond="FName like ('"+ txt_Search.getText() +"%') OR LName like ('"+ txt_Search.getText()+"%')";           
       }
       
        DB_Query.selectAllQuery(table, cond);
        try
        {
            
            if(DB_Query.rs.next())
            {
                //JOptionPane.showMessageDialog(null, table +":"+cond);
               String cand_code = DB_Query.rs.getString("Cand_Code");
               global_Cand_Code=cand_code;
               String FName = DB_Query.rs.getString("FName");
               String LName = DB_Query.rs.getString("LName");
               txt_Cand_Code.setText(String.valueOf(cand_code));
               txt_FName.setText(FName);
               txt_LName.setText(LName); 
               if(DB_Query.rs.getString("Picture")==null || DB_Query.rs.getString("Picture").isEmpty())
               {
                   ImageIcon pic = new ImageIcon("Pictures/none.jpg");
                   lbl_Image.setIcon(pic);
               }
               else
               {
                   picture=DB_Query.rs.getString("Picture");
                   ImageIcon pic = new ImageIcon("Pictures/"+picture);
                   lbl_Image.setIcon(pic);
                   searched_picture=picture;
                   found_pic=picture;
               }
               if(DB_Query.rs.getString("Resume")!=null)
               {
                    resume=DB_Query.rs.getString("Resume");
                    lbl_Resume_Source.setText(resume);
                    resumeLocation="Resumes/"+DB_Query.rs.getString("Resume");
                    searched_resume=resume;

                    getHyperLink(lbl_Resume_Source);
               }
               employeed=DB_Query.rs.getInt("Employeed");
               
               if(employeed==1)
               {
                   rdo_Employeed_Yes.setSelected(true);
               }
               else
               {
                   rdo_Employeed_No.setSelected(true);
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
       table="tbl_cand_general";
                                
                cond= "Cand_Code = '" + global_Cand_Code+"'";               
       
       
        DB_Query.selectAllQuery(table, cond);
        try
        {
            
            if(DB_Query.rs.next())
            {
               String Cell_No = DB_Query.rs.getString("Cell_No");
               String Home_Phone = DB_Query.rs.getString("Home_Phone"); 
               String Gender = DB_Query.rs.getString("Gender");
               String Address = DB_Query.rs.getString("Address");
               String DOB=DB_Query.rs.getString("DOB");
               if(DOB!=null)
               {
                   StringTokenizer dt = new StringTokenizer(DOB, "-");
                  cmb_Year.setSelectedItem(dt.nextToken());
                  cmb_Month.setSelectedItem(String.valueOf(Integer.valueOf(dt.nextToken())));
                  cmb_Day.setSelectedItem(String.valueOf(Integer.valueOf(dt.nextToken())));
               }


               String Passport_No = DB_Query.rs.getString("Passport_No");
               String Email_Id = DB_Query.rs.getString("Email_Id");
               txt_Cell_No.setText(Cell_No);
               txt_Home_Phone.setText(Home_Phone);            
               if("M".equalsIgnoreCase(Gender))
               {
                   gender="M";
                   rdo_Male.setSelected(true);
               }
               else
               {
                    gender="F";
                   rdo_Female.setSelected(true);
               }
               txa_Address.setText(Address);
               txt_Passport_No.setText(Passport_No);
               txt_Email_Id.setText(Email_Id);

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
    void showEducationData()
    {
       String cond,table;
       table="tbl_cand_education";

                cond= "Cand_Code = '" + global_Cand_Code+"'";


        DB_Query.selectAllQuery(table, cond);
        try
        {

            if(DB_Query.rs.next())
            {
               String Higer_Edu  = DB_Query.rs.getString("Higer_Edu");
               String University = DB_Query.rs.getString("University");
               String Division = DB_Query.rs.getString("Division");
               String Percentage = DB_Query.rs.getString("Percentage");
               
               txt_Higher_Edu.setText(Higer_Edu);
               txt_University.setText(University);
               txt_Division.setText(Division);
               txt_Percentage.setText(Percentage.toString());
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
    void showProfessionalData()
    {
       String cond,table;
       table="tbl_cand_professional";

                cond= "Cand_Code = '" + global_Cand_Code+"'";


        DB_Query.selectAllQuery(table, cond);
        try
        {

            if(DB_Query.rs.next())
            {
               String Experience  = DB_Query.rs.getString("Experience");
               String Expected_Salary = DB_Query.rs.getString("Expected_Salary");
               String Preferred_Type = DB_Query.rs.getString("Preferred_Type");
               String Managable = DB_Query.rs.getString("Managable");
               String Preferred_Jobs = DB_Query.rs.getString("Preferred_Jobs");
               String RecruitedBy=DB_Query.rs.getString("RecruitedBy");
               txt_Experience.setText(Experience);
               txt_Expected_Salary.setText(Expected_Salary);
               cmb_Preferred_Type.setSelectedItem(Preferred_Type);
               cmb_recruited.setSelectedItem(RecruitedBy);
               if(Managable.equalsIgnoreCase("y"))
               {
                    managable="Y";
                    rdo_Managable_Yes.setSelected(true);
               }
               else
               {
                   managable="N";
                   rdo_Managable_No.setSelected(true);
               } 
                cmb_Preferred_Jobs.setSelectedItem(Preferred_Jobs);
              
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
    
    void insertCandidate()
    {
       String str;       
       
       try
       {
           str="Insert into  tbl_cand_primary values(?,?,?,?,?,?)";
           DBConnect.connect();            
           DBConnect.conn.setAutoCommit(false);
           DB_Query.ps = DBConnect.conn.prepareStatement(str);
           
           DB_Query.ps.setString(1, txt_Cand_Code.getText());
           DB_Query.ps.setString(2, txt_FName.getText());
           DB_Query.ps.setString(3, txt_LName.getText());
           DB_Query.ps.setString(4, resume);
           if(picture!=null)
                 picture=getDistinctName()+".jpg";
           DB_Query.ps.setString(5, picture);
           //if(rdo_Employeed_Yes)
           if(rdo_Employeed_Yes.isSelected()==true)
           {
               employeed=1;
           }
           else
           {
               employeed=0;
           }
           DB_Query.ps.setString(6, String.valueOf(employeed));
           DB_Query.dclQuery("insert");
           if(resume!=null)
            CopyFile.Copy(resumeLocation, "Resumes/"+resume);
           if(picture!=null)
           {
                CreateThumbnail ct = new CreateThumbnail(pictureLocation);
                ct.getThumbnail(120, CreateThumbnail.HORIZONTAL);
                ct.saveThumbnail(new File("Pictures/"+ picture), CreateThumbnail.IMAGE_JPEG);
           }

           //Insert in the tbl_cand_general
           str="Insert into  tbl_cand_general values(?,?,?,?,?,?,?,?)";
           DBConnect.connect();
           DBConnect.conn.setAutoCommit(false);
           DB_Query.ps = DBConnect.conn.prepareStatement(str);

           DB_Query.ps.setString(1, txt_Cand_Code.getText());
           DB_Query.ps.setString(2, txt_Cell_No.getText() );
           DB_Query.ps.setString(3, txt_Home_Phone.getText());
           DB_Query.ps.setString(4, gender);
           DB_Query.ps.setString(5, txa_Address.getText());
           DB_Query.ps.setString(6, String.valueOf(cmb_Year.getSelectedItem())+"-"+
                                    String.valueOf(cmb_Month.getSelectedItem())+"-"+
                                    String.valueOf(cmb_Day.getSelectedItem()));
           DB_Query.ps.setString(7, txt_Passport_No.getText());
           DB_Query.ps.setString(8, txt_Email_Id.getText());
           DB_Query.dclQuery("insert");

           //Insert in the tbl_cand_education
           str="Insert into  tbl_cand_education values(?,?,?,?,?)";
           DBConnect.connect();
           DBConnect.conn.setAutoCommit(false);
           DB_Query.ps = DBConnect.conn.prepareStatement(str);

           DB_Query.ps.setString(1, txt_Cand_Code.getText());
           DB_Query.ps.setString(2, txt_Higher_Edu.getText() );
           DB_Query.ps.setString(3, txt_University.getText());
           DB_Query.ps.setString(4, txt_Division.getText());
           if(txt_Percentage.getText()==null || (txt_Percentage.getText()).isEmpty())
           {
               txt_Percentage.setText("0");
           }
           DB_Query.ps.setInt(5, Integer.valueOf(txt_Percentage.getText()));
           DB_Query.dclQuery("insert");

           //Insert in the tbl_cand_professional
           str="Insert into  tbl_cand_professional values(?,?,?,?,?,?,?)";
           DBConnect.connect();
           DBConnect.conn.setAutoCommit(false);
           DB_Query.ps = DBConnect.conn.prepareStatement(str);

           DB_Query.ps.setString(1, txt_Cand_Code.getText());
           if(txt_Experience.getText()==null || (txt_Experience.getText()).isEmpty())
           {
               txt_Experience.setText("0");
           }
           DB_Query.ps.setInt(2, Integer.valueOf(txt_Experience.getText()));
           if(txt_Expected_Salary.getText()==null || (txt_Expected_Salary.getText()).isEmpty())
           {
               txt_Expected_Salary.setText("0");
           }
           DB_Query.ps.setInt(3, Integer.valueOf(txt_Expected_Salary.getText()));
           DB_Query.ps.setString(4, String.valueOf(cmb_Preferred_Type.getSelectedItem()));
           DB_Query.ps.setString(5, managable);
           DB_Query.ps.setString(6, String.valueOf(cmb_Preferred_Jobs.getSelectedItem()));
           DB_Query.ps.setString(7, String.valueOf(cmb_recruited.getSelectedItem()));

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
       if(picture!=null)
            if(!(searched_picture==(picture)))
                picture=getDistinctName()+".jpg";
       
       try
       {
           str="Update tbl_cand_primary set Fname=?, Lname=?, Resume=?, Picture=? , Employeed=? Where Cand_Code=?";
           DBConnect.connect();            
           DBConnect.conn.setAutoCommit(false);
           DB_Query.ps = DBConnect.conn.prepareStatement(str);           
           DB_Query.ps.setString(1, txt_FName.getText());
           DB_Query.ps.setString(2, txt_LName.getText());
           DB_Query.ps.setString(3, resume);
           DB_Query.ps.setString(4, picture);
           if(rdo_Employeed_Yes.isSelected()==true)
           {
               employeed=1;
           }
           else
           {
               employeed=0;
           }
           DB_Query.ps.setString(5, String.valueOf(employeed));
           DB_Query.ps.setString(6, txt_Cand_Code.getText());
           DB_Query.dclQuery("update");
           if(resume!=null )
               if((!(searched_resume==(resume))))
                CopyFile.Copy(resumeLocation, "Resumes/"+resume);
           if(picture!=null )
               if(!(searched_picture==(picture)))
               {
                    File target = new File("Pictures/"+ found_pic);
                    target.delete();
                    CreateThumbnail ct = new CreateThumbnail(pictureLocation);
                    ct.getThumbnail(120, CreateThumbnail.HORIZONTAL);
                    ct.saveThumbnail(new File("Pictures/"+ picture), CreateThumbnail.IMAGE_JPEG);
               }

           str="Update tbl_cand_general set Cell_No=?, Home_Phone=?, Gender=?, Address=? , DOB=? , Passport_No=?, Email_Id=? Where Cand_Code=?";
           DBConnect.connect();
           DBConnect.conn.setAutoCommit(false);
           DB_Query.ps = DBConnect.conn.prepareStatement(str);
           DB_Query.ps.setString(1, txt_Cell_No.getText());
           DB_Query.ps.setString(2, txt_Home_Phone.getText());
           DB_Query.ps.setString(3, gender);
           DB_Query.ps.setString(4, txa_Address.getText());
           DB_Query.ps.setString(5, String.valueOf(cmb_Year.getSelectedItem())+"-"+
                                    String.valueOf(cmb_Month.getSelectedItem())+"-"+
                                    String.valueOf(cmb_Day.getSelectedItem()));
           DB_Query.ps.setString(6, txt_Passport_No.getText());
           DB_Query.ps.setString(7, txt_Email_Id.getText());
           DB_Query.ps.setString(8, txt_Cand_Code.getText());
           DB_Query.dclQuery("update");

           str="Update tbl_cand_education set Higer_Edu=?, University=?, Division=?, Percentage=?  Where Cand_Code=?";
           DBConnect.connect();
           DBConnect.conn.setAutoCommit(false);
           DB_Query.ps = DBConnect.conn.prepareStatement(str);
           DB_Query.ps.setString(1, txt_Higher_Edu.getText() );
           DB_Query.ps.setString(2, txt_University.getText());
           DB_Query.ps.setString(3, txt_Division.getText());
           DB_Query.ps.setInt(4, Integer.valueOf(txt_Percentage.getText()));
           DB_Query.ps.setString(5, txt_Cand_Code.getText());
           DB_Query.dclQuery("update");

           str="Update tbl_cand_professional set Experience=?, Expected_" +
                   "Salary=?, Preferred_Type=?, Managable=? ,Preferred_Jobs=?, RecruitedBy=?" +
                   " Where Cand_Code=?";
           DBConnect.connect();
           DBConnect.conn.setAutoCommit(false);
           DB_Query.ps = DBConnect.conn.prepareStatement(str);
           if(txt_Experience.getText()==null || (txt_Experience.getText()).isEmpty())
           {
               txt_Experience.setText("0");
           }
           DB_Query.ps.setInt(1, Integer.valueOf(txt_Experience.getText()));
           if(txt_Expected_Salary.getText()==null || (txt_Expected_Salary.getText()).isEmpty())
           {
               txt_Expected_Salary.setText("0");
           }
           DB_Query.ps.setInt(2, Integer.valueOf(txt_Expected_Salary.getText()));
           DB_Query.ps.setString(3, String.valueOf(cmb_Preferred_Type.getSelectedItem()));
           DB_Query.ps.setString(4, managable);
           DB_Query.ps.setString(5, String.valueOf(cmb_Preferred_Jobs.getSelectedItem()));
           DB_Query.ps.setString(6, String.valueOf(cmb_recruited.getSelectedItem()));
           DB_Query.ps.setString(7, txt_Cand_Code.getText());
           DB_Query.dclQuery("update");

           JOptionPane.showMessageDialog(null, "Data Has Been Updated Successfully.");
       }
       catch(SQLException e)
       {
           JOptionPane.showMessageDialog(null, "Error Occured :" + e);
       }        
    }

    void deleteCandidate()
    {
       String str;
       
       try
       {
           //Delete From The tbl_cand_general
           int close = JOptionPane.showConfirmDialog(null,"Do you want delete this record?",
                                                        "Delete Record",JOptionPane.YES_NO_OPTION);
           if(close == JOptionPane.YES_OPTION)
           {

               DBConnect.connect();
               str="Delete from tbl_cand_general where Cand_Code=?;";
               DBConnect.conn.setAutoCommit(false);
               DB_Query.ps = DBConnect.conn.prepareStatement(str);
               DB_Query.ps.setString(1, txt_Cand_Code.getText());
               DB_Query.dclQuery("delete");

               //Delete from the tbl_cand_education table
               str="Delete from tbl_cand_education where Cand_Code=?;";
               DBConnect.connect();
               DBConnect.conn.setAutoCommit(false);
               DB_Query.ps = DBConnect.conn.prepareStatement(str);
               DB_Query.ps.setString(1, txt_Cand_Code.getText());
               DB_Query.dclQuery("delete");


                //Delete from the tbl_cand_professional table
               str="Delete from tbl_cand_professional where Cand_Code=?;";
               DBConnect.connect();
               DBConnect.conn.setAutoCommit(false);
               DB_Query.ps = DBConnect.conn.prepareStatement(str);
               DB_Query.ps.setString(1, txt_Cand_Code.getText());
               DB_Query.dclQuery("delete");

               //Delete from the tbl_cand_primary table
               str="Delete from tbl_cand_primary where Cand_Code=?;";
               DBConnect.connect();
               DBConnect.conn.setAutoCommit(false);
               DB_Query.ps = DBConnect.conn.prepareStatement(str);
               DB_Query.ps.setString(1, txt_Cand_Code.getText());
               DB_Query.dclQuery("delete");

               JOptionPane.showMessageDialog(null, "Data Has Been Deleted Successfully.");
           }
       }
       catch(SQLException e)
       {
           JOptionPane.showMessageDialog(null, "Error Occured :" + e);
       }        
    }
    
    void getHyperLink(JLabel lbl)
    {
        lbl.setForeground(Color.BLUE);
        lbl.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

           public void mouseExited(MouseEvent e) {
             setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
           public void mouseClicked(MouseEvent e) {

               try
               {                 
                    if(resume!=null)
                    Desktop.getDesktop().open( new File(resumeLocation) );
               }
               catch(IOException ie)
               {

                   JOptionPane.showMessageDialog(null, ie );
               }

            }
          });
        
    }
    void disableAllFields()
    {
        txt_Cand_Code.setEditable(false);
        txt_FName.setEditable(false);
        txt_LName.setEditable(false);
        txt_Cell_No.setEditable(false);
        txt_Home_Phone.setEditable(false);
        txt_Passport_No.setEditable(false);
        txt_Email_Id.setEditable(false);
        txt_Expected_Salary.setEditable(false);
        txt_Experience.setEditable(false);
        txt_Higher_Edu.setEditable(false);
        txt_University.setEditable(false);
        txt_Division.setEditable(false);
        txt_Percentage.setEditable(false);
        txa_Address.setEditable(false);
        //txa_Preferred_Jobs.setEditable(false);
        //cmb_Candidate_Type.setEditable(false);
        cmb_Preferred_Type.setEditable(false);
        cmb_recruited.setEnabled(false);
        cmb_Day.setEnabled(false);
        cmb_Month.setEnabled(false);
        cmb_Year.setEnabled(false);
        cmb_Preferred_Type.setEnabled(false);
        rdo_Male.setEnabled(false);
        rdo_Female.setEnabled(false);
        rdo_Managable_Yes.setEnabled(false);
        rdo_Managable_No.setEnabled(false);
        rdo_Employeed_Yes.setEnabled(false);
        rdo_Employeed_No.setEnabled(false);
        btn_Resume_Change.setEnabled(false);
        btn_Picture_Change.setEnabled(false);
    }

    

    void enableAllFields()
    {
        txt_FName.setEditable(true);
        txt_LName.setEditable(true);
        txt_Cell_No.setEditable(true);
        txt_Home_Phone.setEditable(true);
        txt_Passport_No.setEditable(true);
        txt_Email_Id.setEditable(true);
        txt_Expected_Salary.setEditable(true);
        txt_Experience.setEditable(true);
        txt_Higher_Edu.setEditable(true);
        txt_University.setEditable(true);
        txt_Division.setEditable(true);
        txt_Percentage.setEditable(true);
        txa_Address.setEditable(true);
        //txa_Preferred_Jobs.setEditable(true);
        //cmb_Candidate_Type.setEditable(false);
        cmb_Preferred_Type.setEditable(true);
        cmb_recruited.setEnabled(true);
        cmb_Day.setEnabled(true);
        cmb_Month.setEnabled(true);
        cmb_Year.setEnabled(true);
        cmb_Preferred_Type.setEnabled(true);
        rdo_Male.setEnabled(true);
        rdo_Female.setEnabled(true);
        rdo_Managable_Yes.setEnabled(true);
        rdo_Managable_No.setEnabled(true);
        rdo_Employeed_Yes.setEnabled(true);
        rdo_Employeed_No.setEnabled(true);
        btn_Resume_Change.setEnabled(true);
        btn_Picture_Change.setEnabled(true);
    }

    void clearAllFields()
    {
        ImageIcon pic = new ImageIcon("Pictures/none.jpg");
        lbl_Image.setIcon(pic);
        searched_picture=picture=found_pic=null;
        searched_resume=resume=null;
        gender="M";
        managable="Y";
        lbl_Resume_Source.setText("Null");
        txt_Cand_Code.setText("");
        txt_FName.setText("");
        txt_LName.setText("");
        txt_Cell_No.setText("");
        txt_Home_Phone.setText("");
        txt_Passport_No.setText("");
        txt_Email_Id.setText("");
        txt_Expected_Salary.setText("");
        txt_Experience.setText("");
        txt_Higher_Edu.setText("");
        txt_University.setText("");
        txt_Division.setText("");
        txt_Percentage.setText("");
        txa_Address.setText("");
        cmb_Preferred_Jobs.setSelectedIndex(0);
        //cmb_Candidate_Type.setEditable(false);
        cmb_Preferred_Type.setSelectedIndex(0);
        cmb_recruited.setSelectedIndex(0);
        cmb_Day.setSelectedIndex(0);
        cmb_Month.setSelectedIndex(0);
        cmb_Year.setSelectedItem(String.valueOf(DOB_year));
        cmb_Preferred_Type.setSelectedIndex(0);
        rdo_Male.setSelected(true);
        rdo_Female.setSelected(false);
        rdo_Managable_Yes.setSelected(true);
        rdo_Managable_No.setSelected(false);
        rdo_Employeed_Yes.setSelected(false);
        rdo_Employeed_No.setSelected(true);
        lbl_Resume_Source.setText("None");
        lbl_Resume_Source.setIcon(new ImageIcon(""));
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
        if(objValid.validName(txt_FName.getText())==false)
        {
            txt_FName.requestFocus();
            return false;
        }
        if(objValid.validCellNo(txt_Cell_No.getText())== false)
        {
            txt_Cell_No.requestFocus();
            return false;
        }
        if(objValid.validHomePhone(txt_Home_Phone.getText())== false)
        {
            txt_Home_Phone.requestFocus();
            return false;
        }


        if(objValid.validPassPort(txt_Passport_No.getText())== false)
        {
            txt_Passport_No.requestFocus();
            return false;
        }


         if(objValid.validHigherEdu(txt_Higher_Edu.getText())== false)
        {
            txt_Higher_Edu.requestFocus();
            return false;
        }

         if(objValid.validUniversity(txt_University.getText())== false)
        {
            txt_University.requestFocus();
            return false;
        }

        if(objValid.validDivClass(txt_Division.getText())== false)
        {
            txt_Division.requestFocus();
            return false;

        }

        if(objValid.validPercentage(txt_Percentage.getText())== false)
        {
            txt_Percentage.requestFocus();
            return false;

        }
         if(objValid.validExperience(txt_Experience.getText())== false)
        {
            txt_Experience.requestFocus();
            return false;

        }

        if(objValid.validExpectedSal(txt_Expected_Salary.getText())== false)
        {
            txt_Expected_Salary.requestFocus();
            return false;

        }

         
        if(objValid.validEmailId(txt_Email_Id.getText())== false)
        {
           txt_Email_Id.requestFocus();
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