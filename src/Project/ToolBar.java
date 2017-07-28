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
import javax.swing.event.*;

class ToolBar extends JToolBar
{ 
        private MDIDesktopPane desktop;
        public JButton btn_Candidate,btn_Company,btn_Job,btn_Candidate_Report,
                btn_Job_Report,btn_Company_Report,btn_Calculator,btn_Notepad,btn_Logout;
      public ToolBar()
      {
         
          this.btn_Candidate = new JButton(new ImageIcon("icons/candidate.png"));
          this.btn_Candidate.setToolTipText("Data Siswa");
          
          this.btn_Company = new JButton(new ImageIcon("icons/company.png"));
          this.btn_Company.setToolTipText("Data Guru");

          this.btn_Job = new JButton(new ImageIcon("icons/job.png"));
          this.btn_Job.setToolTipText("Data Pelajaran");

          this.btn_Candidate_Report = new JButton(new ImageIcon("icons/candidate_report.png"));
          this.btn_Candidate_Report.setToolTipText("Laporan Siswa");

          this.btn_Company_Report = new JButton(new ImageIcon("icons/company_report.png"));
          this.btn_Company_Report.setToolTipText("Laporan Guru");

          this.btn_Company_Report = new JButton(new ImageIcon("icons/company_report.png"));
          this.btn_Company_Report.setToolTipText("Laporan Guru");

          this.btn_Job_Report = new JButton(new ImageIcon("icons/job_report.png"));
          this.btn_Job_Report.setToolTipText("Laporan Pelajaran"
                  + "");

          this.btn_Calculator = new JButton(new ImageIcon("icons/calculator.png"));
          this.btn_Calculator.setToolTipText("Calculator");

          this.btn_Notepad = new JButton(new ImageIcon("icons/notepad.png"));
          this.btn_Notepad.setToolTipText("Notepad");

          this.btn_Logout = new JButton(new ImageIcon("icons/logout.png"));
          this.btn_Logout.setToolTipText("Logout");
          
          this.add(btn_Candidate);
          this.add(btn_Company);
          this.add(btn_Job);
          this.add(btn_Candidate_Report);
          this.add(btn_Company_Report);
          this.add(btn_Job_Report);
          this.add(btn_Calculator);
          this.add(btn_Notepad);
          this.add(btn_Logout);
      }
  }
