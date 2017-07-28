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

public class MenuControl  extends JMenu{


    JMenuItem mnu_UserPreferences= new JMenuItem("Pengaturan Pengguna");
    JMenuItem mnu_UserLogs= new JMenuItem("Log Pengguna");
    JMenuItem mnu_Logout= new JMenuItem("Logout");


    private MDIDesktopPane desktop;
    public MenuControl(MDIDesktopPane desktop)
    {
        this.desktop= desktop;
        this.setText("Pengaturan");
        this.setMnemonic('t');
       
        add(mnu_UserPreferences);
        mnu_UserPreferences.setMnemonic('s');
        mnu_UserPreferences.setAccelerator(
				KeyStroke.getKeyStroke(
				KeyEvent.VK_S,ActionEvent.CTRL_MASK
					)
				);
        mnu_UserPreferences.setIcon(new ImageIcon("icons/small_user_setting.png"));
        mnu_UserPreferences.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
            MenuControl.this.desktop.add(new Form_RegUser());
          }
        });
        add(mnu_UserLogs);
        mnu_UserLogs.setMnemonic('l');
        mnu_UserLogs.setAccelerator(
				KeyStroke.getKeyStroke(
				KeyEvent.VK_L,ActionEvent.CTRL_MASK
					)
				);
        mnu_UserLogs.setIcon(new ImageIcon("icons/small_user_logs.png"));
        mnu_UserLogs.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
        MenuControl.this.desktop.add(new Form_UserLog());
          }
        });     
    }    
}