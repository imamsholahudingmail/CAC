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



class MenuProfile extends JMenu{
    private MDIDesktopPane desktop;
     JMenuItem mnu_Candidate=new JMenuItem("Siswa");
     JMenuItem mnu_Company= new JMenuItem("Guru");
     JMenuItem mnu_Job= new JMenuItem("Pelajaran");

    public MenuProfile(MDIDesktopPane desktop)
    {
        this.desktop= desktop;
       setText("Profil");
       this.setMnemonic('P');
       add(mnu_Candidate);
       mnu_Candidate.setMnemonic('C');
       mnu_Candidate.setAccelerator(
				KeyStroke.getKeyStroke(
				KeyEvent.VK_C,ActionEvent.CTRL_MASK
					)
				);
       mnu_Candidate.setIcon(new ImageIcon("icons/small_candidate.png"));
       mnu_Candidate.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        MenuProfile.this.desktop.add(new Form_Candidate());
          }
        });
        add(mnu_Company);
        mnu_Company.setMnemonic('o');
        mnu_Company.setAccelerator(
				KeyStroke.getKeyStroke(
				KeyEvent.VK_O,ActionEvent.CTRL_MASK
					)
				);
        mnu_Company.setIcon(new ImageIcon("icons/small_company.png"));
        mnu_Company.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
        MenuProfile.this.desktop.add(new Form_Company());
          }
        });
        add(mnu_Job);
        mnu_Job.setMnemonic('j');
        mnu_Job.setAccelerator(
				KeyStroke.getKeyStroke(
				KeyEvent.VK_J,ActionEvent.CTRL_MASK
					)
                              );
        mnu_Job.setIcon(new ImageIcon("icons/small_job.png"));
        mnu_Job.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
        MenuProfile.this.desktop.add(new Form_Jobs());
          }
        });       
        
    }
}
