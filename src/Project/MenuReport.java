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
import javax.swing.event.*;

public class MenuReport  extends JMenu{

    JMenuItem mnu_Candidate=new JMenuItem("Siswa");
    JMenuItem mnu_Company= new JMenuItem("Guru");
    JMenuItem mnu_Job= new JMenuItem("Pelajaran");


    private MDIDesktopPane desktop;
    public MenuReport(MDIDesktopPane desktop)
    {
        this.desktop= desktop;
        this.setText("Search/Report");
       this.setMnemonic('S');
       add(mnu_Candidate);
       mnu_Candidate.setMnemonic('c');
       mnu_Candidate.setAccelerator(
				KeyStroke.getKeyStroke(
				KeyEvent.VK_C,ActionEvent.ALT_MASK
					)
				);
       mnu_Candidate.setIcon(new ImageIcon("icons/small_cand_report.png"));
       mnu_Candidate.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
                //JOptionPane.showMessageDialog(null, "Test");
                MenuReport.this.desktop.add(new Form_CandidateTbl());
          }
        });
        add(mnu_Company);
        mnu_Company.setMnemonic('o');
        mnu_Company.setAccelerator(
				KeyStroke.getKeyStroke(
				KeyEvent.VK_O,ActionEvent.ALT_MASK 
					)
				);
        mnu_Company.setIcon(new ImageIcon("icons/small_comp_report.png"));
        mnu_Company.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
            MenuReport.this.desktop.add(new Form_CompanyTbl());
          }
        });


        add(mnu_Job);
        mnu_Job.setMnemonic('j');
        mnu_Job.setAccelerator(
				KeyStroke.getKeyStroke(
				KeyEvent.VK_J,ActionEvent.ALT_MASK
					)
				);
        mnu_Job.setIcon(new ImageIcon("icons/small_job_report.png"));
        mnu_Job.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
            MenuReport.this.desktop.add(new Form_JobTbl());
          }
        });
    }
}
